package com.ted.sdk.yellow.entry;

import java.util.ArrayList;
import java.util.List;

public class MessageItem$MessageMenu
{
  public static final int MENU_LEVEL_1 = 1;
  public static final int MENU_LEVEL_2 = 2;
  private String mAlert;
  private final int mCmdType;
  private final String mCommand;
  private String mExtendData;
  private final int mLevel;
  private List<MessageItem.SpItem> mSpList;
  private List<MessageMenu> mSubMenuList;
  private final String mTitle;
  
  MessageItem$MessageMenu(int paramInt1, String paramString1, int paramInt2, String paramString2, String paramString3)
  {
    mLevel = paramInt1;
    mTitle = paramString1;
    mCmdType = paramInt2;
    mCommand = paramString2;
    mExtendData = paramString3;
    if ((paramInt1 == 1) && (mSubMenuList == null)) {
      mSubMenuList = new ArrayList();
    }
    if ((paramInt1 == 2) && (mSpList == null)) {
      mSpList = new ArrayList();
    }
  }
  
  public int getCmdType()
  {
    return mCmdType;
  }
  
  public String getCommand()
  {
    return mCommand;
  }
  
  public String getExtendData()
  {
    return mExtendData;
  }
  
  public int getLevel()
  {
    return mLevel;
  }
  
  public List<MessageItem.SpItem> getSpList()
  {
    return mSpList;
  }
  
  public List<MessageMenu> getSubMenuList()
  {
    return mSubMenuList;
  }
  
  public String getTitle()
  {
    return mTitle;
  }
  
  public boolean hasSubMenu()
  {
    return (mSubMenuList != null) && (mSubMenuList.size() > 0);
  }
}

/* Location:
 * Qualified Name:     com.ted.sdk.yellow.entry.MessageItem.MessageMenu
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */