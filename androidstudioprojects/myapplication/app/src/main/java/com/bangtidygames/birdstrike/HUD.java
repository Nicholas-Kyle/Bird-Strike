package com.bangtidygames.birdstrike;

import com.bangtidygames.framework.Image;

/**
 * Created by Nick on 04/10/2016.
 */

public class HUD {

    public static Image getHearts(int anInt){
        Image h = null;
        if (anInt == 1){
            h = Assets.hearts_1;
        } else if (anInt == 2){
            h = Assets.hearts_2;
        } else if (anInt == 3){
            h = Assets.hearts_3;
        } else if (anInt == 4){
            h = Assets.hearts_4;
        } else if (anInt == 5){
            h = Assets.hearts_5;
        } else if (anInt == 6){
            h = Assets.hearts_6;
        } else if (anInt == 7){
            h = Assets.hearts_7;
        } else if (anInt == 8){
            h = Assets.hearts_8;
        } else if (anInt == 9){
            h = Assets.hearts_9;
        } else if (anInt == 10){
            h = Assets.hearts_10;
        } else if (anInt == 11){
            h = Assets.hearts_11;
        } else if (anInt == 12){
            h = Assets.hearts_12;
        } else if (anInt == 13){
            h = Assets.hearts_13;
        } else if (anInt == 14){
            h = Assets.hearts_14;
        } else if (anInt == 15){
            h = Assets.hearts_15;
        } else if (anInt == 16){
            h = Assets.hearts_16;
        } else if (anInt == 17){
            h = Assets.hearts_17;
        } else if (anInt == 18){
            h = Assets.hearts_18;
        } else if (anInt == 19){
            h = Assets.hearts_19;
        } else if (anInt == 20){
            h = Assets.hearts_20;
        }
        return h;
    }

    public static Image getBirds(int anInt) {
        Image b = null;
        if (anInt == 0) {
            b = Assets.birds_0;
        } else if (anInt == 1) {
            b = Assets.birds_1;
        } else if (anInt == 2) {
            b = Assets.birds_2;
        } else if (anInt == 3) {
            b = Assets.birds_3;
        } else if (anInt == 4) {
            b = Assets.birds_4;
        } else if (anInt == 5) {
            b = Assets.birds_5;
        }
        return b;
    }

    public static Image getLaps(int anInt){
        Image l = null;
        if (anInt == 1) {
            l = Assets.lap_1_1;
        } else if (anInt == 2) {
            l = Assets.lap_1_2;
        } else if (anInt == 12) {
            l = Assets.lap_2_2;
        } else if (anInt == 3) {
            l = Assets.lap_1_3;
        } else if (anInt == 13) {
            l = Assets.lap_2_3;
        } else if (anInt == 23) {
            l = Assets.lap_3_3;
        } else if (anInt == 4) {
            l = Assets.lap_1_4;
        } else if (anInt == 14) {
            l = Assets.lap_2_4;
        } else if (anInt == 24) {
            l = Assets.lap_3_4;
        } else if (anInt == 34) {
            l = Assets.lap_4_4;
        } else if (anInt == 5) {
            l = Assets.lap_1_5;
        } else if (anInt == 15) {
            l = Assets.lap_2_5;
        } else if (anInt == 25) {
            l = Assets.lap_3_5;
        } else if (anInt == 35) {
            l = Assets.lap_4_5;
        } else if (anInt == 45) {
            l = Assets.lap_5_5;
        }
        return l;
    }
}
