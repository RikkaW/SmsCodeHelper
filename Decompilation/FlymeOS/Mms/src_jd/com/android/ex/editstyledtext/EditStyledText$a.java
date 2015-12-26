package com.android.ex.editstyledtext;

import android.util.Log;
import java.util.HashMap;

public class EditStyledText$a
{
  private EditStyledText.d a;
  private int b;
  private HashMap<Integer, a> c;
  
  private a c(int paramInt)
  {
    if (c.containsKey(Integer.valueOf(paramInt))) {
      return (a)c.get(Integer.valueOf(paramInt));
    }
    return null;
  }
  
  public void a()
  {
    b(5);
  }
  
  public void a(int paramInt)
  {
    a(paramInt, null);
  }
  
  public void a(int paramInt, Object[] paramArrayOfObject)
  {
    c(paramInt).a(paramArrayOfObject);
    b = paramInt;
    b(paramInt);
  }
  
  public boolean b(int paramInt)
  {
    Log.d("EditModeActions", "--- do the next action: " + paramInt + "," + a.m());
    a locala = c(paramInt);
    if (locala == null)
    {
      Log.e("EditModeActions", "--- invalid action error.");
      return false;
    }
    switch (a.m())
    {
    default: 
      return false;
    case 0: 
      return locala.a();
    case 1: 
      return locala.b();
    case 2: 
      return locala.c();
    }
    if (a.j()) {
      return locala.e();
    }
    return locala.d();
  }
  
  public class a
  {
    private Object[] a;
    
    protected void a(Object[] paramArrayOfObject)
    {
      a = paramArrayOfObject;
    }
    
    protected boolean a()
    {
      return false;
    }
    
    protected boolean b()
    {
      return a();
    }
    
    protected boolean c()
    {
      return b();
    }
    
    protected boolean d()
    {
      return c();
    }
    
    protected boolean e()
    {
      return c();
    }
  }
}

/* Location:
 * Qualified Name:     com.android.ex.editstyledtext.EditStyledText.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */