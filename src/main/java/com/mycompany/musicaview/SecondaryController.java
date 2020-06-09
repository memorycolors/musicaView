package com.mycompany.musicaview;

import com.mycompany.musicaview.emtity.Album;
import com.mycompany.musicaview.emtity.Cancion;
import com.mycompany.musicaview.emtity.Genero;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javax.persistence.EntityManager;

public class SecondaryController {

    private EntityManager entityManager;
    private TableView tableViewPrevio;
    private Genero genero;
    private Album album;
    private Pane rootPrimary;
    private Cancion cancion;
    private boolean nuevaCancion;
    @FXML
    private TextField textFeldCantante;
    @FXML
    private TextField textFeldTitulo;
    @FXML
    private TextField textFeldNCanciones;
    @FXML
    private DatePicker datePickerFechaLanzamiento;
    @FXML
    private TextField textFieldPrecio;
    @FXML
    private CheckBox checkBoxSi;
    @FXML
    private ComboBox<Genero> comboBoxGenero;
    @FXML
    private ImageView ImageViewFoto;
    @FXML
    private AnchorPane rootDetalle;
    @FXML
    private ComboBox<Album> comboBoxAlbum;

    public void setTableViewPrevio(TableView tableViewPrevio) {
        this.tableViewPrevio = tableViewPrevio;
    }

    public void setCancion(EntityManager entityManager, Cancion cancion, boolean nuevaCancion) {
        this.entityManager = entityManager;

        if (!nuevaCancion) {
            this.cancion = entityManager.find(Cancion.class, cancion.getId());
        } else {
            this.cancion = cancion;
        }
        this.nuevaCancion = nuevaCancion;

    }

    public void mostrarDatos() {
        textFeldCantante.setText(cancion.getCantante());
        textFeldTitulo.setText(cancion.getTitulo());
        
    }

    @FXML
    private void onActionButtonCancelar(ActionEvent event) {
        StackPane rootMain = (StackPane) rootDetalle.getScene().getRoot();
        rootMain.getChildren().remove(rootDetalle);
        rootPrimary.setVisible(true);
        entityManager.getTransaction().rollback();

        int numFilaSeleccionada = tableViewPrevio.getSelectionModel().getSelectedIndex();
        TablePosition pos = new TablePosition(tableViewPrevio, numFilaSeleccionada, null);
        tableViewPrevio.getFocusModel().focus(pos);
        tableViewPrevio.requestFocus();
    }

    @FXML
    private void onActionButtonGuardar(ActionEvent event) {
        StackPane rootMain = (StackPane) rootDetalle.getScene().getRoot();
        rootMain.getChildren().remove(rootDetalle);
        cancion.setCantante(textFeldCantante.getText());
        cancion.setTitulo(textFeldTitulo.getText());
        
        int numFilaSeleccionada;
        if (nuevaCancion) {
            entityManager.getTransaction().begin();
            tableViewPrevio.getItems().add(cancion);
            numFilaSeleccionada = tableViewPrevio.getItems().size() - 1;
            tableViewPrevio.getSelectionModel().select(numFilaSeleccionada);
            tableViewPrevio.scrollTo(numFilaSeleccionada);
            entityManager.merge(cancion);
            entityManager.persist(cancion);
            entityManager.getTransaction().commit();
        } else {
            numFilaSeleccionada = tableViewPrevio.getSelectionModel().getSelectedIndex();
            tableViewPrevio.getItems().set(numFilaSeleccionada, cancion);

        }

        TablePosition pos = new TablePosition(tableViewPrevio, numFilaSeleccionada, null);
        tableViewPrevio.getFocusModel().focus(pos);
        tableViewPrevio.requestFocus();
        rootPrimary.setVisible(true);
    }

    @FXML
    private void onActionButtonExaminar(ActionEvent event) {
    }

    public void setRootPrimary(Pane rootPrimary) {
        this.rootPrimary = rootPrimary;
    }

}

////   public void initialize(URL url, ResourceBundle rb) {
////       comboBoxGenero.setConverter(new StringConverter<Genero>() {
////           @Override
////           public String toString(Genero genero) {
////               if (genero == null) {
////                   return null;
////               } else {
////                   return genero.getNombre();
////               }
//           }
