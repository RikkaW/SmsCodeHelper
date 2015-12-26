package com.meizu.account.pay;

public class PayResultCode
{
  public static final int PAY_ERROR_AUTH_ERROR = 4;
  public static final int PAY_ERROR_BAD_REQUEST = 3;
  public static final int PAY_ERROR_CANCEL = 2;
  public static final int PAY_ERROR_EXCEPTION = 101;
  public static final int PAY_ERROR_NETWORK_ERROR = 1;
  public static final int PAY_ERROR_SERVICE_NOT_AVAILABLE = 100;
  public static final int PAY_SUCCESS = 0;
  
  public static int fixCode(int paramInt)
  {
    if ((paramInt > 0) && (paramInt <= 4)) {
      return paramInt;
    }
    return 101;
  }
}

/* Location:
 * Qualified Name:     com.meizu.account.pay.PayResultCode
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */