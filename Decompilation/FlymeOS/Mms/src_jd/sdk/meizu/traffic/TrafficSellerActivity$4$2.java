package sdk.meizu.traffic;

import sdk.meizu.traffic.auth.MzAuthException;

class TrafficSellerActivity$4$2
  implements Runnable
{
  TrafficSellerActivity$4$2(TrafficSellerActivity.4 param4, MzAuthException paramMzAuthException) {}
  
  public void run()
  {
    this$1.this$0.startActivityForResult(val$e.getLoginIntent(), 100);
  }
}

/* Location:
 * Qualified Name:     sdk.meizu.traffic.TrafficSellerActivity.4.2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */