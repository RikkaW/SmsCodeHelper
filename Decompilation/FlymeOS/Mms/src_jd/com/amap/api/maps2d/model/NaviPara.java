package com.amap.api.maps2d.model;

public class NaviPara
{
  public static final int DRIVING_AVOID_CONGESTION = 4;
  public static final int DRIVING_DEFAULT = 0;
  public static final int DRIVING_NO_HIGHWAY = 3;
  public static final int DRIVING_NO_HIGHWAY_AVOID_CONGESTION = 6;
  public static final int DRIVING_NO_HIGHWAY_AVOID_SHORT_MONEY = 5;
  public static final int DRIVING_NO_HIGHWAY_SAVE_MONEY_AVOID_CONGESTION = 8;
  public static final int DRIVING_SAVE_MONEY = 1;
  public static final int DRIVING_SAVE_MONEY_AVOID_CONGESTION = 7;
  public static final int DRIVING_SHORT_DISTANCE = 2;
  private int a = 0;
  private LatLng b;
  
  public int getNaviStyle()
  {
    return a;
  }
  
  public LatLng getTargetPoint()
  {
    return b;
  }
  
  public void setNaviStyle(int paramInt)
  {
    if ((paramInt >= 0) && (paramInt < 9)) {
      a = paramInt;
    }
  }
  
  public void setTargetPoint(LatLng paramLatLng)
  {
    b = paramLatLng;
  }
}

/* Location:
 * Qualified Name:     com.amap.api.maps2d.model.NaviPara
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */