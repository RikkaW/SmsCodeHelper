package com.meizu.update;

import akh;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class UpdateInfo
  implements Parcelable
{
  public static final Parcelable.Creator<UpdateInfo> CREATOR = new akh();
  public String mDigest;
  public boolean mExistsUpdate;
  public boolean mNeedUpdate;
  public boolean mNoteNetWork;
  public String mSize;
  public long mSizeByte;
  public String mUpdateUrl;
  public String mUpdateUrl2;
  public int mVerifyMode;
  public String mVersionDate;
  public String mVersionDesc;
  public String mVersionName;
  
  public UpdateInfo() {}
  
  private UpdateInfo(Parcel paramParcel)
  {
    readFromParcel(paramParcel);
  }
  
  private int boolean2int(boolean paramBoolean)
  {
    if (paramBoolean) {
      return 1;
    }
    return 0;
  }
  
  public static UpdateInfo generateNoUpdateInfo()
  {
    UpdateInfo localUpdateInfo = new UpdateInfo();
    mNeedUpdate = false;
    mExistsUpdate = false;
    return localUpdateInfo;
  }
  
  private boolean int2boolean(int paramInt)
  {
    return paramInt != 0;
  }
  
  private void readFromParcel(Parcel paramParcel)
  {
    mNeedUpdate = int2boolean(paramParcel.readInt());
    mExistsUpdate = int2boolean(paramParcel.readInt());
    mUpdateUrl = paramParcel.readString();
    mVersionDesc = paramParcel.readString();
    mVersionName = paramParcel.readString();
    mSize = paramParcel.readString();
    mVersionDate = paramParcel.readString();
    mDigest = paramParcel.readString();
    mVerifyMode = paramParcel.readInt();
    mSizeByte = paramParcel.readLong();
    mUpdateUrl2 = paramParcel.readString();
    mNoteNetWork = int2boolean(paramParcel.readInt());
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeInt(boolean2int(mNeedUpdate));
    paramParcel.writeInt(boolean2int(mExistsUpdate));
    paramParcel.writeString(mUpdateUrl);
    paramParcel.writeString(mVersionDesc);
    paramParcel.writeString(mVersionName);
    paramParcel.writeString(mSize);
    paramParcel.writeString(mVersionDate);
    paramParcel.writeString(mDigest);
    paramParcel.writeInt(mVerifyMode);
    paramParcel.writeLong(mSizeByte);
    paramParcel.writeString(mUpdateUrl2);
    paramParcel.writeInt(boolean2int(mNoteNetWork));
  }
}

/* Location:
 * Qualified Name:     com.meizu.update.UpdateInfo
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */