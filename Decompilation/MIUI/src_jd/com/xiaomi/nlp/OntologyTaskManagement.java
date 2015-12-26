package com.xiaomi.nlp;

import com.xiaomi.common.FileOperator;
import com.xiaomi.common.Log;
import com.xiaomi.common.StringProcess;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class OntologyTaskManagement
{
  private List<OntologyTaskFrame> ontologyFrame = new ArrayList();
  private HashMap<String, List<Integer>> ontologyFrameAttrs2FramIndex = new HashMap();
  private HashMap<String, Integer> ontologyName2FrameIndex = new HashMap();
  private int ontologySize = 0;
  
  private boolean loadSingleOntology(String paramString)
  {
    ArrayList localArrayList1;
    int n;
    int i;
    String str;
    ArrayList localArrayList2;
    ArrayList localArrayList3;
    ArrayList localArrayList4;
    int k;
    int j;
    int m;
    Object localObject1;
    long l1;
    long l2;
    int i1;
    for (;;)
    {
      try
      {
        localArrayList1 = FileOperator.readFile(paramString);
        n = 0;
        if (n >= localArrayList1.size()) {
          return true;
        }
      }
      catch (IOException paramString)
      {
        return false;
      }
      paramString = ((String)localArrayList1.get(n)).trim().toLowerCase();
      i = n;
      if (paramString.startsWith("defframe"))
      {
        paramString = paramString.split("(//)+")[0].split("[\\t\\s]+");
        if (paramString.length < 2)
        {
          Log.i("OntologyTaskManagement", "loadOntology Error:" + (String)localArrayList1.get(n));
          return false;
        }
        str = paramString[1].trim().toLowerCase();
        localArrayList2 = new ArrayList();
        localArrayList3 = new ArrayList();
        localArrayList4 = new ArrayList(1);
        i = -1;
        k = -1;
        j = -1;
        m = -1;
        paramString = null;
        localObject1 = null;
        l1 = -1L;
        l2 = -1L;
        i1 = n + 1;
        if (i1 < localArrayList1.size()) {
          break;
        }
        i = n;
      }
      n = i + 1;
    }
    Object localObject2 = StringProcess.trimStart(StringProcess.trimEnd(((String)localArrayList1.get(i1)).toLowerCase(), new Character[] { Character.valueOf(' '), Character.valueOf('\t') }), new Character[] { Character.valueOf(' '), Character.valueOf('\t') });
    long l3;
    int i2;
    if (((String)localObject2).equals(""))
    {
      l3 = l2;
      localObject2 = localObject1;
      i2 = m;
      m = i;
      i = i2;
      localObject1 = paramString;
      paramString = (String)localObject2;
      l2 = l1;
      l1 = l3;
    }
    for (;;)
    {
      i2 = i1 + 1;
      localObject2 = localObject1;
      i1 = m;
      l3 = l2;
      l2 = l1;
      l1 = l3;
      localObject1 = paramString;
      paramString = (String)localObject2;
      m = i;
      i = i1;
      i1 = i2;
      break;
      String[] arrayOfString;
      int i3;
      if (((String)localObject2).startsWith("{"))
      {
        l3 = l1;
        localObject2 = paramString;
        i2 = i;
        l1 = l2;
        l2 = l3;
        paramString = (String)localObject1;
        localObject1 = localObject2;
        i = m;
        m = i2;
      }
      else if (((String)localObject2).startsWith("//"))
      {
        l3 = l1;
        localObject2 = paramString;
        i2 = i;
        l1 = l2;
        l2 = l3;
        paramString = (String)localObject1;
        localObject1 = localObject2;
        i = m;
        m = i2;
      }
      else
      {
        if (((String)localObject2).startsWith("}"))
        {
          localObject2 = new OntologyTaskFrame(localArrayList4, str, localArrayList2, localArrayList3);
          n = ontologySize;
          ontologySize = (n + 1);
          ((OntologyTaskFrame)localObject2).setPriority(n);
          if (i >= 0) {
            ((OntologyTaskFrame)localObject2).setOperateID(i);
          }
          if (k >= 0) {
            ((OntologyTaskFrame)localObject2).setSummation(k);
          }
          if (l1 >= 0L) {
            ((OntologyTaskFrame)localObject2).setStartPeriodOfValidity(l1);
          }
          if (l2 >= 0L) {
            ((OntologyTaskFrame)localObject2).setEndPeriodOfValidity(l2);
          }
          if (paramString != null) {
            ((OntologyTaskFrame)localObject2).setVersion(paramString);
          }
          if (localObject1 != null) {
            ((OntologyTaskFrame)localObject2).setArea((String)localObject1);
          }
          if (j >= 0) {
            ((OntologyTaskFrame)localObject2).setPlatform(j);
          }
          if (m >= 0) {
            ((OntologyTaskFrame)localObject2).setSystemLevel(m);
          }
          ontologyFrame.add(localObject2);
          paramString = localArrayList2.iterator();
          for (;;)
          {
            if (!paramString.hasNext())
            {
              ontologyName2FrameIndex.put(str, Integer.valueOf(ontologyFrame.size() - 1));
              i = i1;
              break;
            }
            localObject1 = (String)paramString.next();
            if (!ontologyFrameAttrs2FramIndex.containsKey(localObject1)) {
              ontologyFrameAttrs2FramIndex.put(localObject1, new ArrayList());
            }
            ((List)ontologyFrameAttrs2FramIndex.get(localObject1)).add(Integer.valueOf(ontologyFrame.size() - 1));
          }
        }
        arrayOfString = ((String)localObject2).split("(:|：|(//))+");
        if (arrayOfString.length < 2)
        {
          l3 = l1;
          localObject2 = paramString;
          i2 = i;
          l1 = l2;
          l2 = l3;
          paramString = (String)localObject1;
          localObject1 = localObject2;
          i = m;
          m = i2;
        }
        else if (arrayOfString[0].equals("actionid"))
        {
          int i5 = Integer.valueOf(arrayOfString[1].trim()).intValue();
          if (arrayOfString.length > 2)
          {
            i3 = ((String)localObject2).indexOf(":", 10);
            i2 = i3;
            if (i3 < 0) {
              i2 = Integer.MAX_VALUE;
            }
            int i4 = ((String)localObject2).indexOf("：", 10);
            i3 = i4;
            if (i4 < 0) {
              i3 = Integer.MAX_VALUE;
            }
            i4 = i2;
            if (i2 > i3) {
              i4 = i3;
            }
            l3 = 1104508800000L;
            localObject2 = OntologyTaskFrame.parsePeriod(((String)localObject2).substring(i4 + 1));
            if (localObject2 != null)
            {
              if (localObject2.length == 1) {}
              for (long l4 = localObject2[0];; l4 = localObject2[1])
              {
                localArrayList4.add(new ActionIdPeriod(i5, l3, l4));
                l3 = l1;
                localObject2 = paramString;
                i2 = i;
                l1 = l2;
                l2 = l3;
                paramString = (String)localObject1;
                localObject1 = localObject2;
                i = m;
                m = i2;
                break;
                l3 = localObject2[0];
              }
            }
            localArrayList4.add(new ActionIdPeriod(i5));
            l3 = l1;
            localObject2 = paramString;
            i2 = i;
            l1 = l2;
            l2 = l3;
            paramString = (String)localObject1;
            localObject1 = localObject2;
            i = m;
            m = i2;
          }
          else
          {
            localArrayList4.add(new ActionIdPeriod(i5));
            l3 = l1;
            localObject2 = paramString;
            i2 = i;
            l1 = l2;
            l2 = l3;
            paramString = (String)localObject1;
            localObject1 = localObject2;
            i = m;
            m = i2;
          }
        }
        else if (arrayOfString[0].equals("operateid"))
        {
          i2 = Integer.valueOf(arrayOfString[1].trim()).intValue();
          l3 = l1;
          localObject2 = paramString;
          l1 = l2;
          i = m;
          l2 = l3;
          paramString = (String)localObject1;
          localObject1 = localObject2;
          m = i2;
        }
        else if (arrayOfString[0].equals("summation"))
        {
          k = Integer.valueOf(arrayOfString[1].trim()).intValue();
          l3 = l1;
          localObject2 = paramString;
          i2 = i;
          l1 = l2;
          i = m;
          l2 = l3;
          paramString = (String)localObject1;
          localObject1 = localObject2;
          m = i2;
        }
        else if (arrayOfString[0].equals("platform"))
        {
          j = Integer.valueOf(arrayOfString[1].trim()).intValue();
          l3 = l1;
          localObject2 = paramString;
          i2 = i;
          l1 = l2;
          i = m;
          l2 = l3;
          paramString = (String)localObject1;
          localObject1 = localObject2;
          m = i2;
        }
        else if (arrayOfString[0].equals("version"))
        {
          localObject2 = arrayOfString[1].trim();
          l3 = l1;
          paramString = (String)localObject1;
          localObject1 = localObject2;
          l1 = l2;
          i2 = i;
          i = m;
          l2 = l3;
          m = i2;
        }
        else if (arrayOfString[0].equals("area"))
        {
          localObject1 = arrayOfString[1].trim();
          l3 = l1;
          localObject2 = paramString;
          l1 = l2;
          i2 = i;
          i = m;
          l2 = l3;
          paramString = (String)localObject1;
          localObject1 = localObject2;
          m = i2;
        }
        else if (arrayOfString[0].equals("systemLevel"))
        {
          i2 = Integer.valueOf(arrayOfString[1].trim()).intValue();
          l3 = l1;
          localObject2 = paramString;
          m = i;
          i = i2;
          l1 = l2;
          l2 = l3;
          paramString = (String)localObject1;
          localObject1 = localObject2;
        }
        else if (arrayOfString[0].equals("period"))
        {
          localObject2 = OntologyTaskFrame.parsePeriod((String)localObject2);
          if (localObject2 != null)
          {
            if (localObject2.length == 1)
            {
              l3 = localObject2[0];
              l2 = l1;
              localObject2 = paramString;
              i2 = i;
              l1 = l3;
              paramString = (String)localObject1;
              localObject1 = localObject2;
              i = m;
              m = i2;
              continue;
            }
            l2 = localObject2[0];
            l1 = localObject2[1];
            localObject2 = paramString;
            i2 = i;
            paramString = (String)localObject1;
            localObject1 = localObject2;
            i = m;
            m = i2;
          }
        }
        else
        {
          localArrayList2.add(arrayOfString[0].trim());
          i2 = -1;
        }
      }
      try
      {
        i3 = Integer.valueOf(arrayOfString[1].trim()).intValue();
        i2 = i3;
      }
      catch (Exception localException)
      {
        for (;;) {}
      }
      localArrayList3.add(Integer.valueOf(i2));
      l3 = l1;
      localObject2 = paramString;
      i2 = i;
      l1 = l2;
      l2 = l3;
      paramString = (String)localObject1;
      localObject1 = localObject2;
      i = m;
      m = i2;
    }
  }
  
  public Ontology getOntologyByOntologyName(String paramString)
  {
    if (ontologyName2FrameIndex.containsKey(paramString)) {
      return (Ontology)ontologyFrame.get(((Integer)ontologyName2FrameIndex.get(paramString)).intValue());
    }
    return null;
  }
  
  public boolean loadOntology(String paramString)
  {
    boolean bool = false;
    int i = 0;
    for (;;)
    {
      StringBuilder localStringBuilder = new StringBuilder(String.valueOf(paramString)).append("/Task");
      if (i != 0) {}
      for (String str = String.valueOf(i); !loadSingleOntology(str + ".Ontology"); str = "")
      {
        if (ontologyFrame.size() > 0) {
          bool = true;
        }
        return bool;
      }
      i += 1;
    }
  }
  
  public String toString()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    Iterator localIterator = ontologyFrame.iterator();
    for (;;)
    {
      if (!localIterator.hasNext()) {
        return localStringBuffer.toString();
      }
      localStringBuffer.append(((OntologyTaskFrame)localIterator.next()).toString()).append("\r\n");
    }
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.nlp.OntologyTaskManagement
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */