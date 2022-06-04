package com.example.maqt.entity;

import com.example.maqt.entity.template.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Glavka extends Document {

    @OneToMany
    private List<Glavka> glavkaList;

    @ManyToOne
    private Glavname glavName;
}
