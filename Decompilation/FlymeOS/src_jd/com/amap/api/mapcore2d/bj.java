package com.amap.api.mapcore2d;

import java.util.Random;

class bj
{
  private static bj b;
  private String a = "http://tm.mapabc.com";
  
  public static bj a()
  {
    try
    {
      if (b == null) {
        b = new bj();
      }
      bj localbj = b;
      return localbj;
    }
    finally {}
  }
  
  public String b()
  {
    switch (new Random(System.currentTimeMillis()).nextInt(100000) % 4)
    {
    default: 
      return "";
    case 0: 
      if (y.l == 2) {
        return "http://wprd01.is.autonavi.com";
      }
      return "http://webrd01.is.autonavi.com";
    case 1: 
      if (y.l == 2) {
        return "http://wprd02.is.autonavi.com";
      }
      return "http://webrd02.is.autonavi.com";
    case 2: 
      if (y.l == 2) {
        return "http://wprd03.is.autonavi.com";
      }
      return "http://webrd03.is.autonavi.com";
    }
    if (y.l == 2) {
      return "http://wprd04.is.autonavi.com";
    }
    return "http://webrd04.is.autonavi.com";
  }
  
  public String c()
  {
    return a;
  }
  
  public String d()
  {
    switch (new Random(System.currentTimeMillis()).nextInt(100000) % 4)
    {
    default: 
      return "";
    case 0: 
      return "http://mst01.is.autonavi.com";
    case 1: 
      return "http://mst02.is.autonavi.com";
    case 2: 
      return "http://mst03.is.autonavi.com";
    }
    return "http://mst04.is.autonavi.com";
  }
}

/* Location:
 * Qualified Name:     com.amap.api.mapcore2d.bj
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */