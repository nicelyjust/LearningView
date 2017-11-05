package com.nicely.learningview;
/*
 *  @项目名：  LearningView
 *  @包名：    com.nicely.learningview
 *  @创建者:   lz
 *  @创建时间:  2017/11/5 14:35
 *  @修改时间:  nicely 2017/11/5 14:35
 *  @描述：    TODO
 */

import android.app.Application;
import android.content.Context;
import android.os.Handler;

public class AppManager extends Application{

    private static Context sAppContext;
    private static Handler sHandler;

    @Override
    public void onCreate() {
        super.onCreate();
        sAppContext = getApplicationContext();
        sHandler = new Handler();
    }

    public static Context getsAppContext() {
        return sAppContext;
    }

    public static Handler getsHandler() {
        return sHandler;
    }
}
