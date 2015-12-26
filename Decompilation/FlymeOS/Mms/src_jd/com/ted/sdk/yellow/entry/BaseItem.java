package com.ted.sdk.yellow.entry;

import ant;
import com.ted.android.contacts.netparser.model.DealItem;
import com.ted.android.contacts.netparser.model.MenuItem;
import com.ted.android.contacts.netparser.model.NumItem;
import com.ted.android.contacts.netparser.model.NumItem.RelevantNumber;
import com.ted.android.contacts.netparser.model.SpItem;
import com.ted.sdk.yellow.util.LocationInfo;
import java.util.Iterator;
import java.util.List;

public class BaseItem
{
  protected final String mNumber;
  
  public BaseItem(String paramString)
  {
    mNumber = paramString;
  }
  
  private static ContactItem.ContactMenu buildContactMenu(int paramInt, MenuItem paramMenuItem)
  {
    String str2 = paramMenuItem.a();
    String str3 = paramMenuItem.g();
    String str4 = paramMenuItem.b();
    int i = ActionType.changToCmdType(paramMenuItem.e());
    String str5;
    if (i == 3)
    {
      str1 = LocationInfo.getInstance().getLatitude();
      str5 = LocationInfo.getInstance().getLongitude();
    }
    for (String str1 = ant.a(paramMenuItem.c(), str1, str5);; str1 = paramMenuItem.c()) {
      return new ContactItem.ContactMenu(paramInt, str2, str3, str4, i, str1, paramMenuItem.h());
    }
  }
  
  public static CallerIdItem parseToCallerIdItem(String paramString, NumItem paramNumItem)
  {
    if (paramNumItem == null) {
      return null;
    }
    return new CallerIdItem(paramString, paramNumItem.d(), paramNumItem.b(), paramNumItem.k(), paramNumItem.l(), paramNumItem.m());
  }
  
  public static ContactItem parseToContactMenuItem(String paramString, NumItem paramNumItem)
  {
    if (paramNumItem == null) {
      return null;
    }
    paramString = new ContactItem(paramString, paramNumItem.e());
    Object localObject1 = paramNumItem.i();
    if (localObject1 != null)
    {
      localObject1 = ((List)localObject1).iterator();
      break label171;
      label35:
      if (((Iterator)localObject1).hasNext()) {}
    }
    else
    {
      localObject1 = paramNumItem.p();
      if (localObject1 != null)
      {
        localObject1 = ((List)localObject1).iterator();
        label60:
        if (((Iterator)localObject1).hasNext()) {
          break label357;
        }
      }
      paramNumItem = paramNumItem.o();
      if (paramNumItem != null) {
        paramNumItem = paramNumItem.iterator();
      }
    }
    for (;;)
    {
      if (!paramNumItem.hasNext())
      {
        return paramString;
        Object localObject2 = (MenuItem)((Iterator)localObject1).next();
        ContactItem.ContactMenu localContactMenu = buildContactMenu(1, (MenuItem)localObject2);
        paramString.addMenu(localContactMenu);
        Object localObject3 = ((MenuItem)localObject2).f();
        if (localObject3 != null) {
          localObject3 = ((List)localObject3).iterator();
        }
        for (;;)
        {
          if (!((Iterator)localObject3).hasNext())
          {
            localObject2 = ((MenuItem)localObject2).d();
            if (localObject2 == null) {
              break label35;
            }
            localObject2 = ((List)localObject2).iterator();
            for (;;)
            {
              label171:
              if (!((Iterator)localObject2).hasNext()) {
                break label35;
              }
              localObject4 = (MenuItem)((Iterator)localObject2).next();
              localObject3 = buildContactMenu(2, (MenuItem)localObject4);
              localContactMenu.getSubMenuList().add(localObject3);
              localObject4 = ((MenuItem)localObject4).f();
              if (localObject4 == null) {
                break;
              }
              localObject4 = ((List)localObject4).iterator();
              while (((Iterator)localObject4).hasNext())
              {
                Object localObject5 = (SpItem)((Iterator)localObject4).next();
                localObject5 = new MessageItem.SpItem(((SpItem)localObject5).b(), ((SpItem)localObject5).c(), ((SpItem)localObject5).d(), ((SpItem)localObject5).a());
                ((ContactItem.ContactMenu)localObject3).getSpList().add(localObject5);
              }
            }
          }
          Object localObject4 = (SpItem)((Iterator)localObject3).next();
          localObject4 = new MessageItem.SpItem(((SpItem)localObject4).b(), ((SpItem)localObject4).c(), ((SpItem)localObject4).d(), ((SpItem)localObject4).a());
          localContactMenu.getSpList().add(localObject4);
        }
        label357:
        paramString.addDeal(new ContactItem.DealItem((DealItem)((Iterator)localObject1).next()));
        break label60;
      }
      paramString.addRelevantNumber(new ContactItem.RelevantNumber((NumItem.RelevantNumber)paramNumItem.next()));
    }
  }
  
  public static MessageItem parseToMessageMenuItem(String paramString, NumItem paramNumItem)
  {
    if (paramNumItem == null) {
      return null;
    }
    MessageItem localMessageItem = new MessageItem(paramString);
    paramString = paramNumItem.j();
    if (paramString == null) {
      return localMessageItem;
    }
    Object localObject2;
    int i;
    Object localObject1;
    label101:
    label147:
    Object localObject3;
    do
    {
      paramNumItem = paramString.iterator();
      while (!((Iterator)localObject2).hasNext())
      {
        do
        {
          do
          {
            if (!paramNumItem.hasNext()) {
              return localMessageItem;
            }
            localObject2 = (MenuItem)paramNumItem.next();
            i = ActionType.changToCmdType(((MenuItem)localObject2).e());
          } while (-1 == i);
          if (i != 3) {
            break;
          }
          paramString = LocationInfo.getInstance().getLatitude();
          localObject1 = LocationInfo.getInstance().getLongitude();
          paramString = ant.a(((MenuItem)localObject2).c(), paramString, (String)localObject1);
          localObject1 = new MessageItem.MessageMenu(1, ((MenuItem)localObject2).a(), i, paramString, ((MenuItem)localObject2).h());
          localMessageItem.addMenu((MessageItem.MessageMenu)localObject1);
          paramString = ((MenuItem)localObject2).d();
        } while (paramString == null);
        localObject2 = paramString.iterator();
      }
      localObject3 = (MenuItem)((Iterator)localObject2).next();
      i = ActionType.changToCmdType(((MenuItem)localObject3).e());
    } while (-1 == i);
    Object localObject4;
    if (i == 3)
    {
      paramString = LocationInfo.getInstance().getLatitude();
      localObject4 = LocationInfo.getInstance().getLongitude();
    }
    for (paramString = ant.a(((MenuItem)localObject3).c(), paramString, (String)localObject4);; paramString = ((MenuItem)localObject3).c())
    {
      paramString = new MessageItem.MessageMenu(2, ((MenuItem)localObject3).a(), i, paramString, ((MenuItem)localObject3).h());
      ((MessageItem.MessageMenu)localObject1).getSubMenuList().add(paramString);
      localObject3 = ((MenuItem)localObject3).f();
      if (localObject3 == null) {
        break;
      }
      localObject3 = ((List)localObject3).iterator();
      while (((Iterator)localObject3).hasNext())
      {
        localObject4 = (SpItem)((Iterator)localObject3).next();
        localObject4 = new MessageItem.SpItem(((SpItem)localObject4).b(), ((SpItem)localObject4).c(), ((SpItem)localObject4).d(), ((SpItem)localObject4).a());
        paramString.getSpList().add(localObject4);
      }
      break label147;
      paramString = ((MenuItem)localObject2).c();
      break label101;
    }
  }
  
  public String getNumber()
  {
    return mNumber;
  }
}

/* Location:
 * Qualified Name:     com.ted.sdk.yellow.entry.BaseItem
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */