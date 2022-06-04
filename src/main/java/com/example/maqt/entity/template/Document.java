package com.example.maqt.entity.template;

import com.example.maqt.entity.State;
import com.example.maqt.entity.Type;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import javax.persistence.*;


@Data
@MappedSuperclass
public abstract class Document extends AbsMainEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String number;

    @Column(nullable = false)
    private String date;

    @ManyToOne(fetch = FetchType.LAZY)
    private Type type ;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private State state;



    @JsonProperty(value = "parent_name")
    private String parentName()
    {
        return this.state.getName();
    }

    @JsonProperty(value = "parent_id")
    private Integer parentID()
    {
        return this.state.getId();
    }
}



















