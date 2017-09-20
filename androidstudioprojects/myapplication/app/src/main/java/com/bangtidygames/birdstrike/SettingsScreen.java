package com.bangtidygames.birdstrike;

import com.bangtidygames.framework.Game;
import com.bangtidygames.framework.Graphics;
import com.bangtidygames.framework.Screen;
import com.bangtidygames.framework.Input.TouchEvent;

import java.util.List;

/**
 * Created by Nick on 20/09/2017.
 */

public class SettingsScreen extends Screen {

    private int xPos;

    public SettingsScreen(Game game, int xPos) {
        super(game);
        this.xPos= xPos;
    }

    @Override
    public void update(float deltaTime) {
        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);

            if (event.type == TouchEvent.TOUCH_UP) {
                if (inBounds(event, 50, 50, 70, 71)) {
                    //TODO
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
        g.drawImage(Assets.menu_forest, -(xPos % 640), 0);
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
        game.setScreen(new MainMenuScreen(game, xPos));
    }

}