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

    private StudyDeckController studyDeckController;
    private AddCardsController addCardsController;
    private CreateDeckController createDeckController;
    private ObservableList<Deck> items = FXCollections.observableArrayList();
    private DataModel dataModel;

    @FXML
    ListView<Deck> deckList;

    @FXML
    Button addCards;

    @FXML
    Button studyDeck;

    @FXML
    Button deleteDeck;

    //@FXML
    //Button settings;

    @FXML
    Button createDeck;

    public MainController(DataModel model){
        dataModel = model;
        addCardsController = new AddCardsController(model);
        createDeckController = new CreateDeckController(model);
        studyDeckController = new StudyDeckController(model);
    }

    @FXML
    public void createDeckWindow() {
        try {
            FXMLLoader newDeckLoader = new FXMLLoader(Main.class.getResource("create_deck.fxml"));
            newDeckLoader.setController(createDeckController);
            AnchorPane newDeckPane = newDeckLoader.load();

            Stage stage = new Stage();
            Scene scene = new Scene(newDeckPane);
            stage.setScene(scene);
            stage.show();
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void addCardsWindow(){
        try {
            FXMLLoader addCardsLoader = new FXMLLoader(Main.class.getResource("add_cards.fxml"));
            addCardsLoader.setController(addCardsController);
            AnchorPane addCardsPane = addCardsLoader.load();

            Stage stage = new Stage();
            Scene scene = new Scene(addCardsPane);
            stage.setScene(scene);
            stage.show();
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void studyDeckWindow(){
        try {
            FXMLLoader newDeckLoader = new FXMLLoader(Main.class.getResource("study_deck.fxml"));
            newDeckLoader.setController(studyDeckController);
            AnchorPane newDeckPane = newDeckLoader.load();

            Stage stage = new Stage();
            Scene scene = new Scene(newDeckPane);
            stage.setScene(scene);
            stage.show();
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void deleteSelectedDeck(){
        Deck delDeck = dataModel.getCurrentDeck();
    }

    //public void settingsWindow(){}

    public void initModel(){

        //Passes the dataModel Observable list to the ListView
        deckList.setItems(dataModel.getDeckList());

        deckList.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) ->
                dataModel.setCurrentDeck(newSelection));

        dataModel.currentDeckProperty().addListener((obs, oldDeck, newDeck) -> {
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        createDeck.setOnAction(event -> createDeckWindow());
        addCards.setOnAction(event -> addCardsWindow());
        studyDeck.setOnAction(event -> studyDeckWindow());
        deleteDeck.setOnAction(event -> deleteSelectedDeck());
        //settings.setOnAction(event -> settingsWindow());
    }
}