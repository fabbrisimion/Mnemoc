package com.mnemoc.Models;

import com.mnemoc.Utils.Database;
import javafx.beans.Observable;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Hashtable;
import java.util.Map;

public class DataModel {

    private Database db;
    private Map<Long, Integer> map;
    private static boolean firstCard = true;

    public DataModel(){
        db = new Database();
        map = new Hashtable<>();
    }

    private final ObservableList<Deck> deckList = FXCollections.observableArrayList(deck ->
        new Observable[] {deck.nameProperty()});

    private final ObservableList<Card> cards = FXCollections.observableArrayList(card ->
            new Observable[] {card.frontProperty()});

    private final ObjectProperty<Deck> currentDeck = new SimpleObjectProperty<>(null);
    private final ObjectProperty<Card> currentCard = new SimpleObjectProperty<>(null);

    public ObjectProperty<Deck> currentDeckProperty() { return currentDeck; }
    public ObjectProperty<Card> currentCardProperty() { return currentCard; }

    public final Deck getCurrentDeck() { return currentDeckProperty().get(); }

    public final void setCurrentDeck(Deck deck) {
        currentDeckProperty().set(deck);
        loadCards(deck);
    }

    public final Card getCurrentCard() { return currentCardProperty().get(); }

    public final void setCurrentCard(Card card) { currentCardProperty().set(card); }

    public ObservableList<Deck> getDeckList() { return deckList; }

    public final void addDeck(Deck dk) {
        deckList.add(dk);
        db.insertDeck(dk);
    }

    public final void addCard(Card cd){
        cards.add(cd);
        db.insertCard(cd);
    }

    public void loadDecks(){
        db.getDecks(deckList);
        for(Deck dk : deckList) map.put(dk.getDid(),0);
    }

    public Card getCard(){
        return cards.get(map.get(getCurrentDeck().getDid()));
    }

    public void nextCard(){
        Long did = getCurrentDeck().getDid();
        if(map.get(did) >= cards.size()) map.put(did,0);
        else map.put(did,map.get(did)+1);
    }

    private void loadCards(Deck dk){
        cards.clear();
        db.getCards(dk, cards);
    }

    public void removeDeck(){
        Deck dk = getCurrentDeck();
        db.remove(dk);
        deckList.remove(dk);
        map.remove(dk.getDid());
    }

    public void updateCard(Card cd){
        db.updateCard(cd);
    }
}