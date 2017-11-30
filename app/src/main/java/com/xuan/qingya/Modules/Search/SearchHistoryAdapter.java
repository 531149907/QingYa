package com.xuan.qingya.Modules.Search;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageButton;
import android.widget.TextView;

import com.xuan.qingya.Common.RecyclerConfig.ItemFadeAndDropAnimation;
import com.xuan.qingya.Models.entity.SearchHistory;
import com.xuan.qingya.R;
import com.xuan.qingya.Utils.DensityUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhouzhixuan on 2017/9/12.
 */

public class SearchHistoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements ItemFadeAndDropAnimation {
    private List<SearchHistory> data;
    private SearchHistoryAdapter.OnItemClickListener onItemClickListener;
    private Context context;
    private boolean enableAnimation;

    @Override
    public void itemAnimationStart(final View itemView, final int position) {
        itemView.post(new Runnable() {
            @Override
            public void run() {
                if (itemView.getHeight() * (position + 1) + DensityUtil.dip2px(80) > DensityUtil.getScreenHeight()) {
                    enableAnimation = false;
                } else if (enableAnimation) {
                    itemView.setAlpha(0);
                    ObjectAnimator animator = ObjectAnimator.ofFloat(itemView, "translationY", -(80 + position * 20), 0);
                    animator.setDuration(250);

                    ObjectAnimator animator2 = ObjectAnimator.ofFloat(itemView, "alpha", 0, 1);
                    animator2.setDuration(250);

                    AnimatorSet animatorSet = new AnimatorSet();
                    animatorSet.playTogether(animator, animator2);
                    animatorSet.setStartDelay(position * 30);
                    animatorSet.setInterpolator(new DecelerateInterpolator());
                    animatorSet.start();
                }
            }
        });

    }

    interface OnItemClickListener {
        void onItemClick(SearchHistory bean);

        void onFillContentClick(String content);
    }

    public void addOnClickListener(SearchHistoryAdapter.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public SearchHistoryAdapter(Context context) {
        this.context = context;
        this.data = new ArrayList<>();
        enableAnimation = true;
    }

    public void setData(List<SearchHistory> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item_search, parent, false);
        return new MainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final MainViewHolder viewHolder = (MainViewHolder) holder;
        final SearchHistory bean = data.get(position);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(bean);
                }
            }
        });

        viewHolder.fillTopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickListener != null) {
                    onItemClickListener.onFillContentClick(bean.getContent());
                }
            }
        });

        viewHolder.content.setText(bean.getContent());

        if (enableAnimation) {
            itemAnimationStart(viewHolder.itemView, position);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MainViewHolder extends RecyclerView.ViewHolder {

        ImageButton historyIcon;
        ImageButton fillTopButton;
        TextView content;

        public MainViewHolder(View itemView) {
            super(itemView);

            historyIcon = itemView.findViewById(R.id.history_icon);
            fillTopButton = itemView.findViewById(R.id.fill_icon);
            content = itemView.findViewById(R.id.content);
        }
    }
}
