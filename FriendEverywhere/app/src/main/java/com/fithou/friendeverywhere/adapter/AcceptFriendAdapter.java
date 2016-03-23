package com.fithou.friendeverywhere.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.fithou.friendeverywhere.R;
import com.fithou.friendeverywhere.object.FriendObject;
import com.fithou.friendeverywhere.ultis.FileUtils;

import java.util.ArrayList;

public class AcceptFriendAdapter extends RecyclerView.Adapter<AcceptFriendAdapter.RecyclerViewHolder> {

    private ArrayList<FriendObject> listData = new ArrayList<>();

    public AcceptFriendAdapter(ArrayList<FriendObject> listData) {
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
        View itemView = inflater.inflate(R.layout.item_accept_friend, viewGroup, false);
        return new RecyclerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder viewHolder, int position) {
        viewHolder.tv_fullname.setText(listData.get(position).getFriend_object().getFullname());
        if (listData.get(position).getFriend_object().getPhoto() != null && listData.get(position).getFriend_object().getPhoto().length() > 0) {
            byte[] bytes = FileUtils.decodeImage(listData.get(position).getFriend_object().getPhoto());
            Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            viewHolder.img_avatar.setImageBitmap(bmp);
        }
    }

    public void addItem(int position, FriendObject data) {
        listData.add(position, data);
        notifyItemInserted(position);
    }

    public void removeItem(int position) {
        listData.remove(position);
        notifyItemRemoved(position);
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder implements
            View.OnClickListener {

        public ImageView img_avatar;
        public TextView tv_fullname;
        public Button btn_confirm;
        public Button btn_delete;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            img_avatar = (ImageView) itemView.findViewById(R.id.img_avatar);
            tv_fullname = (TextView) itemView.findViewById(R.id.tv_fullname);
            btn_confirm = (Button) itemView.findViewById(R.id.btn_confirm);
            btn_delete = (Button) itemView.findViewById(R.id.btn_delete);

            btn_delete.setOnClickListener(this);
            img_avatar.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int id = v.getId();
            switch (id) {
                case R.id.img_avatar:
                    break;
                case R.id.btn_delete:
                    removeItem(getAdapterPosition());
                    break;
                default:
                    break;
            }
        }
    }

}