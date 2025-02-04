package ru.ase.xml;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

// Класс Item, объекты которого хранятся в коллекции (Set)
@XmlAccessorType(XmlAccessType.FIELD)
class Item {
    private String productId;
    private int quantity;

    public Item() { }

    public Item(String productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    // Геттеры и сеттеры
    public String getProductId() {
        return productId;
    }
    public void setProductId(String productId) {
        this.productId = productId;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
