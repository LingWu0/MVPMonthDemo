package com.example.xxsj.haobingli201710262.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xxsj.haobingli201710262.R;
import com.example.xxsj.haobingli201710262.model.bean.DataBean;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * 类的用途：使用MVP实现数据的展示以及购物车
 * 作者：郝兵丽
 * 日期:2017/10/26
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    private Context context;
    private List<DataBean.SongListBean> list;


    public RecyclerViewAdapter(Context context, List<DataBean.SongListBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item, parent, false);

        return new MyViewHolder(inflate);
    }

    //点击事件接口
    public interface OnItemClickListener {
        void onItemClick(View view, String image, String geming, int price);
    }

    private OnItemClickListener mItemClickListener;

    //设置单击事件接口
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mItemClickListener = onItemClickListener;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        DisplayImageOptions build = new DisplayImageOptions.Builder()
                //图片加载过程中显示的图片
                .showStubImage(R.mipmap.ic_launcher)
                //图片加载失败时用的的图片
                .showImageForEmptyUri(R.mipmap.ic_launcher_round)
                //允许内存缓存
                .cacheInMemory()
                //允许磁盘缓存
                .cacheOnDisc()
                .build();

        ImageLoader.getInstance().displayImage(list.get(position).getPic_small(), holder.db_image, build);

        holder.db_geming.setText(list.get(position).getTitle());
        holder.db_renming.setText(list.get(position).getArtist_name());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mItemClickListener != null) {
                    mItemClickListener.onItemClick(v, list.get(position).getPic_small(), list.get(position).getTitle(), list.get(position).getFile_duration());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        //把整个view放到viewHolder中，实现点击条目监听事件
        View itemView;
        private final ImageView db_image;
        private final TextView db_geming;
        private final TextView db_renming;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            db_image = (ImageView) itemView.findViewById(R.id.db_image);
            db_geming = (TextView) itemView.findViewById(R.id.db_geming);
            db_renming = (TextView) itemView.findViewById(R.id.db_renming);
        }
    }

}
