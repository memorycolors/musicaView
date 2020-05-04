package com.mycompany.musicaview;

import com.mycompany.musicaview.emtity.Cancion;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javax.persistence.EntityManager;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.persistence.Query;

public class PrimaryController implements Initializable {

    private EntityManager entityManager;
    @FXML
    private TableColumn<Cancion, String> columnaTitulo;
    @FXML
    private TableColumn<Cancion, String> columnaAlbum;
    @FXML
    private TableColumn<Cancion, String> columnaCantante;
    @FXML
    private TableView<Cancion> tableViewmusica;

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
        columnaTitulo.setCellValueFactory(new PropertyValueFactory<>("Titulo"));
        columnaAlbum.setCellValueFactory(new PropertyValueFactory<>("Album"));
        columnaCantante.setCellValueFactory(new PropertyValueFactory<>("Cantante"));
    }
}
