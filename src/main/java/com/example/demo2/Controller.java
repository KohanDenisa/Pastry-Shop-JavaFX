package com.example.demo2;

import com.example.demo2.domain.BCake;
import com.example.demo2.domain.Order;
import com.example.demo2.jdbc.Jdbc;
import com.example.demo2.service.BCakeService;
import com.example.demo2.service.OrderService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private final BCakeService bCakeService = new BCakeService();
    private final OrderService orderService = new OrderService();
    private final Jdbc db = new Jdbc();

    @FXML
    public TableView<BCake> cake_table;
    public static TableView<BCake> cake_table_info_app;
    public static ObservableList<BCake> cake_data_table;
    @FXML
    private TableColumn<BCake, String> table_cake_id, table_cake_price, table_cake_weight;


    @FXML
    public TableView<Order> order_table;
    public static TableView<Order> order_table_info_app;
    public static ObservableList<Order> order_data_table;
    @FXML
    private TableColumn<Order, String> table_order_id, table_order_status, table_order_date, table_order_bcake;

    @FXML
    public TextField bcake_price;
    @FXML
    public TextField bcake_weight;
    @FXML
    public TextField bcake_id;
    @FXML
    public Button bcake_create;
    @FXML
    public Button bcake_update;
    @FXML
    public Button bcake_remove;
    @FXML
    public TextField order_id;
    @FXML
    public TextField order_date;
    @FXML
    public TextField order_status;
    @FXML
    public ChoiceBox order_bcakes;
    private ObservableList<Integer> cake_data_id;
    @FXML
    public Button order_create;
    @FXML
    public Button order_remove;
    @FXML
    public Button order_update;
    @FXML
    public Button streams_test;
    @FXML
    private Button quit_button;

    private void initializeCols(){
        table_cake_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        table_cake_price.setCellValueFactory(new PropertyValueFactory<>("price"));
        table_cake_weight.setCellValueFactory(new PropertyValueFactory<>("weight"));

        table_order_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        table_order_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        table_order_status.setCellValueFactory(new PropertyValueFactory<>("status"));
        table_order_bcake.setCellValueFactory(new PropertyValueFactory<>("bcakeId"));
    }

    private void loadData(){
        cake_data_table = FXCollections.observableArrayList();
        order_data_table = FXCollections.observableArrayList();
        cake_data_id = FXCollections.observableArrayList(cake_data_table.stream().map(BCake::getId).toList());

        bCakeService.deserializeRepo("serBCakes.txt");
        orderService.deserializeRepo("serOrders.txt");

        for(BCake bCake: bCakeService.getAll()){
            db.addBCake(bCake);
        }

        for(Order order: orderService.getAll()){
            db.addOrder(order);
        }

        cake_table.setItems(cake_data_table);
        order_table.setItems(order_data_table);
        order_bcakes.setItems(cake_data_id);

        update_cake_table();
        update_order_table();
        update_cake_choice();
    }

    private void update_cake_table(){
        cake_data_table.clear();
        cake_data_table.addAll(bCakeService.getAll());
    }

    private void update_order_table(){
        order_data_table.clear();
        order_data_table.addAll(orderService.getAll());
    }

    private void update_cake_choice(){
        cake_data_id.clear();
        cake_data_id.addAll(bCakeService.getAll().stream().map(BCake::getId).toList());
    }

    @FXML
    protected void onCreateCakeButton(){
        try {
            int price = Integer.parseInt(bcake_price.getText());
            int weight = Integer.parseInt(bcake_weight.getText());
            BCake bCake = new BCake(price, weight);
            db.addBCake(bCake);
            bCakeService.add(bCake);
            update_cake_choice();
            update_cake_table();
        }
        catch (NumberFormatException e){
            e.printStackTrace();
        }
    }

    @FXML
    protected void onUpdateCakeButton(){
        try{
            int id = Integer.parseInt(bcake_id.getText());
            int price = Integer.parseInt(bcake_price.getText());
            int weight = Integer.parseInt(bcake_weight.getText());
            BCake updatedBCake = new BCake(id, price, weight);
            db.updateBCake(bCakeService.getAll().stream().filter(cake -> cake.getId() == id).limit(1).toList().get(0), updatedBCake);
            bCakeService.update(bCakeService.getAll().stream().filter(cake -> cake.getId() == id).limit(1).toList().get(0), updatedBCake);
            update_cake_table();
        }
        catch (NumberFormatException e){
            e.printStackTrace();
        }
    }

    @FXML
    protected void onRemoveCakeButton(){
        try{
            int id = Integer.parseInt(bcake_id.getText());
            for(Order order : orderService.getAll().stream().filter(order -> order.getbCake().getId() == id).toList()) {
                orderService.delete(order);
                db.removeOrder(order);
            }
            db.removeBCake(bCakeService.getAll().stream().filter(cake -> cake.getId() == id).toList().get(0));
            bCakeService.delete(bCakeService.getAll().stream().filter(cake -> cake.getId() == id).toList().get(0));
            update_cake_choice();
            update_cake_table();
            update_order_table();
        }
        catch (NumberFormatException e){
            e.printStackTrace();
        }
    }

    @FXML
    protected void onCreateOrderButton(){
        try {
            int status = Integer.parseInt(order_status.getText());
            String date = order_date.getText();
            int selCake = order_bcakes.getSelectionModel().getSelectedIndex();
            BCake cake = bCakeService.getCakeAtIndex(selCake);
            Order order = new Order(status, date, cake);
            db.addOrder(order);
            orderService.add(order);
            update_order_table();
        }
        catch (NumberFormatException e){
            e.printStackTrace();
        }
    }

    @FXML
    protected void onUpdateOrderButton(){
        try{
            int id = Integer.parseInt(order_id.getText());
            int status = Integer.parseInt(order_status.getText());
            String date = order_date.getText();
            int selCake = order_bcakes.getSelectionModel().getSelectedIndex();
            BCake cake = bCakeService.getCakeAtIndex(selCake);
            Order updatedOrder = new Order(id, status, date, cake);
            db.updateOrder(orderService.getAll().stream().filter(order -> order.getId() == id).limit(1).toList().get(0), updatedOrder);
            orderService.update(orderService.getAll().stream().filter(order -> order.getId() == id).limit(1).toList().get(0), updatedOrder);
            update_order_table();
        }
        catch (NumberFormatException e){
            e.printStackTrace();
        }
    }

    @FXML
    protected void onRemoveOrderButton(){
        try{
            int id = Integer.parseInt(order_id.getText());
            db.removeOrder(orderService.getAll().stream().filter(order -> order.getId() == id).toList().get(0));
            orderService.delete(orderService.getAll().stream().filter(order -> order.getId() == id).toList().get(0));
            update_order_table();
        }
        catch (NumberFormatException e){
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cake_table_info_app = cake_table;

        db.openConnection();
        db.createSchema();
        bCakeService.deserializeRepo("serBCakes.bin");
        orderService.deserializeRepo("serOrders.bin");

        List<BCake> cakes = bCakeService.getAll();
        List<Order> orders = orderService.getAll();

        int maxCake = cakes.get(0).getId();
        int maxOrder = orders.get(0).getId();

        for(int i = 1; i < cakes.size(); i++){
            if(maxCake < cakes.get(i).getId())
                maxCake = cakes.get(i).getId();
        }
        BCake.updateLastId(maxCake + 1);

        for(int i = 1; i < orders.size(); i++){
            if(maxOrder < orders.get(i).getId())
                maxOrder = orders.get(i).getId();
        }
        Order.updateLastId(maxOrder + 1);

        initializeCols();
        loadData();
    }

    @FXML
    private void closeButtonAction(){
        Stage stage = (Stage) quit_button.getScene().getWindow();

        bCakeService.serializeRepo("serBCakes.bin");
        orderService.serializeRepo("serOrders.bin");
        db.closeConnection();

        stage.close();
    }

    @FXML
    public void onSteamsTestAction(){
        System.out.println("Stream for BCakes");
        System.out.println("1) Sort cakes that have the price lower than 25");
        bCakeService.sort1();

        System.out.println();
        System.out.println("2) Sort cakes the have the weight greater than 200");
        bCakeService.sort2();

        System.out.println();
        System.out.println();

        System.out.println("Stream for Orders");
        System.out.println("1) Sort orders that have a status equal to 0");
        orderService.sort3();

        System.out.println();
        System.out.println("2) Sort orders that have the date equal 12/12/2022");
        orderService.sort4();

        System.out.println();
        System.out.println("3) Sort orders that have a status equal to 0");
        orderService.sort5();
    }

//    @FXML
//    private Label welcomeText;
//
//
//
//    @FXML
//    protected void onHelloButtonClick() {
//        welcomeText.setText("Welcome to JavaFX Application!");
//    }
}