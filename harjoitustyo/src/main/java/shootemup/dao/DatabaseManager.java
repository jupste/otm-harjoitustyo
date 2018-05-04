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
    private String destination;

    /**
     * Constructor of DatabaseManager class. A data access object used to keep
     * track of scores.
     *
     * @param dest name of file to write
     */
    public DatabaseManager(String dest) {
        this.connection = null;
        this.statement = null;
        this.destination = dest;
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(dest);
            statement = connection.createStatement();
            String insert = "CREATE TABLE IF NOT EXISTS SCORES " + "(NAME TEXT NOT NULL UNIQUE, SCORE INT NOT NULL);";
            statement.executeUpdate(insert);
            statement.close();
            connection.commit();
            connection.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    /**
     * Method used to insert an entry to the table. If an entry already exists
     * for player, it updates the score value if it higher than the one already
     * in the table.
     *
     * @param name name of player
     * @param score score of player
     */
    public void insertIntoTable(String name, int score) {

        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(destination);
            statement = connection.createStatement();
            String insert = "INSERT INTO SCORES(NAME, SCORE) \n"
                    + "SELECT +'" + name + "', " + score + " \n"
                    + "WHERE NOT EXISTS(SELECT * FROM SCORES WHERE NAME= '" + name + "');";
            statement.executeUpdate(insert);
            insert = "UPDATE SCORES SET SCORE= MAX(SCORE ," + score + ") WHERE NAME= '" + name + "';";
            statement.executeUpdate(insert);
            statement.close();
            connection.commit();
            connection.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    /**
     * Sorts the table by score and returns a list of entries.
     *
     * @return ObservableList that is used by Table object in ScreenLoader
     * class.
     */
    public ObservableList<EntryObject> getScores() {
        ObservableList<EntryObject> scores = FXCollections.observableArrayList();
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(destination);
            statement = connection.createStatement();
            String insert = "SELECT * FROM SCORES ORDER BY SCORE DESC;";
            ResultSet results = statement.executeQuery(insert);
            while (results.next()) {
                String name = results.getString("NAME");
                int score = results.getInt("SCORE");
                scores.add(new EntryObject(name, score));
            }
            connection.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return scores;
    }

    /**
     * Clears the table from all entries.
     */
    public void clearTable() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(destination);
            statement = connection.createStatement();
            String insert = "DELETE FROM SCORES;";
            statement.executeUpdate(insert);
            statement.close();
            connection.commit();
            connection.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }
}
