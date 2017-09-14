package com.xuan.qingya.Modules.Search;

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
import com.xuan.qingya.Models.Entity.BaseBean;
import com.xuan.qingya.Models.Entity.InterviewBean;
import com.xuan.qingya.R;

import java.util.List;

/**
 * Created by zhouzhixuan on 2017/9/12.
 */

public class SearchResultAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int FOOTER_VIEW = -1;
    private SearchContract.SearchPresenter presenter;
    private Context context;
    private List<BaseBean> data;
    private SearchResultAdapter.OnItemClickListener onItemClickListener;

    interface OnItemClickListener {
        void onClick(View view, BaseBean bean, int position);
    }

    public void addOnClickListener(SearchResultAdapter.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public SearchResultAdapter(Context context, List<BaseBean> data, SearchContract.SearchPresenter presenter) {
        this.context = context;
        this.data = data;
        this.presenter = presenter;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        switch (viewType) {
            case Constant.CONTENT_DISCOVER_ARTICLE_READ:
                view = layoutInflater.inflate(R.layout.layout_item_article_pure, parent, false);
                break;
            case Constant.CONTENT_DISCOVER_ARTICLE_IMAGE:
                view = layoutInflater.inflate(R.layout.layout_item_article_withpic, parent, false);
                break;
            case Constant.CONTENT_DISCOVER_ARTICLE_POEM:
                view = layoutInflater.inflate(R.layout.layout_item_poem, parent, false);
                break;
            case Constant.CONTENT_DISCOVER_MUSIC:
                view = layoutInflater.inflate(R.layout.layout_item_music, parent, false);
                break;
            case Constant.CONTENT_DISCOVER_QUESTION:
                view = layoutInflater.inflate(R.layout.layout_item_ask, parent, false);
                break;
            case Constant.CONTENT_INTERVIEW_IMAGE:
            case Constant.CONTENT_INTERVIEW_VIDEO:
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
            final ArticleBean bean = (ArticleBean) data.get(position);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onClick(view, bean, position);
                    }
                }
            });

            switch (getItemViewType(position)) {
                case Constant.CONTENT_DISCOVER_ARTICLE_READ:
                case Constant.CONTENT_DISCOVER_MOVIE:
                    viewHolder.title.setText(bean.getTitle());
                    viewHolder.author.setText(bean.getAuthor());
                    Glide.with(context).load(R.drawable.a26).into(viewHolder.cover);
                    viewHolder.content.setText(bean.getContent());
                    break;
                case Constant.CONTENT_DISCOVER_ARTICLE_IMAGE:
                    Glide.with(context).load(R.drawable.a8).into(viewHolder.cover);
                    viewHolder.author.setText(bean.getAuthor());
                    viewHolder.content.setText(bean.getContent());
                    break;
                case Constant.CONTENT_DISCOVER_ARTICLE_POEM:
                    viewHolder.author.setText(bean.getAuthor());
                    viewHolder.content.setText(bean.getContent());
                    break;
                case Constant.CONTENT_DISCOVER_QUESTION:
                    viewHolder.title.setText(bean.getTitle());
                    viewHolder.author.setText(bean.getAnswer_author());
                    viewHolder.content.setText(bean.getContent());
                    break;
                case Constant.CONTENT_DISCOVER_MUSIC:
                    viewHolder.title.setText(bean.getTitle());
                    viewHolder.author.setText(bean.getAuthor());
                    viewHolder.content.setText(bean.getContent());
                    Glide.with(context).load(R.drawable.a24).into(viewHolder.cover);
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
                    presenter.onLoveButtonClick(bean, !bean.isLoved());
                    notifyItemChanged(position);
                }
            });
            viewHolder.love_counter.setText(String.valueOf(bean.getLove()));
        }

        if (holder instanceof InterviewViewHolder) {

            final InterviewViewHolder contentViewHolder = (InterviewViewHolder) holder;
            final InterviewBean bean = (InterviewBean) data.get(position);

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
                }
            });
            contentViewHolder.love_counter.setText(String.valueOf(bean.getLove()));
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

    class DiscoverViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView author;
        ImageView cover;
        TextView content;
        TextView type;
        TextView love_counter;
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
