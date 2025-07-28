package com.automation.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonValidatorUtil {

    //Handle Malformed Json
    public static JSONObject parseJson(String repsonseStr)
    {
        try
        {
            return new JSONObject(repsonseStr);
        } catch (JSONException e) {
            throw new RuntimeException("Malformed Json"+e.getMessage());
        }
    }

    public static boolean isKeyPresent(JSONObject jsonObject, String key)
    {
        return jsonObject.has(key);
    }

    public static Object getValue(JSONObject jsonObject,String key)
    {
        if(isKeyPresent(jsonObject,key))
        {
            return jsonObject.get(key);
        }
        else {
            throw new RuntimeException("Key is not present "+key);
        }
    }

    public static JSONObject nestedObject(JSONObject jsonObject,String... keys)
    {
        JSONObject current=jsonObject;
        for(String key:keys)
        {
            if(current.has(key))
            {
                current=current.getJSONObject(key);
            }
            else {
                throw new RuntimeException("Key is missing "+key);
            }
        }
        return current;
    }

    public static JSONArray getJsonArray(JSONObject jsonObject,String key)
    {
        if(jsonObject.has(key))
        {
            return jsonObject.getJSONArray(key);
        }
        else {
            throw new RuntimeException("Key not found" + key);
        }
    }

    public static void printArrayElements(JSONArray jsonArray)
    {
        for(int i=0;i<jsonArray.length();i++)
        {
            System.out.println(("Array element [" + i + "]: "+jsonArray.get(i)));
        }
    }


}
