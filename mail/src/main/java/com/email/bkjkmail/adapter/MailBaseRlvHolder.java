package com.email.bkjkmail.adapter;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by wudi on 17-3-15.
 *
 * 基础的ViewHolder
 * Created by wudi on 2016/5/17.
 */
public abstract class MailBaseRlvHolder extends RecyclerView.ViewHolder{

    public MailBaseRlvHolder(ViewGroup parent, @LayoutRes int resId) {
        super(LayoutInflater.from(parent.getContext()).inflate(resId, parent, false));
    }

    /**
     * 获取布局中的View
     * @param viewId view的Id
     * @param <T> View的类型
     * @return view
     */
    public <T extends View>T getView(@IdRes int viewId){
        return (T) (itemView.findViewById(viewId));
    }

    /**
     * 获取Context实例
     * @return context
     */
    public Context getContext() {
        return itemView.getContext();
    }
}
