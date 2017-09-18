package com.bangtidygames.birdstrike;

import com.bangtidygames.framework.Game;
import com.bangtidygames.framework.Graphics;
import com.bangtidygames.framework.Graphics.ImageFormat;
import com.bangtidygames.framework.Screen;

/**
 * Created by Nick on 15/09/2017.
 */

public class LoadingScreen extends Screen {
    public LoadingScreen(Game game) {

        super(game);
    }

    @Override
    public void update(float deltaTime) {
        Graphics g = game.getGraphics();
        Assets.menu_desert = g.newImage("MenuMap/desert.png", ImageFormat.RGB565);
        Assets.menu_forest = g.newImage("MenuMap/forest.png", ImageFormat.RGB565);
        Assets.menu_graveyard = g.newImage("MenuMap/graveyard.png", ImageFormat.RGB565);
        Assets.menu_snow = g.newImage("MenuMap/snow.png", ImageFormat.RGB565);

        Assets.level_1 = g.newImage("MenuLevels/lvl1.png", ImageFormat.ARGB4444);

        Assets.BG_forest_1280 = g.newImage("grassTileset/BG_forest_1280.png", ImageFormat.RGB565);

        Assets.UI = g.newImage("RunningUI.png", ImageFormat.RGB565);

        Assets.hearts_1 = g.newImage("HUD/heart_1.png", ImageFormat.ARGB4444);
        Assets.hearts_2 = g.newImage("HUD/heart_2.png", ImageFormat.ARGB4444);
        Assets.hearts_3 = g.newImage("HUD/heart_3.png", ImageFormat.ARGB4444);
        Assets.hearts_4 = g.newImage("HUD/heart_4.png", ImageFormat.ARGB4444);
        Assets.hearts_5 = g.newImage("HUD/heart_5.png", ImageFormat.ARGB4444);
        Assets.hearts_6 = g.newImage("HUD/heart_6.png", ImageFormat.ARGB4444);
        Assets.hearts_7 = g.newImage("HUD/heart_7.png", ImageFormat.ARGB4444);
        Assets.hearts_8 = g.newImage("HUD/heart_8.png", ImageFormat.ARGB4444);
        Assets.hearts_9 = g.newImage("HUD/heart_9.png", ImageFormat.ARGB4444);
        Assets.hearts_10 = g.newImage("HUD/heart_10.png", ImageFormat.ARGB4444);
        Assets.hearts_11 = g.newImage("HUD/heart_11.png", ImageFormat.ARGB4444);
        Assets.hearts_12 = g.newImage("HUD/heart_12.png", ImageFormat.ARGB4444);
        Assets.hearts_13 = g.newImage("HUD/heart_13.png", ImageFormat.ARGB4444);
        Assets.hearts_14 = g.newImage("HUD/heart_14.png", ImageFormat.ARGB4444);
        Assets.hearts_15 = g.newImage("HUD/heart_15.png", ImageFormat.ARGB4444);
        Assets.hearts_16 = g.newImage("HUD/heart_16.png", ImageFormat.ARGB4444);
        Assets.hearts_17 = g.newImage("HUD/heart_17.png", ImageFormat.ARGB4444);
        Assets.hearts_18 = g.newImage("HUD/heart_18.png", ImageFormat.ARGB4444);
        Assets.hearts_19 = g.newImage("HUD/heart_19.png", ImageFormat.ARGB4444);
        Assets.hearts_20 = g.newImage("HUD/heart_20.png", ImageFormat.ARGB4444);

        Assets.birds_0 = g.newImage("HUD/bird_0.png", ImageFormat.ARGB4444);
        Assets.birds_1 = g.newImage("HUD/bird_1.png", ImageFormat.ARGB4444);
        Assets.birds_2 = g.newImage("HUD/bird_2.png", ImageFormat.ARGB4444);
        Assets.birds_3 = g.newImage("HUD/bird_3.png", ImageFormat.ARGB4444);
        Assets.birds_4 = g.newImage("HUD/bird_4.png", ImageFormat.ARGB4444);
        Assets.birds_5 = g.newImage("HUD/bird_5.png", ImageFormat.ARGB4444);

        Assets.lap_1_1 = g.newImage("HUD/lap_1_1.png", ImageFormat.ARGB4444);
        Assets.lap_1_2 = g.newImage("HUD/lap_1_2.png", ImageFormat.ARGB4444);
        Assets.lap_2_2 = g.newImage("HUD/lap_2_2.png", ImageFormat.ARGB4444);
        Assets.lap_1_3 = g.newImage("HUD/lap_1_3.png", ImageFormat.ARGB4444);
        Assets.lap_2_3 = g.newImage("HUD/lap_2_3.png", ImageFormat.ARGB4444);
        Assets.lap_3_3 = g.newImage("HUD/lap_3_3.png", ImageFormat.ARGB4444);
        Assets.lap_1_4 = g.newImage("HUD/lap_1_4.png", ImageFormat.ARGB4444);
        Assets.lap_2_4 = g.newImage("HUD/lap_2_4.png", ImageFormat.ARGB4444);
        Assets.lap_3_4 = g.newImage("HUD/lap_3_4.png", ImageFormat.ARGB4444);
        Assets.lap_4_4 = g.newImage("HUD/lap_4_4.png", ImageFormat.ARGB4444);
        Assets.lap_1_5 = g.newImage("HUD/lap_1_5.png", ImageFormat.ARGB4444);
        Assets.lap_2_5 = g.newImage("HUD/lap_2_5.png", ImageFormat.ARGB4444);
        Assets.lap_3_5 = g.newImage("HUD/lap_3_5.png", ImageFormat.ARGB4444);
        Assets.lap_4_5 = g.newImage("HUD/lap_4_5.png", ImageFormat.ARGB4444);
        Assets.lap_5_5 = g.newImage("HUD/lap_5_5.png", ImageFormat.ARGB4444);

        Assets.bluebiplane = g.newImage("bluebiplane.png", ImageFormat.ARGB4444);
        Assets.explosion = g.newImage("explosion.png", ImageFormat.ARGB4444);

        Assets.brown_1 = g.newImage("birds/brown_1.png", ImageFormat.ARGB4444);
        Assets.brown_2 = g.newImage("birds/brown_2.png", ImageFormat.ARGB4444);
        Assets.brown_3 = g.newImage("birds/brown_3.png", ImageFormat.ARGB4444);
        Assets.brown_4 = g.newImage("birds/brown_4.png", ImageFormat.ARGB4444);
        Assets.brown_5 = g.newImage("birds/brown_5.png", ImageFormat.ARGB4444);
        Assets.brown_6 = g.newImage("birds/brown_6.png", ImageFormat.ARGB4444);
        Assets.brown_7 = g.newImage("birds/brown_7.png", ImageFormat.ARGB4444);
        Assets.brown_8 = g.newImage("birds/brown_8.png", ImageFormat.ARGB4444);
        Assets.brown_9 = g.newImage("birds/brown_9.png", ImageFormat.ARGB4444);
        Assets.grey_1 = g.newImage("birds/grey_1.png", ImageFormat.ARGB4444);
        Assets.grey_2 = g.newImage("birds/grey_2.png", ImageFormat.ARGB4444);
        Assets.grey_3 = g.newImage("birds/grey_3.png", ImageFormat.ARGB4444);
        Assets.grey_4 = g.newImage("birds/grey_4.png", ImageFormat.ARGB4444);
        Assets.grey_5 = g.newImage("birds/grey_5.png", ImageFormat.ARGB4444);
        Assets.blank = g.newImage("birds/blank.png", ImageFormat.ARGB4444);

        Assets.blueFinishPost = g.newImage("blueFinishPost.png", ImageFormat.ARGB4444);

        Assets.grass_1 = g.newImage("grassTileset/1.png", ImageFormat.ARGB4444);
        Assets.grass_2 = g.newImage("grassTileset/2.png", ImageFormat.ARGB4444);
        Assets.grass_3 = g.newImage("grassTileset/3.png", ImageFormat.ARGB4444);
        Assets.grass_4 = g.newImage("grassTileset/4.png", ImageFormat.ARGB4444);
        Assets.grass_5 = g.newImage("grassTileset/5.png", ImageFormat.ARGB4444);
        Assets.grass_6 = g.newImage("grassTileset/6.png", ImageFormat.ARGB4444);
        Assets.grass_7 = g.newImage("grassTileset/7.png", ImageFormat.ARGB4444);
        Assets.grass_8 = g.newImage("grassTileset/8.png", ImageFormat.ARGB4444);
        Assets.grass_9 = g.newImage("grassTileset/9.png", ImageFormat.ARGB4444);
        Assets.grass_10 = g.newImage("grassTileset/10.png", ImageFormat.ARGB4444);
        Assets.grass_11 = g.newImage("grassTileset/11.png", ImageFormat.ARGB4444);
        Assets.grass_12 = g.newImage("grassTileset/12.png", ImageFormat.ARGB4444);
        Assets.grass_13 = g.newImage("grassTileset/13.png", ImageFormat.ARGB4444);
        Assets.grass_14 = g.newImage("grassTileset/14.png", ImageFormat.ARGB4444);
        Assets.grass_15 = g.newImage("grassTileset/15.png", ImageFormat.ARGB4444);
        Assets.grass_16 = g.newImage("grassTileset/16.png", ImageFormat.ARGB4444);
        Assets.grass_17 = g.newImage("grassTileset/17.png", ImageFormat.ARGB4444);
        Assets.grass_18 = g.newImage("grassTileset/18.png", ImageFormat.ARGB4444);
        Assets.grass_19 = g.newImage("grassTileset/19.png", ImageFormat.ARGB4444);
        Assets.grass_20 = g.newImage("grassTileset/20.png", ImageFormat.ARGB4444);
        Assets.grass_21 = g.newImage("grassTileset/21.png", ImageFormat.ARGB4444);
        Assets.grass_22 = g.newImage("grassTileset/22.png", ImageFormat.ARGB4444);
        Assets.grass_23 = g.newImage("grassTileset/23.png", ImageFormat.ARGB4444);
        Assets.grass_24 = g.newImage("grassTileset/24.png", ImageFormat.ARGB4444);
        Assets.grass_25 = g.newImage("grassTileset/25.png", ImageFormat.ARGB4444);
        Assets.grass_26 = g.newImage("grassTileset/26.png", ImageFormat.ARGB4444);
        Assets.grass_27 = g.newImage("grassTileset/27.png", ImageFormat.ARGB4444);
        LoadSave.load(game.getFileIO());
        LoadSave.loadHearts(game.getFileIO());
        //TODO remove next 2 lines, replace with check time elapsed in order to add hearts
        if (LoadSave.hearts<1){
            LoadSave.setHearts(20);
        }


        //This is how you would load a sound if you had one.
        //Assets.click = game.getAudio().createSound("explode.ogg");


        game.setScreen(new MainMenuScreen(game));

    }

    @Override
    public void paint(float deltaTime) {
        Graphics g = game.getGraphics();
        g.drawImage(Assets.splash, 0, 0);
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

    }
}