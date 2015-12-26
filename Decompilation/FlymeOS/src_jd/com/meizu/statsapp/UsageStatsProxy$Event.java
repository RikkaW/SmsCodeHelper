package com.meizu.statsapp;

import ajr;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.meizu.statsapp.util.Utils;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

public class UsageStatsProxy$Event
  implements Parcelable
{
  public static final Parcelable.Creator<Event> CREATOR = new ajr();
  private int a;
  private String b;
  private int c;
  private long d;
  private String e;
  private String f;
  private String g;
  private Object h;
  private String i;
  private long j;
  private String k;
  
  public UsageStatsProxy$Event() {}
  
  public UsageStatsProxy$Event(Parcel paramParcel)
  {
    if (paramParcel == null) {
      return;
    }
    a = paramParcel.readInt();
    b = paramParcel.readString();
    c = paramParcel.readInt();
    d = paramParcel.readLong();
    e = paramParcel.readString();
    f = paramParcel.readString();
    g = paramParcel.readString();
    String str = paramParcel.readString();
    if (!Utils.isEmpty(str)) {}
    try
    {
      h = new JSONObject(str);
      i = paramParcel.readString();
      j = paramParcel.readLong();
      k = paramParcel.readString();
      return;
    }
    catch (JSONException localJSONException)
    {
      for (;;)
      {
        localJSONException.printStackTrace();
      }
    }
  }
  
  public UsageStatsProxy$Event(String paramString1, int paramInt, long paramLong, String paramString2, String paramString3, String paramString4, Object paramObject, String paramString5)
  {
    b = paramString1;
    c = paramInt;
    d = paramLong;
    e = paramString2;
    f = paramString3;
    g = paramString4;
    h = paramObject;
    k = paramString5;
  }
  
  public String a()
  {
    return b;
  }
  
  public void a(int paramInt)
  {
    c = paramInt;
  }
  
  public void a(long paramLong)
  {
    d = paramLong;
  }
  
  public void a(Object paramObject)
  {
    h = paramObject;
  }
  
  public void a(String paramString)
  {
    b = paramString;
  }
  
  public int b()
  {
    return c;
  }
  
  public void b(int paramInt)
  {
    a = paramInt;
  }
  
  public void b(long paramLong)
  {
    j = paramLong;
  }
  
  public void b(String paramString)
  {
    e = paramString;
  }
  
  public long c()
  {
    return d;
  }
  
  public void c(String paramString)
  {
    f = paramString;
  }
  
  public String d()
  {
    return e;
  }
  
  public void d(String paramString)
  {
    g = paramString;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public String e()
  {
    return f;
  }
  
  public void e(String paramString)
  {
    i = paramString;
  }
  
  public String f()
  {
    return g;
  }
  
  public void f(String paramString)
  {
    k = paramString;
  }
  
  public Object g()
  {
    return h;
  }
  
  public String h()
  {
    if (h == null) {
      return "";
    }
    try
    {
      if (((h instanceof Map)) && (((Map)h).size() > 0))
      {
        JSONStringer localJSONStringer = new JSONStringer();
        localJSONStringer.object();
        Iterator localIterator = ((Map)h).entrySet().iterator();
        while (localIterator.hasNext())
        {
          Map.Entry localEntry = (Map.Entry)localIterator.next();
          localJSONStringer.key((String)localEntry.getKey()).value(localEntry.getValue());
        }
      }
      String str;
      return "";
    }
    catch (JSONException localJSONException)
    {
      localJSONException.printStackTrace();
      return "";
      localJSONException.endObject();
      return localJSONException.toString();
      str = h.toString();
      return str;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  public int i()
  {
    return a;
  }
  
  public String j()
  {
    return i;
  }
  
  public long k()
  {
    return j;
  }
  
  public String l()
  {
    return k;
  }
  
  public String toString()
  {
    return "{name=" + b + ", type=" + c + ", package=" + f + ", page=" + g + ", properties=" + h + "}";
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    if (paramParcel == null) {
      return;
    }
    paramParcel.writeInt(a);
    paramParcel.writeString(b);
    paramParcel.writeInt(c);
    paramParcel.writeLong(d);
    if (e == null)
    {
      str = "";
      paramParcel.writeString(str);
      if (f != null) {
        break label130;
      }
      str = "";
      label62:
      paramParcel.writeString(str);
      if (g != null) {
        break label138;
      }
      str = "";
      label77:
      paramParcel.writeString(str);
      if (h != null) {
        break label146;
      }
    }
    label130:
    label138:
    label146:
    for (String str = "";; str = h())
    {
      paramParcel.writeString(str);
      paramParcel.writeString(i);
      paramParcel.writeLong(j);
      paramParcel.writeString(k);
      return;
      str = e;
      break;
      str = f;
      break label62;
      str = g;
      break label77;
    }
  }
}

/* Location:
 * Qualified Name:     com.meizu.statsapp.UsageStatsProxy.Event
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */