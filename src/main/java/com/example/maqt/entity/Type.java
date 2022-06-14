package com.example.maqt.entity;

import com.example.maqt.entity.template.AbsIntEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Type extends AbsIntEntity {

    @Column(nullable = false)
    private Integer degree;
}
