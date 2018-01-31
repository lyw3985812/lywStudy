package com.lyw.greendaotest.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Administrator on 2018/1/29 0029.
 */

@Entity
public class Dog {
    @Id
    private Long fk_dogId;

    private String name;

    @Generated(hash = 1616688292)
    public Dog(Long fk_dogId, String name) {
        this.fk_dogId = fk_dogId;
        this.name = name;
    }

    @Generated(hash = 2001499333)
    public Dog() {
    }

    public Long getFk_dogId() {
        return this.fk_dogId;
    }

    public void setFk_dogId(Long fk_dogId) {
        this.fk_dogId = fk_dogId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
