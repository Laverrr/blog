package com.laver.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Data
public class Vote implements Serializable {

    @Id // 主键
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 自增长策略
    private Long id; // 用户的唯一标识

    @OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @Column(nullable = false) // 映射为字段，值不能为空
    @org.hibernate.annotations.CreationTimestamp  // 由数据库自动创建时间
    private Timestamp createTime;

    public Vote(User user) {
        this.user = user;
    }
}
