package com.xuan.qingya.Modules.Search;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.xuan.qingya.Models.Entity.SearchHistoryBean;
import com.xuan.qingya.R;

import java.util.List;

/**
 * Created by zhouzhixuan on 2017/9/12.
 */

public class SearchHistoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private SearchContract.SearchPresenter presenter;
    private List<SearchHistoryBean> data;
    private SearchHistoryAdapter.OnItemClickListener onItemClickListener;
    private Context context;

    interface OnItemClickListener {
        void onClick(View view, SearchHistoryBean bean, int position);
    }

    public void addOnClickListener(SearchHistoryAdapter.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public SearchHistoryAdapter(Context context, List<SearchHistoryBean> data, SearchContract.SearchPresenter presenter) {
        this.context = context;
        this.data = data;
        this.presenter = presenter;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item_search, parent, false);
        return new MainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final MainViewHolder viewHolder = (MainViewHolder) holder;
        final SearchHistoryBean bean = data.get(position);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onSearchHistoryItemClick(view, bean, viewHolder.content.getText().toString());
            }
        });

        viewHolder.fillTopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.fillEditTextWithHistoryContent(viewHolder.content.getText().toString());
            }
        });

        viewHolder.content.setText(bean.getContent());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MainViewHolder extends RecyclerView.ViewHolder {

        ImageButton historyIcon;
        ImageButton fillTopButton;
        TextView content;

        public MainViewHolder(View itemView) {
            super(itemView);

            historyIcon = itemView.findViewById(R.id.history_icon);
            fillTopButton = itemView.findViewById(R.id.fill_icon);
            content = itemView.findViewById(R.id.content);
        }
    }
}
