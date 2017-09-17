package com.xuan.qingya.Modules.Main.Discover;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xuan.qingya.Common.Constant;
import com.xuan.qingya.Models.Entity.TopicBean;
import com.xuan.qingya.Modules.Discover.Topic.DiscoverTopicActivity;
import com.xuan.qingya.Modules.Main.MainContract;
import com.xuan.qingya.R;

import java.util.List;

/**
 * Created by zhouzhixuan on 2017/8/27.
 */

public class DiscoverRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int FOOTER_VIEW = -1;
    private MainContract.DiscoverPresenter presenter;
    private Context context;
    private List<TopicBean> data;
    private DiscoverRecyclerViewAdapter.OnItemClickListener onItemClickListener;

    interface OnItemClickListener {
        void onClick(View view, TopicBean bean, int position);
    }

    public void addOnClickListener(DiscoverRecyclerViewAdapter.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public DiscoverRecyclerViewAdapter(Context context, List<TopicBean> data, MainContract.DiscoverPresenter presenter) {
        this.context = context;
        this.data = data;
        this.presenter = presenter;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        switch (viewType) {
            case Constant.CONTENT_DISCOVER_TOPIC:
                view = layoutInflater.inflate(R.layout.layout_item_discover_topic, parent, false);
                break;
            case FOOTER_VIEW:
                view = layoutInflater.inflate(R.layout.layout_item_footer, parent, false);
                return new DiscoverRecyclerViewAdapter.FooterViewHolder(view);
        }

        return new DiscoverRecyclerViewAdapter.ContentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof DiscoverRecyclerViewAdapter.FooterViewHolder) {
            return;
        }

        final DiscoverRecyclerViewAdapter.ContentViewHolder contentViewHolder = (DiscoverRecyclerViewAdapter.ContentViewHolder) holder;
        final TopicBean bean = data.get(position);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickListener != null) {
                    onItemClickListener.onClick(view, bean, position);

                    Intent intent = new Intent(context, DiscoverTopicActivity.class);
                    context.startActivity(intent);

                }
            }
        });

        contentViewHolder.title.setText(bean.getTitle());
        contentViewHolder.author.setText(bean.getAuthor());
        Glide.with(context).load(bean.getCover()).into(contentViewHolder.cover);

        if (bean.isLoved()) {
            contentViewHolder.love_btn.setImageDrawable(context
                    .getResources().getDrawable(R.drawable.ic_favorite_24dp));
        } else {
            contentViewHolder.love_btn.setImageDrawable(context
                    .getResources().getDrawable(R.drawable.ic_favorite_border_24dp));

        }
        contentViewHolder.love_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bean.isLoved()) {
                    //之前赞过，现在取消赞
                    contentViewHolder.love_btn.setImageDrawable(context
                            .getResources().getDrawable(R.drawable.ic_favorite_border_24dp));
                } else {
                    //之前没赞过，现在赞
                    contentViewHolder.love_btn.setImageDrawable(context
                            .getResources().getDrawable(R.drawable.ic_favorite_24dp));
                }
                presenter.onLoveButtonClick(bean, !bean.isLoved());
                notifyItemChanged(position);
                presenter.retainNestedScrollViewPosition();
            }
        });
        contentViewHolder.love_counter.setText(String.valueOf(bean.getLove()));


        ObjectAnimator animator = ObjectAnimator.ofFloat(holder.itemView, "alpha", 0, 1);
        animator.setDuration(200);
        animator.start();

    }

    @Override
    public int getItemViewType(int position) {
        if (position == getItemCount() - 1) {
            return FOOTER_VIEW;
        }
        return data.get(position).getType();
    }

    @Override
    public int getItemCount() {
        return data.size() + 1;
    }

    class ContentViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView author;
        ImageView cover;
        TextView love_counter;
        ImageButton love_btn;

        public ContentViewHolder(View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.item_title);
            author = itemView.findViewById(R.id.item_author);
            cover = itemView.findViewById(R.id.item_cover);
            love_btn = itemView.findViewById(R.id.item_love_btn);
            love_counter = itemView.findViewById(R.id.item_love_counter);
        }
    }

    class FooterViewHolder extends RecyclerView.ViewHolder {
        public FooterViewHolder(View itemView) {
            super(itemView);
        }
    }
}
