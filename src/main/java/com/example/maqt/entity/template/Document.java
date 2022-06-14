package com.example.maqt.entity.template;

import com.example.maqt.entity.Attachment;
import com.example.maqt.entity.State;
import com.example.maqt.entity.Type;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import javax.persistence.*;
import java.util.List;


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

    @ManyToOne(fetch = FetchType.LAZY)
    private State state;

    @OneToOne
    private Attachment attachment;


}



















