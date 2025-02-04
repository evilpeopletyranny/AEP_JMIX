package ru.ase.xml;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

// Класс Customer, который используется внутри Order
@XmlAccessorType(XmlAccessType.FIELD)
class Customer {
    private String name;
    private String address;

    public Customer() { }

    public Customer(String name, String address) {
        this.name = name;
        this.address = address;
    }

    // Геттеры и сеттеры
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
}

