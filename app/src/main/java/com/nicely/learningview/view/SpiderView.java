package com.nicely.learningview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.nicely.learningview.util.TDevice;



/*
 *  @项目名：  Demo
 *  @包名：    com.eebbk.nicely.demo.view
 *  @文件名:   SpiderView
 *  @创建者:   Administrator
 *  @创建时间:  2017/11/3 17:27
 *  @描述：
 */

public class SpiderView extends View {

    private static final int count = 6;
    private Context mContext;
    private int     mCenterX;
    private int     mCenterY;
    private float    angle    = (float) (Math.PI * 2 / count);
    private float    radius   = 200;                   //网格最大半径
    private String[] titles   = {"a", "b", "c", "d", "e", "f"};
    private Float[]  data     = {100f, 60f, 60f, 60f, 100f, 50f, 10f, 20f}; //各维度分值
    private float    maxValue = 100;             //数据最大值
    private Paint mainPaint;                //雷达区画笔
    private Paint valuePaint;               //数据区画笔
    private Paint mPointPaint;
    private Paint mTxtPaint;                //文本画笔
    private Path  mLPath;
    private Path  mLinePath;

    public SpiderView(Context context) {
        this(context , null);
    }

    public SpiderView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs , 0);
    }

    public SpiderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
    }
     private void init() {
         mLPath = new Path();
         mainPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
         mainPaint.setStyle(Paint.Style.STROKE);
         mainPaint.setColor(Color.BLACK);

         mLinePath = new Path();

         valuePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
         valuePaint.setAlpha(255);
         valuePaint.setColor(Color.BLUE);
         valuePaint.setStyle(Paint.Style.STROKE);

         mPointPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
         mPointPaint.setColor(Color.BLUE);
         mPointPaint.setStyle(Paint.Style.FILL);

         mTxtPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
         Rect rect = new Rect();
         mTxtPaint.getTextBounds("1" , 0 ,"1".length() , rect);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mCenterX = w/2 ;
        mCenterY = h/2;
        super.onSizeChanged(w, h, oldw, oldh);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawPolygon(canvas);
        drawLines(canvas);
        drawPoints(canvas);
    }

    private void drawPolygon(Canvas canvas) {// 求出最小的边长
        float r = radius / (count - 1);
        for (int i = 1; i < count; i++) {
            float curR = r*i;
            mLPath.reset();
            for (int j = 0; j < count; j++) {
                if (j == 0) {
                    mLPath.moveTo(mCenterX + curR, mCenterY);
                } else {
                    //根据半径，计算出蜘蛛丝上每个点的坐标
                    float x = (float) (mCenterX + curR * Math.cos(angle * j));
                    float y = (float) (mCenterY + curR * Math.sin(angle * j));
                    mLPath.lineTo(x, y);
                }
            }
            mLPath.close();//闭合路径
            canvas.drawPath(mLPath, mainPaint);

        }
    }

    private void drawLines(Canvas canvas) {
        for (int i = 0; i < count; i++) {
            mLinePath.reset();
            mLinePath.moveTo(mCenterX , mCenterY);
            float x = (float) (mCenterX + radius * Math.cos(angle * i));
            float y = (float) (mCenterY + radius * Math.sin(angle * i));
            mLinePath.lineTo(x, y);
            canvas.drawPath(mLinePath , mainPaint);
        }
    }

    private void drawPoints(Canvas canvas) {
        mLinePath.reset();
        for (int i = 0; i < count; i++) {
            float percent = data[i] / maxValue;
            float x = (float) (mCenterX + radius * Math.cos(angle * i) * percent);
            float y = (float) (mCenterY + radius * Math.sin(angle * i) * percent);
            if (i == 0) {
                mLinePath.moveTo(x , mCenterY );
            }else {
                mLinePath.lineTo(x , y );
            }
            canvas.drawCircle(x , y  , TDevice.dip2px(mContext , 3), mPointPaint);

        }
        valuePaint.setAlpha(255);
        valuePaint.setColor(Color.BLUE);
        valuePaint.setStyle(Paint.Style.STROKE);

        canvas.drawPath(mLinePath ,valuePaint);
        valuePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        valuePaint.setColor(Color.BLUE);
        valuePaint.setAlpha(128);
        canvas.drawPath(mLinePath ,valuePaint);
    }
}
