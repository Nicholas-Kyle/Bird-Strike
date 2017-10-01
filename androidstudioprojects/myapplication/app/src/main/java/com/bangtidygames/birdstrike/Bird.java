package com.bangtidygames.birdstrike;

import android.graphics.Rect;

import com.bangtidygames.framework.Graphics;
import com.bangtidygames.framework.Image;

/**
 * Created by Nick on 30/09/2016.
 */

public class Bird {

    private boolean alive = true;
    private boolean moving = true;

    private float xPos;
    private float yPos;
    private float speedX;

    private Rect r;

    private Image b6, b7, b8, blank;

    private Animation birdDeadAnim;

    public Bird(int x, int y, float speed){
        r = new Rect();
        xPos = x;
        yPos = y;
        speedX = speed;
        if (speed == (float)0) {
            moving = false;
        }
        this.loadImages();
    }

    public void update(float deltaTime){
        Robot robot = GameScreen.getRobot();
        float robotX = robot.getAbsoluteX();
        if((xPos-600)<robotX) {
            xPos -= (robot.getChangeX() * speedX);
            r.set((int) xPos - 12, (int) yPos - 12, (int) xPos + 12, (int) yPos + 12);
        }
        if (alive) {
            if (Rect.intersects(r, Robot.rect) || Rect.intersects(r, Robot.rect2)) {
                alive = false;
                robot.hitBird();
                Assets.playSound(Assets.birdSound);
            }
        }
        this.updateImages();
    }

    /**
     * Loads the images for dead birds
     */
    private void loadImages(){

        b6 = Assets.brown_6;
        b7 = Assets.brown_7;
        b8 = Assets.brown_8;
        blank = Assets.blank;


        birdDeadAnim = new Animation();
        birdDeadAnim.addFrame(b6, 50);
        birdDeadAnim.addFrame(b7, 50);
        birdDeadAnim.addFrame(b8, 50);
        birdDeadAnim.addFrame(blank, 10000);
    }

    public float getXPos() {
        return xPos;
    }

    /**
     *  Updates dead bird animation, if alive = false
     */
    private void updateImages() {
        if (!alive){
            birdDeadAnim.update(10);
        }
    }

    /**
     *  Paints dead bird animations only, live bird images are painted by levelmanager
     */
    public void paint(Graphics g){
        if (!alive){
            g.drawImage(birdDeadAnim.getImage(), (int)this.getXPos()-16, (int)yPos-16);
        }
    }

    public float getYPos() {
        return yPos;
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

    public boolean isMoving() {
        return moving;
    }
}
