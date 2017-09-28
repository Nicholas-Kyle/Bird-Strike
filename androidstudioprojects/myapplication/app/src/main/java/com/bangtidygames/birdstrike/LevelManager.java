package com.bangtidygames.birdstrike;

import com.bangtidygames.framework.Graphics;
import com.bangtidygames.framework.Image;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Nick on 09/09/2016.
 */

public class LevelManager {

    private int checkpoints;
    private boolean finishPost = true;
    private boolean levelComplete = false;

    private int levelWidth;

    private int level;

    private int oneStar;
    private int twoStars;
    private int threeStars;

    private ArrayList tileArray = new ArrayList();
    private ArrayList birdArray = new ArrayList();

    private Image bgImage;
    private Image b1, b2, b3, b4, b5, b9;

    private Animation birdAnim;

    private Background bg1, bg2;

    private Robot robot;

    public LevelManager(int level) {

        this.robot = GameScreen.getRobot();
        this.setLevel(level);
        this.loadBackground(level);
        this.loadMap(level);
        this.loadBirds(level);
        this.loadBirdImages();
        this.setLevelWidth(level);
    }

    private void setLevelWidth(int level) {
        //TODO check correct when setting new level
        if (level < 100) {
            this.levelWidth = 1280;
        }
    }

    private void setRobotY(int height) {
        robot.setCenterY(height*32-17);
    }

    private void loadBackground(int level) {
            bg1 = new Background(0, 0, 1280);
            bg2 = new Background(1280, 0, 1280);
            bgImage = Assets.BG_forest_1280;
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
            width = 40;
            scanner = new Scanner(SampleGame.map3);
        }
        if (level == 4) {
            width = 40;
            scanner = new Scanner(SampleGame.map4);
        }
        if (level == 5) {
            width = 40;
            scanner = new Scanner(SampleGame.map5);
        }
        if (level == 6) {
            width = 40;
            scanner = new Scanner(SampleGame.map6);
        }
        if (level == 7) {
            width = 40;
            scanner = new Scanner(SampleGame.map7);
        }
        if (level == 8) {
            width = 40;
            scanner = new Scanner(SampleGame.map8);
        }
        if (level == 9) {
            width = 40;
            scanner = new Scanner(SampleGame.map9);
        }
        if (level == 10) {
            width = 40;
            scanner = new Scanner(SampleGame.map10);
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
                this.checkpoints = Character.getNumericValue(ch);
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
        //TODO check when setting new level
        if (level == 1) {

            Bird bird = new Bird(920, 320, (float) 1.36);
            birdArray.add(bird);

            Bird bird2 = new Bird(1000, 380, (float) 1.36);
            birdArray.add(bird2);

            Bird bird3 = new Bird(1100, 380, (float) 1.36);
            birdArray.add(bird3);

            this.oneStar=0;
            this.twoStars=1;
            this.threeStars=3;
        }

        if (level == 2) {

            Bird bird = new Bird(920, 200, (float)1.36);
            birdArray.add(bird);

            Bird bird2 = new Bird(1250, 300, (float)1.36);
            birdArray.add(bird2);

            Bird bird3 = new Bird(1650, 140, (float)1.36);
            birdArray.add(bird3);

            Bird bird4 = new Bird(2010, 140, (float)1.36);
            birdArray.add(bird4);

            Bird bird5 = new Bird(2250, 250, (float)1.36);
            birdArray.add(bird5);

            this.oneStar=0;
            this.twoStars=3;
            this.threeStars=5;

        } else if (level == 5) {

            Bird bird = new Bird(1250, 300, (float) 1.36);
            birdArray.add(bird);

            Bird bird2 = new Bird(1690, 140, (float) 1.36);
            birdArray.add(bird2);

            Bird bird3 = new Bird(1900, 5, (float) 1.36);
            birdArray.add(bird3);

            Bird bird4 = new Bird(2050, 200, (float) 1.36);
            birdArray.add(bird4);

            Bird bird5 = new Bird(2270, 250, (float) 1.36);
            birdArray.add(bird5);

            Bird bird6 = new Bird(3500, 350, (float) 1.36);
            birdArray.add(bird6);

            this.oneStar=0;
            this.twoStars=4;
            this.threeStars=6;

        } else if (level == 10) {

            Bird bird = new Bird(850, 200, (float)1.36);
            birdArray.add(bird);

            Bird bird2 = new Bird(1620, 8, (float)1.36);
            birdArray.add(bird2);

            Bird bird3 = new Bird(2250, 385, (float)1.36);
            birdArray.add(bird3);

            Bird bird4 = new Bird(3350, 140, (float)1.36);
            birdArray.add(bird4);

            Bird bird5 = new Bird(3750, 200, (float)1.36);
            birdArray.add(bird5);

            Bird bird6 = new Bird(4150, 385, (float)1.36);
            birdArray.add(bird6);

            Bird bird7 = new Bird(4550, 140, (float)1.36);
            birdArray.add(bird7);

            Bird bird8 = new Bird(4950, 250, (float)1.36);
            birdArray.add(bird8);

            this.oneStar=0;
            this.twoStars=5;
            this.threeStars=8;
        }

    }

    private void loadBirdImages() {

        b1 = Assets.brown_1;
        b2 = Assets.brown_2;
        b3 = Assets.brown_3;
        b4 = Assets.brown_4;
        b5 = Assets.brown_5;
        b9 = Assets.brown_9;

        birdAnim = new Animation();
        birdAnim.addFrame(b1, 50);
        birdAnim.addFrame(b2, 50);
        birdAnim.addFrame(b3, 50);
        birdAnim.addFrame(b4, 50);
        birdAnim.addFrame(b5, 50);
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

    public void paintBirds(Graphics g) {
        for (int i = 0; i < birdArray.size(); i++) {
            Bird bird = (Bird) birdArray.get(i);
            if (bird.isAlive()) {
                g.drawImage(birdAnim.getImage(), (int) bird.getXPos() - 16, (int) bird.getYPos() - 16);
            } else {
                bird.paint(g);
            }
        }
    }

    public int getCheckpoints() {
        return checkpoints;
    }

    public void checkpointPassed() {
        this.checkpoints--;
        if (checkpoints == 1){
            Assets.gameMusic.setVolume(.85f);
            Assets.gameMusic.play();
        }
        if (checkpoints == 0) {
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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getOneStar() {
        return oneStar;
    }

    public int getTwoStars() {
        return twoStars;
    }

    public int getThreeStars() {
        return threeStars;
    }
}