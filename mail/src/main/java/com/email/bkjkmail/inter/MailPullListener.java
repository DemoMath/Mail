package com.email.bkjkmail.inter;

import com.email.bkjkmail.bean.MailBox;

/**
 * Created by wudi on 2018/1/30.
 */

public interface MailPullListener {
    void onSuccess(MailBox mailBox);
}
