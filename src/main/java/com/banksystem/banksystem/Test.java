package com.banksystem.banksystem;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Test {

    public static void main(String[] args) {
        String dt = "2022-07-20T14:00:00";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        var d = LocalDateTime.parse(dt, formatter);
        System.out.println(d.getHour());
    }

}
