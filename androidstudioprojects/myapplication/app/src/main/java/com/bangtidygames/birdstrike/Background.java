package com.bangtidygames.birdstrike;

/**
 * Created by Nick on 15/09/2017.
 */

public class Background {

    private int bgX, bgY, speedX, length;

    private Robot robot = GameScreen.getRobot();

    public Background(int x, int y, int z){
        bgX = x;
        bgY = y;
        length = z;
        speedX = 0;

    }

    public void update() {
        speedX = (int)-robot.getChangeX()/4;
        bgX += speedX;

        if (bgX <= -length){
            bgX += (length*2);
        }
    }

    public int getBgX() {
        return bgX;
    }

    public int getBgY() {
        return bgY;
    }

}