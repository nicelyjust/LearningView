package com.nicely.learningview;

import java.util.ArrayList;
import java.util.List;

/*
 *  @项目名：  LearningView
 *  @包名：    com.nicely.learningview
 *  @创建者:   lz
 *  @创建时间:  2018/12/29 0:21
 *  @修改时间:  nicely 2018/12/29 0:21
 *  @描述：
 */
public class Teacher implements Observable {
    private List<Observer> mObservers = new ArrayList<>();
    @Override
    public void subscribe(Observer observer) {
        mObservers.add(observer);
    }

    @Override
    public void unSubscribe(Observer observer) {
        mObservers.remove(observer);
    }

    @Override
    public void update() {
        for (Observer observer : mObservers) {
            observer.update();
        }
    }

}
