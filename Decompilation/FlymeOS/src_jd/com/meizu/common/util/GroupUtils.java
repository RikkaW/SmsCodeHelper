package com.meizu.common.util;

import android.database.Cursor;
import android.text.format.Time;
import android.util.Log;
import java.util.List;

public class GroupUtils
{
  private static final int DATE_TYPE_LENGTH = 6;
  private static final int DEFAULT_DATE = -1;
  public static final int FORMER_YEAR = 5;
  public static final int FUTURE = 0;
  private static final int SPECIFIED_DATE_GROUP_LENGTH = 4;
  public static final int SPECIFIED_FUTURE = 0;
  public static final int SPECIFIED_OTHER = 3;
  public static final int SPECIFIED_THIS_MONTH = 2;
  public static final int SPECIFIED_THIS_WEEK = 1;
  public static final int THIS_MONTH = 3;
  public static final int THIS_WEEK = 2;
  public static final int THIS_YEAR = 4;
  public static final int TODAY = 1;
  public static final int TYPE_FWMO = 0;
  
  private static int checkDateType(long paramLong1, Time paramTime1, Time paramTime2, long paramLong2)
  {
    int i = -1;
    paramTime1.set(paramLong1);
    int j;
    if (year < year) {
      j = 5;
    }
    do
    {
      int k;
      do
      {
        do
        {
          return j;
          if (paramLong1 > paramLong2) {
            i = 0;
          }
          if (year == year)
          {
            i = 4;
            if (month == month) {
              i = 3;
            }
          }
          k = yearDay - weekDay;
          j = i;
        } while (yearDay < k);
        j = i;
      } while (yearDay >= k + 7);
      j = 2;
    } while (monthDay != monthDay);
    return 1;
  }
  
  public static int[] getGroupCountsByCursor(Cursor paramCursor, int paramInt)
  {
    return getGroupCountsByCursor(paramCursor, paramInt, 0, paramCursor.getCount() - 1);
  }
  
  public static int[] getGroupCountsByCursor(Cursor paramCursor, int paramInt1, int paramInt2, int paramInt3)
  {
    if (paramInt2 > paramInt3)
    {
      Log.e("Error", "getGroupConntByCursor startPos > endPos error");
      return null;
    }
    int[] arrayOfInt = new int[6];
    Time localTime1 = new Time();
    Time localTime2 = new Time();
    long l = System.currentTimeMillis();
    localTime2.set(l);
    if (paramCursor.moveToPosition(paramInt2))
    {
      signDateCount(checkDateType(paramCursor.getLong(paramInt1), localTime1, localTime2, l), arrayOfInt);
      if (paramCursor.getPosition() != paramInt3) {
        break label95;
      }
    }
    for (;;)
    {
      return arrayOfInt;
      label95:
      if (paramCursor.moveToNext()) {
        break;
      }
    }
  }
  
  public static int[] getGroupCountsByList(List<Long> paramList)
  {
    return getGroupCountsByList(paramList, 0, paramList.size() - 1);
  }
  
  public static int[] getGroupCountsByList(List<Long> paramList, int paramInt1, int paramInt2)
  {
    if (paramInt1 > paramInt2)
    {
      Log.e("Error", "getGroupConntByCursor startPos > endPos error");
      return null;
    }
    int[] arrayOfInt = new int[6];
    Time localTime1 = new Time();
    Time localTime2 = new Time();
    long l = System.currentTimeMillis();
    localTime2.set(l);
    while (paramInt1 <= paramInt2)
    {
      signDateCount(checkDateType(((Long)paramList.get(paramInt1)).longValue(), localTime1, localTime2, l), arrayOfInt);
      paramInt1 += 1;
    }
    return arrayOfInt;
  }
  
  public static int[] getSpecifiedGroupCounts(int paramInt1, Cursor paramCursor, int paramInt2)
  {
    return getSpecifiedGroupCounts(paramInt1, paramCursor, paramInt2, 0, paramCursor.getCount() - 1);
  }
  
  public static int[] getSpecifiedGroupCounts(int paramInt1, Cursor paramCursor, int paramInt2, int paramInt3, int paramInt4)
  {
    paramCursor = getGroupCountsByCursor(paramCursor, paramInt2, paramInt3, paramInt4);
    paramInt1 = paramCursor[0];
    paramInt2 = paramCursor[1];
    paramInt3 = paramCursor[2];
    paramInt4 = paramCursor[3];
    int i = paramCursor[4];
    return new int[] { paramInt1, paramInt2 + paramInt3, paramInt4, paramCursor[5] + i };
  }
  
  public static int[] getSpecifiedGroupCounts(int paramInt, List<Long> paramList)
  {
    return getSpecifiedGroupCounts(paramInt, paramList, 0, paramList.size() - 1);
  }
  
  public static int[] getSpecifiedGroupCounts(int paramInt1, List<Long> paramList, int paramInt2, int paramInt3)
  {
    paramList = getGroupCountsByList(paramList, paramInt2, paramInt3);
    paramInt1 = paramList[0];
    paramInt2 = paramList[1];
    paramInt3 = paramList[2];
    int i = paramList[3];
    int j = paramList[4];
    return new int[] { paramInt1, paramInt2 + paramInt3, i, paramList[5] + j };
  }
  
  private static void signDateCount(int paramInt, int[] paramArrayOfInt)
  {
    switch (paramInt)
    {
    default: 
      return;
    case 0: 
      paramArrayOfInt[0] += 1;
      return;
    case 1: 
      paramArrayOfInt[1] += 1;
      return;
    case 2: 
      paramArrayOfInt[2] += 1;
      return;
    case 3: 
      paramArrayOfInt[3] += 1;
      return;
    case 4: 
      paramArrayOfInt[4] += 1;
      return;
    }
    paramArrayOfInt[5] += 1;
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.util.GroupUtils
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */