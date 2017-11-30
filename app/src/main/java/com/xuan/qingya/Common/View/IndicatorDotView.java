package com.xuan.qingya.Common.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zhouzhixuan on 2017/10/1.
 */

public class IndicatorDotView extends View {
    public static final int DEFAULT_DOT_RADIUS = 9;
    public static final int DEFAULT_DOT_COLOR_SELECTED = Color.parseColor("#ffffff");
    public static final int DEFAULT_DOT_COLOR_UNSELECTED = Color.parseColor("#40ffffff");

    private Paint paint;
    private int dotRadius = DEFAULT_DOT_RADIUS;
    private int dotColor = DEFAULT_DOT_COLOR_SELECTED;

    public IndicatorDotView(Context context) {
        super(context);
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
    }

    public IndicatorDotView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public IndicatorDotView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        widthMeasureSpec = MeasureSpec.makeMeasureSpec(DEFAULT_DOT_RADIUS * 2, MeasureSpec.EXACTLY);
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(DEFAULT_DOT_RADIUS * 2, MeasureSpec.EXACTLY);
        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        paint.setColor(dotColor);
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, dotRadius, paint);
    }

    public int getDotRadius() {
        return dotRadius;
    }

    public void setDotRadius(int dotRadius) {
        this.dotRadius = dotRadius;
        invalidate();
    }

    public int getDotColor() {
        return dotColor;
    }

    public void setDotColor(int dotColor) {
        this.dotColor = dotColor;
        invalidate();
    }
}
