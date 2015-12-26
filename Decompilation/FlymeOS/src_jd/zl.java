import android.content.Context;
import android.content.res.Resources;
import android.util.FloatMath;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import com.android.mms.MmsApp;
import com.android.mms.view.MessageListItemBase;

public class zl
{
  public static float a = 1.0F;
  private static int b = 0;
  private static float c = 0.0F;
  private static float d = 0.0F;
  private static boolean e = false;
  private static float f = 0.0F;
  private static boolean g = false;
  private static float h = 0.0F;
  private static Context i = MmsApp.c();
  private static int j = i.getResources().getDimensionPixelOffset(2131558766);
  private static int k = i.getResources().getDimensionPixelOffset(2131558745);
  
  public static void a(MessageListItemBase paramMessageListItemBase)
  {
    View localView = paramMessageListItemBase.findViewById(2131886460);
    if (localView != null) {
      ((TextView)localView).setTextSize(0, a * j);
    }
    paramMessageListItemBase = paramMessageListItemBase.findViewById(2131886461);
    if (paramMessageListItemBase != null) {
      ((TextView)paramMessageListItemBase).setTextSize(0, a * k);
    }
  }
  
  public static boolean a(MotionEvent paramMotionEvent)
  {
    switch (paramMotionEvent.getAction() & 0xFF)
    {
    }
    do
    {
      for (;;)
      {
        return false;
        if (paramMotionEvent.getPointerCount() == 1)
        {
          c = 0.0F;
          d = 0.0F;
          h = paramMotionEvent.getY(0);
          e = false;
          g = false;
          continue;
          if ((!g) && (paramMotionEvent.getPointerCount() == 2))
          {
            b = 0;
            continue;
            if ((!g) && (paramMotionEvent.getPointerCount() == 2))
            {
              c = Math.max(10.0F, b(paramMotionEvent));
              b = 1;
              e = true;
              f = a;
            }
          }
        }
      }
      if ((b == 1) && (!g) && (paramMotionEvent.getPointerCount() >= 2))
      {
        d = Math.max(10.0F, b(paramMotionEvent));
        d = c + (d - c) * 0.1F;
        float f2 = d / c - 1.0F + f;
        float f1 = f2;
        if (f2 > 1.5F) {
          f1 = 1.5F;
        }
        f2 = f1;
        if (f1 < 0.8F) {
          f2 = 0.8F;
        }
        a = f2;
      }
      while (e)
      {
        return true;
        if ((!g) && (!e) && (Math.abs(paramMotionEvent.getY() - h) > 10.0F)) {
          g = true;
        }
      }
    } while (!e);
    return true;
  }
  
  private static float b(MotionEvent paramMotionEvent)
  {
    float f1 = paramMotionEvent.getX(0) - paramMotionEvent.getX(1);
    float f2 = paramMotionEvent.getY(0) - paramMotionEvent.getY(1);
    return FloatMath.sqrt(f1 * f1 + f2 * f2);
  }
}

/* Location:
 * Qualified Name:     zl
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */