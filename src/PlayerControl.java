import java.awt.RenderingHints.Key;
import java.awt.event.KeyEvent;

import edu.princeton.cs.introcs.StdDraw;

public class PlayerControl {

    private double playerX;
    private double playerY;
    private double playerSpeed;

    public PlayerControl(double  playerX, double playerY, double playerSpeed){
        this.playerX = 0.5;
        this.playerY = 0.5;
        this.playerSpeed = 0.01;
    }

    public void move(){
        if(StdDraw.isKeyPressed(KeyEvent.VK_W)) {
            playerY = playerY + playerSpeed;
        }
        if(StdDraw.isKeyPressed(KeyEvent.VK_S)) {
            playerY = playerY - playerSpeed;
        }
        if(StdDraw.isKeyPressed(KeyEvent.VK_A)) {
            playerX = playerX - playerSpeed;
        }
        if(StdDraw.isKeyPressed(KeyEvent.VK_D)) {
            playerX = playerX + playerSpeed;
        }
        
        if(playerX > 1) {
            playerX = 1;
        }
        if(playerX < 0) {
            playerX = 0;
        }
        if(playerY > 1) {
            playerY = 1;
        }
        if(playerY < 0) {
            playerY = 0;
        }
    }

    public double getPlayerX(){
        return this.playerX;
    }

    public double getPlayerY(){
        return this.playerY;
    }

    public double getPlayerSpeed(){
        return this.playerSpeed;
    }

    public void setPlayerX(double playerX){
        this.playerX = playerX;
    }

    public void setPlayerY(double playerY){
        this.playerY = playerY;
    }

    public void setPlayerSpeed(double playerSpeed){
        this.playerSpeed = playerSpeed;
    }
}
