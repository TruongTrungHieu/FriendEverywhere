package com.fithou.friendeverywhere.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.fithou.friendeverywhere.R;
import com.fithou.friendeverywhere.asynctask.ConfirmFriendAsyncTask;
import com.fithou.friendeverywhere.object.FriendObject;
import com.fithou.friendeverywhere.ultis.Callback;
import com.fithou.friendeverywhere.ultis.Constants;
import com.fithou.friendeverywhere.ultis.FileUtils;

import org.json.JSONObject;

import java.util.ArrayList;

public class AcceptFriendAdapter extends RecyclerView.Adapter<AcceptFriendAdapter.RecyclerViewHolder> {

    private Context context;
    private ArrayList<FriendObject> listData = new ArrayList<>();

    public AcceptFriendAdapter(Context context, ArrayList<FriendObject> listData) {
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
            btn_confirm.setOnClickListener(this);
            img_avatar.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int id = v.getId();
            switch (id) {
                case R.id.img_avatar:
                    break;
                case R.id.btn_delete:
                    confirm_server(true, listData.get(getAdapterPosition()).getFriend_object().getUser_id(), getAdapterPosition());
                    break;
                case R.id.btn_confirm:
                    confirm_server(false, listData.get(getAdapterPosition()).getFriend_object().getUser_id(), getAdapterPosition());
                    break;
                default:
                    break;
            }
        }
    }

    private void confirm_server(boolean isDeleted, String friend_id, final int pos) {
        new ConfirmFriendAsyncTask(context).setCallback(new Callback() {
            @Override
            public void onPreExecute() {

            }

            @Override
            public void onPostExecute(Object o) {
                final JSONObject jsonObject = (JSONObject) o;
                if (jsonObject != null) {
                    try {
                        removeItem(pos);
                    } catch (Exception e) {
                        Log.e("confirm_server", e.getMessage());
                    }
                }
            }
        }).execute(Constants.getPreference(context, Constants.XML_USER_ID), friend_id, isDeleted ? "0" : "2");
    }

}
