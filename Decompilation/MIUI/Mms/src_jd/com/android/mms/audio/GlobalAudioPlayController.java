package com.android.mms.audio;

import com.android.mms.ui.MessageListItem;
import java.util.List;

public class GlobalAudioPlayController
{
  private static GlobalAudioPlayController sInstance;
  private List<MessageListItem> mPlayList;
  
  public static GlobalAudioPlayController get()
  {
    if (sInstance == null) {
      sInstance = new GlobalAudioPlayController();
    }
    return sInstance;
  }
  
  public void onStopPlay()
  {
    if ((mPlayList != null) && (mPlayList.size() > 0))
    {
      MessageListItem localMessageListItem = (MessageListItem)mPlayList.remove(0);
      if (localMessageListItem != null) {
        localMessageListItem.onMessageListItemClick();
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.audio.GlobalAudioPlayController
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */