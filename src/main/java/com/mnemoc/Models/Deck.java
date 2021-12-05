package com.mnemoc.Models;

import javafx.beans.property.*;

public class Deck {

    private final StringProperty name = new SimpleStringProperty();
    private final LongProperty did = new SimpleLongProperty();

    public final StringProperty nameProperty(){ return this.name; }
    public final String getName() { return this.nameProperty().get(); }
    public final void setName(final String name) { this.nameProperty().set(name); }

    public final LongProperty didProperty(){ return this.did; }
    public final Long getDid() { return this.didProperty().get(); }
    public final void setDid(final Long did) {this.didProperty().set(did);}

    public Deck(){}

    public Deck(String name) {
        setName(name);
    }
}
