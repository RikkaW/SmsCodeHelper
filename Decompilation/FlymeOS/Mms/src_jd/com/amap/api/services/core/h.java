package com.amap.api.services.core;

import android.content.Context;
import com.amap.api.services.help.Tip;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

public class h
  extends r<i, ArrayList<Tip>>
{
  public h(Context paramContext, i parami)
  {
    super(paramContext, parami);
  }
  
  protected ArrayList<Tip> a(String paramString)
  {
    try
    {
      paramString = j.o(new JSONObject(paramString));
      return paramString;
    }
    catch (JSONException paramString)
    {
      d.a(paramString, "InputtipsHandler", "paseJSON");
    }
    return null;
  }
  
  protected String a_()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("output=json").append("&keywords=").append(c(a).a));
    String str = a).b;
    if (!j.h(str))
    {
      str = c(str);
      localStringBuffer.append("&city=").append(str);
    }
    localStringBuffer.append("&key=").append(w.f(d));
    localStringBuffer.append("&language=").append(c.b());
    return localStringBuffer.toString();
  }
  
  public String b()
  {
    return c.a() + "/assistant/inputtips?";
  }
}

/* Location:
 * Qualified Name:     com.amap.api.services.core.h
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */