package com.mnemoc.Models;

import com.mnemoc.Utils.Database;
import javafx.beans.Observable;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DataModel {

    private Database db;

    //Deck table
    private final ObservableList<Deck> deckList = FXCollections.observableArrayList(deck ->
        new Observable[] {deck.nameProperty()});

    private final ObservableList<Card> cards = FXCollections.observableArrayList(card ->
            new Observable[] {card.frontProperty()});

    private final ObjectProperty<Deck> currentDeck = new SimpleObjectProperty<>(null);

    public ObjectProperty<Deck> currentDeckProperty() { return currentDeck; }

    public final Deck getCurrentDeck() { return currentDeckProperty().get(); }

    public final void setCurrentDeck(Deck deck) {currentDeckProperty().set(deck); }

    public ObservableList<Deck> getDeckList() { return deckList; }

    public final void addDeck(Deck dk) {
        deckList.add(dk);
        db.insertDeck(dk);
    }

    public DataModel(){
        db = new Database();
    }

    public void loadData(){
        db.getDecks(deckList);
    }

    public void saveData(){

    }

}