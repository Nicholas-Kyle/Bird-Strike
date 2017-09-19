package com.bangtidygames.birdstrike;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.bangtidygames.framework.Game;
import com.bangtidygames.framework.Graphics;
import com.bangtidygames.framework.Screen;
import com.bangtidygames.framework.Input.TouchEvent;

/**
 * Created by Nick on 15/09/2017.
 */

public class MainMenuScreen extends Screen {

    private boolean dragging = false;

    private int x = 0;
    private int maxMenuX = 480;
    private int currentDrag;
    private int drag;

    private Set<MenuLevelPosition> LevelPositions= new HashSet<>();

    public MainMenuScreen(Game game) {
        super(game);
        this.setLevelPositions();
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

            if (event.type == TouchEvent.TOUCH_UP) {
                if (dragging == false) {
                    for (MenuLevelPosition level : LevelPositions) {
                        if (inBounds(event, level.getX() - x, level.getY(), 119, 150)) {
                            if (LoadSave.getHearts() != 0) {
                                selectLevel(level.getLevelNumber());
                            } else {
                                //TODO add you must wait x amount of time for hearts to reload
                            }
                        }
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
                    g.drawImage(Assets.level_1, level.getX() - menuPos, level.getY());
                } else if(level.getLevelNumber() == 2) {
                    g.drawImage(Assets.level_2, level.getX() - menuPos, level.getY());
                } else if(level.getLevelNumber() == 3) {
                    g.drawImage(Assets.level_3, level.getX() - menuPos, level.getY());
                } else if(level.getLevelNumber() == 4) {
                    g.drawImage(Assets.level_4, level.getX() - menuPos, level.getY());
                } else if(level.getLevelNumber() == 5) {
                    g.drawImage(Assets.level_5, level.getX() - menuPos, level.getY());
                } else if(level.getLevelNumber() == 6) {
                    g.drawImage(Assets.level_6, level.getX() - menuPos, level.getY());
                }
                if (LoadSave.stars[level.getLevelNumber()-1] == 1){
                    g.drawImage(Assets.one_star, level.getX() - menuPos, level.getY());
                } else if (LoadSave.stars[level.getLevelNumber()-1] == 2) {
                    g.drawImage(Assets.two_stars, level.getX() - menuPos, level.getY());
                } else if (LoadSave.stars[level.getLevelNumber()-1] == 3) {
                    g.drawImage(Assets.three_stars, level.getX() - menuPos, level.getY());
                }
            }
        } else if (menuPos <= 0){
            g.drawImage(Assets.menu_forest, (0), 0);
            for (MenuLevelPosition level : LevelPositions) {
                if(level.getLevelNumber() == 1) {
                    g.drawImage(Assets.level_1, level.getX(), level.getY());
                } else if(level.getLevelNumber() == 2) {
                    g.drawImage(Assets.level_2, level.getX(), level.getY());
                } else if(level.getLevelNumber() == 3) {
                    g.drawImage(Assets.level_3, level.getX(), level.getY());
                } else if(level.getLevelNumber() == 4) {
                    g.drawImage(Assets.level_4, level.getX(), level.getY());
                } else if(level.getLevelNumber() == 5) {
                    g.drawImage(Assets.level_5, level.getX(), level.getY());
                } else if(level.getLevelNumber() == 6) {
                    g.drawImage(Assets.level_6, level.getX(), level.getY());
                }
                if (LoadSave.stars[level.getLevelNumber()-1] == 1){
                    g.drawImage(Assets.one_star, level.getX(), level.getY());
                } else if (LoadSave.stars[level.getLevelNumber()-1] == 2) {
                    g.drawImage(Assets.two_stars, level.getX(), level.getY());
                } else if (LoadSave.stars[level.getLevelNumber()-1] == 3) {
                    g.drawImage(Assets.three_stars, level.getX(), level.getY());
                }
            }
        } else if (menuPos > maxMenuX){
            g.drawImage(Assets.menu_forest, (0-maxMenuX), 0);
            g.drawImage(Assets.menu_forest, (1280-maxMenuX), 0);
            g.drawImage(Assets.menu_forest, (2560-maxMenuX), 0);
            g.drawImage(Assets.menu_forest, (3840-maxMenuX), 0);
            for (MenuLevelPosition level : LevelPositions) {
                if(level.getLevelNumber() == 1) {
                    g.drawImage(Assets.level_1, level.getX()-maxMenuX, level.getY());
                } else if(level.getLevelNumber() == 2) {
                    g.drawImage(Assets.level_2, level.getX()-maxMenuX, level.getY());
                } else if(level.getLevelNumber() == 3) {
                    g.drawImage(Assets.level_3, level.getX()-maxMenuX, level.getY());
                } else if(level.getLevelNumber() == 4) {
                    g.drawImage(Assets.level_4, level.getX()-maxMenuX, level.getY());
                } else if(level.getLevelNumber() == 5) {
                    g.drawImage(Assets.level_5, level.getX()-maxMenuX, level.getY());
                } else if(level.getLevelNumber() == 6) {
                    g.drawImage(Assets.level_6, level.getX()-maxMenuX, level.getY());
                }
                if (LoadSave.stars[level.getLevelNumber()-1] == 1){
                    g.drawImage(Assets.one_star, level.getX()-maxMenuX, level.getY());
                } else if (LoadSave.stars[level.getLevelNumber()-1] == 2) {
                    g.drawImage(Assets.two_stars, level.getX()-maxMenuX, level.getY());
                } else if (LoadSave.stars[level.getLevelNumber()-1] == 3) {
                    g.drawImage(Assets.three_stars, level.getX()-maxMenuX, level.getY());
                }
            }
        }
    }

    private void selectLevel(int level) {
        game.setScreen(new GameScreen(game, level));
    }

    private void setLevelPositions() {
        int x = 88;
        int y = 88;
        int gapX = 200;
        int gapY = 150;
        MenuLevelPosition level1 = new MenuLevelPosition(x, y, 1);
        LevelPositions.add(level1);
        MenuLevelPosition level2 = new MenuLevelPosition((x+gapX*1), (y+gapY*1), 2);
        LevelPositions.add(level2);
        MenuLevelPosition level3 = new MenuLevelPosition((x+gapX*2), y, 3);
        LevelPositions.add(level3);
        MenuLevelPosition level4 = new MenuLevelPosition((x+gapX*3), (y+gapY*1), 4);
        LevelPositions.add(level4);
        MenuLevelPosition level5 = new MenuLevelPosition((x+gapX*4), y, 5);
        LevelPositions.add(level5);
        MenuLevelPosition level6 = new MenuLevelPosition((x+gapX*5), (y+gapY*1), 6);
        LevelPositions.add(level6);
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
        android.os.Process.killProcess(android.os.Process.myPid());
    }

}


