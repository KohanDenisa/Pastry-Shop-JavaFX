package com.example.demo2.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Repository<T> implements Serializable {
    private List<T> list = new ArrayList<>();

    public void add(T obj){
        list.add(obj);
    }

    public void update(T obj, T updatedObj){
        if(obj.equals(updatedObj)) {
            int index = list.indexOf(obj);
            list.set(index, updatedObj);
        }
    }

    public void remove(T obj){
        list.remove(list.stream().filter(object -> object.equals(obj)).toList().get(0));
    }

    public void display(){
       for(T obj : list){
           System.out.println(obj.toString());
       }
    }

    public List<T> getAll(){
        return list.stream().toList();
    }

    public T getObjAtIndex(int id){
        return list.get(id);
    }

    public void serialize(String filename){
        Serialization.serialize(this.list, filename);
    }

    public void deserialize(String filename){
        list = Serialization.deserialize(filename);
    }
}
