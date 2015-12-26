package com.android.mms.model;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SqliteWrapper;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.MimeTypeMap;
import com.android.mms.ContentRestrictionException;
import com.android.mms.dom.events.EventImpl;
import com.google.android.mms.MmsException;
import java.util.HashMap;
import java.util.Map;
import org.w3c.dom.events.Event;

public class AudioModel
  extends MediaModel
{
  private final HashMap<String, String> mExtras = new HashMap();
  
  public AudioModel(Context paramContext, Uri paramUri)
    throws MmsException
  {
    this(paramContext, null, null, paramUri);
    initModelFromUri(paramUri);
    checkContentRestriction();
  }
  
  public AudioModel(Context paramContext, String paramString1, String paramString2, Uri paramUri)
    throws MmsException
  {
    super(paramContext, "audio", paramString1, paramString2, paramUri);
  }
  
  private void initFromContentUri(Uri paramUri)
    throws MmsException
  {
    Object localObject = mContext.getContentResolver();
    Cursor localCursor = SqliteWrapper.query(mContext, (ContentResolver)localObject, paramUri, null, null, null, null);
    if (localCursor != null)
    {
      for (;;)
      {
        try
        {
          if (!localCursor.moveToFirst()) {
            break;
          }
          if (isMmsUri(paramUri))
          {
            paramUri = localCursor.getString(localCursor.getColumnIndexOrThrow("_data"));
            mContentType = localCursor.getString(localCursor.getColumnIndexOrThrow("ct"));
            setInternalSrc(paramUri.substring(paramUri.lastIndexOf('/') + 1));
            if (!TextUtils.isEmpty(mContentType)) {
              break label256;
            }
            throw new MmsException("Type of media is unknown.");
          }
        }
        finally
        {
          localCursor.close();
        }
        localObject = localCursor.getString(localCursor.getColumnIndexOrThrow("_data"));
        mContentType = localCursor.getString(localCursor.getColumnIndexOrThrow("mime_type"));
        paramUri = localCursor.getString(localCursor.getColumnIndexOrThrow("album"));
        if (!TextUtils.isEmpty(paramUri)) {
          mExtras.put("album", paramUri);
        }
        String str = localCursor.getString(localCursor.getColumnIndexOrThrow("artist"));
        paramUri = (Uri)localObject;
        if (!TextUtils.isEmpty(str))
        {
          mExtras.put("artist", str);
          paramUri = (Uri)localObject;
        }
      }
      throw new MmsException("Nothing found: " + paramUri);
      label256:
      localCursor.close();
      return;
    }
    throw new MmsException("Bad URI: " + paramUri);
  }
  
  private void initFromFile(Uri paramUri)
  {
    String str2 = paramUri.getPath();
    String str1 = str2;
    if (str2 != null) {
      str1 = str2.substring(str2.lastIndexOf('/') + 1);
    }
    setInternalSrc(str1);
    MimeTypeMap localMimeTypeMap = MimeTypeMap.getSingleton();
    str2 = MimeTypeMap.getFileExtensionFromUrl(mSrc);
    str1 = str2;
    if (TextUtils.isEmpty(str2))
    {
      int i = mSrc.lastIndexOf('.');
      str1 = str2;
      if (i >= 0) {
        str1 = mSrc.substring(i + 1);
      }
    }
    if (str1 == null) {}
    for (str1 = null;; str1 = localMimeTypeMap.getMimeTypeFromExtension(str1.toLowerCase()))
    {
      mContentType = str1;
      if (Log.isLoggable("Mms:app", 2)) {
        Log.v("Mms/media", "New AudioModel initFromFile created: mSrc=" + mSrc + " mContentType=" + mContentType + " mUri=" + paramUri);
      }
      return;
    }
  }
  
  private void initModelFromUri(Uri paramUri)
    throws MmsException
  {
    if (paramUri.getScheme().equals("content")) {
      initFromContentUri(paramUri);
    }
    for (;;)
    {
      initMediaDuration();
      return;
      if (paramUri.getScheme().equals("file")) {
        initFromFile(paramUri);
      }
    }
  }
  
  protected void checkContentRestriction()
    throws ContentRestrictionException
  {
    ContentRestrictionFactory.getContentRestriction().checkAudioContentType(mContentType);
  }
  
  public Map<String, ?> getExtras()
  {
    return mExtras;
  }
  
  public void handleEvent(Event paramEvent)
  {
    String str = paramEvent.getType();
    MediaModel.MediaAction localMediaAction = MediaModel.MediaAction.NO_ACTIVE_ACTION;
    if (str.equals("SmilMediaStart"))
    {
      localMediaAction = MediaModel.MediaAction.START;
      pauseMusicPlayer();
    }
    for (;;)
    {
      appendAction(localMediaAction);
      notifyModelChanged(false);
      return;
      if (str.equals("SmilMediaEnd"))
      {
        localMediaAction = MediaModel.MediaAction.STOP;
      }
      else if (str.equals("SmilMediaPause"))
      {
        localMediaAction = MediaModel.MediaAction.PAUSE;
      }
      else if (str.equals("SmilMediaSeek"))
      {
        localMediaAction = MediaModel.MediaAction.SEEK;
        mSeekTo = ((EventImpl)paramEvent).getSeekTo();
      }
    }
  }
  
  protected boolean isPlayable()
  {
    return true;
  }
}

/* Location:
 * Qualified Name:     com.android.mms.model.AudioModel
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */