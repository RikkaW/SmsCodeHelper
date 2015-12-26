package com.meizu.i18n.phonenumbers.geocoding;

import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeSet;

class DefaultMapStorage
  extends AreaCodeMapStorageStrategy
{
  private String[] descriptions;
  private int[] phoneNumberPrefixes;
  
  public String getDescription(int paramInt)
  {
    return descriptions[paramInt];
  }
  
  public int getPrefix(int paramInt)
  {
    return phoneNumberPrefixes[paramInt];
  }
  
  public void readExternal(ObjectInput paramObjectInput)
  {
    int j = 0;
    numOfEntries = paramObjectInput.readInt();
    if ((phoneNumberPrefixes == null) || (phoneNumberPrefixes.length < numOfEntries)) {
      phoneNumberPrefixes = new int[numOfEntries];
    }
    if ((descriptions == null) || (descriptions.length < numOfEntries)) {
      descriptions = new String[numOfEntries];
    }
    int i = 0;
    while (i < numOfEntries)
    {
      phoneNumberPrefixes[i] = paramObjectInput.readInt();
      descriptions[i] = paramObjectInput.readUTF();
      i += 1;
    }
    int k = paramObjectInput.readInt();
    possibleLengths.clear();
    i = j;
    while (i < k)
    {
      possibleLengths.add(Integer.valueOf(paramObjectInput.readInt()));
      i += 1;
    }
  }
  
  public void readFromSortedMap(SortedMap<Integer, String> paramSortedMap)
  {
    numOfEntries = paramSortedMap.size();
    phoneNumberPrefixes = new int[numOfEntries];
    descriptions = new String[numOfEntries];
    Iterator localIterator = paramSortedMap.keySet().iterator();
    int i = 0;
    while (localIterator.hasNext())
    {
      int j = ((Integer)localIterator.next()).intValue();
      phoneNumberPrefixes[i] = j;
      possibleLengths.add(Integer.valueOf((int)Math.log10(j) + 1));
      i += 1;
    }
    paramSortedMap.values().toArray(descriptions);
  }
  
  public void writeExternal(ObjectOutput paramObjectOutput)
  {
    paramObjectOutput.writeInt(numOfEntries);
    int i = 0;
    while (i < numOfEntries)
    {
      paramObjectOutput.writeInt(phoneNumberPrefixes[i]);
      paramObjectOutput.writeUTF(descriptions[i]);
      i += 1;
    }
    paramObjectOutput.writeInt(possibleLengths.size());
    Iterator localIterator = possibleLengths.iterator();
    while (localIterator.hasNext()) {
      paramObjectOutput.writeInt(((Integer)localIterator.next()).intValue());
    }
  }
}

/* Location:
 * Qualified Name:     com.meizu.i18n.phonenumbers.geocoding.DefaultMapStorage
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */