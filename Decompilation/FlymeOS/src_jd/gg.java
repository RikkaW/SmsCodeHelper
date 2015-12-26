import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;
import com.android.mms.ui.ManageSimMessages.b;
import com.google.android.mms.MmsException;
import com.meizu.common.widget.AnimCheckBox;
import com.meizu.common.widget.FoldableTextView;

public class gg
  extends CursorAdapter
{
  protected LayoutInflater a;
  private final LruCache<Long, vv> b;
  private vx.a c;
  private Context d;
  private int e = -1;
  private Cursor f;
  private ManageSimMessages.b g;
  
  public gg(Context paramContext, Cursor paramCursor, ManageSimMessages.b paramb)
  {
    super(paramContext, paramCursor);
    d = paramContext;
    b = new LruCache(50);
    if ((paramCursor == null) || (paramCursor.isClosed())) {
      return;
    }
    c = new gg.a(paramCursor);
    a(c.b);
    e = c.b;
    g = paramb;
    a = LayoutInflater.from(paramContext);
  }
  
  private vv a(long paramLong, Cursor paramCursor)
  {
    Object localObject = (vv)b.get(Long.valueOf(paramLong));
    if (localObject == null)
    {
      if (paramCursor == null) {
        return null;
      }
      try
      {
        vv localvv = new vv(d, "sms", paramCursor, c, null);
        try
        {
          b.put(Long.valueOf(e), localvv);
          return localvv;
        }
        catch (MmsException paramCursor)
        {
          localObject = localvv;
        }
      }
      catch (MmsException paramCursor)
      {
        for (;;) {}
      }
      Log.e("ManageSimMessages-MessageSimListAdapter", paramCursor.getMessage());
      return (vv)localObject;
    }
    return (vv)localObject;
  }
  
  private void a(int paramInt)
  {
    aau.a(CursorAdapter.class, this, "mRowIDColumn", Integer.valueOf(paramInt));
  }
  
  private Cursor c(long paramLong)
  {
    f = getCursor();
    if ((f == null) || (f.isClosed())) {}
    do
    {
      while (!f.moveToNext())
      {
        return null;
        f.moveToFirst();
      }
    } while (paramLong != f.getLong(e));
    return f;
  }
  
  public vv a(long paramLong)
  {
    vv localvv = (vv)b.get(Long.valueOf(paramLong));
    if (localvv != null) {
      return localvv;
    }
    return a(paramLong, c(paramLong));
  }
  
  public vv a(Cursor paramCursor)
  {
    return a(paramCursor.getLong(e), paramCursor);
  }
  
  public void a()
  {
    if (b != null) {
      b.evictAll();
    }
  }
  
  public vv b(long paramLong)
  {
    return (vv)b.remove(Long.valueOf(paramLong));
  }
  
  public void bindView(View paramView, Context paramContext, Cursor paramCursor)
  {
    FoldableTextView localFoldableTextView;
    TextView localTextView;
    if (paramView != null)
    {
      paramContext = (TextView)paramView.findViewById(2131886734);
      localFoldableTextView = (FoldableTextView)paramView.findViewById(2131886735);
      localTextView = (TextView)paramView.findViewById(2131886733);
      paramView = (AnimCheckBox)paramView.findViewById(16908289);
      paramView.setUpdateListner(new gh(this, localTextView, paramView));
      paramView = a(paramCursor);
      if (paramView != null)
      {
        if (paramView.R() > 0L) {
          break label154;
        }
        localTextView.setText("");
        if ((paramView.W() != null) && (!paramView.W().equals(""))) {
          break label174;
        }
        paramContext.setText(d.getString(2131493015));
      }
    }
    for (;;)
    {
      if (paramView.S()) {
        break label185;
      }
      localFoldableTextView.setVisibility(0);
      localFoldableTextView.setText(o);
      localFoldableTextView.setClickable(true);
      localFoldableTextView.setNonSpanClickable(false);
      return;
      label154:
      localTextView.setText(wd.a(d, paramView.R(), false));
      break;
      label174:
      paramContext.setText(paramView.W());
    }
    label185:
    localFoldableTextView.setText(d.getString(2131493652));
  }
  
  public void changeCursor(Cursor paramCursor)
  {
    super.changeCursor(paramCursor);
    a(c.b);
    e = c.b;
  }
  
  public View newView(Context paramContext, Cursor paramCursor, ViewGroup paramViewGroup)
  {
    return a.inflate(2130968836, paramViewGroup, false);
  }
  
  public static class a
    extends vx.a
  {
    public a(Cursor paramCursor)
    {
      super();
      try
      {
        b = paramCursor.getColumnIndexOrThrow("index_on_icc");
        return;
      }
      catch (IllegalArgumentException paramCursor)
      {
        Log.w("colsMap", paramCursor.getMessage());
      }
    }
  }
}

/* Location:
 * Qualified Name:     gg
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */