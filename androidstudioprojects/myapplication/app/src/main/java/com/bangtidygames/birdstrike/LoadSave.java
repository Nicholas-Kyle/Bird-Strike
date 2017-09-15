package com.bangtidygames.birdstrike;

import com.bangtidygames.framework.FileIO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Created by Nick on 05/10/2016.
 */

public class LoadSave {
    public static boolean soundEnabled = true;
    public static int[] highscores = new int[]{100, 80, 50, 30, 10};
    public static int hearts = 20;

    public static void load(FileIO files) {
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(files.readFile(".robot")));
            soundEnabled = Boolean.parseBoolean(in.readLine());
            for (int i = 0; i < 5; i++) {
                highscores[i] = Integer.parseInt(in.readLine());
            }
        } catch (IOException e) {
            // TODO
        } catch (NumberFormatException e) {
            // TODO
        } finally {
            try {
                if (in != null)
                    in.close();
            } catch (IOException e) {
                // TODO
            }
        }

    }

    public static void loadHearts(FileIO files) {

        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(files.readFile("hearts.txt")));
            if (in == null){

            }
            hearts = Integer.parseInt(in.readLine());

        } catch (IOException e) {
            // TODO
        } catch (NumberFormatException e) {
            // TODO
        } finally {
            try {
                if (in != null)
                    in.close();
            } catch (IOException e) {
                // TODO
            }
        }

    }

    public static void save(FileIO files) {
        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new OutputStreamWriter(files.writeFile(".robot")));
            out.write(Boolean.toString(soundEnabled));
            for (int i = 0; i < 5; i++) {
                out.write(Integer.toString(highscores[i]));
            }

        } catch (IOException e) {
        } finally {
            try {
                if (out != null)
                    out.close();
            } catch (IOException e) {
            }
        }
    }

    public static void saveHearts(FileIO files) {

        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new OutputStreamWriter(files.writeFile("hearts.txt")));
            out.write(Integer.toString(hearts));

        } catch (IOException e) {
        } finally {
            try {
                if (out != null)
                    out.close();
            } catch (IOException e) {
            }
        }
    }

    public static int getHearts() {
        return hearts;
    }

    public static void setHearts(int hearts) {
        LoadSave.hearts = hearts;
    }
}