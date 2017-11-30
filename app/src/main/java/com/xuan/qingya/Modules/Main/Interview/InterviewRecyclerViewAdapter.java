package com.xuan.qingya.Modules.Main.Interview;

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
import com.xuan.qingya.Core.Observer.AnimationObserver.AnimationController;
import com.xuan.qingya.Core.Observer.AnimationObserver.AnimationObserverContract;
import com.xuan.qingya.Models.entity.Interview;
import com.xuan.qingya.Models.entity.ItemViewInfo;
import com.xuan.qingya.Modules.Interview.InterviewDetailActivity;
import com.xuan.qingya.Modules.Main.MainActivity;
import com.xuan.qingya.R;
import com.xuan.qingya.Utils.DensityUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhouzhixuan on 2017/8/27.
 */

public class InterviewRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements AnimationObserverContract.AnimationObserver {
    private final int FOOTER_VIEW = -1;
    private InterviewPresenter presenter;
    private Context context;
    private List<Interview> data;
    private RecyclerView recyclerView;
    private InterviewRecyclerViewAdapter.OnItemClickListener onItemClickListener;

    private ItemViewInfo viewInfo = new ItemViewInfo();
    private int[] clickedViewLocation = new int[2];
    private int clickedViewPosition = 0;

    interface OnItemClickListener {
        void onClick(View view, Interview bean, int position);
    }

    public void addOnClickListener(InterviewRecyclerViewAdapter.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public InterviewRecyclerViewAdapter(Context context, RecyclerView recyclerView, InterviewPresenter presenter) {
        this.context = context;
        this.presenter = presenter;
        this.recyclerView = recyclerView;
        data = new ArrayList<>();
    }

    public void setData(List<Interview> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        switch (viewType) {

            case FOOTER_VIEW:
                view = layoutInflater.inflate(R.layout.layout_item_footer, parent, false);
                return new InterviewRecyclerViewAdapter.FooterViewHolder(view);
            default:
                view = layoutInflater.inflate(R.layout.layout_item_interview, parent, false);
                break;
        }

        return new InterviewRecyclerViewAdapter.ContentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof InterviewRecyclerViewAdapter.FooterViewHolder) {
            return;
        }

        final InterviewRecyclerViewAdapter.ContentViewHolder contentViewHolder = (InterviewRecyclerViewAdapter.ContentViewHolder) holder;
        final Interview bean = data.get(position);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickListener != null) {
                    onItemClickListener.onClick(view, bean, position);
                }

                clickedViewPosition = position;
                initAnimation(clickedViewPosition);
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
                presenter.onLoveButtonClick(bean);
                notifyItemChanged(position);
                presenter.retainNestedScrollViewPosition();
            }
        });
        contentViewHolder.love_counter.setText(String.valueOf(bean.getLove()));
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

    private void initAnimation(int clickedViewPosition) {
        AnimationController.getInstance().cleanObservers();
        AnimationController.getInstance().addObserver(this);

        final View clickedView = recyclerView.getChildAt(clickedViewPosition);
        clickedView.getLocationOnScreen(clickedViewLocation);

        viewInfo.setSize(clickedView.getWidth(), clickedView.getHeight());
        viewInfo.setLocation(clickedViewLocation);
        viewInfo.setTranslationXY(clickedView.getTranslationX(), clickedView.getTranslationY());

        int appBarHeight = context.getResources().getDimensionPixelSize(R.dimen.appbar_normal_height);
        boolean isAppbarOverlay = false;
        if (clickedViewLocation[1] < appBarHeight) {
            isAppbarOverlay = true;
        }

        Intent intent = new Intent(context, InterviewDetailActivity.class);
        intent.putExtra("bean", data.get(clickedViewPosition));
        intent.putExtra("viewInfo", viewInfo);
        intent.putExtra("isAppbarOverlay", isAppbarOverlay);
        context.startActivity(intent);
        ((MainActivity) context).overridePendingTransition(0, 0);

        recyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                ((MainActivity) context).getAppBarLayout().setAlpha(0);
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
                ((MainActivity) context).getAppBarLayout().setAlpha(1);
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
}
