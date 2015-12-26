package org.apache.thrift;

import java.nio.ByteBuffer;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

public final class TBaseHelper
{
  private static final Comparator comparator = new NestedStructureComparator(null);
  
  public static int byteBufferToByteArray(ByteBuffer paramByteBuffer, byte[] paramArrayOfByte, int paramInt)
  {
    int i = paramByteBuffer.remaining();
    System.arraycopy(paramByteBuffer.array(), paramByteBuffer.arrayOffset() + paramByteBuffer.position(), paramArrayOfByte, paramInt, i);
    return i;
  }
  
  public static byte[] byteBufferToByteArray(ByteBuffer paramByteBuffer)
  {
    if (wrapsFullArray(paramByteBuffer)) {
      return paramByteBuffer.array();
    }
    byte[] arrayOfByte = new byte[paramByteBuffer.remaining()];
    byteBufferToByteArray(paramByteBuffer, arrayOfByte, 0);
    return arrayOfByte;
  }
  
  public static int compareTo(byte paramByte1, byte paramByte2)
  {
    if (paramByte1 < paramByte2) {
      return -1;
    }
    if (paramByte2 < paramByte1) {
      return 1;
    }
    return 0;
  }
  
  public static int compareTo(int paramInt1, int paramInt2)
  {
    if (paramInt1 < paramInt2) {
      return -1;
    }
    if (paramInt2 < paramInt1) {
      return 1;
    }
    return 0;
  }
  
  public static int compareTo(long paramLong1, long paramLong2)
  {
    if (paramLong1 < paramLong2) {
      return -1;
    }
    if (paramLong2 < paramLong1) {
      return 1;
    }
    return 0;
  }
  
  public static int compareTo(Comparable paramComparable1, Comparable paramComparable2)
  {
    return paramComparable1.compareTo(paramComparable2);
  }
  
  public static int compareTo(String paramString1, String paramString2)
  {
    return paramString1.compareTo(paramString2);
  }
  
  public static int compareTo(List paramList1, List paramList2)
  {
    int i = compareTo(paramList1.size(), paramList2.size());
    if (i != 0) {
      return i;
    }
    i = 0;
    while (i < paramList1.size())
    {
      int j = comparator.compare(paramList1.get(i), paramList2.get(i));
      if (j != 0) {
        return j;
      }
      i += 1;
    }
    return 0;
  }
  
  public static int compareTo(Map paramMap1, Map paramMap2)
  {
    int i = compareTo(paramMap1.size(), paramMap2.size());
    if (i != 0) {
      return i;
    }
    Object localObject = new TreeMap(comparator);
    ((SortedMap)localObject).putAll(paramMap1);
    paramMap1 = ((SortedMap)localObject).entrySet().iterator();
    localObject = new TreeMap(comparator);
    ((SortedMap)localObject).putAll(paramMap2);
    paramMap2 = ((SortedMap)localObject).entrySet().iterator();
    while ((paramMap1.hasNext()) && (paramMap2.hasNext()))
    {
      localObject = (Map.Entry)paramMap1.next();
      Map.Entry localEntry = (Map.Entry)paramMap2.next();
      i = comparator.compare(((Map.Entry)localObject).getKey(), localEntry.getKey());
      if (i != 0) {
        return i;
      }
      i = comparator.compare(((Map.Entry)localObject).getValue(), localEntry.getValue());
      if (i != 0) {
        return i;
      }
    }
    return 0;
  }
  
  public static int compareTo(Set paramSet1, Set paramSet2)
  {
    int i = compareTo(paramSet1.size(), paramSet2.size());
    if (i != 0) {
      return i;
    }
    TreeSet localTreeSet = new TreeSet(comparator);
    localTreeSet.addAll(paramSet1);
    paramSet1 = new TreeSet(comparator);
    paramSet1.addAll(paramSet2);
    paramSet2 = localTreeSet.iterator();
    paramSet1 = paramSet1.iterator();
    while ((paramSet2.hasNext()) && (paramSet1.hasNext()))
    {
      i = comparator.compare(paramSet2.next(), paramSet1.next());
      if (i != 0) {
        return i;
      }
    }
    return 0;
  }
  
  public static int compareTo(boolean paramBoolean1, boolean paramBoolean2)
  {
    return Boolean.valueOf(paramBoolean1).compareTo(Boolean.valueOf(paramBoolean2));
  }
  
  public static int compareTo(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
  {
    int i = compareTo(paramArrayOfByte1.length, paramArrayOfByte2.length);
    if (i != 0) {
      return i;
    }
    i = 0;
    while (i < paramArrayOfByte1.length)
    {
      int j = compareTo(paramArrayOfByte1[i], paramArrayOfByte2[i]);
      if (j != 0) {
        return j;
      }
      i += 1;
    }
    return 0;
  }
  
  public static String paddedByteString(byte paramByte)
  {
    return Integer.toHexString((paramByte | 0x100) & 0x1FF).toUpperCase().substring(1);
  }
  
  public static ByteBuffer rightSize(ByteBuffer paramByteBuffer)
  {
    if (wrapsFullArray(paramByteBuffer)) {
      return paramByteBuffer;
    }
    return ByteBuffer.wrap(byteBufferToByteArray(paramByteBuffer));
  }
  
  public static void toString(ByteBuffer paramByteBuffer, StringBuilder paramStringBuilder)
  {
    byte[] arrayOfByte = paramByteBuffer.array();
    int k = paramByteBuffer.arrayOffset();
    int m = paramByteBuffer.limit();
    if (m - k > 128) {}
    for (int i = k + 128;; i = m)
    {
      int j = k;
      while (j < i)
      {
        if (j > k) {
          paramStringBuilder.append(" ");
        }
        paramStringBuilder.append(paddedByteString(arrayOfByte[j]));
        j += 1;
      }
    }
    if (m != i) {
      paramStringBuilder.append("...");
    }
  }
  
  public static boolean wrapsFullArray(ByteBuffer paramByteBuffer)
  {
    return (paramByteBuffer.hasArray()) && (paramByteBuffer.position() == 0) && (paramByteBuffer.arrayOffset() == 0) && (paramByteBuffer.remaining() == paramByteBuffer.capacity());
  }
  
  private static class NestedStructureComparator
    implements Comparator
  {
    public int compare(Object paramObject1, Object paramObject2)
    {
      if ((paramObject1 == null) && (paramObject2 == null)) {
        return 0;
      }
      if (paramObject1 == null) {
        return -1;
      }
      if (paramObject2 == null) {
        return 1;
      }
      if ((paramObject1 instanceof List)) {
        return TBaseHelper.compareTo((List)paramObject1, (List)paramObject2);
      }
      if ((paramObject1 instanceof Set)) {
        return TBaseHelper.compareTo((Set)paramObject1, (Set)paramObject2);
      }
      if ((paramObject1 instanceof Map)) {
        return TBaseHelper.compareTo((Map)paramObject1, (Map)paramObject2);
      }
      if ((paramObject1 instanceof byte[])) {
        return TBaseHelper.compareTo((byte[])paramObject1, (byte[])paramObject2);
      }
      return TBaseHelper.compareTo((Comparable)paramObject1, (Comparable)paramObject2);
    }
  }
}

/* Location:
 * Qualified Name:     org.apache.thrift.TBaseHelper
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */