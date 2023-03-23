package com.example.demo2.domain;

import java.io.Serializable;
import java.util.Objects;

public class Order implements Identifiable, Serializable, Comparable<Order> {
    public static final long serialVersionUID = 1L;
    private int status; //0-Done, 1-Pending
    private String date;
    private static int lastId = -1;
    private int id;
    private BCake bCake;

    private int bcakeId;

    public Order(){
        status = 0;
        date = "";
        setId();
        bCake = new BCake();
    }

    public Order(int status, String date){
        this.status = status;
        this.date = date;
        setId();
        bCake = new BCake();
    }

    public Order(int status, String date, BCake bCake){
        this.status = status;
        this.date = date;
        setId();
        this.bCake = bCake;
    }

    public Order(int id, int status, String date, BCake bCake) {
        this.status = status;
        this.date = date;
        this.id = id;
        this.bCake = bCake;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public BCake getbCake() {
        return bCake;
    }

    public void setbCake(BCake bCake) {
        this.bCake = bCake;
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

    public int getBcakeId(){
        bcakeId = bCake.getId();
        return bCake.getId();
    }

    @Override
    public String toString() {
        return "Order{" +
                "status=" + status +
                ", date='" + date + '\'' +
                ", id=" + id +
                ", bCake=" + bCake +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this);
    }

    @Override
    public int compareTo(Order o) {
        return this.getDate().compareTo(o.getDate());
    }
}
