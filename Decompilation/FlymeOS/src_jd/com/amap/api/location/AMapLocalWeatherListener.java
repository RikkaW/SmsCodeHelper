package com.amap.api.location;

public abstract interface AMapLocalWeatherListener
{
  public abstract void onWeatherForecaseSearched(AMapLocalWeatherForecast paramAMapLocalWeatherForecast);
  
  public abstract void onWeatherLiveSearched(AMapLocalWeatherLive paramAMapLocalWeatherLive);
}

/* Location:
 * Qualified Name:     com.amap.api.location.AMapLocalWeatherListener
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */