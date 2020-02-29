package com.ndovel.ebook.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Data
@EqualsAndHashCode
@Table(name = "authority")
@Entity
public class Authority implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @JsonIgnore
    private Integer id;

    @Column(name = "name", columnDefinition = "varchar(50)", unique = true)
    private String name;

    @Override
    @JsonIgnore
    public String getAuthority() {
        return name;
    }
}
