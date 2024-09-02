package com.example.studentmanagement.util;

import java.util.Base64;
import java.util.UUID;

public class UtilMatters {
    public static  String generateId(){
        return UUID.randomUUID().toString();
    }
    public static String convertBase64(String data){
        return Base64.getEncoder().encodeToString(data.getBytes());
    }
}
