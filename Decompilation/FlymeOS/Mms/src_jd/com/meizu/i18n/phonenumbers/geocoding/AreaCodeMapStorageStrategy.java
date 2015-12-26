package com.meizu.i18n.phonenumbers.geocoding;

import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.SortedMap;
import java.util.TreeSet;

abstract class AreaCodeMapStorageStrategy
{
  protected int numOfEntries = 0;
  protected final TreeSet<Integer> possibleLengths = new TreeSet();
  
  public abstract String getDescription(int paramInt);
  
  public int getNumOfEntries()
  {
    return numOfEntries;
  }
  
  public TreeSet<Integer> getPossibleLengths()
  {
    return possibleLengths;
  }
  
  public abstract int getPrefix(int paramInt);
  
  public abstract void readExternal(ObjectInput paramObjectInput);
  
  public abstract void readFromSortedMap(SortedMap<Integer, String> paramSortedMap);
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    int j = getNumOfEntries();
    int i = 0;
    while (i < j)
    {
      localStringBuilder.append(getPrefix(i)).append("|").append(getDescription(i)).append("\n");
      i += 1;
    }
    return localStringBuilder.toString();
  }
  
  public abstract void writeExternal(ObjectOutput paramObjectOutput);
}

/* Location:
 * Qualified Name:     com.meizu.i18n.phonenumbers.geocoding.AreaCodeMapStorageStrategy
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */