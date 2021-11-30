package com.mnemoc.Controller;

import com.mnemoc.Models.DataModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateDeckController implements Initializable {

    private DataModel model;

    private MainController controller;

    private String text;

    @FXML
    private Button ok;
    
    @FXML
    TextField deckName;

    @FXML
    public void createDeck() {
        try {
            text = deckName.getText();
            if(!text.equals(""))
                model.addToDeckList(text);
            closeStage();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void verifyDeckNameExistence(ActionEvent e) {}

    @FXML
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
