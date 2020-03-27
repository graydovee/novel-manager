package com.ndovel.novel.model.entity;

import com.ndovel.novel.model.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "content", indexes = {
        @Index(name = "index_id_deleted", columnList = "id,deleted")
        })
public class Content extends BaseEntity {
    @Column(name = "info",columnDefinition = "text")
    private String info;

    @Column(name = "visit", columnDefinition = "bigint(20) default 0")
    private Long visit;
}
