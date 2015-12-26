package com.xiaomi.smsunderstand.task;

import com.xiaomi.common.StringProcess;
import com.xiaomi.smsunderstand.EntityInfo;

public class RealNumberRecognition
{
  public static double isRightNumber(EntityInfo paramEntityInfo, String paramString)
  {
    paramString = paramEntityInfo.getTarget();
    int i = paramEntityInfo.getNumberCount();
    if (i == paramString.length())
    {
      paramEntityInfo.setRemark("Integer");
      return 1.0D;
    }
    int j = StringProcess.getCharCount(paramString, new Character[] { Character.valueOf('.') });
    if ((j != 1) || (i + j != paramString.length())) {
      return 0.0D;
    }
    paramEntityInfo.setRemark("Double");
    return 1.0D;
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.smsunderstand.task.RealNumberRecognition
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */