package com.xiaomi.network;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class WeightedHost
  implements Comparable<WeightedHost>
{
  private final LinkedList<AccessHistory> accessHistories = new LinkedList();
  String host;
  private long touchedTime = 0L;
  protected int weight;
  
  public WeightedHost()
  {
    this(null, 0);
  }
  
  public WeightedHost(String paramString, int paramInt)
  {
    host = paramString;
    weight = paramInt;
  }
  
  /* Error */
  protected void addAccessHistory(AccessHistory paramAccessHistory)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_1
    //   3: ifnull +78 -> 81
    //   6: invokestatic 43	com/xiaomi/network/UploadHostStatHelper:getInstance	()Lcom/xiaomi/network/UploadHostStatHelper;
    //   9: invokevirtual 46	com/xiaomi/network/UploadHostStatHelper:fireHostEvent	()V
    //   12: aload_0
    //   13: getfield 29	com/xiaomi/network/WeightedHost:accessHistories	Ljava/util/LinkedList;
    //   16: aload_1
    //   17: invokevirtual 50	java/util/LinkedList:add	(Ljava/lang/Object;)Z
    //   20: pop
    //   21: aload_1
    //   22: invokevirtual 56	com/xiaomi/network/AccessHistory:getWeight	()I
    //   25: istore 4
    //   27: iload 4
    //   29: ifle +55 -> 84
    //   32: aload_0
    //   33: aload_0
    //   34: getfield 35	com/xiaomi/network/WeightedHost:weight	I
    //   37: aload_1
    //   38: invokevirtual 56	com/xiaomi/network/AccessHistory:getWeight	()I
    //   41: iadd
    //   42: putfield 35	com/xiaomi/network/WeightedHost:weight	I
    //   45: aload_0
    //   46: getfield 29	com/xiaomi/network/WeightedHost:accessHistories	Ljava/util/LinkedList;
    //   49: invokevirtual 59	java/util/LinkedList:size	()I
    //   52: bipush 30
    //   54: if_icmple +27 -> 81
    //   57: aload_0
    //   58: getfield 29	com/xiaomi/network/WeightedHost:accessHistories	Ljava/util/LinkedList;
    //   61: invokevirtual 63	java/util/LinkedList:remove	()Ljava/lang/Object;
    //   64: checkcast 52	com/xiaomi/network/AccessHistory
    //   67: astore_1
    //   68: aload_0
    //   69: aload_0
    //   70: getfield 35	com/xiaomi/network/WeightedHost:weight	I
    //   73: aload_1
    //   74: invokevirtual 56	com/xiaomi/network/AccessHistory:getWeight	()I
    //   77: isub
    //   78: putfield 35	com/xiaomi/network/WeightedHost:weight	I
    //   81: aload_0
    //   82: monitorexit
    //   83: return
    //   84: iconst_0
    //   85: istore_3
    //   86: aload_0
    //   87: getfield 29	com/xiaomi/network/WeightedHost:accessHistories	Ljava/util/LinkedList;
    //   90: invokevirtual 59	java/util/LinkedList:size	()I
    //   93: iconst_1
    //   94: isub
    //   95: istore_2
    //   96: iload_2
    //   97: iflt +31 -> 128
    //   100: aload_0
    //   101: getfield 29	com/xiaomi/network/WeightedHost:accessHistories	Ljava/util/LinkedList;
    //   104: iload_2
    //   105: invokevirtual 67	java/util/LinkedList:get	(I)Ljava/lang/Object;
    //   108: checkcast 52	com/xiaomi/network/AccessHistory
    //   111: invokevirtual 56	com/xiaomi/network/AccessHistory:getWeight	()I
    //   114: ifge +14 -> 128
    //   117: iload_3
    //   118: iconst_1
    //   119: iadd
    //   120: istore_3
    //   121: iload_2
    //   122: iconst_1
    //   123: isub
    //   124: istore_2
    //   125: goto -29 -> 96
    //   128: aload_0
    //   129: aload_0
    //   130: getfield 35	com/xiaomi/network/WeightedHost:weight	I
    //   133: iload 4
    //   135: iload_3
    //   136: imul
    //   137: iadd
    //   138: putfield 35	com/xiaomi/network/WeightedHost:weight	I
    //   141: goto -96 -> 45
    //   144: astore_1
    //   145: aload_0
    //   146: monitorexit
    //   147: aload_1
    //   148: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	149	0	this	WeightedHost
    //   0	149	1	paramAccessHistory	AccessHistory
    //   95	30	2	i	int
    //   85	52	3	j	int
    //   25	112	4	k	int
    // Exception table:
    //   from	to	target	type
    //   6	27	144	finally
    //   32	45	144	finally
    //   45	81	144	finally
    //   86	96	144	finally
    //   100	117	144	finally
    //   128	141	144	finally
  }
  
  public int compareTo(WeightedHost paramWeightedHost)
  {
    if (paramWeightedHost == null) {
      return 1;
    }
    return weight - weight;
  }
  
  public WeightedHost fromJSON(JSONObject paramJSONObject)
    throws JSONException
  {
    try
    {
      touchedTime = paramJSONObject.getLong("tt");
      weight = paramJSONObject.getInt("wt");
      host = paramJSONObject.getString("host");
      paramJSONObject = paramJSONObject.getJSONArray("ah");
      int i = 0;
      while (i < paramJSONObject.length())
      {
        JSONObject localJSONObject = paramJSONObject.getJSONObject(i);
        accessHistories.add(new AccessHistory().fromJSON(localJSONObject));
        i += 1;
      }
      return this;
    }
    finally {}
  }
  
  public ArrayList<AccessHistory> getUnTouchedAccessHistory()
  {
    try
    {
      ArrayList localArrayList = new ArrayList();
      Iterator localIterator = accessHistories.iterator();
      while (localIterator.hasNext())
      {
        AccessHistory localAccessHistory = (AccessHistory)localIterator.next();
        if (localAccessHistory.getTime() > touchedTime) {
          localArrayList.add(localAccessHistory);
        }
      }
      touchedTime = System.currentTimeMillis();
    }
    finally {}
    return localArrayList1;
  }
  
  public JSONObject toJSON()
    throws JSONException
  {
    try
    {
      JSONObject localJSONObject1 = new JSONObject();
      localJSONObject1.put("tt", touchedTime);
      localJSONObject1.put("wt", weight);
      localJSONObject1.put("host", host);
      JSONArray localJSONArray = new JSONArray();
      Iterator localIterator = accessHistories.iterator();
      while (localIterator.hasNext()) {
        localJSONArray.put(((AccessHistory)localIterator.next()).toJSON());
      }
      localJSONObject2.put("ah", localJSONArray);
    }
    finally {}
    return localJSONObject2;
  }
  
  public String toString()
  {
    return host + ":" + weight;
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.network.WeightedHost
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */