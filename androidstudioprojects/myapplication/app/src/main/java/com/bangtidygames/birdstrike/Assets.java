package com.bangtidygames.birdstrike;

import android.provider.ContactsContract;

import com.bangtidygames.framework.Image;
import com.bangtidygames.framework.Music;
import com.bangtidygames.framework.Sound;

/**
 * Created by Nick on 15/09/2017.
 */

public class Assets {

    public static Image splash, menu_desert, menu_forest, menu_graveyard, menu_snow;
    public static Image level_1, level_2, level_3, level_4, level_5, level_6;
    public static Image setting, pause, menuButton, restartButton, resumeButton;
    public static Image one_star, two_stars, three_stars;
    public static Image bluebiplane, explosion;
    public static Image brown_1, brown_2, brown_3, brown_4, brown_5, brown_6, brown_7, brown_8, brown_9,
            grey_1, grey_2, grey_3, grey_4, grey_5, blank;
    public static Image BG_forest_1280;
    public static Image UI;
    public static Image hearts_1, hearts_2, hearts_3, hearts_4, hearts_5, hearts_6, hearts_7, hearts_8, hearts_9, hearts_10,
            hearts_11, hearts_12, hearts_13, hearts_14, hearts_15, hearts_16, hearts_17, hearts_18, hearts_19, hearts_20;
    public static Image birds_0, birds_1, birds_2, birds_3, birds_4, birds_5;
    public static Image checkpoints_1, checkpoints_2, checkpoints_3, checkpoints_4, checkpoints_5, checkpoints_6,
            checkpoints_7, checkpoints_8, checkpoints_9, checkpoints_10, checkpoints_11, checkpoints_12,
            checkpoints_13, checkpoints_14, checkpoints_15, checkpoints_16, checkpoints_17, checkpoints_18,
            checkpoints_19, checkpoints_20, checkpoints_21, checkpoints_22, checkpoints_23, checkpoints_24,
            checkpoints_25;
    public static Image blueFinishPost;
    public static Image grass_1, grass_2, grass_3, grass_4, grass_5, grass_6, grass_7, grass_8, grass_9, grass_10, grass_11,
            grass_12, grass_13, grass_14, grass_15, grass_16, grass_17, grass_18, grass_19, grass_20, grass_21, grass_22,
            grass_23, grass_24, grass_25, grass_26, grass_27;
    public static Sound crash, birdSound;
    public static Music theme;

    public static void load(SampleGame sampleGame) {
        // TODO Auto-generated method stub
        theme = sampleGame.getAudio().createMusic("menutheme.mp3");
        theme.setLooping(true);
        theme.setVolume(0.85f);
        theme.play();
    }

}