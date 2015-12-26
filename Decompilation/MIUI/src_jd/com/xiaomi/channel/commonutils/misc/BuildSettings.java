package com.xiaomi.channel.commonutils.misc;

public class BuildSettings
{
  public static final boolean IsBetaBuild;
  public static final boolean IsDebugBuild;
  public static final boolean IsDefaultChannel;
  public static final boolean IsForYYBuild;
  public static final boolean IsLogableBuild;
  public static final boolean IsRCBuild;
  public static boolean IsTestBuild;
  private static int envType = 1;
  
  static
  {
    boolean bool2 = false;
    IsDefaultChannel = "@SHIP.TO.2A2FE0D7@".contains("2A2FE0D7");
    if ((IsDefaultChannel) || ("DEBUG".equalsIgnoreCase("@SHIP.TO.2A2FE0D7@"))) {}
    for (boolean bool1 = true;; bool1 = false)
    {
      IsDebugBuild = bool1;
      IsLogableBuild = "LOGABLE".equalsIgnoreCase("@SHIP.TO.2A2FE0D7@");
      IsForYYBuild = "@SHIP.TO.2A2FE0D7@".contains("YY");
      IsTestBuild = "@SHIP.TO.2A2FE0D7@".equalsIgnoreCase("TEST");
      IsBetaBuild = "BETA".equalsIgnoreCase("@SHIP.TO.2A2FE0D7@");
      bool1 = bool2;
      if ("@SHIP.TO.2A2FE0D7@" != null)
      {
        bool1 = bool2;
        if ("@SHIP.TO.2A2FE0D7@".startsWith("RC")) {
          bool1 = true;
        }
      }
      IsRCBuild = bool1;
      envType = 1;
      if (!"@SHIP.TO.2A2FE0D7@".equalsIgnoreCase("SANDBOX")) {
        break;
      }
      envType = 2;
      return;
    }
    if ("@SHIP.TO.2A2FE0D7@".equalsIgnoreCase("ONEBOX"))
    {
      envType = 3;
      return;
    }
  }
  
  public static boolean IsOneBoxBuild()
  {
    return envType == 3;
  }
  
  public static boolean IsSandBoxBuild()
  {
    return envType == 2;
  }
  
  public static int getEnvType()
  {
    return envType;
  }
  
  public static void setEnvType(int paramInt)
  {
    envType = paramInt;
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.channel.commonutils.misc.BuildSettings
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */