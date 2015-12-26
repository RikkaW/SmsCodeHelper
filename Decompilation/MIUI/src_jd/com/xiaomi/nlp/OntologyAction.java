package com.xiaomi.nlp;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class OntologyAction
  extends Ontology
{
  private int actionID = -1;
  public List<OntologyButton> buttons;
  private long endPeriodOfValidity = 4102416000000L;
  private long startPeriodOfValidity = 1104508800000L;
  
  public OntologyAction(String paramString, int paramInt, List<OntologyButton> paramList)
  {
    ontologyName = paramString;
    actionID = paramInt;
    buttons = paramList;
  }
  
  public int getActionCount(int paramInt)
  {
    if ((buttons != null) && (buttons.size() > paramInt)) {
      return ((OntologyButton)buttons.get(paramInt)).getActionCount();
    }
    return 0;
  }
  
  public String getBtnAction(int paramInt1, int paramInt2)
  {
    if ((buttons != null) && (buttons.size() > paramInt1)) {
      return (String)((OntologyButton)buttons.get(paramInt1)).getAttrs2Type().get("action" + paramInt2);
    }
    return "";
  }
  
  public int getBtnNumber()
  {
    return buttons.size();
  }
  
  public String getBtnTitle(int paramInt)
  {
    if ((buttons != null) && (buttons.size() > paramInt)) {
      return (String)((OntologyButton)buttons.get(paramInt)).getAttrs2Type().get("title");
    }
    return "";
  }
  
  public boolean matchPeriod()
  {
    long l = System.currentTimeMillis();
    return (startPeriodOfValidity <= l) && (endPeriodOfValidity >= l);
  }
  
  public void setEndPeriodOfValidity(long paramLong)
  {
    endPeriodOfValidity = paramLong;
  }
  
  public void setStartPeriodOfValidity(long paramLong)
  {
    startPeriodOfValidity = paramLong;
  }
  
  public String toString()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("defframe").append(' ').append(ontologyName).append("\r\n").append('{').append("\r\n");
    localStringBuffer.append("\tactionID：").append(actionID).append("\r\n");
    Object localObject = generalPeriod(startPeriodOfValidity, endPeriodOfValidity);
    if (localObject != null) {
      localStringBuffer.append("\tperiod：").append((String)localObject).append("\r\n");
    }
    localObject = buttons.iterator();
    for (;;)
    {
      if (!((Iterator)localObject).hasNext())
      {
        localStringBuffer.append('}').append("\r\n");
        return localStringBuffer.toString();
      }
      localStringBuffer.append(((OntologyButton)((Iterator)localObject).next()).toString()).append("\r\n");
    }
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.nlp.OntologyAction
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */