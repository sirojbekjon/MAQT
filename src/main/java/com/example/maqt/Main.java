package com.example.maqt;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoField;

public class Main {
    public static void main(String[] args){
        LocalDate date = LocalDate.now();
        int n = 0,m=0;
        while (n<20)
        {
         LocalDate after = date.plusDays(m);
            m++;
//            DayOfWeek day = DayOfWeek.of(after.get(ChronoField.DAY_OF_WEEK));
            int k = after.getDayOfWeek().getValue();
            if (!(k == 6 || k==7)){
                n++;
            }
//            switch (day){
//                case WEDNESDAY: n++; break;
//                case THURSDAY: n++;  break;
//                case FRIDAY: n++;    break;
//                case MONDAY: n++;    break;
//                case  TUESDAY: n++;  break;
//            }
            if (n==20)
            System.out.println(after.getDayOfMonth());
        }


    }
}
