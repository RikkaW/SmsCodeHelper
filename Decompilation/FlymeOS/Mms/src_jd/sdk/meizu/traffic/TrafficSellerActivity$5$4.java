package sdk.meizu.traffic;

import android.content.Intent;
import android.provider.ContactsContract.CommonDataKinds.Phone;

class TrafficSellerActivity$5$4
  implements Runnable
{
  TrafficSellerActivity$5$4(TrafficSellerActivity.5 param5) {}
  
  public void run()
  {
    Intent localIntent = new Intent("android.intent.action.PICK", ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
    this$1.this$0.startActivityForResult(localIntent, 1000);
  }
}

/* Location:
 * Qualified Name:     sdk.meizu.traffic.TrafficSellerActivity.5.4
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */