package com.mycompany.musicaview;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * JavaFX App
 */
public class App extends Application {

    private EntityManagerFactory emf;
    private EntityManager em;
    private static Scene scene;
    private static PrimaryController primaryController;

    @Override

    public void start(Stage primaryStage) throws IOException {

        emf = Persistence.createEntityManagerFactory("musicaPU");
        em = emf.createEntityManager();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("primary.fxml"));
        Parent root = fxmlLoader.load();
        PrimaryController musicaViewController = (PrimaryController) fxmlLoader.getController();
        musicaViewController.setEntityManager(em);
       //musicaViewController.cargarcanciones();
        Scene scene = new Scene(root, 300, 250);

    }

     void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private  Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        primaryController = (PrimaryController) fxmlLoader.getController();
        System.out.println("PrimaryControler:" + primaryController);
        // Después de obtener el objeto del controlador y del EntityManager:
        primaryController.setEntityManager(em);
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();

    }

    @Override
    public void stop() throws Exception {
        em.close();
        emf.close();
        try {
            DriverManager.getConnection("jdbc:derby:BDmusica;shutdown=true");
        } catch (SQLException ex) {
        }
    }

}
