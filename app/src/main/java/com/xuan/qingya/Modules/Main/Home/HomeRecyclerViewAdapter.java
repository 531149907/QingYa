package com.xuan.qingya.Modules.Main.Home;

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
import com.xuan.qingya.Modules.Main.MainContract;
import com.xuan.qingya.R;

import java.util.List;

public class HomeRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int FOOTER_VIEW = -1;
    private MainContract.HomePresenter presenter;
    private Context context;
    private List<ArticleBean> data;
    private OnItemClickListener onItemClickListener;

    interface OnItemClickListener {
        void onClick(View view, ArticleBean bean, int position);
    }

    public void addOnClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public HomeRecyclerViewAdapter(Context context, List<ArticleBean> data, MainContract.HomePresenter presenter) {
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
            case FOOTER_VIEW:
                view = layoutInflater.inflate(R.layout.layout_item_footer, parent, false);
                return new FooterViewHolder(view);
        }

        return new ContentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof FooterViewHolder) {
            return;
        }

        final ContentViewHolder contentViewHolder = (ContentViewHolder) holder;
        final ArticleBean bean = data.get(position);

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
                contentViewHolder.title.setText(bean.getTitle());
                contentViewHolder.author.setText(bean.getAuthor());
                Glide.with(context).load(R.drawable.a26).into(contentViewHolder.cover);
                contentViewHolder.content.setText(bean.getContent());
                break;
            case Constant.CONTENT_DISCOVER_ARTICLE_IMAGE:
                Glide.with(context).load(R.drawable.a8).into(contentViewHolder.cover);
                contentViewHolder.author.setText(bean.getAuthor());
                contentViewHolder.content.setText(bean.getContent());
                break;
            case Constant.CONTENT_DISCOVER_ARTICLE_POEM:
                contentViewHolder.author.setText(bean.getAuthor());
                contentViewHolder.content.setText(bean.getContent());
                break;
            case Constant.CONTENT_DISCOVER_QUESTION:
                contentViewHolder.title.setText(bean.getTitle());
                contentViewHolder.author.setText(bean.getAnswer_author());
                contentViewHolder.content.setText(bean.getContent());
                break;
            case Constant.CONTENT_DISCOVER_MUSIC:
                contentViewHolder.title.setText(bean.getTitle());
                contentViewHolder.author.setText(bean.getAuthor());
                contentViewHolder.content.setText(bean.getContent());
                Glide.with(context).load(R.drawable.a24).into(contentViewHolder.cover);
                break;
        }

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
        TextView content;
        TextView type;
        TextView love_counter;
        ImageButton love_btn;

        public ContentViewHolder(View itemView) {
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

    class FooterViewHolder extends RecyclerView.ViewHolder {
        public FooterViewHolder(View itemView) {
            super(itemView);
        }
    }
}
