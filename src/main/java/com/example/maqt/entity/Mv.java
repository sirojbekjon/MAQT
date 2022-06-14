package com.example.maqt.entity;

import com.example.maqt.entity.template.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Mv extends Document{


    @OneToMany(fetch = FetchType.LAZY)
    private List<Mv> mvList;
}

