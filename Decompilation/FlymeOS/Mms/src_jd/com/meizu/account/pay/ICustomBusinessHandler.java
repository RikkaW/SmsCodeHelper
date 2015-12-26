package com.meizu.account.pay;

public abstract interface ICustomBusinessHandler
{
  public abstract String handleAccountChange(String paramString);
  
  public abstract CustomBusinessResult handleCustomBusiness(String paramString1, String paramString2);
}

/* Location:
 * Qualified Name:     com.meizu.account.pay.ICustomBusinessHandler
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */