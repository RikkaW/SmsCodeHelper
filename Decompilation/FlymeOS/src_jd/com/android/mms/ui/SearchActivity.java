package com.android.mms.ui;

import android.app.ActionBar;
import android.app.ListActivity;
import android.content.AsyncQueryHandler;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Bundle;
import android.provider.Telephony.MmsSms;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.style.StyleSpan;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import gm;
import gm.b;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import xk;
import xl;

public class SearchActivity
  extends ListActivity
{
  gm.b a;
  private AsyncQueryHandler b;
  
  private long a(long paramLong1, long paramLong2)
  {
    Object localObject1 = Uri.parse("content://mms-sms/messageIdToThread").buildUpon().appendQueryParameter("row_id", String.valueOf(paramLong1)).appendQueryParameter("table_to_use", String.valueOf(paramLong2)).build().toString();
    localObject1 = getContentResolver().query(Uri.parse((String)localObject1), null, null, null, null);
    if (localObject1 != null) {}
    try
    {
      if (((Cursor)localObject1).moveToFirst())
      {
        paramLong1 = ((Cursor)localObject1).getLong(((Cursor)localObject1).getColumnIndex("thread_id"));
        return paramLong1;
      }
      return -1L;
    }
    finally
    {
      ((Cursor)localObject1).close();
    }
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    Object localObject1 = getIntent().getStringExtra("query");
    paramBundle = (Bundle)localObject1;
    if (localObject1 == null) {
      paramBundle = getIntent().getStringExtra("intent_extra_data_key");
    }
    if (paramBundle != null) {}
    for (localObject1 = paramBundle.trim();; localObject1 = paramBundle)
    {
      localObject2 = getIntent().getData();
      if ((localObject2 == null) || (((Uri)localObject2).getQueryParameter("source_id") == null)) {
        break;
      }
      new Thread(new xk(this, (Uri)localObject2, (String)localObject1), "Search thread").start();
      return;
    }
    setContentView(2130968827);
    Object localObject2 = getContentResolver();
    paramBundle.trim();
    paramBundle = getListView();
    paramBundle.setItemsCanFocus(true);
    paramBundle.setFocusable(true);
    paramBundle.setClickable(true);
    setTitle("");
    gm.a(a);
    b = new xl(this, (ContentResolver)localObject2, (String)localObject1, paramBundle);
    paramBundle = Telephony.MmsSms.SEARCH_URI.buildUpon().appendQueryParameter("pattern", (String)localObject1).build();
    b.startQuery(0, null, paramBundle, null, null, null, null);
    getActionBar().setDisplayHomeAsUpEnabled(true);
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    switch (paramMenuItem.getItemId())
    {
    default: 
      return false;
    }
    finish();
    return true;
  }
  
  public void onStop()
  {
    super.onStop();
    gm.b(a);
  }
  
  public static class TextViewSnippet
    extends TextView
  {
    private static String a = "…";
    private static int b = 1;
    private String c;
    private String d;
    private Pattern e;
    
    public TextViewSnippet(Context paramContext)
    {
      super();
    }
    
    public TextViewSnippet(Context paramContext, AttributeSet paramAttributeSet)
    {
      super(paramAttributeSet);
    }
    
    public TextViewSnippet(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
    {
      super(paramAttributeSet, paramInt);
    }
    
    public void a(String paramString1, String paramString2)
    {
      e = Pattern.compile("\\b" + Pattern.quote(paramString2), 2);
      c = paramString1;
      d = paramString2;
      requestLayout();
    }
    
    protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    {
      Object localObject1 = c.toLowerCase();
      Object localObject2 = d.toLowerCase();
      int i = 0;
      int i2 = ((String)localObject2).length();
      int i3 = ((String)localObject1).length();
      localObject1 = e.matcher(c);
      if (((Matcher)localObject1).find(0)) {
        i = ((Matcher)localObject1).start();
      }
      TextPaint localTextPaint = getPaint();
      float f1 = localTextPaint.measureText(d);
      float f2 = getWidth() - localTextPaint.measureText(a) * 2.0F;
      if (f1 > f2) {
        localObject2 = c.substring(i, i + i2);
      }
      int j;
      int k;
      int i1;
      int n;
      String str;
      do
      {
        do
        {
          localObject1 = new SpannableString((CharSequence)localObject2);
          i = 0;
          localObject2 = e.matcher((CharSequence)localObject2);
          while (((Matcher)localObject2).find(i))
          {
            ((SpannableString)localObject1).setSpan(new StyleSpan(b), ((Matcher)localObject2).start(), ((Matcher)localObject2).end(), 0);
            i = ((Matcher)localObject2).end();
          }
          j = -1;
          localObject1 = null;
          k = -1;
          int m = -1;
          m += 1;
          i1 = Math.max(0, i - m);
          n = Math.min(i3, i + i2 + m);
          if (i1 != k) {
            break;
          }
          localObject2 = localObject1;
        } while (n == j);
        str = c.substring(i1, n);
        localObject2 = localObject1;
      } while (localTextPaint.measureText(str) > f2);
      if (i1 == 0)
      {
        localObject1 = "";
        label295:
        if (n != i3) {
          break label351;
        }
      }
      label351:
      for (localObject2 = "";; localObject2 = a)
      {
        localObject1 = String.format("%s%s%s", new Object[] { localObject1, str, localObject2 });
        k = i1;
        j = n;
        break;
        localObject1 = a;
        break label295;
      }
      setText((CharSequence)localObject1);
      super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.SearchActivity
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */