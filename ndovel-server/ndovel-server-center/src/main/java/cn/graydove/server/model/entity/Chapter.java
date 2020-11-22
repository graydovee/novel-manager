package cn.graydove.server.model.entity;

import cn.graydove.server.model.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Chapter extends BaseEntity {

    private String title;

    private Long nextChapterId;

    private Long preChapterId;
}
