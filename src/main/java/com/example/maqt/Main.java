package com.example.maqt;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> numbers = new ArrayList<>();
        numbers.add(2);
        numbers.add(3);
        numbers.add(6);
        numbers.add(1);
        numbers.add(7);
        numbers.add(9);
        numbers.forEach((n)->{
            System.out.println(n);
        });
    }
}
