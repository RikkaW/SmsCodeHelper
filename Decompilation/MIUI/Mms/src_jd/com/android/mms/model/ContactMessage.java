package com.android.mms.model;

import android.text.TextUtils;
import android.util.Pair;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class ContactMessage
{
  private ArrayList<ContactRecord> mContactRecords;
  private String mMessage;
  private ArrayList<Object> mMessageParts;
  private int mPosition;
  private HashMap<String, String> mTypeLabelMap;
  
  public ContactMessage(String paramString, HashMap<String, String> paramHashMap)
  {
    mMessage = paramString;
    mContactRecords = new ArrayList();
    mPosition = 0;
    mTypeLabelMap = paramHashMap;
  }
  
  public void addRecord(String paramString1, String paramString2, int paramInt1, int paramInt2)
  {
    if ((paramInt1 >= mPosition) && (paramInt1 < paramInt2))
    {
      ContactRecord localContactRecord = null;
      if (!mContactRecords.isEmpty()) {
        localContactRecord = (ContactRecord)mContactRecords.get(mContactRecords.size() - 1);
      }
      String str = mMessage.substring(mPosition, paramInt1);
      if ((localContactRecord == null) || (!localContactRecord.addRecord(paramString1, paramString2, paramInt1, paramInt2, str)))
      {
        paramString1 = new ContactRecord(paramString1, paramString2, paramInt1, paramInt2);
        mContactRecords.add(paramString1);
      }
    }
    mPosition = paramInt2;
  }
  
  public int count()
  {
    return mContactRecords.size();
  }
  
  public ArrayList<ContactRecord> getContactRecords()
  {
    return mContactRecords;
  }
  
  public ArrayList<Object> split()
  {
    if (mMessageParts == null)
    {
      mMessageParts = new ArrayList();
      int i = 0;
      Object localObject = mContactRecords.iterator();
      while (((Iterator)localObject).hasNext())
      {
        ContactRecord localContactRecord = (ContactRecord)((Iterator)localObject).next();
        int k = mStart;
        int j = mEnd;
        if (k > i)
        {
          String str = mMessage.substring(i, k).trim();
          if (!TextUtils.isEmpty(str)) {
            mMessageParts.add(str);
          }
        }
        mMessageParts.add(localContactRecord);
        i = j;
      }
      if (i < mMessage.length())
      {
        localObject = mMessage.substring(i, mMessage.length()).trim();
        if (!TextUtils.isEmpty((CharSequence)localObject)) {
          mMessageParts.add(localObject);
        }
      }
    }
    return mMessageParts;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder(mMessage);
    int i = mContactRecords.size() - 1;
    while (i >= 0)
    {
      ContactRecord localContactRecord = (ContactRecord)mContactRecords.get(i);
      localStringBuilder.replace(mStart, mEnd, localContactRecord.toString());
      i -= 1;
    }
    return localStringBuilder.toString();
  }
  
  public class ContactRecord
  {
    private ArrayList<Pair<String, String>> mContact = new ArrayList();
    int mEnd;
    int mStart;
    
    public ContactRecord(String paramString1, String paramString2, int paramInt1, int paramInt2)
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
        localStringBuilder.append("[" + (String)mTypeLabelMap.get(first) + "] " + (String)second + ";\n");
      }
      if (localStringBuilder.length() != 0) {
        localStringBuilder.deleteCharAt(localStringBuilder.length() - 1);
      }
      return localStringBuilder.toString();
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.model.ContactMessage
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */