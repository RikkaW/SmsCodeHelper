package com.android.mms.ui;

import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import com.google.android.collect.Lists;
import java.util.ArrayList;

class BookmarkFragment$SelectedMessage
{
  private MessageItem mSelectedBookmark;
  
  private BookmarkFragment$SelectedMessage(BookmarkFragment paramBookmarkFragment) {}
  
  public void copy()
  {
    ArrayList localArrayList = new ArrayList(1);
    localArrayList.add(mSelectedBookmark);
    MessageUtils.copyMessageTextToClipboard(BookmarkFragment.access$100(this$0), localArrayList);
  }
  
  public void delete()
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(BookmarkFragment.access$100(this$0));
    localBuilder.setTitle(2131362092);
    localBuilder.setMessage(2131362094);
    localBuilder.setIconAttribute(16843605);
    localBuilder.setCancelable(true);
    localBuilder.setPositiveButton(2131361891, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        BookmarkFragment.access$300(this$0).deleteBookmark(mSelectedBookmark);
      }
    });
    localBuilder.setNegativeButton(2131361892, null);
    localBuilder.show();
  }
  
  public void forwardMessage()
  {
    MessageUtils.forwardMessage(BookmarkFragment.access$100(this$0), Lists.newArrayList(new MessageItem[] { mSelectedBookmark }), false);
  }
  
  public String getMessage()
  {
    return mSelectedBookmark.getBody();
  }
  
  public void returnPickerResult()
  {
    Intent localIntent = new Intent();
    if ((mSelectedBookmark.isMms()) && (mSelectedBookmark.isDownloaded()))
    {
      localIntent.putExtra("msg_uri", mSelectedBookmark.getMessageUri());
      localIntent.putExtra("subject", mSelectedBookmark.getSubject());
    }
    for (;;)
    {
      if (BookmarkFragment.access$400(this$0)) {
        ((PickerActivity)BookmarkFragment.access$100(this$0)).returnPickerResult(localIntent);
      }
      return;
      String str2 = mSelectedBookmark.getBody();
      String str1 = str2;
      if (str2 == null) {
        str1 = "";
      }
      localIntent.putExtra("android.intent.extra.shortcut.NAME", str1);
    }
  }
  
  public void selectBookmark(MessageItem paramMessageItem)
  {
    mSelectedBookmark = paramMessageItem;
  }
  
  public void viewOrigin()
  {
    long l = mSelectedBookmark.getThreadId();
    Intent localIntent = ComposeMessageRouterActivity.createIntent(BookmarkFragment.access$100(this$0), l);
    localIntent.setData(ComposeMessageRouterActivity.createViewOrginDataUri(l, mSelectedBookmark.getMsgId()));
    ComposeMessageRouterActivity.route(BookmarkFragment.access$100(this$0), localIntent);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.BookmarkFragment.SelectedMessage
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */