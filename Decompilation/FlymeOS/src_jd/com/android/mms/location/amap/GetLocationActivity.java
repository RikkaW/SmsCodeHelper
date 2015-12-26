package com.android.mms.location.amap;

import android.os.Bundle;
import com.amap.api.maps2d.MapView;
import com.android.mms.location.BaseGetLocationView;
import kf;

public class GetLocationActivity
  extends kf
{
  private MapView m;
  
  protected void b()
  {
    super.b();
    ((GetLocationView)a).a(d);
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130968723);
    m = ((MapView)findViewById(2131886581));
    m.onCreate(paramBundle);
    b();
    o();
  }
  
  protected void onDestroy()
  {
    super.onDestroy();
    m.onDestroy();
  }
  
  protected void onPause()
  {
    a.e();
    super.onPause();
    m.onPause();
  }
  
  protected void onResume()
  {
    super.onResume();
    m.onResume();
  }
  
  protected void onSaveInstanceState(Bundle paramBundle)
  {
    super.onSaveInstanceState(paramBundle);
    m.onSaveInstanceState(paramBundle);
  }
  
  protected boolean r()
  {
    return super.r();
  }
}

/* Location:
 * Qualified Name:     com.android.mms.location.amap.GetLocationActivity
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */