package com.example.demo2.repository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Serialization {
    public static <T> void serialize(List<T> list, String filename){
        ObjectOutputStream out = null;
        try{
            out = new ObjectOutputStream(new FileOutputStream(filename));
            out.writeObject(list);
        } catch(FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            try{
                out.close();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public static <T> List<T> deserialize(String filename){
        ObjectInputStream in = null;
        List<T> list = null;
        try{
            in = new ObjectInputStream(new FileInputStream(filename));
            list = (List<T>) in.readObject();
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        } finally {
            try{
                in.close();
            } catch (IOException | NullPointerException e){
                e.printStackTrace();
            }
        }
        if(list == null)
            return new ArrayList<>();
        return list;
    }
}
