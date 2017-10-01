package com.bangtidygames.birdstrike;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.util.Log;

import com.bangtidygames.framework.Screen;
import com.bangtidygames.framework.implementation.AndroidGame;

/**
 * Created by Nick on 15/09/2017.
 */

public class SampleGame extends AndroidGame {

    public static String map1;
    public static String map2;
    public static String map3;
    public static String map4;
    public static String map5;
    public static String map6;
    public static String map7;
    public static String map8;
    public static String map9;
    public static String map10;
    //TODO add level maps

    boolean firstTimeCreate = true;

    @Override
    public Screen getInitScreen() {

        if (firstTimeCreate) {
            Assets.load(this);
            firstTimeCreate = false;
        }

        InputStream is = getResources().openRawResource(R.raw.map1);
        map1 = convertStreamToString(is);

        InputStream is2 = getResources().openRawResource(R.raw.map2);
        map2 = convertStreamToString(is2);

        InputStream is3 = getResources().openRawResource(R.raw.map3);
        map3 = convertStreamToString(is3);

        InputStream is4 = getResources().openRawResource(R.raw.map4);
        map4 = convertStreamToString(is4);

        InputStream is5 = getResources().openRawResource(R.raw.map5);
        map5 = convertStreamToString(is5);

        InputStream is6 = getResources().openRawResource(R.raw.map6);
        map6 = convertStreamToString(is6);

        InputStream is7 = getResources().openRawResource(R.raw.map7);
        map7 = convertStreamToString(is7);

        InputStream is8 = getResources().openRawResource(R.raw.map8);
        map8 = convertStreamToString(is8);

        InputStream is9 = getResources().openRawResource(R.raw.map9);
        map9 = convertStreamToString(is9);

        InputStream is10 = getResources().openRawResource(R.raw.map10);
        map10 = convertStreamToString(is10);

        //TODO add level maps

        return new SplashLoadingScreen(this);

    }

    @Override
    public void onBackPressed() {
        getCurrentScreen().backButton();
    }

    private static String convertStreamToString(InputStream is) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append((line + "\n"));
            }
        } catch (IOException e) {
            Log.w("LOG", e.getMessage());
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                Log.w("LOG", e.getMessage());
            }
        }
        return sb.toString();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}