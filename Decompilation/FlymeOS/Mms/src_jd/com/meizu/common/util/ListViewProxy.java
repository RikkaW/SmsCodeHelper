package com.meizu.common.util;

import android.content.ClipData;
import android.os.Build.VERSION;
import android.util.Log;
import android.view.ActionMode;
import android.view.MenuItem;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.widget.AbsListView;
import android.widget.Checkable;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.meizu.common.widget.AnimCheckBox;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ListViewProxy
  implements InvocationHandler
{
  public static final int ACTION_DRAG_FLAG_NFC_SHARE = 1;
  public static final int ACTION_DRAG_FLAG_NONE = 0;
  public static final int ACTION_TYPE_NORMAL = 0;
  public static final int ACTION_TYPE_WARNING = 1;
  private static Method mNotifyDragDropAnimType;
  private static Method mNotifyStatusBarNfcShareStateChanged;
  private static Method mStartDragMz;
  private static final String tag = "AbsListViewProxy";
  protected AbsListView mAbsList;
  private Method mApplyMeizuPartitionStyle;
  private Method mCheckedAll;
  private Field mChoiceActionMode;
  private Method mGetDragPosition;
  private boolean mIsFlyOS;
  private Method mSetDelayTopOverScrollEnabled;
  private Method mSetDelayTopOverScrollOffset;
  private Method mSetDividerFilterListenerMethod;
  private Method mSetDividerPaddingListenerMethod;
  private Method mSetDragListenerMethod;
  private Method mSetDragSelectionListenerMethod;
  private Method mSetEnableDragSelectionMethod;
  private Method mUnCheckedAll;
  
  public ListViewProxy(AbsListView paramAbsListView)
  {
    mAbsList = paramAbsListView;
    mIsFlyOS = CommonUtils.isFlymeOS();
  }
  
  private Object createListenerInstance(Class<?> paramClass)
  {
    return Proxy.newProxyInstance(paramClass.getClassLoader(), new Class[] { paramClass }, this);
  }
  
  private void getDragPostionAndId(long[] paramArrayOfLong)
  {
    if (mAbsList != null) {}
    try
    {
      if (mGetDragPosition == null) {
        mGetDragPosition = AbsListView.class.getMethod("getDragPosition", new Class[0]);
      }
      localObject1 = (ListAdapter)mAbsList.getAdapter();
      if ((mGetDragPosition == null) || (localObject1 == null)) {}
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      try
      {
        Object localObject1 = mGetDragPosition.invoke(mAbsList, new Object[0]);
        if ((localObject1 instanceof Integer))
        {
          int i = ((Integer)localObject1).intValue();
          long l = ((ListAdapter)mAbsList.getAdapter()).getItemId(i);
          paramArrayOfLong[0] = i;
          paramArrayOfLong[1] = l;
        }
        return;
        localNoSuchMethodException = localNoSuchMethodException;
        localNoSuchMethodException.printStackTrace();
        mGetDragPosition = null;
      }
      catch (IllegalArgumentException localIllegalArgumentException)
      {
        for (;;)
        {
          localIllegalArgumentException.printStackTrace();
          Object localObject2 = null;
        }
      }
      catch (IllegalAccessException localIllegalAccessException)
      {
        for (;;)
        {
          localIllegalAccessException.printStackTrace();
          Object localObject3 = null;
        }
      }
      catch (InvocationTargetException localInvocationTargetException)
      {
        for (;;)
        {
          localInvocationTargetException.printStackTrace();
          Object localObject4 = null;
        }
      }
    }
  }
  
  public static boolean notifyDragDropAnimType(View paramView, int paramInt)
  {
    if (!CommonUtils.isFlymeOS()) {
      return false;
    }
    try
    {
      if (mNotifyDragDropAnimType == null) {
        mNotifyDragDropAnimType = View.class.getDeclaredMethod("notifyDragDropAnimType", new Class[] { Integer.TYPE });
      }
      mNotifyDragDropAnimType.setAccessible(true);
      mNotifyDragDropAnimType.invoke(paramView, new Object[0]);
      return true;
    }
    catch (Exception paramView)
    {
      Log.e("AbsListViewProxy", "notifyDragDropAnimType fail to be invoked");
      mNotifyDragDropAnimType = null;
    }
    return false;
  }
  
  public static boolean notifyStatusBarNfcShareStateChanged(View paramView, boolean paramBoolean)
  {
    if (!CommonUtils.isFlymeOS()) {
      return false;
    }
    try
    {
      if (mNotifyStatusBarNfcShareStateChanged == null) {
        mNotifyStatusBarNfcShareStateChanged = View.class.getDeclaredMethod("notifyStatusBarNfcShareStateChanged", new Class[] { Boolean.TYPE });
      }
      mNotifyStatusBarNfcShareStateChanged.setAccessible(true);
      mNotifyStatusBarNfcShareStateChanged.invoke(paramView, new Object[0]);
      return true;
    }
    catch (Exception paramView)
    {
      Log.e("AbsListViewProxy", "notifyStatusBarNfcShareStateChanged fail to be invoked");
      mNotifyStatusBarNfcShareStateChanged = null;
    }
    return false;
  }
  
  public static boolean setItemForChecked(AbsListView paramAbsListView, View paramView)
  {
    if (Build.VERSION.SDK_INT >= 21) {}
    for (int i = 1; (paramView == null) || ((CommonUtils.isFlymeOS()) && (i != 0)); i = 0) {
      return false;
    }
    if (paramAbsListView.getChoiceMode() == 3)
    {
      paramView = paramView.findViewById(16908289);
      if ((paramView != null) && ((paramView instanceof Checkable)))
      {
        if ((paramView instanceof AnimCheckBox)) {
          ((AnimCheckBox)paramView).setIsAnimation(true);
        }
        if (paramAbsListView.getCheckedItemCount() > 0) {
          ((Checkable)paramView).setChecked(true);
        }
        for (;;)
        {
          return true;
          ((Checkable)paramView).setChecked(false);
        }
      }
    }
    return false;
  }
  
  public static boolean startDragMz(View paramView, ClipData paramClipData, View.DragShadowBuilder paramDragShadowBuilder, Object paramObject, int paramInt)
  {
    if (!CommonUtils.isFlymeOS()) {
      return false;
    }
    try
    {
      if (mStartDragMz == null) {
        mStartDragMz = View.class.getDeclaredMethod("startDragMz", new Class[] { ClipData.class, View.DragShadowBuilder.class, Object.class, Integer.TYPE });
      }
      mStartDragMz.setAccessible(true);
      mStartDragMz.invoke(paramView, new Object[0]);
      return true;
    }
    catch (Exception paramView)
    {
      Log.e("AbsListViewProxy", "startDragMz fail to be invoked");
      mStartDragMz = null;
    }
    return false;
  }
  
  public boolean applyMeizuPartitionStyle()
  {
    if ((mIsFlyOS) && ((mAbsList instanceof ListView))) {
      try
      {
        if (mApplyMeizuPartitionStyle == null) {
          mApplyMeizuPartitionStyle = ListView.class.getDeclaredMethod("applyMeizuPartitionStyle", new Class[0]);
        }
        mApplyMeizuPartitionStyle.setAccessible(true);
        mApplyMeizuPartitionStyle.invoke(mAbsList, new Object[0]);
        return true;
      }
      catch (Exception localException)
      {
        Log.e("AbsListViewProxy", "setCenterListContent fail to be invoked");
        mApplyMeizuPartitionStyle = null;
        return false;
      }
    }
    return false;
  }
  
  protected boolean bottomDeviderEnabled()
  {
    return true;
  }
  
  public boolean checkedAll()
  {
    int i = 0;
    if (!mIsFlyOS)
    {
      while (i < mAbsList.getCount())
      {
        mAbsList.setItemChecked(i, true);
        i += 1;
      }
      return true;
    }
    if (!(mAbsList instanceof ListView))
    {
      Log.e("tag", "the Target is not a ListView");
      return false;
    }
    try
    {
      if (mCheckedAll == null) {
        mCheckedAll = ListView.class.getDeclaredMethod("checkedAll", new Class[0]);
      }
      mCheckedAll.setAccessible(true);
      mCheckedAll.invoke(mAbsList, new Object[0]);
      return true;
    }
    catch (Exception localException)
    {
      mCheckedAll = null;
      Log.e("AbsListViewProxy", "checkedAll fail to be invoked");
    }
    return false;
  }
  
  protected boolean dividerEnabled(int paramInt)
  {
    return true;
  }
  
  public boolean finishMultiChoice()
  {
    if (!mIsFlyOS) {
      return false;
    }
    for (;;)
    {
      try
      {
        if (mChoiceActionMode == null) {
          mChoiceActionMode = AbsListView.class.getDeclaredField("mChoiceActionMode");
        }
        mChoiceActionMode.setAccessible(true);
        if (!(mChoiceActionMode.get(mAbsList) instanceof ActionMode)) {
          break label95;
        }
        ActionMode localActionMode = (ActionMode)mChoiceActionMode.get(mAbsList);
        if (localActionMode != null)
        {
          localActionMode.finish();
          return true;
        }
      }
      catch (Exception localException)
      {
        Log.e("AbsListViewProxy", "finishMultiChoice fail to be invoked");
        mChoiceActionMode = null;
        return false;
      }
      return false;
      label95:
      Object localObject = null;
    }
  }
  
  protected int getActionItemType(MenuItem paramMenuItem)
  {
    return 0;
  }
  
  public int[] getDividerPadding(int paramInt)
  {
    return null;
  }
  
  public Object invoke(Object paramObject, Method paramMethod, Object[] paramArrayOfObject)
  {
    try
    {
      paramObject = paramMethod.getName();
      if ("onActionItemDragStart".equals(paramObject))
      {
        if (paramArrayOfObject.length > 0) {}
        for (int i = onActionItemDragStart(((Integer)paramArrayOfObject[0]).intValue(), ((Long)paramArrayOfObject[1]).longValue());; i = onActionItemDragStart((int)paramObject[0], paramObject[1]))
        {
          return Integer.valueOf(i);
          paramObject = new long[2];
          getDragPostionAndId((long[])paramObject);
        }
      }
      if ("onActionItemDrop".equals(paramObject))
      {
        onActionItemDrop((MenuItem)paramArrayOfObject[0], ((Integer)paramArrayOfObject[1]).intValue(), ((Long)paramArrayOfObject[2]).longValue());
        return null;
      }
      if ("onActionItemDragEnd".equals(paramObject))
      {
        if (paramArrayOfObject.length > 0)
        {
          onActionItemDragEnd(((Integer)paramArrayOfObject[0]).intValue(), ((Long)paramArrayOfObject[1]).longValue());
          return null;
        }
        paramObject = new long[2];
        getDragPostionAndId((long[])paramObject);
        onActionItemDragEnd((int)paramObject[0], paramObject[1]);
        return null;
      }
      if ("getActionItemType".equals(paramObject)) {
        return Integer.valueOf(getActionItemType((MenuItem)paramArrayOfObject[0]));
      }
      if ("onDragSelection".equals(paramObject)) {
        return Boolean.valueOf(onDragSelection((View)paramArrayOfObject[0], ((Integer)paramArrayOfObject[1]).intValue(), ((Long)paramArrayOfObject[2]).longValue()));
      }
      if ("topDividerEnabled".equals(paramObject)) {
        return Boolean.valueOf(topDividerEnabled());
      }
      if ("dividerEnabled".equals(paramObject)) {
        return Boolean.valueOf(dividerEnabled(((Integer)paramArrayOfObject[0]).intValue()));
      }
      if ("bottomDeviderEnabled".equals(paramObject)) {
        return Boolean.valueOf(bottomDeviderEnabled());
      }
      if ("getDividerPadding".equals(paramObject))
      {
        paramObject = getDividerPadding(((Integer)paramArrayOfObject[0]).intValue());
        return paramObject;
      }
    }
    catch (Exception paramObject)
    {
      ((Exception)paramObject).printStackTrace();
    }
    return null;
  }
  
  protected void onActionItemDragEnd(int paramInt, long paramLong) {}
  
  protected int onActionItemDragStart(int paramInt, long paramLong)
  {
    return 0;
  }
  
  protected void onActionItemDrop(MenuItem paramMenuItem, int paramInt, long paramLong) {}
  
  protected boolean onDragSelection(View paramView, int paramInt, long paramLong)
  {
    return false;
  }
  
  public boolean setCenterListContent(boolean paramBoolean)
  {
    return false;
  }
  
  public boolean setDelayTopOverScrollEnabled(boolean paramBoolean)
  {
    if (mIsFlyOS) {
      try
      {
        if (mSetDelayTopOverScrollEnabled == null) {
          mSetDelayTopOverScrollEnabled = AbsListView.class.getDeclaredMethod("setDelayTopOverScrollEnabled", new Class[] { Boolean.TYPE });
        }
        mSetDelayTopOverScrollEnabled.setAccessible(true);
        mSetDelayTopOverScrollEnabled.invoke(mAbsList, new Object[] { Boolean.valueOf(paramBoolean) });
        return true;
      }
      catch (Exception localException)
      {
        Log.e("AbsListViewProxy", "setDelayTopOverScrollEnabled fail to be invoked");
        mSetDelayTopOverScrollEnabled = null;
        return false;
      }
    }
    return false;
  }
  
  public boolean setDelayTopOverScrollOffset(int paramInt)
  {
    if (mIsFlyOS) {
      try
      {
        if (mSetDelayTopOverScrollOffset == null) {
          mSetDelayTopOverScrollOffset = AbsListView.class.getDeclaredMethod("setDelayTopOverScrollOffset", new Class[] { Integer.TYPE });
        }
        mSetDelayTopOverScrollOffset.setAccessible(true);
        mSetDelayTopOverScrollOffset.invoke(mAbsList, new Object[] { Integer.valueOf(paramInt) });
        return true;
      }
      catch (Exception localException)
      {
        Log.e("AbsListViewProxy", "setDelayTopOverScrollOffset fail to be invoked");
        mSetDelayTopOverScrollOffset = null;
        return false;
      }
    }
    return false;
  }
  
  public boolean setDividerFilterListener()
  {
    boolean bool = true;
    if ((!mIsFlyOS) || (!(mAbsList instanceof ListView))) {
      return false;
    }
    label120:
    for (;;)
    {
      try
      {
        Object localObject = Class.forName("android.widget.ListView$DividerFilter");
        if (mSetDividerFilterListenerMethod == null) {
          mSetDividerFilterListenerMethod = ListView.class.getMethod("setDividerFilterListener", new Class[] { localObject });
        }
        bool = false;
      }
      catch (Exception localException)
      {
        try
        {
          localObject = createListenerInstance((Class)localObject);
          if (localObject == null) {
            break label120;
          }
          mSetDividerFilterListenerMethod.invoke(mAbsList, new Object[] { localObject });
          return bool;
        }
        catch (IllegalArgumentException localIllegalArgumentException)
        {
          localIllegalArgumentException.printStackTrace();
          return false;
        }
        catch (IllegalAccessException localIllegalAccessException)
        {
          localIllegalAccessException.printStackTrace();
          return false;
        }
        catch (InvocationTargetException localInvocationTargetException)
        {
          localInvocationTargetException.printStackTrace();
          return false;
        }
        localException = localException;
        localException.printStackTrace();
        mSetDividerFilterListenerMethod = null;
        return false;
      }
    }
  }
  
  public boolean setDividerPaddingsListener()
  {
    boolean bool = true;
    if ((!mIsFlyOS) || (!(mAbsList instanceof ListView))) {
      return false;
    }
    label120:
    for (;;)
    {
      try
      {
        Object localObject = Class.forName("android.widget.ListView$DividerPadding");
        if (mSetDividerPaddingListenerMethod == null) {
          mSetDividerPaddingListenerMethod = ListView.class.getMethod("setDividerPadding", new Class[] { localObject });
        }
        bool = false;
      }
      catch (Exception localException)
      {
        try
        {
          localObject = createListenerInstance((Class)localObject);
          if (localObject == null) {
            break label120;
          }
          mSetDividerPaddingListenerMethod.invoke(mAbsList, new Object[] { localObject });
          return bool;
        }
        catch (IllegalArgumentException localIllegalArgumentException)
        {
          localIllegalArgumentException.printStackTrace();
          return false;
        }
        catch (IllegalAccessException localIllegalAccessException)
        {
          localIllegalAccessException.printStackTrace();
          return false;
        }
        catch (InvocationTargetException localInvocationTargetException)
        {
          localInvocationTargetException.printStackTrace();
          return false;
        }
        localException = localException;
        localException.printStackTrace();
        mSetDividerPaddingListenerMethod = null;
        return false;
      }
    }
  }
  
  public boolean setEnableDragSelection(boolean paramBoolean)
  {
    boolean bool = true;
    if ((!mIsFlyOS) || (!(mAbsList instanceof ListView))) {
      return false;
    }
    try
    {
      if (mSetEnableDragSelectionMethod == null) {
        mSetEnableDragSelectionMethod = ListView.class.getMethod("setEnableDragSelection", new Class[] { Boolean.TYPE });
      }
    }
    catch (Exception localException)
    {
      label75:
      localException.printStackTrace();
      mSetEnableDragSelectionMethod = null;
      return false;
    }
    try
    {
      mSetEnableDragSelectionMethod.invoke(mAbsList, new Object[] { Boolean.valueOf(paramBoolean) });
      paramBoolean = bool;
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      localIllegalArgumentException.printStackTrace();
      paramBoolean = false;
      break label75;
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      localIllegalAccessException.printStackTrace();
      paramBoolean = false;
      break label75;
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      localInvocationTargetException.printStackTrace();
      paramBoolean = false;
      break label75;
    }
    return paramBoolean;
  }
  
  public boolean setEnableDragSelectionListener()
  {
    boolean bool = true;
    if ((!mIsFlyOS) || (!(mAbsList instanceof ListView))) {
      return false;
    }
    label120:
    for (;;)
    {
      try
      {
        Object localObject = Class.forName("android.widget.ListView$OnDragSelectListener");
        if (mSetDragSelectionListenerMethod == null) {
          mSetDragSelectionListenerMethod = ListView.class.getMethod("setEnableDragSelection", new Class[] { localObject });
        }
        bool = false;
      }
      catch (Exception localException)
      {
        try
        {
          localObject = createListenerInstance((Class)localObject);
          if (localObject == null) {
            break label120;
          }
          mSetDragSelectionListenerMethod.invoke(mAbsList, new Object[] { localObject });
          return bool;
        }
        catch (IllegalArgumentException localIllegalArgumentException)
        {
          localIllegalArgumentException.printStackTrace();
          return false;
        }
        catch (IllegalAccessException localIllegalAccessException)
        {
          localIllegalAccessException.printStackTrace();
          return false;
        }
        catch (InvocationTargetException localInvocationTargetException)
        {
          localInvocationTargetException.printStackTrace();
          return false;
        }
        localException = localException;
        localException.printStackTrace();
        mSetDragSelectionListenerMethod = null;
        return false;
      }
    }
  }
  
  /* Error */
  public boolean setEnabledMultiChoice()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 53	com/meizu/common/util/ListViewProxy:mIsFlyOS	Z
    //   4: ifne +13 -> 17
    //   7: aload_0
    //   8: getfield 45	com/meizu/common/util/ListViewProxy:mAbsList	Landroid/widget/AbsListView;
    //   11: iconst_3
    //   12: invokevirtual 360	android/widget/AbsListView:setChoiceMode	(I)V
    //   15: iconst_1
    //   16: ireturn
    //   17: ldc_w 362
    //   20: invokestatic 334	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   23: astore_1
    //   24: aload_0
    //   25: getfield 364	com/meizu/common/util/ListViewProxy:mSetDragListenerMethod	Ljava/lang/reflect/Method;
    //   28: ifnonnull +23 -> 51
    //   31: aload_0
    //   32: ldc 84
    //   34: ldc_w 366
    //   37: iconst_1
    //   38: anewarray 58	java/lang/Class
    //   41: dup
    //   42: iconst_0
    //   43: aload_1
    //   44: aastore
    //   45: invokevirtual 90	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   48: putfield 364	com/meizu/common/util/ListViewProxy:mSetDragListenerMethod	Ljava/lang/reflect/Method;
    //   51: aload_0
    //   52: aload_1
    //   53: invokespecial 339	com/meizu/common/util/ListViewProxy:createListenerInstance	(Ljava/lang/Class;)Ljava/lang/Object;
    //   56: astore_1
    //   57: aload_1
    //   58: ifnull +64 -> 122
    //   61: aload_0
    //   62: getfield 364	com/meizu/common/util/ListViewProxy:mSetDragListenerMethod	Ljava/lang/reflect/Method;
    //   65: aload_0
    //   66: getfield 45	com/meizu/common/util/ListViewProxy:mAbsList	Landroid/widget/AbsListView;
    //   69: iconst_1
    //   70: anewarray 4	java/lang/Object
    //   73: dup
    //   74: iconst_0
    //   75: aload_1
    //   76: aastore
    //   77: invokevirtual 102	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   80: pop
    //   81: aload_0
    //   82: getfield 45	com/meizu/common/util/ListViewProxy:mAbsList	Landroid/widget/AbsListView;
    //   85: iconst_4
    //   86: invokevirtual 360	android/widget/AbsListView:setChoiceMode	(I)V
    //   89: iconst_1
    //   90: ireturn
    //   91: astore_1
    //   92: aload_1
    //   93: invokevirtual 116	java/lang/IllegalArgumentException:printStackTrace	()V
    //   96: iconst_0
    //   97: ireturn
    //   98: astore_1
    //   99: aload_1
    //   100: invokevirtual 313	java/lang/Exception:printStackTrace	()V
    //   103: aload_0
    //   104: aconst_null
    //   105: putfield 364	com/meizu/common/util/ListViewProxy:mSetDragListenerMethod	Ljava/lang/reflect/Method;
    //   108: iconst_0
    //   109: ireturn
    //   110: astore_1
    //   111: aload_1
    //   112: invokevirtual 117	java/lang/IllegalAccessException:printStackTrace	()V
    //   115: iconst_0
    //   116: ireturn
    //   117: astore_1
    //   118: aload_1
    //   119: invokevirtual 118	java/lang/reflect/InvocationTargetException:printStackTrace	()V
    //   122: iconst_0
    //   123: ireturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	124	0	this	ListViewProxy
    //   23	53	1	localObject	Object
    //   91	2	1	localIllegalArgumentException	IllegalArgumentException
    //   98	2	1	localException	Exception
    //   110	2	1	localIllegalAccessException	IllegalAccessException
    //   117	2	1	localInvocationTargetException	InvocationTargetException
    // Exception table:
    //   from	to	target	type
    //   61	89	91	java/lang/IllegalArgumentException
    //   17	51	98	java/lang/Exception
    //   61	89	110	java/lang/IllegalAccessException
    //   61	89	117	java/lang/reflect/InvocationTargetException
  }
  
  protected boolean topDividerEnabled()
  {
    return true;
  }
  
  public boolean unCheckedAll()
  {
    if (!(mAbsList instanceof ListView))
    {
      Log.e("tag", "unchecked error");
      return false;
    }
    if (!mIsFlyOS)
    {
      mAbsList.clearChoices();
      mAbsList.setItemChecked(0, false);
      mAbsList.requestLayout();
      return true;
    }
    try
    {
      if (mUnCheckedAll == null) {
        mUnCheckedAll = ListView.class.getDeclaredMethod("unCheckedAll", new Class[0]);
      }
      mUnCheckedAll.setAccessible(true);
      mUnCheckedAll.invoke(mAbsList, new Object[0]);
      return true;
    }
    catch (Exception localException)
    {
      Log.e("AbsListViewProxy", "unCheckedAll fail to be invoked");
      mUnCheckedAll = null;
    }
    return false;
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.util.ListViewProxy
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */