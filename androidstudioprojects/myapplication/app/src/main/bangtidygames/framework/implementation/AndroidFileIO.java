package com.bangtidygames.framework.implementation;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.os.Environment;
import android.preference.PreferenceManager;

import com.bangtidygames.framework.FileIO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Nick on 15/09/2017.
 */

public class AndroidFileIO implements FileIO {
    Context context;
    AssetManager assets;
    String externalStoragePath;

    public AndroidFileIO(Context context) {
        this.context = context;
        this.assets = context.getAssets();
        this.externalStoragePath = Environment.getExternalStorageDirectory()
                .getAbsolutePath() + File.separator;

    }

    @Override
    public InputStream readAsset(String file) throws IOException {
        return assets.open(file);
    }

    @Override
    public InputStream readFile(String file) throws IOException {
        FileInputStream fis = context.openFileInput(file);
        return fis;
    }

    @Override
    public OutputStream writeFile(String file) throws IOException {
        FileOutputStream fos = context.openFileOutput(file, context.MODE_PRIVATE);
        return fos;
    }

    public SharedPreferences getSharedPref() {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }
}