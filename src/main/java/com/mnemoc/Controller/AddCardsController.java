package com.mnemoc.Controller;

import com.mnemoc.Models.Card;
import com.mnemoc.Models.DataModel;
import com.mnemoc.Models.Deck;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;

public class AddCardsController implements Initializable {

    private DataModel model;
    private Deck currentDeck;
    private String labelText;

    @FXML
    Label addLabel;

    @FXML
    TextArea frontField;

    @FXML
    TextArea backField;

    @FXML
    Button add;

    public AddCardsController(DataModel model){
        this.model = model;
        currentDeck = this.model.getCurrentDeck();
    }

    public void addCard(){
        try {
            Card cd = new Card();
            cd.setCid(System.currentTimeMillis());
            cd.setDid(currentDeck.getDid());
            cd.setFront(frontField.getText());
            cd.setBack(backField.getText());

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String lbl = "Deck: " + currentDeck.getName();
        addLabel.setText(lbl);
        add.setOnAction(event -> addCard()); }
}
