package com.xiaomi.network;

import org.json.JSONException;
import org.json.JSONObject;

public class AccessHistory
{
  private long cost;
  private String exceptionName;
  private long size;
  private long ts;
  private int weight;
  
  public AccessHistory()
  {
    this(0, 0L, 0L, null);
  }
  
  public AccessHistory(int paramInt, long paramLong1, long paramLong2, Exception paramException)
  {
    weight = paramInt;
    cost = paramLong1;
    size = paramLong2;
    ts = System.currentTimeMillis();
    if (paramException != null) {
      exceptionName = paramException.getClass().getSimpleName();
    }
  }
  
  public AccessHistory fromJSON(JSONObject paramJSONObject)
    throws JSONException
  {
    cost = paramJSONObject.getLong("cost");
    size = paramJSONObject.getLong("size");
    ts = paramJSONObject.getLong("ts");
    weight = paramJSONObject.getInt("wt");
    exceptionName = paramJSONObject.optString("expt");
    return this;
  }
  
  public long getCost()
  {
    return cost;
  }
  
  public String getException()
  {
    return exceptionName;
  }
  
  public long getSize()
  {
    return size;
  }
  
  public long getTime()
  {
    return ts;
  }
  
  public int getWeight()
  {
    return weight;
  }
  
  public JSONObject toJSON()
    throws JSONException
  {
    JSONObject localJSONObject = new JSONObject();
    localJSONObject.put("cost", cost);
    localJSONObject.put("size", size);
    localJSONObject.put("ts", ts);
    localJSONObject.put("wt", weight);
    localJSONObject.put("expt", exceptionName);
    return localJSONObject;
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.network.AccessHistory
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */