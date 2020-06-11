package com.mycompany.musicaview;

import com.mycompany.musicaview.emtity.Album;
import com.mycompany.musicaview.emtity.Cancion;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javax.persistence.EntityManager;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;
import javax.persistence.Query;

public class PrimaryController implements Initializable {

    private EntityManager entityManager;
    private Cancion cancionSeleccionada;
    @FXML
    private TableColumn<Cancion, String> columnaTitulo;
    @FXML
    private TableColumn<Cancion, String> columnaAlbum;
    @FXML
    private TableColumn<Cancion, String> columnaCantante;
    @FXML
    private TableView<Cancion> tableViewmusica;
    @FXML
    private TextField textFieldTitulo;
    @FXML
    private TextField textFieldCantante;

    @FXML
    private AnchorPane rootPrimary;
    @FXML
    private ComboBox<Album> ComboBoxAlbum;
    @FXML
    private VBox VBoxPrimary;

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;

    }

    public void cargarCanciones() {
        Query queryCancionFindAll = entityManager.createNamedQuery("Cancion.findAll");
        List<Cancion> listCancion = queryCancionFindAll.getResultList();
        tableViewmusica.setItems(FXCollections.observableArrayList(listCancion));
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        ComboBoxAlbum.setConverter(new StringConverter<Album>() {
            @Override
            public String toString(Album album) {
                if (album == null) {
                    return null;
                } else {
                    return album.getNombre();
                }
            }

            @Override
            public Album fromString(String Nombre) {
                return null;
            }
        }
        );
        columnaTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        columnaCantante.setCellValueFactory(new PropertyValueFactory<>("cantante"));
        columnaAlbum.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue().getAlbum() != null) {
                        property.setValue(cellData.getValue().getAlbum().getNombre());
                    }
                    return property;
                });
        tableViewmusica.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            cancionSeleccionada = newValue;
            if (cancionSeleccionada != null) {
                textFieldTitulo.setText(cancionSeleccionada.getTitulo());
                if (cancionSeleccionada.getAlbum() != null) {
                    ComboBoxAlbum.setValue(cancionSeleccionada.getAlbum());
                }
                textFieldCantante.setText(cancionSeleccionada.getCantante());

            } else {
                textFieldTitulo.setText("");
                textFieldCantante.setText("");
            }
        });

    }

    @FXML
    private void onActionButtonGuardar(ActionEvent event) {
        if (cancionSeleccionada != null) {
            cancionSeleccionada.setTitulo(textFieldTitulo.getText());
            // cancionSeleccionada.setAlbum(textFieldAlbum.getText());
            cancionSeleccionada.setCantante(textFieldCantante.getText());
            cancionSeleccionada.setAlbum(ComboBoxAlbum.getValue());
            entityManager.getTransaction().begin();
            entityManager.merge(cancionSeleccionada);
            entityManager.getTransaction().commit();

            int numFilaSeleccionada = tableViewmusica.getSelectionModel().getSelectedIndex();
            tableViewmusica.getItems().set(numFilaSeleccionada, cancionSeleccionada);
            TablePosition pos = new TablePosition(tableViewmusica, numFilaSeleccionada, null);
            tableViewmusica.getFocusModel().focus(pos);
            tableViewmusica.requestFocus();

        }
    }

    @FXML
    private void onActionButtonEditar(ActionEvent event) {
        // Cargar la vista de detalle
        int numFilaSeleccionada = tableViewmusica.getSelectionModel().getSelectedIndex();
        if (numFilaSeleccionada != -1) {
            try {
                // Cargar la vista de detalle
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("secondary.fxml"));
                Parent rootDetalle = fxmlLoader.load();
                SecondaryController secondaryController = (SecondaryController) fxmlLoader.getController();
                secondaryController.setRootPrimary(VBoxPrimary);

                secondaryController.setTableViewPrevio(tableViewmusica);
                secondaryController.setCancion(entityManager, cancionSeleccionada, false);
                secondaryController.mostrarDatos();
                // Ocultar la vista de la lista
                VBoxPrimary.setVisible(false);

                // Añadir la vista de detalle al StackPane principal para que se muestre
                StackPane rootMain = (StackPane) VBoxPrimary.getScene().getRoot();
                rootMain.getChildren().add(rootDetalle);
            } catch (IOException e) {
                Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, e);

            }

        } else {
            Alert alert = new Alert(AlertType.WARNING, "No ha saleccionado una cancion");
            alert.showAndWait();
        }

    }

    @FXML
    private void onActionButtonSuprimir(ActionEvent event) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmar");
        alert.setHeaderText("¿Desea suprimir el siguiente registro?");
        alert.setContentText(cancionSeleccionada.getCantante() + " "
                + cancionSeleccionada.getTitulo());
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            // Acciones a realizar si el usuario acepta
            entityManager.getTransaction().begin();
            cancionSeleccionada = entityManager.merge(cancionSeleccionada);
            entityManager.remove(cancionSeleccionada);
            entityManager.getTransaction().commit();

            tableViewmusica.getItems().remove(cancionSeleccionada);

            tableViewmusica.getFocusModel().focus(null);
            tableViewmusica.requestFocus();
        } else {
            // Acciones a realizar si el usuario cancela
            int numFilaSeleccionada = tableViewmusica.getSelectionModel().getSelectedIndex();
            tableViewmusica.getItems().set(numFilaSeleccionada, cancionSeleccionada);
            TablePosition pos = new TablePosition(tableViewmusica, numFilaSeleccionada, null);
            tableViewmusica.getFocusModel().focus(pos);
            tableViewmusica.requestFocus();
        }
    }

    @FXML
    private void onActionButtonNuevo(ActionEvent event) {
        // Cargar la vista de detalle

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Secondary.fxml"));
            Parent rootDetalleView = fxmlLoader.load();
            SecondaryController sencondaryController = (SecondaryController) fxmlLoader.getController();
            sencondaryController.setRootPrimary(VBoxPrimary);
            sencondaryController.setTableViewPrevio(tableViewmusica);
            
            cancionSeleccionada = new Cancion();
            sencondaryController.setCancion(entityManager, cancionSeleccionada, true);
            sencondaryController.mostrarDatos();
            
            VBoxPrimary.setVisible(false);
            
            StackPane rootMain = (StackPane) VBoxPrimary.getScene().getRoot();
            rootMain.getChildren().add(rootDetalleView);
        } catch (IOException e) {
                Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, e);

            }
    }

}
