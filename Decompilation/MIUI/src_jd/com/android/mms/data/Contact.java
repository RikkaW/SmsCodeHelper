package com.android.mms.data;

import android.app.Application;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SqliteWrapper;
import android.net.Uri;
import android.os.Parcelable;
import android.provider.ContactsContract.Contacts;
import android.provider.ContactsContract.Data;
import android.provider.ContactsContract.Presence;
import android.provider.ContactsContract.Profile;
import android.provider.Telephony.Mms;
import android.provider.Telephony.MmsSms;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;
import com.android.internal.util.ArrayUtils;
import com.android.mms.LogTag;
import com.android.mms.MmsApp;
import com.android.mms.ui.MessageUtils;
import com.xiaomi.mms.utils.B2cMessageUtils;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import miui.os.Build;
import miui.telephony.PhoneNumberUtils;
import miui.telephony.PhoneNumberUtils.PhoneNumber;
import miui.yellowpage.MiPubUtils;
import miui.yellowpage.YellowPage;
import miui.yellowpage.YellowPagePhone;
import miui.yellowpage.YellowPageUtils;

public class Contact
{
  private static final String[] PREFIXES = { "12520" };
  private static final HashSet<UpdateListener> mListeners = new HashSet();
  private static char[] mSpecialCharacters = { 44, 59 };
  private static ContactsCache sContactCache;
  private static ContactPhotoLoader sContactPhotoLoader;
  private String mAccountType;
  private String mCompareKey;
  private long mContactMethodId;
  private int mContactMethodType;
  private boolean mIsB2cNumber;
  private boolean mIsEmail;
  private boolean mIsMe;
  private boolean mIsPhoneNumber;
  private volatile boolean mIsStale;
  private boolean mIsYellowPageNumber;
  private String mLabel;
  private String mMxPhoneNumber;
  private String mName;
  private String mNameAndNumber;
  private String mNick;
  private String mNumber;
  private String mNumberE164;
  private boolean mNumberIsModified;
  private Uri mPeopleReferenceUri;
  private long mPersonId;
  private String mPhoneTag;
  private long mPhotoId;
  private int mPresenceResId;
  private String mPresenceText;
  private volatile boolean mQueryPending;
  private long mRawContactId;
  private long mRecipientId;
  private boolean mSendToVoicemail;
  private long mYellowPageId;
  private String mYellowPageThumbnailName;
  
  private Contact(String paramString)
  {
    init(paramString, "");
  }
  
  private Contact(boolean paramBoolean)
  {
    init("Self_Item_Key", "");
    mIsMe = paramBoolean;
  }
  
  public static void addListener(UpdateListener paramUpdateListener)
  {
    synchronized (mListeners)
    {
      mListeners.add(paramUpdateListener);
      return;
    }
  }
  
  public static void asyncloadAllInBackground()
  {
    if (sContactCachemLoadAllState.get() != 1)
    {
      Thread local1 = new Thread()
      {
        public void run()
        {
          Contact.sContactCache.loadAll();
        }
      };
      local1.setName("AllContactsLoader");
      local1.setPriority(1);
      local1.start();
    }
  }
  
  public static void cancelRequest(ImageView paramImageView)
  {
    sContactPhotoLoader.cancelRequest(paramImageView);
  }
  
  private static String emptyIfNull(String paramString)
  {
    if (paramString != null) {
      return paramString;
    }
    return "";
  }
  
  public static String formatNameAndNumber(String paramString1, String paramString2, String paramString3)
  {
    paramString3 = paramString2;
    if (paramString1 != null) {
      paramString3 = paramString1 + " <" + paramString2 + ">";
    }
    return paramString3;
  }
  
  public static Contact get(String paramString)
  {
    return sContactCache.get(paramString);
  }
  
  public static List<Contact> getByPhoneUris(Parcelable[] paramArrayOfParcelable)
  {
    return sContactCache.getContactInfoForPhoneUris(paramArrayOfParcelable);
  }
  
  public static int getLoadAllState()
  {
    return sContactCachemLoadAllState.get();
  }
  
  public static void init(Context paramContext)
  {
    paramContext = paramContext.getApplicationContext();
    sContactCache = new ContactsCache(paramContext, null);
    sContactPhotoLoader = new ContactPhotoLoader(paramContext, 285343793, 2130837723);
    RecipientIdCache.init(paramContext);
  }
  
  private void init(String paramString1, String paramString2)
  {
    mContactMethodId = -1L;
    mName = paramString2;
    mNick = "";
    setNumber(paramString1);
    mNumberIsModified = false;
    mLabel = "";
    mPersonId = 0L;
    mPhotoId = 0L;
    mPresenceResId = 0;
    mIsStale = true;
    mIsYellowPageNumber = false;
    mIsB2cNumber = false;
    mYellowPageThumbnailName = null;
    mSendToVoicemail = false;
    mPhoneTag = "";
  }
  
  public static void invalidatePhotoCache()
  {
    sContactPhotoLoader.clear();
  }
  
  public static boolean isSimContact(Contact paramContact)
  {
    return "com.android.contacts.sim".equals(mAccountType);
  }
  
  public static void loadPhoto(ImageView paramImageView, Contact paramContact)
  {
    sContactPhotoLoader.loadPhoto(paramImageView, paramContact);
  }
  
  public static String normalizePhoneNumber(String paramString)
  {
    PhoneNumberUtils.PhoneNumber localPhoneNumber = PhoneNumberUtils.PhoneNumber.parse(paramString);
    paramString = normalizePhoneNumber(localPhoneNumber, paramString);
    localPhoneNumber.recycle();
    return paramString;
  }
  
  public static String normalizePhoneNumber(PhoneNumberUtils.PhoneNumber paramPhoneNumber, String paramString)
  {
    String str1 = paramString;
    String str2 = paramPhoneNumber.getEffectiveNumber();
    String str3 = paramPhoneNumber.getCountryCode();
    if ((TextUtils.isEmpty(str3)) || (paramPhoneNumber.isChineseNumber()))
    {
      if (paramPhoneNumber.isChineseNumber()) {
        paramPhoneNumber = str2;
      }
      do
      {
        do
        {
          do
          {
            return paramPhoneNumber;
            str2 = ((TelephonyManager)MmsApp.getApp().getSystemService("phone")).getSimCountryIso();
            paramPhoneNumber = str1;
          } while (TextUtils.isEmpty(str2));
          str2 = str2.toUpperCase();
          paramPhoneNumber = str1;
        } while (TextUtils.isEmpty(str2));
        paramString = PhoneNumberUtils.formatNumberToE164(paramString, str2);
        paramPhoneNumber = str1;
      } while (TextUtils.isEmpty(paramString));
      return paramString;
    }
    return "+" + str3 + str2;
  }
  
  private void notSynchronizedUpdateNameAndNumber()
  {
    mNameAndNumber = formatNameAndNumber(mName, mNumber, mNumberE164);
  }
  
  public static void pauseCaching()
  {
    sContactPhotoLoader.pause();
  }
  
  public static void removeAllListener()
  {
    synchronized (mListeners)
    {
      mListeners.clear();
      return;
    }
  }
  
  public static void removeListener(UpdateListener paramUpdateListener)
  {
    synchronized (mListeners)
    {
      mListeners.remove(paramUpdateListener);
      return;
    }
  }
  
  public static String removeSpaceForAddress(String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    int i = 0;
    while (i < paramString.length())
    {
      char c = paramString.charAt(i);
      if (c > ' ') {
        localStringBuilder.append(Character.toUpperCase(c));
      }
      i += 1;
    }
    return localStringBuilder.toString();
  }
  
  public static void requestClear(boolean paramBoolean)
  {
    sContactPhotoLoader.requestClear(paramBoolean);
  }
  
  public static void resumeCaching()
  {
    sContactPhotoLoader.resume();
  }
  
  public static ContactList searchForContacts(String paramString)
  {
    return sContactCache.searchForContacts(paramString);
  }
  
  private void setToUnknown()
  {
    mContactMethodId = -1L;
    mContactMethodType = 0;
    mRawContactId = 0L;
    mName = null;
    mNick = null;
    mNameAndNumber = mNumber;
    mLabel = null;
    mPersonId = 0L;
    mPresenceResId = 0;
    mPresenceText = null;
    mPhotoId = 0L;
    mIsYellowPageNumber = false;
    mIsB2cNumber = false;
    mYellowPageThumbnailName = null;
    mSendToVoicemail = false;
    mAccountType = null;
  }
  
  public static void stopCaching()
  {
    sContactPhotoLoader.stop();
  }
  
  public boolean equals(Object paramObject)
  {
    try
    {
      paramObject = (Contact)paramObject;
      if (mCompareKey == null)
      {
        if (mCompareKey == null) {
          return true;
        }
      }
      else
      {
        boolean bool = mCompareKey.equals(mCompareKey);
        return bool;
      }
    }
    catch (ClassCastException paramObject) {}
    return false;
  }
  
  /* Error */
  public boolean existsInDatabase()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 122	com/android/mms/data/Contact:mPersonId	J
    //   6: lstore_1
    //   7: lload_1
    //   8: lconst_0
    //   9: lcmp
    //   10: ifle +9 -> 19
    //   13: iconst_1
    //   14: istore_3
    //   15: aload_0
    //   16: monitorexit
    //   17: iload_3
    //   18: ireturn
    //   19: iconst_0
    //   20: istore_3
    //   21: goto -6 -> 15
    //   24: astore 4
    //   26: aload_0
    //   27: monitorexit
    //   28: aload 4
    //   30: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	31	0	this	Contact
    //   6	2	1	l	long
    //   14	7	3	bool	boolean
    //   24	5	4	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   2	7	24	finally
  }
  
  public String getCompareKey()
  {
    try
    {
      String str = mCompareKey;
      return str;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public long getContactMethodId()
  {
    return mContactMethodId;
  }
  
  public int getContactMethodType()
  {
    return mContactMethodType;
  }
  
  /* Error */
  public String getDisplayTag()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 158	com/android/mms/data/Contact:mPhoneTag	Ljava/lang/String;
    //   6: ifnull +17 -> 23
    //   9: aload_0
    //   10: getfield 158	com/android/mms/data/Contact:mPhoneTag	Ljava/lang/String;
    //   13: aload_0
    //   14: getfield 138	com/android/mms/data/Contact:mName	Ljava/lang/String;
    //   17: invokevirtual 333	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   20: ifeq +10 -> 30
    //   23: ldc 87
    //   25: astore_1
    //   26: aload_0
    //   27: monitorexit
    //   28: aload_1
    //   29: areturn
    //   30: aload_0
    //   31: getfield 158	com/android/mms/data/Contact:mPhoneTag	Ljava/lang/String;
    //   34: astore_1
    //   35: goto -9 -> 26
    //   38: astore_1
    //   39: aload_0
    //   40: monitorexit
    //   41: aload_1
    //   42: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	43	0	this	Contact
    //   25	10	1	str	String
    //   38	4	1	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   2	23	38	finally
    //   30	35	38	finally
  }
  
  /* Error */
  public Uri getEmailUri()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: invokevirtual 453	com/android/mms/data/Contact:existsInDatabase	()Z
    //   6: ifeq +18 -> 24
    //   9: getstatic 458	android/provider/ContactsContract$CommonDataKinds$Phone:CONTENT_URI	Landroid/net/Uri;
    //   12: aload_0
    //   13: getfield 117	com/android/mms/data/Contact:mContactMethodId	J
    //   16: invokestatic 464	android/content/ContentUris:withAppendedId	(Landroid/net/Uri;J)Landroid/net/Uri;
    //   19: astore_1
    //   20: aload_0
    //   21: monitorexit
    //   22: aload_1
    //   23: areturn
    //   24: new 466	android/net/Uri$Builder
    //   27: dup
    //   28: invokespecial 467	android/net/Uri$Builder:<init>	()V
    //   31: astore_1
    //   32: aload_1
    //   33: ldc_w 469
    //   36: invokevirtual 473	android/net/Uri$Builder:scheme	(Ljava/lang/String;)Landroid/net/Uri$Builder;
    //   39: pop
    //   40: aload_1
    //   41: aload_0
    //   42: getfield 168	com/android/mms/data/Contact:mNumber	Ljava/lang/String;
    //   45: invokevirtual 476	android/net/Uri$Builder:encodedOpaquePart	(Ljava/lang/String;)Landroid/net/Uri$Builder;
    //   48: pop
    //   49: aload_1
    //   50: invokevirtual 479	android/net/Uri$Builder:build	()Landroid/net/Uri;
    //   53: astore_1
    //   54: goto -34 -> 20
    //   57: astore_1
    //   58: aload_0
    //   59: monitorexit
    //   60: aload_1
    //   61: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	62	0	this	Contact
    //   19	35	1	localObject1	Object
    //   57	4	1	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   2	20	57	finally
    //   24	54	57	finally
  }
  
  public String getMxPhoneNumber()
  {
    try
    {
      String str = mMxPhoneNumber;
      return str;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  /* Error */
  public String getName()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 138	com/android/mms/data/Contact:mName	Ljava/lang/String;
    //   6: invokestatic 362	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   9: ifeq +12 -> 21
    //   12: aload_0
    //   13: invokevirtual 486	com/android/mms/data/Contact:getNumber	()Ljava/lang/String;
    //   16: astore_1
    //   17: aload_0
    //   18: monitorexit
    //   19: aload_1
    //   20: areturn
    //   21: aload_0
    //   22: getfield 138	com/android/mms/data/Contact:mName	Ljava/lang/String;
    //   25: astore_1
    //   26: goto -9 -> 17
    //   29: astore_1
    //   30: aload_0
    //   31: monitorexit
    //   32: aload_1
    //   33: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	34	0	this	Contact
    //   16	10	1	str	String
    //   29	4	1	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   2	17	29	finally
    //   21	26	29	finally
  }
  
  public String getNameAndNumber()
  {
    try
    {
      String str = mNameAndNumber;
      return str;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public String getNickname()
  {
    try
    {
      String str = mNick;
      return str;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public String getNumber()
  {
    try
    {
      String str = mNumber;
      return str;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public Uri getPeopleReferenceUri()
  {
    return mPeopleReferenceUri;
  }
  
  public long getPersonId()
  {
    return mPersonId;
  }
  
  /* Error */
  public Uri getPhoneUri()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: invokevirtual 453	com/android/mms/data/Contact:existsInDatabase	()Z
    //   6: ifeq +18 -> 24
    //   9: getstatic 458	android/provider/ContactsContract$CommonDataKinds$Phone:CONTENT_URI	Landroid/net/Uri;
    //   12: aload_0
    //   13: getfield 117	com/android/mms/data/Contact:mContactMethodId	J
    //   16: invokestatic 464	android/content/ContentUris:withAppendedId	(Landroid/net/Uri;J)Landroid/net/Uri;
    //   19: astore_1
    //   20: aload_0
    //   21: monitorexit
    //   22: aload_1
    //   23: areturn
    //   24: new 466	android/net/Uri$Builder
    //   27: dup
    //   28: invokespecial 467	android/net/Uri$Builder:<init>	()V
    //   31: astore_1
    //   32: aload_1
    //   33: ldc_w 493
    //   36: invokevirtual 473	android/net/Uri$Builder:scheme	(Ljava/lang/String;)Landroid/net/Uri$Builder;
    //   39: pop
    //   40: aload_1
    //   41: aload_0
    //   42: getfield 168	com/android/mms/data/Contact:mNumber	Ljava/lang/String;
    //   45: invokevirtual 476	android/net/Uri$Builder:encodedOpaquePart	(Ljava/lang/String;)Landroid/net/Uri$Builder;
    //   48: pop
    //   49: aload_1
    //   50: invokevirtual 479	android/net/Uri$Builder:build	()Landroid/net/Uri;
    //   53: astore_1
    //   54: goto -34 -> 20
    //   57: astore_1
    //   58: aload_0
    //   59: monitorexit
    //   60: aload_1
    //   61: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	62	0	this	Contact
    //   19	35	1	localObject1	Object
    //   57	4	1	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   2	20	57	finally
    //   24	54	57	finally
  }
  
  public long getPhotoId()
  {
    return mPhotoId;
  }
  
  public String getRealName()
  {
    try
    {
      String str = mName;
      return str;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public long getRecipientId()
  {
    try
    {
      long l = mRecipientId;
      return l;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public boolean getSendToVoicemail()
  {
    return mSendToVoicemail;
  }
  
  public String getTag()
  {
    try
    {
      String str = mPhoneTag;
      return str;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public Uri getUri()
  {
    try
    {
      Uri localUri = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, mPersonId);
      return localUri;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public long getYellowPageId()
  {
    try
    {
      long l = mYellowPageId;
      return l;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public String getYellowPageThumbnailName()
  {
    try
    {
      String str = mYellowPageThumbnailName;
      return str;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public int hashCode()
  {
    return mCompareKey.hashCode();
  }
  
  public boolean isB2cNumber()
  {
    try
    {
      boolean bool = mIsB2cNumber;
      return bool;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public boolean isEmail()
  {
    try
    {
      boolean bool = mIsEmail;
      return bool;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public boolean isNumberModified()
  {
    return mNumberIsModified;
  }
  
  public boolean isPhoneNumber()
  {
    try
    {
      boolean bool = mIsPhoneNumber;
      return bool;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  /* Error */
  public boolean isYellowPageB2cNumber()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 228	com/android/mms/data/Contact:mContactMethodType	I
    //   6: istore_1
    //   7: iload_1
    //   8: iconst_4
    //   9: if_icmpne +9 -> 18
    //   12: iconst_1
    //   13: istore_2
    //   14: aload_0
    //   15: monitorexit
    //   16: iload_2
    //   17: ireturn
    //   18: iconst_0
    //   19: istore_2
    //   20: goto -6 -> 14
    //   23: astore_3
    //   24: aload_0
    //   25: monitorexit
    //   26: aload_3
    //   27: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	28	0	this	Contact
    //   6	4	1	i	int
    //   13	7	2	bool	boolean
    //   23	4	3	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   2	7	23	finally
  }
  
  public boolean isYellowPageNumber()
  {
    try
    {
      boolean bool = mIsYellowPageNumber;
      return bool;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public Contact load(boolean paramBoolean1, boolean paramBoolean2)
  {
    sContactCache.loadContact(this, paramBoolean1, paramBoolean2);
    return this;
  }
  
  public void setIsB2cNumber(boolean paramBoolean)
  {
    try
    {
      mIsB2cNumber = paramBoolean;
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public void setIsNumberModified(boolean paramBoolean)
  {
    mNumberIsModified = paramBoolean;
  }
  
  public void setName(String paramString)
  {
    try
    {
      mName = paramString;
      return;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  public void setNickname(String paramString)
  {
    try
    {
      mNick = paramString;
      return;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  /* Error */
  public void setNumber(String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aload_1
    //   4: putfield 168	com/android/mms/data/Contact:mNumber	Ljava/lang/String;
    //   7: aload_0
    //   8: aload_1
    //   9: invokestatic 533	com/android/mms/ui/MessageUtils:isPhoneNumber	(Ljava/lang/String;)Z
    //   12: putfield 517	com/android/mms/data/Contact:mIsPhoneNumber	Z
    //   15: aload_0
    //   16: getfield 517	com/android/mms/data/Contact:mIsPhoneNumber	Z
    //   19: ifeq +110 -> 129
    //   22: aload_1
    //   23: invokestatic 344	miui/telephony/PhoneNumberUtils$PhoneNumber:parse	(Ljava/lang/CharSequence;)Lmiui/telephony/PhoneNumberUtils$PhoneNumber;
    //   26: astore 4
    //   28: aload 4
    //   30: invokevirtual 353	miui/telephony/PhoneNumberUtils$PhoneNumber:getEffectiveNumber	()Ljava/lang/String;
    //   33: astore 5
    //   35: aload 4
    //   37: invokevirtual 536	miui/telephony/PhoneNumberUtils$PhoneNumber:getPrefix	()Ljava/lang/String;
    //   40: astore_3
    //   41: aload_3
    //   42: astore_2
    //   43: aload_3
    //   44: ifnonnull +6 -> 50
    //   47: ldc 87
    //   49: astore_2
    //   50: aload_0
    //   51: new 273	java/lang/StringBuilder
    //   54: dup
    //   55: invokespecial 274	java/lang/StringBuilder:<init>	()V
    //   58: aload_2
    //   59: invokevirtual 278	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   62: aload 5
    //   64: invokevirtual 278	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   67: invokevirtual 286	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   70: invokestatic 538	com/android/mms/data/Contact:removeSpaceForAddress	(Ljava/lang/String;)Ljava/lang/String;
    //   73: putfield 172	com/android/mms/data/Contact:mCompareKey	Ljava/lang/String;
    //   76: aload_0
    //   77: aload 4
    //   79: aload_0
    //   80: getfield 168	com/android/mms/data/Contact:mNumber	Ljava/lang/String;
    //   83: invokestatic 347	com/android/mms/data/Contact:normalizePhoneNumber	(Lmiui/telephony/PhoneNumberUtils$PhoneNumber;Ljava/lang/String;)Ljava/lang/String;
    //   86: putfield 482	com/android/mms/data/Contact:mMxPhoneNumber	Ljava/lang/String;
    //   89: aload 4
    //   91: invokevirtual 350	miui/telephony/PhoneNumberUtils$PhoneNumber:recycle	()V
    //   94: aload_0
    //   95: aload_1
    //   96: invokestatic 542	com/xiaomi/mms/utils/B2cMessageUtils:isB2cNumber	(Ljava/lang/String;)Z
    //   99: putfield 154	com/android/mms/data/Contact:mIsB2cNumber	Z
    //   102: aload_0
    //   103: getfield 154	com/android/mms/data/Contact:mIsB2cNumber	Z
    //   106: ifne +11 -> 117
    //   109: aload_0
    //   110: aload_1
    //   111: invokestatic 544	com/android/mms/ui/MessageUtils:isEmail	(Ljava/lang/String;)Z
    //   114: putfield 513	com/android/mms/data/Contact:mIsEmail	Z
    //   117: aload_0
    //   118: invokespecial 206	com/android/mms/data/Contact:notSynchronizedUpdateNameAndNumber	()V
    //   121: aload_0
    //   122: iconst_1
    //   123: putfield 323	com/android/mms/data/Contact:mNumberIsModified	Z
    //   126: aload_0
    //   127: monitorexit
    //   128: return
    //   129: aload_0
    //   130: aload_1
    //   131: putfield 172	com/android/mms/data/Contact:mCompareKey	Ljava/lang/String;
    //   134: aload_0
    //   135: aload_1
    //   136: putfield 482	com/android/mms/data/Contact:mMxPhoneNumber	Ljava/lang/String;
    //   139: goto -45 -> 94
    //   142: astore_1
    //   143: aload_0
    //   144: monitorexit
    //   145: aload_1
    //   146: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	147	0	this	Contact
    //   0	147	1	paramString	String
    //   42	17	2	str1	String
    //   40	4	3	str2	String
    //   26	64	4	localPhoneNumber	PhoneNumberUtils.PhoneNumber
    //   33	30	5	str3	String
    // Exception table:
    //   from	to	target	type
    //   2	41	142	finally
    //   50	94	142	finally
    //   94	117	142	finally
    //   117	126	142	finally
    //   129	139	142	finally
  }
  
  public void setRecipientId(long paramLong)
  {
    try
    {
      mRecipientId = paramLong;
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public String toString()
  {
    String str1;
    String str2;
    label24:
    String str3;
    label36:
    String str4;
    if (mNumber != null)
    {
      str1 = mNumber;
      if (mName == null) {
        break label135;
      }
      str2 = mName;
      if (mNick == null) {
        break label142;
      }
      str3 = mNick;
      if (mNameAndNumber == null) {
        break label149;
      }
      str4 = mNameAndNumber;
      label49:
      if (mLabel == null) {
        break label157;
      }
    }
    label135:
    label142:
    label149:
    label157:
    for (String str5 = mLabel;; str5 = "null")
    {
      return String.format("{ number=%s, name=%s, nickname=%s, nameAndNumber=%s, label=%s, person_id=%d, hash=%d method_id=%d }", new Object[] { str1, str2, str3, str4, str5, Long.valueOf(mPersonId), Integer.valueOf(hashCode()), Long.valueOf(mContactMethodId) });
      str1 = "null";
      break;
      str2 = "null";
      break label24;
      str3 = "null";
      break label36;
      str4 = "null";
      break label49;
    }
  }
  
  private static class ContactsCache
  {
    private static final String[] CALLER_ID_PROJECTION;
    private static final String[] EMAIL_PROJECTION = { "_id", "data4", "contact_presence", "contact_id", "display_name", "photo_id", "nickname", "send_to_voicemail", "raw_contact_id", "data1" };
    private static final Uri EMAIL_WITH_PRESENCE_URI;
    private static final Uri PHONES_WITH_PRESENCE_URI = ContactsContract.Data.CONTENT_URI;
    private static final String[] SELF_PROJECTION;
    static CharBuffer sStaticKeyBuffer = CharBuffer.allocate(5);
    private final HashMap<String, ArrayList<Contact>> mContactsHash = new HashMap();
    private final Context mContext;
    private AtomicInteger mLoadAllState = new AtomicInteger(0);
    private final TaskQueue mTaskQueue = new TaskQueue();
    
    static
    {
      CALLER_ID_PROJECTION = new String[] { "_id", "data1", "data3", "display_name", "contact_id", "contact_presence", "contact_status", "data4", "photo_id", "nickname", "send_to_voicemail", "raw_contact_id", "account_type" };
      SELF_PROJECTION = new String[] { "_id", "display_name" };
      EMAIL_WITH_PRESENCE_URI = ContactsContract.Data.CONTENT_URI;
    }
    
    private ContactsCache(Context paramContext)
    {
      mContext = paramContext;
    }
    
    private boolean contactChanged(Contact paramContact1, Contact paramContact2)
    {
      if (mContactMethodType != mContactMethodType) {}
      while ((!Contact.emptyIfNull(mNick).equals(Contact.emptyIfNull(mNick))) || (mContactMethodId != mContactMethodId) || (mPersonId != mPersonId) || (mPresenceResId != mPresenceResId) || (mSendToVoicemail != mSendToVoicemail) || (!Contact.emptyIfNull(mName).equals(Contact.emptyIfNull(mName))) || (!Contact.emptyIfNull(mLabel).equals(Contact.emptyIfNull(mLabel))) || (mPhotoId != mPhotoId) || (mIsYellowPageNumber != mIsYellowPageNumber) || (mIsB2cNumber != mIsB2cNumber) || (mPhoneTag != mPhoneTag) || (mPeopleReferenceUri != mPeopleReferenceUri)) {
        return true;
      }
      return false;
    }
    
    private boolean containSpecialCharacter(String paramString)
    {
      if (paramString == null) {}
      for (;;)
      {
        return false;
        char[] arrayOfChar = Contact.mSpecialCharacters;
        int j = arrayOfChar.length;
        int i = 0;
        while (i < j)
        {
          if (paramString.indexOf(arrayOfChar[i]) != -1) {
            return true;
          }
          i += 1;
        }
      }
    }
    
    private void fillB2cContact(Contact paramContact, String paramString)
    {
      if (!TextUtils.isEmpty(paramString)) {
        try
        {
          Contact.access$902(paramContact, 5);
          Contact.access$1602(paramContact, paramString);
          Contact.access$2002(paramContact, true);
          Contact.access$2602(paramContact, null);
          return;
        }
        finally {}
      }
    }
    
    private void fillB2cContactByYellowPage(Contact paramContact, YellowPage paramYellowPage)
    {
      try
      {
        Contact.access$902(paramContact, 4);
        Contact.access$1602(paramContact, paramYellowPage.getName());
        Contact.access$2002(paramContact, true);
        Contact.access$2602(paramContact, paramYellowPage.getThumbnailName());
        return;
      }
      finally {}
    }
    
    private boolean fillEmailTypeContact(Contact paramContact, Cursor paramCursor)
    {
      for (;;)
      {
        try
        {
          Contact.access$1202(paramContact, paramCursor.getLong(0));
          Contact.access$2802(paramContact, paramCursor.getLong(8));
          Contact.access$1302(paramContact, paramCursor.getLong(3));
          if (paramCursor.getInt(7) == 1)
          {
            bool = true;
            Contact.access$1502(paramContact, bool);
            Contact.access$1402(paramContact, getPresenceIconResourceId(paramCursor.getInt(2)));
            Contact.access$1802(paramContact, paramCursor.getLong(5));
            Contact.access$1002(paramContact, paramCursor.getString(6));
            String str2 = paramCursor.getString(1);
            String str1 = str2;
            if (TextUtils.isEmpty(str2)) {
              str1 = paramCursor.getString(4);
            }
            if (!TextUtils.isEmpty(str1))
            {
              Contact.access$1602(paramContact, str1);
              return true;
            }
            return false;
          }
        }
        finally {}
        boolean bool = false;
      }
    }
    
    private void fillPhoneTypeContact(Contact paramContact, Cursor paramCursor)
    {
      boolean bool = true;
      for (;;)
      {
        try
        {
          Contact.access$902(paramContact, 1);
          Contact.access$1202(paramContact, paramCursor.getLong(0));
          Contact.access$2802(paramContact, paramCursor.getLong(11));
          Contact.access$3002(paramContact, paramCursor.getString(12));
          Contact.access$1702(paramContact, paramCursor.getString(2));
          Contact.access$1602(paramContact, paramCursor.getString(3));
          Contact.access$1002(paramContact, paramCursor.getString(9));
          Contact.access$1302(paramContact, paramCursor.getLong(4));
          Contact.access$1802(paramContact, paramCursor.getLong(8));
          Contact.access$1402(paramContact, getPresenceIconResourceId(paramCursor.getInt(5)));
          Contact.access$2502(paramContact, paramCursor.getString(6));
          Contact.access$2902(paramContact, paramCursor.getString(7));
          if (paramCursor.getInt(10) == 1)
          {
            Contact.access$1502(paramContact, bool);
            return;
          }
        }
        finally {}
        bool = false;
      }
    }
    
    private void fillSelfContact(Contact paramContact, Cursor paramCursor)
    {
      try
      {
        Contact.access$1602(paramContact, paramCursor.getString(1));
        if (TextUtils.isEmpty(mName)) {
          Contact.access$1602(paramContact, mContext.getString(2131361808));
        }
        return;
      }
      finally {}
    }
    
    private void fillYellowPageContact(Contact paramContact, YellowPagePhone paramYellowPagePhone)
    {
      try
      {
        Contact.access$902(paramContact, 1);
        Contact.access$1602(paramContact, paramYellowPagePhone.getYellowPageName());
        Contact.access$2102(paramContact, paramYellowPagePhone.getTag());
        Contact.access$1902(paramContact, true);
        Contact.access$2702(paramContact, paramYellowPagePhone.getYellowPageId());
        return;
      }
      finally {}
    }
    
    private Contact get(String paramString, boolean paramBoolean)
    {
      String str = paramString;
      if (TextUtils.isEmpty(paramString)) {
        str = "";
      }
      return internalGet(str, paramBoolean);
    }
    
    private Contact getContactInfo(Contact paramContact)
    {
      if (mIsMe) {
        return getContactInfoForSelf();
      }
      if (B2cMessageUtils.isB2cNumber(paramContact)) {
        return getContactInfoForB2cNumber(paramContact.getNumber());
      }
      if (shouldMatchEmailField(mNumber)) {
        return getContactInfoForEmailAddress(mNumber);
      }
      return getContactInfoForPhoneNumber(mNumber);
    }
    
    private Contact getContactInfoForB2cNumber(String paramString)
    {
      Contact localContact = new Contact(paramString, null);
      Contact.access$902(localContact, 5);
      getYellowPageInfoForB2cContact(paramString, localContact);
      return localContact;
    }
    
    private Contact getContactInfoForEmailAddress(String paramString)
    {
      Contact localContact = new Contact(paramString, null);
      Contact.access$902(localContact, 2);
      Contact.access$2202(localContact, Uri.fromParts("mailto", paramString, null));
      paramString = SqliteWrapper.query(mContext, mContext.getContentResolver(), EMAIL_WITH_PRESENCE_URI, EMAIL_PROJECTION, "UPPER(data1)=UPPER(?) AND mimetype='vnd.android.cursor.item/email_v2'", new String[] { paramString }, null);
      if (paramString != null) {}
      try
      {
        boolean bool;
        do
        {
          if (!paramString.moveToNext()) {
            break;
          }
          bool = fillEmailTypeContact(localContact, paramString);
        } while (!bool);
        return localContact;
      }
      finally
      {
        paramString.close();
      }
    }
    
    private Contact getContactInfoForPhoneNumber(String paramString)
    {
      Contact localContact = new Contact(paramString, null);
      Contact.access$902(localContact, 1);
      Contact.access$2202(localContact, Uri.fromParts("tel", paramString, null));
      paramString = removePrefix(paramString);
      Object localObject = PhoneNumberUtils.toCallerIDMinMatch(paramString);
      if ((!TextUtils.isEmpty(paramString)) && (!TextUtils.isEmpty((CharSequence)localObject)))
      {
        localObject = mContext.getContentResolver().query(PHONES_WITH_PRESENCE_URI, CALLER_ID_PROJECTION, " Data._ID IN  (SELECT data_id FROM phone_lookup WHERE min_match = ?)", new String[] { localObject }, "length(data1)");
        if (localObject == null) {
          break label163;
        }
      }
      try
      {
        ((Cursor)localObject).moveToPosition(-1);
        while (((Cursor)localObject).moveToNext())
        {
          String str = removePrefix(((Cursor)localObject).getString(1));
          if ((!containSpecialCharacter(str)) && (PhoneNumberUtils.compareStrictly(MessageUtils.deleteSpecialIntlCode(paramString), MessageUtils.deleteSpecialIntlCode(str))))
          {
            fillPhoneTypeContact(localContact, (Cursor)localObject);
            return localContact;
          }
        }
        label163:
        return getYellowPageInfoForPhoneNumber(paramString, localContact);
      }
      finally
      {
        ((Cursor)localObject).close();
      }
    }
    
    private Contact getContactInfoForSelf()
    {
      Contact localContact = new Contact(true, null);
      Contact.access$902(localContact, 3);
      Cursor localCursor = mContext.getContentResolver().query(ContactsContract.Profile.CONTENT_URI, SELF_PROJECTION, null, null, null);
      if (localCursor == null)
      {
        Log.w("Contact", "getContactInfoForSelf() returned NULL cursor! contact uri used " + ContactsContract.Profile.CONTENT_URI);
        return localContact;
      }
      try
      {
        if (localCursor.moveToFirst()) {
          fillSelfContact(localContact, localCursor);
        }
        return localContact;
      }
      finally
      {
        localCursor.close();
      }
    }
    
    private int getPresenceIconResourceId(int paramInt)
    {
      if (paramInt != 0) {
        return ContactsContract.Presence.getPresenceIconResourceId(paramInt);
      }
      return 0;
    }
    
    private void getYellowPageInfoForB2cContact(String paramString, Contact paramContact)
    {
      YellowPage localYellowPage = MiPubUtils.getLocalYellowPage(MmsApp.getApp(), paramString);
      if (localYellowPage != null)
      {
        fillB2cContactByYellowPage(paramContact, localYellowPage);
        return;
      }
      fillB2cContact(paramContact, B2cMessageUtils.getB2cAddressDisplayName(mContext, paramString));
    }
    
    private Contact getYellowPageInfoForPhoneNumber(String paramString, Contact paramContact)
    {
      paramString = YellowPageUtils.getPhoneInfo(mContext, paramString, false);
      Contact.access$2102(paramContact, "");
      if (paramString != null)
      {
        if (paramString.isYellowPage()) {
          fillYellowPageContact(paramContact, paramString);
        }
      }
      else {
        return paramContact;
      }
      Contact.access$2102(paramContact, paramString.getTag());
      return paramContact;
    }
    
    private Contact internalGet(String paramString, boolean paramBoolean)
    {
      int i = 0;
      if (!paramBoolean) {}
      for (;;)
      {
        Object localObject;
        try
        {
          if (Telephony.Mms.isEmailAddress(paramString)) {
            break label209;
          }
          if (!MessageUtils.isAlias(paramString)) {
            break label211;
          }
        }
        finally {}
        ArrayList localArrayList = (ArrayList)mContactsHash.get(localObject);
        if (localArrayList != null)
        {
          int k = localArrayList.size();
          int j = 0;
          localObject = localArrayList;
          if (j < k)
          {
            localObject = (Contact)localArrayList.get(j);
            if (i != 0)
            {
              if (paramString.equals(mNumber))
              {
                return (Contact)localObject;
                localObject = simplifyPhoneNumber(paramString);
              }
            }
            else if (PhoneNumberUtils.compareStrictly(MessageUtils.deleteSpecialIntlCode(paramString), MessageUtils.deleteSpecialIntlCode(mNumber), false)) {
              return (Contact)localObject;
            }
            j += 1;
            continue;
          }
        }
        else
        {
          localArrayList = new ArrayList();
          mContactsHash.put(localObject, localArrayList);
          localObject = localArrayList;
        }
        if (paramBoolean) {}
        for (paramString = new Contact(true, null);; paramString = new Contact(paramString, null))
        {
          ((ArrayList)localObject).add(paramString);
          return paramString;
        }
        label209:
        i = 1;
        label211:
        if (i != 0) {
          localObject = paramString;
        }
      }
    }
    
    private boolean isAlphaNumber(String paramString)
    {
      if (!PhoneNumberUtils.isWellFormedSmsAddress(paramString)) {}
      do
      {
        do
        {
          return true;
        } while (MessageUtils.isAlias(paramString));
        paramString = PhoneNumberUtils.extractNetworkPortion(paramString);
      } while ((TextUtils.isEmpty(paramString)) || (paramString.length() < 3));
      return false;
    }
    
    private void loadContact(Contact paramContact, boolean paramBoolean1, boolean paramBoolean2)
    {
      int j = 0;
      for (;;)
      {
        if (paramBoolean1) {}
        try
        {
          boolean bool = mQueryPending;
          if (bool)
          {
            try
            {
              paramContact.wait();
            }
            catch (InterruptedException localInterruptedException) {}
          }
          else
          {
            int i = j;
            if (!mQueryPending) {
              if (!paramBoolean2)
              {
                i = j;
                if (!mIsStale) {}
              }
              else
              {
                Contact.access$402(paramContact, false);
                Contact.access$802(paramContact, true);
                i = 1;
              }
            }
            if (i != 0)
            {
              if (paramBoolean1) {
                updateContact(paramContact);
              }
            }
            else {
              return;
            }
          }
        }
        finally {}
      }
      pushTask(paramContact);
    }
    
    private String removePrefix(String paramString)
    {
      String str = paramString;
      if (paramString.length() >= 12)
      {
        str = paramString;
        if (ArrayUtils.contains(Contact.PREFIXES, paramString.substring(0, 5))) {
          str = paramString.substring(5);
        }
      }
      return str;
    }
    
    private boolean shouldMatchEmailField(String paramString)
    {
      return (Telephony.Mms.isEmailAddress(paramString)) || (isAlphaNumber(paramString));
    }
    
    private String simplifyPhoneNumber(String paramString)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      int i = paramString.length() - 1;
      while ((i >= 0) && (localStringBuilder.length() < 5))
      {
        char c = paramString.charAt(i);
        if (Character.isDigit(c)) {
          localStringBuilder.append(c);
        }
        i -= 1;
      }
      if (localStringBuilder.length() == 0) {
        return paramString;
      }
      return localStringBuilder.toString();
    }
    
    /* Error */
    private void updateContact(Contact paramContact)
    {
      // Byte code:
      //   0: aload_1
      //   1: ifnonnull +4 -> 5
      //   4: return
      //   5: aload_0
      //   6: aload_1
      //   7: invokespecial 588	com/android/mms/data/Contact$ContactsCache:getContactInfo	(Lcom/android/mms/data/Contact;)Lcom/android/mms/data/Contact;
      //   10: astore_2
      //   11: aload_1
      //   12: monitorenter
      //   13: aload_0
      //   14: aload_1
      //   15: aload_2
      //   16: invokespecial 590	com/android/mms/data/Contact$ContactsCache:contactChanged	(Lcom/android/mms/data/Contact;Lcom/android/mms/data/Contact;)Z
      //   19: ifeq +266 -> 285
      //   22: aload_1
      //   23: aload_2
      //   24: invokestatic 132	com/android/mms/data/Contact:access$1000	(Lcom/android/mms/data/Contact;)Ljava/lang/String;
      //   27: invokestatic 260	com/android/mms/data/Contact:access$1002	(Lcom/android/mms/data/Contact;Ljava/lang/String;)Ljava/lang/String;
      //   30: pop
      //   31: aload_1
      //   32: aload_2
      //   33: invokestatic 333	com/android/mms/data/Contact:access$2300	(Lcom/android/mms/data/Contact;)Ljava/lang/String;
      //   36: invokestatic 593	com/android/mms/data/Contact:access$2302	(Lcom/android/mms/data/Contact;Ljava/lang/String;)Ljava/lang/String;
      //   39: pop
      //   40: aload_1
      //   41: aload_2
      //   42: invokestatic 596	com/android/mms/data/Contact:access$2400	(Lcom/android/mms/data/Contact;)Ljava/lang/String;
      //   45: invokestatic 599	com/android/mms/data/Contact:access$2402	(Lcom/android/mms/data/Contact;Ljava/lang/String;)Ljava/lang/String;
      //   48: pop
      //   49: aload_1
      //   50: aload_2
      //   51: invokestatic 160	com/android/mms/data/Contact:access$1700	(Lcom/android/mms/data/Contact;)Ljava/lang/String;
      //   54: invokestatic 268	com/android/mms/data/Contact:access$1702	(Lcom/android/mms/data/Contact;Ljava/lang/String;)Ljava/lang/String;
      //   57: pop
      //   58: aload_1
      //   59: aload_2
      //   60: invokestatic 147	com/android/mms/data/Contact:access$1300	(Lcom/android/mms/data/Contact;)J
      //   63: invokestatic 238	com/android/mms/data/Contact:access$1302	(Lcom/android/mms/data/Contact;J)J
      //   66: pop2
      //   67: aload_1
      //   68: aload_2
      //   69: invokestatic 163	com/android/mms/data/Contact:access$1800	(Lcom/android/mms/data/Contact;)J
      //   72: invokestatic 253	com/android/mms/data/Contact:access$1802	(Lcom/android/mms/data/Contact;J)J
      //   75: pop2
      //   76: aload_1
      //   77: aload_2
      //   78: invokestatic 150	com/android/mms/data/Contact:access$1400	(Lcom/android/mms/data/Contact;)I
      //   81: invokestatic 250	com/android/mms/data/Contact:access$1402	(Lcom/android/mms/data/Contact;I)I
      //   84: pop
      //   85: aload_1
      //   86: aload_2
      //   87: invokestatic 602	com/android/mms/data/Contact:access$2500	(Lcom/android/mms/data/Contact;)Ljava/lang/String;
      //   90: invokestatic 271	com/android/mms/data/Contact:access$2502	(Lcom/android/mms/data/Contact;Ljava/lang/String;)Ljava/lang/String;
      //   93: pop
      //   94: aload_1
      //   95: aload_2
      //   96: invokestatic 166	com/android/mms/data/Contact:access$1900	(Lcom/android/mms/data/Contact;)Z
      //   99: invokestatic 295	com/android/mms/data/Contact:access$1902	(Lcom/android/mms/data/Contact;Z)Z
      //   102: pop
      //   103: aload_1
      //   104: aload_2
      //   105: invokestatic 169	com/android/mms/data/Contact:access$2000	(Lcom/android/mms/data/Contact;)Z
      //   108: invokestatic 206	com/android/mms/data/Contact:access$2002	(Lcom/android/mms/data/Contact;Z)Z
      //   111: pop
      //   112: aload_1
      //   113: aload_2
      //   114: invokestatic 605	com/android/mms/data/Contact:access$2600	(Lcom/android/mms/data/Contact;)Ljava/lang/String;
      //   117: invokestatic 209	com/android/mms/data/Contact:access$2602	(Lcom/android/mms/data/Contact;Ljava/lang/String;)Ljava/lang/String;
      //   120: pop
      //   121: aload_1
      //   122: aload_2
      //   123: invokestatic 608	com/android/mms/data/Contact:access$2700	(Lcom/android/mms/data/Contact;)J
      //   126: invokestatic 302	com/android/mms/data/Contact:access$2702	(Lcom/android/mms/data/Contact;J)J
      //   129: pop2
      //   130: aload_1
      //   131: aload_2
      //   132: invokestatic 144	com/android/mms/data/Contact:access$1200	(Lcom/android/mms/data/Contact;)J
      //   135: invokestatic 232	com/android/mms/data/Contact:access$1202	(Lcom/android/mms/data/Contact;J)J
      //   138: pop2
      //   139: aload_1
      //   140: aload_2
      //   141: invokestatic 611	com/android/mms/data/Contact:access$2800	(Lcom/android/mms/data/Contact;)J
      //   144: invokestatic 235	com/android/mms/data/Contact:access$2802	(Lcom/android/mms/data/Contact;J)J
      //   147: pop2
      //   148: aload_1
      //   149: aload_2
      //   150: invokestatic 128	com/android/mms/data/Contact:access$900	(Lcom/android/mms/data/Contact;)I
      //   153: invokestatic 198	com/android/mms/data/Contact:access$902	(Lcom/android/mms/data/Contact;I)I
      //   156: pop
      //   157: aload_1
      //   158: aload_2
      //   159: invokestatic 614	com/android/mms/data/Contact:access$2900	(Lcom/android/mms/data/Contact;)Ljava/lang/String;
      //   162: invokestatic 274	com/android/mms/data/Contact:access$2902	(Lcom/android/mms/data/Contact;Ljava/lang/String;)Ljava/lang/String;
      //   165: pop
      //   166: aload_1
      //   167: aload_2
      //   168: invokestatic 157	com/android/mms/data/Contact:access$1600	(Lcom/android/mms/data/Contact;)Ljava/lang/String;
      //   171: invokestatic 202	com/android/mms/data/Contact:access$1602	(Lcom/android/mms/data/Contact;Ljava/lang/String;)Ljava/lang/String;
      //   174: pop
      //   175: aload_1
      //   176: aload_2
      //   177: invokestatic 154	com/android/mms/data/Contact:access$1500	(Lcom/android/mms/data/Contact;)Z
      //   180: invokestatic 244	com/android/mms/data/Contact:access$1502	(Lcom/android/mms/data/Contact;Z)Z
      //   183: pop
      //   184: aload_1
      //   185: aload_2
      //   186: invokestatic 617	com/android/mms/data/Contact:access$3000	(Lcom/android/mms/data/Contact;)Ljava/lang/String;
      //   189: invokestatic 265	com/android/mms/data/Contact:access$3002	(Lcom/android/mms/data/Contact;Ljava/lang/String;)Ljava/lang/String;
      //   192: pop
      //   193: aload_1
      //   194: aload_2
      //   195: invokestatic 172	com/android/mms/data/Contact:access$2100	(Lcom/android/mms/data/Contact;)Ljava/lang/String;
      //   198: invokestatic 292	com/android/mms/data/Contact:access$2102	(Lcom/android/mms/data/Contact;Ljava/lang/String;)Ljava/lang/String;
      //   201: pop
      //   202: aload_1
      //   203: aload_2
      //   204: invokestatic 176	com/android/mms/data/Contact:access$2200	(Lcom/android/mms/data/Contact;)Landroid/net/Uri;
      //   207: invokestatic 361	com/android/mms/data/Contact:access$2202	(Lcom/android/mms/data/Contact;Landroid/net/Uri;)Landroid/net/Uri;
      //   210: pop
      //   211: aload_1
      //   212: invokestatic 620	com/android/mms/data/Contact:access$3100	(Lcom/android/mms/data/Contact;)V
      //   215: aload_1
      //   216: invokestatic 333	com/android/mms/data/Contact:access$2300	(Lcom/android/mms/data/Contact;)Ljava/lang/String;
      //   219: invokestatic 194	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
      //   222: ifne +63 -> 285
      //   225: invokestatic 624	com/android/mms/data/Contact:access$3200	()Ljava/util/HashSet;
      //   228: astore_2
      //   229: aload_2
      //   230: monitorenter
      //   231: invokestatic 624	com/android/mms/data/Contact:access$3200	()Ljava/util/HashSet;
      //   234: invokevirtual 630	java/util/HashSet:clone	()Ljava/lang/Object;
      //   237: checkcast 626	java/util/HashSet
      //   240: astore_3
      //   241: aload_2
      //   242: monitorexit
      //   243: aload_3
      //   244: invokevirtual 634	java/util/HashSet:iterator	()Ljava/util/Iterator;
      //   247: astore_2
      //   248: aload_2
      //   249: invokeinterface 639 1 0
      //   254: ifeq +31 -> 285
      //   257: aload_2
      //   258: invokeinterface 642 1 0
      //   263: checkcast 644	com/android/mms/data/Contact$UpdateListener
      //   266: aload_1
      //   267: invokeinterface 647 2 0
      //   272: goto -24 -> 248
      //   275: astore_2
      //   276: aload_1
      //   277: monitorexit
      //   278: aload_2
      //   279: athrow
      //   280: astore_3
      //   281: aload_2
      //   282: monitorexit
      //   283: aload_3
      //   284: athrow
      //   285: aload_1
      //   286: iconst_0
      //   287: invokestatic 551	com/android/mms/data/Contact:access$802	(Lcom/android/mms/data/Contact;Z)Z
      //   290: pop
      //   291: aload_1
      //   292: invokevirtual 650	java/lang/Object:notifyAll	()V
      //   295: aload_1
      //   296: monitorexit
      //   297: return
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	298	0	this	ContactsCache
      //   0	298	1	paramContact	Contact
      //   275	7	2	localObject2	Object
      //   240	4	3	localHashSet	HashSet
      //   280	4	3	localObject3	Object
      // Exception table:
      //   from	to	target	type
      //   13	231	275	finally
      //   243	248	275	finally
      //   248	272	275	finally
      //   276	278	275	finally
      //   283	285	275	finally
      //   285	297	275	finally
      //   231	243	280	finally
      //   281	283	280	finally
    }
    
    public Contact get(String paramString)
    {
      return get(paramString, false);
    }
    
    public List<Contact> getContactInfoForPhoneUris(Parcelable[] paramArrayOfParcelable)
    {
      if (paramArrayOfParcelable.length == 0) {
        return null;
      }
      Object localObject1 = new StringBuilder();
      int j = 1;
      int m = paramArrayOfParcelable.length;
      int i = 0;
      Object localObject2;
      if (i < m)
      {
        localObject2 = (Uri)paramArrayOfParcelable[i];
        int k = j;
        if ("content".equals(((Uri)localObject2).getScheme()))
        {
          if (j == 0) {
            break label83;
          }
          k = 0;
          ((StringBuilder)localObject1).append(((Uri)localObject2).getLastPathSegment());
        }
        for (;;)
        {
          i += 1;
          j = k;
          break;
          label83:
          ((StringBuilder)localObject1).append(',').append(((Uri)localObject2).getLastPathSegment());
          k = j;
        }
      }
      if (j != 0) {
        return null;
      }
      paramArrayOfParcelable = null;
      if (((StringBuilder)localObject1).length() > 0)
      {
        paramArrayOfParcelable = "_id IN (" + ((StringBuilder)localObject1).toString() + ")";
        paramArrayOfParcelable = mContext.getContentResolver().query(PHONES_WITH_PRESENCE_URI, CALLER_ID_PROJECTION, paramArrayOfParcelable, null, null);
      }
      if (paramArrayOfParcelable == null) {
        return null;
      }
      localObject1 = new ArrayList();
      try
      {
        if (paramArrayOfParcelable.moveToNext())
        {
          localObject2 = Contact.get(paramArrayOfParcelable.getString(1));
          fillPhoneTypeContact((Contact)localObject2, paramArrayOfParcelable);
          ((List)localObject1).add(localObject2);
        }
        return localList;
      }
      finally
      {
        paramArrayOfParcelable.close();
      }
    }
    
    void loadAll()
    {
      if (mLoadAllState.getAndSet(1) == 1) {
        return;
      }
      long l = System.currentTimeMillis();
      int i = 0;
      int j = 0;
      HashSet localHashSet = new HashSet();
      Object localObject1 = mContext.getContentResolver().query(Uri.withAppendedPath(Telephony.MmsSms.CONTENT_URI, "canonical-addresses"), null, null, null, null);
      int k;
      if (localObject1 != null)
      {
        k = ((Cursor)localObject1).getColumnIndex("address");
        for (;;)
        {
          try
          {
            ((Cursor)localObject1).moveToPosition(-1);
            if (!((Cursor)localObject1).moveToNext()) {
              break;
            }
            String str2 = ((Cursor)localObject1).getString(k);
            if (TextUtils.isEmpty(str2)) {
              LogTag.error("loadAll: Unexpected empty number in recipient query.", new Object[0]);
            } else {
              localHashSet.add(internalGet(str3, false));
            }
          }
          finally
          {
            ((Cursor)localObject1).close();
          }
        }
        ((Cursor)localObject1).close();
      }
      localObject1 = mContext.getContentResolver().query(Uri.withAppendedPath(Telephony.MmsSms.CONTENT_URI, "private-addresses"), null, null, null, null);
      if (localObject1 != null)
      {
        k = ((Cursor)localObject1).getColumnIndex("address");
        for (;;)
        {
          try
          {
            ((Cursor)localObject1).moveToPosition(-1);
            if (!((Cursor)localObject1).moveToNext()) {
              break;
            }
            String str4 = ((Cursor)localObject1).getString(k);
            if (TextUtils.isEmpty(str4)) {
              LogTag.error("loadAll: Unexpected empty number in recipient query.", new Object[0]);
            } else {
              localHashSet.add(internalGet(str5, false));
            }
          }
          finally
          {
            ((Cursor)localObject1).close();
          }
        }
        ((Cursor)localObject1).close();
      }
      Object localObject17 = mContext.getContentResolver().query(ContactsContract.Data.CONTENT_URI, CALLER_ID_PROJECTION, "mimetype='vnd.android.cursor.item/phone_v2'", null, "length(data1)");
      Object localObject18;
      Object localObject19;
      Object localObject9;
      if (localObject17 != null)
      {
        try
        {
          i = ((Cursor)localObject17).getCount();
          ((Cursor)localObject17).moveToPosition(-1);
          for (;;)
          {
            if (!((Cursor)localObject17).moveToNext()) {
              break label607;
            }
            localObject1 = ((Cursor)localObject17).getString(1);
            if (!TextUtils.isEmpty((CharSequence)localObject1)) {
              break;
            }
            LogTag.error("loadAll: Unexpected empty number in phone query.", new Object[0]);
          }
          localObject18 = removePrefix(str1);
        }
        finally
        {
          ((Cursor)localObject17).close();
        }
        Object localObject2 = simplifyPhoneNumber((String)localObject18);
        for (;;)
        {
          try
          {
            localObject2 = (ArrayList)mContactsHash.get(localObject2);
            if (localObject2 == null) {
              break;
            }
            localIterator2 = ((ArrayList)localObject2).iterator();
          }
          finally
          {
            try
            {
              Iterator localIterator2;
              Contact.access$402((Contact)localObject19, false);
              fillPhoneTypeContact((Contact)localObject19, (Cursor)localObject17);
              localHashSet.remove(localObject19);
              continue;
            }
            finally {}
            localObject3 = finally;
          }
          if (!localIterator2.hasNext()) {
            break;
          }
          localObject19 = (Contact)localIterator2.next();
          if (localHashSet.contains(localObject19))
          {
            localObject2 = ((Contact)localObject19).getNumber();
            if (!shouldMatchEmailField((String)localObject2))
            {
              localObject9 = removePrefix((String)localObject2);
              boolean bool;
              if (Build.IS_CTA_BUILD)
              {
                localObject2 = localObject9;
                if (((String)localObject9).length() >= 12)
                {
                  localObject2 = localObject9;
                  if (((String)localObject9).startsWith("86")) {
                    localObject2 = ((String)localObject9).substring(2, ((String)localObject9).length());
                  }
                }
                bool = PhoneNumberUtils.compareStrictly((String)localObject2, (String)localObject18, false);
                if (!bool) {}
              }
              else
              {
                bool = PhoneNumberUtils.compareLoosely((String)localObject9, (String)localObject18);
              }
            }
          }
        }
        label607:
        ((Cursor)localObject17).close();
      }
      Object localObject5 = localHashSet.iterator();
      localObject17 = new HashSet();
      while (((Iterator)localObject5).hasNext())
      {
        localObject9 = (Contact)((Iterator)localObject5).next();
        if (B2cMessageUtils.isB2cNumber((Contact)localObject9)) {
          ((HashSet)localObject17).add(((Contact)localObject9).getNumber());
        }
      }
      localObject5 = null;
      try
      {
        localObject9 = MiPubUtils.getLocalYellowPages(mContext, (Set)localObject17);
        localObject5 = localObject9;
      }
      catch (Exception localException1)
      {
        for (;;)
        {
          try
          {
            Contact.access$402((Contact)localObject9, false);
            fillB2cContactByYellowPage((Contact)localObject9, (YellowPage)((HashMap)localObject5).get(localObject19));
            ((Iterator)localObject18).remove();
            ((HashSet)localObject17).remove(localObject19);
            continue;
          }
          finally {}
          localException1 = localException1;
          LogTag.debug("Exception is thrown when getting yellow page: %s", new Object[] { localException1.toString() });
        }
      }
      if ((localObject5 != null) && (((HashMap)localObject5).size() > 0))
      {
        localObject18 = localHashSet.iterator();
        while (((Iterator)localObject18).hasNext())
        {
          localObject9 = (Contact)((Iterator)localObject18).next();
          localObject19 = ((Contact)localObject9).getNumber();
          if (!((HashMap)localObject5).containsKey(localObject19)) {
            break;
          }
        }
      }
      Object localObject7 = new ArrayList();
      Object localObject10 = ((HashSet)localObject17).iterator();
      while (((Iterator)localObject10).hasNext()) {
        ((ArrayList)localObject7).add(((Iterator)localObject10).next());
      }
      localObject7 = B2cMessageUtils.getB2cAddessDisplayNames(mContext, (List)localObject7);
      if ((localObject7 != null) && (((HashMap)localObject7).size() > 0))
      {
        localObject10 = localHashSet.iterator();
        while (((Iterator)localObject10).hasNext())
        {
          localObject17 = (Contact)((Iterator)localObject10).next();
          localObject18 = ((Contact)localObject17).getNumber();
          if (((HashMap)localObject7).containsKey(localObject18))
          {
            Contact.access$402((Contact)localObject17, false);
            fillB2cContact((Contact)localObject17, (String)((HashMap)localObject7).get(localObject18));
            ((Iterator)localObject10).remove();
          }
        }
      }
      localObject7 = mContext.getContentResolver().query(ContactsContract.Data.CONTENT_URI, EMAIL_PROJECTION, "mimetype='vnd.android.cursor.item/email_v2'", null, null);
      if (localObject7 != null)
      {
        try
        {
          j = ((Cursor)localObject7).getCount();
          ((Cursor)localObject7).moveToPosition(-1);
          for (;;)
          {
            if (!((Cursor)localObject7).moveToNext()) {
              break label1195;
            }
            localObject10 = ((Cursor)localObject7).getString(9);
            if (!TextUtils.isEmpty((CharSequence)localObject10)) {
              break;
            }
            LogTag.error("loadAll: Unexpected empty email in email query.", new Object[0]);
          }
        }
        finally
        {
          ((Cursor)localObject7).close();
        }
        do
        {
          try
          {
            localObject12 = (ArrayList)mContactsHash.get(localObject11);
            if (localObject12 == null) {
              break;
            }
            localObject17 = ((ArrayList)localObject12).iterator();
          }
          finally
          {
            try
            {
              for (;;)
              {
                Object localObject12;
                Contact.access$402((Contact)localObject12, false);
                fillEmailTypeContact((Contact)localObject12, (Cursor)localObject7);
                localHashSet.remove(localObject12);
              }
            }
            finally {}
            localObject13 = finally;
          }
          if (!((Iterator)localObject17).hasNext()) {
            break;
          }
          localObject12 = (Contact)((Iterator)localObject17).next();
        } while ((!localHashSet.contains(localObject12)) || (!shouldMatchEmailField(((Contact)localObject12).getNumber())));
        label1195:
        ((Cursor)localObject7).close();
      }
      Object localObject14 = new ArrayList();
      localObject7 = ((HashSet)localObject16).iterator();
      while (((Iterator)localObject7).hasNext())
      {
        localObject17 = ((Contact)((Iterator)localObject7).next()).getNumber();
        if (!shouldMatchEmailField((String)localObject17)) {
          ((List)localObject14).add(localObject17);
        }
      }
      localObject7 = null;
      try
      {
        localObject14 = YellowPageUtils.getLocalYellowPagePhones(mContext, (List)localObject14);
        localObject7 = localObject14;
      }
      catch (Exception localException2)
      {
        for (;;)
        {
          try
          {
            Contact.access$402((Contact)localObject14, false);
            fillYellowPageContact((Contact)localObject14, (YellowPagePhone)((HashMap)localObject7).get(localObject18));
            ((Iterator)localObject17).remove();
            continue;
          }
          finally {}
          localException2 = localException2;
          LogTag.debug("Exception is thrown when getting yellow page phones: %s", new Object[] { localException2.toString() });
        }
      }
      if ((localObject7 != null) && (((HashMap)localObject7).size() > 0))
      {
        localObject17 = ((HashSet)localObject16).iterator();
        while (((Iterator)localObject17).hasNext())
        {
          localObject14 = (Contact)((Iterator)localObject17).next();
          localObject18 = ((Contact)localObject14).getNumber();
          if (!((HashMap)localObject7).containsKey(localObject18)) {
            break;
          }
        }
      }
      Iterator localIterator1 = ((HashSet)localObject16).iterator();
      while (localIterator1.hasNext()) {
        synchronized ((Contact)localIterator1.next())
        {
          Contact.access$402(???, false);
          ???.setToUnknown();
        }
      }
      LogTag.debug("Contact cache loading time = %d. %d phone contacts, %d email contacts, %d sp contacts and %d cleared", new Object[] { Long.valueOf(System.currentTimeMillis() - l), Integer.valueOf(i), Integer.valueOf(j), Integer.valueOf(0), Integer.valueOf(((HashSet)localObject16).size()) });
      mLoadAllState.set(0);
    }
    
    public void pushTask(Contact paramContact)
    {
      mTaskQueue.push(paramContact);
    }
    
    ContactList searchForContacts(String paramString)
    {
      ContactList localContactList1 = new ContactList();
      paramString = mContext.getContentResolver().query(ContactsContract.Data.CONTENT_URI, CALLER_ID_PROJECTION, "mimetype=? AND (display_name LIKE ? OR data1 LIKE ?)", new String[] { "vnd.android.cursor.item/phone_v2", paramString, paramString }, "length(data1)");
      if (paramString != null)
      {
        try
        {
          paramString.moveToPosition(-1);
          while (paramString.moveToNext())
          {
            ??? = paramString.getString(1);
            if (TextUtils.isEmpty((CharSequence)???)) {
              LogTag.error("loadAll: Unexpected empty number in phone query.", new Object[0]);
            } else {
              synchronized (internalGet((String)???, false))
              {
                Contact.access$402((Contact)???, false);
                fillPhoneTypeContact((Contact)???, paramString);
                ((ContactList)localObject1).add((Contact)???);
              }
            }
          }
        }
        finally
        {
          paramString.close();
        }
        paramString.close();
      }
      return localContactList2;
    }
    
    private static class TaskQueue
    {
      private final ArrayList<Contact> mThingsToLoad = new ArrayList();
      Thread mWorkerThread = new Thread(new Runnable()
      {
        public void run()
        {
          for (;;)
          {
            Contact localContact = null;
            synchronized (mThingsToLoad)
            {
              int i = mThingsToLoad.size();
              if (i == 0) {}
              try
              {
                mThingsToLoad.wait();
                if (mThingsToLoad.size() > 0) {
                  localContact = (Contact)mThingsToLoad.remove(0);
                }
                if (localContact == null) {
                  continue;
                }
                Contact.sContactCache.updateContact(localContact);
              }
              catch (InterruptedException localInterruptedException) {}
            }
          }
        }
      });
      
      public TaskQueue()
      {
        mWorkerThread.setName("SingleContactLoader");
        mWorkerThread.setPriority(1);
        mWorkerThread.start();
      }
      
      public void push(Contact paramContact)
      {
        synchronized (mThingsToLoad)
        {
          if (!mThingsToLoad.contains(paramContact))
          {
            mThingsToLoad.add(paramContact);
            mThingsToLoad.notify();
          }
          return;
        }
      }
    }
  }
  
  public static abstract interface UpdateListener
  {
    public abstract void onUpdate(Contact paramContact);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.data.Contact
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */