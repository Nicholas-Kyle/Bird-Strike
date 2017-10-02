package com.bangtidygames.birdstrike;

import android.graphics.Rect;

/**
 * Created by Nick on 15/09/2017.
 */

public class Robot {

    private final float SPEEDX = 3;
    private final float TOPSPEEDY = (float)3.5;
    private final float BOTTOMSPEEDY = (float)3;

    private boolean movingUp = false;
    private boolean collision = false;
    private boolean badLanding = false;
    private boolean tooHigh = false;
    private boolean caught = false;
    private boolean caughtOpponent = false;
    private boolean grounded = true;
    private boolean stillGrounded = true;

    private int birdsHit = 0;

    private int centerX;
    private int centerY;
    private float speedY = (float)0;
    private float invertSpeedY = (float)3.5;
    private int points;

    private float changeX;
    private float absoluteX;
    private float changeY;

    public static Rect rect = new Rect(0, 0, 0, 0);
    public static Rect rect2 = new Rect(0, 0, 0, 0);
    public static Rect rect3 = new Rect(0, 0, 0, 0);

    public Robot(){
        super();
        centerX = 240;
        absoluteX = centerX;
    }

    public void update(float deltaTime) {

        if (!stillGrounded) {
            grounded = false;
        }
        stillGrounded = false;

        changeX = SPEEDX * deltaTime;

        absoluteX += changeX;

        if (!grounded) {
            changeY = speedY * deltaTime;

            centerY -= changeY;

            if (centerY < -50) {
                tooHigh = true;
            }

            boolean maxSpeedY = speedY >= TOPSPEEDY;

            boolean minSpeedY = speedY <= -BOTTOMSPEEDY;

            if (!movingUp && !minSpeedY) {
                speedY -= .065 * deltaTime;
            }

            if (movingUp && !maxSpeedY) {
                speedY += .1 * deltaTime;
            }

        }



        // todo Collision rectangles updated ****************** require adjustment ********************

        rect.set(centerX - 39, centerY -10, centerX + 39, centerY);
        rect2.set(centerX + 2, centerY - 18, centerX + 23, centerY + 10);
        rect3.set(centerX + 15, centerY +8, centerX + 26, centerY + 18);

    }

    public void adjustX(int difference){
        centerX += difference;
        absoluteX += difference;
    }

    public void bounce(int tileY){
        setCenterY(tileY-18);
        if (speedY <= -BOTTOMSPEEDY){
            badLanding = true;
        }
        if (invertSpeedY==(float) 3.5){
            invertSpeedY = (float) (speedY * (-0.6));
        } else if (invertSpeedY<=0.1){
            invertSpeedY = (float) 0;
            this.setGrounded(true);
        } else {
            invertSpeedY = (float) (invertSpeedY * 0.6);
        }
        speedY = invertSpeedY;
    }

    public void hitBird(){
        birdsHit ++;
    }

    public int getBirdsHit() {
        return birdsHit;
    }

    public void setBirdsHit(int birdsHit) {
        this.birdsHit = birdsHit;
    }

    public void addPoints(int pointsToAdd){
        points += pointsToAdd;
    }

    public void moveUp(){
        invertSpeedY = (float) 3.5;
        setGrounded(false);
        setMovingUp(true);
    }

    public void stopUp() {
        setMovingUp(false);
    }

    public int getCenterX() {
        return centerX;
    }

    public int getCenterY() {
        return centerY;
    }

    public void setCenterX(int centerX) {
        this.centerX = centerX;
    }

    public void setCenterY(int centerY) {
        this.centerY = centerY;
    }

    public void setSpeedY(int speedY) {
        this.speedY = speedY;
    }

    public void setMovingUp(boolean movingUp) {
        this.movingUp = movingUp;
    }

    public boolean isCollision() {
        return collision;
    }

    public void setCollision(boolean collision) {
        this.collision = collision;
    }

    public float getAbsoluteX() {
        return absoluteX;
    }

    public float getChangeX() {
        return changeX;
    }

    public void setChangeX(int changeX) {
        this.changeX = changeX;
    }

    public boolean isCaught() {
        return caught;
    }

    public void setCaught(boolean caught) {
        this.caught = caught;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public boolean isBadLanding() {
        return badLanding;
    }

    public void setBadLanding(boolean badLanding) {
        this.badLanding = badLanding;
    }

    public boolean isTooHigh() {
        return tooHigh;
    }

    public void setTooHigh(boolean tooHigh) {
        this.tooHigh = tooHigh;
    }

    public boolean isCaughtOpponent() {
        return caughtOpponent;
    }

    public void setCaughtOpponent(boolean caughtOpponent) {
        this.caughtOpponent = caughtOpponent;
    }

    public boolean isGrounded() {
        return grounded;
    }

    public void setGrounded(boolean grounded) {
        this.grounded = grounded;
    }

    public boolean isStillGrounded() {
        return stillGrounded;
    }

    public void setStillGrounded(boolean stillGrounded) {
        this.stillGrounded = stillGrounded;
    }
}