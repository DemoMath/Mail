package com.email.bkjkmail.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebSettings;
import android.widget.TextView;

import com.email.bkjkmail.R;
import com.email.bkjkmail.bean.MailBean;
import com.email.bkjkmail.widget.StaticWebView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wudi on 2018/1/30.
 * 邮箱详情信息
 */
public class MailDetailActiviy extends MailBaseActivity {
    public static final String MAIL_BEAN = "MAIL_BEAN";
    @BindView(R.id.mail_detail_subject_tv)
    TextView mMailDetailSubjectTv;
    @BindView(R.id.mail_detail_sender_tv)
    TextView mMailDetailSenderTv;
    @BindView(R.id.mail_detail_to_tv)
    TextView mMailDetailToTv;
    @BindView(R.id.mail_detail_cc_tv)
    TextView mMailDetailCcTv;
    @BindView(R.id.mail_detail_bcc_tv)
    TextView mMailDetailBccTv;
    @BindView(R.id.mail_detail_sendtime_tv)
    TextView mMailDetailSendtimeTv;
    @BindView(R.id.mail_detail_content_wv)
    StaticWebView mMailDetailContentWv;
    private MailBean mMailBean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_detail);
        ButterKnife.bind(this);
        mMailBean = (MailBean) getIntent().getSerializableExtra(MAIL_BEAN);

        mMailDetailSubjectTv.setText(mMailBean.getSubject());
        mMailDetailSenderTv.setText(mMailBean.getFrom());
        mMailDetailToTv.setText(mMailBean.getTo());
        mMailDetailCcTv.setText(mMailBean.getCc());
        mMailDetailSendtimeTv.setText(mMailBean.getSentDate());
        String attachPath = mMailBean.getAttachPath();

        WebSettings wSet = mMailDetailContentWv.getSettings();
        wSet.setJavaScriptEnabled(true);
        String bodyText = mMailBean.getBodyText();
        mMailDetailContentWv.loadData(bodyText, "text/html; charset=UTF-8", null);
    }
}
