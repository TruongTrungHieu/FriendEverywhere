package com.fithou.friendeverywhere.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fithou.friendeverywhere.R;
import com.fithou.friendeverywhere.activity.MessageActivity;
import com.fithou.friendeverywhere.object.MessageObject;

import java.util.ArrayList;

/**
 * Created by admin on 24/03/2016.
 */
public class MessageListAdapter extends RecyclerView.Adapter<MessageListAdapter.RecyclerViewHolder> {

    private ArrayList<MessageObject> listData = new ArrayList<>();
    private Context mContext;
    private MessageObject messageObject;

    public MessageListAdapter(ArrayList<MessageObject> listData, Context c) {
        this.listData = listData;
        this.mContext = c;
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup viewGroup,
                                                 int position) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.message_list_item, viewGroup, false);
        return new RecyclerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder viewHolder, int position) {
        MessageObject messageObject = listData.get(position);
        viewHolder.tv_message_list_name.setText(messageObject.getGroupObject().getDisplay_name());
        viewHolder.tv_message_list_content.setText(messageObject.getContent());
        if (messageObject.getSeen() == 1) {
            viewHolder.tv_message_list_name.setTextColor(Color.BLACK);
            viewHolder.tv_message_list_content.setTextColor(Color.BLACK);
            viewHolder.tv_message_list_time.setTextColor(Color.BLACK);
        } else {
            viewHolder.tv_message_list_name.setTextColor(Color.parseColor("#FF797979"));
            viewHolder.tv_message_list_content.setTextColor(Color.parseColor("#FF797979"));
            viewHolder.tv_message_list_time.setTextColor(Color.parseColor("#FF797979"));
        }
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder implements
            View.OnClickListener {

        public View line;
        public TextView tv_message_list_name, tv_message_list_content, tv_message_list_time;
        public ImageView img_message_list_ava;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            line = (View) itemView.findViewById(R.id.line_message_list);
            img_message_list_ava = (ImageView) itemView.findViewById(R.id.iv_mess_list_item);
            tv_message_list_name = (TextView) itemView.findViewById(R.id.tv_name_mess_list_item);
            tv_message_list_content = (TextView) itemView.findViewById(R.id.tv_content_mess_list_item);
            tv_message_list_time = (TextView) itemView.findViewById(R.id.tv_time_mess_list_item);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent mess_activity = new Intent(mContext, MessageActivity.class);
            mess_activity.putExtra("MESSAGE_DETAILS", listData.get(getAdapterPosition()));
            mContext.startActivity(mess_activity);
        }
    }
}
