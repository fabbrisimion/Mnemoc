package com.mnemoc.Controller;

import com.mnemoc.Models.Card;
import com.mnemoc.Models.DataModel;
import com.mnemoc.Utils.Scheduler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

public class StudyDeckController implements Initializable {

    private DataModel model;
    private Card currCard;
    private static boolean studied = false;
    private static boolean firstCard = true;

    @FXML
    AnchorPane pane;

    @FXML
    HBox hbox;

    @FXML
    Label front;

    @FXML
    Label back;

    @FXML
    Button showAnswear;

    class ButtonHandler implements EventHandler<ActionEvent>{

        private final String type;

        ButtonHandler(String s){
            type = s;
        }

        @Override
        public void handle(ActionEvent mouseEvent) {
            Long time = System.currentTimeMillis();
            Scheduler.schedule(currCard, type);
            currCard.setLastRev(time);
            //model.updateCard(currCard);
            System.out.println(currCard);
            model.nextCard();
            currCard = model.getCard();
            setCardFields(currCard);
        }
    }

    private void setCardFields(Card cd){
        front.setText(cd.getFront());
        back.setText(cd.getBack());
        back.setVisible(false);
    }

    public StudyDeckController(DataModel model){
        this.model = model;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        currCard = model.getCard();
        setCardFields(currCard);
        showAnswear.setOnAction(event -> back.setVisible(true));
        for (Node b : hbox.getChildren()) {
            Button button = (Button)b;
            button.setOnAction(new ButtonHandler(button.getText()));
        }
    }
}
