package com.android.lostpersonproject.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.android.lostpersonproject.R;
import com.android.lostpersonproject.bean.PersonDetailBean;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

public class GalleryAdapter extends
        RecyclerView.Adapter<GalleryAdapter.ViewHolder>
{
    private LayoutInflater mInflater;
    private ArrayList<PersonDetailBean.PhotosBean> mDatas;
    private Context context;


    public GalleryAdapter(Context context, ArrayList<PersonDetailBean.PhotosBean> datats)
    {
        mInflater = LayoutInflater.from(context);
        mDatas = datats;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public ViewHolder(View arg0)
        {
            super(arg0);
        }

        ImageView mImg;
    }

    @Override
    public int getItemCount()
    {
        return mDatas.size();
    }

    /**
     * 创建ViewHolder
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i)
    {
        View view = mInflater.inflate(R.layout.item_person_detail,
                viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);

        viewHolder.mImg = (ImageView) view
                .findViewById(R.id.iv_head);
        return viewHolder;
    }

    /**
     * 设置值
     */
    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i)
    {

        Glide.with(context) .load(mDatas.get(i).getPortrait()) .apply(RequestOptions.bitmapTransform(new CircleCrop())) .into( viewHolder.mImg);

        viewHolder.mImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    onItemClick.setOnItemClick(mDatas.get(i)+"", i);
            }
        });

    }


    OnItemClick onItemClick;

    public interface OnItemClick {
        void setOnItemClick(String id, int position);
    }

    public void setOnItemClick( OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

}

