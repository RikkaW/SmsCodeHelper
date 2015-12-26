package com.xiaomi.mms.mx.audio.player;

public class PlayerStatus
{
  public final int mDuration;
  public final int mProgress;
  public final int mType;
  
  public PlayerStatus(int paramInt)
  {
    this(paramInt, 0, 0);
  }
  
  public PlayerStatus(int paramInt1, int paramInt2, int paramInt3)
  {
    mType = paramInt1;
    mDuration = paramInt2;
    mProgress = paramInt3;
  }
  
  public String toString()
  {
    return String.format("Plat Status, type = %1$s, duration = %2$s, progress = %3$s", new Object[] { Integer.valueOf(mType), Integer.valueOf(mDuration), Integer.valueOf(mProgress) });
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mms.mx.audio.player.PlayerStatus
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */