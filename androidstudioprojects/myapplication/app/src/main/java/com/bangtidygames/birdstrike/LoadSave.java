package com.bangtidygames.birdstrike;

import com.bangtidygames.framework.FileIO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * Created by Nick on 05/10/2016.
 */

public class LoadSave {
    public static boolean soundEnabled = true;
    public static int level = 0;
    public static int[] stars = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};// TODO update when more levels are added
    public static int hearts = 20;

    public static void load(FileIO files) {
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(files.readFile("BirdStrike.txt")));
            soundEnabled = Boolean.parseBoolean(in.readLine());
            level = Integer.parseInt(in.readLine());
            hearts = Integer.parseInt(in.readLine());
            String savedString = in.readLine();
            StringTokenizer st = new StringTokenizer(savedString, ",");
            int i = 0;
            while (st.hasMoreElements()) {
                stars[i] = Integer.parseInt(st.nextToken());
                i++;
            }
        } catch (IOException e) {
            System.out.println("Cannot read file");
        } catch (NumberFormatException e) {
            throw new RuntimeException("Number format exception");
        } finally {
            try {
                if (in != null)
                    in.close();
            } catch (IOException e) {
                throw new RuntimeException("Failed to close BufferedReader");
            }
        }
    }

    public static void save(FileIO files) {
        BufferedWriter out = null;
        try {
            StringBuilder str = new StringBuilder();
            for (int i = 0; i < stars.length; i++) {
                str.append(stars[i]).append(",");
            }
            out = new BufferedWriter(new OutputStreamWriter(files.writeFile("BirdStrike.txt")));
            out.write(Boolean.toString(soundEnabled));
            out.newLine();
            out.write(Integer.toString(level));
            out.newLine();
            out.write(Integer.toString(hearts));
            out.newLine();
            out.write(str.toString());

        } catch (IOException e) {
            throw new RuntimeException("Couldn't load saved data");
        } finally {
            try {
                if (out != null)
                    out.close();
            } catch (IOException e) {
                throw new RuntimeException("Failed to close BufferedWriter");
            }
        }
    }

    public static void addStars(int level, int numStars){
        stars[(level-1)] = numStars;
    }

    public static int getStars(int level){
        return stars[(level-1)];
    }

}