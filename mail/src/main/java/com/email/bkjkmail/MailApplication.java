package com.email.bkjkmail;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.email.bkjkmail.db.DaoMaster;
import com.email.bkjkmail.db.DaoSession;
import com.idescout.sql.SqlScoutServer;

/**
 * Created by wudi on 2018/2/1.
 */

public class MailApplication extends Application {

    public static MailApplication sMailApplication;

    private DaoSession mDaoSession;


    public static MailApplication getInstance() {
        return sMailApplication;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        sMailApplication = this;
        SqlScoutServer.create(this, getPackageName());
        initGreenDao();
    }

    private void initGreenDao() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "mail.db");
        SQLiteDatabase writableDatabase = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(writableDatabase);
        mDaoSession = daoMaster.newSession();
    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }
}


