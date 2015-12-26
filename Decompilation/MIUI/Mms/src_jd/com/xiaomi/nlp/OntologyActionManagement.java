package com.xiaomi.nlp;

import com.xiaomi.common.FileOperator;
import com.xiaomi.common.Log;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class OntologyActionManagement
{
  private static Object actionExtendLock = new Object();
  private HashMap<Integer, List<Integer>> actionID2actionOntologyIndexs = new HashMap();
  private List<OntologyAction> actionOntology = new ArrayList();
  private boolean changedAction = false;
  private String dir = null;
  private Set<Integer> updatedActions;
  
  public int getActionCount(int paramInt1, int paramInt2)
  {
    OntologyAction localOntologyAction = getOntology(paramInt1);
    if (localOntologyAction != null) {
      return localOntologyAction.getActionCount(paramInt2);
    }
    return 0;
  }
  
  public String getBtnAction(int paramInt1, int paramInt2, int paramInt3)
  {
    OntologyAction localOntologyAction = getOntology(paramInt1);
    if (localOntologyAction != null) {
      return localOntologyAction.getBtnAction(paramInt2, paramInt3);
    }
    return "";
  }
  
  public int getBtnNumber(int paramInt)
  {
    OntologyAction localOntologyAction = getOntology(paramInt);
    if (localOntologyAction != null) {
      return localOntologyAction.getBtnNumber();
    }
    return 0;
  }
  
  public String getBtnTitle(int paramInt1, int paramInt2)
  {
    OntologyAction localOntologyAction = getOntology(paramInt1);
    if (localOntologyAction != null) {
      return localOntologyAction.getBtnTitle(paramInt2);
    }
    return "";
  }
  
  public OntologyAction getOntology(int paramInt)
  {
    Object localObject = (List)actionID2actionOntologyIndexs.get(Integer.valueOf(paramInt));
    if ((localObject == null) || (((List)localObject).size() == 0)) {
      return null;
    }
    localObject = ((List)localObject).iterator();
    Integer localInteger;
    do
    {
      if (!((Iterator)localObject).hasNext()) {
        return null;
      }
      localInteger = (Integer)((Iterator)localObject).next();
    } while (!((OntologyAction)actionOntology.get(localInteger.intValue())).matchPeriod());
    return (OntologyAction)actionOntology.get(localInteger.intValue());
  }
  
  public boolean loadOntology(String paramString)
  {
    boolean bool = false;
    dir = paramString;
    int i = 0;
    for (;;)
    {
      StringBuilder localStringBuilder = new StringBuilder(String.valueOf(paramString)).append("/Action");
      if (i != 0) {}
      for (String str = String.valueOf(i); !loadSingleOntology(str + ".Ontology", false); str = "")
      {
        if (!loadSingleOntology(paramString + "/ActionExtend.Ontology", true)) {
          Log.i("OntologyActionManagement", "Load ActionExtend Ontology Error!!!");
        }
        if (actionOntology.size() > 0) {
          bool = true;
        }
        return bool;
      }
      i += 1;
    }
  }
  
  public boolean loadSingleOntology(String paramString, boolean paramBoolean)
  {
    int k;
    for (;;)
    {
      try
      {
        if (!FileOperator.fileExist(paramString)) {
          break;
        }
        if (paramBoolean) {
          synchronized (actionExtendLock)
          {
            paramString = FileOperator.readFile(paramString);
            if ((paramBoolean) && (updatedActions == null)) {
              updatedActions = new HashSet();
            }
            k = 0;
            if (k < paramString.size()) {
              break label108;
            }
            return true;
          }
        }
        paramString = FileOperator.readFile(paramString);
      }
      catch (IOException paramString)
      {
        Log.i("OntologyActionManagement", "Load Action Ontology Error!!!" + paramString.getMessage());
        return false;
      }
    }
    return false;
    label108:
    ??? = ((String)paramString.get(k)).trim();
    if (((String)???).startsWith("defframe"))
    {
      ??? = ???.split("(//)+")[0].split("[\\t\\s]+");
      if (???.length >= 2) {
        break label207;
      }
    }
    ArrayList localArrayList;
    int i;
    int j;
    long l1;
    long l2;
    label198:
    label207:
    for (??? = "actionframe";; ??? = ???[1].trim().toLowerCase())
    {
      localArrayList = new ArrayList();
      i = k + 1;
      j = -1;
      l1 = -1L;
      l2 = -1L;
      if (i < paramString.size()) {
        break label222;
      }
      k += 1;
      break;
    }
    label222:
    Object localObject2 = ((String)paramString.get(i)).trim();
    int m;
    long l3;
    if (((String)localObject2).startsWith("{"))
    {
      m = j;
      l3 = l2;
      j = i;
      l2 = l1;
      l1 = l3;
      i = m;
    }
    for (;;)
    {
      m = j + 1;
      j = i;
      l3 = l2;
      i = m;
      l2 = l1;
      l1 = l3;
      break;
      if (((String)localObject2).startsWith("//"))
      {
        m = i;
        i = j;
        l3 = l1;
        l1 = l2;
        l2 = l3;
        j = m;
      }
      else
      {
        Object localObject3;
        if (((String)localObject2).startsWith("}"))
        {
          if (l2 >= 0L)
          {
            k = i;
            if (l2 < System.currentTimeMillis()) {
              break label198;
            }
          }
          localObject3 = (List)actionID2actionOntologyIndexs.get(Integer.valueOf(j));
          localObject2 = localObject3;
          if (localObject3 == null) {
            localObject2 = new ArrayList(1);
          }
          ((List)localObject2).add(Integer.valueOf(actionOntology.size()));
          actionID2actionOntologyIndexs.put(Integer.valueOf(j), localObject2);
          if (paramBoolean) {
            updatedActions.add(Integer.valueOf(j));
          }
          ??? = new OntologyAction((String)???, j, localArrayList);
          if (l1 >= 0L) {
            ((OntologyAction)???).setStartPeriodOfValidity(l1);
          }
          if (l2 >= 0L) {
            ((OntologyAction)???).setEndPeriodOfValidity(l2);
          }
          actionOntology.add(???);
          k = i;
          break label198;
        }
        if (((String)localObject2).toLowerCase().startsWith("actionid"))
        {
          localObject2 = ((String)localObject2).split("[:：]", 2);
          if (localObject2.length != 2)
          {
            Log.i("OntologyActionManagement", "loadOntology Error:" + (String)paramString.get(i));
            m = i;
            i = j;
            l3 = l1;
            l1 = l2;
            l2 = l3;
            j = m;
          }
          else
          {
            m = Integer.parseInt(localObject2[1]);
            j = i;
            i = m;
            l3 = l1;
            l1 = l2;
            l2 = l3;
          }
        }
        else if ((((String)localObject2).startsWith("defbutton")) && (j >= 0))
        {
          localObject2 = new HashMap();
          int n = 0;
          m = i + 1;
          label658:
          if (m >= paramString.size())
          {
            m = i;
            i = j;
            l3 = l1;
            l1 = l2;
            l2 = l3;
            j = m;
          }
          else
          {
            localObject3 = ((String)paramString.get(m)).trim();
            int i1;
            if (((String)localObject3).startsWith("{")) {
              i1 = n;
            }
            for (;;)
            {
              m += 1;
              n = i1;
              break label658;
              i1 = n;
              if (!((String)localObject3).startsWith("//"))
              {
                if (((String)localObject3).startsWith("}"))
                {
                  localArrayList.add(new OntologyButton(n, (HashMap)localObject2));
                  i = j;
                  l3 = l1;
                  l1 = l2;
                  l2 = l3;
                  j = m;
                  break;
                }
                localObject3 = ((String)localObject3).split("[:：]", 2);
                if (localObject3.length != 2)
                {
                  Log.i("OntologyActionManagement", "loadOntology Error:" + (String)paramString.get(i));
                  i1 = n;
                }
                else
                {
                  i1 = n;
                  if (localObject3[0].startsWith("action"))
                  {
                    localObject3[0] = ("action" + n);
                    i1 = n + 1;
                  }
                  ((HashMap)localObject2).put(localObject3[0], localObject3[1]);
                }
              }
            }
          }
        }
        else
        {
          if (((String)localObject2).startsWith("period"))
          {
            localObject2 = OntologyTaskFrame.parsePeriod((String)localObject2);
            if (localObject2 != null)
            {
              if (localObject2.length == 1)
              {
                l2 = localObject2[0];
                m = i;
                i = j;
                l3 = l1;
                l1 = l2;
                l2 = l3;
                j = m;
                continue;
              }
              l2 = localObject2[0];
              l1 = localObject2[1];
              m = i;
              i = j;
              j = m;
              continue;
            }
          }
          m = i;
          i = j;
          l3 = l1;
          l1 = l2;
          l2 = l3;
          j = m;
        }
      }
    }
  }
  
  public String toString()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    Iterator localIterator = actionOntology.iterator();
    for (;;)
    {
      if (!localIterator.hasNext()) {
        return localStringBuffer.toString();
      }
      localStringBuffer.append(((OntologyAction)localIterator.next()).toString()).append("\r\n");
    }
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.nlp.OntologyActionManagement
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */