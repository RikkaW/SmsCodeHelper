import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import com.android.mms.MmsApp;
import com.android.mms.ui.MessageListView;
import com.android.mms.view.MessageListItemBase;
import com.google.android.mms.MmsException;
import java.util.regex.Pattern;

public class vx
  extends CursorAdapter
{
  public static final String[] a = { "transport_type", "_id", "thread_id", "address", "body", "date", "date_sent", "read", "type", "status", "locked", "error_code", "subject", "sub", "sub_cs", "date", "date_sent", "read", "m_type", "msg_box", "d_rpt", "rr", "err_type", "locked", "st", "text_only", "delivery_status", "resp_st", "association_id", "port", "protocol", "first_media_id", "slideshow_description", "file_link", "m_size", "exp", "t_rate", "group_msg_id", "is_favorite", "sim_id", "imsi" };
  private static int d = 0;
  private static int e = 0;
  private static int f = 0;
  private static int u = -1;
  protected LayoutInflater b;
  protected final vx.a c;
  private final vx.b g;
  private vx.c h;
  private Handler i;
  private Pattern j;
  private Context k;
  private boolean l;
  private final MessageListView m;
  private boolean n;
  private int o = 0;
  private int p = 0;
  private int q = 0;
  private int r = 1;
  private boolean s;
  private int t = -1;
  
  public vx(Context paramContext, Cursor paramCursor, MessageListView paramMessageListView, boolean paramBoolean1, Pattern paramPattern, boolean paramBoolean2)
  {
    super(paramContext, paramCursor, 2);
    k = paramContext;
    j = paramPattern;
    n = paramBoolean2;
    b = ((LayoutInflater)paramContext.getSystemService("layout_inflater"));
    g = new vx.b(50);
    if (paramBoolean1) {}
    for (c = new vx.a();; c = new vx.a(paramCursor))
    {
      o = paramContext.getResources().getDimensionPixelSize(2131558732);
      p = paramContext.getResources().getDimensionPixelSize(2131558737);
      q = paramContext.getResources().getDimensionPixelSize(2131558775);
      d = k.getResources().getDimensionPixelSize(2131558771);
      e = k.getResources().getDimensionPixelSize(2131558772);
      f = k.getResources().getDimensionPixelSize(2131558773);
      m = paramMessageListView;
      paramMessageListView.setRecyclerListener(new vy(this));
      return;
    }
  }
  
  public static final long a(String paramString, long paramLong)
  {
    return a(paramString.equals("mms"), paramLong);
  }
  
  public static final long a(boolean paramBoolean, long paramLong)
  {
    long l1 = paramLong;
    if (paramBoolean) {
      l1 = -paramLong;
    }
    return l1;
  }
  
  private boolean b(Cursor paramCursor)
  {
    return (paramCursor != null) && (!paramCursor.isClosed()) && (!paramCursor.isBeforeFirst()) && (!paramCursor.isAfterLast());
  }
  
  private int c(Cursor paramCursor)
  {
    int i2 = 1;
    if ("sms".equals(paramCursor.getString(c.a)))
    {
      int i3 = paramCursor.getInt(c.h);
      if (i3 != 1)
      {
        i1 = i2;
        if (i3 != 0) {}
      }
      else
      {
        i1 = i2;
        if (i3 != 7) {
          i1 = 0;
        }
      }
      return i1;
    }
    int i1 = paramCursor.getInt(c.p);
    i2 = paramCursor.getInt(c.o);
    paramCursor = paramCursor.getString(c.D);
    if ((!TextUtils.isEmpty(paramCursor)) && (paramCursor.contains("talk#audio/amr")) && (i2 != 130))
    {
      if ((i1 == 1) || (i1 == 0)) {
        return 4;
      }
      return 5;
    }
    if ((i1 == 1) || (i1 == 0)) {
      return 2;
    }
    return 3;
  }
  
  private boolean c()
  {
    return ((Boolean)aau.a(CursorAdapter.class, this, "mDataValid")).booleanValue();
  }
  
  public Cursor a(vv paramvv)
  {
    Cursor localCursor = getCursor();
    if ((b(localCursor)) && (localCursor.moveToFirst())) {
      do
      {
        long l1 = localCursor.getLong(r);
        String str = localCursor.getString(0);
        if ((l1 == e) && (d.equals(str))) {
          return localCursor;
        }
      } while (localCursor.moveToNext());
    }
    return null;
  }
  
  public vv a(Cursor paramCursor)
  {
    return a(paramCursor.getString(c.a), paramCursor.getLong(c.b), paramCursor);
  }
  
  public vv a(String paramString, long paramLong, Cursor paramCursor)
  {
    vv localvv = (vv)g.get(Long.valueOf(a(paramString, paramLong)));
    Object localObject = localvv;
    if (localvv == null)
    {
      localObject = localvv;
      if (paramCursor != null)
      {
        localObject = localvv;
        if (!b(paramCursor)) {}
      }
    }
    try
    {
      paramString = new vv(k, paramString, paramCursor, c, j, n);
      Log.e("MessageListAdapter", "getCachedMessageItem: ", paramCursor);
    }
    catch (MmsException paramCursor)
    {
      try
      {
        g.put(Long.valueOf(a(d, e)), paramString);
        localObject = paramString;
        return (vv)localObject;
      }
      catch (MmsException paramCursor)
      {
        for (;;) {}
      }
      paramCursor = paramCursor;
      paramString = localvv;
    }
    return paramString;
  }
  
  public void a()
  {
    g.evictAll();
    MmsApp.c().n();
  }
  
  public void a(int paramInt)
  {
    t = paramInt;
  }
  
  public void a(Handler paramHandler)
  {
    i = paramHandler;
  }
  
  public void a(vx.c paramc)
  {
    h = paramc;
  }
  
  public void a(boolean paramBoolean)
  {
    n = paramBoolean;
  }
  
  public boolean areAllItemsEnabled()
  {
    return true;
  }
  
  public void b()
  {
    super.notifyDataSetChanged();
  }
  
  public void b(int paramInt)
  {
    u = paramInt;
  }
  
  public void b(boolean paramBoolean)
  {
    l = paramBoolean;
  }
  
  public void bindView(View paramView, Context paramContext, Cursor paramCursor)
  {
    if ((paramView instanceof MessageListItemBase))
    {
      paramContext = a(paramCursor.getString(c.a), paramCursor.getLong(c.b), paramCursor);
      if (paramContext != null) {}
    }
    else
    {
      return;
    }
    paramView = (MessageListItemBase)paramView;
    int i1 = paramCursor.getPosition();
    paramView.setNumberCheckState(t);
    paramView.setUnderlineFuncState(u);
    paramView.a(paramContext, l, i1, m, paramCursor.isLast());
    paramView.setEnableCheckBox(s);
    paramView.setMsgListItemHandler(i);
    i1 = e;
    if (m.m()) {
      i1 = f;
    }
    if ((paramCursor.isFirst()) && (paramCursor.isLast()))
    {
      paramView.setPadding(d, o, i1, p);
      return;
    }
    if (paramCursor.isFirst())
    {
      paramView.setPadding(d, o, i1, q);
      return;
    }
    if (paramCursor.isLast())
    {
      paramView.setPadding(d, 0, i1, p);
      return;
    }
    paramView.setPadding(d, 0, i1, q);
  }
  
  public void c(boolean paramBoolean)
  {
    s = paramBoolean;
  }
  
  public long getItemId(int paramInt)
  {
    long l2 = 0L;
    long l1 = l2;
    if (c())
    {
      l1 = l2;
      if (getCursor() != null)
      {
        l1 = l2;
        if (getCursor().moveToPosition(paramInt))
        {
          l1 = getCursor().getLong(r);
          l1 = a(getCursor().getString(c.a), l1);
        }
      }
    }
    return l1;
  }
  
  public int getItemViewType(int paramInt)
  {
    Cursor localCursor = (Cursor)getItem(paramInt);
    if (localCursor != null) {
      return c(localCursor);
    }
    Log.i("MessageListAdapter", "getItemViewType cursor is null, the position = " + paramInt);
    return -1;
  }
  
  public int getViewTypeCount()
  {
    return 6;
  }
  
  public View newView(Context paramContext, Cursor paramCursor, ViewGroup paramViewGroup)
  {
    switch (c(paramCursor))
    {
    default: 
      return null;
    case 0: 
      return b.inflate(2130968743, paramViewGroup, false);
    case 1: 
      return b.inflate(2130968747, paramViewGroup, false);
    case 2: 
      paramContext = b.inflate(2130968741, paramViewGroup, false);
      paramContext.findViewById(2131886462).setVisibility(0);
      return paramContext;
    case 3: 
      paramContext = b.inflate(2130968745, paramViewGroup, false);
      paramContext.findViewById(2131886462).setVisibility(0);
      return paramContext;
    case 4: 
      return b.inflate(2130968744, paramViewGroup, false);
    }
    return b.inflate(2130968748, paramViewGroup, false);
  }
  
  public void notifyDataSetChanged()
  {
    super.notifyDataSetChanged();
    g.evictAll();
    if (h != null) {
      h.a(this);
    }
  }
  
  protected void onContentChanged()
  {
    if ((getCursor() != null) && (!getCursor().isClosed()) && (h != null)) {
      h.b(this);
    }
  }
  
  public static class a
  {
    public int A;
    public int B;
    public int C;
    public int D;
    public int E;
    public int F;
    public int G;
    public int H;
    public int I;
    public int J;
    public int K;
    public int L;
    public int a;
    public int b;
    public int c;
    public int d;
    public int e;
    public int f;
    public int g;
    public int h;
    public int i;
    public int j;
    public int k;
    public int l;
    public int m;
    public int n;
    public int o;
    public int p;
    public int q;
    public int r;
    public int s;
    public int t;
    public int u;
    public int v;
    public int w;
    public int x;
    public int y;
    public int z;
    
    public a()
    {
      a = 0;
      b = 1;
      c = 2;
      d = 3;
      e = 4;
      f = 5;
      g = 6;
      h = 8;
      i = 9;
      j = 10;
      k = 11;
      l = 12;
      m = 13;
      n = 14;
      o = 18;
      p = 19;
      q = 20;
      r = 21;
      s = 22;
      t = 23;
      u = 24;
      v = 25;
      w = 26;
      x = 27;
      y = 10;
      z = 28;
      A = 29;
      B = 30;
      C = 31;
      D = 32;
      E = 33;
      F = 34;
      G = 35;
      H = 36;
      I = 37;
      J = 38;
      K = 39;
      L = 40;
    }
    
    public a(Cursor paramCursor)
    {
      try
      {
        a = paramCursor.getColumnIndexOrThrow("transport_type");
      }
      catch (IllegalArgumentException localIllegalArgumentException33)
      {
        try
        {
          b = paramCursor.getColumnIndexOrThrow("_id");
        }
        catch (IllegalArgumentException localIllegalArgumentException33)
        {
          try
          {
            c = paramCursor.getColumnIndexOrThrow("thread_id");
          }
          catch (IllegalArgumentException localIllegalArgumentException33)
          {
            try
            {
              d = paramCursor.getColumnIndexOrThrow("address");
            }
            catch (IllegalArgumentException localIllegalArgumentException33)
            {
              try
              {
                e = paramCursor.getColumnIndexOrThrow("body");
              }
              catch (IllegalArgumentException localIllegalArgumentException33)
              {
                try
                {
                  f = paramCursor.getColumnIndexOrThrow("date");
                }
                catch (IllegalArgumentException localIllegalArgumentException33)
                {
                  try
                  {
                    g = paramCursor.getColumnIndexOrThrow("date_sent");
                  }
                  catch (IllegalArgumentException localIllegalArgumentException33)
                  {
                    try
                    {
                      h = paramCursor.getColumnIndexOrThrow("type");
                    }
                    catch (IllegalArgumentException localIllegalArgumentException33)
                    {
                      try
                      {
                        i = paramCursor.getColumnIndexOrThrow("status");
                      }
                      catch (IllegalArgumentException localIllegalArgumentException33)
                      {
                        try
                        {
                          j = paramCursor.getColumnIndexOrThrow("locked");
                        }
                        catch (IllegalArgumentException localIllegalArgumentException33)
                        {
                          try
                          {
                            k = paramCursor.getColumnIndexOrThrow("error_code");
                          }
                          catch (IllegalArgumentException localIllegalArgumentException33)
                          {
                            try
                            {
                              m = paramCursor.getColumnIndexOrThrow("sub");
                            }
                            catch (IllegalArgumentException localIllegalArgumentException33)
                            {
                              try
                              {
                                n = paramCursor.getColumnIndexOrThrow("sub_cs");
                              }
                              catch (IllegalArgumentException localIllegalArgumentException33)
                              {
                                try
                                {
                                  o = paramCursor.getColumnIndexOrThrow("m_type");
                                }
                                catch (IllegalArgumentException localIllegalArgumentException33)
                                {
                                  try
                                  {
                                    p = paramCursor.getColumnIndexOrThrow("msg_box");
                                  }
                                  catch (IllegalArgumentException localIllegalArgumentException33)
                                  {
                                    try
                                    {
                                      q = paramCursor.getColumnIndexOrThrow("d_rpt");
                                    }
                                    catch (IllegalArgumentException localIllegalArgumentException33)
                                    {
                                      try
                                      {
                                        r = paramCursor.getColumnIndexOrThrow("rr");
                                      }
                                      catch (IllegalArgumentException localIllegalArgumentException33)
                                      {
                                        try
                                        {
                                          s = paramCursor.getColumnIndexOrThrow("err_type");
                                        }
                                        catch (IllegalArgumentException localIllegalArgumentException33)
                                        {
                                          try
                                          {
                                            t = paramCursor.getColumnIndexOrThrow("locked");
                                          }
                                          catch (IllegalArgumentException localIllegalArgumentException33)
                                          {
                                            try
                                            {
                                              u = paramCursor.getColumnIndexOrThrow("st");
                                            }
                                            catch (IllegalArgumentException localIllegalArgumentException33)
                                            {
                                              try
                                              {
                                                v = paramCursor.getColumnIndexOrThrow("text_only");
                                              }
                                              catch (IllegalArgumentException localIllegalArgumentException33)
                                              {
                                                try
                                                {
                                                  w = paramCursor.getColumnIndexOrThrow("delivery_status");
                                                }
                                                catch (IllegalArgumentException localIllegalArgumentException33)
                                                {
                                                  try
                                                  {
                                                    x = paramCursor.getColumnIndexOrThrow("resp_st");
                                                  }
                                                  catch (IllegalArgumentException localIllegalArgumentException33)
                                                  {
                                                    try
                                                    {
                                                      y = paramCursor.getColumnIndexOrThrow("is_status_report");
                                                    }
                                                    catch (IllegalArgumentException localIllegalArgumentException33)
                                                    {
                                                      try
                                                      {
                                                        z = paramCursor.getColumnIndexOrThrow("association_id");
                                                      }
                                                      catch (IllegalArgumentException localIllegalArgumentException33)
                                                      {
                                                        try
                                                        {
                                                          A = paramCursor.getColumnIndexOrThrow("port");
                                                        }
                                                        catch (IllegalArgumentException localIllegalArgumentException33)
                                                        {
                                                          try
                                                          {
                                                            B = paramCursor.getColumnIndexOrThrow("protocol");
                                                          }
                                                          catch (IllegalArgumentException localIllegalArgumentException33)
                                                          {
                                                            try
                                                            {
                                                              E = paramCursor.getColumnIndexOrThrow("file_link");
                                                            }
                                                            catch (IllegalArgumentException localIllegalArgumentException33)
                                                            {
                                                              try
                                                              {
                                                                F = paramCursor.getColumnIndexOrThrow("m_size");
                                                              }
                                                              catch (IllegalArgumentException localIllegalArgumentException33)
                                                              {
                                                                try
                                                                {
                                                                  G = paramCursor.getColumnIndexOrThrow("exp");
                                                                }
                                                                catch (IllegalArgumentException localIllegalArgumentException33)
                                                                {
                                                                  try
                                                                  {
                                                                    H = paramCursor.getColumnIndexOrThrow("t_rate");
                                                                  }
                                                                  catch (IllegalArgumentException localIllegalArgumentException33)
                                                                  {
                                                                    try
                                                                    {
                                                                      I = paramCursor.getColumnIndexOrThrow("group_msg_id");
                                                                    }
                                                                    catch (IllegalArgumentException localIllegalArgumentException33)
                                                                    {
                                                                      try
                                                                      {
                                                                        J = paramCursor.getColumnIndexOrThrow("is_favorite");
                                                                      }
                                                                      catch (IllegalArgumentException localIllegalArgumentException33)
                                                                      {
                                                                        try
                                                                        {
                                                                          for (;;)
                                                                          {
                                                                            K = paramCursor.getColumnIndexOrThrow("sim_id");
                                                                            try
                                                                            {
                                                                              L = paramCursor.getColumnIndexOrThrow("imsi");
                                                                              return;
                                                                            }
                                                                            catch (IllegalArgumentException paramCursor)
                                                                            {
                                                                              Log.w("colsMap", paramCursor.getMessage());
                                                                            }
                                                                            localIllegalArgumentException1 = localIllegalArgumentException1;
                                                                            Log.w("colsMap", localIllegalArgumentException1.getMessage());
                                                                            continue;
                                                                            localIllegalArgumentException2 = localIllegalArgumentException2;
                                                                            Log.w("colsMap", localIllegalArgumentException2.getMessage());
                                                                            continue;
                                                                            localIllegalArgumentException3 = localIllegalArgumentException3;
                                                                            Log.w("colsMap", localIllegalArgumentException3.getMessage());
                                                                            continue;
                                                                            localIllegalArgumentException4 = localIllegalArgumentException4;
                                                                            Log.w("colsMap", localIllegalArgumentException4.getMessage());
                                                                            continue;
                                                                            localIllegalArgumentException5 = localIllegalArgumentException5;
                                                                            Log.w("colsMap", localIllegalArgumentException5.getMessage());
                                                                            continue;
                                                                            localIllegalArgumentException6 = localIllegalArgumentException6;
                                                                            Log.w("colsMap", localIllegalArgumentException6.getMessage());
                                                                            continue;
                                                                            localIllegalArgumentException7 = localIllegalArgumentException7;
                                                                            Log.w("colsMap", localIllegalArgumentException7.getMessage());
                                                                            continue;
                                                                            localIllegalArgumentException8 = localIllegalArgumentException8;
                                                                            Log.w("colsMap", localIllegalArgumentException8.getMessage());
                                                                            continue;
                                                                            localIllegalArgumentException9 = localIllegalArgumentException9;
                                                                            Log.w("colsMap", localIllegalArgumentException9.getMessage());
                                                                            continue;
                                                                            localIllegalArgumentException10 = localIllegalArgumentException10;
                                                                            Log.w("colsMap", localIllegalArgumentException10.getMessage());
                                                                            continue;
                                                                            localIllegalArgumentException11 = localIllegalArgumentException11;
                                                                            Log.w("colsMap", localIllegalArgumentException11.getMessage());
                                                                            continue;
                                                                            localIllegalArgumentException12 = localIllegalArgumentException12;
                                                                            Log.w("colsMap", localIllegalArgumentException12.getMessage());
                                                                            continue;
                                                                            localIllegalArgumentException13 = localIllegalArgumentException13;
                                                                            Log.w("colsMap", localIllegalArgumentException13.getMessage());
                                                                            continue;
                                                                            localIllegalArgumentException14 = localIllegalArgumentException14;
                                                                            Log.w("colsMap", localIllegalArgumentException14.getMessage());
                                                                            continue;
                                                                            localIllegalArgumentException15 = localIllegalArgumentException15;
                                                                            Log.w("colsMap", localIllegalArgumentException15.getMessage());
                                                                            continue;
                                                                            localIllegalArgumentException16 = localIllegalArgumentException16;
                                                                            Log.w("colsMap", localIllegalArgumentException16.getMessage());
                                                                            continue;
                                                                            localIllegalArgumentException17 = localIllegalArgumentException17;
                                                                            Log.w("colsMap", localIllegalArgumentException17.getMessage());
                                                                            continue;
                                                                            localIllegalArgumentException18 = localIllegalArgumentException18;
                                                                            Log.w("colsMap", localIllegalArgumentException18.getMessage());
                                                                            continue;
                                                                            localIllegalArgumentException19 = localIllegalArgumentException19;
                                                                            Log.w("colsMap", localIllegalArgumentException19.getMessage());
                                                                            continue;
                                                                            localIllegalArgumentException20 = localIllegalArgumentException20;
                                                                            Log.w("colsMap", localIllegalArgumentException20.getMessage());
                                                                            continue;
                                                                            localIllegalArgumentException21 = localIllegalArgumentException21;
                                                                            Log.w("colsMap", localIllegalArgumentException21.getMessage());
                                                                            continue;
                                                                            localIllegalArgumentException22 = localIllegalArgumentException22;
                                                                            Log.w("colsMap", localIllegalArgumentException22.getMessage());
                                                                            continue;
                                                                            localIllegalArgumentException23 = localIllegalArgumentException23;
                                                                            Log.w("colsMap", localIllegalArgumentException23.getMessage());
                                                                            continue;
                                                                            localIllegalArgumentException24 = localIllegalArgumentException24;
                                                                            Log.w("colsMap", localIllegalArgumentException24.getMessage());
                                                                            continue;
                                                                            localIllegalArgumentException25 = localIllegalArgumentException25;
                                                                            Log.w("colsMap", localIllegalArgumentException25.getMessage());
                                                                            continue;
                                                                            localIllegalArgumentException26 = localIllegalArgumentException26;
                                                                            Log.w("colsMap", localIllegalArgumentException26.getMessage());
                                                                            continue;
                                                                            localIllegalArgumentException27 = localIllegalArgumentException27;
                                                                            Log.w("colsMap", localIllegalArgumentException27.getMessage());
                                                                            continue;
                                                                            localIllegalArgumentException28 = localIllegalArgumentException28;
                                                                            Log.w("colsMap", localIllegalArgumentException28.getMessage());
                                                                            continue;
                                                                            localIllegalArgumentException29 = localIllegalArgumentException29;
                                                                            Log.w("colsMap", localIllegalArgumentException29.getMessage());
                                                                            continue;
                                                                            localIllegalArgumentException30 = localIllegalArgumentException30;
                                                                            Log.w("colsMap", localIllegalArgumentException30.getMessage());
                                                                            continue;
                                                                            localIllegalArgumentException31 = localIllegalArgumentException31;
                                                                            Log.w("colsMap", localIllegalArgumentException31.getMessage());
                                                                            continue;
                                                                            localIllegalArgumentException32 = localIllegalArgumentException32;
                                                                            Log.w("colsMap", localIllegalArgumentException32.getMessage());
                                                                            continue;
                                                                            localIllegalArgumentException33 = localIllegalArgumentException33;
                                                                            Log.w("colsMap", localIllegalArgumentException33.getMessage());
                                                                          }
                                                                        }
                                                                        catch (IllegalArgumentException localIllegalArgumentException34)
                                                                        {
                                                                          for (;;)
                                                                          {
                                                                            Log.w("colsMap", localIllegalArgumentException34.getMessage());
                                                                          }
                                                                        }
                                                                      }
                                                                    }
                                                                  }
                                                                }
                                                              }
                                                            }
                                                          }
                                                        }
                                                      }
                                                    }
                                                  }
                                                }
                                              }
                                            }
                                          }
                                        }
                                      }
                                    }
                                  }
                                }
                              }
                            }
                          }
                        }
                      }
                    }
                  }
                }
              }
            }
          }
        }
      }
    }
  }
  
  static class b
    extends LruCache<Long, vv>
  {
    public b(int paramInt)
    {
      super();
    }
    
    protected void a(boolean paramBoolean, Long paramLong, vv paramvv1, vv paramvv2)
    {
      paramvv1.P();
    }
  }
  
  public static abstract interface c
  {
    public abstract void a(vx paramvx);
    
    public abstract void b(vx paramvx);
  }
}

/* Location:
 * Qualified Name:     vx
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */