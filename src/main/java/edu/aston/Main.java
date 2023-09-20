package edu.aston;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> integerArrayList = new ArrayList<>();
        integerArrayList.add(1);
        integerArrayList.add(null);
        integerArrayList.add(0);
        integerArrayList.sort(Integer::compare);
        System.out.println(integerArrayList.toString());
    }
}