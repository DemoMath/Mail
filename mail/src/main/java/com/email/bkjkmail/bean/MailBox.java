package com.email.bkjkmail.bean;

import java.util.List;

/**
 * Created by wudi on 2018/1/30.
 */


public class MailBox {
    //未读邮件数量
    private Integer unReadCount;
    //删除邮件数量
    private Integer deleteCount;
    //新邮件数量
    private Integer newCount;
    //邮件总数
    private Integer totalCount;
    //邮件
    private List<MailBean> messages;
    //当前页数
    private Integer pageNo = 1;
    //当页数据容量
    private Integer pageSize = 10;
    //总页数
    private Integer pageTotal;

    public Integer getUnReadCount() {
        return unReadCount;
    }

    public void setUnReadCount(Integer unReadCount) {
        this.unReadCount = unReadCount;
    }

    public Integer getDeleteCount() {
        return deleteCount;
    }

    public void setDeleteCount(Integer deleteCount) {
        this.deleteCount = deleteCount;
    }

    public Integer getNewCount() {
        return newCount;
    }

    public void setNewCount(Integer newCount) {
        this.newCount = newCount;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public List<MailBean> getMessages() {
        return messages;
    }

    public void setMessages(List<MailBean> messages) {
        this.messages = messages;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(Integer pageTotal) {
        this.pageTotal = pageTotal;
    }
}