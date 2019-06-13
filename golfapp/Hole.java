package golfapp;

import javafx.beans.property.SimpleIntegerProperty;

public class Hole {

    private SimpleIntegerProperty length;
    private SimpleIntegerProperty par;

    public Hole(int length, int par) {
        this.length = new SimpleIntegerProperty(length);
        this.par = new SimpleIntegerProperty(par);
    }

    public SimpleIntegerProperty getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = new SimpleIntegerProperty(length);
    }

    public SimpleIntegerProperty getPar() {
        return par;
    }

    public void setPar(int par) {
        this.par = new SimpleIntegerProperty(par);
    }
}
