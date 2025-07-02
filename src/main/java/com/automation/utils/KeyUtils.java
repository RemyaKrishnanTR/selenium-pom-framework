package com.automation.utils;

import java.awt.*;
import java.awt.event.KeyEvent;

public class KeyUtils {
    public static void typeText(Robot robot,String text)
    {
        for(char ch:text.toCharArray())
        {
            typeCharacter(robot,ch);
        }
    }

    public static void typeCharacter(Robot robot,char ch)
    {
      int keyCode= KeyEvent.getExtendedKeyCodeForChar(ch);
      if(keyCode==KeyEvent.CHAR_UNDEFINED)
      {
          throw new RuntimeException("cannot type character"+ch);
      }
      robot.keyPress(keyCode);
      robot.keyRelease(keyCode);
    }

    public static void pressTab(Robot robot)
    {
        robot.keyPress(KeyEvent.VK_TAB);
        robot.keyRelease(KeyEvent.VK_TAB);
    }

    public static void pressEnter(Robot robot)
    {
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

    }
}
