/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shootemup.dao;

/**
 *
 * @author jussiste
 */
public class EntryObject {
    private String name;
    private int score;
    
    /**
     * Constructor for EntryObject class. Used to display scores on highscore-table.
     * @param name name of player
     * @param score score of player
     */
    public EntryObject(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }
    
}
