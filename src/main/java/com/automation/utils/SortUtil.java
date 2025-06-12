package com.automation.utils;

import java.util.List;

public class SortUtil {
    public static boolean sortedAscending(List<Integer> ls)
    {
        for(int i=0;i<ls.size()-1;i++)
        {
            if (ls.get(i)>ls.get(i+1))
            {
                return false;
            }
        }
        return true;
    }

    public static boolean sortedDescending(List<Integer> ls)
    {
        for(int i=0;i< ls.size()-1;i++)
        {
            if(ls.get(i)<ls.get(i+1))
            {
                return false;
            }
        }
        return true;
    }
}
