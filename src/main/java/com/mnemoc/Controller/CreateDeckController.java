package com.mnemoc.Controller;

import com.mnemoc.Models.DataModel;
import com.mnemoc.Models.Deck;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CreateDeckController {

    private DataModel model;

    private MainController controller;

    private String text;

    @FXML
    Button ok;
    
    @FXML
    TextField deckName;

    @FXML
    public void createDeck(ActionEvent event) {
        try {
            DataModel a = model;
            text = deckName.getText();
            a.setCurrentDeck(new Deck(text));
            closeStage();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void verifyDeckNameExistence(ActionEvent e) {}

    @FXML
    private void closeStage(){
        Stage stage = (Stage) ok.getScene().getWindow();
        stage.close();
    }

    public void initModel(DataModel model) {
        if(this.model != null)  throw new IllegalStateException("Model can only be initialized once");
        this.model = model;
    }
}
