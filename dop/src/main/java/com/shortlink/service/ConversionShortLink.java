package com.shortlink.service;

import org.hashids.Hashids;
import org.springframework.stereotype.Service;

@Service
public class ConversionShortLink {

    public static String convert(String input){
        String domen = "localhost:8080/api/l/";
        Hashids hashids = new Hashids(input, 8);
        return domen + hashids.encode(1L);
    }
}
