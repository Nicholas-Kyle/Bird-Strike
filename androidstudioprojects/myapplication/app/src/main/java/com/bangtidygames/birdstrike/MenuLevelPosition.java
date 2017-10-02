package com.bangtidygames.birdstrike;

/**
 * Created by Nick on 18/09/2017.
 */

public class MenuLevelPosition {
    final int x;
    final int y;
    final int levelNumber;
    final int starsToUnlock;

    MenuLevelPosition(int x, int y, int levelNumber, int starsToUnlock){
        this.x=x;
        this.y=y;
        this.levelNumber=levelNumber;
        this.starsToUnlock=starsToUnlock;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getLevelNumber() {
        return levelNumber;
    }

    public int getStarsToUnlock(){
        return starsToUnlock;
    }
}
