package com.example.demo2.domain;

import java.io.Serializable;
import java.util.Objects;

public class BCake implements Serializable, Identifiable, Comparable<BCake> {
    private static final long serialVersionUID = 1L;

    int price;
    int weight;
    private static int lastId = -1;
    private int id;

    public BCake(){
        price = 0;
        weight = 0;
        setId();
    }

    public BCake(int price, int weight){
        this.price = price;
        this.weight = weight;
        setId();
    }

    public BCake(int id, int price, int weight) {
        this.price = price;
        this.weight = weight;
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    private void setId(){
        id = ++lastId;
    }

    public void setId(int id){
        this.id = id;
    }
    @Override
    public int getId(){
        return id;
    }

    public static void updateLastId(int id){
        lastId = id;
    }

    @Override
    public String toString() {
        return "BCake{" +
                "price=" + price +
                ", weight=" + weight +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BCake bCake = (BCake) o;
        return id == bCake.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this);
    }

    @Override
    public int compareTo(BCake o) {
        if(this.price<o.price)
            return -1;
        if(this.price>o.price)
            return 1;
        return 0;
    }
}
