package com.mnemoc;

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

        DataModel model = new DataModel();
        model.loadDecks();

        //Loading main_view stage and associated MainController
        MainController mainController = new MainController(model);
        FXMLLoader mainLoader = new FXMLLoader(Main.class.getResource("main_view.fxml"));
        mainLoader.setController(mainController);
        AnchorPane root = mainLoader.load();


        Scene scene = new Scene(root);
        stage.setTitle("Mnemoc");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}