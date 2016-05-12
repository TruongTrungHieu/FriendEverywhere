package com.fithou.friendeverywhere.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fithou.friendeverywhere.R;
import com.fithou.friendeverywhere.activity.FriendProfileActivity;
import com.fithou.friendeverywhere.object.FriendObject;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;

public class FindFriendAdapter extends RecyclerView.Adapter<FindFriendAdapter.RecyclerViewHolder> {

    private ArrayList<FriendObject> listData = new ArrayList<>();
    private Context context;

    public FindFriendAdapter(Context context, ArrayList<FriendObject> listData) {
        this.context = context;
        this.listData = listData;
    }

    public void updateList(ArrayList<FriendObject> data) {
        listData = data;
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
        View itemView = inflater.inflate(R.layout.item_find_friend, viewGroup, false);
        return new RecyclerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder viewHolder, int position) {
        final FriendObject friendObject = listData.get(position);
        viewHolder.tv_fullname.setText(friendObject.getFriend_object().getFullname());
        viewHolder.rl_main_ff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent profile = new Intent(context, FriendProfileActivity.class);
                profile.putExtra("FRIEND", friendObject);
                context.startActivity(profile);
            }
        });
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder implements
            View.OnClickListener {

        public RoundedImageView img_avatar;
        public TextView tv_fullname;
        public RelativeLayout rl_main_ff;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            rl_main_ff = (RelativeLayout) itemView.findViewById(R.id.rl_main_ff);
            img_avatar = (RoundedImageView) itemView.findViewById(R.id.rimg_avatar_ff);
            tv_fullname = (TextView) itemView.findViewById(R.id.tv_fullname_ff);
        }

        @Override
        public void onClick(View v) {

        }
    }

}

