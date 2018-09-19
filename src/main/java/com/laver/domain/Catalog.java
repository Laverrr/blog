package com.laver.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
public class Catalog implements Serializable {

    @Id // 主键
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 自增长策略
    private Long id; // 用户的唯一标识

    @NotEmpty(message = "名称不能为空")
    @Size(min=2, max=30)
    @Column(nullable = false) // 映射为字段，值不能为空
    private String name;

    @OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    public Catalog(@NotEmpty(message = "名称不能为空") @Size(min = 2, max = 30) String name, User user) {
        this.name = name;
        this.user = user;
    }
}
