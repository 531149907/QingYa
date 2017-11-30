package com.xuan.qingya.Modules.Profile.Notification.List;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xuan.qingya.Models.entity.Message;
import com.xuan.qingya.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhouzhixuan on 2017/9/4.
 */

public class NotificationRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int FOOTER_VIEW = -1;
    private final int ITEM_VIEW = 0;
    private Context context;
    private List<Message> data;
    private OnItemClickListener onItemClickListener;

    public NotificationRecyclerViewAdapter(Context context) {
        this.context = context;
        this.data = new ArrayList<>();
    }

    public void setData(List<Message> data) {
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
                return new NotificationRecyclerViewAdapter.FooterViewHolder(view);
            case ITEM_VIEW:
                view = layoutInflater.inflate(R.layout.layout_item_notification, parent, false);
                return new NotificationViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof NotificationRecyclerViewAdapter.FooterViewHolder) {
            return;
        }

        final Message bean = data.get(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickListener != null) {
                    onItemClickListener.onClick(bean, position);
                }
            }
        });

        final NotificationRecyclerViewAdapter.NotificationViewHolder viewHolder = (NotificationRecyclerViewAdapter.NotificationViewHolder) holder;

        viewHolder.title.setText(bean.getTitle());
        viewHolder.date.setText(bean.getDate());
        if (position == data.size() - 1) {
            viewHolder.divider.setVisibility(View.INVISIBLE);
        }
        viewHolder.content.setText(bean.getContent());

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

    class NotificationViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView date;
        TextView content;
        ImageView divider;

        public NotificationViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            date = itemView.findViewById(R.id.time);
            content = itemView.findViewById(R.id.content);
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
        void onClick(Message bean, int position);
    }
}
