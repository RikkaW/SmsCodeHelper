package com.android.mms.ui;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Parcelable;
import android.widget.TextView;
import com.android.mms.data.Contact;
import com.google.android.collect.Lists;
import com.google.android.collect.Maps;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import miui.app.ProgressDialog;

class NewMessageActivity$16
  extends AsyncTask<Void, Void, Void>
{
  private HashMap<Contact, List<Uri>> mMap;
  
  NewMessageActivity$16(NewMessageActivity paramNewMessageActivity, Parcelable[] paramArrayOfParcelable) {}
  
  protected Void doInBackground(Void... arg1)
  {
    if ((val$uris != null) && (val$uris.length > 0) && (NewMessageActivity.access$2500(this$0) != null))
    {
      ArrayList localArrayList = Lists.newArrayList();
      Parcelable[] arrayOfParcelable = val$uris;
      int j = arrayOfParcelable.length;
      int i = 0;
      while (i < j)
      {
        ??? = arrayOfParcelable[i];
        if (isCancelled()) {
          return null;
        }
        Object localObject2 = (Uri)???;
        Contact localContact;
        if ("tel".equals(((Uri)localObject2).getScheme())) {
          localContact = Contact.get(((Uri)localObject2).getSchemeSpecificPart());
        }
        for (;;)
        {
          synchronized (NewMessageActivity.access$2500(this$0))
          {
            List localList = (List)NewMessageActivity.access$2500(this$0).get(localContact);
            ??? = localList;
            if (localList == null) {
              ??? = new ArrayList(2);
            }
            ???.add(localObject2);
            mMap.put(localContact, ???);
            ??? = Contact.getByPhoneUris((Parcelable[])localArrayList.toArray(new Parcelable[localArrayList.size()]));
            if (!isCancelled()) {
              break;
            }
            return null;
          }
          if ("content".equals(((Uri)localObject2).getScheme()))
          {
            localArrayList.add(localObject2);
          }
          else
          {
            ??? = ((Uri)localObject2).getSchemeSpecificPart();
            if (MessageUtils.isEmail(???))
            {
              localObject2 = Contact.get(???);
              ??? = null;
              synchronized (NewMessageActivity.access$2500(this$0))
              {
                if (NewMessageActivity.access$2500(this$0).containsKey(localObject2)) {
                  ??? = (List)NewMessageActivity.access$2500(this$0).get(localObject2);
                }
                mMap.put(localObject2, ???);
              }
            }
          }
        }
        if (??? != null)
        {
          localObject2 = ???.iterator();
          while (((Iterator)localObject2).hasNext())
          {
            localContact = (Contact)((Iterator)localObject2).next();
            if (isCancelled()) {
              return null;
            }
            ??? = null;
            synchronized (NewMessageActivity.access$2500(this$0))
            {
              if (NewMessageActivity.access$2500(this$0).containsKey(localContact)) {
                ??? = (List)NewMessageActivity.access$2500(this$0).get(localContact);
              }
              mMap.put(localContact, ???);
            }
          }
        }
        i += 1;
      }
    }
    return null;
  }
  
  protected void onPostExecute(Void arg1)
  {
    Iterator localIterator = mMap.keySet().iterator();
    while (localIterator.hasNext())
    {
      Contact localContact = (Contact)localIterator.next();
      NewMessageActivity.access$3500(this$0, localContact, localContact.getNumber());
      synchronized (NewMessageActivity.access$2500(this$0))
      {
        if (!NewMessageActivity.access$2500(this$0).containsKey(localContact)) {
          NewMessageActivity.access$2500(this$0).put(localContact, mMap.get(localContact));
        }
      }
    }
    mMap.clear();
    mMap = null;
    NewMessageActivity.access$2900(this$0);
    if (NewMessageActivity.access$3600(this$0).getVisibility() == 0) {
      NewMessageActivity.access$1900(this$0);
    }
    if ((NewMessageActivity.access$3400(this$0) != null) && (NewMessageActivity.access$3400(this$0).isShowing())) {
      NewMessageActivity.access$3400(this$0).dismiss();
    }
    NewMessageActivity.access$3402(this$0, null);
    NewMessageActivity.access$3702(this$0, null);
    NewMessageActivity.access$3800(this$0);
  }
  
  protected void onPreExecute()
  {
    NewMessageActivity.access$3402(this$0, new ProgressDialog(this$0));
    NewMessageActivity.access$3400(this$0).setMessage(this$0.getText(2131362038));
    NewMessageActivity.access$3400(this$0).setIndeterminate(true);
    NewMessageActivity.access$3400(this$0).setCancelable(false);
    NewMessageActivity.access$3400(this$0).show();
    NewMessageActivity.access$3000(this$0).setVisibility(8);
    mMap = Maps.newHashMap();
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.NewMessageActivity.16
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */