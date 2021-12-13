package com.mnemoc.Models;

import javafx.beans.property.*;

public class Card {

    private final LongProperty cid = new SimpleLongProperty();
    private final LongProperty did = new SimpleLongProperty();
    private final StringProperty front = new SimpleStringProperty();
    private final StringProperty back = new SimpleStringProperty();
    private final LongProperty last_rev = new SimpleLongProperty();
    private final FloatProperty e_nr = new SimpleFloatProperty();
    private final LongProperty next_rev = new SimpleLongProperty();
    private final LongProperty reps = new SimpleLongProperty();
    private final LongProperty intrvl = new SimpleLongProperty();

    public final LongProperty cidProperty() {return this.cid; }
    public final Long getCid() { return this.cidProperty().get(); }
    public final void setCid(final Long cid) { this.cidProperty().set(cid); }

    public final LongProperty didProperty() {return this.did; }
    public final Long getDid() { return this.didProperty().get(); }
    public final void setDid(final Long did) { this.didProperty().set(did); }

    public final StringProperty frontProperty(){ return this.front; }
    public final String getFront() { return this.frontProperty().get(); }
    public final void setFront(final String front) { this.frontProperty().set(front); }

    public final StringProperty backProperty(){ return this.back; }
    public final String getBack() { return this.backProperty().get(); }
    public final void setBack(final String back) { this.backProperty().set(back); }

    public final LongProperty lastRevProperty() {return this.last_rev; }
    public final Long getLastRev() { return this.lastRevProperty().get(); }
    public final void setLastRev(final Long last_rev) { this.lastRevProperty().set(last_rev); }

    public final FloatProperty eNumProperty() {return this.e_nr; }
    public final Float getENr() { return this.eNumProperty().get(); }
    public final void setENr(final Float e_nr) { this.eNumProperty().set(e_nr); }

    public final LongProperty nextRevProperty() {return this.next_rev; }
    public final Long getNextRev() { return this.nextRevProperty().get(); }
    public final void setNextRev(final Long next_rev) { this.nextRevProperty().set(next_rev); }

    public final LongProperty repsProperty() {return this.reps; }
    public final Long getReps() { return this.repsProperty().get(); }
    public final void setReps(final Long reps) { this.repsProperty().set(reps); }

    public final LongProperty intrvlProperty() {return this.intrvl; }
    public final Long getIntrvl() { return this.intrvlProperty().get(); }
    public final void setIntrvl(final Long intrvl) { this.intrvlProperty().set(intrvl); }

    @Override
    public String toString() {
        return String.format("Card {\n" +
                "%d,\n" +
                "%d, \n" +
                "%s, \n" +
                "%s, \n" +
                "%d, \n" +
                "%.2f, \n" +
                "%d, \n" +
                "%d, \n" +
                "%d };\n", getCid(),getDid(),getFront(),getBack(),getLastRev(),getENr(),getNextRev(),getIntrvl(),getReps());
    }
}
