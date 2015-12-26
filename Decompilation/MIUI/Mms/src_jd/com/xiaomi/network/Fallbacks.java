package com.xiaomi.network;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class Fallbacks
{
  private String host;
  private final ArrayList<Fallback> mFallbacks = new ArrayList();
  
  public Fallbacks() {}
  
  public Fallbacks(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      throw new IllegalArgumentException("the host is empty");
    }
    host = paramString;
  }
  
  /* Error */
  public void addFallback(Fallback paramFallback)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: iconst_0
    //   3: istore_2
    //   4: iload_2
    //   5: aload_0
    //   6: getfield 18	com/xiaomi/network/Fallbacks:mFallbacks	Ljava/util/ArrayList;
    //   9: invokevirtual 40	java/util/ArrayList:size	()I
    //   12: if_icmpge +31 -> 43
    //   15: aload_0
    //   16: getfield 18	com/xiaomi/network/Fallbacks:mFallbacks	Ljava/util/ArrayList;
    //   19: iload_2
    //   20: invokevirtual 44	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   23: checkcast 46	com/xiaomi/network/Fallback
    //   26: aload_1
    //   27: invokevirtual 50	com/xiaomi/network/Fallback:match	(Lcom/xiaomi/network/Fallback;)Z
    //   30: ifeq +36 -> 66
    //   33: aload_0
    //   34: getfield 18	com/xiaomi/network/Fallbacks:mFallbacks	Ljava/util/ArrayList;
    //   37: iload_2
    //   38: aload_1
    //   39: invokevirtual 54	java/util/ArrayList:set	(ILjava/lang/Object;)Ljava/lang/Object;
    //   42: pop
    //   43: iload_2
    //   44: aload_0
    //   45: getfield 18	com/xiaomi/network/Fallbacks:mFallbacks	Ljava/util/ArrayList;
    //   48: invokevirtual 40	java/util/ArrayList:size	()I
    //   51: if_icmplt +12 -> 63
    //   54: aload_0
    //   55: getfield 18	com/xiaomi/network/Fallbacks:mFallbacks	Ljava/util/ArrayList;
    //   58: aload_1
    //   59: invokevirtual 58	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   62: pop
    //   63: aload_0
    //   64: monitorexit
    //   65: return
    //   66: iload_2
    //   67: iconst_1
    //   68: iadd
    //   69: istore_2
    //   70: goto -66 -> 4
    //   73: astore_1
    //   74: aload_0
    //   75: monitorexit
    //   76: aload_1
    //   77: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	78	0	this	Fallbacks
    //   0	78	1	paramFallback	Fallback
    //   3	67	2	i	int
    // Exception table:
    //   from	to	target	type
    //   4	43	73	finally
    //   43	63	73	finally
  }
  
  public Fallbacks fromJSON(JSONObject paramJSONObject)
    throws JSONException
  {
    try
    {
      host = paramJSONObject.getString("host");
      paramJSONObject = paramJSONObject.getJSONArray("fbs");
      int i = 0;
      while (i < paramJSONObject.length())
      {
        mFallbacks.add(new Fallback(host).fromJSON(paramJSONObject.getJSONObject(i)));
        i += 1;
      }
      return this;
    }
    finally {}
  }
  
  /* Error */
  public Fallback getFallback()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 18	com/xiaomi/network/Fallbacks:mFallbacks	Ljava/util/ArrayList;
    //   6: invokevirtual 40	java/util/ArrayList:size	()I
    //   9: iconst_1
    //   10: isub
    //   11: istore_1
    //   12: iload_1
    //   13: iflt +43 -> 56
    //   16: aload_0
    //   17: getfield 18	com/xiaomi/network/Fallbacks:mFallbacks	Ljava/util/ArrayList;
    //   20: iload_1
    //   21: invokevirtual 44	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   24: checkcast 46	com/xiaomi/network/Fallback
    //   27: astore_2
    //   28: aload_2
    //   29: invokevirtual 94	com/xiaomi/network/Fallback:match	()Z
    //   32: ifeq +17 -> 49
    //   35: invokestatic 100	com/xiaomi/network/HostManager:getInstance	()Lcom/xiaomi/network/HostManager;
    //   38: aload_2
    //   39: invokevirtual 104	com/xiaomi/network/Fallback:getISP	()Ljava/lang/String;
    //   42: invokevirtual 107	com/xiaomi/network/HostManager:setCurrentISP	(Ljava/lang/String;)V
    //   45: aload_0
    //   46: monitorexit
    //   47: aload_2
    //   48: areturn
    //   49: iload_1
    //   50: iconst_1
    //   51: isub
    //   52: istore_1
    //   53: goto -41 -> 12
    //   56: aconst_null
    //   57: astore_2
    //   58: goto -13 -> 45
    //   61: astore_2
    //   62: aload_0
    //   63: monitorexit
    //   64: aload_2
    //   65: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	66	0	this	Fallbacks
    //   11	42	1	i	int
    //   27	31	2	localFallback	Fallback
    //   61	4	2	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   2	12	61	finally
    //   16	45	61	finally
  }
  
  public ArrayList<Fallback> getFallbacks()
  {
    return mFallbacks;
  }
  
  public String getHost()
  {
    return host;
  }
  
  public void purge(boolean paramBoolean)
  {
    for (;;)
    {
      int i;
      try
      {
        i = mFallbacks.size() - 1;
        if (i >= 0)
        {
          Fallback localFallback = (Fallback)mFallbacks.get(i);
          if (paramBoolean)
          {
            if (localFallback.isExpired()) {
              mFallbacks.remove(i);
            }
          }
          else if (!localFallback.isEffective()) {
            mFallbacks.remove(i);
          }
        }
      }
      finally {}
      return;
      i -= 1;
    }
  }
  
  public JSONObject toJSON()
    throws JSONException
  {
    try
    {
      JSONObject localJSONObject1 = new JSONObject();
      localJSONObject1.put("host", host);
      JSONArray localJSONArray = new JSONArray();
      Iterator localIterator = mFallbacks.iterator();
      while (localIterator.hasNext()) {
        localJSONArray.put(((Fallback)localIterator.next()).toJSON());
      }
      localJSONObject2.put("fbs", localJSONArray);
    }
    finally {}
    return localJSONObject2;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(host);
    localStringBuilder.append("\n");
    Iterator localIterator = mFallbacks.iterator();
    while (localIterator.hasNext()) {
      localStringBuilder.append((Fallback)localIterator.next());
    }
    return localStringBuilder.toString();
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.network.Fallbacks
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */