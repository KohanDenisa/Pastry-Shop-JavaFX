package com.example.demo2.jdbc;

import com.example.demo2.domain.BCake;
import com.example.demo2.domain.Order;
import org.sqlite.SQLiteDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Jdbc {
    private static final String JDBC_URL = "jdbc:sqlite:data/cakeShop.db";
    private Connection conn = null;

    public void openConnection(){
        try{
            SQLiteDataSource ds = new SQLiteDataSource();
            ds.setUrl(JDBC_URL);
            if(conn == null || conn.isClosed())
                conn = ds.getConnection();
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void closeConnection(){
        try{
            conn.close();
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void createSchema(){
        try{
            conn.setAutoCommit(false);
            final Statement stmt = conn.createStatement();
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS " +
                    "bcakes(id int, price int, weight int, PRIMARY KEY(id));");
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS " +
                    "orders(id int, status int, date varchar(100), bcakeID int, " +
                    "PRIMARY KEY(id), FOREIGN KEY(bcakeID) REFERENCES BCAKES(id));");
            conn.commit();
            stmt.close();
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void addBCake(BCake bCake){
        try{
            conn.setAutoCommit(false);
            PreparedStatement statement = conn.prepareStatement(
                    "INSERT INTO BCAKES VALUES(?, ?, ?)"
            );
            statement.setInt(1, bCake.getId());
            statement.setInt(2, bCake.getPrice());
            statement.setInt(3, bCake.getWeight());
            statement.executeUpdate();
            conn.commit();
            statement.close();
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void addOrder(Order order){
        try{
            conn.setAutoCommit(false);
            PreparedStatement statement = conn.prepareStatement(
                    "INSERT INTO ORDERS VALUES(?, ?, ?, ?)"
            );
            statement.setInt(1, order.getId());
            statement.setInt(2, order.getStatus());
            statement.setString(3, order.getDate());
            statement.setInt(4, order.getbCake().getId());
            statement.executeUpdate();
            conn.commit();
            statement.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void updateBCake(BCake initBCake, BCake updatedBCake){
        try{
            conn.setAutoCommit(false);
            PreparedStatement statement = conn.prepareStatement(
                    "UPDATE BCAKES SET price = ?, weight = ? WHERE id = ?"
            );
            statement.setInt(1, updatedBCake.getPrice());
            statement.setInt(2, updatedBCake.getWeight());
            statement.setInt(3, initBCake.getId());
            statement.executeUpdate();
            conn.commit();
            statement.close();
        } catch(SQLException e){
            e.printStackTrace();
        }
    }
    public void updateOrder(Order initOrder, Order updatedOrder){
        try{
            conn.setAutoCommit(false);
            PreparedStatement statement = conn.prepareStatement(
                    "UPDATE ORDERS SET status = ?, date = ?, bcakeID = ? WHERE id = ?"
            );
            statement.setInt(1, updatedOrder.getStatus());
            statement.setString(2, updatedOrder.getDate());
            statement.setInt(3, updatedOrder.getbCake().getId());
            statement.setInt(4, initOrder.getId());
            statement.executeUpdate();
            conn.commit();
            statement.close();
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void removeBCake(BCake bCake){
        try{
            conn.setAutoCommit(false);
            PreparedStatement statement = conn.prepareStatement(
                    "DELETE FROM BCAKES WHERE id = ?"
            );
            statement.setInt(1, bCake.getId());
            statement.executeUpdate();
            conn.commit();
            statement.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void removeOrder(Order order){
        try{
            conn.setAutoCommit(false);
            PreparedStatement statement = conn.prepareStatement(
                    "DELETE FROM ORDERS WHERE id = ?"
            );
            statement.setInt(1, order.getId());
            statement.executeUpdate();
            conn.commit();
            statement.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public List<BCake> getAllBCakes(){
        List<BCake> bCakes = new ArrayList<>();
        try{
            PreparedStatement statement = conn.prepareStatement(
                    "SELECT * FROM BCAKES"
            );
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                BCake bCake = new BCake(
                        rs.getInt("id"),
                        rs.getInt("price"),
                        rs.getInt("weight")
                );
                bCakes.add(bCake);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return bCakes;
    }

    public BCake getBCakeID(int id){
        BCake bCake = null;
        try{
            PreparedStatement statement = conn.prepareStatement(
                    "SELECT * FROM BCAKES WHERE id = ?"
            );
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            bCake = new BCake(
                    rs.getInt("id"),
                    rs.getInt("price"),
                    rs.getInt("weight")
            );
        } catch (SQLException e){
            e.printStackTrace();
        }
        return bCake;
    }

    public List<Order> getAllOrder(){
        List<Order> orders = new ArrayList<>();
        try{
            PreparedStatement statement = conn.prepareStatement(
                    "SELECT * FROM ORDERS"
            );
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                Order order = new Order(
                        rs.getInt("id"),
                        rs.getInt("status"),
                        rs.getString("date"),
                        getBCakeID(rs.getInt("bcakeID"))
                );
                orders.add(order);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return orders;
    }

    public Order getOrderID(int id){
        Order order = null;
        try{
            PreparedStatement statement = conn.prepareStatement(
                    "SELECT * FROM ORDERS WHERE id = ?"
            );
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            order = new Order(
                    rs.getInt("id"),
                    rs.getInt("status"),
                    rs.getString("date"),
                    getBCakeID(rs.getInt("bcakeID"))
            );
        } catch (SQLException e){
            e.printStackTrace();
        }
        return order;
    }
}
