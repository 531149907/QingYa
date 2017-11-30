package com.xuan.qingya.Modules.Search;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.robinhood.ticker.TickerUtils;
import com.robinhood.ticker.TickerView;
import com.xuan.qingya.Common.Constant;
import com.xuan.qingya.Common.RecyclerConfig.ItemFadeAndUpAnimation;
import com.xuan.qingya.Models.entity.Article;
import com.xuan.qingya.Models.entity.Base;
import com.xuan.qingya.Models.entity.Interview;
import com.xuan.qingya.R;
import com.xuan.qingya.Utils.DensityUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhouzhixuan on 2017/9/12.
 */

public class SearchResultAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements ItemFadeAndUpAnimation {
    private final int FOOTER_VIEW = -1;
    private SearchPresenter presenter;
    private Context context;
    private List<Base> data;
    private SearchResultAdapter.OnItemClickListener onItemClickListener;
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
                    ObjectAnimator animator = ObjectAnimator.ofFloat(itemView, "translationY", (80 + position * 20), 0);
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
        void onItemClick(Base bean);

        void onLoveClick(Base bean);
    }

    public void addOnClickListener(SearchResultAdapter.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public SearchResultAdapter(Context context, SearchPresenter presenter) {
        this.context = context;
        this.presenter = presenter;
        this.data = new ArrayList<>();
        enableAnimation = true;
    }

    public void setData(List<Base> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        switch (viewType) {
            case Constant.CONTENT_SUB_TYPE_ARTICLE_READ:
                view = layoutInflater.inflate(R.layout.layout_item_article_pure, parent, false);
                break;
            case Constant.CONTENT_SUB_TYPE_ARTICLE_IMAGE:
                view = layoutInflater.inflate(R.layout.layout_item_article_withpic, parent, false);
                break;
            case Constant.CONTENT_SUB_TYPE_ARTICLE_POEM:
                view = layoutInflater.inflate(R.layout.layout_item_poem, parent, false);
                break;
            case Constant.CONTENT_SUB_TYPE_MUSIC:
                view = layoutInflater.inflate(R.layout.layout_item_music, parent, false);
                break;
            case Constant.CONTENT_SUB_TYPE_QUESTION:
                view = layoutInflater.inflate(R.layout.layout_item_ask, parent, false);
                break;
            case Constant.CONTENT_SUB_TYPE_INTERVIEW:
                view = layoutInflater.inflate(R.layout.layout_item_interview, parent, false);
                return new InterviewViewHolder(view);
            case FOOTER_VIEW:
                view = layoutInflater.inflate(R.layout.layout_item_footer, parent, false);
                return new FooterViewHolder(view);
        }

        return new DiscoverViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof FooterViewHolder) {
            return;
        }

        if (holder instanceof DiscoverViewHolder) {
            final DiscoverViewHolder viewHolder = (DiscoverViewHolder) holder;
            final Article bean = (Article) data.get(position);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(bean);
                    }
                }
            });

            switch (getItemViewType(position)) {
                case Constant.CONTENT_SUB_TYPE_ARTICLE_POEM:
                case Constant.CONTENT_SUB_TYPE_MOVIE:
                    viewHolder.title.setText(bean.getTitle());
                    viewHolder.author.setText(bean.getAuthor());
                    Glide.with(context).load(bean.getCoverImg()).into(viewHolder.cover);
                    viewHolder.content.setText(bean.getContent());
                    break;
                case Constant.CONTENT_SUB_TYPE_ARTICLE_IMAGE:
                    Glide.with(context).load(bean.getCoverImg()).into(viewHolder.cover);
                    viewHolder.author.setText(bean.getAuthor());
                    viewHolder.content.setText(bean.getContent());
                    break;
                case Constant.CONTENT_SUB_TYPE_ARTICLE_READ:
                    viewHolder.author.setText(bean.getAuthor());
                    viewHolder.content.setText(bean.getContent());
                    break;
                case Constant.CONTENT_SUB_TYPE_QUESTION:
                    viewHolder.title.setText(bean.getTitle());
                    viewHolder.author.setText(bean.getAnswerAuthor());
                    viewHolder.content.setText(bean.getContent());
                    break;
                case Constant.CONTENT_SUB_TYPE_MUSIC:
                    viewHolder.title.setText(bean.getTitle());
                    viewHolder.author.setText(bean.getAuthor());
                    viewHolder.content.setText(bean.getContent());
                    Glide.with(context).load(bean.getCoverImg()).into(viewHolder.cover);
                    break;
            }

            if (bean.isLoved()) {
                viewHolder.love_btn.setImageDrawable(context
                        .getResources().getDrawable(R.drawable.ic_favorite_24dp));
            } else {
                viewHolder.love_btn.setImageDrawable(context
                        .getResources().getDrawable(R.drawable.ic_favorite_border_24dp));

            }
            viewHolder.love_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (bean.isLoved()) {
                        //之前赞过，现在取消赞
                        viewHolder.love_btn.setImageDrawable(context
                                .getResources().getDrawable(R.drawable.ic_favorite_border_24dp));
                    } else {
                        //之前没赞过，现在赞
                        viewHolder.love_btn.setImageDrawable(context
                                .getResources().getDrawable(R.drawable.ic_favorite_24dp));
                    }
                    presenter.onLoveButtonClick(bean);
                    notifyItemChanged(position);
                }
            });
            viewHolder.love_counter.setText(String.valueOf(bean.getLove()));
        }

        if (holder instanceof InterviewViewHolder) {

            final InterviewViewHolder contentViewHolder = (InterviewViewHolder) holder;
            final Interview bean = (Interview) data.get(position);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(bean);
                    }
                }
            });

            contentViewHolder.title.setText(bean.getTitle());
            contentViewHolder.author.setText(bean.getAuthor());
            Glide.with(context).load(bean.getCoverContent()).into(contentViewHolder.cover);

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
                    if (onItemClickListener != null) {
                        onItemClickListener.onLoveClick(bean);
                    }
                    contentViewHolder.love_counter.setText(bean.getLove());
                    contentViewHolder.love_counter.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            notifyItemChanged(position);
                        }
                    }, 200);
                    notifyItemChanged(position);
                }
            });
            contentViewHolder.love_counter.setText(String.valueOf(bean.getLove()));
        }

        if (enableAnimation) {
            itemAnimationStart(holder.itemView, position);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == getItemCount() - 1) {
            return FOOTER_VIEW;
        }
        return data.get(position).getSubType();
    }

    @Override
    public int getItemCount() {
        return data.size() + 1;
    }

    class DiscoverViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView author;
        ImageView cover;
        TextView content;
        TextView type;
        TickerView love_counter;
        ImageButton love_btn;

        public DiscoverViewHolder(View itemView) {
            super(itemView);
            if (itemView.findViewById(R.id.item_title) != null) {
                title = itemView.findViewById(R.id.item_title);
            }
            if (itemView.findViewById(R.id.item_author) != null) {
                author = itemView.findViewById(R.id.item_author);
            }
            if (itemView.findViewById(R.id.item_cover) != null) {
                cover = itemView.findViewById(R.id.item_cover);
            }
            if (itemView.findViewById(R.id.item_content) != null) {
                content = itemView.findViewById(R.id.item_content);
            }

            type = itemView.findViewById(R.id.item_type);
            love_btn = itemView.findViewById(R.id.item_love_btn);
            love_counter = itemView.findViewById(R.id.item_love_counter);
            love_counter.setCharacterList(TickerUtils.getDefaultNumberList());
            love_counter.setAnimationDuration(200);
            love_counter.setAnimationInterpolator(new DecelerateInterpolator());
            love_counter.setGravity(Gravity.RIGHT);
        }
    }

    class InterviewViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView author;
        ImageView cover;
        TextView love_counter;
        ImageButton love_btn;

        public InterviewViewHolder(View itemView) {
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
