package com.xuan.qingya.Modules.Profile.Collection;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xuan.qingya.Common.Constant;
import com.xuan.qingya.Models.Entity.ArticleBean;
import com.xuan.qingya.Modules.Profile.ProfileContract;
import com.xuan.qingya.R;

import java.util.List;

/**
 * Created by zhouzhixuan on 2017/9/5.
 */

public class CollectionRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int FOOTER_VIEW = -1;
    private ProfileContract.CollectionPresenter presenter;
    private Context context;
    private List<ArticleBean> data;
    private CollectionRecyclerViewAdapter.OnItemClickListener onItemClickListener;

    interface OnItemClickListener {
        void onClick(View view, ArticleBean bean, int position);
    }

    public void addOnClickListener(CollectionRecyclerViewAdapter.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public CollectionRecyclerViewAdapter(Context context, List<ArticleBean> data, ProfileContract.CollectionPresenter presenter) {
        this.context = context;
        this.data = data;
        this.presenter = presenter;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        switch (viewType) {
            case Constant.CONTENT_DISCOVER_MOVIE:
            case Constant.CONTENT_DISCOVER_ARTICLE_READ:
            case Constant.CONTENT_DISCOVER_ARTICLE_IMAGE:
            case Constant.CONTENT_DISCOVER_ARTICLE_POEM:
            case Constant.CONTENT_DISCOVER_MUSIC:
            case Constant.CONTENT_DISCOVER_QUESTION:
                view = layoutInflater.inflate(R.layout.layout_item_collection_others, parent, false);
                return new OthersItemViewHolder(view);
            case Constant.CONTENT_DISCOVER_PHOTOGRAPHY:
                view = layoutInflater.inflate(R.layout.layout_item_collection_photo, parent, false);
                return new PhotoItemViewHolder(view);
            case Constant.CONTENT_INTERVIEW_IMAGE:
            case Constant.CONTENT_INTERVIEW_VIDEO:
                view = layoutInflater.inflate(R.layout.layout_item_collection_interview, parent, false);
                return new InterviewItemViewHolder(view);
            case FOOTER_VIEW:
                view = layoutInflater.inflate(R.layout.layout_item_footer, parent, false);
                return new CollectionRecyclerViewAdapter.FooterViewHolder(view);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof FooterViewHolder) {
            return;
        }

        final ArticleBean bean = data.get(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickListener != null) {
                    onItemClickListener.onClick(view, bean, position);
                }
            }
        });

        if (holder instanceof OthersItemViewHolder) {
            final OthersItemViewHolder viewHolder = (OthersItemViewHolder) holder;

            viewHolder.title.setText(bean.getTitle());
            if (bean.getType() == Constant.CONTENT_DISCOVER_QUESTION) {
                viewHolder.author.setText(bean.getAnswer_author());
            } else {
                viewHolder.author.setText(bean.getAuthor());
            }
            Glide.with(context).load(bean.getCover_img()).into(viewHolder.cover);

            if (position == data.size() - 1) {
                viewHolder.divider.setVisibility(View.INVISIBLE);
            }

            if (bean.isLoved()) {
                viewHolder.love.setImageDrawable(context
                        .getResources().getDrawable(R.drawable.ic_favorite_24dp));
            } else {
                viewHolder.love.setImageDrawable(context
                        .getResources().getDrawable(R.drawable.ic_favorite_border_24dp));

            }

            viewHolder.love.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (bean.isLoved()) {
                        //之前赞过，现在取消赞
                        viewHolder.love.setImageDrawable(context
                                .getResources().getDrawable(R.drawable.ic_favorite_border_24dp));
                    } else {
                        //之前没赞过，现在赞
                        viewHolder.love.setImageDrawable(context
                                .getResources().getDrawable(R.drawable.ic_favorite_24dp));
                    }
                    presenter.onLoveCancel(bean);
                    notifyItemChanged(position);
                }
            });
        }

        if (holder instanceof PhotoItemViewHolder) {
            final PhotoItemViewHolder viewHolder = (PhotoItemViewHolder) holder;
            viewHolder.author.setText(bean.getAuthor());
            Glide.with(context).load(bean.getCover_img()).into(viewHolder.cover);

            if (bean.isLoved()) {
                viewHolder.love.setImageDrawable(context
                        .getResources().getDrawable(R.drawable.ic_favorite_24dp));
            } else {
                viewHolder.love.setImageDrawable(context
                        .getResources().getDrawable(R.drawable.ic_favorite_border_24dp));

            }

            viewHolder.love.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (bean.isLoved()) {
                        //之前赞过，现在取消赞
                        viewHolder.love.setImageDrawable(context
                                .getResources().getDrawable(R.drawable.ic_favorite_border_24dp));
                    } else {
                        //之前没赞过，现在赞
                        viewHolder.love.setImageDrawable(context
                                .getResources().getDrawable(R.drawable.ic_favorite_24dp));
                    }
                    presenter.onLoveCancel(bean);
                    notifyItemChanged(position);
                }
            });
        }

        if (holder instanceof InterviewItemViewHolder) {
            final InterviewItemViewHolder viewHolder = (InterviewItemViewHolder) holder;

            viewHolder.title.setText(bean.getTitle());
            viewHolder.author.setText(bean.getAuthor());
            Glide.with(context).load(bean.getCover_img()).into(viewHolder.cover);

            if (bean.isLoved()) {
                viewHolder.love.setImageDrawable(context
                        .getResources().getDrawable(R.drawable.ic_favorite_24dp));
            } else {
                viewHolder.love.setImageDrawable(context
                        .getResources().getDrawable(R.drawable.ic_favorite_border_24dp));

            }

            viewHolder.love.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (bean.isLoved()) {
                        //之前赞过，现在取消赞
                        viewHolder.love.setImageDrawable(context
                                .getResources().getDrawable(R.drawable.ic_favorite_border_24dp));
                    } else {
                        //之前没赞过，现在赞
                        viewHolder.love.setImageDrawable(context
                                .getResources().getDrawable(R.drawable.ic_favorite_24dp));
                    }
                    presenter.onLoveCancel(bean);
                    notifyItemChanged(position);
                }
            });
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

    class InterviewItemViewHolder extends RecyclerView.ViewHolder {
        ImageView cover;
        TextView title;
        TextView author;
        ImageButton love;

        public InterviewItemViewHolder(View itemView) {
            super(itemView);
            cover = itemView.findViewById(R.id.cover);
            title = itemView.findViewById(R.id.title);
            author = itemView.findViewById(R.id.author);
            love = itemView.findViewById(R.id.love);
        }
    }

    class PhotoItemViewHolder extends RecyclerView.ViewHolder {
        ImageView cover;
        TextView author;
        ImageButton love;

        public PhotoItemViewHolder(View itemView) {
            super(itemView);
            cover = itemView.findViewById(R.id.cover);
            author = itemView.findViewById(R.id.author);
            love = itemView.findViewById(R.id.love);
        }
    }

    class OthersItemViewHolder extends RecyclerView.ViewHolder {
        ImageView cover;
        TextView title;
        TextView author;
        ImageButton love;
        ImageView divider;

        public OthersItemViewHolder(View itemView) {
            super(itemView);
            cover = itemView.findViewById(R.id.cover);
            title = itemView.findViewById(R.id.title);
            author = itemView.findViewById(R.id.author);
            love = itemView.findViewById(R.id.love);
            divider = itemView.findViewById(R.id.divider);
        }
    }

    class FooterViewHolder extends RecyclerView.ViewHolder {
        public FooterViewHolder(View itemView) {
            super(itemView);
        }
    }
}
