package com.automation.tests.day7;

import java.util.Arrays;

public class C {

    public static void main(String[] args) {
        int[][] scores = new int[5][];
        System.out.println(Arrays.toString(scores));
        Object[][][] cubbies = new Object[3][0][5];
        System.out.println(Arrays.deepToString(cubbies));

        java.util.Date[] dates[] = new java.util.Date[2][];
        System.out.println(Arrays.toString(dates));
        //int[][] types = new int[];
        //int[][] java = new int[][];


        long thatNumber = 5 >= 5 ? 1+2 : 1*1;
        System.out.println(thatNumber);
        if(++thatNumber < 4)
            thatNumber += 1;

        System.out.println(thatNumber);
    }

}
