package com.xuan.qingya.Modules.Discover.List;

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
import com.xuan.qingya.R;

import java.util.List;

/**
 * Created by zhouzhixuan on 2017/8/30.
 */

public class DiscoverListRecyclerViewAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int FOOTER_VIEW = -1;
    private Context context;
    private List<T> data;
    private DiscoverListRecyclerViewAdapter.OnItemClickListener onItemClickListener;

    public DiscoverListRecyclerViewAdapter(Context context, List<T> data) {
        this.context = context;
        this.data = data;
    }

    public void addOnClickListener(DiscoverListRecyclerViewAdapter.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
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
                return new DiscoverListRecyclerViewAdapter.FooterViewHolder(view);
        }

        return new DiscoverListRecyclerViewAdapter.ContentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof DiscoverListRecyclerViewAdapter.FooterViewHolder) {
            return;
        }

        final DiscoverListRecyclerViewAdapter.ContentViewHolder contentViewHolder = (DiscoverListRecyclerViewAdapter.ContentViewHolder) holder;
        final T bean = data.get(position);

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

    interface OnItemClickListener {
        void onClick(View view, T bean, int position);
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
