import android.content.AsyncQueryHandler;
import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;
import android.view.View;

public class mq
{
  private ms a;
  private mq.b b;
  private mq.a c;
  private String[] d;
  private Context e;
  private AlertDialog f;
  
  public mq(Context paramContext, mq.b paramb)
  {
    e = paramContext;
    b = paramb;
    a();
  }
  
  public void a()
  {
    a = new ms(e, null, 2);
    c();
    View localView = aaa.a(e, e.getResources().getString(2131493542));
    f = new AlertDialog.Builder(e).setAdapter(a, new mr(this)).setCustomTitle(localView).create();
  }
  
  public void b()
  {
    f.show();
  }
  
  public void c()
  {
    if (c == null)
    {
      c = new mq.a(e.getContentResolver());
      d = e.getResources().getStringArray(2131361797);
    }
    c.cancelOperation(10000);
    String str = mt.a();
    c.startQuery(10000, null, mt.a, d, str, null, null);
  }
  
  public void d()
  {
    if (a != null) {
      a.changeCursor(null);
    }
    b = null;
  }
  
  public void e()
  {
    if ((f != null) && (f.isShowing())) {
      f.dismiss();
    }
  }
  
  final class a
    extends AsyncQueryHandler
  {
    public a(ContentResolver paramContentResolver)
    {
      super();
    }
    
    protected void onQueryComplete(int paramInt, Object paramObject, Cursor paramCursor)
    {
      switch (paramInt)
      {
      default: 
        return;
      }
      mq.a(mq.this).changeCursor(paramCursor);
    }
  }
  
  public static abstract interface b
  {
    public abstract void a(String paramString);
  }
}

/* Location:
 * Qualified Name:     mq
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */