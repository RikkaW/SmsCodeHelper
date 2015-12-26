import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v7.appcompat.R.styleable;
import android.support.v7.internal.widget.TintTypedArray;
import android.widget.AbsListView;

public class arb
  extends ara
{
  private Context b;
  private int c;
  private int d;
  
  public arb(AbsListView paramAbsListView, Context paramContext, int paramInt)
  {
    super(paramAbsListView);
    b = paramContext;
    paramAbsListView = TintTypedArray.obtainStyledAttributes(b, null, R.styleable.MzListViewProxy, paramInt, 0);
    c = paramAbsListView.getDimensionPixelSize(R.styleable.MzListViewProxy_mzDividerPaddingStart, 0);
    d = paramAbsListView.getDimensionPixelSize(R.styleable.MzListViewProxy_mzDividerPaddingEnd, 0);
  }
  
  public int[] a(int paramInt)
  {
    if (ViewCompat.getLayoutDirection(a) == 1) {}
    int[] arrayOfInt;
    for (paramInt = 1;; paramInt = 0)
    {
      arrayOfInt = new int[2];
      if (paramInt == 0) {
        break;
      }
      arrayOfInt[0] = d;
      arrayOfInt[1] = c;
      return arrayOfInt;
    }
    arrayOfInt[1] = d;
    arrayOfInt[0] = c;
    return arrayOfInt;
  }
}

/* Location:
 * Qualified Name:     arb
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */