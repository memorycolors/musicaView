package com.mycompany.musicaview;

import com.mycompany.musicaview.emtity.Cancion;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javax.persistence.EntityManager;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.persistence.Query;

public class PrimaryController implements Initializable {

    private EntityManager entityManager;
    private Titulo tituloSeleccionada;
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
    private TextField textFieldAlbum;

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
        columnaTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        columnaAlbum.setCellValueFactory(new PropertyValueFactory<>("album"));
        columnaCantante.setCellValueFactory(new PropertyValueFactory<>("cantante"));

        columnaAlbum.setCellValueFactory(
                cellData -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue().getAlbum() != null) {
                        property.setValue(cellData.getValue().getAlbum().getNombre());
                    }
                    return property;
                });
        tableViewContactos.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    tituloSeleccionada = newValue;
                    if (tituloSeleccionada != null) {
                        textFieldTitulo.setText(tituloSeleccionada.getTitulo());
                        textFieldAlbum.setText(tituloSeleccionada.getAlbum());
                        textFieldCantante.setText(tituloSeleccionada.getCantante());

                    } else {
                        textFieldTitulo.setText("");
                        textFieldAlbum.setText("");
                        textFieldCantante.setText("");
                    }
                });

    }

    @FXML
    private void onActionButtonGuardar(ActionEvent event) {
        if (tituloSeleccionada != null) {
            tituloSeleccionada.setTitulo(textFieldTitulo.getText());
            tituloSeleccionada.setAlbum(textFieldAlbum.getText());
            tituloSeleccionada.setCantante(textFieldCantante.getText());
            entityManager.getTransaction().begin();
            entityManager.merge(tituloSeleccionada);
            entityManager.getTransaction().commit();

            int numFilaSeleccionada = tableViewContactos.getSelectionModel().getSelectedIndex();
            tableViewContactos.getItems().set(numFilaSeleccionada, tituloSeleccionada);
            TablePosition pos = new TablePosition(tableViewContactos, numFilaSeleccionada, null);
            tableViewContactos.getFocusModel().focus(pos);
            tableViewContactos.requestFocus();
        }
    }
}
