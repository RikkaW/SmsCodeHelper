package com.xiaomi.smack;

public abstract interface ConnectionListener
{
  public abstract void connectionClosed(Connection paramConnection, int paramInt, Exception paramException);
  
  public abstract void connectionStarted(Connection paramConnection);
  
  public abstract void reconnectionFailed(Connection paramConnection, Exception paramException);
  
  public abstract void reconnectionSuccessful(Connection paramConnection);
}

/* Location:
 * Qualified Name:     com.xiaomi.smack.ConnectionListener
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */