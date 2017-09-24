package com.bangtidygames.birdstrike;

import android.graphics.Rect;

/**
 * Created by Nick on 30/09/2016.
 */

public class Bird {

    private boolean alive = true;
    private boolean moving = true;

    private  int mapWidth;

    //private float screenXPos;
    private float xPos;
    private float yPos;
    private float speedX;

    private Rect r;

    public Bird(int x, int y, int width, float speed){
        r = new Rect();
        xPos = x;
        // screenXPos = x;
        yPos = y;
        speedX = speed;
        if (speed == (float)0) {
            moving = false;
        }
        mapWidth = width;
    }

    public void update(float deltaTime){
        Robot robot = GameScreen.getRobot();
        float robotX = robot.getAbsoluteX();
        //float changeX = speedX * deltaTime;
        //if(changeX >100){
        //    changeX = 0;
        //}
        if((xPos-800)<robotX) {
            xPos -= robot.getChangeX() * speedX;
            //this.setScreenXPos(this.getxPos() + robot.getCenterX() - robotX);
            r.set((int) xPos - 12, (int) yPos - 12, (int) xPos + 12, (int) yPos + 12);
        }
        if (alive) {
            if (Rect.intersects(r, Robot.rect) || Rect.intersects(r, Robot.rect2)) {
                alive = false;
                robot.hitBird();
                Assets.birdSound.play(0.60f);
            }
        }
        if (!moving && xPos < -100){
            xPos += mapWidth;
        }
    }

    public float getScreenXPos() {
        return xPos;
    }

    /**public void setScreenXPos(float screenXPos) {
     this.screenXPos = screenXPos;
     }*/

    public float getxPos() {
        return xPos;
    }

    public void setxPos(float xPos) {
        this.xPos = xPos;
    }

    public float getyPos() {
        return yPos;
    }

    public void setyPos(float yPos) {
        this.yPos = yPos;
    }

    public Rect getR() {
        return r;
    }

    public void setR(Rect r) {
        this.r = r;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }
}
