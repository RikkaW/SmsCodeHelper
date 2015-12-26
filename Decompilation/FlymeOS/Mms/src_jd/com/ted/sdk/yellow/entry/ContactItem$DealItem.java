package com.ted.sdk.yellow.entry;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.ted.android.contacts.netparser.model.DealItem;

public class ContactItem$DealItem
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
  
  ContactItem$DealItem(Parcel paramParcel)
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
  
  ContactItem$DealItem(DealItem paramDealItem)
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

/* Location:
 * Qualified Name:     com.ted.sdk.yellow.entry.ContactItem.DealItem
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */