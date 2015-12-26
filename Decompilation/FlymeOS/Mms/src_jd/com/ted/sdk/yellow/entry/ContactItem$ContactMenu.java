package com.ted.sdk.yellow.entry;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.util.ArrayList;
import java.util.List;

public class ContactItem$ContactMenu
  implements Parcelable
{
  public static final Parcelable.Creator<ContactMenu> CREATOR = new ContactItem.ContactMenu.1();
  public static final int MENU_LEVEL_1 = 1;
  public static final int MENU_LEVEL_2 = 2;
  private final int mCmdType;
  private final String mCommand;
  private String mExtendData;
  private final int mLevel;
  private final String mLogoUrl;
  private ArrayList<MessageItem.SpItem> mSpList = new ArrayList();
  private ArrayList<ContactMenu> mSubMenuList;
  private final String mSummary;
  private final String mTitle;
  
  ContactItem$ContactMenu(int paramInt1, String paramString1, String paramString2, String paramString3, int paramInt2, String paramString4, String paramString5)
  {
    mLevel = paramInt1;
    mTitle = paramString1;
    mSummary = paramString2;
    mLogoUrl = paramString3;
    mCmdType = paramInt2;
    mCommand = paramString4;
    mExtendData = paramString5;
    if ((paramInt1 == 1) && (mSubMenuList == null)) {
      mSubMenuList = new ArrayList();
    }
    if ((paramInt1 == 2) && (mSpList == null)) {
      mSpList = new ArrayList();
    }
  }
  
  private ContactItem$ContactMenu(Parcel paramParcel)
  {
    mLevel = paramParcel.readInt();
    mCmdType = paramParcel.readInt();
    Bundle localBundle = paramParcel.readBundle();
    mTitle = localBundle.getString("title");
    mSummary = localBundle.getString("summary");
    mLogoUrl = localBundle.getString("logo_uri");
    mCommand = localBundle.getString("command");
    mSubMenuList = localBundle.getParcelableArrayList("submenu");
    mExtendData = localBundle.getString("extend_data");
    paramParcel.readList(mSpList, getClass().getClassLoader());
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public int getCmdType()
  {
    return mCmdType;
  }
  
  public String getCommand()
  {
    return mCommand;
  }
  
  public String getExtendData()
  {
    return mExtendData;
  }
  
  public int getLevel()
  {
    return mLevel;
  }
  
  public String getLogoUrl()
  {
    return mLogoUrl;
  }
  
  public List<MessageItem.SpItem> getSpList()
  {
    return mSpList;
  }
  
  public List<ContactMenu> getSubMenuList()
  {
    return mSubMenuList;
  }
  
  public String getSummary()
  {
    return mSummary;
  }
  
  public String getTitle()
  {
    return mTitle;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeInt(mLevel);
    paramParcel.writeInt(mCmdType);
    Bundle localBundle = new Bundle();
    localBundle.putString("title", mTitle);
    localBundle.putString("summary", mSummary);
    localBundle.putString("logo_uri", mLogoUrl);
    localBundle.putString("command", mCommand);
    localBundle.putString("extend_data", mExtendData);
    localBundle.putParcelableArrayList("submenu", mSubMenuList);
    paramParcel.writeBundle(localBundle);
    paramParcel.writeList(mSpList);
  }
}

/* Location:
 * Qualified Name:     com.ted.sdk.yellow.entry.ContactItem.ContactMenu
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */