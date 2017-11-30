package com.xuan.qingya.Common.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.bumptech.glide.Glide;

/**
 * Created by zhouzhixuan on 2017/10/1.
 */

public class BannerItemView extends android.support.v7.widget.AppCompatImageView {
    private Context context;
    private Paint mainTitlePaint;
    private Paint subTitlePaint;
    private Paint coverPaint;
    private float mainTitleFontHeight;
    private float subTitleFontHeight;

    private int colors[] = {0xffffff, 0x33000000};
    private float positions[] = {0, 1};
    private LinearGradient shader;
    private int resId;

    private int viewHeight, viewWidth;

    public BannerItemView(Context context) {
        this(context, null);
    }

    public BannerItemView(Context context, int resId) {
        this(context, null);
        this.resId = resId;
    }

    public BannerItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    private void init() {
        mainTitlePaint = new Paint();
        mainTitlePaint.setStyle(Paint.Style.FILL);
        mainTitlePaint.setAntiAlias(true);
        mainTitlePaint.setTextAlign(Paint.Align.LEFT);
        mainTitlePaint.setTextSize(24 * 3);
        mainTitlePaint.setColor(Color.parseColor("#ffffff"));

        subTitlePaint = new Paint();
        subTitlePaint.setStyle(Paint.Style.FILL);
        subTitlePaint.setAntiAlias(true);
        subTitlePaint.setTextAlign(Paint.Align.LEFT);
        subTitlePaint.setTextSize(14 * 3);
        subTitlePaint.setColor(Color.parseColor("#aaffffff"));

        coverPaint = new Paint();
        coverPaint.setAntiAlias(true);

        setScaleType(ScaleType.CENTER_CROP);

        Paint.FontMetrics fontMetrics = mainTitlePaint.getFontMetrics();
        mainTitleFontHeight = fontMetrics.descent - fontMetrics.ascent;

        fontMetrics = subTitlePaint.getFontMetrics();
        subTitleFontHeight = fontMetrics.descent - fontMetrics.ascent;
    }

    public BannerItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        viewHeight = getMeasuredHeight();
        viewWidth = getMeasuredWidth();
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        shader = new LinearGradient(0, 0, 0, viewHeight, colors, positions, Shader.TileMode.CLAMP);
        coverPaint.setShader(shader);

        Glide.with(this).load(resId).into(this);
        canvas.drawRect(0, 0, getWidth(), getHeight(), coverPaint);
        canvas.drawText("简约现代台灯", 16 * 3, 79 * 3 + mainTitleFontHeight, mainTitlePaint);
        canvas.drawText("青芽优选推荐 · 广告", 16 * 3, 122 * 3 + subTitleFontHeight, subTitlePaint);
    }
}
