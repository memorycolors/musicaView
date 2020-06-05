package com.mycompany.musicaview;

import com.mycompany.musicaview.emtity.Genero;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javax.persistence.EntityManager;


public class SecondaryController {
    private EntityManager entityManager;
    
    private Genero genero;
    
    private Pane rootPrimary;
    @FXML
    private TextField textFeldCantante;
    @FXML
    private TextField textFeldTitulo;
    @FXML
    private TextField textFeldAlbum;
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
    private void onActionButtonCancelar(ActionEvent event) {
    }

    @FXML
    private void onActionButtonGuardar(ActionEvent event) {
    
    
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
