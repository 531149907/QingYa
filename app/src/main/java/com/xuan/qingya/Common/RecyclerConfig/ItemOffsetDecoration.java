package com.xuan.qingya.Common.RecyclerConfig;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.xuan.qingya.Utils.DensityUtil;

/**
 * Created by zhouzhixuan on 2017/9/16.
 */

public class ItemOffsetDecoration extends RecyclerView.ItemDecoration {
    private int space;

    public ItemOffsetDecoration(int space) {
        this.space = DensityUtil.dip2px(space);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.bottom = space;
    }
}
