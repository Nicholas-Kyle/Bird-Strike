package com.bangtidygames.birdstrike;

import android.graphics.Rect;

import com.bangtidygames.framework.Image;

/**
 * Created by Nick on 15/09/2017.
 */

public class Tile {

    private int  tileY, levelLength;

    private float tileX;

    public int type;

    public Image tileImage;

    private Robot robot = GameScreen.getRobot();

    private Rect r;

    public Tile(int x, int y, int typeInt, int width) {

        tileX = x * 32;
        tileY = y * 32;

        type = typeInt;

        levelLength = width * 32;

        r = new Rect();

        if (type == 1) {
            tileImage = Assets.grass_1;
        } else if (type == 2) {
            tileImage = Assets.grass_2;
        } else if (type == 3) {
            tileImage = Assets.grass_3;
        } else if (type == 4) {
            tileImage = Assets.grass_4;
        } else if (type == 5) {
            tileImage = Assets.grass_5;
        } else if (type == 6) {
            tileImage = Assets.grass_6;
        } else if (type == 7) {
            tileImage = Assets.grass_7;
        } else if (type == 8) {
            tileImage = Assets.grass_8;
        } else if (type == 9) {
            tileImage = Assets.grass_9;
        } else if (type == 10) {
            this.tileYAdjust(25);
            tileImage = Assets.grass_10;
        } else if (type == 11) {
            tileImage = Assets.grass_11;
        } else if (type == 12) {
            tileImage = Assets.grass_12;
        } else if (type == 13) {
            tileImage = Assets.grass_13;
        } else if (type == 14) {
            tileImage = Assets.grass_14;
        } else if (type == 15) {
            tileImage = Assets.grass_15;
        } else if (type == 16) {
            tileImage = Assets.grass_16;
        } else if (type == 17) {
            tileImage = Assets.grass_17;
        } else if (type == 18) {
            tileImage = Assets.grass_18;
        } else if (type == 19) {
            this.tileYAdjust(16);
            tileImage = Assets.grass_19;
        } else if (type == 20) {
            this.tileYAdjust(19);
            tileImage = Assets.grass_20;
        } else if (type == 21) {
            this.tileYAdjust(16);
            tileImage = Assets.grass_21;
        } else if (type == 22) {
            this.tileYAdjust(11);
            tileImage = Assets.grass_22;
        } else if (type == 23) {
            this.tileYAdjust(69);
            tileImage = Assets.grass_23;
        } else if (type == 24) {
            this.tileYAdjust(12);
            tileImage = Assets.grass_24;
        } else if (type == 25) {
            this.tileYAdjust(12);
            tileImage = Assets.grass_25;
        } else if (type == 26) {
            this.tileYAdjust(14);
            tileImage = Assets.grass_26;
        } else if (type == 27) {
            this.tileYAdjust(75);
            tileImage = Assets.grass_27;
        } else if (type == 28) {
            this.tileYAdjust(40);
            tileImage = Assets.blueFinishPost;
        } else {
            type = 0;
        }

    }

    public void update() {

        tileX -= robot.getChangeX();

        if (tileX < robot.getCenterX() - 340) {
            tileX += levelLength;
        }

        if (type == 1 || type == 3 || type == 4 || type == 6 || type == 8 || type == 13 ||
                type == 14 || type == 18) {
            r.set((int) tileX, tileY, (int) tileX + 32, tileY + 8);
            if (Rect.intersects(r, Robot.rect3)) {
                robot.bounce(tileY);
            }
        }

        if (type < 18 && type != 1 && type != 3 && type != 4 && type != 6 && type != 8
                && type != 13 && type != 14 && type != 0) {
            r.set((int) tileX, tileY, (int) tileX + 32, tileY + 32);
            if (Rect.intersects(r, Robot.rect) || Rect.intersects(r, Robot.rect2)) {
                robot.setCollision(true);
            }
        } else if (type == 4 || type == 18) {
            r.set((int) tileX, tileY, (int) tileX + 32, tileY + 12);
            if (Rect.intersects(r, Robot.rect) || Rect.intersects(r, Robot.rect2)) {
                robot.setCollision(true);
            }
        } else if (type == 8) {
            r.set((int) tileX, tileY, (int) tileX + 32, tileY + 23);
            if (Rect.intersects(r, Robot.rect) || Rect.intersects(r, Robot.rect2)) {
                robot.setCollision(true);
            }
        } else if (type == 23) {
            r.set((int) tileX + 31, tileY + 2, (int) tileX + 40, tileY + 69);
            if (Rect.intersects(r, Robot.rect) || Rect.intersects(r, Robot.rect2)) {
                robot.setCollision(true);
            }
        } else if (type == 23) {
            r.set((int) tileX + 9, tileY + 9, (int) tileX + 60, tileY + 37);
            if (Rect.intersects(r, Robot.rect) || Rect.intersects(r, Robot.rect2)) {
                robot.setCollision(true);
            }
        } else if (type == 27) {
            r.set((int) tileX + 29, tileY + 2, (int) tileX + 41, tileY + 75);
            if (Rect.intersects(r, Robot.rect) || Rect.intersects(r, Robot.rect2)) {
                robot.setCollision(true);
            }
        } else if (type == 27) {
            r.set((int) tileX + 8, tileY + 9, (int) tileX + 60, tileY + 38);
            if (Rect.intersects(r, Robot.rect) || Rect.intersects(r, Robot.rect2)) {
                robot.setCollision(true);
            }
        } else if (type == 22) {
            r.set((int) tileX + 8, tileY, (int) tileX + 21, tileY + 11);
            if (Rect.intersects(r, Robot.rect) || Rect.intersects(r, Robot.rect2)) {
                robot.setCollision(true);
            }
        } else if (type == 26) {
            r.set((int) tileX, tileY, (int) tileX + 22, tileY + 14);
            if (Rect.intersects(r, Robot.rect) || Rect.intersects(r, Robot.rect2)) {
                robot.setCollision(true);
            }
        } else if (type == 20) {
            r.set((int) tileX, tileY, (int) tileX + 19, tileY + 19);
            if (Rect.intersects(r, Robot.rect) || Rect.intersects(r, Robot.rect2)) {
                robot.setCollision(true);
            }
        } else if (type == 34) {
            r.set((int) tileX, tileY, (int) tileX + 96, tileY + 15);
            if (Rect.intersects(r, Robot.rect) || Rect.intersects(r, Robot.rect2)) {
                robot.setCollision(true);
            }
            r.set((int) tileX, tileY + 81, (int) tileX + 96, tileY + 96);
            if (Rect.intersects(r, Robot.rect) || Rect.intersects(r, Robot.rect2)) {
                robot.setCollision(true);
            }
        } else if (type == 35) {
            r.set((int) tileX, tileY, (int) tileX + 96, tileY + 15);
            if (Rect.intersects(r, Robot.rect) || Rect.intersects(r, Robot.rect2)) {
                robot.setCollision(true);
            }
            r.set((int) tileX, tileY + 81, (int) tileX + 96, tileY + 96);
            if (Rect.intersects(r, Robot.rect) || Rect.intersects(r, Robot.rect2)) {
                robot.setCollision(true);
            }
            r.set((int) tileX, tileY, (int) tileX + 1, tileY + 96);
            if (Rect.intersects(r, Robot.rect) || Rect.intersects(r, Robot.rect2)) {
                robot.setCollision(true);
            }
        }

        if (type == 28) {
            LevelManager levelManager = GameScreen.getLevelManager();
            r.set((int) tileX + 23, -50, (int) tileX + 23, 480);
            if (Rect.intersects(r, Robot.rect) || Rect.intersects(r, Robot.rect2)) {
                if (!levelManager.isFinishPost()) {
                    levelManager.setFinishPost(true);
                    levelManager.addLap();
                }
            } else {
                levelManager.setFinishPost(false);
            }
        }
    }





    private void tileYAdjust(int y) {
        tileY -= (y - 32);
    }

    public float getTileX() {
        return tileX;
    }

    public int getTileY() {
        return tileY;
    }

    public Image getTileImage() {
        return tileImage;
    }


}