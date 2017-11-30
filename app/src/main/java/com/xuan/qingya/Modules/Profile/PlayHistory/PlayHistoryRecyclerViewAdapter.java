package com.xuan.qingya.Modules.Profile.PlayHistory;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xuan.qingya.Models.entity.Music;
import com.xuan.qingya.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhouzhixuan on 2017/9/7.
 */

public class PlayHistoryRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int FOOTER_VIEW = -1;
    private final int ITEM_VIEW = 0;
    private Context context;
    private List<Music> data;
    private OnItemClickListener onItemClickListener;

    public PlayHistoryRecyclerViewAdapter(Context context) {
        this.context = context;
        this.data = new ArrayList<>();
    }

    public void setData(List<Music> data) {
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
                return new FooterViewHolder(view);
            case ITEM_VIEW:
                view = layoutInflater.inflate(R.layout.layout_item_play_list, parent, false);
                return new ItemViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof FooterViewHolder) {
            return;
        }

        final Music bean = data.get(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickListener != null) {
                    onItemClickListener.onClick(bean, position);
                }
            }
        });

        final ItemViewHolder viewHolder = (ItemViewHolder) holder;

        if (position == data.size() - 1) {
            viewHolder.divider.setVisibility(View.INVISIBLE);
        }

        /*viewHolder.author.setText(bean.getAuthor());
        viewHolder.time.setText(bean.getTime());
        viewHolder.title.setText(bean.getTitle());*/

    }

    @Override
    public int getItemViewType(int position) {
        if (position == data.size()) {
            return FOOTER_VIEW;
        }
        return ITEM_VIEW;
    }

    @Override
    public int getItemCount() {
        return data.size() + 1;
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView author;
        TextView time;
        ImageView status;
        ImageView divider;

        public ItemViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            author = itemView.findViewById(R.id.author);
            time = itemView.findViewById(R.id.length);
            status = itemView.findViewById(R.id.music_status);
            divider = itemView.findViewById(R.id.divider);
        }
    }

    class FooterViewHolder extends RecyclerView.ViewHolder {
        public FooterViewHolder(View itemView) {
            super(itemView);
        }
    }

    public void addOnClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    interface OnItemClickListener {
        void onClick(Music bean, int position);
    }
}
