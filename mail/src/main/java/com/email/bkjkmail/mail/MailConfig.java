package com.email.bkjkmail.mail;

import com.email.bkjkmail.bean.MailAccount;

/**
 * Created by wudi on 2018/1/29.
 * 邮箱链接配置
 */
public class MailConfig {
    /*****账户  密码*******/
    public static MailAccount sMailAccount;


    /*** IMAP 收取邮件 ***/
    static final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
    static final String FALLBACK = "false";
    static final String RECEIVE_PROTOCOL = "imap";
    static final String IMAP_HOST = "mail.bkjk.com";
    static final String IMAP_PORT = "993";
    static final String SOCKETFACTORY_PROT = "993";

    /***SMTP 发送邮件***/
    static final String SNED_PROTOCOL = "smtp";
    static final String SMTP_AUTH = "true";
    static final String SMTP_HOST = "mail.bkjk.com";
    static final String SMTP_PORT = "587";
    static final String SMTP_STARTTLS = "true";
}
