package com.example.demo2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 700);
        stage.setTitle("Birthday cake shop");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
//        int p1, w1, p2, w2, p3, w3, p4, w4;
//        int s1, s2;
//        String d1, d2, d3, d4;
//
//        s1 = 0;
//        s2 = 1;
//        p1 = 22;
//        p2 = 30;
//        w1 = 150;
//        w2 = 100;
//        p3 = 25;
//        p4 = 30;
//        w3 = 125;
//        w4 = 200;
//        d1 = "10/10/2022";
//        d2 = "11/10/2022";
//        d3 = "13/10/2022";
//        d4 = "14/10/2022";
//
//        BCake bc1 = new BCake(p1, w1);
//        BCake bc2 = new BCake(p3, w3);
//        BCake bc3 = new BCake(p1, w2);
//        BCake bc4 = new BCake(p4, w4);
//        BCake bc5 = new BCake(p2, w3);
//
//        Order o1 = new Order(s1, d1, bc1);
//        Order o2 = new Order(s2, d2, bc2);
//        Order o3 = new Order(s2, d3, bc3);
//        Order o4 = new Order(s1, d2, bc4);
//        Order o5 = new Order(s2, d4, bc5);
//
//        OrderService orderService = new OrderService();
//        BCakeService bCakeService = new BCakeService();
//
//        bCakeService.add(bc1);
//        bCakeService.add(bc2);
//        bCakeService.add(bc3);
//        bCakeService.add(bc4);
//        bCakeService.add(bc5);
//
//        bCakeService.serializeRepo("serBCakes.txt");
//
//        orderService.add(o1);
//        orderService.add(o2);
//        orderService.add(o3);
//        orderService.add(o4);
//        orderService.add(o5);
//
//        orderService.serializeRepo("serOrders.txt");
//
//        Jdbc db = new Jdbc();
//        db.openConnection();
//        db.createSchema();

//        db.addBCake(bc1);
//        db.addBCake(bc2);
//        db.addBCake(bc3);
//        db.addBCake(bc4);
//        db.addBCake(bc5);
//
//        db.addOrder(o1);
//        db.addOrder(o2);
//        db.addOrder(o3);
//        db.addOrder(o4);
//        db.addOrder(o5);

//        db.closeConnection();

        launch();
    }
}