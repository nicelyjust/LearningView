package com.lifesense.mio.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;


/*
 *  @项目名：  mio_sport
 *  @包名：    com.lifesense.mio.widget
 *  @文件名:   RingGapView
 *  @创建者:   lz
 *  @创建时间:  2018/12/11 18:12
 *  @描述：    Training Effect for this Workout 百分占比图
 */
public class RingGapView extends View {

    private static final int DEFAULT_WIDTH = 400;
    private static final int DEFAULT_HEIGHT = 30;

    private int HEIGHT;
    private int WIDTH;

    private Paint mBgPaint;
    private Paint mPaint;
    private Paint mTxtPaint;
    private int count = 5;
    private int gapAngle = 6;
    private RectF mRectF;
    private float mAngle;

    public RingGapView(Context context) {
        this(context, null);
    }

    public RingGapView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RingGapView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint() {
        mBgPaint = new Paint();
        mAngle = (360f - count * gapAngle) / count;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        this.WIDTH = w;
        this.HEIGHT = h;
        mRectF = new RectF(0, 0, WIDTH, HEIGHT);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for (int i = 0; i < count; i++) {
            float startAngle = 90 + gapAngle / 2 + i * gapAngle ;
            canvas.drawArc(mRectF, startAngle, startAngle + mAngle, true, mBgPaint);
        }
    }
}
