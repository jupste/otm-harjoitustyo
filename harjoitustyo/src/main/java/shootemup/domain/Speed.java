/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shootemup.domain;

import javafx.scene.input.KeyCode;

/**
 *
 * @author jussiste
 */
public class Speed {
    private double xSpeed;
    private double ySpeed;

    public Speed(KeyCode key) {
        switch(key){
            case W:
                this.ySpeed=-3;
                break;
            case A: 
                this.xSpeed=-3;
                break;
            case S:
                this.ySpeed=3;
                break;
            case D: 
                this.xSpeed=3;
                break;
            default:
                break;
        }
    }

    public double getxSpeed() {
        return xSpeed;
    }

    public double getySpeed() {
        return ySpeed;
    }
    
}
