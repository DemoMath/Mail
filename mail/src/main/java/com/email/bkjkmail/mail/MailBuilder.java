package com.email.bkjkmail.mail;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;

import static com.email.bkjkmail.mail.MailConfig.FALLBACK;
import static com.email.bkjkmail.mail.MailConfig.IMAP_HOST;
import static com.email.bkjkmail.mail.MailConfig.IMAP_PORT;
import static com.email.bkjkmail.mail.MailConfig.RECEIVE_PROTOCOL;
import static com.email.bkjkmail.mail.MailConfig.SMTP_AUTH;
import static com.email.bkjkmail.mail.MailConfig.SMTP_HOST;
import static com.email.bkjkmail.mail.MailConfig.SMTP_PORT;
import static com.email.bkjkmail.mail.MailConfig.SMTP_STARTTLS;
import static com.email.bkjkmail.mail.MailConfig.SNED_PROTOCOL;
import static com.email.bkjkmail.mail.MailConfig.SOCKETFACTORY_PROT;
import static com.email.bkjkmail.mail.MailConfig.SSL_FACTORY;

/**
 * Created by wudi on 2018/1/29.
 * 构造连接需要的工具
 */
public class MailBuilder {

    private static Store mStore;

    private static Transport mTransport;

    /**
     * 拿到imap 仓库 收取邮件
     * @return 仓库
     * @throws NoSuchProviderException
     */
    public static Store getStore(){
        if (mStore == null) {
            synchronized (Object.class) {
                if (mStore == null) {
                    Session session = Session.getDefaultInstance(getImapPro());
                    try {
                        mStore = session.getStore();
                    } catch (NoSuchProviderException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return mStore;
    }

    /**
     * 拿到smtp 仓库 发送邮件
     * @return 仓库
     * @throws NoSuchProviderException
     */
    public static Transport getTransport(){
        if (mTransport == null) {
            synchronized (Object.class) {
                if (mTransport == null) {
                    Session session = Session.getInstance(getSmtpPro(), new Authenticator() {
                        @Override
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(MailConfig.sMailAccount.getAccount(), MailConfig.sMailAccount.getPassword());
                        }
                    });
                    try {
                        mTransport = session.getTransport();
                    } catch (NoSuchProviderException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return mTransport;
    }


    /**
     * 拿到imap链接 的pro
     * @return Properties
     */
    private static Properties getImapPro() {
        Properties props = new Properties();
        props.setProperty("mail.imap.socketFactory.class", SSL_FACTORY);
        props.setProperty("mail.imap.socketFactory.fallback", FALLBACK);
        props.setProperty("mail.store.protocol", RECEIVE_PROTOCOL);
        props.setProperty("mail.imap.host", IMAP_HOST);
        props.setProperty("mail.imap.port", IMAP_PORT);
        props.setProperty("mail.imap.socketFactory.port", SOCKETFACTORY_PROT);
        return props;
    }

    /**
     * 拿到imap链接 的pro
     * @return Properties
     */
    private static Properties getSmtpPro() {
        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", SNED_PROTOCOL); //协议名称
        props.setProperty("mail.smtp.host", SMTP_HOST); //smtp服务器
        props.setProperty("mail.smtp.socketFactory.port", SMTP_PORT); //服务器端口
        props.setProperty("mail.smtp.auth", SMTP_AUTH); //是否需要用户名密码鉴权
        props.setProperty("mail.smtp.starttls.enable", SMTP_STARTTLS); //如果是ssl端口，需要加这个属性。
        props.setProperty("mail.smtp.ssl.trust", "MailInfo.bkjk.com");
        return props;
    }
}
