package com.ted.android.contacts.netparser.model;

import java.util.ArrayList;
import java.util.LinkedList;

public class MenuItem
{
  private String a;
  private MenuType b;
  private String c;
  private String d;
  private int e;
  private Long f;
  private String g;
  private ArrayList<MenuItem> h;
  private LinkedList<SpItem> i;
  private String j;
  
  public String a()
  {
    return a;
  }
  
  public void a(int paramInt)
  {
    e = paramInt;
  }
  
  public void a(MenuType paramMenuType)
  {
    b = paramMenuType;
  }
  
  public void a(Long paramLong)
  {
    f = paramLong;
  }
  
  public void a(String paramString)
  {
    a = paramString;
  }
  
  public void a(ArrayList<MenuItem> paramArrayList)
  {
    h = paramArrayList;
  }
  
  public void a(LinkedList<SpItem> paramLinkedList)
  {
    i = paramLinkedList;
  }
  
  public String b()
  {
    return c;
  }
  
  public void b(String paramString)
  {
    c = paramString;
  }
  
  public String c()
  {
    return d;
  }
  
  public void c(String paramString)
  {
    d = paramString;
  }
  
  public ArrayList<MenuItem> d()
  {
    return h;
  }
  
  public void d(String paramString)
  {
    g = paramString;
  }
  
  public MenuType e()
  {
    return b;
  }
  
  public void e(String paramString)
  {
    j = paramString;
  }
  
  public LinkedList<SpItem> f()
  {
    return i;
  }
  
  public String g()
  {
    return g;
  }
  
  public String h()
  {
    return j;
  }
  
  public static enum MenuType {}
}

/* Location:
 * Qualified Name:     com.ted.android.contacts.netparser.model.MenuItem
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */