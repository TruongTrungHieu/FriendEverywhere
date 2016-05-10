package com.fithou.friendeverywhere.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fithou.friendeverywhere.R;
import com.fithou.friendeverywhere.object.UserObject;
import com.fithou.friendeverywhere.ultis.Commons;
import com.fithou.friendeverywhere.ultis.StringSupport;
import com.fithou.friendeverywhere.ultis.newmessage.UserTempObject;

import java.util.ArrayList;

public class NewMessageListAdapter extends RecyclerView.Adapter<NewMessageListAdapter.RecyclerViewHolder> {

    private ArrayList<UserTempObject> listData = new ArrayList<>();
    private Context mContext;
    private Commons.OnRecycleItemClickListener_NewMessage onRecycleItemClickListener_NewMessage;

    public NewMessageListAdapter(ArrayList<UserTempObject> listData, Commons.OnRecycleItemClickListener_NewMessage onRecycleItemClickListener_newMessage) {
        this.listData = listData;
        this.onRecycleItemClickListener_NewMessage = onRecycleItemClickListener_newMessage;
    }

    public void setFilter(ArrayList<UserTempObject> userModels) {
        listData = new ArrayList<>();
        listData.addAll(userModels);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup viewGroup,
                                                 int position) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.item_new_message, viewGroup, false);
        return new RecyclerViewHolder(itemView, new RecyclerViewHolder.RecycleViewHolderClickListener() {
            @Override
            public void onItemClick(View caller, int position) {
                onRecycleItemClickListener_NewMessage.onRecycleItemClicked(caller, position);
            }
        });
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder viewHolder, int position) {
        UserTempObject userTempObject = listData.get(position);
        UserObject userObject = userTempObject.getUserObject();
        if (userTempObject.isHeader()) {
            viewHolder.fl_header.setVisibility(View.VISIBLE);
            viewHolder.tv_header.setText(StringSupport.getFirstCharacterName(userObject.getFullname()));
            viewHolder.line_long.setVisibility(View.VISIBLE);
            viewHolder.line_short.setVisibility(View.GONE);
        } else {
            viewHolder.fl_header.setVisibility(View.GONE);
            viewHolder.line_long.setVisibility(View.GONE);
            viewHolder.line_short.setVisibility(View.VISIBLE);
        }
        viewHolder.ck_choice.setChecked(userTempObject.isSelected());

        // TODO: set avatar
        viewHolder.tv_fullname.setText(userObject.getFullname());
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder implements
            View.OnClickListener {

        public FrameLayout fl_header;
        public TextView tv_header;
        public View line_long;
        public View line_short;
        public TextView tv_fullname;
        public ImageView img_avatar;
        public CheckBox ck_choice;
        public RelativeLayout rl_item;

        public interface RecycleViewHolderClickListener {
            void onItemClick(View caller, int position);
        }

        public RecycleViewHolderClickListener mListener;

        public RecyclerViewHolder(View itemView, RecycleViewHolderClickListener listener) {
            super(itemView);
            mListener = listener;
            fl_header = (FrameLayout) itemView.findViewById(R.id.fl_header);
            tv_header = (TextView) itemView.findViewById(R.id.tv_header_title_newmessage);
            line_long = (View) itemView.findViewById(R.id.line_long_newmessage);
            line_short = (View) itemView.findViewById(R.id.line_short_newmessage);
            tv_fullname = (TextView) itemView.findViewById(R.id.tv_fullname_newmessage);
            img_avatar = (ImageView) itemView.findViewById(R.id.img_avatar_newmessage);
            ck_choice = (CheckBox) itemView.findViewById(R.id.checkbox_newmessage);
            rl_item = (RelativeLayout) itemView.findViewById(R.id.rl_new_message_main);

            rl_item.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.rl_new_message_main) {
                mListener.onItemClick(v, getAdapterPosition());
            }

        }
    }
}
