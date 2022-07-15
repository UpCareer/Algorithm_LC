package com.ck.pi;

import java.util.ArrayList;
import java.util.List;

/**
 * Entree -> has options
 */
public class Entree extends MenuItem{

    private float price;
    private List<Integer> relatedIds = new ArrayList<>();

    public Entree(float price, List<Integer> relatedIds) {
        this.price = price;
        this.relatedIds = relatedIds;
    }

    public Entree(int id, String name, float price) {
        super(id, name);
        this.price = price;
    }

    public Entree addRelatedID(int id) {
        this.relatedIds.add(id);
        return this;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public List<Integer> getRelatedIds() {
        return relatedIds;
    }

    public void setRelatedIds(List<Integer> relatedIds) {
        this.relatedIds = relatedIds;
    }
}
