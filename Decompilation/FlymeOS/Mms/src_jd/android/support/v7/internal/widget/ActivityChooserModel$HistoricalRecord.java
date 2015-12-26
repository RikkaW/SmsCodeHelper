package android.support.v7.internal.widget;

import android.content.ComponentName;
import java.math.BigDecimal;

public final class ActivityChooserModel$HistoricalRecord
{
  public final ComponentName activity;
  public final long time;
  public final float weight;
  
  public ActivityChooserModel$HistoricalRecord(ComponentName paramComponentName, long paramLong, float paramFloat)
  {
    activity = paramComponentName;
    time = paramLong;
    weight = paramFloat;
  }
  
  public ActivityChooserModel$HistoricalRecord(String paramString, long paramLong, float paramFloat)
  {
    this(ComponentName.unflattenFromString(paramString), paramLong, paramFloat);
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {}
    do
    {
      return true;
      if (paramObject == null) {
        return false;
      }
      if (getClass() != paramObject.getClass()) {
        return false;
      }
      paramObject = (HistoricalRecord)paramObject;
      if (activity == null)
      {
        if (activity != null) {
          return false;
        }
      }
      else if (!activity.equals(activity)) {
        return false;
      }
      if (time != time) {
        return false;
      }
    } while (Float.floatToIntBits(weight) == Float.floatToIntBits(weight));
    return false;
  }
  
  public int hashCode()
  {
    if (activity == null) {}
    for (int i = 0;; i = activity.hashCode()) {
      return ((i + 31) * 31 + (int)(time ^ time >>> 32)) * 31 + Float.floatToIntBits(weight);
    }
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("[");
    localStringBuilder.append("; activity:").append(activity);
    localStringBuilder.append("; time:").append(time);
    localStringBuilder.append("; weight:").append(new BigDecimal(weight));
    localStringBuilder.append("]");
    return localStringBuilder.toString();
  }
}

/* Location:
 * Qualified Name:     android.support.v7.internal.widget.ActivityChooserModel.HistoricalRecord
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */