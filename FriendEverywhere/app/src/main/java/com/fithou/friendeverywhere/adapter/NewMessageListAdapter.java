package com.fithou.friendeverywhere.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.fithou.friendeverywhere.R;
import com.fithou.friendeverywhere.object.UserObject;
import com.fithou.friendeverywhere.ultis.newmessage.UserTempObject;

import java.util.ArrayList;

public class NewMessageListAdapter extends RecyclerView.Adapter<NewMessageListAdapter.RecyclerViewHolder> {

    private ArrayList<UserTempObject> listData = new ArrayList<>();
    private Context mContext;

    public NewMessageListAdapter(ArrayList<UserTempObject> listData) {
        this.listData = listData;
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
        return new RecyclerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder viewHolder, int position) {
        UserTempObject userTempObject = listData.get(position);
        UserObject userObject = userTempObject.getUserObject();
        if (userTempObject.isHeader()) {
            viewHolder.fl_header.setVisibility(View.VISIBLE);
            viewHolder.tv_header.setText(userObject.getFirtCharacterName());
            viewHolder.line_long.setVisibility(View.VISIBLE);
            viewHolder.line_short.setVisibility(View.GONE);
        } else {
            viewHolder.fl_header.setVisibility(View.GONE);
            viewHolder.line_long.setVisibility(View.GONE);
            viewHolder.line_short.setVisibility(View.VISIBLE);
        }
        // TODO: set avatar
        viewHolder.tv_fullname.setText(userObject.getFullname());
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder implements
            View.OnClickListener {

        public FrameLayout fl_header;
        public TextView tv_header;
        public View line_long;
        public View line_short;
        public TextView tv_fullname;
        public ImageView img_avatar;
        public CheckBox ck_choice;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            fl_header = (FrameLayout) itemView.findViewById(R.id.fl_header);
            tv_header = (TextView) itemView.findViewById(R.id.tv_header_title_newmessage);
            line_long = (View) itemView.findViewById(R.id.line_long_newmessage);
            line_short = (View) itemView.findViewById(R.id.line_short_newmessage);
            tv_fullname = (TextView) itemView.findViewById(R.id.tv_fullname_newmessage);
            img_avatar = (ImageView) itemView.findViewById(R.id.img_avatar_newmessage);
            ck_choice = (CheckBox) itemView.findViewById(R.id.checkbox_newmessage);
        }

        @Override
        public void onClick(View v) {
            if (ck_choice.isSelected()) {
                listData.get(getAdapterPosition()).removeSelected();
                ck_choice.setSelected(false);
            } else {
                listData.get(getAdapterPosition()).setIsSelected();
                ck_choice.setSelected(true);
            }
        }
    }
}
