package de.enrico.utils;

import java.sql.Timestamp;
import java.util.Date;

public class Timestamps {

    public static Timestamp getTime() {
        Date date = new Date();
        long time = date.getTime();
        java.sql.Timestamp ts = new Timestamp(time);
        return ts;
    }


}
