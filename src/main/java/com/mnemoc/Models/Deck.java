package com.mnemoc.Models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Deck {

    private final StringProperty name = new SimpleStringProperty();

    public final StringProperty nameProperty(){ return this.name; }

    public final String getName() { return this.nameProperty().get(); }

    public final void setName(final String name) { this.nameProperty().set(name); }

    public Deck(String name) {
        setName(name);
    }
}
