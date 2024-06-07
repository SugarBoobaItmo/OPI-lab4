package com.example.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeBean {
    public String getCurrentDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        return sdf.format(new Date());
    }
}
