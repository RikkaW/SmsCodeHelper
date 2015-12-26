package com.meizu.i18n.phonenumbers.geocoding;

import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeSet;

final class FlyweightMapStorage
  extends AreaCodeMapStorageStrategy
{
  private static final int INT_NUM_BYTES = 4;
  private static final int SHORT_NUM_BYTES = 2;
  private int descIndexSizeInBytes;
  private ByteBuffer descriptionIndexes;
  private String[] descriptionPool;
  private ByteBuffer phoneNumberPrefixes;
  private int prefixSizeInBytes;
  
  private void createDescriptionPool(SortedSet<String> paramSortedSet, SortedMap<Integer, String> paramSortedMap)
  {
    descIndexSizeInBytes = getOptimalNumberOfBytesForValue(paramSortedSet.size() - 1);
    descriptionIndexes = ByteBuffer.allocate(numOfEntries * descIndexSizeInBytes);
    descriptionPool = new String[paramSortedSet.size()];
    paramSortedSet.toArray(descriptionPool);
    int i = 0;
    int j = 0;
    while (i < numOfEntries)
    {
      paramSortedSet = (String)paramSortedMap.get(Integer.valueOf(readWordFromBuffer(phoneNumberPrefixes, prefixSizeInBytes, i)));
      int k = Arrays.binarySearch(descriptionPool, paramSortedSet);
      storeWordInBuffer(descriptionIndexes, descIndexSizeInBytes, j, k);
      j += 1;
      i += 1;
    }
  }
  
  private static int getOptimalNumberOfBytesForValue(int paramInt)
  {
    if (paramInt <= 32767) {
      return 2;
    }
    return 4;
  }
  
  private void readEntries(ObjectInput paramObjectInput)
  {
    numOfEntries = paramObjectInput.readInt();
    if ((phoneNumberPrefixes == null) || (phoneNumberPrefixes.capacity() < numOfEntries)) {
      phoneNumberPrefixes = ByteBuffer.allocate(numOfEntries * prefixSizeInBytes);
    }
    if ((descriptionIndexes == null) || (descriptionIndexes.capacity() < numOfEntries)) {
      descriptionIndexes = ByteBuffer.allocate(numOfEntries * descIndexSizeInBytes);
    }
    int i = 0;
    while (i < numOfEntries)
    {
      readExternalWord(paramObjectInput, prefixSizeInBytes, phoneNumberPrefixes, i);
      readExternalWord(paramObjectInput, descIndexSizeInBytes, descriptionIndexes, i);
      i += 1;
    }
  }
  
  private static void readExternalWord(ObjectInput paramObjectInput, int paramInt1, ByteBuffer paramByteBuffer, int paramInt2)
  {
    paramInt2 *= paramInt1;
    if (paramInt1 == 2)
    {
      paramByteBuffer.putShort(paramInt2, paramObjectInput.readShort());
      return;
    }
    paramByteBuffer.putInt(paramInt2, paramObjectInput.readInt());
  }
  
  private static int readWordFromBuffer(ByteBuffer paramByteBuffer, int paramInt1, int paramInt2)
  {
    paramInt2 *= paramInt1;
    if (paramInt1 == 2) {
      return paramByteBuffer.getShort(paramInt2);
    }
    return paramByteBuffer.getInt(paramInt2);
  }
  
  private static void storeWordInBuffer(ByteBuffer paramByteBuffer, int paramInt1, int paramInt2, int paramInt3)
  {
    paramInt2 *= paramInt1;
    if (paramInt1 == 2)
    {
      paramByteBuffer.putShort(paramInt2, (short)paramInt3);
      return;
    }
    paramByteBuffer.putInt(paramInt2, paramInt3);
  }
  
  private static void writeExternalWord(ObjectOutput paramObjectOutput, int paramInt1, ByteBuffer paramByteBuffer, int paramInt2)
  {
    paramInt2 *= paramInt1;
    if (paramInt1 == 2)
    {
      paramObjectOutput.writeShort(paramByteBuffer.getShort(paramInt2));
      return;
    }
    paramObjectOutput.writeInt(paramByteBuffer.getInt(paramInt2));
  }
  
  public String getDescription(int paramInt)
  {
    paramInt = readWordFromBuffer(descriptionIndexes, descIndexSizeInBytes, paramInt);
    return descriptionPool[paramInt];
  }
  
  public int getPrefix(int paramInt)
  {
    return readWordFromBuffer(phoneNumberPrefixes, prefixSizeInBytes, paramInt);
  }
  
  public void readExternal(ObjectInput paramObjectInput)
  {
    int j = 0;
    prefixSizeInBytes = paramObjectInput.readInt();
    descIndexSizeInBytes = paramObjectInput.readInt();
    int k = paramObjectInput.readInt();
    possibleLengths.clear();
    int i = 0;
    while (i < k)
    {
      possibleLengths.add(Integer.valueOf(paramObjectInput.readInt()));
      i += 1;
    }
    k = paramObjectInput.readInt();
    if (descriptionPool != null)
    {
      i = j;
      if (descriptionPool.length >= k) {}
    }
    else
    {
      descriptionPool = new String[k];
      i = j;
    }
    while (i < k)
    {
      String str = paramObjectInput.readUTF();
      descriptionPool[i] = str;
      i += 1;
    }
    readEntries(paramObjectInput);
  }
  
  public void readFromSortedMap(SortedMap<Integer, String> paramSortedMap)
  {
    TreeSet localTreeSet = new TreeSet();
    numOfEntries = paramSortedMap.size();
    prefixSizeInBytes = getOptimalNumberOfBytesForValue(((Integer)paramSortedMap.lastKey()).intValue());
    phoneNumberPrefixes = ByteBuffer.allocate(numOfEntries * prefixSizeInBytes);
    Iterator localIterator = paramSortedMap.entrySet().iterator();
    int i = 0;
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      int j = ((Integer)localEntry.getKey()).intValue();
      storeWordInBuffer(phoneNumberPrefixes, prefixSizeInBytes, i, j);
      possibleLengths.add(Integer.valueOf((int)Math.log10(j) + 1));
      localTreeSet.add(localEntry.getValue());
      i += 1;
    }
    createDescriptionPool(localTreeSet, paramSortedMap);
  }
  
  public void writeExternal(ObjectOutput paramObjectOutput)
  {
    paramObjectOutput.writeInt(prefixSizeInBytes);
    paramObjectOutput.writeInt(descIndexSizeInBytes);
    paramObjectOutput.writeInt(possibleLengths.size());
    Object localObject = possibleLengths.iterator();
    while (((Iterator)localObject).hasNext()) {
      paramObjectOutput.writeInt(((Integer)((Iterator)localObject).next()).intValue());
    }
    paramObjectOutput.writeInt(descriptionPool.length);
    localObject = descriptionPool;
    int j = localObject.length;
    int i = 0;
    while (i < j)
    {
      paramObjectOutput.writeUTF(localObject[i]);
      i += 1;
    }
    paramObjectOutput.writeInt(numOfEntries);
    i = 0;
    while (i < numOfEntries)
    {
      writeExternalWord(paramObjectOutput, prefixSizeInBytes, phoneNumberPrefixes, i);
      writeExternalWord(paramObjectOutput, descIndexSizeInBytes, descriptionIndexes, i);
      i += 1;
    }
  }
}

/* Location:
 * Qualified Name:     com.meizu.i18n.phonenumbers.geocoding.FlyweightMapStorage
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */