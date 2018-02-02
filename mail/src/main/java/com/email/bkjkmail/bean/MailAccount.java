package com.email.bkjkmail.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.OrderBy;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;
import com.email.bkjkmail.db.DaoSession;
import com.email.bkjkmail.db.MailInfoDao;
import com.email.bkjkmail.db.MailAccountDao;

/**
 * Created by wudi on 2018/2/1.
 */
@Entity
public class MailAccount {

    @Id(autoincrement = true)
    private Long id;

    @Index(unique = true)
    private String account;

    @Index(unique = true)
    private String password;

    @Index(unique = true)
    private String uuid;

    @ToMany(referencedJoinProperty = "accountId")
    @OrderBy("time ASC")
    private List<MailInfo> mailInfos;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 20390142)
    private transient MailAccountDao myDao;

    @Generated(hash = 708574035)
    public MailAccount(Long id, String account, String password, String uuid) {
        this.id = id;
        this.account = account;
        this.password = password;
        this.uuid = uuid;
    }

    @Generated(hash = 1301836918)
    public MailAccount() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccount() {
        return this.account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 324610475)
    public List<MailInfo> getMailInfos() {
        if (mailInfos == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            MailInfoDao targetDao = daoSession.getMailInfoDao();
            List<MailInfo> mailInfosNew = targetDao._queryMailAccount_MailInfos(id);
            synchronized (this) {
                if (mailInfos == null) {
                    mailInfos = mailInfosNew;
                }
            }
        }
        return mailInfos;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1233912661)
    public synchronized void resetMailInfos() {
        mailInfos = null;
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1840112158)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getMailAccountDao() : null;
    }

    public String getUuid() {
        return this.uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

}
