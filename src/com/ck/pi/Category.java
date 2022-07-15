package com.ck.pi;

import java.util.ArrayList;
import java.util.List;

/**
 * Category has Entrees
 */

public class Category extends MenuItem{
    private List<Integer> relatedIds = new ArrayList<>();

    public Category(List<Integer> relatedIds) {
        this.relatedIds = relatedIds;
    }

    public Category(int id, String name) {
        super(id, name);
    }

    public Category addRelatedID(int id) {
        this.relatedIds.add(id);
        return this;
    }

    public List<Integer> getRelatedIds() {
        return relatedIds;
    }

    public void setRelatedIds(List<Integer> relatedIds) {
        this.relatedIds = relatedIds;
    }
}
