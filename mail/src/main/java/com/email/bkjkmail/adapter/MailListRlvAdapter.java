package com.email.bkjkmail.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.email.bkjkmail.R;
import com.email.bkjkmail.bean.MailBean;

import java.util.List;

/**
 * Created by wudi on 2018/1/30.
 */
public class MailListRlvAdapter extends MailBaseRlvAdapter<MailBean,MailListRlvAdapter.MailListRlvHolder> {

    /**
     * 构造方法
     *
     * @param context 传入上下文，不能为空
     * @param datas   传入数据，不能为空
     */
    public MailListRlvAdapter(@NonNull Context context, @NonNull List<MailBean> datas) {
        super(context, datas);
    }

    @Override
    public void onBindViewHolder(MailListRlvHolder holder, int position) {
        final MailBean mailBean = mDatas.get(position);

        LinearLayout layoutLl = holder.getView(R.id.mail_item_layout_ll);
        TextView fromAddressTv = holder.getView(R.id.mail_from_address_tv);
        TextView subjectTv = holder.getView(R.id.mail_subject_tv);
        TextView contentTv = holder.getView(R.id.mail_content_tv);
        TextView sendTimeTv = holder.getView(R.id.mail_send_time_tv);

        String from = mailBean.getFrom();
        String subject = mailBean.getSubject();
        String sentDate = mailBean.getSentDate();

        subjectTv.setText(subject);
        fromAddressTv.setText(from);
        sendTimeTv.setText(sentDate);

        layoutLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(mailBean);
                }
            }
        });
    }

    @Override
    public MailListRlvHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MailListRlvHolder(parent, R.layout.item_mail_list);
    }

    public static class MailListRlvHolder extends MailBaseRlvHolder {
        public MailListRlvHolder(ViewGroup parent, int resId) {
            super(parent, resId);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(MailBean mailBean);
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }
}
