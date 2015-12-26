package com.meizu.i18n.phonenumbers.geocoding;

import com.meizu.i18n.phonenumbers.PhoneNumberUtil;
import com.meizu.i18n.phonenumbers.Phonenumber.PhoneNumber;
import java.io.ByteArrayOutputStream;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.logging.Logger;

public class AreaCodeMap
  implements Externalizable
{
  private static final Logger LOGGER = Logger.getLogger(AreaCodeMap.class.getName());
  private AreaCodeMapStorageStrategy areaCodeMapStorage;
  private final PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
  
  private int binarySearch(int paramInt1, int paramInt2, long paramLong)
  {
    int j = 0;
    int i = paramInt2;
    paramInt2 = paramInt1;
    paramInt1 = j;
    for (;;)
    {
      if (paramInt2 <= i)
      {
        paramInt1 = paramInt2 + i >>> 1;
        j = areaCodeMapStorage.getPrefix(paramInt1);
        if (j != paramLong) {}
      }
      else
      {
        return paramInt1;
      }
      if (j > paramLong)
      {
        paramInt1 -= 1;
        i = paramInt1;
      }
      else
      {
        paramInt2 = paramInt1 + 1;
      }
    }
  }
  
  private AreaCodeMapStorageStrategy createDefaultMapStorage()
  {
    return new DefaultMapStorage();
  }
  
  private AreaCodeMapStorageStrategy createFlyweightMapStorage()
  {
    return new FlyweightMapStorage();
  }
  
  private static int getSizeOfAreaCodeMapStorage(AreaCodeMapStorageStrategy paramAreaCodeMapStorageStrategy, SortedMap<Integer, String> paramSortedMap)
  {
    paramAreaCodeMapStorageStrategy.readFromSortedMap(paramSortedMap);
    paramSortedMap = new ByteArrayOutputStream();
    ObjectOutputStream localObjectOutputStream = new ObjectOutputStream(paramSortedMap);
    paramAreaCodeMapStorageStrategy.writeExternal(localObjectOutputStream);
    localObjectOutputStream.flush();
    int i = paramSortedMap.size();
    localObjectOutputStream.close();
    return i;
  }
  
  AreaCodeMapStorageStrategy getAreaCodeMapStorage()
  {
    return areaCodeMapStorage;
  }
  
  AreaCodeMapStorageStrategy getSmallerMapStorage(SortedMap<Integer, String> paramSortedMap)
  {
    try
    {
      AreaCodeMapStorageStrategy localAreaCodeMapStorageStrategy1 = createFlyweightMapStorage();
      int i = getSizeOfAreaCodeMapStorage(localAreaCodeMapStorageStrategy1, paramSortedMap);
      AreaCodeMapStorageStrategy localAreaCodeMapStorageStrategy2 = createDefaultMapStorage();
      int j = getSizeOfAreaCodeMapStorage(localAreaCodeMapStorageStrategy2, paramSortedMap);
      if (i < j) {
        return localAreaCodeMapStorageStrategy1;
      }
      return localAreaCodeMapStorageStrategy2;
    }
    catch (IOException paramSortedMap)
    {
      LOGGER.severe(paramSortedMap.getMessage());
    }
    return createFlyweightMapStorage();
  }
  
  String lookup(Phonenumber.PhoneNumber paramPhoneNumber)
  {
    int i = areaCodeMapStorage.getNumOfEntries();
    if (i == 0) {
      return null;
    }
    long l = Long.parseLong(paramPhoneNumber.getCountryCode() + phoneUtil.getNationalSignificantNumber(paramPhoneNumber));
    paramPhoneNumber = areaCodeMapStorage.getPossibleLengths();
    i -= 1;
    while (paramPhoneNumber.size() > 0)
    {
      Integer localInteger = (Integer)paramPhoneNumber.last();
      String str = String.valueOf(l);
      if (str.length() > localInteger.intValue()) {
        l = Long.parseLong(str.substring(0, localInteger.intValue()));
      }
      i = binarySearch(0, i, l);
      if (i < 0) {
        return null;
      }
      if (l == areaCodeMapStorage.getPrefix(i)) {
        return areaCodeMapStorage.getDescription(i);
      }
      paramPhoneNumber = paramPhoneNumber.headSet(localInteger);
    }
    return null;
  }
  
  public void readAreaCodeMap(SortedMap<Integer, String> paramSortedMap)
  {
    areaCodeMapStorage = getSmallerMapStorage(paramSortedMap);
  }
  
  public void readExternal(ObjectInput paramObjectInput)
  {
    if (paramObjectInput.readBoolean()) {}
    for (areaCodeMapStorage = new FlyweightMapStorage();; areaCodeMapStorage = new DefaultMapStorage())
    {
      areaCodeMapStorage.readExternal(paramObjectInput);
      return;
    }
  }
  
  public String toString()
  {
    return areaCodeMapStorage.toString();
  }
  
  public void writeExternal(ObjectOutput paramObjectOutput)
  {
    paramObjectOutput.writeBoolean(areaCodeMapStorage instanceof FlyweightMapStorage);
    areaCodeMapStorage.writeExternal(paramObjectOutput);
  }
}

/* Location:
 * Qualified Name:     com.meizu.i18n.phonenumbers.geocoding.AreaCodeMap
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */