package com.android.mms;

import android.database.CharArrayBuffer;
import android.database.Cursor;
import android.database.CursorWrapper;
import android.net.Uri;
import android.text.TextUtils;
import com.android.mms.data.Conversation;
import com.android.mms.ui.ComposeMessageRouterActivity;
import com.android.mms.ui.MessageUtils;
import com.google.android.mms.pdu.EncodedStringValue;
import com.google.android.mms.pdu.MiuiPduPersister;

class SuggestionsProvider$SearchSuggestionCursorTranslator
  extends CursorWrapper
{
  private final int mBodyPos;
  private final int mDatePos;
  private final String mFirstQuery;
  private final int mRowIdPos;
  private final int mSubjectPos;
  private final int mThreadIdPos;
  
  public SuggestionsProvider$SearchSuggestionCursorTranslator(SuggestionsProvider paramSuggestionsProvider, Cursor paramCursor, String paramString)
  {
    super(paramCursor);
    mThreadIdPos = paramCursor.getColumnIndex("thread_id");
    mBodyPos = paramCursor.getColumnIndex("body");
    mSubjectPos = paramCursor.getColumnIndex("sub");
    mRowIdPos = paramCursor.getColumnIndex("_id");
    mDatePos = paramCursor.getColumnIndex("date");
    mFirstQuery = paramString.split(" +")[0];
  }
  
  public void copyStringToBuffer(int paramInt, CharArrayBuffer paramCharArrayBuffer)
  {
    throw new IllegalArgumentException("Wrong data type for column " + paramInt);
  }
  
  public byte[] getBlob(int paramInt)
  {
    throw new IllegalArgumentException("Wrong data type for column " + paramInt);
  }
  
  public int getColumnCount()
  {
    return SuggestionsProvider.access$000().length;
  }
  
  public int getColumnIndex(String paramString)
  {
    int i = 0;
    while (i < SuggestionsProvider.access$000().length)
    {
      if (SuggestionsProvider.access$000()[i].equals(paramString)) {
        return i;
      }
      i += 1;
    }
    return -1;
  }
  
  public int getColumnIndexOrThrow(String paramString)
    throws IllegalArgumentException
  {
    int i = getColumnIndex(paramString);
    if (i == -1) {
      throw new IllegalArgumentException("Cannot find column " + paramString);
    }
    return i;
  }
  
  public String getColumnName(int paramInt)
  {
    return SuggestionsProvider.access$000()[paramInt];
  }
  
  public String[] getColumnNames()
  {
    return (String[])SuggestionsProvider.access$000().clone();
  }
  
  public int getCount()
  {
    return mCursor.getCount();
  }
  
  public double getDouble(int paramInt)
  {
    throw new IllegalArgumentException("Wrong data type for column " + paramInt);
  }
  
  public float getFloat(int paramInt)
  {
    throw new IllegalArgumentException("Wrong data type for column " + paramInt);
  }
  
  public int getInt(int paramInt)
  {
    throw new IllegalArgumentException("Wrong data type for column " + paramInt);
  }
  
  public long getLong(int paramInt)
  {
    throw new IllegalArgumentException("Wrong data type for column " + paramInt);
  }
  
  public short getShort(int paramInt)
  {
    throw new IllegalArgumentException("Wrong data type for column " + paramInt);
  }
  
  public String getString(int paramInt)
  {
    long l1;
    Object localObject;
    if (getColumnName(paramInt).equals("suggest_text_1"))
    {
      l1 = mCursor.getLong(mThreadIdPos);
      localObject = Conversation.get(this$0.getContext(), l1);
      ((Conversation)localObject).loadRecipients(true, false);
      return ((Conversation)localObject).getContactNames();
    }
    if (getColumnName(paramInt).equals("suggest_text_2"))
    {
      localObject = mCursor.getString(mSubjectPos);
      if (localObject != null) {
        if (!TextUtils.isEmpty((CharSequence)localObject)) {
          localObject = new EncodedStringValue(106, MiuiPduPersister.getBytes((String)localObject)).getString();
        }
      }
      for (;;)
      {
        return MessageUtils.getSnippet((String)localObject, mFirstQuery, 20, 5);
        localObject = "";
        continue;
        localObject = mCursor.getString(mBodyPos);
      }
    }
    if (getColumnName(paramInt).equals("suggest_icon_1")) {
      return null;
    }
    if (getColumnName(paramInt).equals("suggest_intent_action")) {
      return "android.intent.action.VIEW";
    }
    if (getColumnName(paramInt).equals("suggest_intent_data"))
    {
      l1 = mCursor.getLong(mThreadIdPos);
      long l2 = mCursor.getLong(mRowIdPos);
      localObject = null;
      if (l2 < 0L) {
        localObject = mCursor.getString(mBodyPos);
      }
      localObject = ComposeMessageRouterActivity.createSearchResultDataUri(l1, l2, mFirstQuery, (String)localObject).toString();
      LogTag.error("dataUri = %s", new Object[] { localObject });
      return (String)localObject;
    }
    if (getColumnName(paramInt).equals("suggest_shortcut_id")) {
      return "_-1";
    }
    throw new IllegalArgumentException("Invalid column index " + paramInt);
  }
  
  public int getType(int paramInt)
  {
    return SuggestionsProvider.access$100()[paramInt];
  }
  
  public boolean isNull(int paramInt)
  {
    return SuggestionsProvider.access$100()[paramInt] == 0;
  }
}

/* Location:
 * Qualified Name:     com.android.mms.SuggestionsProvider.SearchSuggestionCursorTranslator
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */