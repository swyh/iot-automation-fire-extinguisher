package com.power.kimyounghoon.project;

/**
 * Created by KIMYOUNGHOON on 5/5/2018.
 */

public class Utill {

    private static String IP = "192.168.0.8";

    public static String getURL(String page){
        return "http://" + IP +"/php/" + page;
    }
}
