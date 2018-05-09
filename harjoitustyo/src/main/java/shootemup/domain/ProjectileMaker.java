/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shootemup.domain;

import javafx.scene.Node;
import java.util.ArrayList;

/**
 *
 * @author jussiste
 */
public class ProjectileMaker {

    private ArrayList<Projectile> projectiles;
    private int ammo;

    public ProjectileMaker() {
        this.projectiles = new ArrayList<>();
        this.ammo = 50;
    }

    /**
     * Method that tells whether player can shoot. Used by KeyController class.
     *
     * @return Returns true if ProjectileMaker has ammo left, otherwise false.
     */
    public boolean hasAmmo() {
        if (this.ammo > 0) {
            this.ammo--;
            return true;
        }
        return false;
    }

    /**
     * Method to add to ammo count.
     *
     * @param reload the amount added to ammo.
     */
    public void reload(int reload) {
        this.ammo += reload;
    }

    /**
     * Creates a projectile at player position.
     *
     * @param x player x-position
     * @param y player y-position
     * @param xSpeed x-vector of projectile
     * @param ySpeed y-vector of projectile
     * @return Circle to add to the board.
     */
    public Node initProjectile(double x, double y, double xSpeed, double ySpeed) {
        Projectile projectile = new Projectile(x, y, xSpeed, ySpeed);
        this.projectiles.add(projectile);
        return projectile.getProjectile();
    }

    public ArrayList<Projectile> getProjectiles() {
        return projectiles;
    }

    public int getAmmo() {
        return ammo;
    }

    /**
     * Removes projectiles simultaneously from both arrays thus keeping the
     * order in place.
     *
     * @param p projectile to remove
     */
    public void removeProjectile(Projectile p) {
        projectiles.remove(projectiles.indexOf(p));
    }
}
