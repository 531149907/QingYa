package com.xuan.qingya.Modules.Discover.Topic;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xuan.qingya.Models.Entity.ArticleBean;
import com.xuan.qingya.R;

import java.util.List;

/**
 * Created by zhouzhixuan on 2017/9/15.
 */

public class TopicDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int FOOTER_VIEW = -1;
    private DiscoverTopicContract.DiscoverTopicPresenter presenter;
    private Context context;
    private List<ArticleBean> data;
    private OnItemClickListener onItemClickListener;
    private boolean enableAnimation;

    public void itemAnimationStart(final View itemView, final int position) {
        itemView.post(new Runnable() {
            @Override
            public void run() {
                /*if (itemView.getHeight() * (position + 1) + DensityUtil.dip2px(240) > DensityUtil.getScreenHeight()) {
                    enableAnimation = false;
                }*/
                if (enableAnimation) {
                    ObjectAnimator animator = ObjectAnimator.ofFloat(itemView, "translationY", 50, 0);
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
        void onClick(View view, ArticleBean bean, int position);
    }

    public void addOnClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public TopicDetailAdapter(Context context, List<ArticleBean> data, DiscoverTopicContract.DiscoverTopicPresenter presenter) {
        this.context = context;
        this.data = data;
        this.presenter = presenter;
        enableAnimation = true;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item_discover_topic_card, parent, false);
        return new ContentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof FooterViewHolder) {
            return;
        }

        holder.itemView.setAlpha(0);

        final ContentViewHolder contentViewHolder = (ContentViewHolder) holder;
        final ArticleBean bean = data.get(position % 4);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickListener != null) {
                    onItemClickListener.onClick(view, bean, position);
                }
            }
        });

        contentViewHolder.title.setText(bean.getTitle());
        contentViewHolder.author.setText(bean.getAuthor());
        contentViewHolder.description.setText(bean.getContent());
        Glide.with(context).load(R.drawable.a26).into(contentViewHolder.cover);


        if (bean.isLoved())

        {
            contentViewHolder.love_btn.setImageDrawable(context
                    .getResources().getDrawable(R.drawable.ic_favorite_24dp));
        } else

        {
            contentViewHolder.love_btn.setImageDrawable(context
                    .getResources().getDrawable(R.drawable.ic_favorite_border_24dp));

        }
        contentViewHolder.love_btn.setOnClickListener(new View.OnClickListener()

        {
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
            }
        });
        contentViewHolder.love_counter.setText(String.valueOf(bean.getLove()));

        if (enableAnimation) {
            itemAnimationStart(holder.itemView, position);
        }
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
        TextView description;
        TextView love_counter;
        ImageButton love_btn;

        public ContentViewHolder(View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            author = itemView.findViewById(R.id.author);
            cover = itemView.findViewById(R.id.cover);
            description = itemView.findViewById(R.id.description);
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
