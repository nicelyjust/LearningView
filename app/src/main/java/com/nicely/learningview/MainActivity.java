package com.nicely.learningview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.nicely.learningview.bean.PieVo;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<PieVo> mPieVos = new ArrayList<>(6);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
