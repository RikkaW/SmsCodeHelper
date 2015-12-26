package com.android.mms.model;

import android.text.TextUtils;
import android.util.Pair;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class ContactMessage$ContactRecord
{
  private ArrayList<Pair<String, String>> mContact = new ArrayList();
  int mEnd;
  int mStart;
  
  public ContactMessage$ContactRecord(ContactMessage paramContactMessage, String paramString1, String paramString2, int paramInt1, int paramInt2)
  {
    mContact.add(new Pair(paramString1, paramString2));
    mStart = paramInt1;
    mEnd = paramInt2;
  }
  
  public boolean addRecord(String paramString1, String paramString2, int paramInt1, int paramInt2, String paramString3)
  {
    if ("vnd.android.cursor.item/name".equalsIgnoreCase(paramString1)) {}
    while ((mEnd != paramInt1) && (TextUtils.getTrimmedLength(paramString3) != 0)) {
      return false;
    }
    mContact.add(new Pair(paramString1, paramString2));
    mEnd = paramInt2;
    return true;
  }
  
  public ArrayList<Pair<String, String>> getContactRecord()
  {
    return mContact;
  }
  
  public String getPreviewString()
  {
    if (mContact.size() >= 1) {
      return (String)mContact.get(0)).second;
    }
    return "";
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    Iterator localIterator = mContact.iterator();
    while (localIterator.hasNext())
    {
      Pair localPair = (Pair)localIterator.next();
      localStringBuilder.append("[" + (String)ContactMessage.access$000(this$0).get(first) + "] " + (String)second + ";\n");
    }
    if (localStringBuilder.length() != 0) {
      localStringBuilder.deleteCharAt(localStringBuilder.length() - 1);
    }
    return localStringBuilder.toString();
  }
}

/* Location:
 * Qualified Name:     com.android.mms.model.ContactMessage.ContactRecord
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */