package com.bangtidygames.birdstrike;

import com.bangtidygames.framework.Graphics;
import com.bangtidygames.framework.Image;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Nick on 09/09/2016.
 */

public class LevelManager {

    private int remainingOpponents;

    private int lap;
    private boolean finishPost = true;
    private boolean levelComplete = false;

    private int levelWidth;

    private ArrayList tileArray = new ArrayList();
    private ArrayList compPlayerArray = new ArrayList();
    private ArrayList birdArray = new ArrayList();

    private Image bgImage;

    private Image b1, b2, b3, b4, b5, b6, b7, b8, b9, blank;

    private Animation birdAnim, birdDeadAnim1, birdDeadAnim2, birdDeadAnim3, birdDeadAnim4, birdDeadAnim5;

    private Background bg1, bg2;

    private Robot robot;

    public LevelManager(int level) {

        this.robot = GameScreen.getRobot();
        this.loadBackground(level);
        this.loadMap(level);
        this.loadBirds(level);
        this.loadBirdImages(level);
        this.setLevelWidth(level);
    }

    private void setLevelWidth(int level) {
        if (level < 100) {
            this.levelWidth = 1280;
        }
    }

    private void setRobotY(int height) {
        robot.setCenterY(height*32-18);
    }

    private void loadBackground(int level) {

        if (level < 100) {
            bg1 = new Background(0, 0, 1280);
            bg2 = new Background(1280, 0, 1280);
            bgImage = Assets.BG_forest_1280;
        }
    }

    private void loadMap(int level) {
        ArrayList lines = new ArrayList();
        int width = 0;
        Scanner scanner = null;

        if (level == 1) {
            width = 40;
            scanner = new Scanner(SampleGame.map1);
        }
        if (level == 2) {
            width = 40;
            scanner = new Scanner(SampleGame.map2);
        }
        if (level == 3) {
            scanner = new Scanner(SampleGame.map3);
        }
        if (level == 4) {
            scanner = new Scanner(SampleGame.map4);
        }
        if (level == 5) {
            scanner = new Scanner(SampleGame.map5);
        }
        if (level == 6) {
            scanner = new Scanner(SampleGame.map6);
        }

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            if (line == null) {
                break;
            }

            if (!line.startsWith("!")) {
                lines.add(line);
            }
        }

        for (int j = 0; j < 17; j++) {
            String line = (String) lines.get(j);
            if (j == 15) {
                char ch = line.charAt(0);
                this.lap = Character.getNumericValue(ch);
            } else if (j == 16) {
                char ch = line.charAt(0);
                setRobotY(Character.getNumericValue(ch));
            } else

                for (int i = 0; i < width; i++) {
                    if (i < line.length()) {
                        char ch = line.charAt(i);
                        Tile t = new Tile(i, j, Character.getNumericValue(ch), width);
                        tileArray.add(t);
                    }
                }
        }
    }

    private void loadBirds(int level) {
        //Bird(int xPosition, int yPosition, int width, float speed)
        if (level == 1) {
            Bird bird = new Bird(1000, 200, levelWidth, (float)1.36);
            birdArray.add(bird);
            Bird bird2 = new Bird(1400, 300, levelWidth, (float)1.36);
            birdArray.add(bird2);
            Bird bird3 = new Bird(1800, 140, levelWidth, (float)1.36);
            birdArray.add(bird3);
            Bird bird4 = new Bird(2140, 140, levelWidth, (float)1.36);
            birdArray.add(bird4);
            Bird bird5 = new Bird(2400, 250, levelWidth, (float)1.36);
            birdArray.add(bird5);

        }
        if (level == 2) {
            Bird bird = new Bird(1400, 300, levelWidth, (float) 1.36);
            birdArray.add(bird);
            Bird bird2 = new Bird(1800, 140, levelWidth, (float) 1.36);
            birdArray.add(bird2);
            Bird bird3 = new Bird(2140, 147, levelWidth, (float) 1.36);
            birdArray.add(bird3);
            Bird bird4 = new Bird(2400, 250, levelWidth, (float) 1.36);
            birdArray.add(bird4);
            Bird bird5 = new Bird(3600, 350, levelWidth, (float) 1.36);
            birdArray.add(bird5);
        }
    }

    private void loadBirdImages(int level) {
        // load brown birds
        if (level < 100) {
            b1 = Assets.brown_1;
            b2 = Assets.brown_2;
            b3 = Assets.brown_3;
            b4 = Assets.brown_4;
            b5 = Assets.brown_5;
            b6 = Assets.brown_6;
            b7 = Assets.brown_7;
            b8 = Assets.brown_8;
            b9 = Assets.brown_9;
            blank = Assets.blank;

            birdAnim = new Animation();
            birdAnim.addFrame(b1, 50);
            birdAnim.addFrame(b2, 50);
            birdAnim.addFrame(b3, 50);
            birdAnim.addFrame(b4, 50);
            birdAnim.addFrame(b5, 50);

            birdDeadAnim1 = new Animation();
            birdDeadAnim1.addFrame(b6, 50);
            birdDeadAnim1.addFrame(b7, 50);
            birdDeadAnim1.addFrame(b8, 50);
            birdDeadAnim1.addFrame(blank, 10000);

            birdDeadAnim2 = new Animation();
            birdDeadAnim2.addFrame(b6, 50);
            birdDeadAnim2.addFrame(b7, 50);
            birdDeadAnim2.addFrame(b8, 50);
            birdDeadAnim2.addFrame(blank, 10000);

            birdDeadAnim3 = new Animation();
            birdDeadAnim3.addFrame(b6, 50);
            birdDeadAnim3.addFrame(b7, 50);
            birdDeadAnim3.addFrame(b8, 50);
            birdDeadAnim3.addFrame(blank, 10000);

            birdDeadAnim4 = new Animation();
            birdDeadAnim4.addFrame(b6, 50);
            birdDeadAnim4.addFrame(b7, 50);
            birdDeadAnim4.addFrame(b8, 50);
            birdDeadAnim4.addFrame(blank, 10000);

            birdDeadAnim5 = new Animation();
            birdDeadAnim5.addFrame(b6, 50);
            birdDeadAnim5.addFrame(b7, 50);
            birdDeadAnim5.addFrame(b8, 50);
            birdDeadAnim5.addFrame(blank, 10000);

        }
        // TODO load grey birds
    }

    public void updateLaps() {
        float x;
        x = (robot.getAbsoluteX() - robot.getCenterX()) - 110;
        if (lap < 10) {
            if (x > levelWidth) {
                lap += 10;
            }
        } else if (lap < 20) {
            if (x > levelWidth * 2) {
                lap += 10;
            }
        } else if (lap < 30) {
            if (x > levelWidth * 3) {
                lap += 10;
            }
        } else if (lap < 40) {
            if (x > levelWidth * 4) {
                lap += 10;
            }
        }
        if (lap % 11 == 0) {
            this.levelComplete = true;
        }
    }

    public void updateBackground(){
        bg1.update();
        bg2.update();
    }

    public void updateTiles() {
        for (int i = 0; i < tileArray.size(); i++) {
            Tile t = (Tile) tileArray.get(i);
            t.update();
        }
    }


    public void updateBirds(float deltaTime) {
        for (int i = 0; i < birdArray.size(); i++) {
            Bird bird = (Bird) birdArray.get(i);
            bird.update(deltaTime);
            if (!bird.isAlive()){
                if (i == 0) {
                    birdDeadAnim1.update(10);
                } else if (i == 1) {
                    birdDeadAnim2.update(10);
                } else if (i == 2) {
                    birdDeadAnim3.update(10);
                } else if (i == 3) {
                    birdDeadAnim4.update(10);
                } else if (i == 4) {
                    birdDeadAnim5.update(10);
                }
            }
        }
        birdAnim.update(10);

    }

    public void paintBackground(Graphics g) {
        g.drawImage(bgImage, bg1.getBgX(), bg1.getBgY());
        g.drawImage(bgImage, bg2.getBgX(), bg2.getBgY());
    }

    public void paintTiles(Graphics g) {
        for (int i = 0; i < tileArray.size(); i++) {
            Tile t = (Tile) tileArray.get(i);
            if (t.type != 0 && t.type != 35 && t.type != 34) {
                g.drawImage(t.getTileImage(), (int)t.getTileX(), t.getTileY());
            }
        }
    }

    public void paintWarp(Graphics g) {
        for (int i = 0; i < tileArray.size(); i++) {
            Tile t = (Tile) tileArray.get(i);
            if (t.type == 35 || t.type == 34) {
                g.drawImage(t.getTileImage(), (int)t.getTileX(), t.getTileY());
            }
        }
    }

    public void paintBirds(Graphics g) {
        for (int i = 0; i < birdArray.size(); i++) {
            Bird bird = (Bird) birdArray.get(i);
            if (bird.isAlive()) {
                if (bird.isMoving()) {
                    g.drawImage(birdAnim.getImage(), (int) bird.getScreenXPos() - 16,
                            (int) bird.getyPos() - 16);
                } else {
                    g.drawImage(b9, (int) bird.getScreenXPos() - 16,
                            (int) bird.getyPos() - 16);
                }
            } else {
                Image birdDeadImage = null;
                if (i == 0) {
                    birdDeadImage = birdDeadAnim1.getImage();
                } else if (i == 1) {
                    birdDeadImage = birdDeadAnim2.getImage();
                } else if (i == 2) {
                    birdDeadImage = birdDeadAnim3.getImage();
                } else if (i == 3) {
                    birdDeadImage = birdDeadAnim4.getImage();
                } else if (i == 4) {
                    birdDeadImage = birdDeadAnim5.getImage();
                }
                g.drawImage(birdDeadImage, (int) bird.getScreenXPos() - 16,
                        (int) bird.getyPos() - 16);
            }
        }
    }

    public int getRemainingOpponents() {
        return remainingOpponents;
    }

    public void adjustRemainingOpponents(int difference) {
        this.remainingOpponents -= difference;
    }

    public int getLap() {
        return lap;
    }

    public void setLap(int lap) {
        this.lap = lap;
    }

    public void addLap() {
        this.lap += 10;
        if (lap % 11 == 0) {
            this.levelComplete = true;
        }
    }

    public boolean isLevelComplete() {
        return levelComplete;
    }

    public void setLevelComplete(boolean levelComplete) {
        this.levelComplete = levelComplete;
    }

    public boolean isFinishPost() {
        return finishPost;
    }

    public void setFinishPost(boolean finishPost) {
        this.finishPost = finishPost;
    }
}