package com.yiqikeji.lostpersonproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yiqikeji.lostpersonproject.bean.SearchBean;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class SearchAdapter extends BaseAdapter {

    private ArrayList<SearchBean.ResultBean> list;
    private Context context;
    private String score;

    public SearchAdapter(String score,ArrayList<SearchBean.ResultBean> list, Context context) {
        this.list = list;
        this.context = context;
        this.score = score;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Holder holder = null;
        if (convertView == null) {
            holder = new Holder();
            convertView = LayoutInflater.from(context).inflate(com.yiqikeji.lostpersonproject.R.layout.item_search, parent, false);
            holder.iv_head = (ImageView) convertView.findViewById(com.yiqikeji.lostpersonproject.R.id.iv_head);
            holder.tv_name = (TextView) convertView.findViewById(com.yiqikeji.lostpersonproject.R.id.tv_name);
            holder.tv_like = (TextView) convertView.findViewById(com.yiqikeji.lostpersonproject.R.id.tv_like);
            holder.ll_item = (LinearLayout) convertView.findViewById(com.yiqikeji.lostpersonproject.R.id.ll_item);

            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        holder.tv_name.setText("" + list.get(position).getName());
        if (Double.parseDouble(list.get(position).getScore())>Double.parseDouble(score)){
            holder.tv_like.setTextColor(context.getResources().getColor(com.yiqikeji.lostpersonproject.R.color.text2));
        }else {
            holder.tv_like.setTextColor(context.getResources().getColor(com.yiqikeji.lostpersonproject.R.color.bg_text));
        }
        holder.tv_like.setText("" + list.get(position).getScore()+"%");

        Glide.with(context).load(list.get(position).getPortrait()).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(holder.iv_head);

        if (position / 2 % 2 == 0) {
            holder.ll_item.setBackgroundColor(context.getResources().getColor(com.yiqikeji.lostpersonproject.R.color.white));
        }else{
            holder.ll_item.setBackgroundColor(context.getResources().getColor(com.yiqikeji.lostpersonproject.R.color.hint2));
        }
        return convertView;
    }

    public class Holder {
        public ImageView iv_head;
        public TextView tv_name, tv_like;
        public LinearLayout ll_item;
    }


}

