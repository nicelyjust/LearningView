package com.nicely.learningview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.FloatRange;
import android.support.annotation.IntRange;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.nicely.learningview.R;
import com.nicely.learningview.util.TDevice;


/*
 *  @项目名：  mio_sport
 *  @包名：    com.lifesense.mio.widget
 *  @文件名:   RingGapView
 *  @创建者:   lz
 *  @创建时间:  2018/12/11 18:12
 *  @描述：    Training Effect for this Workout 百分占比图
 */
public class RingGapView extends View {

    private static final String TAG = "RingGapView";
    private static final int DEFAULT_WIDTH = 400;
    private static final int DEFAULT_HEIGHT = 30;

    private int HEIGHT;
    private int WIDTH;

    private Paint mBgPaint;
    /**
     * 当前的进度值画笔
     */
    private Paint mPaint;
    private TextPaint mTxtPaint;
    /**
     * 中间文本的大小
     */
    private Rect textBound = new Rect();
    private int count = 5;
    private int gapAngle = 6;
    private RectF mRectF;
    private float mAngle;
    private float mCenterTxtSize;
    private int mCenterTxtColor;
    private float mCircleWidth;
    private CharSequence mCenterText;
    /**
     * 最大值
     */
    private int mMaxValue;
    /**
     * 当前值
     */
    private float mValue;

    public RingGapView(Context context) {
        this(context, null);
    }

    public RingGapView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public void setValue(@IntRange(from = 0) int maxValue, @FloatRange(from = 0) float value, @Nullable String centerText) {
        this.mMaxValue = maxValue;
        this.mValue = value;
        this.mCenterText = centerText;
        invalidate();
    }

    public RingGapView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.RingGapView);
        mCenterText = typedArray.getText(R.styleable.RingGapView_android_text);
        mCenterTxtSize = typedArray.getDimension(R.styleable.RingGapView_android_textSize, TDevice.dip2px(context, 15));
        mCenterTxtColor = typedArray.getColor(R.styleable.RingGapView_android_textColor, Color.WHITE);
        mCircleWidth = typedArray.getDimension(R.styleable.RingGapView_c_Width, TDevice.dip2px(context, 4));
        gapAngle = typedArray.getInt(R.styleable.RingGapView_gapAngle, 6);
        mMaxValue = typedArray.getInt(R.styleable.RingGapView_ring_maxValue, 5);
        mValue = typedArray.getFloat(R.styleable.RingGapView_ring_value, 5f);
        typedArray.recycle();
        initPaint();
    }

    private void initPaint() {
        mBgPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBgPaint.setColor(0xFF262930);
        mBgPaint.setStrokeWidth(mCircleWidth);
        mBgPaint.setStyle(Paint.Style.STROKE);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(0xFFB2B3B5);
        mPaint.setStrokeWidth(mCircleWidth);
        mPaint.setStyle(Paint.Style.STROKE);

        mAngle = (360f - count * gapAngle) / count;

        mTxtPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        mTxtPaint.setTextAlign(Paint.Align.CENTER);
        mTxtPaint.setColor(mCenterTxtColor);
        mTxtPaint.setTextSize(mCenterTxtSize);
        mTxtPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        this.WIDTH = w;
        this.HEIGHT = h;
        mRectF = new RectF(mCircleWidth, mCircleWidth, WIDTH - mCircleWidth, HEIGHT - mCircleWidth);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(0xFF1F2123);
        canvas.drawCircle(WIDTH / 2f, HEIGHT / 2f, WIDTH / 2f - mCircleWidth, mBgPaint);
        canvas.drawArc(mRectF, 90, mValue / mMaxValue * 360, false, mPaint);

        mBgPaint.setColor(0xFF1E2022);
        mBgPaint.setStrokeWidth(mCircleWidth +1);
        Log.d(TAG, "onDraw: " + mCenterText);
        for (int i = 0; i < count; i++) {
            float startAngle = 90 - gapAngle/2 +  gapAngle*i + mAngle*i;
            Log.d(TAG, "onDraw: startAngle == " + startAngle);
            Log.d(TAG, "onDraw: mAngle == " + mAngle);
            canvas.drawArc(mRectF, startAngle, gapAngle, false, mBgPaint);
        }
        mTxtPaint.getTextBounds(mCenterText.toString(), 0, mCenterText.length(), textBound);
        canvas.drawText("hello", WIDTH / 2f , HEIGHT / 2f + textBound.height() / 2f, mTxtPaint);
    }
}
