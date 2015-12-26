package android.support.v7.internal.view;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.TypedArray;
import android.support.v4.view.ActionProvider;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.appcompat.R.styleable;
import android.support.v7.internal.view.menu.MenuItemImpl;
import android.support.v7.internal.view.menu.MenuItemWrapperICS;
import android.util.AttributeSet;
import android.util.Log;
import android.view.InflateException;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.SubMenu;
import android.view.View;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import org.xmlpull.v1.XmlPullParser;

public class SupportMenuInflater
  extends MenuInflater
{
  private static final Class<?>[] ACTION_PROVIDER_CONSTRUCTOR_SIGNATURE = ACTION_VIEW_CONSTRUCTOR_SIGNATURE;
  private static final Class<?>[] ACTION_VIEW_CONSTRUCTOR_SIGNATURE = { Context.class };
  private static final String LOG_TAG = "SupportMenuInflater";
  private static final int NO_ID = 0;
  private static final String XML_GROUP = "group";
  private static final String XML_ITEM = "item";
  private static final String XML_MENU = "menu";
  private final Object[] mActionProviderConstructorArguments;
  private final Object[] mActionViewConstructorArguments;
  private Context mContext;
  private Object mRealOwner;
  
  public SupportMenuInflater(Context paramContext)
  {
    super(paramContext);
    mContext = paramContext;
    mActionViewConstructorArguments = new Object[] { paramContext };
    mActionProviderConstructorArguments = mActionViewConstructorArguments;
  }
  
  private Object findRealOwner(Object paramObject)
  {
    if ((paramObject instanceof Activity)) {}
    while (!(paramObject instanceof ContextWrapper)) {
      return paramObject;
    }
    return findRealOwner(((ContextWrapper)paramObject).getBaseContext());
  }
  
  private Object getRealOwner()
  {
    if (mRealOwner == null) {
      mRealOwner = findRealOwner(mContext);
    }
    return mRealOwner;
  }
  
  private void parseMenu(XmlPullParser paramXmlPullParser, AttributeSet paramAttributeSet, Menu paramMenu)
  {
    MenuState localMenuState = new MenuState(paramMenu);
    int i = paramXmlPullParser.getEventType();
    label49:
    int k;
    int j;
    if (i == 2)
    {
      paramMenu = paramXmlPullParser.getName();
      if (paramMenu.equals("menu"))
      {
        i = paramXmlPullParser.next();
        paramMenu = null;
        int m = 0;
        k = i;
        j = 0;
        i = m;
        label65:
        if (j != 0) {
          return;
        }
      }
    }
    switch (k)
    {
    default: 
    case 2: 
    case 3: 
      for (;;)
      {
        k = paramXmlPullParser.next();
        break label65;
        throw new RuntimeException("Expecting menu, got " + paramMenu);
        j = paramXmlPullParser.next();
        i = j;
        if (j != 1) {
          break;
        }
        i = j;
        break label49;
        if (i == 0)
        {
          String str = paramXmlPullParser.getName();
          if (str.equals("group"))
          {
            localMenuState.readGroup(paramAttributeSet);
          }
          else if (str.equals("item"))
          {
            localMenuState.readItem(paramAttributeSet);
          }
          else if (str.equals("menu"))
          {
            parseMenu(paramXmlPullParser, paramAttributeSet, localMenuState.addSubMenuItem());
          }
          else
          {
            paramMenu = str;
            i = 1;
            continue;
            str = paramXmlPullParser.getName();
            if ((i != 0) && (str.equals(paramMenu)))
            {
              paramMenu = null;
              i = 0;
            }
            else if (str.equals("group"))
            {
              localMenuState.resetGroup();
            }
            else if (str.equals("item"))
            {
              if (!localMenuState.hasAddedItem()) {
                if ((itemActionProvider != null) && (itemActionProvider.hasSubMenu())) {
                  localMenuState.addSubMenuItem();
                } else {
                  localMenuState.addItem();
                }
              }
            }
            else if (str.equals("menu"))
            {
              j = 1;
            }
          }
        }
      }
    }
    throw new RuntimeException("Unexpected end of document");
  }
  
  /* Error */
  public void inflate(int paramInt, Menu paramMenu)
  {
    // Byte code:
    //   0: aload_2
    //   1: instanceof 172
    //   4: ifne +10 -> 14
    //   7: aload_0
    //   8: iload_1
    //   9: aload_2
    //   10: invokespecial 174	android/view/MenuInflater:inflate	(ILandroid/view/Menu;)V
    //   13: return
    //   14: aconst_null
    //   15: astore_3
    //   16: aconst_null
    //   17: astore 5
    //   19: aconst_null
    //   20: astore 4
    //   22: aload_0
    //   23: getfield 54	android/support/v7/internal/view/SupportMenuInflater:mContext	Landroid/content/Context;
    //   26: invokevirtual 178	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   29: iload_1
    //   30: invokevirtual 184	android/content/res/Resources:getLayout	(I)Landroid/content/res/XmlResourceParser;
    //   33: astore 6
    //   35: aload 6
    //   37: astore 4
    //   39: aload 6
    //   41: astore_3
    //   42: aload 6
    //   44: astore 5
    //   46: aload_0
    //   47: aload 6
    //   49: aload 6
    //   51: invokestatic 190	android/util/Xml:asAttributeSet	(Lorg/xmlpull/v1/XmlPullParser;)Landroid/util/AttributeSet;
    //   54: aload_2
    //   55: invokespecial 143	android/support/v7/internal/view/SupportMenuInflater:parseMenu	(Lorg/xmlpull/v1/XmlPullParser;Landroid/util/AttributeSet;Landroid/view/Menu;)V
    //   58: aload 6
    //   60: ifnull -47 -> 13
    //   63: aload 6
    //   65: invokeinterface 195 1 0
    //   70: return
    //   71: astore_2
    //   72: aload 4
    //   74: astore_3
    //   75: new 197	android/view/InflateException
    //   78: dup
    //   79: ldc -57
    //   81: aload_2
    //   82: invokespecial 202	android/view/InflateException:<init>	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   85: athrow
    //   86: astore_2
    //   87: aload_3
    //   88: ifnull +9 -> 97
    //   91: aload_3
    //   92: invokeinterface 195 1 0
    //   97: aload_2
    //   98: athrow
    //   99: astore_2
    //   100: aload 5
    //   102: astore_3
    //   103: new 197	android/view/InflateException
    //   106: dup
    //   107: ldc -57
    //   109: aload_2
    //   110: invokespecial 202	android/view/InflateException:<init>	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   113: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	114	0	this	SupportMenuInflater
    //   0	114	1	paramInt	int
    //   0	114	2	paramMenu	Menu
    //   15	88	3	localObject1	Object
    //   20	53	4	localObject2	Object
    //   17	84	5	localObject3	Object
    //   33	31	6	localXmlResourceParser	android.content.res.XmlResourceParser
    // Exception table:
    //   from	to	target	type
    //   22	35	71	org/xmlpull/v1/XmlPullParserException
    //   46	58	71	org/xmlpull/v1/XmlPullParserException
    //   22	35	86	finally
    //   46	58	86	finally
    //   75	86	86	finally
    //   103	114	86	finally
    //   22	35	99	java/io/IOException
    //   46	58	99	java/io/IOException
  }
  
  static class InflatedOnMenuItemClickListener
    implements MenuItem.OnMenuItemClickListener
  {
    private static final Class<?>[] PARAM_TYPES = { MenuItem.class };
    private Method mMethod;
    private Object mRealOwner;
    
    public InflatedOnMenuItemClickListener(Object paramObject, String paramString)
    {
      mRealOwner = paramObject;
      Class localClass = paramObject.getClass();
      try
      {
        mMethod = localClass.getMethod(paramString, PARAM_TYPES);
        return;
      }
      catch (Exception paramObject)
      {
        paramString = new InflateException("Couldn't resolve menu item onClick handler " + paramString + " in class " + localClass.getName());
        paramString.initCause((Throwable)paramObject);
        throw paramString;
      }
    }
    
    public boolean onMenuItemClick(MenuItem paramMenuItem)
    {
      try
      {
        if (mMethod.getReturnType() == Boolean.TYPE) {
          return ((Boolean)mMethod.invoke(mRealOwner, new Object[] { paramMenuItem })).booleanValue();
        }
        mMethod.invoke(mRealOwner, new Object[] { paramMenuItem });
        return true;
      }
      catch (Exception paramMenuItem)
      {
        throw new RuntimeException(paramMenuItem);
      }
    }
  }
  
  class MenuState
  {
    private static final int defaultGroupId = 0;
    private static final int defaultItemCategory = 0;
    private static final int defaultItemCheckable = 0;
    private static final boolean defaultItemChecked = false;
    private static final boolean defaultItemEnabled = true;
    private static final int defaultItemId = 0;
    private static final int defaultItemOrder = 0;
    private static final boolean defaultItemVisible = true;
    private int groupCategory;
    private int groupCheckable;
    private boolean groupEnabled;
    private int groupId;
    private int groupOrder;
    private boolean groupVisible;
    private ActionProvider itemActionProvider;
    private String itemActionProviderClassName;
    private String itemActionViewClassName;
    private int itemActionViewLayout;
    private boolean itemAdded;
    private char itemAlphabeticShortcut;
    private int itemCategoryOrder;
    private int itemCheckable;
    private boolean itemChecked;
    private boolean itemEnabled;
    private int itemIconResId;
    private int itemId;
    private String itemListenerMethodName;
    private char itemNumericShortcut;
    private int itemShowAsAction;
    private CharSequence itemTitle;
    private CharSequence itemTitleCondensed;
    private boolean itemVisible;
    private Menu menu;
    
    public MenuState(Menu paramMenu)
    {
      menu = paramMenu;
      resetGroup();
    }
    
    private char getShortcut(String paramString)
    {
      if (paramString == null) {
        return '\000';
      }
      return paramString.charAt(0);
    }
    
    private <T> T newInstance(String paramString, Class<?>[] paramArrayOfClass, Object[] paramArrayOfObject)
    {
      try
      {
        paramArrayOfClass = mContext.getClassLoader().loadClass(paramString).getConstructor(paramArrayOfClass).newInstance(paramArrayOfObject);
        return paramArrayOfClass;
      }
      catch (Exception paramArrayOfClass)
      {
        Log.w("SupportMenuInflater", "Cannot instantiate class: " + paramString, paramArrayOfClass);
      }
      return null;
    }
    
    private void setItem(MenuItem paramMenuItem)
    {
      int i = 1;
      Object localObject = paramMenuItem.setChecked(itemChecked).setVisible(itemVisible).setEnabled(itemEnabled);
      if (itemCheckable >= 1) {}
      for (boolean bool = true;; bool = false)
      {
        ((MenuItem)localObject).setCheckable(bool).setTitleCondensed(itemTitleCondensed).setIcon(itemIconResId).setAlphabeticShortcut(itemAlphabeticShortcut).setNumericShortcut(itemNumericShortcut);
        if (itemShowAsAction >= 0) {
          MenuItemCompat.setShowAsAction(paramMenuItem, itemShowAsAction);
        }
        if (itemListenerMethodName == null) {
          break label162;
        }
        if (!mContext.isRestricted()) {
          break;
        }
        throw new IllegalStateException("The android:onClick attribute cannot be used within a restricted context");
      }
      paramMenuItem.setOnMenuItemClickListener(new SupportMenuInflater.InflatedOnMenuItemClickListener(SupportMenuInflater.this.getRealOwner(), itemListenerMethodName));
      label162:
      if ((paramMenuItem instanceof MenuItemImpl)) {
        localObject = (MenuItemImpl)paramMenuItem;
      }
      if (itemCheckable >= 2)
      {
        if ((paramMenuItem instanceof MenuItemImpl)) {
          ((MenuItemImpl)paramMenuItem).setExclusiveCheckable(true);
        }
      }
      else
      {
        if (itemActionViewClassName == null) {
          break label297;
        }
        MenuItemCompat.setActionView(paramMenuItem, (View)newInstance(itemActionViewClassName, SupportMenuInflater.ACTION_VIEW_CONSTRUCTOR_SIGNATURE, mActionViewConstructorArguments));
      }
      for (;;)
      {
        if (itemActionViewLayout > 0)
        {
          if (i != 0) {
            break label286;
          }
          MenuItemCompat.setActionView(paramMenuItem, itemActionViewLayout);
        }
        for (;;)
        {
          if (itemActionProvider != null) {
            MenuItemCompat.setActionProvider(paramMenuItem, itemActionProvider);
          }
          return;
          if (!(paramMenuItem instanceof MenuItemWrapperICS)) {
            break;
          }
          ((MenuItemWrapperICS)paramMenuItem).setExclusiveCheckable(true);
          break;
          label286:
          Log.w("SupportMenuInflater", "Ignoring attribute 'itemActionViewLayout'. Action view already specified.");
        }
        label297:
        i = 0;
      }
    }
    
    public void addItem()
    {
      itemAdded = true;
      setItem(menu.add(groupId, itemId, itemCategoryOrder, itemTitle));
    }
    
    public SubMenu addSubMenuItem()
    {
      itemAdded = true;
      SubMenu localSubMenu = menu.addSubMenu(groupId, itemId, itemCategoryOrder, itemTitle);
      setItem(localSubMenu.getItem());
      return localSubMenu;
    }
    
    public boolean hasAddedItem()
    {
      return itemAdded;
    }
    
    public void readGroup(AttributeSet paramAttributeSet)
    {
      paramAttributeSet = mContext.obtainStyledAttributes(paramAttributeSet, R.styleable.MenuGroup);
      groupId = paramAttributeSet.getResourceId(R.styleable.MenuGroup_android_id, 0);
      groupCategory = paramAttributeSet.getInt(R.styleable.MenuGroup_android_menuCategory, 0);
      groupOrder = paramAttributeSet.getInt(R.styleable.MenuGroup_android_orderInCategory, 0);
      groupCheckable = paramAttributeSet.getInt(R.styleable.MenuGroup_android_checkableBehavior, 0);
      groupVisible = paramAttributeSet.getBoolean(R.styleable.MenuGroup_android_visible, true);
      groupEnabled = paramAttributeSet.getBoolean(R.styleable.MenuGroup_android_enabled, true);
      paramAttributeSet.recycle();
    }
    
    public void readItem(AttributeSet paramAttributeSet)
    {
      int j = 1;
      paramAttributeSet = mContext.obtainStyledAttributes(paramAttributeSet, R.styleable.MenuItem);
      itemId = paramAttributeSet.getResourceId(R.styleable.MenuItem_android_id, 0);
      itemCategoryOrder = (paramAttributeSet.getInt(R.styleable.MenuItem_android_menuCategory, groupCategory) & 0xFFFF0000 | paramAttributeSet.getInt(R.styleable.MenuItem_android_orderInCategory, groupOrder) & 0xFFFF);
      itemTitle = paramAttributeSet.getText(R.styleable.MenuItem_android_title);
      itemTitleCondensed = paramAttributeSet.getText(R.styleable.MenuItem_android_titleCondensed);
      itemIconResId = paramAttributeSet.getResourceId(R.styleable.MenuItem_android_icon, 0);
      itemAlphabeticShortcut = getShortcut(paramAttributeSet.getString(R.styleable.MenuItem_android_alphabeticShortcut));
      itemNumericShortcut = getShortcut(paramAttributeSet.getString(R.styleable.MenuItem_android_numericShortcut));
      int i;
      if (paramAttributeSet.hasValue(R.styleable.MenuItem_android_checkable)) {
        if (paramAttributeSet.getBoolean(R.styleable.MenuItem_android_checkable, false))
        {
          i = 1;
          itemCheckable = i;
          label156:
          itemChecked = paramAttributeSet.getBoolean(R.styleable.MenuItem_android_checked, false);
          itemVisible = paramAttributeSet.getBoolean(R.styleable.MenuItem_android_visible, groupVisible);
          itemEnabled = paramAttributeSet.getBoolean(R.styleable.MenuItem_android_enabled, groupEnabled);
          itemShowAsAction = paramAttributeSet.getInt(R.styleable.MenuItem_showAsAction, -1);
          itemListenerMethodName = paramAttributeSet.getString(R.styleable.MenuItem_android_onClick);
          itemActionViewLayout = paramAttributeSet.getResourceId(R.styleable.MenuItem_actionLayout, 0);
          itemActionViewClassName = paramAttributeSet.getString(R.styleable.MenuItem_actionViewClass);
          itemActionProviderClassName = paramAttributeSet.getString(R.styleable.MenuItem_actionProviderClass);
          if (itemActionProviderClassName == null) {
            break label333;
          }
          i = j;
          label264:
          if ((i == 0) || (itemActionViewLayout != 0) || (itemActionViewClassName != null)) {
            break label338;
          }
        }
      }
      for (itemActionProvider = ((ActionProvider)newInstance(itemActionProviderClassName, SupportMenuInflater.ACTION_PROVIDER_CONSTRUCTOR_SIGNATURE, mActionProviderConstructorArguments));; itemActionProvider = null)
      {
        paramAttributeSet.recycle();
        itemAdded = false;
        return;
        i = 0;
        break;
        itemCheckable = groupCheckable;
        break label156;
        label333:
        i = 0;
        break label264;
        label338:
        if (i != 0) {
          Log.w("SupportMenuInflater", "Ignoring attribute 'actionProviderClass'. Action view already specified.");
        }
      }
    }
    
    public void resetGroup()
    {
      groupId = 0;
      groupCategory = 0;
      groupOrder = 0;
      groupCheckable = 0;
      groupVisible = true;
      groupEnabled = true;
    }
  }
}

/* Location:
 * Qualified Name:     android.support.v7.internal.view.SupportMenuInflater
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */