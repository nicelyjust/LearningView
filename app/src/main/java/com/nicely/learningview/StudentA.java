package com.nicely.learningview;

/*
 *  @项目名：  LearningView
 *  @包名：    com.nicely.learningview
 *  @创建者:   lz
 *  @创建时间:  2018/12/29 0:50
 *  @修改时间:  nicely 2018/12/29 0:50
 *  @描述：
 */
public class StudentA implements Observer {
    @Override
    public void update() {
        System.out.println("StudentA收到消息");
    }
}
