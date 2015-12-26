package com.android.mms;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.CharArrayBuffer;
import android.database.Cursor;
import android.database.CursorWrapper;
import android.database.MatrixCursor;
import android.net.Uri;
import android.net.Uri.Builder;
import android.provider.Telephony.MmsSms;
import android.text.TextUtils;
import com.android.mms.data.Conversation;
import com.android.mms.ui.ComposeMessageRouterActivity;
import com.android.mms.ui.MessageUtils;
import com.google.android.mms.pdu.EncodedStringValue;
import com.google.android.mms.pdu.MiuiPduPersister;

public class SuggestionsProvider
  extends ContentProvider
{
  private static final String[] COLUMNS = { "suggest_text_1", "suggest_text_2", "suggest_icon_1", "suggest_intent_action", "suggest_intent_data", "suggest_shortcut_id" };
  private static final int[] COLUMN_TYPES = { 3, 3, 0, 3, 3, 3 };
  
  public int delete(Uri paramUri, String paramString, String[] paramArrayOfString)
  {
    return 0;
  }
  
  public String getType(Uri paramUri)
  {
    return null;
  }
  
  public Uri insert(Uri paramUri, ContentValues paramContentValues)
  {
    return null;
  }
  
  public boolean onCreate()
  {
    return true;
  }
  
  public Cursor query(Uri paramUri, String[] paramArrayOfString1, String paramString1, String[] paramArrayOfString2, String paramString2)
  {
    paramUri = paramArrayOfString2[0].trim();
    if (TextUtils.isEmpty(paramUri)) {
      return new MatrixCursor(COLUMNS);
    }
    return new SearchSuggestionCursorTranslator(getContext().getContentResolver().query(Telephony.MmsSms.SEARCH_URI.buildUpon().appendQueryParameter("pattern", paramArrayOfString2[0]).appendQueryParameter("privacy_flag", "0").build(), null, null, null, null), paramUri);
  }
  
  public int update(Uri paramUri, ContentValues paramContentValues, String paramString, String[] paramArrayOfString)
  {
    return 0;
  }
  
  private class SearchSuggestionCursorTranslator
    extends CursorWrapper
  {
    private final int mBodyPos;
    private final int mDatePos;
    private final String mFirstQuery;
    private final int mRowIdPos;
    private final int mSubjectPos;
    private final int mThreadIdPos;
    
    public SearchSuggestionCursorTranslator(Cursor paramCursor, String paramString)
    {
      super();
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
      return SuggestionsProvider.COLUMNS.length;
    }
    
    public int getColumnIndex(String paramString)
    {
      int i = 0;
      while (i < SuggestionsProvider.COLUMNS.length)
      {
        if (SuggestionsProvider.COLUMNS[i].equals(paramString)) {
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
      return SuggestionsProvider.COLUMNS[paramInt];
    }
    
    public String[] getColumnNames()
    {
      return (String[])SuggestionsProvider.COLUMNS.clone();
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
        localObject = Conversation.get(getContext(), l1);
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
      return SuggestionsProvider.COLUMN_TYPES[paramInt];
    }
    
    public boolean isNull(int paramInt)
    {
      return SuggestionsProvider.COLUMN_TYPES[paramInt] == 0;
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.SuggestionsProvider
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */