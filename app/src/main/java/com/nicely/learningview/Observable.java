package com.nicely.learningview;

/*
 *  @项目名：  LearningView
 *  @包名：    com.nicely.learningview
 *  @创建者:   lz
 *  @创建时间:  2018/12/29 0:02
 *  @修改时间:  nicely 2018/12/29 0:02
 *  @描述：    可观察对象 被观察者想象成老师
 */
public interface Observable {
    void subscribe(Observer observer);
    void unSubscribe(Observer observer);
    void update();
}
