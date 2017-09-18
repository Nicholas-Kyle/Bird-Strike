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

                    //  Enter level screen positions here
                    // add system to adjust screen positions for future updates
                    // and to share screen positions with graphics for buttons

                    for (MenuLevelPosition level : LevelPositions) {
                        if (inBounds(event, level.getX() - x, level.getY(), 119, 150)) {
                            if (LoadSave.getHearts() != 0) {
                                selectLevel(2);
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
        if (menuPos > 0) {
            g.drawImage(Assets.menu_forest, (0-menuPos), 0);
            for (MenuLevelPosition level : LevelPositions) {
                g.drawImage(Assets.level_1, level.getX() - menuPos, level.getY());
            }
        } else {
            g.drawImage(Assets.menu_forest, (0), 0);
            for (MenuLevelPosition level : LevelPositions) {
                g.drawImage(Assets.level_1, level.getX(), level.getY());
            }
        }
        g.drawImage(Assets.menu_desert, (1280-menuPos), 0);
        g.drawImage(Assets.menu_forest, (2560-menuPos), 0);
        g.drawImage(Assets.menu_forest, (3840-menuPos), 0);

    }

    private void selectLevel(int level) {
        game.setScreen(new GameScreen(game, level));
    }

    private void setLevelPositions() {
        MenuLevelPosition level1 = new MenuLevelPosition(88, 88);
        LevelPositions.add(level1);
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


