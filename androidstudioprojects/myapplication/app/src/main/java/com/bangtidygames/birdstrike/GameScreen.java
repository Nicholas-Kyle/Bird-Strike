package com.bangtidygames.birdstrike;

import java.util.List;
import java.util.Random;

import android.graphics.Color;
import android.graphics.Paint;

import com.bangtidygames.framework.Game;
import com.bangtidygames.framework.Graphics;
import com.bangtidygames.framework.Image;
import com.bangtidygames.framework.Input.TouchEvent;
import com.bangtidygames.framework.Screen;

import static com.bangtidygames.birdstrike.Assets.*;

/**
 * Created by Nick on 15/09/2017.
 */

public class GameScreen extends Screen {
    enum GameState {
        Ready, Running, Paused, GameOver, Complete
    }

    GameState state = GameState.Ready;

    private static Robot robot;

    private static LevelManager levelManager;

    private float startTime = 0;
    private float crashTime = 0;

    private Image currentSprite;

    private int  n;

    private int pauseXPos = 15;
    private int pauseYPos = 394;
    private int pauseUIResumeXPos = 250;
    private int pauseUIResumeYPos = 50;
    private int pauseUIRestartXPos = 250;
    private int pauseUIRestartYPos = 180;
    private int pauseUIMenuXPos = 250;
    private int pauseUIMenuYPos = 310;
    private int buttonWidth = 300;
    private int buttonHeight = 108;

    private Paint paint, paint2;

    private boolean liftOnce;

    public GameScreen(Game game, int levelNumber) {
        super(game);

        if (LoadSave.soundEnabled) {
            Assets.gameMusic.play();
        }

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

        robot = new Robot();
        levelManager = new LevelManager(levelNumber);

        currentSprite = bluebiplane;

        Random rand = new Random();
        //Generates a random int between 1 and 50
        n = rand.nextInt(50) + 1;

    }

    @Override
    public void update(float deltaTime) {
        List touchEvents = game.getInput().getTouchEvents();

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

    private void updateReady() {
        if (startTime == 0) {
            startTime = System.nanoTime();
        }
        if (System.nanoTime() > (startTime + 750000000)) {
            state = GameState.Running;
        }
    }

    private void updateRunning(List touchEvents, float deltaTime) {
        if (robot.isCollision() || robot.isTooHigh() || robot.isBadLanding()) {
            if (crashTime == 0) {
                crashTime = System.nanoTime();
                currentSprite = explosion;
                playSound(crash);
            }
            if (System.nanoTime() > (crashTime + 250000000)) {
                if (LoadSave.hearts != 0) {
                    LoadSave.hearts--;
                }
                LoadSave.save(game.getFileIO());
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

            robot.update(deltaTime);
            levelManager.updateTiles();
            levelManager.updateBirds(deltaTime);
            levelManager.updateBackground();
            if (levelManager.isLevelComplete()) {
                if (LoadSave.getStars(levelManager.getLevel()) == 2) {
                    if (robot.getBirdsHit() >= levelManager.getThreeStars()) {
                        LoadSave.addStars(levelManager.getLevel(), 3);
                    }
                } else if (LoadSave.getStars(levelManager.getLevel()) == 1) {
                    if (robot.getBirdsHit() >= levelManager.getThreeStars()) {
                        LoadSave.addStars(levelManager.getLevel(), 3);
                    } else if (robot.getBirdsHit() >= levelManager.getTwoStars()) {
                        LoadSave.addStars(levelManager.getLevel(), 2);
                    }
                } else if (LoadSave.getStars(levelManager.getLevel()) == 0) {
                    if (robot.getBirdsHit() >= levelManager.getThreeStars()) {
                        LoadSave.addStars(levelManager.getLevel(), 3);
                    } else if (robot.getBirdsHit() >= levelManager.getTwoStars()) {
                        LoadSave.addStars(levelManager.getLevel(), 2);
                    } else if (robot.getBirdsHit() >= levelManager.getOneStar()) {
                        LoadSave.addStars(levelManager.getLevel(), 1);
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
        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            TouchEvent event = (TouchEvent) touchEvents.get(i);
            if (event.type == TouchEvent.TOUCH_UP) {
                liftOnce = true;
            } if (event.type == TouchEvent.TOUCH_DOWN && liftOnce) {
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
                } else if (inBounds(event, pauseXPos, pauseYPos, 70, 71)){
                    LoadSave.soundEnabled = !LoadSave.soundEnabled;
                }
            }
        }
    }

    private void updateGameOver(List touchEvents) {
        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            TouchEvent event = (TouchEvent) touchEvents.get(i);
            if (event.type == TouchEvent.TOUCH_DOWN) {
                liftOnce = true;
            }
            if (event.type == TouchEvent.TOUCH_UP && liftOnce) {
                if (inBounds(event, pauseUIRestartXPos, pauseUIRestartYPos, buttonWidth,
                        buttonHeight)) {
                    nullify();
                    restartLevel(levelManager.getLevel());
                } else if (inBounds(event, pauseUIMenuXPos, pauseUIMenuYPos, buttonWidth,
                        buttonHeight)) {
                    nullify();
                    goToMenu();
                }
            }
        }
    }

    private void updateComplete(List touchEvents) {
        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            TouchEvent event = (TouchEvent) touchEvents.get(i);
            if (event.type == TouchEvent.TOUCH_DOWN) {
                liftOnce = true;
            }
            if (event.type == TouchEvent.TOUCH_UP && liftOnce) {
                if (inBounds(event, pauseUIRestartXPos, pauseUIRestartYPos, buttonWidth,
                        buttonHeight)) {
                    nullify();
                    restartLevel(levelManager.getLevel());
                } else if (inBounds(event, pauseUIMenuXPos, pauseUIMenuYPos, buttonWidth,
                        buttonHeight)) {
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

        levelManager.paintBirds(g);

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
            g.drawImage(HUD.getCheckpoints(levelManager.getCheckpoints()), 0, 0);
            g.drawImage(pause, pauseXPos, pauseYPos);
        }
    }

    private void drawPausedUI() {
        Graphics g = game.getGraphics();
        g.drawARGB(155, 0, 0, 0);
        g.drawImage(resumeButton, pauseUIResumeXPos, pauseUIResumeYPos);
        g.drawImage(restartButton, pauseUIRestartXPos, pauseUIRestartYPos);
        g.drawImage(menuButton, pauseUIMenuXPos, pauseUIMenuYPos);
        if(LoadSave.soundEnabled){
            g.drawImage(soundOnButton, pauseXPos, pauseYPos);
        } else {
         g.drawImage(soundOffButton, pauseXPos, pauseYPos);
        }
    }

    private void drawGameOverUI() {
        Graphics g = game.getGraphics();
        int textYPos = (pauseUIResumeYPos + buttonHeight/2);
        g.drawARGB(155, 0, 0, 0);

        if (robot.isCollision()) {
            if (n<=44) {
                g.drawString("Ok good try, next time try to avoid that", 400, textYPos, paint);
            } else if (n>44 && n<=48) {
                g.drawString("Wow, that was truly awful", 400, textYPos, paint);
            } else if (n>48 && n<=50) {
                g.drawString("I say 'weak', you say 'sauce'", 400, textYPos, paint);
            }
        } else if (robot.isTooHigh()) {
            g.drawString("Looks like you flew too close to the sun", 400, textYPos, paint);
        } else if (robot.isBadLanding()) {
            g.drawString("Try landing a bit slower", 400, textYPos, paint);
        }
        g.drawImage(restartButton, pauseUIRestartXPos, pauseUIRestartYPos);
        g.drawImage(menuButton, pauseUIMenuXPos, pauseUIMenuYPos);

    }

    private void drawCompleteUI() {
        Graphics g = game.getGraphics();
        g.drawARGB(155, 0, 0, 0);
        g.drawString("Well done, you hit " + robot.getBirdsHit() + " birds", 400, 35, paint);
        if (LoadSave.getStars(levelManager.getLevel())==1) {
            g.drawImage(one_star_complete, 275, pauseUIRestartYPos - 125);
        } else if (LoadSave.getStars(levelManager.getLevel())==2) {
            g.drawImage(two_stars_complete, 275, pauseUIRestartYPos - 125);
        } else if (LoadSave.getStars(levelManager.getLevel())==3) {
            g.drawImage(three_stars_complete, 275, pauseUIRestartYPos - 125);
        }
        g.drawImage(restartButton, pauseUIRestartXPos, pauseUIRestartYPos);
        g.drawImage(menuButton, pauseUIMenuXPos, pauseUIMenuYPos);
    }

    @Override
    public void pause() {
        if (state == GameState.Running) state = GameState.Paused;
        gameMusic.pause();
    }

    @Override
    public void resume() {
        if (LoadSave.soundEnabled) {
            gameMusic.play();
        }
        if (state == GameState.Paused) state = GameState.Running;
    }

    @Override
    public void dispose() {

    }

    @Override
    public void backButton() {
        pause();
    }

    private void goToMenu() {
        LoadSave.save(game.getFileIO());
        gameMusic.stop();
        game.setScreen(new MainMenuScreen(game));
    }

    private void restartLevel(int level) {
        if (LoadSave.hearts < 1) {
            this.goToMenu();
        } else {
            game.setScreen(new GameScreen(game, level));
        }
    }

    public static Robot getRobot() {
        return robot;
    }

    public static LevelManager getLevelManager() {
        return levelManager;
    }

}