package com.xiaomi.mms.utils;

import com.google.android.collect.Sets;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class MxCapabilityText
{
  public static String combine(String... paramVarArgs)
  {
    if ((paramVarArgs == null) || (paramVarArgs.length == 0)) {
      throw new IllegalArgumentException("capabilities not specified");
    }
    StringBuilder localStringBuilder = new StringBuilder();
    int j = paramVarArgs.length;
    int i = 0;
    while (i < j)
    {
      String str = paramVarArgs[i];
      if (localStringBuilder.length() > 0) {
        localStringBuilder.append("#");
      }
      localStringBuilder.append(str);
      i += 1;
    }
    return localStringBuilder.toString();
  }
  
  public static Set<String> parse(String paramString)
  {
    HashSet localHashSet = Sets.newHashSet();
    if (paramString != null) {
      Collections.addAll(localHashSet, paramString.split("#"));
    }
    return localHashSet;
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mms.utils.MxCapabilityText
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */