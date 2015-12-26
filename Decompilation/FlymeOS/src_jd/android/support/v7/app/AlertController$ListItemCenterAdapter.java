package android.support.v7.app;

import android.content.Context;
import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

class AlertController$ListItemCenterAdapter
  extends ArrayAdapter
{
  Context context;
  private int resourceId;
  CharSequence[] strings;
  ColorStateList textColor;
  ColorStateList[] textColors;
  private int textViewResourceId;
  
  public AlertController$ListItemCenterAdapter(Context paramContext, int paramInt1, int paramInt2, CharSequence[] paramArrayOfCharSequence)
  {
    super(paramContext, paramInt1, paramInt2, paramArrayOfCharSequence);
    context = paramContext;
    resourceId = paramInt1;
    textViewResourceId = paramInt2;
    strings = paramArrayOfCharSequence;
  }
  
  public AlertController$ListItemCenterAdapter(Context paramContext, int paramInt1, int paramInt2, CharSequence[] paramArrayOfCharSequence, ColorStateList paramColorStateList)
  {
    super(paramContext, paramInt1, paramInt2, paramArrayOfCharSequence);
    context = paramContext;
    resourceId = paramInt1;
    textViewResourceId = paramInt2;
    strings = paramArrayOfCharSequence;
    textColor = paramColorStateList;
  }
  
  public AlertController$ListItemCenterAdapter(Context paramContext, int paramInt1, int paramInt2, CharSequence[] paramArrayOfCharSequence, ColorStateList[] paramArrayOfColorStateList)
  {
    super(paramContext, paramInt1, paramInt2, paramArrayOfCharSequence);
    context = paramContext;
    resourceId = paramInt1;
    textViewResourceId = paramInt2;
    strings = paramArrayOfCharSequence;
    textColors = paramArrayOfColorStateList;
  }
  
  public long getItemId(int paramInt)
  {
    return paramInt;
  }
  
  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    View localView;
    if (paramView == null)
    {
      localView = ((LayoutInflater)context.getSystemService("layout_inflater")).inflate(resourceId, null);
      paramView = new AlertController.ViewHolder();
      item = ((TextView)localView.findViewById(textViewResourceId));
      localView.setTag(paramView);
      paramViewGroup = paramView;
      item.setText(strings[paramInt]);
      if (textColors == null) {
        break label108;
      }
      item.setTextColor(textColors[paramInt]);
    }
    label108:
    while (textColor == null)
    {
      return localView;
      paramViewGroup = (AlertController.ViewHolder)paramView.getTag();
      localView = paramView;
      break;
    }
    item.setTextColor(textColor);
    return localView;
  }
  
  public boolean hasStableIds()
  {
    return true;
  }
}

/* Location:
 * Qualified Name:     android.support.v7.app.AlertController.ListItemCenterAdapter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */