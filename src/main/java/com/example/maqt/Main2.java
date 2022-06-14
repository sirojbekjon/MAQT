package com.example.maqt;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

public class Main2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        int a = 0;
        int[] nums = new int[n];
        for(int i = 0; i<nums.length; i++){
            nums[i]=scanner.nextInt();
        }
        for (int num : nums) {
            if (a == num){
                num=0;
                a = num;
            }
        }
        System.out.println(Arrays.toString(nums));
    }
}
