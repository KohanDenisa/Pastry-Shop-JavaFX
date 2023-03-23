package com.example.demo2.service;

import com.example.demo2.domain.BCake;
import com.example.demo2.exception.NotABCake;
import com.example.demo2.repository.Repository;

import java.util.List;

public class BCakeService {
    Repository<BCake> repo = new Repository<>();

    public void add(Object o){
        try{
            if(o.getClass() == BCake.class)
                repo.add((BCake) o);
            else
                throw new NotABCake();
        } catch (NotABCake e){
            System.out.println("Object not a BCake!");
        }
    }

    public void delete(Object o){
        try{
            if(o.getClass() == BCake.class){
                repo.remove((BCake) o);
                System.out.println("The BCake has been cancelled.");
                repo.display();
            }
            else
                throw new NotABCake();
        } catch (NotABCake e){
            System.out.println("Object not a BCake!");
        }
    }

    public void update(Object obj, Object updatedObj){
        try{
            if(obj.getClass() != BCake.class || updatedObj.getClass() != BCake.class)
                throw new NotABCake();
            repo.update((BCake) obj, (BCake) updatedObj);
            System.out.println("The BCake has been updated.");
            repo.display();
        } catch (NotABCake e){
            System.out.println("Object not a BCake!");
        }
    }

    public void display(){
        repo.display();
    }

    public List<BCake> getAll(){
        return repo.getAll();
    }

    public BCake getCakeAtIndex(int id){
        return repo.getObjAtIndex(id);
    }

    public void sort1()
    {
        repo.getAll().stream().filter(c->c.getPrice()<25).sorted().forEach(System.out::println);
    }
    public void sort2()
    {
        repo.getAll().stream().filter(c->c.getWeight()>200).sorted().forEach(System.out::println);
    }

    public void serializeRepo(String filename){
        repo.serialize(filename);
    }

    public void deserializeRepo(String filename){
        repo.deserialize(filename);
    }
}
