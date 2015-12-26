package com.android.mms.ui;

import android.content.Context;
import android.content.Intent;
import android.provider.CalendarContract.Events;
import android.text.TextUtils;
import com.android.mms.util.DateParseUtils;
import com.android.mms.util.DateParseUtils.EventDate;
import java.util.Date;
import java.util.List;

final class MessageUtils$14
  implements Runnable
{
  MessageUtils$14(MessageUtils.UriInfo paramUriInfo, MessageItem paramMessageItem, Context paramContext) {}
  
  public void run()
  {
    long l1 = 0L;
    boolean bool = true;
    Object localObject2 = DateParseUtils.parseDate(val$info.formatContent);
    if (localObject2 == null) {
      return;
    }
    Object localObject1;
    label84:
    Object localObject3;
    if (((List)localObject2).size() == 1)
    {
      localObject1 = (DateParseUtils.EventDate)((List)localObject2).get(0);
      if (localObject1 == null)
      {
        bool = false;
        localObject2 = new Intent("android.intent.action.INSERT").setData(CalendarContract.Events.CONTENT_URI).putExtra("allDay", bool);
        if (localObject1 != null) {
          break label166;
        }
        localObject2 = ((Intent)localObject2).putExtra("beginTime", l1).putExtra("endTime", 0);
        localObject1 = localObject2;
        if (val$messageItem != null)
        {
          localObject3 = val$messageItem.getBody();
          localObject1 = localObject2;
          if (!TextUtils.isEmpty((CharSequence)localObject3))
          {
            ((Intent)localObject2).putExtra("event_title", (String)localObject3);
            localObject1 = localObject2;
          }
        }
      }
    }
    for (;;)
    {
      MessageUtils.access$000(val$context, (Intent)localObject1);
      return;
      bool = ((DateParseUtils.EventDate)localObject1).isAllDayEvent();
      break;
      label166:
      l1 = ((DateParseUtils.EventDate)localObject1).getDate().getTime();
      break label84;
      if (((List)localObject2).size() == 2)
      {
        localObject1 = (DateParseUtils.EventDate)((List)localObject2).get(0);
        localObject2 = (DateParseUtils.EventDate)((List)localObject2).get(1);
        label228:
        long l2;
        if ((localObject1 == null) || (localObject2 == null))
        {
          bool = false;
          localObject3 = new Intent("android.intent.action.INSERT").setData(CalendarContract.Events.CONTENT_URI).putExtra("allDay", bool);
          if (localObject1 != null) {
            break label355;
          }
          l2 = 0L;
          label259:
          localObject1 = ((Intent)localObject3).putExtra("beginTime", l2);
          if (localObject2 != null) {
            break label367;
          }
        }
        for (;;)
        {
          localObject2 = ((Intent)localObject1).putExtra("endTime", l1);
          localObject1 = localObject2;
          if (val$messageItem == null) {
            break;
          }
          localObject3 = val$messageItem.getBody();
          localObject1 = localObject2;
          if (TextUtils.isEmpty((CharSequence)localObject3)) {
            break;
          }
          ((Intent)localObject2).putExtra("event_title", (String)localObject3);
          localObject1 = localObject2;
          break;
          if ((((DateParseUtils.EventDate)localObject1).isAllDayEvent()) && (((DateParseUtils.EventDate)localObject2).isAllDayEvent())) {
            break label228;
          }
          bool = false;
          break label228;
          label355:
          l2 = ((DateParseUtils.EventDate)localObject1).getDate().getTime();
          break label259;
          label367:
          l1 = ((DateParseUtils.EventDate)localObject2).getDate().getTime();
        }
      }
      localObject2 = new Intent("android.intent.action.INSERT").setData(CalendarContract.Events.CONTENT_URI).putExtra("allDay", true).putExtra("beginTime", 0).putExtra("endTime", 0);
      localObject1 = localObject2;
      if (val$messageItem != null)
      {
        localObject3 = val$messageItem.getBody();
        localObject1 = localObject2;
        if (!TextUtils.isEmpty((CharSequence)localObject3))
        {
          ((Intent)localObject2).putExtra("event_title", (String)localObject3);
          localObject1 = localObject2;
        }
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.MessageUtils.14
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */