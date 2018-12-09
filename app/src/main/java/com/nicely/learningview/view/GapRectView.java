package com.nicely.learningview.view;
/*
 *  @项目名：  LearningView
 *  @包名：    com.nicely.learningview.view
 *  @创建者:   lz
 *  @创建时间:  2018/12/8 13:54
 *  @修改时间:  nicely 2018/12/8 13:54
 *  @描述：边界条件待考虑
 */

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.nicely.learningview.util.TDevice;

public class GapRectView extends View {
    private static final int DEFAULT_WIDTH = 400;
    private static final int DEFAULT_HEIGHT = 30;
    private int value = 52;
    /**
     * 分的段数
     */
    private static final int COUNT = 6;
    private int REAl_HEIGHT;
    private int REAl_WIDTH;
    private int gapWidth = 10;
    private int gapColor = 0xFF1B1C1C;
    /**
     * 矩形宽度
     */
    private int rectWidth;

    private Paint mRectPaint;
    private Paint mGapPaint;

    private Paint mSplitPaint;
    private int spiltColor = Color.RED;

    private Paint mTopTextPaint;
    /**
     * 上边文本的大小
     */
    private Rect topTextBound = new Rect();

    private Paint mTextPaint;
    private Paint mTrianglePaint;
    private Shader mShader;
    private float mLineX;
    private int mStartY;
    private Path mTriPath;

    public GapRectView(Context context) {
        this(context, null);
    }

    public GapRectView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GapRectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint() {

        gapWidth = TDevice.dip2px(getContext(), 3);

        mRectPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mRectPaint.setStyle(Paint.Style.FILL);

        mGapPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mGapPaint.setStyle(Paint.Style.FILL);
        mGapPaint.setColor(gapColor);
        mGapPaint.setStrokeWidth(gapWidth);

        mSplitPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mSplitPaint.setStyle(Paint.Style.FILL);
        mSplitPaint.setColor(spiltColor);
        mSplitPaint.setStrokeCap(Paint.Cap.ROUND);
        mSplitPaint.setStrokeWidth(TDevice.dip2px(getContext(), 2));

        mTopTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTopTextPaint.setTextSize(TDevice.dip2px(getContext(), 30));
        mTopTextPaint.setAntiAlias(true);
        mTopTextPaint.setColor(Color.BLACK);

        mTopTextPaint.getTextBounds(value + "", 0, (value + "").length(), topTextBound);

        mTriPath = new Path();
        mTrianglePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTrianglePaint.setStyle(Paint.Style.STROKE);
        mTrianglePaint.setStrokeWidth(TDevice.dip2px(getContext(), 5));

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int measureWidthSize = MeasureSpec.getSize(widthMeasureSpec);
        int measureHeightSize = MeasureSpec.getSize(heightMeasureSpec);
        int measureWidthMode = MeasureSpec.getMode(widthMeasureSpec);
        int measureHeightMode = MeasureSpec.getMode(heightMeasureSpec);
        if (measureWidthMode == MeasureSpec.AT_MOST
                && measureHeightMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        } else if (measureWidthMode == MeasureSpec.AT_MOST) {
            //height是精确的 需要算出文字 + 间距
            measureHeightSize += topTextBound.height() + 20;
            setMeasuredDimension(DEFAULT_WIDTH, measureHeightSize);
        } else if (measureHeightMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(measureWidthSize, DEFAULT_HEIGHT);
        } else if (measureWidthMode == MeasureSpec.EXACTLY
                && measureHeightMode == MeasureSpec.EXACTLY) {
            measureHeightSize += topTextBound.height() + 20;
            setMeasuredDimension(measureWidthSize, measureHeightSize);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        this.REAl_HEIGHT = h;
        this.REAl_WIDTH = w;
        if (mShader == null)
            mShader = new LinearGradient(0, 0, REAl_WIDTH, REAl_HEIGHT, 0xff2f4e7d, 0xff408cfb, Shader.TileMode.CLAMP);
        mRectPaint.setShader(mShader);
        LinearGradient linearGradient = new LinearGradient(0, 10, 0 , topTextBound.height(), 0xff2f4e7d, 0xff408cfb, Shader.TileMode.CLAMP);
        mTrianglePaint.setShader(linearGradient);
        rectWidth = (REAl_WIDTH - (COUNT - 1) * gapWidth) / COUNT;
        // TODO: 2018/12/8 存在画在黑色区域的情况
        mLineX = value / 100f * REAl_WIDTH;
        mStartY = 15 + topTextBound.height();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.d("lz", "onDraw: ");
        /*
         * 画整体矩形 ,上下偏移 5
         */
        canvas.drawRect(0, mStartY, REAl_WIDTH, REAl_HEIGHT - 5, mRectPaint);
        /*
         * 画 gap
         */
        int size = COUNT - 1;
        for (int i = 1; i <= size; i++) {
            canvas.drawLine(rectWidth * i + gapWidth * (i - 1), mStartY, rectWidth * i + gapWidth * (i - 1), REAl_HEIGHT - 5, mGapPaint);
        }
        /*
         * 画当前刻度
         */
        canvas.drawLine(mLineX, mStartY, mLineX, REAl_HEIGHT, mSplitPaint);
        canvas.drawText(String.valueOf(value), mLineX - topTextBound.width() / 2, topTextBound.height(), mTopTextPaint);
        float triX = mLineX + topTextBound.width() / 2 + 25;
        mTriPath.moveTo(triX, topTextBound.height());
        mTriPath.lineTo(triX + TDevice.dip2px(getContext(), 11),20);
        mTriPath.lineTo(triX + TDevice.dip2px(getContext(), 22) , topTextBound.height());
        canvas.drawPath(mTriPath ,mTrianglePaint);
    }
}
