package com.example.maqt.payload;

import lombok.Data;

@Data
public class NgshDto {
    private String name;
    private String number;
    private String date;
    private Integer typeId;
    private Integer stateId;
    private Integer[] ngshId;
    private Integer attachment_id;

}
