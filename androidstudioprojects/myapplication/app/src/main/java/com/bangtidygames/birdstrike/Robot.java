package com.bangtidygames.birdstrike;

import android.graphics.Rect;

/**
 * Created by Nick on 15/09/2017.
 */

public class Robot {

    final float TOPSPEEDX = 3;
    final float TOPSPEEDY = (float)3.5;
    final float BOTTOMSPEEDY = (float)3;

    private boolean movingUp = false;
    private boolean collision = false;
    private boolean badLanding = false;
    private boolean tooHigh = false;
    private boolean caught = false;
    private boolean caughtOpponent = false;

    private int birdsHit = 0;

    private int centerX;
    private int centerY;
    private float speedY = (float)0.2;
    private float invertSpeedY = (float)3.5;
    private int points;

    private float speedX;
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
        speedX = 3;
    }

    public void update(float deltaTime) {

        if (speedX<=TOPSPEEDX){
            speedX += .1;
        }

        changeX = speedX * deltaTime;
        if(changeX>100){
            changeX = 0;
        }

        absoluteX += changeX;

        // Updates Y Position
        //check delta adjust
        changeY = speedY * deltaTime;
        if(changeY>100){
            changeY = 0;
        }

        centerY -= changeY;

        if(centerY < -50){
            tooHigh = true;
        }

        // Y position logic

        boolean maxSpeedY = speedY >= TOPSPEEDY;

        boolean minSpeedY = speedY <= -BOTTOMSPEEDY;

        if (!movingUp && !minSpeedY) {
            speedY -= .23;
        }

        if(movingUp && !maxSpeedY) {
            speedY += .35;
        }



        // Collision rectangles updated ****************** require adjustment ********************

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

    public float getSpeedX() {
        return speedX;
    }

    public void setCenterX(int centerX) {
        this.centerX = centerX;
    }

    public void setCenterY(int centerY) {
        this.centerY = centerY;
    }

    public void setSpeedX(int speedX) {
        this.speedX = speedX;
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
}