package com.xiaomi.nlp;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

public class OntologyButton
{
  private int actionCount = 1;
  public HashMap<String, String> attrs2Type;
  private String buttonName = "button";
  
  public OntologyButton(int paramInt, HashMap<String, String> paramHashMap)
  {
    actionCount = paramInt;
    attrs2Type = paramHashMap;
  }
  
  public int getActionCount()
  {
    return actionCount;
  }
  
  public HashMap<String, String> getAttrs2Type()
  {
    return attrs2Type;
  }
  
  public String toString()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("\tdefbutton").append("\r\n\t").append('{').append("\r\n");
    Iterator localIterator = attrs2Type.entrySet().iterator();
    for (;;)
    {
      if (!localIterator.hasNext())
      {
        localStringBuffer.append("\t}");
        return localStringBuffer.toString();
      }
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      localStringBuffer.append("\t\t").append((String)localEntry.getKey()).append(65306).append((String)localEntry.getValue()).append("\r\n");
    }
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.nlp.OntologyButton
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */