
package shootemup.domain;

import javafx.scene.Node;
import javafx.scene.layout.Pane;

/**
 *
 * @author jussiste
 */
public interface Enemy {
    public void move(int dir);
    public Node getEnemy();
    
}
