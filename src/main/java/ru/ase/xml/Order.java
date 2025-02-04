package ru.ase.xml;

import jakarta.xml.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

// Класс, для которого генерируется схема. Он содержит ссылку на объект Customer
// и коллекцию объектов Item.
@XmlRootElement(name = "Order")
@XmlAccessorType(XmlAccessType.FIELD)
public class Order {
    private String orderId;

    // Поле другого класса
    @XmlElement(name = "Customer")
    private Customer customer;

    // Коллекция объектов типа Item. Аннотация @XmlElementWrapper создаёт обёртку вокруг коллекции,
    // а @XmlElement указывает имя элемента для каждого объекта.
    @XmlElementWrapper(name = "Items")
    @XmlElement(name = "Item")
    private Set<Item> items = new HashSet<>();

    public Order() { }

    public Order(String orderId, Customer customer, Set<Item> items) {
        this.orderId = orderId;
        this.customer = customer;
        this.items = items;
    }

    // Геттеры и сеттеры
    public String getOrderId() {
        return orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    public Customer getCustomer() {
        return customer;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    public Set<Item> getItems() {
        return items;
    }
    public void setItems(Set<Item> items) {
        this.items = items;
    }
}
