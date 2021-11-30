package com.mnemoc.Controller;

import com.mnemoc.Models.DataModel;
import com.mnemoc.Models.Deck;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class MainController {

    @FXML
    private CreateDeckController createDeckController;

    private DataModel dataModel;

    @FXML
    ListView<Deck> deckList;

    private ObservableList<Deck> items = FXCollections.observableArrayList();

    @FXML
    public void createDeckDialog(ActionEvent e) {
        try {
            FXMLLoader loader = new FXMLLoader(MainController.class.getResource("/com/mnemoc/create_deck.fxml"));
            Parent root = (Parent) loader.load();
            createDeckController = loader.getController();

            Stage stage = new Stage();
            Scene scene = new Scene(root);
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

        deckList.setCellFactory(lv -> new ListCell<Deck>() {
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
}