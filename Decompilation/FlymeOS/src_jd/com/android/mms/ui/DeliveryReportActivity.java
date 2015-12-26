package com.android.mms.ui;

import aau;
import android.app.ListActivity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Telephony.Mms;
import android.provider.Telephony.Sms;
import android.telephony.PhoneNumberUtils;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import uf;
import ug;
import wd;

public class DeliveryReportActivity
  extends ListActivity
{
  static final String[] a = { "address", "d_rpt", "rr" };
  static final String[] b = { "address", "delivery_status", "read_status" };
  static final String[] c = { "address", "status", "date_sent", "type" };
  private long d;
  private String e;
  
  private long a(Bundle paramBundle, Intent paramIntent)
  {
    if (paramBundle != null) {}
    for (long l1 = paramBundle.getLong("message_id");; l1 = 0L)
    {
      long l2 = l1;
      if (l1 == 0L) {
        l2 = paramIntent.getLongExtra("message_id", 0L);
      }
      return l2;
    }
  }
  
  private static b a(Map<String, b> paramMap, String paramString)
  {
    Iterator localIterator = paramMap.keySet().iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      if (aau.d(paramString))
      {
        if (TextUtils.equals(str, paramString)) {
          return (b)paramMap.get(str);
        }
      }
      else if (PhoneNumberUtils.compare(str, paramString)) {
        return (b)paramMap.get(str);
      }
    }
    return null;
  }
  
  private String a(int paramInt)
  {
    if (paramInt == -1) {
      return getString(2131493131);
    }
    if (paramInt >= 64) {
      return getString(2131493129);
    }
    if (paramInt >= 32) {
      return getString(2131493132);
    }
    return getString(2131493134);
  }
  
  private String a(a parama, Map<String, b> paramMap)
  {
    if (paramMap == null) {
      return getString(2131493132);
    }
    String str = parama.a();
    if (aau.d(str)) {}
    for (str = (String)aau.a("android.provider.Telephony$Mms", "extractAddrSpec", String.class, str);; str = PhoneNumberUtils.stripSeparators(str))
    {
      paramMap = a(paramMap, str);
      if (paramMap != null) {
        break;
      }
      return getString(2131493132);
    }
    if ((parama.b()) && (b != 0)) {}
    switch (b)
    {
    default: 
      switch (a)
      {
      default: 
        return getString(2131493129);
      }
    case 128: 
      return getString(2131493133);
    }
    return getString(2131493136);
    return getString(2131493132);
    return getString(2131493134);
    return getString(2131493135);
  }
  
  private void a()
  {
    View localView = getLayoutInflater().inflate(2130968623, null);
    getListView().addHeaderView(localView, null, true);
  }
  
  private String b(Bundle paramBundle, Intent paramIntent)
  {
    String str = null;
    if (paramBundle != null) {
      str = paramBundle.getString("message_type");
    }
    paramBundle = str;
    if (str == null) {
      paramBundle = paramIntent.getStringExtra("message_type");
    }
    return paramBundle;
  }
  
  private void b()
  {
    List localList = d();
    Object localObject = localList;
    if (localList == null)
    {
      localObject = new ArrayList(1);
      ((List)localObject).add(new ug("", getString(2131493131), null));
      Log.w("DeliveryReportActivity", "cursor == null");
    }
    setListAdapter(new uf(this, (List)localObject));
  }
  
  private void c()
  {
    ListView localListView = getListView();
    localListView.invalidateViews();
    localListView.requestFocus();
  }
  
  private List<ug> d()
  {
    if (e.equals("sms")) {
      return e();
    }
    return f();
  }
  
  private List<ug> e()
  {
    String str = "_id = " + d;
    Cursor localCursor = getContentResolver().query(Telephony.Sms.CONTENT_URI, c, str, null, null);
    if (localCursor == null) {
      return null;
    }
    for (;;)
    {
      ArrayList localArrayList;
      try
      {
        int i = localCursor.getCount();
        if (i <= 0) {
          return null;
        }
        localArrayList = new ArrayList();
        if (localCursor.moveToNext())
        {
          long l = localCursor.getLong(2);
          if ((localCursor.getInt(3) != 2) || (l <= 0L)) {
            break label256;
          }
          str = getString(2131492934) + wd.a(this, l, true);
          localArrayList.add(new ug(getString(2131493075) + localCursor.getString(0), getString(2131493130) + a(localCursor.getInt(1)), str));
          continue;
        }
      }
      finally
      {
        localCursor.close();
      }
      return localArrayList;
      label256:
      Object localObject2 = null;
    }
  }
  
  private List<ug> f()
  {
    Object localObject = h();
    if (localObject == null) {
      return null;
    }
    if (((List)localObject).size() == 0) {
      return null;
    }
    Map localMap = g();
    ArrayList localArrayList = new ArrayList();
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      a locala = (a)((Iterator)localObject).next();
      String str = getString(2131493130) + a(locala, localMap);
      localArrayList.add(new ug(getString(2131493075) + locala.a(), str, null));
    }
    return localArrayList;
  }
  
  private Map<String, b> g()
  {
    Object localObject = Uri.withAppendedPath(Telephony.Mms.REPORT_STATUS_URI, String.valueOf(d));
    Cursor localCursor = getContentResolver().query((Uri)localObject, b, null, null, null);
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
          localObject = (String)aau.a("android.provider.Telephony$Mms", "extractAddrSpec", String.class, localObject);
          localHashMap.put(localObject, new b(localCursor.getInt(1), localCursor.getInt(2)));
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
  
  private List<a> h()
  {
    Object localObject = Uri.withAppendedPath(Telephony.Mms.REPORT_REQUEST_URI, String.valueOf(d));
    localObject = getContentResolver().query((Uri)localObject, a, null, null, null);
    if (localObject == null) {
      return null;
    }
    try
    {
      int i = ((Cursor)localObject).getCount();
      if (i <= 0) {
        return null;
      }
      ArrayList localArrayList = new ArrayList();
      while (((Cursor)localObject).moveToNext()) {
        localArrayList.add(new a(((Cursor)localObject).getString(0), ((Cursor)localObject).getInt(1), ((Cursor)localObject).getInt(2)));
      }
    }
    finally
    {
      ((Cursor)localObject).close();
    }
    return localList;
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    setContentView(2130968622);
    Intent localIntent = getIntent();
    d = a(paramBundle, localIntent);
    e = b(paramBundle, localIntent);
    a();
    b();
  }
  
  public void onResume()
  {
    super.onResume();
    c();
  }
  
  static final class a
  {
    private final String a;
    private final boolean b;
    private final boolean c;
    
    public a(String paramString, int paramInt1, int paramInt2)
    {
      a = paramString;
      if (paramInt1 == 128)
      {
        bool1 = true;
        b = bool1;
        if (paramInt2 != 128) {
          break label52;
        }
      }
      label52:
      for (boolean bool1 = bool2;; bool1 = false)
      {
        c = bool1;
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
      return c;
    }
  }
  
  static final class b
  {
    final int a;
    final int b;
    
    public b(int paramInt1, int paramInt2)
    {
      a = paramInt1;
      b = paramInt2;
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.DeliveryReportActivity
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */