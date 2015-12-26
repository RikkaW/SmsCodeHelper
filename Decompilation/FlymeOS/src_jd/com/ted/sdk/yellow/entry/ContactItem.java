package com.ted.sdk.yellow.entry;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.ted.android.contacts.netparser.model.DealItem;
import com.ted.android.contacts.netparser.model.NumItem.RelevantNumber;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ContactItem
  extends BaseItem
{
  private static final String EXTRA_COMMAND = "command";
  private static final String EXTRA_CURR_PRICE = "curr_price";
  private static final String EXTRA_DATA = "extend_data";
  private static final String EXTRA_DEAL_END = "deal_end";
  private static final String EXTRA_DEAL_IMAGE = "deal_image";
  private static final String EXTRA_DEAL_NAME = "deal_name";
  private static final String EXTRA_DEAL_START = "deal_start";
  private static final String EXTRA_DESCRIPTION = "description";
  private static final String EXTRA_LOGO_URI = "logo_uri";
  private static final String EXTRA_ORIG_PRICE = "orig_price";
  private static final String EXTRA_RESERVED = "reservation";
  private static final String EXTRA_SUBMEMU = "submenu";
  private static final String EXTRA_SUMMARY = "summary";
  private static final String EXTRA_TITLE = "title";
  private static final String EXTRA_URL = "deal_url";
  private final String mAddress;
  private List<DealItem> mDealList;
  private final List<ContactMenu> mMenuList = new ArrayList(3);
  private String mRelevantDesc;
  private String mRelevantLogo;
  private String mRelevantName;
  private List<RelevantNumber> mRelevantNumberList;
  
  public ContactItem(String paramString1, String paramString2)
  {
    super(paramString1);
    mAddress = paramString2;
  }
  
  public void addDeal(DealItem paramDealItem)
  {
    if (mDealList == null) {
      mDealList = new ArrayList();
    }
    mDealList.add(paramDealItem);
  }
  
  public void addMenu(ContactMenu paramContactMenu)
  {
    mMenuList.add(paramContactMenu);
  }
  
  public void addRelevantNumber(RelevantNumber paramRelevantNumber)
  {
    if (mRelevantNumberList == null) {
      mRelevantNumberList = new ArrayList();
    }
    mRelevantNumberList.add(paramRelevantNumber);
  }
  
  public String getAddress()
  {
    return mAddress;
  }
  
  public List<DealItem> getDealList()
  {
    return mDealList;
  }
  
  public List<DealItem> getDealList(boolean paramBoolean)
  {
    if (paramBoolean) {
      return mDealList;
    }
    Iterator localIterator = mDealList.iterator();
    for (;;)
    {
      if (!localIterator.hasNext()) {
        return mDealList;
      }
      long l = ((DealItem)localIterator.next()).getEndTime() * 1000 + 86400000;
      if (System.currentTimeMillis() >= l) {
        localIterator.remove();
      }
    }
  }
  
  public List<ContactMenu> getMenuList()
  {
    return mMenuList;
  }
  
  public String getRelevantDesc()
  {
    return mRelevantDesc;
  }
  
  public String getRelevantLogo()
  {
    return mRelevantLogo;
  }
  
  public String getRelevantName()
  {
    return mRelevantName;
  }
  
  public List<RelevantNumber> getRelevantNumbers()
  {
    return mRelevantNumberList;
  }
  
  public void setRelevantDesc(String paramString)
  {
    mRelevantDesc = paramString;
  }
  
  public void setRelevantLogo(String paramString)
  {
    mRelevantLogo = paramString;
  }
  
  public void setRelevantName(String paramString)
  {
    mRelevantName = paramString;
  }
  
  public static class ContactMenu
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
    
    ContactMenu(int paramInt1, String paramString1, String paramString2, String paramString3, int paramInt2, String paramString4, String paramString5)
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
    
    private ContactMenu(Parcel paramParcel)
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
  
  public static class DealItem
    implements Parcelable
  {
    public static final Parcelable.Creator<DealItem> CREATOR = new ContactItem.DealItem.1();
    private final float mCurrPrice;
    private final String mDealImage;
    private final String mDealName;
    private final String mDescription;
    private int mEnd;
    private final float mOrigPrice;
    private final boolean mReservation;
    private int mStart;
    private final String mUrl;
    
    DealItem(Parcel paramParcel)
    {
      paramParcel = paramParcel.readBundle();
      mDealName = paramParcel.getString("deal_name");
      mDealImage = paramParcel.getString("deal_image");
      mDescription = paramParcel.getString("description");
      mOrigPrice = paramParcel.getFloat("orig_price");
      mCurrPrice = paramParcel.getFloat("curr_price");
      mReservation = paramParcel.getBoolean("reservation");
      mUrl = paramParcel.getString("deal_url");
      mStart = paramParcel.getInt("deal_start");
      mEnd = paramParcel.getInt("deal_end");
    }
    
    DealItem(DealItem paramDealItem)
    {
      mDealName = paramDealItem.getDealName();
      mDealImage = paramDealItem.getDealImage();
      mDescription = paramDealItem.getDescription();
      mOrigPrice = paramDealItem.getOrigPrice();
      mCurrPrice = paramDealItem.getCurrPrice();
      mReservation = paramDealItem.isReserved();
      mUrl = paramDealItem.getUrl();
      mStart = paramDealItem.getStartTime();
      mEnd = paramDealItem.getEndTime();
    }
    
    public int describeContents()
    {
      return 0;
    }
    
    public float getCurrPrice()
    {
      return mCurrPrice;
    }
    
    public String getDealImage()
    {
      return mDealImage;
    }
    
    public String getDealName()
    {
      return mDealName;
    }
    
    public String getDescription()
    {
      return mDescription;
    }
    
    public int getEndTime()
    {
      return mEnd;
    }
    
    public float getOrigPrice()
    {
      return mOrigPrice;
    }
    
    public int getStartTime()
    {
      return mStart;
    }
    
    public String getUrl()
    {
      return mUrl;
    }
    
    public boolean isReserved()
    {
      return mReservation;
    }
    
    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      Bundle localBundle = new Bundle();
      localBundle.putString("deal_name", mDealName);
      localBundle.putString("deal_image", mDealImage);
      localBundle.putString("description", mDescription);
      localBundle.putFloat("orig_price", mOrigPrice);
      localBundle.putFloat("curr_price", mCurrPrice);
      localBundle.putBoolean("reservation", mReservation);
      localBundle.putString("deal_url", mUrl);
      localBundle.putInt("deal_start", mStart);
      localBundle.putInt("deal_end", mEnd);
      paramParcel.writeBundle(localBundle);
    }
  }
  
  public static class RelevantNumber
    implements Parcelable
  {
    public static final Parcelable.Creator<RelevantNumber> CREATOR = new ContactItem.RelevantNumber.1();
    private String desc;
    private String name;
    private String phone;
    
    private RelevantNumber(Parcel paramParcel)
    {
      phone = paramParcel.readString();
      name = paramParcel.readString();
      desc = paramParcel.readString();
    }
    
    RelevantNumber(NumItem.RelevantNumber paramRelevantNumber)
    {
      this(a, b, c);
    }
    
    RelevantNumber(String paramString1, String paramString2, String paramString3)
    {
      phone = paramString1;
      name = paramString2;
      desc = paramString3;
    }
    
    public int describeContents()
    {
      return 0;
    }
    
    public String getDesciption()
    {
      return desc;
    }
    
    public String getName()
    {
      return name;
    }
    
    public String getPhoneNumber()
    {
      return phone;
    }
    
    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      paramParcel.writeString(phone);
      paramParcel.writeString(name);
      paramParcel.writeString(desc);
    }
  }
}

/* Location:
 * Qualified Name:     com.ted.sdk.yellow.entry.ContactItem
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */