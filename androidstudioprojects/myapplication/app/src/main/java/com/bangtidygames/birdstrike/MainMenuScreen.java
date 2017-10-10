package com.bangtidygames.birdstrike;

import android.graphics.Color;
import android.graphics.Paint;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.bangtidygames.framework.Game;
import com.bangtidygames.framework.Graphics;
import com.bangtidygames.framework.Image;
import com.bangtidygames.framework.Screen;
import com.bangtidygames.framework.Input.TouchEvent;

/**
 * Created by Nick on 15/09/2017.
 */

public class MainMenuScreen extends Screen {

    private boolean dragging = false;

    private boolean insufficientStars = false;
    private float insufficientStarsTime;
    private int insufficientStarsLevel;

    private int totalStars;

    private int x = 0;
    private int maxMenuX;
    private int currentDrag;
    private int drag;

    private int soundButtonX = 15;
    private int soundButtonY = 394;

    private Paint paint;
    private Paint paint2;

    private Set<MenuLevelPosition> LevelPositions= new HashSet<>();

    public MainMenuScreen(Game game) {
        super(game);

        paint = new Paint();
        paint.setTextSize(30);
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);

        paint2 = new Paint();
        paint2.setTextSize(20);
        paint2.setAntiAlias(true);
        paint2.setTextAlign(Paint.Align.CENTER);
        paint2.setColor(Color.BLACK);

        this.setLevelPositions();
        this.setMaxMenuX();
        this.setTotalStars();
        this.setX();

        if (LoadSave.soundEnabled){
            Assets.menuMusic.play();
        }
    }

    public MainMenuScreen(Game game, int xPos) {
        super(game);

        paint = new Paint();
        paint.setTextSize(30);
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);

        paint2 = new Paint();
        paint2.setTextSize(20);
        paint2.setAntiAlias(true);
        paint2.setTextAlign(Paint.Align.CENTER);
        paint2.setColor(Color.BLACK);

        this.setLevelPositions();
        this.setMaxMenuX();
        this.setTotalStars();

        this.x=xPos;

        if (LoadSave.soundEnabled){
            Assets.menuMusic.play();
        }
    }

    @Override
    public void update(float deltaTime) {
        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
        int len = touchEvents.size();
        int i = 0;
        while (i < len && len == touchEvents.size()) {
            TouchEvent event = touchEvents.get(i);

            if (event.type == TouchEvent.TOUCH_DRAGGED) {
                if (dragging == false){
                    drag = event.x;
                    dragging = true;
                } else {
                    currentDrag = drag-event.x;
                }
            }

            if (event.type == TouchEvent.TOUCH_DOWN) {
                x += currentDrag;
                currentDrag = 0;
                if (x < 0){
                    x = 0;
                }
                if (x > maxMenuX){
                    x = maxMenuX;
                }
                dragging = false;

                for (MenuLevelPosition level : LevelPositions) {
                    if (inBounds(event, level.getX() - x, level.getY(), 119, 150)) {
                        if (totalStars >= level.getStarsToUnlock()) {
                            if (LoadSave.hearts != 0) {
                                selectLevel(level.getLevelNumber());
                            } else {
                                //TODO add you must wait x amount of time for hearts to reload
                            }
                        } else {
                            insufficientStarsTime = System.nanoTime();
                            insufficientStarsLevel = level.getLevelNumber();
                            insufficientStars = true;
                        }
                    }
                }
                if (inBounds(event, soundButtonX, soundButtonY, 70, 71)){
                    if(LoadSave.soundEnabled){
                        LoadSave.soundEnabled=false;
                        Assets.menuMusic.pause();
                    } else {
                        LoadSave.soundEnabled=true;
                        Assets.menuMusic.play();
                    }
                }
            }
            i++;
        }
    }

    private boolean inBounds(TouchEvent event, int x, int y, int width,
                             int height) {
        double navAdjustX = x * 1.09;
        if (event.x > navAdjustX && event.x < navAdjustX + width - 1 && event.y > y
                && event.y < y + height - 1)
            return true;
        else
            return false;
    }

    @Override
    public void paint(float deltaTime) {
        Graphics g = game.getGraphics();
        int menuPos = x + currentDrag;
        if (menuPos > 0 && menuPos < maxMenuX) {
            g.drawImage(Assets.menu_forest, (0 - menuPos), 0);
            g.drawImage(Assets.menu_forest, (1280 - menuPos), 0);
            g.drawImage(Assets.menu_forest, (2560 - menuPos), 0);
            g.drawImage(Assets.menu_forest, (3840 - menuPos), 0);
            for (MenuLevelPosition level : LevelPositions) {
                if (level.getLevelNumber() == 1) {
                    Image image;
                    if (totalStars >= level.getStarsToUnlock()) {
                        image = Assets.level_1;
                    } else {
                        image = Assets.level_locked;
                    }
                    g.drawImage(image, level.getX() - menuPos, level.getY());
                } else if (level.getLevelNumber() == 2) {
                    Image image;
                    if (totalStars >= level.getStarsToUnlock()) {
                        image = Assets.level_2;
                    } else {
                        image = Assets.level_locked;
                    }
                    g.drawImage(image, level.getX() - menuPos, level.getY());
                } else if (level.getLevelNumber() == 3) {
                    Image image;
                    if (totalStars >= level.getStarsToUnlock()) {
                        image = Assets.level_3;
                    } else {
                        image = Assets.level_locked;
                    }
                    g.drawImage(image, level.getX() - menuPos, level.getY());
                } else if (level.getLevelNumber() == 4) {
                    Image image;
                    if (totalStars >= level.getStarsToUnlock()) {
                        image = Assets.level_4;
                    } else {
                        image = Assets.level_locked;
                    }
                    g.drawImage(image, level.getX() - menuPos, level.getY());
                } else if (level.getLevelNumber() == 5) {
                    Image image;
                    if (totalStars >= level.getStarsToUnlock()) {
                        image = Assets.level_5;
                    } else {
                        image = Assets.level_locked;
                    }
                    g.drawImage(image, level.getX() - menuPos, level.getY());
                } else if (level.getLevelNumber() == 6) {
                    Image image;
                    if (totalStars >= level.getStarsToUnlock()) {
                        image = Assets.level_6;
                    } else {
                        image = Assets.level_locked;
                    }
                    g.drawImage(image, level.getX() - menuPos, level.getY());
                } else if (level.getLevelNumber() == 7) {
                    Image image;
                    if (totalStars >= level.getStarsToUnlock()) {
                        image = Assets.level_7;
                    } else {
                        image = Assets.level_locked;
                    }
                    g.drawImage(image, level.getX() - menuPos, level.getY());
                } else if (level.getLevelNumber() == 8) {
                    Image image;
                    if (totalStars >= level.getStarsToUnlock()) {
                        image = Assets.level_8;
                    } else {
                        image = Assets.level_locked;
                    }
                    g.drawImage(image, level.getX() - menuPos, level.getY());
                } else if (level.getLevelNumber() == 9) {
                    Image image;
                    if (totalStars >= level.getStarsToUnlock()) {
                        image = Assets.level_9;
                    } else {
                        image = Assets.level_locked;
                    }
                    g.drawImage(image, level.getX() - menuPos, level.getY());
                } else if (level.getLevelNumber() == 10) {
                    Image image;
                    if (totalStars >= level.getStarsToUnlock()) {
                        image = Assets.level_10;
                    } else {
                        image = Assets.level_locked;
                    }
                    g.drawImage(image, level.getX() - menuPos, level.getY());
                }
                //TODO add levels over 10
                if (LoadSave.stars[level.getLevelNumber() - 1] == 1) {
                    g.drawImage(Assets.one_star_menu, level.getX() - menuPos, level.getY());
                } else if (LoadSave.stars[level.getLevelNumber() - 1] == 2) {
                    g.drawImage(Assets.two_stars_menu, level.getX() - menuPos, level.getY());
                } else if (LoadSave.stars[level.getLevelNumber() - 1] == 3) {
                    g.drawImage(Assets.three_stars_menu, level.getX() - menuPos, level.getY());
                }

            }

            if (insufficientStars) {
                if (System.nanoTime() < (insufficientStarsTime + 1500000000)) {
                    int x = 0;
                    int y = 0;
                    int starsRequired = 0;
                    for (MenuLevelPosition level : LevelPositions) {
                        if (insufficientStarsLevel == level.getLevelNumber()) {
                            x = level.getX()+60;
                            y = level.getY()-10;
                            starsRequired = level.getStarsToUnlock();
                        }
                    }
                    g.drawString("" + starsRequired + " stars required", x - menuPos, y, paint2);
                } else {
                    insufficientStars = false;
                }
            }

        } else if (menuPos <= 0) {
            g.drawImage(Assets.menu_forest, (0), 0);
            for (MenuLevelPosition level : LevelPositions) {
                if (level.getLevelNumber() == 1) {
                    Image image;
                    if (totalStars >= level.getStarsToUnlock()) {
                        image = Assets.level_1;
                    } else {
                        image = Assets.level_locked;
                    }
                    g.drawImage(image, level.getX(), level.getY());
                } else if (level.getLevelNumber() == 2) {
                    Image image;
                    if (totalStars >= level.getStarsToUnlock()) {
                        image = Assets.level_2;
                    } else {
                        image = Assets.level_locked;
                    }
                    g.drawImage(image, level.getX(), level.getY());
                } else if (level.getLevelNumber() == 3) {
                    Image image;
                    if (totalStars >= level.getStarsToUnlock()) {
                        image = Assets.level_3;
                    } else {
                        image = Assets.level_locked;
                    }
                    g.drawImage(image, level.getX(), level.getY());
                } else if (level.getLevelNumber() == 4) {
                    Image image;
                    if (totalStars >= level.getStarsToUnlock()) {
                        image = Assets.level_4;
                    } else {
                        image = Assets.level_locked;
                    }
                    g.drawImage(image, level.getX(), level.getY());
                }
                if (LoadSave.stars[level.getLevelNumber() - 1] == 1) {
                    g.drawImage(Assets.one_star_menu, level.getX(), level.getY());
                } else if (LoadSave.stars[level.getLevelNumber() - 1] == 2) {
                    g.drawImage(Assets.two_stars_menu, level.getX(), level.getY());
                } else if (LoadSave.stars[level.getLevelNumber() - 1] == 3) {
                    g.drawImage(Assets.three_stars_menu, level.getX(), level.getY());
                }
            }

            if (insufficientStars) {
                if (System.nanoTime() < (insufficientStarsTime + 1500000000)) {
                    int x = 0;
                    int y = 0;
                    int starsRequired = 0;
                    for (MenuLevelPosition level : LevelPositions) {
                        if (insufficientStarsLevel == level.getLevelNumber()) {
                            x = level.getX()+60;
                            y = level.getY()-10;
                            starsRequired = level.getStarsToUnlock();
                        }
                    }
                    g.drawString("" + starsRequired + " stars required", x, y, paint2);
                } else {
                    insufficientStars = false;
                }
            }

        } else if (menuPos >= maxMenuX) {
            g.drawImage(Assets.menu_forest, (0 - maxMenuX), 0);
            g.drawImage(Assets.menu_forest, (1280 - maxMenuX), 0);
            g.drawImage(Assets.menu_forest, (2560 - maxMenuX), 0);
            g.drawImage(Assets.menu_forest, (3840 - maxMenuX), 0);
            for (MenuLevelPosition level : LevelPositions) {
                if (level.getLevelNumber() == 5) {
                    Image image;
                    if (totalStars >= level.getStarsToUnlock()) {
                        image = Assets.level_5;
                    } else {
                        image = Assets.level_locked;
                    }
                    g.drawImage(image, level.getX() - maxMenuX, level.getY());
                } else if (level.getLevelNumber() == 6) {
                    Image image;
                    if (totalStars >= level.getStarsToUnlock()) {
                        image = Assets.level_6;
                    } else {
                        image = Assets.level_locked;
                    }
                    g.drawImage(image, level.getX() - maxMenuX, level.getY());
                } else if (level.getLevelNumber() == 7) {
                    Image image;
                    if (totalStars >= level.getStarsToUnlock()) {
                        image = Assets.level_7;
                    } else {
                        image = Assets.level_locked;
                    }
                    g.drawImage(image, level.getX() - maxMenuX, level.getY());
                } else if (level.getLevelNumber() == 8) {
                    Image image;
                    if (totalStars >= level.getStarsToUnlock()) {
                        image = Assets.level_8;
                    } else {
                        image = Assets.level_locked;
                    }
                    g.drawImage(image, level.getX() - maxMenuX, level.getY());
                } else if (level.getLevelNumber() == 9) {
                    Image image;
                    if (totalStars >= level.getStarsToUnlock()) {
                        image = Assets.level_9;
                    } else {
                        image = Assets.level_locked;
                    }
                    g.drawImage(image, level.getX() - maxMenuX, level.getY());
                } else if (level.getLevelNumber() == 10) {
                    Image image;
                    if (totalStars >= level.getStarsToUnlock()) {
                        image = Assets.level_10;
                    } else {
                        image = Assets.level_locked;
                    }
                    g.drawImage(image, level.getX() - maxMenuX, level.getY());
                }
                //todo levels over 10
                if (LoadSave.stars[level.getLevelNumber() - 1] == 1) {
                    g.drawImage(Assets.one_star_menu, level.getX() - maxMenuX, level.getY());
                } else if (LoadSave.stars[level.getLevelNumber() - 1] == 2) {
                    g.drawImage(Assets.two_stars_menu, level.getX() - maxMenuX, level.getY());
                } else if (LoadSave.stars[level.getLevelNumber() - 1] == 3) {
                    g.drawImage(Assets.three_stars_menu, level.getX() - maxMenuX, level.getY());
                }
            }

            if (insufficientStars) {
                if (System.nanoTime() < (insufficientStarsTime + 1500000000)) {
                    int x = 0;
                    int y = 0;
                    int starsRequired = 0;
                    for (MenuLevelPosition level : LevelPositions) {
                        if (insufficientStarsLevel == level.getLevelNumber()) {
                            x = level.getX()+60;
                            y = level.getY()-10;
                            starsRequired = level.getStarsToUnlock();
                        }
                    }
                    g.drawString("" + starsRequired + " stars required", x - maxMenuX, y, paint2);
                } else {
                    insufficientStars = false;
                }
            }
        }


        if (LoadSave.soundEnabled) {
            g.drawImage(Assets.soundOnButton, soundButtonX, soundButtonY);
        } else {
            g.drawImage(Assets.soundOffButton, soundButtonX, soundButtonY);
        }
        g.drawImage(Assets.star, 15, 8);
        g.drawString("x " + totalStars, 67, 44, paint);

    }

    private void selectLevel(int level) {
        LoadSave.level = level;
        LoadSave.save(game.getFileIO());
        Assets.menuMusic.stop();
        game.setScreen(new GameScreen(game, level));
    }

    private void setLevelPositions() {
        int x = 88;
        int y = 63;
        int gapX = 200;
        int gapY = 170;
        MenuLevelPosition level1 = new MenuLevelPosition(x, y, 1, 0);
        LevelPositions.add(level1);
        MenuLevelPosition level2 = new MenuLevelPosition((x+gapX), (y+gapY), 2, 3);
        LevelPositions.add(level2);
        MenuLevelPosition level3 = new MenuLevelPosition((x+gapX*2), y, 3, 4);
        LevelPositions.add(level3);
        MenuLevelPosition level4 = new MenuLevelPosition((x+gapX*3), (y+gapY), 4, 6);
        LevelPositions.add(level4);
        MenuLevelPosition level5 = new MenuLevelPosition((x+gapX*4), y, 5, 8);
        LevelPositions.add(level5);
        MenuLevelPosition level6 = new MenuLevelPosition((x+gapX*5), (y+gapY), 6, 8);
        LevelPositions.add(level6);
        MenuLevelPosition level7 = new MenuLevelPosition((x+gapX*6), y, 7, 10);
        LevelPositions.add(level7);
        MenuLevelPosition level8 = new MenuLevelPosition((x+gapX*7), (y+gapY), 8, 12);
        LevelPositions.add(level8);
        /**
         MenuLevelPosition level9 = new MenuLevelPosition((x+gapX*8), y, 9, 14);
         LevelPositions.add(level9);
         MenuLevelPosition level10 = new MenuLevelPosition((x+gapX*9), (y+gapY), 10, 15);
         LevelPositions.add(level10);
         */
        //todo levels over 10
    }

    private void setMaxMenuX(){
        int greatestX = 0;
        for (MenuLevelPosition level : LevelPositions) {
            if (level.getX() > greatestX){
                greatestX = level.getX();
            }
        }
        maxMenuX = greatestX-631;
    }

    private void setX(){
        int l = LoadSave.level;
        if (l >= 3){
            for (MenuLevelPosition level : LevelPositions) {
                if (level.levelNumber == l){
                    x = level.getX()-50;
                }
            }
        }
        if (x > maxMenuX){
            x = maxMenuX;
        }
    }

    private void setTotalStars(){
        int starCount=0;
        for (MenuLevelPosition level : LevelPositions){
            starCount+=LoadSave.getStars(level.getLevelNumber());
        }
        totalStars=starCount;
    }

    @Override
    public void pause() {
        Assets.menuMusic.pause();
        LoadSave.save(game.getFileIO());
    }

    @Override
    public void resume() {
        if(LoadSave.soundEnabled){
            Assets.menuMusic.play();
        }
    }

    @Override
    public void dispose() {

    }

    @Override
    public void backButton() {
        Assets.menuMusic.stop();
        LoadSave.save(game.getFileIO());
        android.os.Process.killProcess(android.os.Process.myPid());
    }

}


