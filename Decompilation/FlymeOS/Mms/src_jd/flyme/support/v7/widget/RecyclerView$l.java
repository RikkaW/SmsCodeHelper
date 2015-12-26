package flyme.support.v7.widget;

import android.util.SparseArray;
import android.util.SparseIntArray;
import java.util.ArrayList;

public class RecyclerView$l
{
  private SparseArray<ArrayList<RecyclerView.t>> a = new SparseArray();
  private SparseIntArray b = new SparseIntArray();
  private int c = 0;
  
  private ArrayList<RecyclerView.t> b(int paramInt)
  {
    ArrayList localArrayList2 = (ArrayList)a.get(paramInt);
    ArrayList localArrayList1 = localArrayList2;
    if (localArrayList2 == null)
    {
      localArrayList2 = new ArrayList();
      a.put(paramInt, localArrayList2);
      localArrayList1 = localArrayList2;
      if (b.indexOfKey(paramInt) < 0)
      {
        b.put(paramInt, 5);
        localArrayList1 = localArrayList2;
      }
    }
    return localArrayList1;
  }
  
  public RecyclerView.t a(int paramInt)
  {
    ArrayList localArrayList = (ArrayList)a.get(paramInt);
    if ((localArrayList != null) && (!localArrayList.isEmpty()))
    {
      paramInt = localArrayList.size() - 1;
      RecyclerView.t localt = (RecyclerView.t)localArrayList.get(paramInt);
      localArrayList.remove(paramInt);
      return localt;
    }
    return null;
  }
  
  public void a()
  {
    a.clear();
  }
  
  void a(RecyclerView.a parama)
  {
    c += 1;
  }
  
  void a(RecyclerView.a parama1, RecyclerView.a parama2, boolean paramBoolean)
  {
    if (parama1 != null) {
      b();
    }
    if ((!paramBoolean) && (c == 0)) {
      a();
    }
    if (parama2 != null) {
      a(parama2);
    }
  }
  
  public void a(RecyclerView.t paramt)
  {
    int i = paramt.f();
    ArrayList localArrayList = b(i);
    if (b.get(i) <= localArrayList.size()) {
      return;
    }
    paramt.s();
    localArrayList.add(paramt);
  }
  
  void b()
  {
    c -= 1;
  }
}

/* Location:
 * Qualified Name:     flyme.support.v7.widget.RecyclerView.l
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */