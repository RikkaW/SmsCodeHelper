package com.xiaomi.smsunderstand.task;

import com.xiaomi.common.ACAutomation;
import com.xiaomi.common.FileOperator;
import com.xiaomi.smsunderstand.SMSUnderstand;
import java.io.IOException;

public class ChongzhiRecognition
{
  private static ACAutomation acChongzhiWords;
  private static String chongzhiWordsFileName = null;
  private static boolean ifInitial = false;
  
  public static boolean freeResource()
  {
    if (!ifInitial) {
      return true;
    }
    acChongzhiWords = null;
    ifInitial = false;
    return true;
  }
  
  public static boolean initial()
    throws IOException
  {
    if (ifInitial) {
      return true;
    }
    chongzhiWordsFileName = SMSUnderstand.dictionaryPath + "/chongzhiWords.txt";
    acChongzhiWords = new ACAutomation(FileOperator.readFile(chongzhiWordsFileName));
    ifInitial = true;
    return true;
  }
  
  public static double isRight(String paramString)
  {
    if (!ifInitial) {}
    while (acChongzhiWords.findFirst(paramString, 0) == null) {
      return 0.0D;
    }
    return 1.0D;
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.smsunderstand.task.ChongzhiRecognition
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */