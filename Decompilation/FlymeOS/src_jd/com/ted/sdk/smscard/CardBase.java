package com.ted.sdk.smscard;

import com.ted.android.smscard.ActionMenu;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class CardBase
  implements Serializable
{
  public static final int CARD_TYPE_BANK = 34;
  public static final int CARD_TYPE_CODE = 33;
  public static final int CARD_TYPE_DEFAULT = 1;
  public static final int CARD_TYPE_DIANPING = 14;
  public static final int CARD_TYPE_EXPRESS = 28;
  public static final int CARD_TYPE_E_BUSINESS = 17;
  public static final int CARD_TYPE_E_PAY = 18;
  public static final int CARD_TYPE_FETCH_EXPRESS = 36;
  public static final int CARD_TYPE_GROUP_TICKET = 32;
  public static final int CARD_TYPE_HOTEL = 25;
  public static final int CARD_TYPE_HOTEL_1 = 26;
  public static final int CARD_TYPE_HOTEL_2 = 27;
  public static final int CARD_TYPE_LIFE = 31;
  public static final int CARD_TYPE_MEITUAN = 29;
  public static final int CARD_TYPE_MOVIE = 15;
  public static final int CARD_TYPE_MOVIE_TICKET = 16;
  public static final int CARD_TYPE_OPERATOR = 35;
  public static final int CARD_TYPE_OTHER = 30;
  public static final int CARD_TYPE_PLANE_TICKET = 21;
  public static final int CARD_TYPE_PLANE_TICKET_1 = 22;
  public static final int CARD_TYPE_PLANE_TICKET_2 = 23;
  public static final int CARD_TYPE_PLANE_TICKET_POCKET = 37;
  public static final int CARD_TYPE_TAXI = 10;
  public static final int CARD_TYPE_TAXI_1 = 11;
  public static final int CARD_TYPE_TAXI_2 = 12;
  public static final int CARD_TYPE_TAXI_3 = 13;
  public static final int CARD_TYPE_TRAIN_TICKET = 19;
  public static final int CARD_TYPE_TRAIN_TICKET_1 = 20;
  public static final int CARD_TYPE_VIEW_SPOT = 24;
  public static final int DEFAULT_HIGHLIGHT = -16711936;
  private static final long serialVersionUID = 10L;
  protected HashMap<String, String> data;
  protected int mCardType;
  protected String mIconUri;
  protected String mMessage;
  protected String mSubTitleString;
  protected String mTitleString;
  protected ArrayList<ActionMenu> menus;
  
  public void addData(String paramString1, String paramString2)
  {
    if (data == null) {
      data = new HashMap();
    }
    if (data.containsKey(paramString1))
    {
      data.put(paramString1, (String)data.get(paramString1) + "," + paramString2);
      return;
    }
    data.put(paramString1, paramString2);
  }
  
  public void addMenu(int paramInt, ActionMenu paramActionMenu)
  {
    if (menus == null) {
      menus = new ArrayList();
    }
    Iterator localIterator = menus.iterator();
    if (!localIterator.hasNext()) {}
    for (;;)
    {
      menus.add(paramInt, paramActionMenu);
      return;
      ActionMenu localActionMenu = (ActionMenu)localIterator.next();
      if (!localActionMenu.getText().equalsIgnoreCase(paramActionMenu.getText())) {
        break;
      }
      menus.remove(localActionMenu);
    }
  }
  
  public void addMenu(ActionMenu paramActionMenu)
  {
    if (menus == null) {
      menus = new ArrayList();
    }
    menus.add(paramActionMenu);
  }
  
  public String find(String paramString)
  {
    return (String)data.get(paramString);
  }
  
  public int getCardType()
  {
    return mCardType;
  }
  
  public HashMap<String, String> getData()
  {
    return data;
  }
  
  public String getIconUri()
  {
    return mIconUri;
  }
  
  public List<ActionMenu> getMenuList()
  {
    return menus;
  }
  
  public String getMessage()
  {
    return mMessage;
  }
  
  public String getSubTitle()
  {
    return mSubTitleString;
  }
  
  public String getTitle()
  {
    return mTitleString;
  }
  
  public void setData(HashMap<String, String> paramHashMap)
  {
    if (paramHashMap != null) {
      data = paramHashMap;
    }
  }
  
  public void setMessage(String paramString)
  {
    mMessage = paramString;
  }
}

/* Location:
 * Qualified Name:     com.ted.sdk.smscard.CardBase
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */