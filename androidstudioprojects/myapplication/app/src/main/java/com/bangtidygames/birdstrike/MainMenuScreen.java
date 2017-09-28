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
    private boolean liftOnce = false;

    private int totalStars;

    private int x = 0;
    private int maxMenuX;
    private int currentDrag;
    private int drag;

    private int settingsX = 15;
    private int settingsY = 394;

    private Paint paint;

    private Set<MenuLevelPosition> LevelPositions= new HashSet<>();

    public MainMenuScreen(Game game) {
        super(game);

        paint = new Paint();
        paint.setTextSize(30);
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);

        this.setLevelPositions();
        this.setMaxMenuX();
        this.setTotalStars();
    }

    public MainMenuScreen(Game game, int xPos) {
        super(game);

        paint = new Paint();
        paint.setTextSize(30);
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);

        this.setLevelPositions();
        this.setMaxMenuX();
        this.setTotalStars();

        this.x=xPos;
    }

    @Override
    public void update(float deltaTime) {
        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
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
                liftOnce = true;
            }

            if (event.type == TouchEvent.TOUCH_UP) {
                if (dragging == false && liftOnce == true) {
                    for (MenuLevelPosition level : LevelPositions) {
                        if (inBounds(event, level.getX() - x, level.getY(), 119, 150)) {
                        if (totalStars >= level.getStarsToUnlock()) {
                            if (LoadSave.getHearts() != 0) {
                                selectLevel(level.getLevelNumber());
                            } else {
                                //TODO add you must wait x amount of time for hearts to reload
                            }
                        }
                    } else {
                            //TODO display you need x stars to unlock
                        }
                }
                    if (inBounds(event, settingsX, settingsY, 70, 71)){
                        game.setScreen(new SettingsScreen(game, x));
                    }
                } else {
                    x += currentDrag;
                    currentDrag = 0;
                    if (x < 0){
                        x = 0;
                    }
                    if (x > maxMenuX){
                        x = maxMenuX;
                    }
                    dragging = false;
                }
            }
        }
    }

    private boolean inBounds(TouchEvent event, int x, int y, int width,
                             int height) {
        if (event.x > x && event.x < x + width - 1 && event.y > y
                && event.y < y + height - 1)
            return true;
        else
            return false;
    }

    @Override
    public void paint(float deltaTime) {
        Graphics g = game.getGraphics();
        int menuPos = x+currentDrag;
        if (menuPos > 0 && menuPos < maxMenuX) {
            g.drawImage(Assets.menu_forest, (0-menuPos), 0);
            g.drawImage(Assets.menu_forest, (1280-menuPos), 0);
            g.drawImage(Assets.menu_forest, (2560-menuPos), 0);
            g.drawImage(Assets.menu_forest, (3840-menuPos), 0);
            for (MenuLevelPosition level : LevelPositions) {
                if(level.getLevelNumber() == 1) {
                    Image image;
                    if(totalStars>=level.getStarsToUnlock()){
                        image=Assets.level_1;
                    } else {
                        image=Assets.level_locked;
                    }
                    g.drawImage(image, level.getX() - menuPos, level.getY());
                } else if(level.getLevelNumber() == 2) {
                    Image image;
                    if(totalStars>=level.getStarsToUnlock()){
                        image=Assets.level_2;
                    } else {
                        image=Assets.level_locked;
                    }
                    g.drawImage(image, level.getX() - menuPos, level.getY());
                } else if(level.getLevelNumber() == 3) {
                    Image image;
                    if(totalStars>=level.getStarsToUnlock()){
                        image=Assets.level_3;
                    } else {
                        image=Assets.level_locked;
                    }
                    g.drawImage(image, level.getX() - menuPos, level.getY());
                } else if(level.getLevelNumber() == 4) {
                    Image image;
                    if(totalStars>=level.getStarsToUnlock()){
                        image=Assets.level_4;
                    } else {
                        image=Assets.level_locked;
                    }
                    g.drawImage(image, level.getX() - menuPos, level.getY());
                } else if(level.getLevelNumber() == 5) {
                    Image image;
                    if(totalStars>=level.getStarsToUnlock()){
                        image=Assets.level_5;
                    } else {
                        image=Assets.level_locked;
                    }
                    g.drawImage(image, level.getX() - menuPos, level.getY());
                } else if(level.getLevelNumber() == 6) {
                    Image image;
                    if(totalStars>=level.getStarsToUnlock()){
                        image=Assets.level_6;
                    } else {
                        image=Assets.level_locked;
                    }
                    g.drawImage(image, level.getX() - menuPos, level.getY());
                } else if(level.getLevelNumber() == 7) {
                    Image image;
                    if(totalStars>=level.getStarsToUnlock()){
                        image=Assets.level_7;
                    } else {
                        image=Assets.level_locked;
                    }
                    g.drawImage(image, level.getX() - menuPos, level.getY());
                } else if(level.getLevelNumber() == 8) {
                    Image image;
                    if(totalStars>=level.getStarsToUnlock()){
                        image=Assets.level_8;
                    } else {
                        image=Assets.level_locked;
                    }
                    g.drawImage(image, level.getX() - menuPos, level.getY());
                } else if(level.getLevelNumber() == 9) {
                    Image image;
                    if(totalStars>=level.getStarsToUnlock()){
                        image=Assets.level_9;
                    } else {
                        image=Assets.level_locked;
                    }
                    g.drawImage(image, level.getX() - menuPos, level.getY());
                } else if(level.getLevelNumber() == 10) {
                    Image image;
                    if(totalStars>=level.getStarsToUnlock()){
                        image=Assets.level_10;
                    } else {
                        image=Assets.level_locked;
                    }
                    g.drawImage(image, level.getX() - menuPos, level.getY());
                }
                //TODO add levels
                if (LoadSave.stars[level.getLevelNumber()-1] == 1){
                    g.drawImage(Assets.one_star_menu, level.getX() - menuPos, level.getY());
                } else if (LoadSave.stars[level.getLevelNumber()-1] == 2) {
                    g.drawImage(Assets.two_stars_menu, level.getX() - menuPos, level.getY());
                } else if (LoadSave.stars[level.getLevelNumber()-1] == 3) {
                    g.drawImage(Assets.three_stars_menu, level.getX() - menuPos, level.getY());
                }
            }
        } else if (menuPos <= 0){
            g.drawImage(Assets.menu_forest, (0), 0);
            for (MenuLevelPosition level : LevelPositions) {
                if(level.getLevelNumber() == 1) {
                    Image image;
                    if(totalStars>=level.getStarsToUnlock()){
                        image=Assets.level_1;
                    } else {
                        image=Assets.level_locked;
                    }
                    g.drawImage(image, level.getX(), level.getY());
                } else if(level.getLevelNumber() == 2) {
                    Image image;
                    if(totalStars>=level.getStarsToUnlock()){
                        image=Assets.level_2;
                    } else {
                        image=Assets.level_locked;
                    }
                    g.drawImage(image, level.getX(), level.getY());
                } else if(level.getLevelNumber() == 3) {
                    Image image;
                    if(totalStars>=level.getStarsToUnlock()){
                        image=Assets.level_3;
                    } else {
                        image=Assets.level_locked;
                    }
                    g.drawImage(image, level.getX(), level.getY());
                } else if(level.getLevelNumber() == 4) {
                    Image image;
                    if(totalStars>=level.getStarsToUnlock()){
                        image=Assets.level_4;
                    } else {
                        image=Assets.level_locked;
                    }
                    g.drawImage(image, level.getX(), level.getY());
                }
                if (LoadSave.stars[level.getLevelNumber()-1] == 1){
                    g.drawImage(Assets.one_star_menu, level.getX(), level.getY());
                } else if (LoadSave.stars[level.getLevelNumber()-1] == 2) {
                    g.drawImage(Assets.two_stars_menu, level.getX(), level.getY());
                } else if (LoadSave.stars[level.getLevelNumber()-1] == 3) {
                    g.drawImage(Assets.three_stars_menu, level.getX(), level.getY());
                }
            }
        } else if (menuPos >= maxMenuX){
            g.drawImage(Assets.menu_forest, (0-maxMenuX), 0);
            g.drawImage(Assets.menu_forest, (1280-maxMenuX), 0);
            g.drawImage(Assets.menu_forest, (2560-maxMenuX), 0);
            g.drawImage(Assets.menu_forest, (3840-maxMenuX), 0);
            for (MenuLevelPosition level : LevelPositions) {
                if(level.getLevelNumber() == 7) {
                    Image image;
                    if(totalStars>=level.getStarsToUnlock()){
                        image=Assets.level_7;
                    } else {
                        image=Assets.level_locked;
                    }
                    g.drawImage(image, level.getX()-maxMenuX, level.getY());
                } else if(level.getLevelNumber() == 8) {
                    Image image;
                    if(totalStars>=level.getStarsToUnlock()){
                        image=Assets.level_8;
                    } else {
                        image=Assets.level_locked;
                    }
                    g.drawImage(image, level.getX()-maxMenuX, level.getY());
                } else if(level.getLevelNumber() == 9) {
                    Image image;
                    if(totalStars>=level.getStarsToUnlock()){
                        image=Assets.level_9;
                    } else {
                        image=Assets.level_locked;
                    }
                    g.drawImage(image, level.getX()-maxMenuX, level.getY());
                } else if(level.getLevelNumber() == 10) {
                    Image image;
                    if(totalStars>=level.getStarsToUnlock()){
                        image=Assets.level_10;
                    } else {
                        image=Assets.level_locked;
                    }
                    g.drawImage(image, level.getX()-maxMenuX, level.getY());
                }
                if (LoadSave.stars[level.getLevelNumber()-1] == 1){
                    g.drawImage(Assets.one_star_menu, level.getX()-maxMenuX, level.getY());
                } else if (LoadSave.stars[level.getLevelNumber()-1] == 2) {
                    g.drawImage(Assets.two_stars_menu, level.getX()-maxMenuX, level.getY());
                } else if (LoadSave.stars[level.getLevelNumber()-1] == 3) {
                    g.drawImage(Assets.three_stars_menu, level.getX()-maxMenuX, level.getY());
                }
            }
        }
        g.drawImage(Assets.setting, settingsX, settingsY);
        g.drawImage(Assets.star, 15, 8);
        g.drawString("x "+ totalStars, 67, 44, paint);

    }

    private void selectLevel(int level) {
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
        MenuLevelPosition level7 = new MenuLevelPosition((x+gapX*6), y, 7, 11);
        LevelPositions.add(level7);
        MenuLevelPosition level8 = new MenuLevelPosition((x+gapX*7), (y+gapY), 8, 11);
        LevelPositions.add(level8);
        MenuLevelPosition level9 = new MenuLevelPosition((x+gapX*8), y, 9, 14);
        LevelPositions.add(level9);
        MenuLevelPosition level10 = new MenuLevelPosition((x+gapX*9), (y+gapY), 10, 15);
        LevelPositions.add(level10);
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

    private void setTotalStars(){
        int starCount=0;
        for (MenuLevelPosition level : LevelPositions){
            starCount+=LoadSave.getStars(level.getLevelNumber());
        }
        totalStars=starCount;
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }

    @Override
    public void backButton() {
        //TODO hide process?
        android.os.Process.killProcess(android.os.Process.myPid());
    }

}


