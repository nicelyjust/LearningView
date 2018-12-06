package com.nicely.learningview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.nicely.learningview.bean.PieVo;
import com.nicely.learningview.view.PieView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<PieVo> mPieVos = new ArrayList<>(6);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PieView view = (PieView) findViewById(R.id.pv_view);
        PieVo pieVo = new PieVo(3,0.3f,"java");
        pieVo.setColor(0xffff0000);
        PieVo pieVo1 = new PieVo(4,0.4f,"js");
        pieVo1.setColor(0xff00ff00);
        PieVo pieVo2 = new PieVo(3,0.3f,"c");
        pieVo2.setColor(0xff0000ff);
        mPieVos.add(pieVo);
        mPieVos.add(pieVo1);
        mPieVos.add(pieVo2);
        view.setData( new int[]{3,4,3}, new String[]{"JAVA", "JS","C"});
    }
}
