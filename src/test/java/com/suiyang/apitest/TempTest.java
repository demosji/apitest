package com.suiyang.apitest;

import com.google.gson.Gson;

import java.sql.SQLOutput;
import java.util.HashSet;
import java.util.Set;

public class TempTest {

    Long id = 1l;
    Set<Long> uid = new HashSet<Long>();

    public static void main(String[] args) {

        TempTest tt = new TempTest();
        tt.uid.add(1l);
        tt.uid.add(2l);

        String ss = new Gson().toJson(tt);

        System.out.println(ss);


    }
}
