package com.email.bkjkmail.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.email.bkjkmail.mail.MailBuilder;

import javax.mail.Store;
import javax.mail.Transport;

/**
 * Created by wudi on 2018/1/30.
 * 基类
 */
@SuppressLint("Registered")
public class MailBaseActivity extends AppCompatActivity {

    protected Store mStore;
    protected Transport mTransport;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mStore = MailBuilder.getStore();
        mTransport = MailBuilder.getTransport();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
