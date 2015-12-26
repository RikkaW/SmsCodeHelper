package com.xiaomi.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class ACAutomation
{
  Node root;
  
  public ACAutomation() {}
  
  public ACAutomation(List<String> paramList)
  {
    root = new Node(0, '\000', root);
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
      insert((String)paramList.get(i), i);
      i += 1;
    }
    Node localNode = (Node)localLinkedList.poll();
    depth = (fa.depth + 1);
    if ((localNode == root) || (fa == root))
    {
      fail = root;
      if (fail.type != 1) {
        break label330;
      }
    }
    label330:
    for (paramList = fail;; paramList = fail.lastPattern)
    {
      lastPattern = paramList;
      paramList = childs.values().iterator();
      while (paramList.hasNext()) {
        localLinkedList.add((Node)paramList.next());
      }
      break;
      for (fail = fa.fail;; fail = fail.fail) {
        if ((fail == root) || (fail.childs.containsKey(Character.valueOf(c))))
        {
          if (!fail.childs.containsKey(Character.valueOf(c))) {
            break;
          }
          fail = ((Node)fail.childs.get(Character.valueOf(c)));
          break;
        }
      }
    }
  }
  
  public ACAutomation(List<? extends ACAutomationable> paramList, boolean paramBoolean)
  {
    root = new Node(0, '\000', root);
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
      insert(((ACAutomationable)paramList.get(i)).getWord(), i);
      i += 1;
    }
    Node localNode = (Node)localLinkedList.poll();
    depth = (fa.depth + 1);
    if ((localNode == root) || (fa == root))
    {
      fail = root;
      if (fail.type != 1) {
        break label340;
      }
    }
    label340:
    for (paramList = fail;; paramList = fail.lastPattern)
    {
      lastPattern = paramList;
      paramList = childs.values().iterator();
      while (paramList.hasNext()) {
        localLinkedList.add((Node)paramList.next());
      }
      break;
      for (fail = fa.fail;; fail = fail.fail) {
        if ((fail == root) || (fail.childs.containsKey(Character.valueOf(c))))
        {
          if (!fail.childs.containsKey(Character.valueOf(c))) {
            break;
          }
          fail = ((Node)fail.childs.get(Character.valueOf(c)));
          break;
        }
      }
    }
  }
  
  private void insert(String paramString, int paramInt)
  {
    Node localNode = root;
    int i = 0;
    for (;;)
    {
      if (i >= paramString.length())
      {
        type = 1;
        patternId = paramInt;
        return;
      }
      char c = paramString.charAt(i);
      if (!childs.containsKey(Character.valueOf(c))) {
        childs.put(Character.valueOf(c), new Node(0, c, localNode));
      }
      localNode = (Node)childs.get(Character.valueOf(c));
      i += 1;
    }
  }
  
  protected List<int[]> filterLeftMost(List<int[]> paramList)
  {
    ArrayList localArrayList = new ArrayList();
    if (paramList.size() == 0) {
      return localArrayList;
    }
    int i = 0;
    int j = Integer.MAX_VALUE;
    label26:
    int k;
    if (i < paramList.size())
    {
      k = ((int[])paramList.get(i))[1];
      if (j >= k) {
        break label63;
      }
    }
    for (;;)
    {
      i += 1;
      break label26;
      break;
      label63:
      if (k < j) {
        localArrayList.clear();
      }
      j = Math.min(k, j);
      localArrayList.add((int[])paramList.get(i));
    }
  }
  
  protected int[] filterLongest(List<int[]> paramList)
  {
    if (paramList.size() == 0) {
      return null;
    }
    int j = 0;
    int i = 0;
    if (i >= paramList.size()) {
      return (int[])paramList.get(j);
    }
    if (((int[])paramList.get(i))[2] - ((int[])paramList.get(i))[1] + 1 <= ((int[])paramList.get(j))[2] - ((int[])paramList.get(j))[1] + 1) {}
    for (;;)
    {
      i += 1;
      break;
      j = i;
    }
  }
  
  public List<int[]> filterNoOverlay(List<int[]> paramList)
  {
    ArrayList localArrayList = new ArrayList();
    if (paramList.size() == 0) {
      return localArrayList;
    }
    Collections.sort(paramList, new Comparator()
    {
      public int compare(int[] paramAnonymousArrayOfInt1, int[] paramAnonymousArrayOfInt2)
      {
        if (paramAnonymousArrayOfInt1[1] != paramAnonymousArrayOfInt2[1]) {
          return paramAnonymousArrayOfInt1[1] - paramAnonymousArrayOfInt2[1];
        }
        return paramAnonymousArrayOfInt2[2] - paramAnonymousArrayOfInt1[2];
      }
    });
    int i = 0;
    label33:
    if (i < paramList.size()) {
      if ((localArrayList.size() <= 0) || (((int[])paramList.get(i))[1] > ((int[])localArrayList.get(localArrayList.size() - 1))[2])) {
        break label93;
      }
    }
    for (;;)
    {
      i += 1;
      break label33;
      break;
      label93:
      localArrayList.add((int[])paramList.get(i));
    }
  }
  
  public List<int[]> find(String paramString)
  {
    return filterNoOverlay(match(paramString, 0));
  }
  
  public List<int[]> find(String paramString, int paramInt)
  {
    return filterNoOverlay(match(paramString, paramInt));
  }
  
  public int[] findFirst(String paramString, int paramInt)
  {
    return filterLongest(findFirstAll(paramString, paramInt));
  }
  
  public int[] findFirst(String paramString, int paramInt1, int paramInt2)
  {
    return filterLongest(findFirstAll(paramString, paramInt1, paramInt2));
  }
  
  public List<int[]> findFirstAll(String paramString, int paramInt)
  {
    paramString = filterLeftMost(match(paramString, paramInt));
    Collections.sort(paramString, new Comparator()
    {
      public int compare(int[] paramAnonymousArrayOfInt1, int[] paramAnonymousArrayOfInt2)
      {
        return paramAnonymousArrayOfInt2[2] - paramAnonymousArrayOfInt1[2];
      }
    });
    return paramString;
  }
  
  public List<int[]> findFirstAll(String paramString, int paramInt1, int paramInt2)
  {
    paramString = filterLeftMost(match(paramString, paramInt1, paramInt2));
    Collections.sort(paramString, new Comparator()
    {
      public int compare(int[] paramAnonymousArrayOfInt1, int[] paramAnonymousArrayOfInt2)
      {
        return paramAnonymousArrayOfInt2[2] - paramAnonymousArrayOfInt1[2];
      }
    });
    return paramString;
  }
  
  public List<int[]> match(String paramString, int paramInt)
  {
    ArrayList localArrayList = new ArrayList();
    if ((paramString.length() <= paramInt) || (paramInt < 0)) {
      return localArrayList;
    }
    Object localObject1 = root;
    label30:
    char c;
    if (paramInt < paramString.length()) {
      c = paramString.charAt(paramInt);
    }
    for (;;)
    {
      if ((((Node)localObject1).isRoot()) || (childs.containsKey(Character.valueOf(c))))
      {
        Object localObject2 = localObject1;
        if (childs.containsKey(Character.valueOf(c)))
        {
          localObject2 = (Node)childs.get(Character.valueOf(c));
          localObject1 = localObject2;
          if (localObject1 != root) {
            break label143;
          }
        }
        paramInt += 1;
        localObject1 = localObject2;
        break label30;
        break;
      }
      localObject1 = fail;
    }
    label143:
    if (type == 0) {}
    for (;;)
    {
      localObject1 = lastPattern;
      break;
      localArrayList.add(new int[] { patternId, paramInt - depth + 1, paramInt });
    }
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
    if (paramInt1 <= paramInt2) {
      c = paramString.charAt(paramInt1);
    }
    for (;;)
    {
      if ((((Node)localObject1).isRoot()) || (childs.containsKey(Character.valueOf(c))))
      {
        Object localObject2 = localObject1;
        if (childs.containsKey(Character.valueOf(c)))
        {
          localObject2 = (Node)childs.get(Character.valueOf(c));
          localObject1 = localObject2;
          if (localObject1 != root) {
            break label144;
          }
        }
        paramInt1 += 1;
        localObject1 = localObject2;
        break label30;
        break;
      }
      localObject1 = fail;
    }
    label144:
    if (type == 0) {}
    for (;;)
    {
      localObject1 = lastPattern;
      break;
      localArrayList.add(new int[] { patternId, paramInt1 - depth + 1, paramInt1 });
    }
  }
  
  public List<int[]> startWith(String paramString, int paramInt)
  {
    Node localNode = root;
    ArrayList localArrayList = new ArrayList();
    int i = paramInt;
    for (;;)
    {
      if (i >= paramString.length()) {}
      while (!childs.containsKey(Character.valueOf(paramString.charAt(i)))) {
        return localArrayList;
      }
      localNode = (Node)childs.get(Character.valueOf(paramString.charAt(i)));
      if (type == 1) {
        localArrayList.add(new int[] { patternId, paramInt, paramInt + i });
      }
      i += 1;
    }
  }
  
  class Node
  {
    char c;
    Map<Character, Node> childs;
    int depth;
    Node fa;
    Node fail;
    Node lastPattern;
    int patternId;
    int type;
    
    Node(int paramInt, char paramChar, Node paramNode)
    {
      type = paramInt;
      depth = -1;
      c = paramChar;
      childs = new HashMap();
      this$1 = paramNode;
      if (paramNode == null) {
        this$1 = this;
      }
      fa = ACAutomation.this;
      fail = this;
      lastPattern = this;
    }
    
    public boolean isRoot()
    {
      return fa == this;
    }
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.common.ACAutomation
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */