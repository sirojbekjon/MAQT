package com.example.maqt.entity;

import com.example.maqt.entity.template.AbsIntEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Glavname extends AbsIntEntity {
}
