package com.example.maqt.entity;

import com.example.maqt.entity.template.AbsIntEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Others extends AbsIntEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    private Categore categore;
}
