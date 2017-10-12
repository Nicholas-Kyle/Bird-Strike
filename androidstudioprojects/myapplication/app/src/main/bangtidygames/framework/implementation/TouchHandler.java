package com.bangtidygames.framework.implementation;

import android.view.View.OnTouchListener;

import com.bangtidygames.framework.Input.TouchEvent;

import java.util.List;

/**
 * Created by Nick on 15/09/2017.
 */

public interface TouchHandler extends OnTouchListener {
    public boolean isTouchDown(int pointer);

    public int getTouchX(int pointer);

    public int getTouchY(int pointer);

    public List<TouchEvent> getTouchEvents();

    public void setScale(float x, float y);
}