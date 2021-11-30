package com.mnemoc.Controller;

import com.mnemoc.Main;
import com.mnemoc.Models.DataModel;
import com.mnemoc.Models.Deck;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private CreateDeckController createDeckController;

    private DataModel dataModel;

    @FXML
    ListView<Deck> deckList;

    @FXML
    Button createDeck;

    private ObservableList<Deck> items = FXCollections.observableArrayList();

    @FXML
    public void createDeckDialog() {
        try {
            CreateDeckController newDeckController = new CreateDeckController();
            FXMLLoader newDeckLoader = new FXMLLoader(Main.class.getResource("create_deck.fxml"));
            newDeckLoader.setController(newDeckController);
            AnchorPane newDeckPane = newDeckLoader.load();
            newDeckController.initModel(dataModel);

            Stage stage = new Stage();
            Scene scene = new Scene(newDeckPane);
            stage.setScene(scene);
            stage.show();
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void initModel(DataModel model){
        if (this.dataModel != null)
            throw new IllegalStateException("Model can only be initialized once");

        this.dataModel = model;
        deckList.setItems(model.getDeckList());

        deckList.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) ->
                model.setCurrentDeck(newSelection));

        model.currentDeckProperty().addListener((obs, oldDeck, newDeck) -> {
            if (newDeck == null) {
                deckList.getSelectionModel().clearSelection();
            } else {
                deckList.getSelectionModel().select(newDeck);
            }
        });

        deckList.setCellFactory(lv -> new ListCell<>() {
            @Override
            public void updateItem(Deck deck, boolean empty) {
                super.updateItem(deck, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(deck.getName());
                }
            }
        });
    }

    public void setModel(CreateDeckController controller){
        createDeckController = controller;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        createDeck.setOnAction(event -> this.createDeckDialog());
    }
}