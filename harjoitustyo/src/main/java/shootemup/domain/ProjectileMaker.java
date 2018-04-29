/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shootemup.domain;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import java.math.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author jussiste
 */
public class ProjectileMaker {
    private ArrayList<Speed> speeds;
    private ArrayList<Node> projectiles;
    private int ammo;
    
    public ProjectileMaker() {
        this.speeds = new ArrayList<>();
        this.projectiles=new ArrayList<>();
        this.ammo=50;
    }

    /**
     * Method that tells whether player can shoot. Used by KeyController class. 
     * @return Returns true if ProjectileMaker has ammo left, otherwise false.
     */
    public boolean hasAmmo(){
        if(this.ammo>0){
            this.ammo--;
            return true;
        }
        return false;
    }

    /**
     * Method to add to ammo count.
     * @param reload the amount added to ammo.
     */
    public void reload(int reload){
        this.ammo+=reload;
    }

    /**
     * Creates a projectile at player position. 
     * @param x player x-position
     * @param y player y-position
     * @param speed vector of projectile
     * @return Circle to add to the board.
     */
    public Node initProjectile(double x, double y, Speed speed){
        Circle projectile= new Circle(x+10, y+10, 5, Color.RED);
        this.projectiles.add(projectile);
        this.speeds.add(speed);
        return projectile;
    } 
    public ArrayList<Speed> getSpeeds() {
        return speeds;
    }
    public ArrayList<Node> getProjectiles() {
        return projectiles;
    }

    public int getAmmo() {
        return ammo;
    }

    /**
     * Removes projectiles simultaneously from both arrays thus keeping the order in place. 
     * @param n
     */
    public void removeProjectile(Node n){
        int index=projectiles.indexOf(n);
        projectiles.remove(index);
        speeds.remove(index);
    }
}
