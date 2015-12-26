package android.support.v7.internal.widget;

import android.content.pm.ResolveInfo;
import java.math.BigDecimal;

public final class ActivityChooserModel$ActivityResolveInfo
  implements Comparable<ActivityResolveInfo>
{
  public final ResolveInfo resolveInfo;
  public float weight;
  
  public ActivityChooserModel$ActivityResolveInfo(ActivityChooserModel paramActivityChooserModel, ResolveInfo paramResolveInfo)
  {
    resolveInfo = paramResolveInfo;
  }
  
  public int compareTo(ActivityResolveInfo paramActivityResolveInfo)
  {
    return Float.floatToIntBits(weight) - Float.floatToIntBits(weight);
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
      paramObject = (ActivityResolveInfo)paramObject;
    } while (Float.floatToIntBits(weight) == Float.floatToIntBits(weight));
    return false;
  }
  
  public int hashCode()
  {
    return Float.floatToIntBits(weight) + 31;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("[");
    localStringBuilder.append("resolveInfo:").append(resolveInfo.toString());
    localStringBuilder.append("; weight:").append(new BigDecimal(weight));
    localStringBuilder.append("]");
    return localStringBuilder.toString();
  }
}

/* Location:
 * Qualified Name:     android.support.v7.internal.widget.ActivityChooserModel.ActivityResolveInfo
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */