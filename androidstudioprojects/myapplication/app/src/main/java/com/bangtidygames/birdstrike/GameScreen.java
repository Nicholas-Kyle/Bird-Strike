package com.bangtidygames.birdstrike;

import java.util.List;

import android.graphics.Color;
import android.graphics.Paint;

import com.bangtidygames.framework.Game;
import com.bangtidygames.framework.Graphics;
import com.bangtidygames.framework.Image;
import com.bangtidygames.framework.Input.TouchEvent;
import com.bangtidygames.framework.Screen;

/**
 * Created by Nick on 15/09/2017.
 */

public class GameScreen extends Screen {
    enum GameState {
        NumberOfLaps, Ready, Running, Paused, GameOver, Complete
    }

    GameState state = GameState.NumberOfLaps;

    private static Robot robot;

    private static LevelManager levelManager;

    private float startTime = 0;
    private float crashTime = 0;

    private Image currentSprite;

    private int pauseXPos = 15;
    private int pauseYPos = 394;
    private int pauseUIResumeXPos = 250;
    private int pauseUIResumeYPos = 50;
    private int pauseUIRestartXPos = 250;
    private int pauseUIRestartYPos = 180;
    private int pauseUIMenuXPos = 250;
    private int pauseUIMenuYPos = 310;

    private Paint paint, paint2;

    private boolean liftOnce;

    public GameScreen(Game game, int levelNumber) {
        super(game);

        robot = new Robot();
        levelManager = new LevelManager(levelNumber);

        currentSprite = Assets.bluebiplane;

        // Defining a paint object
        paint = new Paint();
        paint.setTextSize(30);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setAntiAlias(true);
        paint.setColor(Color.WHITE);

        paint2 = new Paint();
        paint2.setTextSize(100);
        paint2.setTextAlign(Paint.Align.CENTER);
        paint2.setAntiAlias(true);
        paint2.setColor(Color.WHITE);

    }

    @Override
    public void update(float deltaTime) {
        List touchEvents = game.getInput().getTouchEvents();

        if (state == GameState.NumberOfLaps)
            updateNumberOfLaps();
        if (state == GameState.Ready)
            updateReady();
        if (state == GameState.Running)
            updateRunning(touchEvents, deltaTime);
        if (state == GameState.Paused)
            updatePaused(touchEvents);
        if (state == GameState.GameOver)
            updateGameOver(touchEvents);
        if (state == GameState.Complete)
            updateComplete(touchEvents);
    }

    private void updateNumberOfLaps() {
        if (startTime == 0) {
            startTime = System.nanoTime();
        }
        if (System.nanoTime() > (startTime + 750000000)) {
            state = GameState.Ready;
        }
    }

    private void updateReady() {
        if (System.nanoTime() > (startTime + 1500000000)) {
            state = GameState.Running;
        }
    }

    private void updateRunning(List touchEvents, float deltaTime) {
        if (robot.isCollision() || robot.isTooHigh() || robot.isBadLanding()) {
            if (crashTime == 0) {
                crashTime = System.nanoTime();
                currentSprite = Assets.explosion;
                Assets.crash.play(0.60f);
            }
            if (System.nanoTime() > (crashTime + 250000000)) {
                if (LoadSave.hearts != 0) {
                    LoadSave.hearts--;
                }

                LoadSave.saveHearts(game.getFileIO());
                state = GameState.GameOver;
            }
        } else {

            int len = touchEvents.size();
            for (int i = 0; i < len; i++) {
                TouchEvent event = (TouchEvent) touchEvents.get(i);
                if (event.type == TouchEvent.TOUCH_DOWN) {
                    if (inBounds(event, pauseXPos, pauseYPos, 70, 70)) {
                        this.liftOnce = false;
                        this.pause();
                    } else {
                        robot.moveUp();
                    }
                }

                if (event.type == TouchEvent.TOUCH_UP) {
                    robot.stopUp();
                }
            }


            if (robot.isCaught()) {
                state = GameState.GameOver;
            }

            if (robot.isCaughtOpponent()) {
                if (levelManager.getRemainingOpponents() == 0) {
                    state = GameState.GameOver;
                } else {
                    robot.setCaughtOpponent(false);
                }
            }

            robot.update(deltaTime);
            levelManager.updateTiles();
            levelManager.updateBirds(deltaTime);
            levelManager.updateBackground();
            if (levelManager.isLevelComplete()) {
                if (LoadSave.getStars(levelManager.getLevel()) == 2) {
                    if (robot.getBirdsHit() >= levelManager.getThreeStars()) {
                        LoadSave.addStars(levelManager.getLevel(), 3);
                        LoadSave.saveStars(game.getFileIO());
                    }
                } else if (LoadSave.getStars(levelManager.getLevel()) == 1) {
                    if (robot.getBirdsHit() >= levelManager.getThreeStars()) {
                        LoadSave.addStars(levelManager.getLevel(), 3);
                        LoadSave.saveStars(game.getFileIO());
                    } else if (robot.getBirdsHit() >= levelManager.getTwoStars()) {
                        LoadSave.addStars(levelManager.getLevel(), 2);
                        LoadSave.saveStars(game.getFileIO());
                    }
                } else if (LoadSave.getStars(levelManager.getLevel()) == 0) {
                    if (robot.getBirdsHit() >= levelManager.getThreeStars()) {
                        LoadSave.addStars(levelManager.getLevel(), 3);
                        LoadSave.saveStars(game.getFileIO());
                    } else if (robot.getBirdsHit() >= levelManager.getTwoStars()) {
                        LoadSave.addStars(levelManager.getLevel(), 2);
                        LoadSave.saveStars(game.getFileIO());
                    } else if (robot.getBirdsHit() >= levelManager.getOneStar()) {
                        LoadSave.addStars(levelManager.getLevel(), 1);
                        LoadSave.saveStars(game.getFileIO());
                    }
                }
                state = GameState.Complete;
            }
        }
    }


    private boolean inBounds(TouchEvent event, int x, int y, int width,
                             int height) {
        return (event.x > x && event.x < x + width - 1 && event.y > y
                && event.y < y + height - 1);
    }

    private void updatePaused(List touchEvents) {
        int buttonWidth = 300;
        int buttonHeight = 108;
        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            TouchEvent event = (TouchEvent) touchEvents.get(i);
            if (event.type == TouchEvent.TOUCH_UP && liftOnce) {
                if (inBounds(event, pauseUIResumeXPos, pauseUIResumeYPos, buttonWidth,
                        buttonHeight)) {
                    resume();
                } else if (inBounds(event, pauseUIRestartXPos, pauseUIRestartYPos, buttonWidth,
                        buttonHeight)) {
                    nullify();
                    restartLevel(levelManager.getLevel());
                } else if (inBounds(event, pauseUIMenuXPos, pauseUIMenuYPos, buttonWidth,
                        buttonHeight)) {
                    nullify();
                    goToMenu();
                }
            } else {
                liftOnce = true;
            }
        }
    }

    private void updateGameOver(List touchEvents) {
        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            TouchEvent event = (TouchEvent) touchEvents.get(i);
            if (event.type == TouchEvent.TOUCH_DOWN) {
                if (inBounds(event, 0, 0, 800, 480)) {
                    nullify();
                    game.setScreen(new MainMenuScreen(game));
                    return;
                }
            }
        }

    }

    private void updateComplete(List touchEvents) {
        int len = touchEvents.size();

        for (int i = 0; i < len; i++) {
            TouchEvent event = (TouchEvent) touchEvents.get(i);
            if (event.type == TouchEvent.TOUCH_DOWN) {
                if (inBounds(event, 0, 240, 800, 240)) {
                    nullify();
                    goToMenu();
                }
            }
        }
    }

    @Override
    public void paint(float deltaTime) {
        Graphics g = game.getGraphics();

        levelManager.paintBackground(g);

        levelManager.paintTiles(g);

        g.drawImage(currentSprite, robot.getCenterX() - 39,
                robot.getCenterY() - 18);

        levelManager.paintWarp(g);

        levelManager.paintBirds(g);

        if (state == GameState.NumberOfLaps)

            drawNumberOfLapsUI();

        if (state == GameState.Ready)

            drawReadyUI();

        if (state == GameState.Running)

            drawRunningUI();

        if (state == GameState.Paused)

            drawPausedUI();

        if (state == GameState.GameOver)

            drawGameOverUI();

        if (state == GameState.Complete)

            drawCompleteUI();

    }

    private void nullify() {

        paint = null;
        robot = null;
        currentSprite = null;
//TODO add additional null as needed
        System.gc();

    }

    private void drawNumberOfLapsUI() {
        Graphics g = game.getGraphics();
        g.drawARGB(155, 0, 0, 0);
        g.drawString(levelManager.getLap() + " Laps", 400, 240, paint);
    }

    private void drawReadyUI() {
        Graphics g = game.getGraphics();
        g.drawARGB(155, 0, 0, 0);
        g.drawString("Ready", 400, 240, paint);
    }

    private void drawRunningUI() {
        Graphics g = game.getGraphics();
        if (!levelManager.isLevelComplete()) {
            g.drawImage(HUD.getHearts(LoadSave.hearts), 0, 0);
            g.drawImage(HUD.getBirds(robot.getBirdsHit()), 0, 0);
            g.drawImage(HUD.getLaps(levelManager.getLap()), 0, 0);
            g.drawImage(Assets.pause, pauseXPos, pauseYPos);
        }
    }

    private void drawPausedUI() {
        Graphics g = game.getGraphics();
        // Darken the entire screen so you can display the Paused screen.
        g.drawARGB(155, 0, 0, 0);
        g.drawImage(Assets.resumeButton, pauseUIResumeXPos, pauseUIResumeYPos);
        g.drawImage(Assets.restartButton, pauseUIRestartXPos, pauseUIRestartYPos);
        g.drawImage(Assets.menuButton, pauseUIMenuXPos, pauseUIMenuYPos);

    }

    private void drawGameOverUI() {
        Graphics g = game.getGraphics();
        g.drawRect(0, 0, 1281, 801, Color.BLACK);
        if (robot.isCollision()) {
            g.drawString("Ok good try, next time try to avoid that, ok", 400, 240, paint);
        } else if (robot.isTooHigh()) {
            g.drawString("Looks like you flew too close to the sun", 400, 240, paint);
        } else if (robot.isBadLanding()) {
            g.drawString("Try landing a bit slower", 400, 240, paint);
        }
        //g.drawString("Tap to return.", 400, 290, paint);

    }

    private void drawCompleteUI() {
        Graphics g = game.getGraphics();
        g.drawRect(0, 0, 1281, 801, Color.BLACK);
        g.drawString("You hit " + robot.getBirdsHit() + " birds", 400, 165, paint2);
        g.drawString("Menu", 400, 360, paint2);

        //g.drawString("Tap to return.", 400, 290, paint);

    }

    @Override
    public void pause() {
        if (state == GameState.Running)
            state = GameState.Paused;
    }

    @Override
    public void resume() {
        if (state == GameState.Paused)
            state = GameState.Running;
    }

    @Override
    public void dispose() {

    }

    @Override
    public void backButton() {
        pause();
    }

    private void goToMenu() {
        game.setScreen(new MainMenuScreen(game));
    }

    private void restartLevel(int level) {
        Assets.crash = game.getAudio().createSound("SFX/explode.ogg");
        game.setScreen(new GameScreen(game, level));
    }

    public static Robot getRobot() {
        return robot;
    }

    public static LevelManager getLevelManager() {
        return levelManager;
    }
}