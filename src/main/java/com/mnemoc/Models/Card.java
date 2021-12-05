package com.mnemoc.Models;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Card {

    private final LongProperty cid = new SimpleLongProperty();
    private final LongProperty did = new SimpleLongProperty();
    private final StringProperty front = new SimpleStringProperty();
    private final StringProperty back = new SimpleStringProperty();
    private final LongProperty last_rev = new SimpleLongProperty();
    private final LongProperty e_nr = new SimpleLongProperty();
    private final LongProperty next_rev = new SimpleLongProperty();

    public final LongProperty cidProperty() {return this.cid; }
    public final Long getCid() { return this.cidProperty().get(); }
    public final void setCid(final Long cid) { this.cidProperty().set(cid); }

    public final LongProperty didProperty() {return this.did; }
    public final Long getDid() { return this.didProperty().get(); }
    public final void setDid(final Long did) { this.cidProperty().set(did); }

    public final StringProperty frontProperty(){ return this.front; }
    public final String getFront() { return this.frontProperty().get(); }
    public final void setFront(final String front) { this.frontProperty().set(front); }

    public final StringProperty backProperty(){ return this.back; }
    public final String getBack() { return this.backProperty().get(); }
    public final void setBack(final String back) { this.backProperty().set(back); }

    public final LongProperty lastRevProperty() {return this.last_rev; }
    public final Long getLastRev() { return this.lastRevProperty().get(); }
    public final void setLastRev(final Long last_rev) { this.lastRevProperty().set(last_rev); }

    public final LongProperty eNumProperty() {return this.e_nr; }
    public final Long getENr() { return this.eNumProperty().get(); }
    public final void setENr(final Long e_nr) { this.cidProperty().set(e_nr); }

    public final LongProperty nextRevProperty() {return this.next_rev; }
    public final Long getNextRev() { return this.nextRevProperty().get(); }
    public final void setNextRev(final Long next_rev) { this.lastRevProperty().set(next_rev); }
}
