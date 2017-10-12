package com.bangtidygames.framework;

import com.bangtidygames.framework.Graphics.ImageFormat;

/**
 * Created by Nick on 15/09/2017.
 */

public interface Image {
    public int getWidth();
    public int getHeight();
    public ImageFormat getFormat();
    public void dispose();
}