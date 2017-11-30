package com.xuan.qingya.Modules.Discover.List;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xuan.qingya.Common.Constant;
import com.xuan.qingya.Core.Observer.AnimationObserver.AnimationController;
import com.xuan.qingya.Core.Observer.AnimationObserver.AnimationObserverContract;
import com.xuan.qingya.Models.entity.Article;
import com.xuan.qingya.Models.entity.ItemViewInfo;
import com.xuan.qingya.Modules.Discover.Detail.DiscoverDetailActivity;
import com.xuan.qingya.R;
import com.xuan.qingya.Utils.DensityUtil;
import com.xuan.qingya.Utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhouzhixuan on 2017/8/30.
 */

public class DiscoverListRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements AnimationObserverContract.AnimationObserver {
    private static final int FOOTER_VIEW = -1;
    private static final int SECTION_VIEW = -2;

    private Context context;
    private RecyclerView recyclerView;
    private List<Article> data;
    private OnItemClickListener onItemClickListener;

    private ItemViewInfo viewInfo = new ItemViewInfo();
    private int[] clickedViewLocation = new int[2];
    private int clickedViewPosition = 0;

    public DiscoverListRecyclerViewAdapter(Context context, RecyclerView recyclerView) {
        this.context = context;
        this.recyclerView = recyclerView;
        data = new ArrayList<>();
    }

    public void setData(List<Article> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        switch (viewType) {
            case Constant.CONTENT_SUB_TYPE_ARTICLE_IMAGE:
            case Constant.CONTENT_SUB_TYPE_ARTICLE_POEM:
            case Constant.CONTENT_SUB_TYPE_ARTICLE_READ:
            case Constant.CONTENT_SUB_TYPE_MUSIC:
            case Constant.CONTENT_SUB_TYPE_MOVIE:
                view = layoutInflater.inflate(R.layout.layout_item_list_style1, parent, false);
                return new DiscoverListRecyclerViewAdapter.ArticleMusicMovieViewHolder(view);
            case Constant.CONTENT_SUB_TYPE_QUESTION:
                view = layoutInflater.inflate(R.layout.layout_item_list_style2, parent, false);
                return new DiscoverListRecyclerViewAdapter.AskViewHolder(view);
            case Constant.CONTENT_SUB_TYPE_PHOTOGRAPHY:
                view = layoutInflater.inflate(R.layout.layout_item_list_style3, parent, false);
                return new DiscoverListRecyclerViewAdapter.PhotographyViewHolder(view);
            case SECTION_VIEW:
                view = layoutInflater.inflate(R.layout.layout_item_section_date, parent, false);
                return new DiscoverListRecyclerViewAdapter.SectionViewHolder(view);
            case FOOTER_VIEW:
                view = layoutInflater.inflate(R.layout.layout_item_footer, parent, false);
                return new DiscoverListRecyclerViewAdapter.FooterViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof DiscoverListRecyclerViewAdapter.FooterViewHolder) {
            return;
        }
        if (holder instanceof DiscoverListRecyclerViewAdapter.SectionViewHolder) {
            return;
        }

        final Article bean = data.get(position - 1);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickedViewPosition = position;
                startEnterAnimation(position);
            }
        });

        if (holder instanceof DiscoverListRecyclerViewAdapter.ArticleMusicMovieViewHolder) {
            final ArticleMusicMovieViewHolder viewHolder = (ArticleMusicMovieViewHolder) holder;

            viewHolder.title.setText(bean.getTitle());
            viewHolder.author.setText(bean.getAuthor());
            Glide.with(context).load(bean.getCoverImg()).into(viewHolder.cover);
            return;
        }
        if (holder instanceof DiscoverListRecyclerViewAdapter.AskViewHolder) {
            final AskViewHolder viewHolder = (AskViewHolder) holder;

            viewHolder.title.setText(bean.getTitle());
            viewHolder.content.setText(bean.getAskContent());
            return;
        }
        if (holder instanceof DiscoverListRecyclerViewAdapter.PhotographyViewHolder) {
            final PhotographyViewHolder viewHolder = (PhotographyViewHolder) holder;

            Glide.with(context).load(bean.getCoverImg()).into(viewHolder.cover);
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
                    if (onItemClickListener != null) {
                        onItemClickListener.onLoveButtonClick(bean, position);
                    }
                    notifyItemChanged(position);
                }
            });
            viewHolder.love_counter.setText(String.valueOf(bean.getLove()));
        }

    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return SECTION_VIEW;
        }
        if (position > data.size()) {
            return FOOTER_VIEW;
        }
        return data.get(position - 1).getSubType();
    }

    @Override
    public int getItemCount() {
        return data.size() + 2;
    }

    class ArticleMusicMovieViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView author;
        ImageView cover;

        public ArticleMusicMovieViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.item_title);
            author = itemView.findViewById(R.id.item_author);
            cover = itemView.findViewById(R.id.item_cover);

        }
    }

    class PhotographyViewHolder extends RecyclerView.ViewHolder {

        ImageView cover;
        TextView love_counter;
        ImageButton love_btn;

        public PhotographyViewHolder(View itemView) {
            super(itemView);
            cover = itemView.findViewById(R.id.item_cover);
            love_btn = itemView.findViewById(R.id.item_love_btn);
            love_counter = itemView.findViewById(R.id.item_love_counter);
        }
    }

    class AskViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView content;

        public AskViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.item_title);
            content = itemView.findViewById(R.id.item_content);
        }
    }

    class SectionViewHolder extends RecyclerView.ViewHolder {

        TextView header_date;

        public SectionViewHolder(View itemView) {
            super(itemView);
            header_date = itemView.findViewById(R.id.item_header_date);
        }
    }

    class FooterViewHolder extends RecyclerView.ViewHolder {
        public FooterViewHolder(View itemView) {
            super(itemView);
        }
    }

    private void startEnterAnimation(final int clickedViewPosition) {
        AnimationController.getInstance().cleanObservers();
        AnimationController.getInstance().addObserver(this);

        for (int count = 0; count < recyclerView.getChildCount(); count++) {
            LogUtil.show("is view null", (recyclerView.getChildAt(count) == null) + "");
        }

        final View clickedView = recyclerView.getChildAt(clickedViewPosition);
        clickedView.getLocationOnScreen(clickedViewLocation);

        viewInfo.setSize(clickedView.getWidth(), clickedView.getHeight());
        viewInfo.setLocation(clickedViewLocation);
        viewInfo.setTranslationXY(clickedView.getTranslationX(), clickedView.getTranslationY());

        int appBarHeight = context.getResources().getDimensionPixelSize(R.dimen.appbar_with_tablayout_height);
        boolean isAppbarOverlay = false;
        if (clickedViewLocation[1] < appBarHeight) {
            isAppbarOverlay = true;
        }

        Intent intent = new Intent(context, DiscoverDetailActivity.class);
        intent.putExtra("bean", data.get(clickedViewPosition - 1));
        intent.putExtra("viewInfo", viewInfo);
        intent.putExtra("entryMode", "fromDiscoverList");
        intent.putExtra("selectedTab", getSelectedTab(data.get(clickedViewPosition - 1).getSubType()));
        intent.putExtra("isAppbarOverlay", isAppbarOverlay);

        if (onItemClickListener != null) {
            onItemClickListener.onClick(null, intent, clickedViewPosition);
        }

        ((DiscoverListActivity) context).overridePendingTransition(0, 0);

        recyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                ((DiscoverListActivity) context).getAppBarLayout().setAlpha(0);
                AnimationController.getInstance().doAnimation("ENTER");
            }
        }, 100);

    }

    @Override
    public void doExitAnimation() {
        final int ANIMATION_DURATION = 350;
        final float screenHeight = DensityUtil.getScreenHeight();
        final View clickedView = recyclerView.getChildAt(clickedViewPosition);

        ObjectAnimator recyclerViewMovement = ObjectAnimator.ofFloat(recyclerView, "translationY", -(viewInfo.getY()), 0);
        recyclerViewMovement.setDuration(ANIMATION_DURATION);

        ObjectAnimator itemViewAlpha = ObjectAnimator.ofFloat(clickedView, "alpha", 0, 1);
        itemViewAlpha.setDuration((long) (ANIMATION_DURATION * 0.8));
        itemViewAlpha.setStartDelay((long) (ANIMATION_DURATION * 0.5));

        final AnimatorSet set = new AnimatorSet();
        set.playTogether(recyclerViewMovement, itemViewAlpha);
        set.setInterpolator(new AccelerateDecelerateInterpolator());

        final int distance = (int) screenHeight - viewInfo.getHeight();
        final RecyclerView.LayoutParams clickedViewParams = (RecyclerView.LayoutParams) clickedView.getLayoutParams();
        final Animation clickedViewAnimation = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                clickedViewParams.height = (int) (screenHeight - distance * interpolatedTime);
                clickedView.setLayoutParams(clickedViewParams);
            }
        };
        clickedViewAnimation.setDuration(ANIMATION_DURATION);
        clickedViewAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        clickedViewAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                set.start();
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                ((DiscoverListActivity) context).getAppBarLayout().setAlpha(1);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        clickedView.startAnimation(clickedViewAnimation);

    }

    @Override
    public void doEnterAnimation() {
        final int ANIMATION_DURATION = 350;
        final float screenHeight = DensityUtil.getScreenHeight();
        final View clickedView = recyclerView.getChildAt(clickedViewPosition);

        ObjectAnimator recyclerViewMovement = ObjectAnimator.ofFloat(recyclerView, "translationY", 0, -(viewInfo.getY()));
        recyclerViewMovement.setDuration(ANIMATION_DURATION);

        ObjectAnimator itemViewAlpha = ObjectAnimator.ofFloat(clickedView, "alpha", 1, 0);
        itemViewAlpha.setDuration((long) (ANIMATION_DURATION * 0.5));

        final AnimatorSet set = new AnimatorSet();
        set.playTogether(recyclerViewMovement, itemViewAlpha);
        set.setInterpolator(new AccelerateDecelerateInterpolator());

        final int distance = (int) screenHeight - viewInfo.getHeight();
        final RecyclerView.LayoutParams clickedViewParams = (RecyclerView.LayoutParams) clickedView.getLayoutParams();
        final Animation clickedViewAnimation = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                clickedViewParams.height = (int) (viewInfo.getHeight() + distance * interpolatedTime);
                clickedView.setLayoutParams(clickedViewParams);
            }
        };
        clickedViewAnimation.setDuration(ANIMATION_DURATION);
        clickedViewAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        clickedViewAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                set.start();
            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        clickedView.startAnimation(clickedViewAnimation);
    }

    private int getSelectedTab(int type) {
        switch (type) {
            case Constant.CONTENT_SUB_TYPE_ARTICLE_IMAGE:
            case Constant.CONTENT_SUB_TYPE_ARTICLE_POEM:
            case Constant.CONTENT_SUB_TYPE_ARTICLE_READ:
                return 0;
            case Constant.CONTENT_SUB_TYPE_PHOTOGRAPHY:
                return 1;
            case Constant.CONTENT_SUB_TYPE_QUESTION:
                return 4;
            case Constant.CONTENT_SUB_TYPE_MUSIC:
                return 2;
            case Constant.CONTENT_SUB_TYPE_MOVIE:
                return 3;
        }
        return 0;
    }

    public void addOnClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    interface OnItemClickListener {
        void onClick(Article bean, Intent intent, int position);

        void onLoveButtonClick(Article bean, int position);
    }
}
