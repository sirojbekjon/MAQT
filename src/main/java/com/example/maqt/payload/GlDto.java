package com.example.maqt.payload;

import lombok.Data;

@Data
public class GlDto {
    private String name;
    private String number;
    private String date;
    private Integer typeId;
    private Integer stateId;
    private Integer attachment_id;
    private Integer[]glavka_id;
    private Integer glavkaName_id;

}
