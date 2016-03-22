package com.fithou.friendeverywhere.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.fithou.friendeverywhere.R;
import com.fithou.friendeverywhere.activity.LoginActivity;
import com.fithou.friendeverywhere.activity.RegisterActivity;
import com.fithou.friendeverywhere.object.CountryObject;

import java.util.ArrayList;

public class CountryAdapter extends  RecyclerView.Adapter<CountryAdapter.RecyclerViewHolder> {

    private ArrayList<CountryObject> listData = new ArrayList<>();
    private Context mContext;
    private boolean isRegister;

    public CountryAdapter(ArrayList<CountryObject> listData, Context c, boolean isRegister) {
        this.listData = listData;
        this.mContext = c;
        this.isRegister = isRegister;
    }

    public void setFilter(ArrayList<CountryObject> countryModels) {
        listData = new ArrayList<>();
        listData.addAll(countryModels);
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
        View itemView = inflater.inflate(R.layout.item_country, viewGroup, false);
        return new RecyclerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder viewHolder, int position) {
        CountryObject countryObject = listData.get(position);
        viewHolder.tv_country_name.setText(countryObject.getName());
        viewHolder.tv_dial_code.setText(countryObject.getDial_code());
        if (position == listData.size() - 1) {
            viewHolder.line.setVisibility(View.GONE);
        }
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder implements
            View.OnClickListener {

        public View line;
        public TextView tv_country_name;
        public TextView tv_dial_code;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            line = (View)itemView.findViewById(R.id.line_country);
            tv_country_name = (TextView)itemView.findViewById(R.id.tv_country_name);
            tv_dial_code = (TextView)itemView.findViewById(R.id.tv_dial_code);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (isRegister) {
                Intent register = new Intent(mContext, RegisterActivity.class);
                register.putExtra("COUNTRY", listData.get(getAdapterPosition()));
                mContext.startActivity(register);
            } else {
                Intent login = new Intent(mContext, LoginActivity.class);
                login.putExtra("COUNTRY", listData.get(getAdapterPosition()));
                mContext.startActivity(login);
            }
        }
    }

}
