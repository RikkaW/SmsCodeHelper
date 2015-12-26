package com.android.mms.ui;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.net.Uri.Builder;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.text.Annotation;
import android.text.SpannableString;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;
import com.android.mms.data.Contact;
import miui.telephony.PhoneNumberUtils;
import miui.telephony.PhoneNumberUtils.TelocationQueryListener;

public class RecipientsAdapter
  extends ResourceCursorAdapter
  implements PhoneNumberUtils.TelocationQueryListener
{
  private static final String[] PROJECTION_PHONE = { "_id", "contact_id", "data2", "data1", "data3", "display_name", "data4", "photo_id" };
  private final ContentResolver mContentResolver;
  private final Context mContext;
  private boolean mIsStoped = false;
  
  public RecipientsAdapter(Context paramContext)
  {
    super(paramContext, 2130968694, null, false);
    mContext = paramContext;
    mContentResolver = paramContext.getContentResolver();
  }
  
  public final void bindView(View paramView, Context paramContext, Cursor paramCursor)
  {
    TextView localTextView1 = (TextView)paramView.findViewById(2131820846);
    localTextView1.setText(paramCursor.getString(5));
    localTextView1.setVisibility(0);
    localTextView1 = (TextView)paramView.findViewById(2131820854);
    String str1 = paramCursor.getString(3);
    localTextView1.setText(str1);
    TextView localTextView2 = (TextView)paramView.findViewById(2131820855);
    int i = paramCursor.getInt(2);
    String str2 = paramCursor.getString(4);
    localTextView2.setText(ContactsContract.CommonDataKinds.Phone.getTypeLabel(paramContext.getResources(), i, str2).toString());
    PhoneNumberUtils.queryTelocationStringAsync(101, localTextView1, str1, (TextView)paramView.findViewById(2131820856), null, this, mContext, str1);
    paramView = paramView.findViewById(2131820797);
    paramView.setBackgroundResource(2130837917);
    paramContext = paramCursor.getString(1);
    if (!paramCursor.isLast())
    {
      paramCursor.moveToNext();
      if (paramContext.equals(paramCursor.getString(1))) {
        paramView.setBackgroundResource(2130837915);
      }
      paramCursor.moveToPrevious();
    }
  }
  
  public void changeCursor(Cursor paramCursor)
  {
    if (mIsStoped)
    {
      if (paramCursor != null) {
        paramCursor.close();
      }
      Log.v("RecipientsAdapter", "change cursor close for stop");
      return;
    }
    super.changeCursor(paramCursor);
  }
  
  public final CharSequence convertToString(Cursor paramCursor)
  {
    String str1 = paramCursor.getString(3);
    if (str1 == null) {
      return "";
    }
    String str2 = str1.trim();
    str1 = paramCursor.getString(5);
    int i = paramCursor.getInt(2);
    Object localObject = paramCursor.getString(4);
    localObject = ContactsContract.CommonDataKinds.Phone.getDisplayLabel(mContext, i, (CharSequence)localObject);
    SpannableString localSpannableString;
    if (str1 == null)
    {
      str1 = "";
      localSpannableString = new SpannableString(Contact.formatNameAndNumber(str1, str2, paramCursor.getString(6)));
      i = localSpannableString.length();
      if (TextUtils.isEmpty(str1)) {
        break label210;
      }
      localSpannableString.setSpan(new Annotation("name", str1), 0, i, 33);
    }
    for (;;)
    {
      localSpannableString.setSpan(new Annotation("person_id", paramCursor.getString(1)), 0, i, 33);
      localSpannableString.setSpan(new Annotation("label", localObject.toString()), 0, i, 33);
      localSpannableString.setSpan(new Annotation("number", str2), 0, i, 33);
      return localSpannableString;
      str1 = str1.replace(", ", " ").replace(",", " ");
      break;
      label210:
      localSpannableString.setSpan(new Annotation("name", str2), 0, i, 33);
    }
  }
  
  public void onComplete(Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4, String paramString)
  {
    if (TextUtils.equals(((TextView)paramObject1).getText(), (String)paramObject2))
    {
      paramObject1 = (TextView)paramObject3;
      if (!TextUtils.isEmpty(paramString)) {
        ((TextView)paramObject1).setText(paramString);
      }
    }
    else
    {
      return;
    }
    ((TextView)paramObject1).setText("");
  }
  
  public Cursor runQueryOnBackgroundThread(CharSequence paramCharSequence)
  {
    String str = null;
    if (paramCharSequence != null) {
      str = paramCharSequence.toString();
    }
    paramCharSequence = ContactsContract.CommonDataKinds.Phone.CONTENT_FILTER_URI.buildUpon().appendPath(str).appendQueryParameter("type", "short_text").build();
    return mContentResolver.query(paramCharSequence, PROJECTION_PHONE, null, null, null);
  }
  
  public void start()
  {
    mIsStoped = false;
  }
  
  public void stop()
  {
    mIsStoped = true;
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.RecipientsAdapter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */