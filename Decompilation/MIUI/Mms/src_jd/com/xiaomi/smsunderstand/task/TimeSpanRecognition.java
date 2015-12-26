package com.xiaomi.smsunderstand.task;

import com.xiaomi.common.ACAutomation;
import com.xiaomi.common.FileOperator;
import com.xiaomi.smsunderstand.EntityInfo;
import com.xiaomi.smsunderstand.SMSUnderstand;
import java.io.IOException;
import java.util.List;

public class TimeSpanRecognition
{
  private static ACAutomation actimeSpanNextWords;
  private static boolean ifInitial = false;
  private static List<String> timeSpanNextWords;
  private static String timeSpanNextWordsFileName = null;
  
  public static boolean freeResource()
  {
    if (!ifInitial) {
      return true;
    }
    timeSpanNextWords.clear();
    actimeSpanNextWords = null;
    ifInitial = false;
    return true;
  }
  
  public static boolean initial()
    throws IOException
  {
    if (ifInitial) {
      return true;
    }
    timeSpanNextWordsFileName = SMSUnderstand.dictionaryPath + "/timeSpanNextWords.txt";
    timeSpanNextWords = FileOperator.readFile(timeSpanNextWordsFileName);
    actimeSpanNextWords = new ACAutomation(timeSpanNextWords);
    ifInitial = true;
    return true;
  }
  
  public static double isRightNumber(EntityInfo paramEntityInfo, String paramString)
  {
    if (!ifInitial) {}
    int[] arrayOfInt;
    do
    {
      do
      {
        return 0.0D;
        i = paramEntityInfo.getNumberCount();
      } while ((i <= 0) || (i + 1 < paramEntityInfo.getTarget().length()));
      arrayOfInt = actimeSpanNextWords.findFirst(paramString, paramEntityInfo.getEndPosition());
      if ((arrayOfInt != null) && (arrayOfInt[1] == paramEntityInfo.getEndPosition()))
      {
        paramEntityInfo.setRemark((String)timeSpanNextWords.get(arrayOfInt[0]));
        paramEntityInfo.noNomal();
        return 1.0D;
      }
    } while (arrayOfInt == null);
    int j = 1;
    int i = paramEntityInfo.getEndPosition();
    for (;;)
    {
      if (i >= arrayOfInt[1]) {}
      for (i = j; i != 0; i = 0)
      {
        paramEntityInfo.setRemark((String)timeSpanNextWords.get(arrayOfInt[0]));
        paramEntityInfo.noNomal();
        return 1.0D;
        int k = paramString.charAt(i);
        if ((k == 32) || (k == 9)) {
          break label161;
        }
      }
      break;
      label161:
      i += 1;
    }
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.smsunderstand.task.TimeSpanRecognition
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */