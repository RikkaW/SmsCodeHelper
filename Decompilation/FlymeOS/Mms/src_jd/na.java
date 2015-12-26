import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.MatrixCursor.RowBuilder;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.Contacts;
import android.provider.ContactsContract.Directory;
import android.provider.ContactsContract.PhoneLookup;
import android.provider.MzContactsContract.MzCommonDataKinds.MzPhoneAndEmail;
import android.provider.MzContactsContract.MzCommonDataKinds.MzSocialProfile;
import android.provider.MzContactsContract.MzData;
import android.provider.MzContactsContract.MzGroups;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class na
  extends aii
{
  protected final ContentResolver a;
  private int c;
  private LinkedHashMap<String, aig> d;
  private HashSet<String> e;
  private HashMap<Long, aii.b> f;
  private aii.b g;
  private int h;
  private boolean i;
  private boolean j;
  private boolean k;
  private Context l;
  private boolean m = true;
  private final Object n = new Object();
  private ArrayList<na.b> o;
  private String p;
  
  public na(Context paramContext, int paramInt)
  {
    super(paramContext, paramInt);
    l = paramContext;
    c = paramInt;
    a = paramContext.getContentResolver();
    d = new LinkedHashMap();
    e = new HashSet();
    f = new HashMap();
    o = new ArrayList();
  }
  
  private aii.b a(long paramLong)
  {
    int i2 = f();
    int i1 = 0;
    while (i1 < i2)
    {
      aif.a locala = c(i1);
      if (((locala instanceof aii.b)) && (f == paramLong)) {
        return (aii.b)locala;
      }
      i1 += 1;
    }
    return null;
  }
  
  private Cursor a(String paramString, boolean paramBoolean, long paramLong)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    Object localObject = nc.a(paramString);
    localStringBuilder.append("sort_key");
    switch (c)
    {
    default: 
      if (paramBoolean)
      {
        paramString = ContactsContract.CommonDataKinds.Phone.CONTENT_FILTER_URI;
        if ((c & 0x2) == 2) {
          paramString = MzContactsContract.MzCommonDataKinds.MzPhoneAndEmail.CONTENT_FILTER_URI;
        }
        paramString = paramString.buildUpon().appendPath((String)localObject).appendQueryParameter("convert_letters", String.valueOf(false)).build();
      }
      break;
    }
    for (;;)
    {
      localObject = paramString;
      if (paramBoolean) {
        localObject = paramString.buildUpon().appendQueryParameter("directory", String.valueOf(paramLong)).appendQueryParameter("limit", String.valueOf(20)).build();
      }
      paramString = a.query((Uri)localObject, b, null, null, localStringBuilder.toString());
      if ((paramBoolean) || (paramString == null)) {
        break;
      }
      paramString.moveToPosition(-1);
      while (paramString.moveToNext())
      {
        localObject = new aig();
        ((aig)localObject).a(paramString.getString(0), paramString.getString(2), Long.valueOf(paramString.getLong(3)));
        d.put(paramString.getString(1), localObject);
      }
      localStringBuilder.delete(0, localStringBuilder.length());
      localStringBuilder.append("times_contacted DESC ,sort_key ,is_primary DESC");
      paramString = ContactsContract.CommonDataKinds.Phone.CONTENT_FILTER_URI.buildUpon().appendPath((String)localObject).appendQueryParameter("convert_letters", String.valueOf(false)).build();
      continue;
      paramString = new String[2];
      if ((c & 0x1) == 1) {
        paramString[0] = "vnd.android.cursor.item/phone_v2";
      }
      if ((c & 0x2) == 2) {
        paramString[1] = "vnd.android.cursor.item/email_v2";
      }
      localStringBuilder.delete(0, localStringBuilder.length());
      localStringBuilder.append("times_contacted DESC ,sort_key ,is_primary DESC");
      paramString = MzContactsContract.MzData.buildUriWithRequestMimetypes(MzContactsContract.MzData.CONTENT_FILTER_URI.buildUpon().appendPath((String)localObject).appendQueryParameter("convert_letters", String.valueOf(false)).build(), paramString);
    }
    return paramString;
  }
  
  private boolean a(String paramString, TextView paramTextView)
  {
    if ((!TextUtils.isEmpty(paramString)) && (!TextUtils.isEmpty(p)))
    {
      Object localObject = Pattern.compile(Pattern.quote(p), 2);
      SpannableString localSpannableString = new SpannableString(paramString);
      localObject = ((Pattern)localObject).matcher(paramString);
      if (((Matcher)localObject).find(0))
      {
        localSpannableString.setSpan(new ForegroundColorSpan(ga.a), ((Matcher)localObject).start(), ((Matcher)localObject).end(), 0);
        paramTextView.setText(localSpannableString);
        return true;
      }
      paramTextView.setText(paramString);
      return false;
    }
    paramTextView.setText(paramString);
    return false;
  }
  
  private boolean b(CharSequence paramCharSequence)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.putAll(f);
    f.clear();
    e.clear();
    g = null;
    h = -1;
    Object localObject1 = ((ConnectivityManager)l.getSystemService("connectivity")).getActiveNetworkInfo();
    if ((localObject1 == null) || (!((NetworkInfo)localObject1).isConnected())) {
      return false;
    }
    synchronized (n)
    {
      Iterator localIterator = o.iterator();
      if (localIterator.hasNext())
      {
        na.b localb = (na.b)localIterator.next();
        long l1 = a;
        Object localObject2 = a(l1);
        localObject1 = localObject2;
        if (localObject2 == null) {
          localObject1 = (aii.b)localHashMap.get(Long.valueOf(l1));
        }
        localObject2 = localObject1;
        if (localObject1 == null) {
          localObject2 = new aii.b(this);
        }
        f = l1;
        k = paramCharSequence;
        h = c;
        i = d;
        j = e;
        g = b;
        f.put(Long.valueOf(l1), localObject2);
      }
    }
    return f.size() > 0;
  }
  
  private Cursor e(String paramString)
  {
    paramString = Uri.withAppendedPath(MzContactsContract.MzGroups.CONTENT_SUMMARY_FILTER_URI, Uri.encode(paramString));
    int i1 = c;
    paramString = paramString.buildUpon().appendQueryParameter("is_primary", String.valueOf(true)).appendQueryParameter("mimetype", "vnd.android.cursor.item/phone_v2").build();
    return a.query(paramString, new String[] { "title", "_id" }, "_id IN (SELECT DISTINCT data1 FROM view_data WHERE mimetype= 'vnd.android.cursor.item/group_membership' AND  phone_number NOT NULL)", null, null);
  }
  
  private void i()
  {
    if (f.size() > 0)
    {
      a(false);
      j();
      a(true);
      Iterator localIterator = f.values().iterator();
      while (localIterator.hasNext())
      {
        aii.b localb = (aii.b)localIterator.next();
        if (l == null) {
          l = new aii.c(this, -1, f);
        }
        l.filter(k);
      }
    }
  }
  
  private void j()
  {
    if ((f() > 0) && ((c(f() - 1) instanceof aii.d))) {
      return;
    }
    a(new aii.d(this));
    a(f() - 1, null);
  }
  
  private void k()
  {
    if ((f() > 0) && ((c(f() - 1) instanceof aii.d))) {
      b(f() - 1);
    }
  }
  
  protected View a(Context paramContext, int paramInt1, Cursor paramCursor, int paramInt2, ViewGroup paramViewGroup)
  {
    return LayoutInflater.from(paramContext).inflate(2130968774, paramViewGroup, false);
  }
  
  protected View a(Context paramContext, int paramInt, Cursor paramCursor, ViewGroup paramViewGroup)
  {
    paramContext = LayoutInflater.from(paramContext);
    if ((c(paramInt) instanceof aii.b)) {
      return paramContext.inflate(2130968756, paramViewGroup, false);
    }
    paramContext = paramContext.inflate(2130968757, paramViewGroup, false);
    paramContext.setOnClickListener(new nb(this));
    return paramContext;
  }
  
  protected Object a(CharSequence paramCharSequence)
  {
    d.clear();
    Cursor localCursor;
    if (paramCharSequence == null)
    {
      paramCharSequence = "";
      if (TextUtils.isEmpty(paramCharSequence)) {
        break label68;
      }
      localCursor = e(paramCharSequence);
      paramCharSequence = a(paramCharSequence, false, -1L);
    }
    for (;;)
    {
      return new Cursor[] { localCursor, paramCharSequence, null };
      paramCharSequence = paramCharSequence.toString().trim();
      break;
      label68:
      paramCharSequence = null;
      localCursor = null;
    }
  }
  
  protected Object a(CharSequence paramCharSequence, long paramLong)
  {
    if (paramCharSequence == null) {}
    for (paramCharSequence = ""; !TextUtils.isEmpty(paramCharSequence); paramCharSequence = paramCharSequence.toString().trim()) {
      return a(paramCharSequence, true, paramLong);
    }
    return null;
  }
  
  public String a(String paramString)
  {
    if ((c & 0x4) == 4) {
      localObject1 = Uri.withAppendedPath(MzContactsContract.MzCommonDataKinds.MzSocialProfile.CONTENT_LOOKUP_URI, Uri.encode(paramString));
    }
    for (Object localObject1 = a.query((Uri)localObject1, new String[] { "display_name", "data1" }, null, null, null);; localObject1 = null)
    {
      Object localObject2;
      if (localObject1 != null)
      {
        localObject2 = localObject1;
        if (((Cursor)localObject1).getCount() != 0) {}
      }
      else
      {
        localObject2 = localObject1;
        if ((c & 0x1) == 1)
        {
          if (localObject1 != null) {
            ((Cursor)localObject1).close();
          }
          localObject1 = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(paramString));
          localObject2 = a.query((Uri)localObject1, new String[] { "display_name", "number" }, null, null, null);
        }
      }
      if (((localObject2 == null) || (((Cursor)localObject2).getCount() == 0)) && ((c & 0x2) == 2))
      {
        if (localObject2 != null) {
          ((Cursor)localObject2).close();
        }
        localObject1 = Uri.withAppendedPath(ContactsContract.CommonDataKinds.Email.CONTENT_LOOKUP_URI, Uri.encode(paramString));
        localObject2 = a.query((Uri)localObject1, new String[] { "display_name", "data1" }, null, null, null);
      }
      for (;;)
      {
        if ((localObject2 != null) && (((Cursor)localObject2).moveToFirst()))
        {
          String str = ((Cursor)localObject2).getString(0);
          localObject1 = str;
          if (((Cursor)localObject2).getCount() > 1)
          {
            localObject1 = str;
            if (!paramString.equals(((Cursor)localObject2).getString(1))) {
              do
              {
                localObject1 = str;
                if (!((Cursor)localObject2).moveToNext()) {
                  break;
                }
              } while (!paramString.equals(((Cursor)localObject2).getString(1)));
            }
          }
        }
        for (localObject1 = ((Cursor)localObject2).getString(0);; localObject1 = "")
        {
          if (localObject2 != null) {
            ((Cursor)localObject2).close();
          }
          return (String)localObject1;
        }
      }
    }
  }
  
  public void a()
  {
    for (;;)
    {
      PackageManager localPackageManager;
      na.b localb;
      String str;
      int i1;
      synchronized (n)
      {
        b();
        Cursor localCursor = a.query(na.a.a, na.a.b, "_id!=1 AND _id!=0", null, null);
        if ((localCursor == null) || (localCursor.getCount() <= 0)) {
          break label298;
        }
        localPackageManager = c().getPackageManager();
        localCursor.moveToPosition(-1);
        if (!localCursor.moveToNext()) {
          break label298;
        }
        if ((localCursor.getInt(6) != 1) || (!nc.f(localCursor.getString(2)))) {
          continue;
        }
        localb = new na.b();
        a = localCursor.getLong(0);
        c = localCursor.getString(3);
        d = localCursor.getString(1);
        e = localCursor.getString(2);
        str = localCursor.getString(4);
        i1 = localCursor.getInt(5);
        if ((str == null) || (i1 == 0)) {}
      }
      try
      {
        b = localPackageManager.getResourcesForApplication(str).getString(i1);
        if (b == null) {
          Log.e("RecipientBaseAdapter", "Cannot resolve directory name: " + i1 + "@" + str);
        }
        a(localb);
        continue;
        localObject2 = finally;
        throw ((Throwable)localObject2);
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        for (;;)
        {
          Log.e("RecipientBaseAdapter", "Cannot resolve directory name: " + i1 + "@" + str, localNameNotFoundException);
        }
      }
    }
    label298:
    if (localObject2 != null) {
      ((Cursor)localObject2).close();
    }
  }
  
  public void a(int paramInt)
  {
    c = paramInt;
  }
  
  protected void a(View paramView, int paramInt, Cursor paramCursor)
  {
    if (!(c(paramInt) instanceof aii.b))
    {
      paramCursor = (TextView)paramView.findViewById(16908308);
      if (k)
      {
        paramView.setEnabled(true);
        paramCursor.setText(2131493741);
      }
    }
    else
    {
      return;
    }
    paramView.setEnabled(false);
    paramCursor.setText(2131493742);
  }
  
  protected void a(View paramView, int paramInt1, Cursor paramCursor, int paramInt2)
  {
    TextView localTextView = (TextView)paramView.findViewById(16908308);
    localTextView.setHorizontallyScrolling(false);
    Object localObject3 = (ImageView)paramView.findViewById(16908294);
    ((ImageView)localObject3).setVisibility(8);
    Object localObject1 = "";
    Object localObject2 = "";
    if (1 == paramCursor.getColumnCount())
    {
      localObject2 = aii.f.a(paramCursor.getBlob(0));
      if (1 == ((ArrayList)localObject2).size())
      {
        paramCursor = get0b;
        localObject1 = get0a;
        paramInt2 = 0;
      }
    }
    for (;;)
    {
      localObject3 = "";
      localObject2 = paramCursor;
      paramCursor = (Cursor)localObject1;
      localObject1 = localObject3;
      for (;;)
      {
        boolean bool = a((String)localObject2, localTextView);
        localObject3 = (TextView)paramView.findViewById(16908309);
        ((TextView)localObject3).setHorizontallyScrolling(false);
        ((TextView)localObject3).setVisibility(0);
        paramView = c(paramInt1);
        if ((paramInt1 == 0) && ((paramView instanceof aii.e)))
        {
          ((TextView)localObject3).setText("");
          ((TextView)localObject3).setVisibility(8);
          return;
          ((ImageView)localObject3).setImageResource(2130837961);
          ((ImageView)localObject3).setVisibility(0);
          localObject2 = ((ArrayList)localObject2).iterator();
          for (paramCursor = (Cursor)localObject1; ((Iterator)localObject2).hasNext(); paramCursor = paramCursor + b + " ") {
            localObject1 = (aii.f)((Iterator)localObject2).next();
          }
          if (2 == paramCursor.getColumnCount())
          {
            localObject2 = paramCursor.getString(0);
            paramCursor = paramCursor.getString(1);
            localObject1 = "";
            paramInt2 = 0;
            continue;
          }
          if (paramCursor.getColumnCount() > 2)
          {
            localObject2 = paramCursor.getString(0);
            localObject1 = paramCursor.getString(1);
            localObject3 = paramCursor.getString(2);
            paramCursor = (Cursor)localObject1;
            paramInt2 = 0;
            localObject1 = localObject3;
          }
        }
        else
        {
          paramView = paramCursor;
          if (!TextUtils.isEmpty((CharSequence)localObject1)) {
            paramView = paramCursor + " " + (String)localObject1;
          }
          if (TextUtils.isEmpty((CharSequence)localObject2))
          {
            a(paramView, localTextView);
            ((TextView)localObject3).setText("");
            ((TextView)localObject3).setVisibility(8);
            return;
          }
          if (paramInt2 == 0)
          {
            if (!bool)
            {
              a(paramView, (TextView)localObject3);
              return;
            }
            ((TextView)localObject3).setText(paramView);
            return;
          }
          ((TextView)localObject3).setText("");
          ((TextView)localObject3).setVisibility(8);
          return;
        }
        localObject1 = "";
        localObject3 = "";
        paramInt2 = 0;
        paramCursor = (Cursor)localObject2;
        localObject2 = localObject3;
      }
      paramInt2 = 1;
      localObject1 = "";
    }
  }
  
  protected void a(CharSequence paramCharSequence, long paramLong, Cursor paramCursor)
  {
    aii.b localb = (aii.b)f.get(Long.valueOf(paramLong));
    if ((localb != null) && (TextUtils.equals(paramCharSequence, k)))
    {
      f.remove(Long.valueOf(paramLong));
      paramCharSequence = new MatrixCursor(b);
      if ((paramCursor != null) && (paramCursor.getCount() > 0))
      {
        paramCursor.moveToPosition(-1);
        while (paramCursor.moveToNext()) {
          if (paramCursor.getColumnCount() > 1)
          {
            String str1 = paramCursor.getString(0);
            String str2 = paramCursor.getString(1);
            String str3 = paramCursor.getString(2);
            if ((!d.containsKey(str2)) && (!e.contains(str2)))
            {
              e.add(str2);
              MatrixCursor.RowBuilder localRowBuilder = paramCharSequence.newRow();
              localRowBuilder.add(str1);
              localRowBuilder.add(str2);
              localRowBuilder.add(str3);
              localRowBuilder.add(Long.valueOf(0L));
            }
          }
        }
      }
      if (paramCharSequence.getCount() <= 0) {
        break label341;
      }
      a(false);
      k();
      if (j)
      {
        j = false;
        d();
        if (g != null)
        {
          a(g);
          h = 0;
        }
      }
      if (localb != g) {
        break label310;
      }
      a(h, paramCharSequence);
      if (!f.isEmpty()) {
        j();
      }
      a(true);
    }
    for (;;)
    {
      if (paramCursor != null) {
        paramCursor.close();
      }
      return;
      label310:
      a(localb);
      a(f() - 1, true);
      a(f() - 1, paramCharSequence);
      break;
      label341:
      if (f.isEmpty()) {
        if (j)
        {
          j = false;
          d();
        }
        else
        {
          k();
        }
      }
    }
  }
  
  protected void a(CharSequence paramCharSequence, Cursor paramCursor1, Cursor paramCursor2, Cursor paramCursor3)
  {
    a(false);
    if (paramCharSequence == null)
    {
      paramCursor3 = "";
      p = paramCursor3;
      if (((paramCursor1 == null) || (paramCursor1.getCount() <= 0)) && ((paramCursor2 == null) || (paramCursor2.getCount() <= 0))) {
        break label232;
      }
      i = false;
      d();
      a(new aii.e(this));
      a(0, paramCursor1);
      a(false, false);
      a(1, paramCursor2);
    }
    label137:
    label232:
    for (int i1 = 1;; i1 = 0)
    {
      if ((m) && (!TextUtils.isEmpty(paramCursor3)) && (b(paramCharSequence)))
      {
        j = false;
        if ((i1 == 0) && (!i))
        {
          i = true;
          d();
          if ((!j) && (g != null))
          {
            a(g);
            h = (f() - 1);
          }
          k = true;
          j();
        }
      }
      for (;;)
      {
        a(true);
        return;
        paramCursor3 = paramCharSequence.toString().trim();
        break;
        if (!i) {
          break label137;
        }
        j = true;
        break label137;
        f.clear();
        if (i1 == 0) {
          d();
        }
      }
    }
  }
  
  public void a(String paramString1, String paramString2)
  {
    String str = null;
    if ((c & 0x4) == 4) {
      localObject1 = Uri.withAppendedPath(MzContactsContract.MzCommonDataKinds.MzSocialProfile.CONTENT_LOOKUP_URI, Uri.encode(paramString1));
    }
    for (Object localObject1 = a.query((Uri)localObject1, new String[] { "contact_id", "lookup", "data1" }, null, null, null);; localObject1 = null)
    {
      Object localObject2;
      if (localObject1 != null)
      {
        localObject2 = localObject1;
        if (((Cursor)localObject1).getCount() != 0) {}
      }
      else
      {
        localObject2 = localObject1;
        if ((c & 0x1) == 1)
        {
          if (localObject1 != null) {
            ((Cursor)localObject1).close();
          }
          localObject1 = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(paramString1));
          localObject2 = a.query((Uri)localObject1, new String[] { "_id", "lookup", "number" }, null, null, null);
        }
      }
      if (((localObject2 == null) || (((Cursor)localObject2).getCount() == 0)) && ((c & 0x2) == 2))
      {
        if (localObject2 != null) {
          ((Cursor)localObject2).close();
        }
        localObject1 = Uri.withAppendedPath(ContactsContract.CommonDataKinds.Email.CONTENT_LOOKUP_URI, Uri.encode(paramString1));
      }
      for (localObject1 = a.query((Uri)localObject1, new String[] { "contact_id", "lookup", "data1" }, null, null, null);; localObject1 = localObject2)
      {
        localObject2 = str;
        if (localObject1 != null)
        {
          localObject2 = str;
          if (((Cursor)localObject1).moveToFirst())
          {
            long l2 = ((Cursor)localObject1).getLong(0);
            str = ((Cursor)localObject1).getString(1);
            localObject2 = str;
            long l1 = l2;
            if (((Cursor)localObject1).getCount() > 1)
            {
              localObject2 = str;
              l1 = l2;
              if (!paramString1.equals(((Cursor)localObject1).getString(2)))
              {
                do
                {
                  localObject2 = str;
                  l1 = l2;
                  if (!((Cursor)localObject1).moveToNext()) {
                    break;
                  }
                } while (!paramString1.equals(((Cursor)localObject1).getString(2)));
                l1 = ((Cursor)localObject1).getLong(0);
                localObject2 = ((Cursor)localObject1).getString(1);
              }
            }
            localObject2 = ContactsContract.Contacts.getLookupUri(l1, (String)localObject2);
          }
        }
        if (localObject1 != null) {
          ((Cursor)localObject1).close();
        }
        if (localObject2 != null)
        {
          localObject1 = new Intent("android.intent.action.VIEW", (Uri)localObject2);
          localObject2 = new Bundle();
          if (((c & 0x1) != 1) || (!c(paramString1))) {
            break label564;
          }
          ((Bundle)localObject2).putString("phone", paramString1);
          ((Bundle)localObject2).putString("name", paramString2);
          ((Bundle)localObject2).putString("android.intent.action.INSERT", "vnd.android.cursor.item/phone_v2");
        }
        for (;;)
        {
          ((Bundle)localObject2).putString("com.android.contacts.extra.SUB_TITLE_EXTRA", l.getString(2131493753));
          ((Bundle)localObject2).putString("com.android.contacts.extra.TITLE_EXTRA", l.getString(2131493755));
          ((Intent)localObject1).putExtras((Bundle)localObject2);
          if (!(l instanceof Activity)) {
            ((Intent)localObject1).addFlags(1342177280);
          }
          l.startActivity((Intent)localObject1);
          return;
          localObject1 = new Intent("android.intent.action.VIEW");
          ((Intent)localObject1).setType("vnd.android.cursor.dir/data");
          break;
          label564:
          if (((c & 0x4) == 4) && (b(paramString1)))
          {
            ((Bundle)localObject2).putString("social_profile", paramString1);
            ((Bundle)localObject2).putString("android.intent.action.INSERT", "vnd.android.cursor.item/social_profile");
          }
          else
          {
            ((Bundle)localObject2).putString("email", paramString1);
            ((Bundle)localObject2).putString("name", paramString2);
            ((Bundle)localObject2).putString("android.intent.action.INSERT", "vnd.android.cursor.item/email_v2");
          }
        }
      }
    }
  }
  
  public void a(na.b paramb)
  {
    o.add(paramb);
  }
  
  public void b()
  {
    if (o != null) {
      o.clear();
    }
  }
  
  public boolean b(String paramString)
  {
    return nc.b(paramString);
  }
  
  public boolean c(String paramString)
  {
    return nc.c(paramString);
  }
  
  public boolean d(String paramString)
  {
    return nc.d(paramString);
  }
  
  static class a
  {
    public static final Uri a = ContactsContract.Directory.CONTENT_URI;
    public static final String[] b = { "_id", "accountName", "accountType", "displayName", "packageName", "typeResourceId", "is_visible" };
  }
  
  static class b
  {
    public long a;
    public String b;
    public String c;
    public String d;
    public String e;
  }
}

/* Location:
 * Qualified Name:     na
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */