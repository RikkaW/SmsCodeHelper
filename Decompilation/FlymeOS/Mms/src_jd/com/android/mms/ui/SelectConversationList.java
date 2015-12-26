package com.android.mms.ui;

import aaa;
import android.app.FragmentManager;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.MzContactsContract.MzNetContacts;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.MultiChoiceView;
import android.telephony.MzPhoneNumberUtils;
import android.text.TextUtils;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.CursorAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.android.mms.MmsApp;
import com.android.mms.fragmentstyle.ConversationListFragment;
import fm;
import ge;
import gm;
import gq;
import gr;
import gr.b;
import iz;
import java.util.ArrayList;
import lx;
import xo;
import xp;
import xq;
import xr;
import xs;
import xt;

public class SelectConversationList
  extends fm
{
  public ArrayList<String> a;
  public c b;
  private ConversationListFragment c;
  private ListView d;
  private lx e;
  private iz f;
  private boolean g = true;
  private Bundle h;
  
  public static String a(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {}
    String str2;
    do
    {
      String str1;
      do
      {
        return paramString;
        str1 = MzPhoneNumberUtils.removeCountryCode(b(paramString));
        paramString = str1;
      } while (TextUtils.isEmpty(str1));
      str2 = MzPhoneNumberUtils.removeCNIpPrefix(str1);
      paramString = str1;
    } while (TextUtils.isEmpty(str2));
    return str2;
  }
  
  private static String b(String paramString)
  {
    return paramString.replace("-", "").replace(" ", "");
  }
  
  private void d()
  {
    c = ((ConversationListFragment)getFragmentManager().findFragmentById(2131886728));
    c.a(null);
    c.b(true);
    c.a(h);
    h = null;
  }
  
  private ListView e()
  {
    d = c.getListView();
    d.setChoiceMode(5);
    d.setMultiChoiceModeListener(c());
    d.setPadding(d.getPaddingLeft(), d.getPaddingTop(), d.getPaddingRight(), getResources().getDimensionPixelOffset(2131559402));
    e = g();
    e.a();
    ((ge)c.getListAdapter()).a(false);
    return d;
  }
  
  private void f()
  {
    ActionBar localActionBar = getSupportActionBar();
    localActionBar.setDisplayShowTabEnabled(false);
    localActionBar.setDisplayShowTitleEnabled(false);
    aaa.a(localActionBar, this);
  }
  
  private lx g()
  {
    if (e == null) {
      e = new xp(this, d);
    }
    return e;
  }
  
  protected void b()
  {
    setContentView(2130968830);
    d();
    f();
    e();
  }
  
  public iz c()
  {
    if (f == null) {
      f = new b(null);
    }
    return f;
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    h = paramBundle;
    b();
    if (MmsApp.n) {
      setTheme(2131624164);
    }
    for (;;)
    {
      if ((getIntent() != null) && (getIntent().hasExtra("com.android.contacts.extra.MULTIPLE_PICK_DATAS")) && (getIntent().getParcelableArrayExtra("com.android.contacts.extra.MULTIPLE_PICK_DATAS").length > 0))
      {
        b = new c(getContentResolver());
        c.a(new xo(this));
      }
      return;
      setTheme(2131624165);
    }
  }
  
  protected void onSaveInstanceState(Bundle paramBundle)
  {
    super.onSaveInstanceState(paramBundle);
    if (c != null) {
      c.onSaveInstanceState(paramBundle);
    }
  }
  
  class a
    extends AsyncTask<Parcelable[], Integer, ArrayList<Integer>>
  {
    Cursor a;
    
    public a(Cursor paramCursor)
    {
      a = paramCursor;
    }
    
    protected ArrayList<Integer> a(Parcelable[]... paramVarArgs)
    {
      Object localObject = paramVarArgs[0];
      a = new ArrayList();
      paramVarArgs = new ArrayList();
      int i = 0;
      while (i < localObject.length)
      {
        String str = ((ContentValues)localObject[i]).getAsString("data1");
        a.add(str);
        i += 1;
      }
      i = 0;
      for (;;)
      {
        try
        {
          if (i >= SelectConversationList.a(SelectConversationList.this).getCount()) {
            continue;
          }
          a.moveToPosition(i);
          localObject = gr.a(MmsApp.c(), a).h();
          if (((gq)localObject).size() != 1) {
            break label236;
          }
          localObject = (gm)((gq)localObject).get(0);
          j = 0;
        }
        catch (Exception localException)
        {
          int j;
          localException.printStackTrace();
          return paramVarArgs;
          j += 1;
          continue;
          return paramVarArgs;
        }
        finally
        {
          a.close();
        }
        if (j < a.size()) {
          if (SelectConversationList.a(((gm)localObject).d()).equals(a.get(j))) {
            paramVarArgs.add(Integer.valueOf(i));
          }
        }
        label236:
        i += 1;
      }
    }
    
    protected void a(ArrayList<Integer> paramArrayList)
    {
      super.onPostExecute(paramArrayList);
      SelectConversationList.c(SelectConversationList.this).a();
      if (paramArrayList == null) {
        return;
      }
      SelectConversationList.a(SelectConversationList.this, false);
      int i = 0;
      while (i < paramArrayList.size())
      {
        SelectConversationList.a(SelectConversationList.this).setItemChecked(((Integer)paramArrayList.get(i)).intValue(), true);
        i += 1;
      }
      SelectConversationList.a(SelectConversationList.this, true);
      ((SelectConversationList.b)c()).c();
      SelectConversationList.c(SelectConversationList.this).a(null);
    }
    
    protected void onPreExecute()
    {
      SelectConversationList.c(SelectConversationList.this).a(2131493788);
    }
  }
  
  public class b
    extends iz
  {
    MenuItem d;
    private MultiChoiceView f;
    
    private b() {}
    
    private void d()
    {
      aaa.a(SelectConversationList.this, f, SelectConversationList.a(SelectConversationList.this).getCheckedItemCount(), SelectConversationList.a(SelectConversationList.this).getAdapter().getCount());
    }
    
    public void c()
    {
      MenuItem localMenuItem;
      if ((SelectConversationList.d(SelectConversationList.this)) && (d != null))
      {
        localMenuItem = d;
        if (SelectConversationList.a(SelectConversationList.this).getCheckedItemCount() <= 0) {
          break label46;
        }
      }
      label46:
      for (boolean bool = true;; bool = false)
      {
        localMenuItem.setEnabled(bool);
        return;
      }
    }
    
    public boolean onActionItemClicked(ActionMode paramActionMode, MenuItem paramMenuItem)
    {
      switch (paramMenuItem.getItemId())
      {
      }
      for (;;)
      {
        return false;
        paramMenuItem = new Intent();
        SparseBooleanArray localSparseBooleanArray = SelectConversationList.a(SelectConversationList.this).getCheckedItemPositions();
        ArrayList localArrayList = new ArrayList();
        Cursor localCursor = ((CursorAdapter)SelectConversationList.c(SelectConversationList.this).getListAdapter()).getCursor();
        int k = localCursor.getPosition();
        int i = 0;
        while (i < localCursor.getCount())
        {
          gq localgq;
          if (localSparseBooleanArray.get(i))
          {
            localCursor.moveToPosition(i);
            localgq = gr.a(MmsApp.c(), localCursor).h();
            if (localgq != null) {}
          }
          else
          {
            i += 1;
            continue;
          }
          int j = 0;
          label141:
          ContentValues localContentValues;
          if (j < localgq.size())
          {
            paramActionMode = (gm)localgq.get(j);
            String str = paramActionMode.d();
            localContentValues = new ContentValues();
            localContentValues.put("data1", str);
            long l = paramActionMode.n();
            boolean bool = MzContactsContract.MzNetContacts.isYPContact(l);
            if ((l <= 0L) && (!bool)) {
              break label241;
            }
          }
          label241:
          for (paramActionMode = paramActionMode.g();; paramActionMode = "")
          {
            localContentValues.put("display_name", paramActionMode);
            localArrayList.add(localContentValues);
            j += 1;
            break label141;
            break;
          }
        }
        localCursor.moveToPosition(k);
        paramActionMode = new ContentValues[localArrayList.size()];
        i = 0;
        while (i < localArrayList.size())
        {
          paramActionMode[i] = ((ContentValues)localArrayList.get(i));
          i += 1;
        }
        paramMenuItem.putExtra("com.android.contacts.extra.MULTIPLE_PICK_DATAS", paramActionMode);
        setResult(-1, paramMenuItem);
        finish();
      }
    }
    
    public boolean onCreateActionMode(ActionMode paramActionMode, Menu paramMenu)
    {
      getMenuInflater().inflate(2131951627, paramMenu);
      d = paramMenu.findItem(2131886118);
      d.setEnabled(false);
      a();
      c();
      f = new MultiChoiceView(SelectConversationList.this);
      f.setOnCloseItemClickListener(new xq(this, paramActionMode));
      f.setOnSelectAllItemClickListener(new xr(this));
      f.setOnCloseItemClickListener(new xs(this));
      paramActionMode.setCustomView(f);
      d();
      SelectConversationList.a(SelectConversationList.this).getAdapter().registerDataSetObserver(new xt(this));
      return true;
    }
    
    public void onDestroyActionMode(ActionMode paramActionMode)
    {
      onBackPressed();
    }
    
    public void onItemCheckedStateChanged(ActionMode paramActionMode, int paramInt, long paramLong, boolean paramBoolean)
    {
      d();
      c();
    }
    
    public boolean onPrepareActionMode(ActionMode paramActionMode, Menu paramMenu)
    {
      if (SelectConversationList.a(SelectConversationList.this).getAdapter().getCount() > 0) {
        a(SelectConversationList.a(SelectConversationList.this).getCheckedItemCount(), SelectConversationList.a(SelectConversationList.this).getAdapter().getCount());
      }
      return true;
    }
  }
  
  public final class c
    extends gr.b
  {
    public c(ContentResolver paramContentResolver)
    {
      super();
    }
    
    protected void onQueryComplete(int paramInt, Object paramObject, Cursor paramCursor)
    {
      new SelectConversationList.a(SelectConversationList.this, paramCursor).execute(new Parcelable[][] { getIntent().getParcelableArrayExtra("com.android.contacts.extra.MULTIPLE_PICK_DATAS") });
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.SelectConversationList
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */