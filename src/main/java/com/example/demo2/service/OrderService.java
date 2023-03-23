package com.example.demo2.service;

import com.example.demo2.domain.BCake;
import com.example.demo2.domain.Order;
import com.example.demo2.exception.NotAnOrder;
import com.example.demo2.repository.Repository;

import java.util.List;

public class OrderService {
    Repository<Order> repo = new Repository<>();

    public void add(Object o){
        try{
            if(o.getClass() == Order.class)
                repo.add((Order) o);
            else
                throw new NotAnOrder();
        } catch (NotAnOrder e){
            System.out.println("Object not a Order!");
        }
    }

    public void delete(Object o){
        try{
            if(o.getClass() == Order.class){
                repo.remove((Order) o);
                System.out.println("The Order has been cancelled.");
                repo.display();
            }
            else
                throw new NotAnOrder();
        } catch (NotAnOrder e){
            System.out.println("Object not a Order!");
        }
    }

    public void update(Object obj, Object updatedObj){
        try{
            if(obj.getClass() != Order.class || updatedObj.getClass() != Order.class)
                throw new NotAnOrder();
            repo.update((Order) obj, (Order) updatedObj);
            System.out.println("The Order has been updated.");
            repo.display();
        } catch (NotAnOrder e){
            System.out.println("Object not a Order!");
        }
    }

    public void display(){
        repo.display();
    }

    public List<Order> getAll(){
        return repo.getAll();
    }

    public void sort3()
    {
        repo.getAll().stream().filter(c->c.getStatus()==1).sorted().forEach(System.out::println);
    }
    public void sort4()
    {
        repo.getAll().stream().filter(c->c.getDate().equals("12/12/2022")).sorted().forEach(System.out::println);
    }
    public void sort5()
    {
        repo.getAll().stream().filter(c->c.getStatus()==0 ).sorted().forEach(System.out::println);
    }

    public Order getOrderAtIndex(int id){
        return repo.getObjAtIndex(id);
    }

    public void serializeRepo(String filename){
        repo.serialize(filename);
    }

    public void deserializeRepo(String filename){
        repo.deserialize(filename);
    }
}
