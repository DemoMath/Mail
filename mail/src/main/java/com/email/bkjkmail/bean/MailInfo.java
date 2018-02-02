package com.email.bkjkmail.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by wudi on 2018/2/1.
 */

@Entity
public class MailInfo {
    @Id(autoincrement = true) private Long id;
    @Index
    private long accountId;
    @Index private String subject;
    @Index private String time;
    @Index private String from;
    @Index private String to;
    @Index private String cc;
    @Index private String bcc;
    @Index private String bodyText;
    @Index private String attachPath;
    @Generated(hash = 733699750)
    public MailInfo(Long id, long accountId, String subject, String time,
            String from, String to, String cc, String bcc, String bodyText,
            String attachPath) {
        this.id = id;
        this.accountId = accountId;
        this.subject = subject;
        this.time = time;
        this.from = from;
        this.to = to;
        this.cc = cc;
        this.bcc = bcc;
        this.bodyText = bodyText;
        this.attachPath = attachPath;
    }
    @Generated(hash = 168647705)
    public MailInfo() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public long getAccountId() {
        return this.accountId;
    }
    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }
    public String getSubject() {
        return this.subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }
    public String getTime() {
        return this.time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public String getFrom() {
        return this.from;
    }
    public void setFrom(String from) {
        this.from = from;
    }
    public String getTo() {
        return this.to;
    }
    public void setTo(String to) {
        this.to = to;
    }
    public String getCc() {
        return this.cc;
    }
    public void setCc(String cc) {
        this.cc = cc;
    }
    public String getBcc() {
        return this.bcc;
    }
    public void setBcc(String bcc) {
        this.bcc = bcc;
    }
    public String getBodyText() {
        return this.bodyText;
    }
    public void setBodyText(String bodyText) {
        this.bodyText = bodyText;
    }
    public String getAttachPath() {
        return this.attachPath;
    }
    public void setAttachPath(String attachPath) {
        this.attachPath = attachPath;
    }
}