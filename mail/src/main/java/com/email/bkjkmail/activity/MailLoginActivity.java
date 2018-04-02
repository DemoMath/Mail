package com.email.bkjkmail.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import com.email.bkjkmail.MailApplication;
import com.email.bkjkmail.R;
import com.email.bkjkmail.bean.MailAccount;
import com.email.bkjkmail.bean.MailInfo;
import com.email.bkjkmail.db.DaoSession;
import com.email.bkjkmail.db.MailAccountDao;
import com.email.bkjkmail.db.MailInfoDao;
import com.email.bkjkmail.mail.MailConfig;
import com.email.bkjkmail.mail.MailOptions;
import com.email.bkjkmail.utils.MailLogger;

import javax.mail.event.ConnectionEvent;
import javax.mail.event.ConnectionListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by wudi on 2018/1/29.
 * 登录页面
 */
public class MailLoginActivity extends MailBaseActivity implements ConnectionListener {

    @BindView(R.id.mail_username_et)
    EditText mMailUsernameEt;

    @BindView(R.id.mail_password_et)
    EditText mMailPasswordEt;

    @BindView(R.id.mail_login_btn)
    Button mMailLoginBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_login);
        ButterKnife.bind(this);
        mStore.addConnectionListener(this);


        MailInfoDao mailInfoDao =  MailApplication.getInstance().getDaoSession().getMailInfoDao();
        MailAccountDao mailAccountDao = MailApplication.getInstance().getDaoSession().getMailAccountDao();

        DaoSession daoSession = MailApplication.getInstance().getDaoSession();

        MailAccount mailAccount = new MailAccount();
        mailAccount.setId(5L);
        mailAccount.setAccount("di.wu@bkjk.com");
        mailAccount.setUuid("111");
        mailAccount.setPassword("123");
        for (int i = 10 ; i < 20 ; i ++) {
            MailInfo mailInfo = new MailInfo();
            mailInfo.setId((long) i);
            mailInfo.setSubject("主题"+i);
            mailInfoDao.insertOrReplace(mailInfo);
        }
        mailAccountDao.insertOrReplace(mailAccount);
    }

    @OnClick(R.id.mail_login_btn)
    public void onViewClicked() {
        final String mailUserName = mMailUsernameEt.getText().toString().trim();
        final String mailPassword = mMailPasswordEt.getText().toString().trim();
        if (TextUtils.isEmpty(mailUserName) || TextUtils.isEmpty(mailPassword)) {
            return;
        }
        MailAccount mailAccount = new MailAccount();
        mailAccount.setAccount(mailUserName);
        mailAccount.setPassword(mailPassword);
        MailConfig.sMailAccount = mailAccount;
        MailOptions.login(mStore);
    }

    @Override
    public void opened(ConnectionEvent e) {
        MailLogger.loge( "连接成功");

        MailAccountDao mailAccountDao = MailApplication.getInstance().getDaoSession().getMailAccountDao();
        mailAccountDao.insertOrReplace(MailConfig.sMailAccount);

        Intent intent = new Intent(MailLoginActivity.this,MailHomeActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void disconnected(ConnectionEvent e) {
        MailLogger.loge( "连接失败+" + e.toString());
    }

    @Override
    public void closed(ConnectionEvent e) {
        MailLogger.loge( "断开连接");
    }
}
