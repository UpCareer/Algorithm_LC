package com.ck.pi;

public class Option extends MenuItem{
    private float price;

    public Option(float price) {
        this.price = price;
    }

    public Option(int id, String name, float price) {
        super(id, name);
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
