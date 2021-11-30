package com.mnemoc.Models;

import javafx.beans.Observable;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DataModel {

    private final ObservableList<Deck> deckList = FXCollections.observableArrayList(deck ->
        new Observable[] {deck.nameProperty()});

    private final ObjectProperty<Deck> currentDeck = new SimpleObjectProperty<>(null);

    public ObjectProperty<Deck> currentDeckProperty() { return currentDeck; }

    public final Deck getCurrentDeck() { return currentDeckProperty().get(); }

    public final void setCurrentDeck(Deck deck) {currentDeckProperty().set(deck); }

    public ObservableList<Deck> getDeckList() { return deckList; }

    public final void addToDeckList(String s) {
        deckList.add(new Deck(s));
    }

    public void loadData(){
        addToDeckList("hello");
    }

    public void saveData(){}
}