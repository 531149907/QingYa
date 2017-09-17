package com.xuan.qingya.Modules.Discover.List;

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
import com.xuan.qingya.Models.Entity.DiscoverListBean;
import com.xuan.qingya.Modules.Discover.Detail.DiscoverDetailActivity;
import com.xuan.qingya.R;

import java.util.List;

/**
 * Created by zhouzhixuan on 2017/8/30.
 */

public class DiscoverListRecyclerViewAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int FOOTER_VIEW = -1;
    private final int SECTION_VIEW = -2;
    private Context context;
    private List<DiscoverListBean> data;
    private DiscoverListRecyclerViewAdapter.OnItemClickListener onItemClickListener;
    private DiscoverListContract.DiscoverListPresenter presenter;

    public DiscoverListRecyclerViewAdapter(Context context, DiscoverListContract.DiscoverListPresenter presenter, List<DiscoverListBean> data) {
        this.context = context;
        this.data = data;
        this.presenter = presenter;
    }

    public void setData(List<DiscoverListBean> data) {
        this.data = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        switch (viewType) {
            case Constant.SIMPLIFY_CONTENT_TYPE_ARTICLE:
            case Constant.SIMPLIFY_CONTENT_TYPE_MUSIC:
            case Constant.SIMPLIFY_CONTENT_TYPE_MOVIE:
                view = layoutInflater.inflate(R.layout.layout_item_list_style1, parent, false);
                return new DiscoverListRecyclerViewAdapter.ArticleMusicMovieViewHolder(view);
            case Constant.SIMPLIFY_CONTENT_TYPE_QUESTION:
                view = layoutInflater.inflate(R.layout.layout_item_list_style2, parent, false);
                return new DiscoverListRecyclerViewAdapter.AskViewHolder(view);
            case Constant.SIMPLIFY_CONTENT_TYPE_PHOTOGRAPHY:
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

        final DiscoverListBean bean = data.get(position - 1);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickListener != null) {
                    onItemClickListener.onClick(view, bean, position);
                }
                Intent intent = new Intent(context, DiscoverDetailActivity.class);
                intent.putExtra("beanType", bean.getType());
                context.startActivity(intent);
            }
        });

        if (holder instanceof DiscoverListRecyclerViewAdapter.ArticleMusicMovieViewHolder) {
            final DiscoverListRecyclerViewAdapter.ArticleMusicMovieViewHolder viewHolder = (DiscoverListRecyclerViewAdapter.ArticleMusicMovieViewHolder) holder;

            viewHolder.title.setText(bean.getTitle());
            viewHolder.author.setText(bean.getAuthor());
            Glide.with(context).load(bean.getCover_img()).into(viewHolder.cover);
            return;
        }
        if (holder instanceof DiscoverListRecyclerViewAdapter.AskViewHolder) {
            final DiscoverListRecyclerViewAdapter.AskViewHolder viewHolder = (DiscoverListRecyclerViewAdapter.AskViewHolder) holder;

            viewHolder.title.setText(bean.getTitle());
            viewHolder.content.setText(bean.getAsk_content());
            return;
        }
        if (holder instanceof DiscoverListRecyclerViewAdapter.PhotographyViewHolder) {
            final DiscoverListRecyclerViewAdapter.PhotographyViewHolder viewHolder = (DiscoverListRecyclerViewAdapter.PhotographyViewHolder) holder;

            Glide.with(context).load(bean.getPhoto_id()).into(viewHolder.cover);
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
                    presenter.retainNestedScrollViewPosition();
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
        return data.get(position - 1).getType_main();
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

    public void addOnClickListener(DiscoverListRecyclerViewAdapter.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    interface OnItemClickListener {
        void onClick(View view, DiscoverListBean bean, int position);
    }
}
