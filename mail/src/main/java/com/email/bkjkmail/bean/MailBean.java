package com.email.bkjkmail.bean;

import java.io.Serializable;

/**
 * Created by wudi on 2018/1/30.
 */
public class MailBean implements Serializable {
    //邮件序号(第几封邮件)
    private int num;
    //主题
    private String subject;
    //发送时间
    private String sentDate;
    //发件人
    private String from;
    //收件人地址
    private String to;
    //抄送人地址
    private String cc;
    //密送人地址
    private String bcc;
    //解析并获取内容
    private String bodyText;
    //附件
    private String attachPath;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSentDate() {
        return sentDate;
    }

    public void setSentDate(String sentDate) {
        this.sentDate = sentDate;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getBcc() {
        return bcc;
    }

    public void setBcc(String bcc) {
        this.bcc = bcc;
    }

    public String getBodyText() {
        return bodyText;
    }

    public void setBodyText(String bodyText) {
        this.bodyText = bodyText;
    }

    public String getAttachPath() {
        return attachPath;
    }

    public void setAttachPath(String attachPath) {
        this.attachPath = attachPath;
    }
}
