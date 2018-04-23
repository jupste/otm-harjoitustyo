/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.util.Observable;
import javafx.collections.ObservableList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import shootemup.dao.*;
/**
 *
 * @author jussiste
 */
public class DataAccessObjectTest {
    private static DatabaseManager dbmanager;
    
    @BeforeClass
    public static void setUpBeforeClass(){
        dbmanager=new DatabaseManager("jdbc:sqlite:test.db");
    }

    @After
    public void clearTable(){
        dbmanager.clearTable();
    }
    @Test
    public void databaseCreated(){
        File file= new File("test.db");
        assertTrue(file.exists());
    }
    @Test
    public void canInsertIntoTable(){
        dbmanager.insertIntoTable("test", 10);
        ObservableList<EntryObject> list= dbmanager.getScores();
        assertEquals(1, list.size());
    }
    @Test
    public void canClearTable(){
        dbmanager.insertIntoTable("test", 30);
        dbmanager.insertIntoTable("test2", 10);
        dbmanager.insertIntoTable("test", 50);
        dbmanager.clearTable();
        ObservableList<EntryObject> list= dbmanager.getScores();
        assertEquals(0, list.size());
    }
    @Test
    public void noDublicates(){
        dbmanager.insertIntoTable("test", 100);
        dbmanager.insertIntoTable("test", 10);
        ObservableList<EntryObject> list= dbmanager.getScores();
        assertEquals(1, list.size());
    }
    @Test
    public void tableIsSorted(){
        dbmanager.insertIntoTable("first", 100);
        dbmanager.insertIntoTable("second", 50);
        dbmanager.insertIntoTable("third", 300);
        ObservableList<EntryObject> list= dbmanager.getScores();
        assertEquals(list.get(0).getName(), "third");
        assertEquals(list.get(1).getName(), "first");
        assertEquals(list.get(2).getName(), "second");
    }
}
