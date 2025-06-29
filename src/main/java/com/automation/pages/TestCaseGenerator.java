package com.automation.pages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestCaseGenerator {

    public static List<Map<String,String>> generateTestcases(Map<String,List<String>> config)
    {
       List<String> keys= new ArrayList<>(config.keySet());
       List<Map<String,String>> result=new ArrayList<>();
       backtrack(config,keys,0,new HashMap<>(),result);
       return result;
    }

    public static void backtrack(Map<String,List<String>> config,List<String> keys,int index, HashMap<String,String> current,List<Map<String,String>> result)
    {
        if(index==keys.size())
        {
            result.add(new HashMap<>(current));
            return;
        }
        String key=keys.get(index);
        for(String option:config.get(key))
        {
            current.put(key,option);
            backtrack(config,keys,index+1,current,result);
            current.remove(key);
        }
    }
}
