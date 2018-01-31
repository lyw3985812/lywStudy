package com.lyw.greendaotest.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Administrator on 2018/1/26 0026.
 */

@Entity
public class Sheep {
    @Id
    private Long id;

    private String name;

    @Generated(hash = 1871150570)
    public Sheep(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Generated(hash = 152960452)
    public Sheep() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
