package com.ted.sdk.location;

import android.content.Context;
import com.ted.sdk.yellow.util.SdkSharedPrefs;

class Location$1
  implements Runnable
{
  Location$1(Context paramContext) {}
  
  public void run()
  {
    Location.access$1(val$ctx);
    SdkSharedPrefs.setNumSegmentDataLoaded(val$ctx, true);
  }
}

/* Location:
 * Qualified Name:     com.ted.sdk.location.Location.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */