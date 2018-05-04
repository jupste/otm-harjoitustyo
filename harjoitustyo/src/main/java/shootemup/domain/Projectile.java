/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shootemup.domain;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 *
 * @author jussiste
 */
public class Projectile {

    private Circle projectile;
    private double xSpeed;
    private double ySpeed;

    public Projectile(double x, double y, double xSpeed, double ySpeed) {
        this.projectile = new Circle(x + 10, y + 10, 5, Color.RED);
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
    }

    public Circle getProjectile() {
        return projectile;
    }

    public double getxSpeed() {
        return xSpeed;
    }

    public double getySpeed() {
        return ySpeed;
    }

}
