package com.mnemoc.Controller;

import com.mnemoc.Models.DataModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class StudyDeckController {

    private DataModel model;

    @FXML
    GridPane gridPane;

    @FXML
    Label front;

    @FXML
    Label back;

    @FXML
    Button easy;

    @FXML
    Button medium;

    @FXML
    Button hard;

    public StudyDeckController(DataModel model){
        this.model = model;
    }

}
