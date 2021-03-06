package com.boston.bcg.dao;

public class Item {
    String id,name,category,unitPrice,grossPrice,description;

    public Item(String id,String name,String unitPrice,String grossPrice,String description,String category){
        this.category=category;
        this.id=id;
        this.description=description;
        this.name=name;
        this.unitPrice=unitPrice;
        this.grossPrice=grossPrice;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public String getGrossPrice() {
        return grossPrice;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

}
