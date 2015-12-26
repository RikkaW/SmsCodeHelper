package com.android.mms.view;

import android.view.View;
import com.meizu.common.widget.ScrollTextView.IDataAdapter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

class CustomTimePicker$c
  implements ScrollTextView.IDataAdapter
{
  int a = 0;
  
  CustomTimePicker$c(CustomTimePicker paramCustomTimePicker, int paramInt)
  {
    a = paramInt;
  }
  
  public String getItemText(int paramInt)
  {
    switch (a)
    {
    case 4: 
    default: 
    case 1: 
    case 2: 
    case 3: 
      do
      {
        return null;
        if (b.a()) {
          return String.valueOf(paramInt);
        }
        int i = paramInt;
        if (paramInt == 0) {
          i = 12;
        }
        return String.valueOf(i);
        return String.valueOf(paramInt);
        if (paramInt == 0) {
          return CustomTimePicker.c(b);
        }
      } while (paramInt != 1);
      return CustomTimePicker.d(b);
    }
    if (CustomTimePicker.e(b)) {
      return CustomTimePicker.g(b, paramInt);
    }
    paramInt -= CustomTimePicker.a(b) / 2;
    if ((paramInt < 0) || (paramInt >= CustomTimePicker.f(b).a())) {
      return "";
    }
    int[] arrayOfInt = CustomTimePicker.c(b, paramInt);
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.set(2, arrayOfInt[1] - 1);
    localCalendar.set(5, arrayOfInt[2]);
    localCalendar.set(1, arrayOfInt[0]);
    return CustomTimePicker.g(b).format(localCalendar.getTime());
  }
  
  public void onChanged(View paramView, int paramInt1, int paramInt2)
  {
    boolean bool = true;
    switch (a)
    {
    case 4: 
    default: 
      return;
    case 1: 
      CustomTimePicker.a(b, paramInt2);
    }
    for (;;)
    {
      paramView = new int[6];
      b.a(paramView);
      if (CustomTimePicker.b(b) == null) {
        break;
      }
      CustomTimePicker.b(b).a(paramView);
      return;
      CustomTimePicker.b(b, paramInt2);
      continue;
      paramView = b;
      if (paramInt2 == 0) {}
      for (;;)
      {
        CustomTimePicker.a(paramView, bool);
        break;
        bool = false;
      }
      paramView = CustomTimePicker.c(b, paramInt2 - CustomTimePicker.a(b) / 2);
      CustomTimePicker.d(b, paramView[0]);
      CustomTimePicker.e(b, paramView[1]);
      CustomTimePicker.f(b, paramView[2]);
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.view.CustomTimePicker.c
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */