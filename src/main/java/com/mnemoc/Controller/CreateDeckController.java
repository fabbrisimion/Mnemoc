package com.mnemoc.Controller;

import com.mnemoc.Models.DataModel;
import com.mnemoc.Models.Deck;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateDeckController implements Initializable {

    private DataModel model;

    @FXML
    private Button ok;
    
    @FXML
    TextField deckName;

    public CreateDeckController(DataModel model){
        this.model = model;
    }

    public void createDeck() {
        try {
            Deck dk = new Deck();
            dk.setDid(System.currentTimeMillis());
            dk.setName(deckName.getText());
            System.out.println(dk.getDid());
            if(!dk.getName().equals(""))
                model.addDeck(dk);
            closeStage();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private boolean duplicateDeck(Deck dk) {
        return true;
    }

    public void closeStage(){
        Stage stage = (Stage) ok.getScene().getWindow();
        stage.close();
    }

    public void initModel(DataModel dataModel) {
        if(this.model != null)  throw new IllegalStateException("Model can only be initialized once");
        this.model = dataModel;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ok.setOnAction(e -> createDeck());
    }
}
