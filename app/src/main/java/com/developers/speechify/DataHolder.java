package com.developers.speechify;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Amanjeet Singh on 28-Mar-16.
 */
public class DataHolder {
    private static ArrayList ing;
    public static void setIng(ArrayList ing){
        DataHolder.ing=ing;
    }
    public static ArrayList getIng(){
        return ing;
    }
}
