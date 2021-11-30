package com.mnemoc;

import com.mnemoc.Controller.CreateDeckController;
import com.mnemoc.Controller.MainController;
import com.mnemoc.Models.DataModel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        //Loading main_view stage and associated MainController
        MainController mainController = new MainController();
        FXMLLoader mainLoader = new FXMLLoader(Main.class.getResource("main_view.fxml"));
        mainLoader.setController(mainController);
        AnchorPane root = (AnchorPane) mainLoader.load();

        CreateDeckController newDeckController = new CreateDeckController();
        FXMLLoader newDeckLoader = new FXMLLoader(Main.class.getResource("create_deck.fxml"));
        newDeckLoader.setController(newDeckController);
        AnchorPane newDeckPane = (AnchorPane) newDeckLoader.load();

        DataModel model = new DataModel();
        model.loadData();
        mainController.initModel(model);
        newDeckController.initModel(model);

        Scene scene = new Scene(root);
        stage.setTitle("Mnemoc");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}