import android.util.Log;
import android.view.ActionMode;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListAdapter;
import android.widget.ListView;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class lx
  implements InvocationHandler
{
  private ListView a;
  
  public lx(ListView paramListView)
  {
    a = paramListView;
  }
  
  private Object a(Class<?> paramClass)
  {
    return Proxy.newProxyInstance(paramClass.getClassLoader(), new Class[] { paramClass }, this);
  }
  
  private void a(long[] paramArrayOfLong)
  {
    if (a != null) {}
    try
    {
      localObject1 = AbsListView.class.getMethod("getDragPosition", new Class[0]);
      ListAdapter localListAdapter = a.getAdapter();
      if ((localObject1 == null) || (localListAdapter == null)) {}
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      try
      {
        Object localObject1 = ((Method)localObject1).invoke(a, new Object[0]);
        if ((localObject1 instanceof Integer))
        {
          int i = ((Integer)localObject1).intValue();
          long l = a.getAdapter().getItemId(i);
          paramArrayOfLong[0] = i;
          paramArrayOfLong[1] = l;
        }
        return;
        localNoSuchMethodException = localNoSuchMethodException;
        localNoSuchMethodException.printStackTrace();
        Object localObject2 = null;
      }
      catch (IllegalArgumentException localIllegalArgumentException)
      {
        for (;;)
        {
          localIllegalArgumentException.printStackTrace();
          Object localObject3 = null;
        }
      }
      catch (IllegalAccessException localIllegalAccessException)
      {
        for (;;)
        {
          localIllegalAccessException.printStackTrace();
          Object localObject4 = null;
        }
      }
      catch (InvocationTargetException localInvocationTargetException)
      {
        for (;;)
        {
          localInvocationTargetException.printStackTrace();
          Object localObject5 = null;
        }
      }
    }
  }
  
  protected int a(int paramInt, long paramLong)
  {
    return 0;
  }
  
  protected int a(MenuItem paramMenuItem)
  {
    return 0;
  }
  
  public void a()
  {
    try
    {
      Object localObject = Class.forName("android.widget.AbsListView$OnItemDragListener");
      Method localMethod = AbsListView.class.getDeclaredMethod("setItemDragListener", new Class[] { localObject });
      localObject = a((Class)localObject);
      if (localObject != null) {
        localMethod.invoke(a, new Object[] { localObject });
      }
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  public void a(int paramInt)
  {
    try
    {
      Method localMethod = AbsListView.class.getDeclaredMethod("setChoiceMode", new Class[] { Integer.TYPE });
      localMethod.setAccessible(true);
      localMethod.invoke(a, new Object[] { Integer.valueOf(paramInt) });
      return;
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      localNoSuchMethodException.printStackTrace();
      return;
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      localInvocationTargetException.printStackTrace();
      return;
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      localIllegalAccessException.printStackTrace();
    }
  }
  
  protected void a(MenuItem paramMenuItem, int paramInt, long paramLong) {}
  
  public void a(boolean paramBoolean)
  {
    try
    {
      Method localMethod = ListView.class.getDeclaredMethod("setEnableDragSelection", new Class[] { Boolean.TYPE });
      localMethod.setAccessible(true);
      localMethod.invoke(a, new Object[] { Boolean.valueOf(paramBoolean) });
      return;
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      localNoSuchMethodException.printStackTrace();
      return;
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      localInvocationTargetException.printStackTrace();
      return;
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      localIllegalAccessException.printStackTrace();
    }
  }
  
  public boolean a(View paramView, int paramInt, long paramLong)
  {
    try
    {
      Method localMethod = AbsListView.class.getDeclaredMethod("performItemLongPress", new Class[] { View.class, Integer.TYPE, Long.TYPE });
      localMethod.setAccessible(true);
      boolean bool = ((Boolean)localMethod.invoke(a, new Object[] { paramView, Integer.valueOf(paramInt), Long.valueOf(paramLong) })).booleanValue();
      return bool;
    }
    catch (NoSuchMethodException paramView)
    {
      paramView.printStackTrace();
      return false;
    }
    catch (InvocationTargetException paramView)
    {
      for (;;)
      {
        paramView.printStackTrace();
      }
    }
    catch (IllegalAccessException paramView)
    {
      for (;;)
      {
        paramView.printStackTrace();
      }
    }
  }
  
  protected void b(int paramInt, long paramLong) {}
  
  public void b(boolean paramBoolean)
  {
    try
    {
      Method localMethod = ListView.class.getDeclaredMethod("setDividerInSide", new Class[] { Boolean.TYPE });
      localMethod.setAccessible(true);
      localMethod.invoke(a, new Object[] { Boolean.valueOf(paramBoolean) });
      return;
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      localNoSuchMethodException.printStackTrace();
      return;
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      localInvocationTargetException.printStackTrace();
      return;
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      localIllegalAccessException.printStackTrace();
    }
  }
  
  public boolean b()
  {
    try
    {
      Method localMethod = ListView.class.getDeclaredMethod("checkedAll", new Class[0]);
      localMethod.setAccessible(true);
      localMethod.invoke(a, new Object[0]);
      return true;
    }
    catch (Exception localException)
    {
      Log.e("AbsListViewProxy", "checkedAll fail to be invoked");
    }
    return false;
  }
  
  protected boolean b(View paramView, int paramInt, long paramLong)
  {
    return false;
  }
  
  public void c()
  {
    try
    {
      Method localMethod = ListView.class.getDeclaredMethod("setSelectionToBottom", new Class[0]);
      localMethod.setAccessible(true);
      localMethod.invoke(a, new Object[0]);
      return;
    }
    catch (Exception localException)
    {
      Log.e("AbsListViewProxy", "setSelectionToBottom failed");
    }
  }
  
  public boolean d()
  {
    ActionMode localActionMode = null;
    try
    {
      Field localField = AbsListView.class.getDeclaredField("mChoiceActionMode");
      localField.setAccessible(true);
      if ((localField.get(a) instanceof ActionMode)) {
        localActionMode = (ActionMode)localField.get(a);
      }
      if (localActionMode != null)
      {
        localActionMode.finish();
        return true;
      }
    }
    catch (Exception localException)
    {
      Log.e("AbsListViewProxy", "finishMultiChoice fail to be invoked");
      return false;
    }
    return false;
  }
  
  public int e()
  {
    try
    {
      Method localMethod = AbsListView.class.getDeclaredMethod("getDragPosition", new Class[0]);
      localMethod.setAccessible(true);
      int i = ((Integer)localMethod.invoke(a, new Object[0])).intValue();
      return i;
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      localNoSuchMethodException.printStackTrace();
      return 0;
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      for (;;)
      {
        localInvocationTargetException.printStackTrace();
      }
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      for (;;)
      {
        localIllegalAccessException.printStackTrace();
      }
    }
  }
  
  public void f()
  {
    try
    {
      Method localMethod = ListView.class.getDeclaredMethod("unCheckedAll", new Class[0]);
      localMethod.setAccessible(true);
      localMethod.invoke(a, new Object[0]);
      return;
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      localNoSuchMethodException.printStackTrace();
      return;
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      localInvocationTargetException.printStackTrace();
      return;
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      localIllegalAccessException.printStackTrace();
    }
  }
  
  public Object invoke(Object paramObject, Method paramMethod, Object[] paramArrayOfObject)
  {
    paramObject = paramMethod.getName();
    if ("onActionItemDragStart".equals(paramObject))
    {
      if (paramArrayOfObject.length > 0) {}
      for (int i = a(((Integer)paramArrayOfObject[0]).intValue(), ((Long)paramArrayOfObject[1]).longValue());; i = a((int)paramObject[0], paramObject[1]))
      {
        return Integer.valueOf(i);
        paramObject = new long[2];
        a((long[])paramObject);
      }
    }
    if ("onActionItemDrop".equals(paramObject))
    {
      a((MenuItem)paramArrayOfObject[0], ((Integer)paramArrayOfObject[1]).intValue(), ((Long)paramArrayOfObject[2]).longValue());
      return null;
    }
    if ("onActionItemDragEnd".equals(paramObject))
    {
      if (paramArrayOfObject.length > 0)
      {
        b(((Integer)paramArrayOfObject[0]).intValue(), ((Long)paramArrayOfObject[1]).longValue());
        return null;
      }
      paramObject = new long[2];
      a((long[])paramObject);
      b((int)paramObject[0], paramObject[1]);
      return null;
    }
    if ("getActionItemType".equals(paramObject)) {
      return Integer.valueOf(a((MenuItem)paramArrayOfObject[0]));
    }
    if ("isItemDragable".equals(paramObject)) {
      return Boolean.valueOf(b((View)paramArrayOfObject[0], ((Integer)paramArrayOfObject[1]).intValue(), ((Long)paramArrayOfObject[2]).longValue()));
    }
    return null;
  }
}

/* Location:
 * Qualified Name:     lx
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */