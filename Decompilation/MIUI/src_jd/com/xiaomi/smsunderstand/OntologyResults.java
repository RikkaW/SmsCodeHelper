package com.xiaomi.smsunderstand;

import com.xiaomi.nlp.OntologyTaskFrame;
import java.util.ArrayList;
import java.util.List;

public class OntologyResults
  extends OntologyTaskFrame
  implements Comparable<OntologyResults>
{
  private static String valueDefault = null;
  private int actionID;
  private List<AttrInfo> attrInfos;
  
  public OntologyResults(OntologyTaskFrame paramOntologyTaskFrame)
  {
    actionID = paramOntologyTaskFrame.getActionID();
    operateID = paramOntologyTaskFrame.getOperateID();
    summation = paramOntologyTaskFrame.getSummation();
    priority = paramOntologyTaskFrame.getPriority();
    ontologyName = paramOntologyTaskFrame.getOntologyName();
    taskType = paramOntologyTaskFrame.getTaskType();
    setStartPeriodOfValidity(paramOntologyTaskFrame.getStartPeriodOfValidity());
    setEndPeriodOfValidity(paramOntologyTaskFrame.getEndPeriodOfValidity());
    attrInfos = new ArrayList(paramOntologyTaskFrame.getAttrCounts());
    int i = 0;
    for (;;)
    {
      if (i >= paramOntologyTaskFrame.getAttrCounts()) {
        return;
      }
      attrInfos.add(new AttrInfo((String)paramOntologyTaskFrame.getAttrNames().get(i), valueDefault, ((Integer)paramOntologyTaskFrame.getAttrType().get(i)).intValue(), 0, 0, 0));
      i += 1;
    }
  }
  
  public static String getValueDefault()
  {
    return valueDefault;
  }
  
  public int compareTo(OntologyResults paramOntologyResults)
  {
    if (priority > priority) {
      return -1;
    }
    if (priority < priority) {
      return 1;
    }
    return 0;
  }
  
  public int getActionID()
  {
    return actionID;
  }
  
  public AttrInfo getAttrByName(String paramString, OntologyTaskFrame paramOntologyTaskFrame)
  {
    int j = paramOntologyTaskFrame.getAttrIndexByName(paramString);
    int i = j;
    if (j < 0)
    {
      i = j;
      if (summation > 0) {
        i = paramOntologyTaskFrame.getFatherAttrIndex(paramString);
      }
    }
    if ((i >= 0) && (i < attrInfos.size())) {
      return (AttrInfo)attrInfos.get(i);
    }
    return null;
  }
  
  public AttrInfo getAttrByType(int paramInt, OntologyTaskFrame paramOntologyTaskFrame)
  {
    paramInt = paramOntologyTaskFrame.getAttrIndexByType(paramInt);
    if ((paramInt >= 0) && (paramInt < attrInfos.size())) {
      return (AttrInfo)attrInfos.get(paramInt);
    }
    return null;
  }
  
  public List<AttrInfo> getAttrInfos()
  {
    return attrInfos;
  }
  
  public String toString()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("frameResult").append(" ").append(ontologyName).append("\r\n").append('{').append("\r\n");
    int i = 0;
    for (;;)
    {
      if (i >= attrInfos.size())
      {
        if (actionID >= 0) {
          localStringBuffer.append("\tactionID：").append(actionID).append("\r\n");
        }
        if (operateID >= 0) {
          localStringBuffer.append("\toperateID：").append(operateID).append("\r\n");
        }
        String str = generalPeriod(getStartPeriodOfValidity(), getEndPeriodOfValidity());
        if (str != null) {
          localStringBuffer.append("\tperiod：").append(str).append("\r\n");
        }
        if (summation >= 0) {
          localStringBuffer.append("\tsummation：").append(summation).append("\r\n");
        }
        localStringBuffer.append('}').append("\r\n");
        return localStringBuffer.toString();
      }
      localStringBuffer.append("\t").append(((AttrInfo)attrInfos.get(i)).getName()).append(65306).append(((AttrInfo)attrInfos.get(i)).getType()).append(65306).append(((AttrInfo)attrInfos.get(i)).getValue()).append("\r\n");
      i += 1;
    }
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.smsunderstand.OntologyResults
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */