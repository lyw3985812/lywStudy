package com.lyw.greendaotest.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

import java.util.List;
import org.greenrobot.greendao.DaoException;
import com.lyw.greendaotest.DaoSession;
import com.lyw.greendaotest.DogDao;
import com.lyw.greendaotest.UserDao;

/**
 * Created by Administrator on 2018/1/26 0026.
 */

@Entity
public class User {
    // 主键，autoincrement设置自增
    @Id(autoincrement = true)
    private Long id;

    // 唯一，默认索引
    @Unique
    private Long userId;

    // 列名，默认使用变量名。变化：customName --> CUSTOM_NAME
    @Property(nameInDb = "USERNAME")
    private String name;

    // 索引，unique设置唯一，name设置索引别名
//    @Index(unique = true)
    private long fk_dogId;

    // 非空
    @NotNull
    private String horseName;

    @Generated(hash = 604905101)
    public User(Long id, Long userId, String name, long fk_dogId,
            @NotNull String horseName) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.fk_dogId = fk_dogId;
        this.horseName = horseName;
    }

    @Generated(hash = 586692638)
    public User() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getFk_dogId() {
        return this.fk_dogId;
    }

    public void setFk_dogId(long fk_dogId) {
        this.fk_dogId = fk_dogId;
    }

    public String getHorseName() {
        return this.horseName;
    }

    public void setHorseName(String horseName) {
        this.horseName = horseName;
    }

//    // 忽略，不持久化，可用关键字transient替代
//    @Transient
//    private int tempUsageCount;
//
        // 对一，实体属性与外联实体中的ID相对应。默认自动自动生成。fk和对象联动，同时改变。对象懒加载
//     @ToOne(joinProperty = "fk_dogId")
//     private Dog dog;
////
//    // 对多，referencedJoinProperty 指定实体中与外联实体属性相对应的外键
//    @ToMany(referencedJoinProperty = "fk_dogId")
//     private List<Dog> dogs;
//
//    /** Used to resolve relations */
//    @Generated(hash = 2040040024)
//    private transient DaoSession daoSession;
//
//    /** Used for active entity operations. */
//    @Generated(hash = 1507654846)
//    private transient UserDao myDao;
//
//    @Generated(hash = 265955384)
//    private transient Long dog__resolvedKey;

//    // 对多，@JoinProperty注解：name 实体中的属性；referencedName 外联实体中的属性。
//    @ToMany(joinProperties = {
//            @JoinProperty(name = "horseName", referencedName = "name")
//    })
//    private List<horse> horses;
//
//    // 对多，@JoinEntity注解：entity 中间表；sourceProperty 实体属性；targetProperty 外链实体属性
//    @ToMany
//    @JoinEntity(
//            entity = JoinSheepToUser.class,
//            sourceProperty = "uId",
//            targetProperty = "sId"
//    )
//    private List<sheep> sheep;


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userId=" + userId +
                ", name='" + name + '\'' +
                ", fk_dogId=" + fk_dogId +
                ", horseName='" + horseName + '\'' +
                '}';
    }

}