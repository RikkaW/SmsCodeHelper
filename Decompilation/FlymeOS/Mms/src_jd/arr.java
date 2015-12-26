import android.view.View;
import flyme.support.v7.widget.RecyclerView.h;
import flyme.support.v7.widget.RecyclerView.q;

public class arr
{
  public static int a(RecyclerView.q paramq, arl paramarl, View paramView1, View paramView2, RecyclerView.h paramh, boolean paramBoolean)
  {
    if ((paramh.r() == 0) || (paramq.f() == 0) || (paramView1 == null) || (paramView2 == null)) {
      return 0;
    }
    if (!paramBoolean) {
      return Math.abs(paramh.d(paramView1) - paramh.d(paramView2)) + 1;
    }
    int i = paramarl.b(paramView2);
    int j = paramarl.a(paramView1);
    return Math.min(paramarl.f(), i - j);
  }
  
  public static int a(RecyclerView.q paramq, arl paramarl, View paramView1, View paramView2, RecyclerView.h paramh, boolean paramBoolean1, boolean paramBoolean2)
  {
    int i = 0;
    int j = i;
    if (paramh.r() != 0)
    {
      j = i;
      if (paramq.f() != 0)
      {
        j = i;
        if (paramView1 != null)
        {
          if (paramView2 != null) {
            break label45;
          }
          j = i;
        }
      }
    }
    return j;
    label45:
    i = Math.min(paramh.d(paramView1), paramh.d(paramView2));
    j = Math.max(paramh.d(paramView1), paramh.d(paramView2));
    if (paramBoolean2) {}
    for (i = Math.max(0, paramq.f() - j - 1);; i = Math.max(0, i))
    {
      j = i;
      if (!paramBoolean1) {
        break;
      }
      j = Math.abs(paramarl.b(paramView2) - paramarl.a(paramView1));
      int k = Math.abs(paramh.d(paramView1) - paramh.d(paramView2));
      float f = j / (k + 1);
      return Math.round(i * f + (paramarl.c() - paramarl.a(paramView1)));
    }
  }
  
  public static int b(RecyclerView.q paramq, arl paramarl, View paramView1, View paramView2, RecyclerView.h paramh, boolean paramBoolean)
  {
    if ((paramh.r() == 0) || (paramq.f() == 0) || (paramView1 == null) || (paramView2 == null)) {
      return 0;
    }
    if (!paramBoolean) {
      return paramq.f();
    }
    int i = paramarl.b(paramView2);
    int j = paramarl.a(paramView1);
    int k = Math.abs(paramh.d(paramView1) - paramh.d(paramView2));
    return (int)((i - j) / (k + 1) * paramq.f());
  }
}

/* Location:
 * Qualified Name:     arr
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */