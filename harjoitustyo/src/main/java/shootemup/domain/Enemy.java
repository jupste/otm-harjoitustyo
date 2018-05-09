
package shootemup.domain;

import javafx.scene.Node;

/**
 *
 * @author jussiste
 */
public interface Enemy {

    /**
     * A method that moves the enemy on the board. Idler class entities move in a random direction each turn determined by the random number. Drones move in a straight line and change direction if random number equals 0.
     * @param dir determines the way a Idler moves and whether Drone changes direction.
     */
    public void move(int dir);
    public Node getEnemy();
    
}
