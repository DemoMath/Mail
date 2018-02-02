package com.email.bkjkmail.mail;

import android.accounts.Account;
import android.content.Context;

import com.email.bkjkmail.MailApplication;
import com.email.bkjkmail.bean.MailAccount;
import com.email.bkjkmail.bean.MailBox;
import com.email.bkjkmail.bean.MailBean;
import com.email.bkjkmail.db.DaoSession;
import com.email.bkjkmail.db.MailAccountDao;
import com.email.bkjkmail.db.MailInfoDao;
import com.email.bkjkmail.inter.MailPullListener;
import com.email.bkjkmail.utils.MailLogger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimePart;

/**
 * Created by wudi on 2018/1/29.
 * 邮箱操作类
 */
public class MailOptions {

    private static ExecutorService sExecutorService;

    private static ExecutorService getExecutorService () {
        if (sExecutorService == null) {
            synchronized (Object.class) {
                if (sExecutorService == null) {
                    sExecutorService = Executors.newCachedThreadPool();
                }
            }
        }
        return sExecutorService;
    }

    /**
     * 登录1
     * @param store 仓库
     */
    public static void login (final Store store){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    store.connect(MailConfig.sMailAccount.getAccount(), MailConfig.sMailAccount.getPassword());
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            }
        };
        getExecutorService().execute(runnable);
    }

    /**
     * 登录2
     * @param store 仓库
     * @param username 账号
     * @param password 密码
     */
    public static void login (final Store store, final String username, final String password){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    store.connect(username, password);
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            }
        };
        getExecutorService().execute(runnable);
    }


    /**
     * 获取收件箱邮件总数
     * @param store
     * @return
     */
    public static int getInboxCount(final Store store) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    Folder inbox = store.getFolder("INBOX");
                    int messageCount = inbox.getMessageCount();
                    MailLogger.loge("收件箱共有邮件："+messageCount);
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            }
        };
        getExecutorService().execute(runnable);
        return 0;
    }

    /**
     * 链接服务器
     * @param store 仓库
     * @throws MessagingException
     */
    public static void connect (final Store store){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    store.connect(MailConfig.sMailAccount.getAccount(), MailConfig.sMailAccount.getPassword());
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            }
        };
        getExecutorService().execute(runnable);
    }

    /**
     * 断开收件服务器链接
     * @param store 仓库
     * @throws MessagingException
     */
    public static void close (final Store store){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    if (store.isConnected()) {
                        store.close();
                    }
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            }
        };
        getExecutorService().execute(runnable);
    }

    /**
     * 断开发送服务器链接
     * @param transport 运输
     * @throws MessagingException
     */
    public static void close (final Transport transport){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    if (transport.isConnected()) {
                        transport.close();
                    }
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            }
        };
        getExecutorService().execute(runnable);
    }

    /**
     * 拉去普通邮件
     * @param store
     * @param listener
     */
    public static void pullMails (final Context context, final Store store , final MailPullListener listener){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                //从本地数据库中读取邮件
                getMailsFromDB (context,MailConfig.sMailAccount,listener);



                if (store.isConnected()) {
                    MailBox mailBox = new MailBox();
                    try {
                        int pageNo = 1;
                        int pageSize = 10;

                        Folder folder = store.getFolder("INBOX");
                        folder.open(Folder.READ_WRITE);
                        //开始解析邮箱信息
                        mailBox.setUnReadCount(folder.getUnreadMessageCount());
                        mailBox.setDeleteCount(folder.getDeletedMessageCount());
                        mailBox.setNewCount(folder.getNewMessageCount());
                        mailBox.setTotalCount(folder.getMessageCount());
                        mailBox.setPageNo(pageNo);
                        mailBox.setPageSize(pageSize);
                        int totalPage = 0;
                        //注释三（代码下面有注释解释）
                        if (folder.getMessageCount() % pageSize > 0){
                            totalPage = folder.getMessageCount() / pageSize + 1;
                        } else {
                            totalPage = folder.getMessageCount() / pageSize;
                        }
                        mailBox.setPageTotal(totalPage);
                        List<MailBean> mails = new ArrayList<>();
                        Message[] messages = null;
                        int totalCount = folder.getMessageCount();
                        //注释四（代码下面有注释解释）
                        if ((totalCount - pageNo * pageSize) > 0){
                            messages = folder.getMessages(totalCount - pageNo * pageSize+1,totalCount - ( pageNo - 1 ) * pageSize);
                        } else {
                            messages = folder.getMessages(1,totalCount - ( pageNo - 1) * pageSize);
                        }
                        //注释五（代码下面有注释解释）
                        for(int i = messages.length - 1 ; i >= 0; i --){
                            MailScreen mailScreen = new MailScreen((MimeMessage) messages[i]);
                            //主题
                            String subject = mailScreen.getSubject();
                            //发送时间
                            String sentDate = mailScreen.getSentDate();
                            //发件人
                            String from = mailScreen.getFrom();
                            //收件人地址
                            String to = mailScreen.getMailAddress("TO");
                            //抄送人地址
                            String cc = mailScreen.getMailAddress("CC");
                            //密送人地址
                            String bcc = mailScreen.getMailAddress("BCC");
                            //解析并获取内容
                            mailScreen.getMailContent((MimePart) messages[i]);
                            String bodyText = mailScreen.getBodyText();
                            //附件
                            String attachPath = mailScreen.getAttachPath();

                            MailBean mailBean = new MailBean();
                            mailBean.setNum( pageSize - i );
                            mailBean.setSubject(subject);
                            mailBean.setSentDate(sentDate);
                            mailBean.setFrom(from);
                            mailBean.setTo(to);
                            mailBean.setCc(cc);
                            mailBean.setBcc(bcc);
                            mailBean.setBodyText(bodyText);
                            mailBean.setAttachPath(attachPath);
                            mails.add(mailBean);
                        }
                        mailBox.setMessages(mails);
                        listener.onSuccess(mailBox);
                    } catch (MessagingException e) {
                        e.printStackTrace();
                    }
                }




            }
        };
        getExecutorService().execute(runnable);
    }

    private static void getMailsFromDB(Context context, MailAccount mailAccount, MailPullListener listener) {
        MailInfoDao mailInfoDao =  MailApplication.getInstance().getDaoSession().getMailInfoDao();
        MailAccountDao mailAccountDao = MailApplication.getInstance().getDaoSession().getMailAccountDao();

        mailAccountDao.getKey(mailAccount);
    }
}
