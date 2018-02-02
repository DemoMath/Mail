package com.email.bkjkmail.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wudi on 17-3-15.
 * 数据适配器的基类
 * @param <M> 数据的泛型
 * @param <H> ViewHolder的泛型
 */
public abstract class MailBaseRlvAdapter<M, H extends MailBaseRlvHolder> extends RecyclerView.Adapter<H>{

    //数据
    protected List<M> mDatas;

    //布局打气筒
    protected LayoutInflater mInflater;

    //上下文对象
    protected Context mContext;

    /**
     * 构造方法
     * @param context 传入上下文，不能为空
     * @param datas 传入数据，不能为空
     */
    public MailBaseRlvAdapter(@NonNull Context context, @NonNull List<M> datas) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        if (this.mDatas == null) {
            this.mDatas = new ArrayList<>();
        }
        this.mDatas = datas;
    }

    @Override
    public abstract void onBindViewHolder(final H holder, int position) ;

    @Override
    public abstract H onCreateViewHolder(ViewGroup parent, int viewType);

    @Override
    public int getItemCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    /**
     * 设置数据，用于数据更新
     * @param datas 传入的数据
     */
    public void setData(List<M> datas) {
//        if (mDatas == null) {
        this.mDatas = datas;
//        } else {
//            mDatas.clear();
//            mDatas.addAll(datas);
//        }
    }

    /**
     * 清除某一条数据，用于数据删除
     * @param position 数据的位置
     */
    public void removeData(int position) {
        mDatas.remove(position);
        notifyItemRemoved(position);
    }
}