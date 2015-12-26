package com.android.mms.dom.smil;

import android.util.Log;
import java.util.ArrayList;
import org.w3c.dom.DOMException;
import org.w3c.dom.smil.ElementTime;
import org.w3c.dom.smil.SMILElement;
import org.w3c.dom.smil.Time;
import org.w3c.dom.smil.TimeList;

public abstract class ElementTimeImpl
  implements ElementTime
{
  final SMILElement mSmilElement;
  
  ElementTimeImpl(SMILElement paramSMILElement)
  {
    mSmilElement = paramSMILElement;
  }
  
  private boolean beginAndEndAreZero()
  {
    Object localObject2 = getBegin();
    Object localObject1 = getEnd();
    if ((((TimeList)localObject2).getLength() == 1) && (((TimeList)localObject1).getLength() == 1))
    {
      localObject2 = ((TimeList)localObject2).item(0);
      localObject1 = ((TimeList)localObject1).item(0);
      return (((Time)localObject2).getOffset() == 0.0D) && (((Time)localObject1).getOffset() == 0.0D);
    }
    return false;
  }
  
  public TimeList getBegin()
  {
    String[] arrayOfString = mSmilElement.getAttribute("begin").split(";");
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    for (;;)
    {
      if (i < arrayOfString.length) {}
      try
      {
        localArrayList.add(new TimeImpl(arrayOfString[i], getBeginConstraints()));
        i += 1;
        continue;
        if (localArrayList.size() == 0) {
          localArrayList.add(new TimeImpl("0", 255));
        }
        return new TimeListImpl(localArrayList);
      }
      catch (IllegalArgumentException localIllegalArgumentException)
      {
        for (;;) {}
      }
    }
  }
  
  int getBeginConstraints()
  {
    return 255;
  }
  
  public float getDur()
  {
    float f = 0.0F;
    try
    {
      String str = mSmilElement.getAttribute("dur");
      if (str != null)
      {
        f = TimeImpl.parseClockValue(str);
        f /= 1000.0F;
      }
      return f;
    }
    catch (IllegalArgumentException localIllegalArgumentException) {}
    return 0.0F;
  }
  
  public TimeList getEnd()
  {
    ArrayList localArrayList = new ArrayList();
    Object localObject = mSmilElement.getAttribute("end").split(";");
    int j = localObject.length;
    int i;
    if ((j != 1) || (localObject[0].length() != 0))
    {
      i = 0;
      for (;;)
      {
        if (i < j) {
          try
          {
            localArrayList.add(new TimeImpl(localObject[i], getEndConstraints()));
            i += 1;
          }
          catch (IllegalArgumentException localIllegalArgumentException)
          {
            for (;;)
            {
              Log.e("ElementTimeImpl", "Malformed time value.", localIllegalArgumentException);
            }
          }
        }
      }
    }
    float f;
    if (localArrayList.size() == 0)
    {
      f = getDur();
      if (f >= 0.0F) {
        break label144;
      }
      localArrayList.add(new TimeImpl("indefinite", getEndConstraints()));
    }
    for (;;)
    {
      return new TimeListImpl(localArrayList);
      label144:
      localObject = getBegin();
      i = 0;
      while (i < ((TimeList)localObject).getLength())
      {
        localArrayList.add(new TimeImpl(((TimeList)localObject).item(i).getResolvedOffset() + f + "s", getEndConstraints()));
        i += 1;
      }
    }
  }
  
  int getEndConstraints()
  {
    return 255;
  }
  
  public short getFill()
  {
    String str = mSmilElement.getAttribute("fill");
    short s1;
    if (str.equalsIgnoreCase("freeze")) {
      s1 = 1;
    }
    short s2;
    do
    {
      return s1;
      if (str.equalsIgnoreCase("remove")) {
        return 0;
      }
      if (str.equalsIgnoreCase("hold")) {
        return 1;
      }
      if (str.equalsIgnoreCase("transition")) {
        return 1;
      }
      if (str.equalsIgnoreCase("auto")) {
        break;
      }
      s2 = getFillDefault();
      s1 = s2;
    } while (s2 != 2);
    if (((mSmilElement.getAttribute("dur").length() == 0) && (mSmilElement.getAttribute("end").length() == 0) && (mSmilElement.getAttribute("repeatCount").length() == 0) && (mSmilElement.getAttribute("repeatDur").length() == 0)) || (beginAndEndAreZero())) {
      return 1;
    }
    return 0;
  }
  
  public short getFillDefault()
  {
    short s2 = 1;
    Object localObject = mSmilElement.getAttribute("fillDefault");
    short s1;
    if (((String)localObject).equalsIgnoreCase("remove")) {
      s1 = 0;
    }
    do
    {
      do
      {
        do
        {
          return s1;
          s1 = s2;
        } while (((String)localObject).equalsIgnoreCase("freeze"));
        if (((String)localObject).equalsIgnoreCase("auto")) {
          return 2;
        }
        s1 = s2;
      } while (((String)localObject).equalsIgnoreCase("hold"));
      s1 = s2;
    } while (((String)localObject).equalsIgnoreCase("transition"));
    localObject = getParentElementTime();
    if (localObject == null) {
      return 2;
    }
    return ((ElementTimeImpl)localObject).getFillDefault();
  }
  
  abstract ElementTime getParentElementTime();
  
  public void setDur(float paramFloat)
    throws DOMException
  {
    mSmilElement.setAttribute("dur", Integer.toString((int)(1000.0F * paramFloat)) + "ms");
  }
}

/* Location:
 * Qualified Name:     com.android.mms.dom.smil.ElementTimeImpl
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */