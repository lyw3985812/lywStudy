package com.lyw.greendaotest.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Administrator on 2018/1/26 0026.
 */

@Entity
public class JoinSheepToUser {
    @Id
    private Long id;

    private Long uId;

    private Long sId;

    @Generated(hash = 1208079434)
    public JoinSheepToUser(Long id, Long uId, Long sId) {
        this.id = id;
        this.uId = uId;
        this.sId = sId;
    }

    @Generated(hash = 943584808)
    public JoinSheepToUser() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUId() {
        return this.uId;
    }

    public void setUId(Long uId) {
        this.uId = uId;
    }

    public Long getSId() {
        return this.sId;
    }

    public void setSId(Long sId) {
        this.sId = sId;
    }
}
