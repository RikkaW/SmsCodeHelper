package com.xiaomi.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class ConstraintACAutomation<T extends ConstraintACAutomationable>
  extends ACAutomation
{
  public List<ACAutomation.Node> InnerNode;
  
  public ConstraintACAutomation(List<T> paramList)
  {
    InnerNode = new ArrayList(paramList.size());
    root = new ACAutomation.Node(this, 0, '\000', root);
    int i = 0;
    LinkedList localLinkedList;
    for (;;)
    {
      if (i >= paramList.size())
      {
        localLinkedList = new LinkedList();
        localLinkedList.add(root);
        if (!localLinkedList.isEmpty()) {
          break;
        }
        return;
      }
      insert((ConstraintACAutomationable)paramList.get(i), i);
      i += 1;
    }
    ACAutomation.Node localNode = (ACAutomation.Node)localLinkedList.poll();
    depth = (fa.depth + 1);
    if ((localNode == root) || (fa == root))
    {
      fail = root;
      if (fail.type == 0) {
        break label346;
      }
    }
    label346:
    for (paramList = fail;; paramList = fail.lastPattern)
    {
      lastPattern = paramList;
      paramList = childs.values().iterator();
      while (paramList.hasNext()) {
        localLinkedList.add((ACAutomation.Node)paramList.next());
      }
      break;
      for (fail = fa.fail;; fail = fail.fail) {
        if ((fail == root) || (fail.childs.containsKey(Character.valueOf(c))))
        {
          if (!fail.childs.containsKey(Character.valueOf(c))) {
            break;
          }
          fail = ((ACAutomation.Node)fail.childs.get(Character.valueOf(c)));
          break;
        }
      }
    }
  }
  
  private void insert(T paramT, int paramInt)
  {
    if (paramInt != InnerNode.size()) {
      throw new RuntimeException("unconsistent in here");
    }
    ACAutomation.Node localNode = root;
    String str = paramT.getWord();
    int i = 0;
    for (;;)
    {
      if (i >= str.length())
      {
        type = paramT.getType();
        patternId = paramInt;
        InnerNode.add(localNode);
        return;
      }
      char c = str.charAt(i);
      if (!childs.containsKey(Character.valueOf(c))) {
        childs.put(Character.valueOf(c), new ACAutomation.Node(this, 0, c, localNode));
      }
      localNode = (ACAutomation.Node)childs.get(Character.valueOf(c));
      i += 1;
    }
  }
  
  public int contains(String paramString, int paramInt)
  {
    int k = -1;
    ACAutomation.Node localNode = root;
    int i = 0;
    for (;;)
    {
      int j;
      if (i >= paramString.length())
      {
        j = k;
        if ((type & paramInt) != 0) {
          j = patternId;
        }
      }
      char c;
      do
      {
        return j;
        c = paramString.charAt(i);
        j = k;
      } while (!childs.containsKey(Character.valueOf(c)));
      localNode = (ACAutomation.Node)childs.get(Character.valueOf(c));
      i += 1;
    }
  }
  
  public List<int[]> find(String paramString, int paramInt)
  {
    return filterNoOverlay(match(paramString, 0, paramInt));
  }
  
  public int[] findFirst(String paramString, int paramInt1, int paramInt2, int paramInt3)
  {
    return filterLongest(findFirstAll(paramString, paramInt1, paramInt2, paramInt3));
  }
  
  public List<int[]> findFirstAll(String paramString, int paramInt1, int paramInt2, int paramInt3)
  {
    paramString = filterLeftMost(match(paramString, paramInt1, paramInt2, paramInt3));
    Collections.sort(paramString, new Comparator()
    {
      public int compare(int[] paramAnonymousArrayOfInt1, int[] paramAnonymousArrayOfInt2)
      {
        return paramAnonymousArrayOfInt2[2] - paramAnonymousArrayOfInt1[2];
      }
    });
    return paramString;
  }
  
  public List<int[]> match(String paramString, int paramInt1, int paramInt2)
  {
    ArrayList localArrayList = new ArrayList();
    if ((paramString.length() <= paramInt1) || (paramInt1 < 0)) {
      return localArrayList;
    }
    Object localObject1 = root;
    label30:
    char c;
    if (paramInt1 < paramString.length()) {
      c = paramString.charAt(paramInt1);
    }
    for (;;)
    {
      if ((((ACAutomation.Node)localObject1).isRoot()) || (childs.containsKey(Character.valueOf(c))))
      {
        Object localObject2 = localObject1;
        if (childs.containsKey(Character.valueOf(c)))
        {
          localObject2 = (ACAutomation.Node)childs.get(Character.valueOf(c));
          localObject1 = localObject2;
          if (localObject1 != root) {
            break label147;
          }
        }
        paramInt1 += 1;
        localObject1 = localObject2;
        break label30;
        break;
      }
      localObject1 = fail;
    }
    label147:
    if ((type & paramInt2) == 0) {}
    for (;;)
    {
      localObject1 = lastPattern;
      break;
      localArrayList.add(new int[] { patternId, paramInt1 - depth + 1, paramInt1 });
    }
  }
  
  public List<int[]> match(String paramString, int paramInt1, int paramInt2, int paramInt3)
  {
    ArrayList localArrayList = new ArrayList();
    if ((paramString.length() <= paramInt1) || (paramInt1 < 0)) {
      return localArrayList;
    }
    Object localObject1 = root;
    label30:
    char c;
    if ((paramInt1 < paramString.length()) && (paramInt1 <= paramInt2)) {
      c = paramString.charAt(paramInt1);
    }
    for (;;)
    {
      if ((((ACAutomation.Node)localObject1).isRoot()) || (childs.containsKey(Character.valueOf(c))))
      {
        Object localObject2 = localObject1;
        if (childs.containsKey(Character.valueOf(c)))
        {
          localObject2 = (ACAutomation.Node)childs.get(Character.valueOf(c));
          localObject1 = localObject2;
          if (localObject1 != root) {
            break label152;
          }
        }
        paramInt1 += 1;
        localObject1 = localObject2;
        break label30;
        break;
      }
      localObject1 = fail;
    }
    label152:
    if ((type & paramInt3) == 0) {}
    for (;;)
    {
      localObject1 = lastPattern;
      break;
      localArrayList.add(new int[] { patternId, paramInt1 - depth + 1, paramInt1 });
    }
  }
  
  public List<int[]> startWith(String paramString, int paramInt1, int paramInt2)
  {
    ACAutomation.Node localNode = root;
    ArrayList localArrayList = new ArrayList();
    int i = paramInt1;
    for (;;)
    {
      if (i >= paramString.length()) {}
      while (!childs.containsKey(Character.valueOf(paramString.charAt(i)))) {
        return localArrayList;
      }
      localNode = (ACAutomation.Node)childs.get(Character.valueOf(paramString.charAt(i)));
      if ((type & paramInt2) != 0) {
        localArrayList.add(new int[] { patternId, paramInt1, paramInt1 + i });
      }
      i += 1;
    }
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.common.ConstraintACAutomation
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */