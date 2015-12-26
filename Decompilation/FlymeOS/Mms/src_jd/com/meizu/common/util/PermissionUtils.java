package com.meizu.common.util;

import android.content.Context;
import android.content.res.Resources;
import com.meizu.common.R.string;
import java.util.ArrayList;

public class PermissionUtils
{
  public static final int OP_GROUP_BOOTCOMPLETED = 9;
  public static final int OP_GROUP_CAMERA = 7;
  public static final int OP_GROUP_GPS = 22;
  public static final int OP_GROUP_INTERNET = 25;
  public static final int OP_GROUP_LOCATION = 5;
  public static final int OP_GROUP_NFC = 13;
  public static final int OP_GROUP_NONE = -1;
  public static final int OP_GROUP_OPEN_BLUETOOTH = 8;
  public static final int OP_GROUP_OPEN_MOBILE_DATA = 3;
  public static final int OP_GROUP_OPEN_WLAN = 4;
  public static final int OP_GROUP_PHONE_CALL = 0;
  public static final int OP_GROUP_PHONE_OUT_GOING_CALL = 23;
  public static final int OP_GROUP_PHONE_READ_CALLLOG = 10;
  public static final int OP_GROUP_PHONE_WRITE_CALLLOG = 21;
  public static final int OP_GROUP_READ_CONTACTS = 1;
  public static final int OP_GROUP_READ_MMS = 11;
  public static final int OP_GROUP_READ_SMS = 2;
  public static final int OP_GROUP_RECEIVE_MMS = 20;
  public static final int OP_GROUP_RECEIVE_SMS = 17;
  public static final int OP_GROUP_RECORDAUDIO = 6;
  public static final int OP_GROUP_RECORDAUDIO_PHONE = 12;
  public static final int OP_GROUP_SEND_MMS = 19;
  public static final int OP_GROUP_SEND_SMS = 16;
  public static final int OP_GROUP_SYSTEM_ALERT = 24;
  public static final int OP_GROUP_WRITE_CONTACTS = 14;
  public static final int OP_GROUP_WRITE_MMS = 18;
  public static final int OP_GROUP_WRITE_SMS = 15;
  public static ArrayList<String> mBootCompletedList;
  public static ArrayList<String> mCameraList;
  public static ArrayList<String> mInternetList;
  public static ArrayList<String> mLocationList;
  public static ArrayList<String> mOPenBluetoothList;
  public static ArrayList<String> mOPenGpsList;
  public static ArrayList<String> mOPenWalnList;
  public static ArrayList<String> mOpenMobileDataList;
  public static ArrayList<String> mOpenNfcList;
  public static ArrayList<String> mPhoneCallList = new ArrayList();
  public static ArrayList<String> mPhoneOutGoingList;
  public static ArrayList<String> mReadCallLogList;
  public static ArrayList<String> mReadContactsList = new ArrayList();
  public static ArrayList<String> mReadMmsList;
  public static ArrayList<String> mReadSmsList = new ArrayList();
  public static ArrayList<String> mReceiveMMsList;
  public static ArrayList<String> mReceiveSmsList;
  public static ArrayList<String> mRecordAudioList;
  public static ArrayList<String> mRecordAudioPhoneList;
  public static ArrayList<String> mSendMmsList;
  public static ArrayList<String> mSendSmsList;
  public static ArrayList<String> mSystemAlertList;
  public static ArrayList<String> mWriteCallLogList;
  public static ArrayList<String> mWriteContactsList;
  public static ArrayList<String> mWriteMmsList;
  public static ArrayList<String> mWriteSmsList;
  private Context mContext;
  
  static
  {
    mOpenMobileDataList = new ArrayList();
    mOPenWalnList = new ArrayList();
    mLocationList = new ArrayList();
    mRecordAudioList = new ArrayList();
    mCameraList = new ArrayList();
    mOPenBluetoothList = new ArrayList();
    mBootCompletedList = new ArrayList();
    mReadCallLogList = new ArrayList();
    mReadMmsList = new ArrayList();
    mRecordAudioPhoneList = new ArrayList();
    mOpenNfcList = new ArrayList();
    mWriteContactsList = new ArrayList();
    mWriteSmsList = new ArrayList();
    mSendSmsList = new ArrayList();
    mReceiveSmsList = new ArrayList();
    mWriteMmsList = new ArrayList();
    mSendMmsList = new ArrayList();
    mReceiveMMsList = new ArrayList();
    mWriteCallLogList = new ArrayList();
    mOPenGpsList = new ArrayList();
    mPhoneOutGoingList = new ArrayList();
    mSystemAlertList = new ArrayList();
    mInternetList = new ArrayList();
    mPhoneCallList.add("android.permission.CALL_PHONE");
    mReadContactsList.add("android.permission.READ_CONTACTS");
    mReadSmsList.add("android.permission.READ_SMS");
    mOpenMobileDataList.add("android.permission.CHANGE_NETWORK_STATE");
    mOPenWalnList.add("android.permission.CHANGE_WIFI_STATE");
    mLocationList.add("android.permission.ACCESS_COARSE_LOCATION");
    mLocationList.add("android.permission.ACCESS_FINE_LOCATION");
    mRecordAudioList.add("android.permission.RECORD_AUDIO");
    mCameraList.add("android.permission.CAMERA");
    mOPenBluetoothList.add("android.permission.BLUETOOTH");
    mOPenBluetoothList.add("android.permission.BLUETOOTH_ADMIN");
    mBootCompletedList.add("android.permission.RECEIVE_BOOT_COMPLETED");
    mReadCallLogList.add("android.permission.READ_CALL_LOG");
    mReadMmsList.add("android.permission.READ_SMS");
    mOpenNfcList.add("android.permission.NFC");
    mWriteContactsList.add("android.permission.WRITE_CONTACTS");
    mWriteSmsList.add("android.permission.WRITE_SMS");
    mSendSmsList.add("android.permission.SEND_SMS");
    mReceiveSmsList.add("android.permission.RECEIVE_SMS");
    mReceiveMMsList.add("android.permission.RECEIVE_MMS");
    mWriteCallLogList.add("android.permission.WRITE_CALL_LOG");
    mPhoneOutGoingList.add("android.permission.PROCESS_OUTGOING_CALLS");
    mSystemAlertList.add("android.permission.SYSTEM_ALERT_WINDOW");
    mInternetList.add("android.permission.INTERNET");
  }
  
  public PermissionUtils(Context paramContext)
  {
    mContext = paramContext;
  }
  
  public String getPermissionLabelByName(String paramString)
  {
    switch (getPermissionTypeByName(paramString))
    {
    default: 
      return null;
    case 0: 
      return mContext.getResources().getString(R.string.mc_pm_call);
    case 1: 
      return mContext.getResources().getString(R.string.mc_pm_contacts);
    case 2: 
      return mContext.getResources().getString(R.string.mc_pm_r_sms);
    case 3: 
      return mContext.getResources().getString(R.string.mc_pm_set_gprs_on);
    case 4: 
      return mContext.getResources().getString(R.string.mc_pm_set_wifi_on);
    case 5: 
      return mContext.getResources().getString(R.string.mc_pm_location);
    case 6: 
      return mContext.getResources().getString(R.string.mc_pm_recordaudio_local);
    case 7: 
      return mContext.getResources().getString(R.string.mc_pm_camera);
    case 8: 
      return mContext.getResources().getString(R.string.mc_pm_set_bluetooth_on);
    case 9: 
      return "";
    case 10: 
      return mContext.getResources().getString(R.string.mc_pm_call_log);
    case 11: 
      return mContext.getResources().getString(R.string.mc_pm_r_mms);
    case 12: 
      return mContext.getResources().getString(R.string.mc_pm_recordaudio_phone);
    case 13: 
      return mContext.getResources().getString(R.string.mc_pm_nfc);
    case 14: 
      return "";
    case 15: 
      return "";
    case 16: 
      return mContext.getResources().getString(R.string.mc_pm_s_sms);
    case 17: 
      return "";
    case 18: 
      return "";
    case 19: 
      return mContext.getResources().getString(R.string.mc_pm_s_mms);
    case 20: 
      return "";
    case 21: 
      return "";
    case 22: 
      return "";
    case 23: 
      return "";
    case 24: 
      return "";
    }
    return mContext.getResources().getString(R.string.mc_pm_online_intenert);
  }
  
  public int getPermissionTypeByName(String paramString)
  {
    if (mPhoneCallList.contains(paramString)) {
      return 0;
    }
    if (mReadContactsList.contains(paramString)) {
      return 1;
    }
    if (mReadSmsList.contains(paramString)) {
      return 2;
    }
    if (mOpenMobileDataList.contains(paramString)) {
      return 3;
    }
    if (mOPenWalnList.contains(paramString)) {
      return 4;
    }
    if (mLocationList.contains(paramString)) {
      return 5;
    }
    if (mRecordAudioList.contains(paramString)) {
      return 6;
    }
    if (mCameraList.contains(paramString)) {
      return 7;
    }
    if (mOPenBluetoothList.contains(paramString)) {
      return 8;
    }
    if (mBootCompletedList.contains(paramString)) {
      return 9;
    }
    if (mReadCallLogList.contains(paramString)) {
      return 10;
    }
    if (mReadMmsList.contains(paramString)) {
      return 11;
    }
    if (mRecordAudioPhoneList.contains(paramString)) {
      return 12;
    }
    if (mOpenNfcList.contains(paramString)) {
      return 13;
    }
    if (mWriteContactsList.contains(paramString)) {
      return 14;
    }
    if (mWriteSmsList.contains(paramString)) {
      return 15;
    }
    if (mSendSmsList.contains(paramString)) {
      return 16;
    }
    if (mReceiveSmsList.contains(paramString)) {
      return 17;
    }
    if (mWriteMmsList.contains(paramString)) {
      return 18;
    }
    if (mSendMmsList.contains(paramString)) {
      return 19;
    }
    if (mReceiveMMsList.contains(paramString)) {
      return 20;
    }
    if (mWriteCallLogList.contains(paramString)) {
      return 21;
    }
    if (mOPenGpsList.contains(paramString)) {
      return 22;
    }
    if (mPhoneOutGoingList.contains(paramString)) {
      return 23;
    }
    if (mSystemAlertList.contains(paramString)) {
      return 24;
    }
    if (mInternetList.contains(paramString)) {
      return 25;
    }
    return -1;
  }
  
  public String[] loadPemissionLables(String[] paramArrayOfString)
  {
    if ((paramArrayOfString == null) || (paramArrayOfString.length == 0)) {
      return null;
    }
    String[] arrayOfString = new String[paramArrayOfString.length];
    ArrayList localArrayList = new ArrayList();
    int j = paramArrayOfString.length;
    int i = 0;
    while (i < j)
    {
      localArrayList.add(getPermissionLabelByName(paramArrayOfString[i]));
      i += 1;
    }
    localArrayList.toArray(arrayOfString);
    return arrayOfString;
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.util.PermissionUtils
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */