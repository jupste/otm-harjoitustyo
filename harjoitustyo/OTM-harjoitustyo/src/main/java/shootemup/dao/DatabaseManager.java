package shootemup.dao;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.*;
import java.util.ArrayList;
import javafx.beans.property.ReadOnlyListWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 *
 * @author jussiste
 */
public class DatabaseManager {
    private Connection connection;
    private Statement statement;
    
    public DatabaseManager() {
        this.connection = null;
        this.statement=null;
        try{
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:hiscoreTable.db");
            connection.setAutoCommit(false);
            statement= connection.createStatement();
            String insert="CREATE TABLE IF NOT EXISTS SCORES " + "(NAME TEXT NOT NULL UNIQUE, SCORE INT NOT NULL);";
            statement.executeUpdate(insert);
            statement.close();
            connection.commit();
            //connection.close();
        }catch(Exception e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }
    public void insertIntoTable(String name, int score){
        try{
            statement= connection.createStatement();
            String insert="INSERT INTO SCORES(NAME, SCORE) \n" +
                "SELECT +'"+ name +"', "+score+" \n" +
                "WHERE NOT EXISTS(SELECT * FROM SCORES WHERE NAME= '" +name+"');";
            statement.executeUpdate(insert);
            insert="UPDATE SCORES SET SCORE= MAX(SCORE ,"+score+") WHERE NAME= '"+name+"';";
            statement.executeUpdate(insert);
            statement.close();
            connection.commit();
        }catch(Exception e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }
    public ObservableList<EntryObject> getScores(){
        ObservableList<EntryObject> scores= FXCollections.observableArrayList();
        try{
            statement= connection.createStatement();
            String insert="SELECT * FROM SCORES ORDER BY SCORE DESC;";
            ResultSet results= statement.executeQuery(insert);
            while(results.next()){
                String name= results.getString("NAME");
                int score= results.getInt("SCORE");
                scores.add(new EntryObject(name, score));
            }
        }catch(Exception e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return scores;
    }
    public void clearTable(){
        try{
            statement= connection.createStatement();
            String insert="DELETE FROM SCORES;";
            statement.executeUpdate(insert);
            statement.close();
            connection.commit();
        }catch(Exception e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }
}