package com.example.maqt.payload;

import lombok.Data;

@Data
public class MvDto {

    private String name;
    private String number;
    private String date;
    private Integer typeId;
    private Integer stateId;
    private Integer[] mvId;
}
