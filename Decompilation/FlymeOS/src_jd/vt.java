import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Telephony.Mms;
import android.provider.Telephony.Sms;
import android.telephony.PhoneNumberUtils;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.android.mms.transaction.SmsReceiver;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class vt
  extends ListView
{
  public static final String[] a = { "address", "d_rpt", "rr", "date", "resp_st", "msg_box" };
  public static final String[] b = { "address", "delivery_status", "read_status" };
  public static final String[] c = { "address", "status", "group_msg_id", "date", "type", "_id", "date_sent" };
  private int d = 0;
  private long e;
  private String f;
  private String g;
  private int h;
  private boolean i;
  private long j;
  private long k;
  private int l;
  private vt.b m;
  private List<vt.c> n;
  private Context o;
  private vt.a p;
  
  public vt(Context paramContext)
  {
    super(paramContext);
    o = paramContext;
  }
  
  private static vt.e a(Map<String, vt.e> paramMap, String paramString)
  {
    Iterator localIterator = paramMap.keySet().iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      if (aau.d(paramString))
      {
        if (TextUtils.equals(str, paramString)) {
          return (vt.e)paramMap.get(str);
        }
      }
      else if (PhoneNumberUtils.compare(str, paramString)) {
        return (vt.e)paramMap.get(str);
      }
    }
    return null;
  }
  
  private void a(vt.c paramc, int paramInt1, int paramInt2)
  {
    int i1 = 2131493332;
    boolean bool;
    switch (paramInt2)
    {
    case 3: 
    default: 
      paramInt1 = 2131493329;
      bool = false;
    }
    for (;;)
    {
      b = o.getString(paramInt1);
      d = bool;
      return;
      if (paramInt1 == -1)
      {
        bool = false;
        paramInt1 = i1;
      }
      else if (paramInt1 >= 64)
      {
        paramInt1 = 2131493327;
        bool = true;
      }
      else if (paramInt1 >= 32)
      {
        bool = false;
        paramInt1 = i1;
      }
      else
      {
        paramInt1 = 2131493331;
        bool = false;
        continue;
        paramInt1 = 2131493333;
        bool = false;
        continue;
        paramInt1 = 2131493328;
        bool = true;
        continue;
        paramInt1 = 2131493330;
        bool = false;
        continue;
        paramInt1 = 2131493330;
        bool = false;
      }
    }
  }
  
  private void a(vt.c paramc, vt.d paramd, Map<String, vt.e> paramMap)
  {
    if (wd.a(vt.d.a(paramd)))
    {
      b = o.getString(2131493328);
      d = true;
      return;
    }
    if (vt.d.b(paramd) == 4)
    {
      b = o.getString(2131493333);
      d = false;
      return;
    }
    if (paramMap == null)
    {
      b = o.getString(2131493332);
      d = false;
      Log.v("MessageDeliveyInfoListView", "setMmsReportStatusText1: item.status = " + b);
      return;
    }
    String str = paramd.a();
    if (aau.d(str)) {}
    for (str = (String)aau.a("android.provider.Telephony$Mms", "extractAddrSpec", String.class, str);; str = PhoneNumberUtils.stripSeparators(str))
    {
      paramMap = a(paramMap, str);
      if (paramMap != null) {
        break;
      }
      b = o.getString(2131493332);
      d = false;
      Log.v("MessageDeliveyInfoListView", "setMmsReportStatusText2: item.status = " + b);
      return;
    }
    if ((paramd.b()) && (b != 0)) {}
    switch (b)
    {
    default: 
      switch (a)
      {
      default: 
        b = o.getString(2131493327);
        d = false;
        return;
      }
    case 128: 
      b = o.getString(2131493133);
      d = false;
      return;
    }
    b = o.getString(2131493136);
    d = false;
    return;
    b = o.getString(2131493332);
    d = false;
    return;
    b = o.getString(2131493331);
    d = false;
    return;
    b = o.getString(2131493135);
    d = false;
  }
  
  private void b(Bundle paramBundle)
  {
    e = c(paramBundle);
    f = e(paramBundle);
    g = d(paramBundle);
    h = f(paramBundle);
    i = g(paramBundle);
    j = h(paramBundle);
    k = i(paramBundle);
    l = j(paramBundle);
    Log.d("MessageDeliveyInfoListView", "initIntentData slotId : " + l);
  }
  
  private void b(vt.c paramc)
  {
    Log.e("MessageDeliveyInfoListView", "reSendSmsMessageItem mProtocol is " + h);
    paramc = ContentUris.withAppendedId(Telephony.Sms.CONTENT_URI, e);
    Object localObject = new ContentValues(2);
    ((ContentValues)localObject).put("sim_id", Integer.valueOf(l));
    ((ContentValues)localObject).put("imsi", zv.c(l));
    o.getContentResolver().update(paramc, (ContentValues)localObject, null, null);
    localObject = Integer.TYPE;
    Class localClass = Integer.TYPE;
    Context localContext = o;
    if (!((Boolean)aau.a(Telephony.Sms.class, "moveMessageToFolder", new Class[] { Context.class, Uri.class, localObject, localClass }, new Object[] { localContext, paramc, Integer.valueOf(6), Integer.valueOf(0) })).booleanValue()) {
      Log.e("MessageDeliveyInfoListView", "handleSmsSent: failed to move message " + paramc + " to sent folder");
    }
    if (h == 256) {}
    for (paramc = "com.android.mms.transaction.SEND_SIP_MESSAGE";; paramc = "com.android.mms.transaction.SEND_MESSAGE")
    {
      paramc = new Intent(paramc, null, o, SmsReceiver.class);
      paramc.putExtra("sim_id", l);
      o.sendBroadcast(paramc);
      return;
    }
  }
  
  private long c(Bundle paramBundle)
  {
    long l2 = 0L;
    long l1 = l2;
    if (paramBundle != null)
    {
      l1 = l2;
      if (paramBundle.containsKey("message_id")) {
        l1 = paramBundle.getLong("message_id");
      }
    }
    return l1;
  }
  
  private void c()
  {
    new vt.g().execute(new Intent[0]);
  }
  
  private String d(Bundle paramBundle)
  {
    Object localObject2 = null;
    Object localObject1 = localObject2;
    if (paramBundle != null)
    {
      localObject1 = localObject2;
      if (paramBundle.containsKey("message_group_id")) {
        localObject1 = paramBundle.getString("message_group_id");
      }
    }
    return (String)localObject1;
  }
  
  private void d()
  {
    m = new vt.b(o);
    setAdapter(m);
  }
  
  private String e(Bundle paramBundle)
  {
    Object localObject2 = null;
    Object localObject1 = localObject2;
    if (paramBundle != null)
    {
      localObject1 = localObject2;
      if (paramBundle.containsKey("message_type")) {
        localObject1 = paramBundle.getString("message_type");
      }
    }
    return (String)localObject1;
  }
  
  private int f(Bundle paramBundle)
  {
    int i2 = 0;
    int i1 = i2;
    if (paramBundle != null)
    {
      i1 = i2;
      if (paramBundle.containsKey("message_protocol")) {
        i1 = paramBundle.getInt("message_protocol");
      }
    }
    return i1;
  }
  
  private boolean g(Bundle paramBundle)
  {
    boolean bool2 = true;
    boolean bool1 = bool2;
    if (paramBundle != null)
    {
      bool1 = bool2;
      if (paramBundle.containsKey("message_is_sms")) {
        bool1 = paramBundle.getBoolean("message_is_sms", true);
      }
    }
    return bool1;
  }
  
  private List<vt.c> getMmsReportItems()
  {
    Object localObject = getMmsReportRequests();
    if (localObject == null) {}
    while (((List)localObject).size() == 0) {
      return null;
    }
    Map localMap = getMmsReportStatus();
    ArrayList localArrayList = new ArrayList();
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      vt.d locald = (vt.d)((Iterator)localObject).next();
      vt.c localc = new vt.c(locald.a(), "", wd.a(o, vt.d.c(locald), true));
      gm localgm = gm.a(a, false);
      f = localgm.h();
      a = localgm.d();
      a(localc, locald, localMap);
      Log.v("MessageDeliveyInfoListView", "getMmsReportItems: item.status = " + b);
      localArrayList.add(localc);
    }
    return localArrayList;
  }
  
  private List<vt.d> getMmsReportRequests()
  {
    Object localObject = Uri.withAppendedPath(Telephony.Mms.REPORT_REQUEST_URI, String.valueOf(e));
    localObject = o.getContentResolver().query((Uri)localObject, a, null, null, null);
    if (localObject == null) {
      return null;
    }
    try
    {
      int i1 = ((Cursor)localObject).getCount();
      if (i1 <= 0) {
        return null;
      }
      ArrayList localArrayList = new ArrayList();
      while (((Cursor)localObject).moveToNext()) {
        localArrayList.add(new vt.d(((Cursor)localObject).getString(0), ((Cursor)localObject).getInt(1), ((Cursor)localObject).getInt(2), ((Cursor)localObject).getLong(3), ((Cursor)localObject).getInt(4), ((Cursor)localObject).getInt(5)));
      }
    }
    finally
    {
      ((Cursor)localObject).close();
    }
    return localList;
  }
  
  private Map<String, vt.e> getMmsReportStatus()
  {
    Object localObject = Uri.withAppendedPath(Telephony.Mms.REPORT_STATUS_URI, String.valueOf(e));
    Cursor localCursor = o.getContentResolver().query((Uri)localObject, b, null, null, null);
    if (localCursor == null) {
      return null;
    }
    HashMap localHashMap;
    for (;;)
    {
      try
      {
        localHashMap = new HashMap();
        if (!localCursor.moveToNext()) {
          break;
        }
        localObject = localCursor.getString(0);
        if (aau.d((String)localObject))
        {
          localObject = (String)aau.a(Telephony.Mms.class, "extractAddrSpec", String.class, localObject);
          localHashMap.put(localObject, new vt.e(localCursor.getInt(1), localCursor.getInt(2)));
        }
        else
        {
          String str2 = PhoneNumberUtils.stripSeparators(str1);
        }
      }
      finally
      {
        localCursor.close();
      }
    }
    localCursor.close();
    return localHashMap;
  }
  
  private List<vt.c> getReportItems()
  {
    if (f.equals("sms")) {
      return getSmsReportItems();
    }
    return getMmsReportItems();
  }
  
  private List<vt.c> getSmsReportItems()
  {
    new StringBuilder().append("_id = ").append(e).toString();
    Object localObject1;
    Object localObject2;
    if (TextUtils.isEmpty(g))
    {
      localObject1 = "_id = " + e;
      localObject2 = o.getContentResolver().query(Telephony.Sms.CONTENT_URI, c, (String)localObject1, null, "_id ASC");
      if (localObject2 != null) {
        break label124;
      }
    }
    label124:
    do
    {
      return null;
      localObject1 = "group_msg_id = '" + g + "'";
      break;
      localObject1 = localObject2;
      if (!TextUtils.isEmpty(g)) {
        break label312;
      }
      if ((((Cursor)localObject2).getCount() != 1) || (!((Cursor)localObject2).moveToFirst()))
      {
        Log.e("MessageDeliveyInfoListView", "why message id have " + ((Cursor)localObject2).getCount() + "item or move to first failed");
        ((Cursor)localObject2).close();
        return null;
      }
      g = ((Cursor)localObject2).getString(((Cursor)localObject2).getColumnIndex("group_msg_id"));
      ((Cursor)localObject2).close();
      if (TextUtils.isEmpty(g))
      {
        Log.e("MessageDeliveyInfoListView", "why mSmsGroupId id is empty");
        return null;
      }
      localObject1 = "group_msg_id = '" + g + "'";
      localObject1 = o.getContentResolver().query(Telephony.Sms.CONTENT_URI, c, (String)localObject1, null, null);
    } while (localObject1 == null);
    for (;;)
    {
      try
      {
        label312:
        int i1 = ((Cursor)localObject1).getCount();
        if (i1 <= 0) {
          return null;
        }
        localObject2 = new ArrayList();
        if (!((Cursor)localObject1).moveToNext()) {
          break;
        }
        i1 = ((Cursor)localObject1).getInt(1);
        int i2 = ((Cursor)localObject1).getInt(4);
        Object localObject3 = ((Cursor)localObject1).getString(0);
        long l1;
        if (i2 == 7)
        {
          l1 = ((Cursor)localObject1).getLong(6);
          long l2 = ((Cursor)localObject1).getLong(5);
          localObject3 = new vt.c((String)localObject3, "", wd.a(o, l1, true));
          gm localgm = gm.a(a, false);
          f = localgm.h();
          a = localgm.d();
          e = l2;
          a((vt.c)localObject3, i1, i2);
          ((List)localObject2).add(localObject3);
        }
        else
        {
          l1 = ((Cursor)localObject1).getLong(3);
        }
      }
      finally
      {
        ((Cursor)localObject1).close();
      }
    }
    ((Cursor)localObject1).close();
    return localList;
  }
  
  private long h(Bundle paramBundle)
  {
    long l2 = 0L;
    long l1 = l2;
    if (paramBundle != null)
    {
      l1 = l2;
      if (paramBundle.containsKey("mms_size")) {
        l1 = paramBundle.getLong("mms_size");
      }
    }
    return l1;
  }
  
  private long i(Bundle paramBundle)
  {
    long l2 = 0L;
    long l1 = l2;
    if (paramBundle != null)
    {
      l1 = l2;
      if (paramBundle.containsKey("message_thread_id")) {
        l1 = paramBundle.getLong("message_thread_id");
      }
    }
    return l1;
  }
  
  private int j(Bundle paramBundle)
  {
    if (zv.a)
    {
      int i2 = -1;
      int i1 = i2;
      if (paramBundle != null)
      {
        i1 = i2;
        if (paramBundle.containsKey("message_sim_id")) {
          i1 = paramBundle.getInt("message_sim_id");
        }
      }
      return i1;
    }
    return 0;
  }
  
  public void a()
  {
    c();
  }
  
  public void a(Bundle paramBundle)
  {
    d = o.getResources().getDimensionPixelSize(2131558647);
    b(paramBundle);
    setScrollBarStyle(33554432);
    setClipToPadding(false);
    setPadding(d, 0, d, 0);
    setOnItemClickListener(null);
    setSelector(new ColorDrawable(0));
    setStackFromBottom(true);
    c();
  }
  
  public void a(vt.c paramc)
  {
    new Thread(new vu(this)).start();
  }
  
  public void b()
  {
    n = getReportItems();
    if ((m != null) && (n != null)) {
      m.notifyDataSetChanged();
    }
  }
  
  public vt.a getResendOnclickListener()
  {
    if (p == null) {
      p = new vt.a(null);
    }
    return p;
  }
  
  public void setSlotId(int paramInt)
  {
    Log.i("MessageDeliveyInfoListView", "setSlotId slotId = " + paramInt);
    l = paramInt;
  }
  
  class a
    implements View.OnClickListener
  {
    private int b;
    
    private a() {}
    
    public void onClick(View paramView)
    {
      if (paramView.getId() == 2131886475) {
        b = ((Integer)paramView.getTag()).intValue();
      }
      paramView = (vt.c)vt.e(vt.this).getItem(b);
      if ((paramView == null) || (!d)) {
        return;
      }
      if (vt.f(vt.this))
      {
        vt.a(vt.this, paramView);
        return;
      }
      a(paramView);
    }
  }
  
  final class b
    extends BaseAdapter
  {
    private final LayoutInflater b;
    private int c = 0;
    
    public b(Context paramContext)
    {
      b = LayoutInflater.from(paramContext);
      c = paramContext.getResources().getDimensionPixelSize(2131559031);
    }
    
    private View a(int paramInt, ViewGroup paramViewGroup)
    {
      paramViewGroup = b.inflate(2130968672, paramViewGroup, false);
      if (paramViewGroup != null)
      {
        vt.f localf = new vt.f();
        a = ((TextView)paramViewGroup.findViewById(2131886471));
        b = ((TextView)paramViewGroup.findViewById(2131886472));
        c = ((TextView)paramViewGroup.findViewById(2131886473));
        d = ((TextView)paramViewGroup.findViewById(2131886474));
        e = paramViewGroup.findViewById(2131886475);
        e.setOnClickListener(getResendOnclickListener());
        paramViewGroup.setTag(localf);
      }
      return paramViewGroup;
    }
    
    private CharSequence a(boolean paramBoolean, String paramString)
    {
      if (!paramBoolean) {
        return paramString;
      }
      paramString = new SpannableStringBuilder(paramString);
      paramString.setSpan(new ForegroundColorSpan(-1420734), 0, paramString.length(), 0);
      return paramString;
    }
    
    private void a(View paramView, int paramInt)
    {
      paramView = (vt.f)paramView.getTag();
      a((vt.c)vt.b(vt.this).get(paramInt), paramView);
      e.setTag(Integer.valueOf(paramInt));
    }
    
    private void a(vt.c paramc, vt.f paramf)
    {
      if (TextUtils.isEmpty(f))
      {
        b.setText(a.replaceAll(" ", ""));
        a.setVisibility(8);
      }
      for (;;)
      {
        c.setText(c);
        d.setText(a(d, b));
        if (!d) {
          break;
        }
        e.setVisibility(0);
        wd.a(e, ga.C());
        return;
        a.setVisibility(0);
        a.setText(f);
        b.setVisibility(0);
        b.setText(a.replaceAll(" ", ""));
      }
      e.setVisibility(8);
    }
    
    public int getCount()
    {
      if (vt.b(vt.this) == null) {
        return 0;
      }
      return vt.b(vt.this).size();
    }
    
    public Object getItem(int paramInt)
    {
      return vt.b(vt.this).get(paramInt);
    }
    
    public long getItemId(int paramInt)
    {
      return paramInt;
    }
    
    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      View localView = paramView;
      if (paramView == null) {
        localView = a(paramInt, paramViewGroup);
      }
      a(localView, paramInt);
      return localView;
    }
  }
  
  public static class c
    extends ug
  {
    public boolean d = false;
    public long e = 0L;
    public String f = "";
    
    public c(String paramString1, String paramString2, String paramString3)
    {
      this(paramString1, paramString2, paramString3, false);
    }
    
    public c(String paramString1, String paramString2, String paramString3, boolean paramBoolean)
    {
      super(paramString2, paramString3);
      d = paramBoolean;
    }
  }
  
  static final class d
  {
    private final String a;
    private final long b;
    private final boolean c;
    private final boolean d;
    private int e;
    private int f;
    
    public d(String paramString, int paramInt1, int paramInt2, long paramLong, int paramInt3, int paramInt4)
    {
      a = paramString;
      if (paramInt1 == 128)
      {
        bool1 = true;
        c = bool1;
        if (paramInt2 != 128) {
          break label74;
        }
      }
      label74:
      for (boolean bool1 = bool2;; bool1 = false)
      {
        d = bool1;
        b = (1000L * paramLong);
        e = paramInt3;
        f = paramInt4;
        return;
        bool1 = false;
        break;
      }
    }
    
    public String a()
    {
      return a;
    }
    
    public boolean b()
    {
      return d;
    }
  }
  
  static final class e
  {
    final int a;
    final int b;
    
    public e(int paramInt1, int paramInt2)
    {
      a = paramInt1;
      b = paramInt2;
    }
  }
  
  static final class f
  {
    public TextView a;
    public TextView b;
    public TextView c;
    public TextView d;
    public View e;
  }
  
  class g
    extends AsyncTask<Intent, Void, Void>
  {
    public g() {}
    
    protected Void a(Intent... paramVarArgs)
    {
      vt.a(vt.this, vt.a(vt.this));
      if (vt.b(vt.this) == null)
      {
        vt.a(vt.this, new ArrayList(1));
        vt.b(vt.this).add(new vt.c("", vt.c(vt.this).getString(2131493131), "--/--", false));
        Log.w("MessageDeliveyInfoListView", "cursor == null");
      }
      return null;
    }
    
    protected void a(Void paramVoid)
    {
      vt.d(vt.this);
    }
  }
}

/* Location:
 * Qualified Name:     vt
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */