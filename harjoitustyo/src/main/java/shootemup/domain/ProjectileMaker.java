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
    public boolean hasAmmo(){
        if(this.ammo>0){
            this.ammo--;
            return true;
        }
        return false;
    }
    public void reload(int reload){
        this.ammo+=reload;
    }
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
    public void removeProjectile(Node n){
        int index=projectiles.indexOf(n);
        projectiles.remove(index);
        speeds.remove(index);
    }
}
