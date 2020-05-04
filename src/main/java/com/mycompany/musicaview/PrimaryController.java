package com.mycompany.musicaview;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javax.persistence.EntityManager;

public class PrimaryController implements Initializable {
    
    
    private EntityManager entityManager;
    @FXML
    private TableColumn<Cancion, String> columnaTitulo;
    @FXML
    private TableColumn<Cancion, String> columnaAlbum;
    @FXML
    private TableColumn<Cancion, String> columnaCantante;
    
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
   
   
    
    

}

