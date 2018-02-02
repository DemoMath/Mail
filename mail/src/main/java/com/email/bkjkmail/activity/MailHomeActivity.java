package com.email.bkjkmail.activity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.email.bkjkmail.R;
import com.email.bkjkmail.adapter.MailListRlvAdapter;
import com.email.bkjkmail.bean.MailBean;
import com.email.bkjkmail.bean.MailBox;
import com.email.bkjkmail.inter.MailPullListener;
import com.email.bkjkmail.mail.MailOptions;

import javax.mail.event.ConnectionEvent;
import javax.mail.event.ConnectionListener;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wudi on 2018/1/30.
 * 主页
 */
public class MailHomeActivity extends MailBaseActivity implements ConnectionListener {

    @BindView(R.id.mail_mail_list_rlv)
    RecyclerView mMailMailListRlv;

    private MailListRlvAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mail_home);
        ButterKnife.bind(this);

        mMailMailListRlv.setLayoutManager(new LinearLayoutManager(this));


        mStore.addConnectionListener(this);

        MailOptions.getInboxCount(mStore);

        MailOptions.pullMails(this,mStore, new MailPullListener() {
            @Override
            public void onSuccess(final MailBox mailBox) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter = new MailListRlvAdapter(MailHomeActivity.this,mailBox.getMessages());
                        mAdapter.setOnItemClickListener(new MailListRlvAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(MailBean mailBean) {
                                Intent intent = new Intent(MailHomeActivity.this,MailDetailActiviy.class);
                                intent.putExtra(MailDetailActiviy.MAIL_BEAN,mailBean);
                                startActivity(intent);

                            }
                        });
                        mMailMailListRlv.setAdapter(mAdapter);
                    }
                });
            }
        });
    }

    @Override
    public void opened(ConnectionEvent e) {

    }

    @Override
    public void disconnected(ConnectionEvent e) {

    }

    @Override
    public void closed(ConnectionEvent e) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MailOptions.close(mStore);
        MailOptions.close(mTransport);
    }
}
