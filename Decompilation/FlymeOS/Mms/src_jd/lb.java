import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.amap.api.maps2d.AMap.OnMarkerClickListener;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.core.SuggestionCity;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.GeocodeSearch.OnGeocodeSearchListener;
import com.amap.api.services.geocoder.RegeocodeAddress;
import com.amap.api.services.geocoder.RegeocodeQuery;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.amap.api.services.poisearch.PoiItemDetail;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.amap.api.services.poisearch.PoiSearch.OnPoiSearchListener;
import com.amap.api.services.poisearch.PoiSearch.Query;
import com.amap.api.services.poisearch.PoiSearch.SearchBound;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class lb
  extends kx
  implements AMap.OnMarkerClickListener, GeocodeSearch.OnGeocodeSearchListener, PoiSearch.OnPoiSearchListener
{
  private lb.b f;
  private String g;
  private String h;
  private lb.a i = new lb.a(a);
  private LatLng j;
  private String k;
  private String l;
  private String m;
  private PoiSearch n;
  private GeocodeSearch o;
  private RegeocodeQuery p;
  private AlertDialog q;
  
  public lb(Context paramContext)
  {
    super(paramContext);
    a(paramContext);
    b(paramContext);
  }
  
  private PoiSearch.Query a(String paramString1, String paramString2, String paramString3)
  {
    paramString1 = new PoiSearch.Query(paramString1, paramString2, g);
    paramString1.setPageSize(20);
    paramString1.setPageNum(0);
    return paramString1;
  }
  
  private ld a(RegeocodeResult paramRegeocodeResult, boolean paramBoolean)
  {
    RegeocodeAddress localRegeocodeAddress = paramRegeocodeResult.getRegeocodeAddress();
    Log.i("amap/SearchUtils", "createSuggestLocation regeocodeAddress.getFormatAddress() = " + localRegeocodeAddress.getFormatAddress());
    l = localRegeocodeAddress.getFormatAddress();
    if (TextUtils.isEmpty(localRegeocodeAddress.getBuilding())) {}
    for (String str = l;; str = localRegeocodeAddress.getBuilding())
    {
      k = str;
      g = localRegeocodeAddress.getCity();
      j = kx.a(paramRegeocodeResult.getRegeocodeQuery().getPoint());
      f.a(k, l);
      if (TextUtils.isEmpty(localRegeocodeAddress.getFormatAddress())) {
        break;
      }
      paramRegeocodeResult = new ld(l, g, kx.a(f.b(), paramRegeocodeResult.getRegeocodeQuery().getPoint()), paramRegeocodeResult.getRegeocodeQuery().getPoint());
      paramRegeocodeResult.d(k);
      paramRegeocodeResult.b(true);
      paramRegeocodeResult.a(paramBoolean);
      paramRegeocodeResult.c(false);
      paramRegeocodeResult.a(null);
      return paramRegeocodeResult;
    }
    return null;
  }
  
  private void a(Context paramContext)
  {
    n = new PoiSearch(paramContext, null);
    n.setOnPoiSearchListener(this);
  }
  
  private void a(PoiResult paramPoiResult)
  {
    ArrayList localArrayList = new ArrayList();
    Object localObject = new StringBuilder();
    paramPoiResult = paramPoiResult.getSearchSuggestionCitys();
    int i1 = 0;
    while (i1 < paramPoiResult.size())
    {
      ((StringBuilder)localObject).delete(0, ((StringBuilder)localObject).length());
      ((StringBuilder)localObject).append(((SuggestionCity)paramPoiResult.get(i1)).getCityName());
      ((StringBuilder)localObject).append(" ");
      ((StringBuilder)localObject).append(((SuggestionCity)paramPoiResult.get(i1)).getSuggestionNum());
      localArrayList.add(((StringBuilder)localObject).toString());
      i1 += 1;
    }
    localObject = new ListView(e);
    ((ListView)localObject).setOnItemClickListener(new lc(this, paramPoiResult));
    ((ListView)localObject).setAdapter(new ArrayAdapter(e, 17367050, localArrayList));
    paramPoiResult = new AlertDialog.Builder(e);
    paramPoiResult.setView((View)localObject);
    paramPoiResult.setTitle(2131493295);
    q = paramPoiResult.create();
    q.show();
  }
  
  private void a(PoiResult paramPoiResult, int paramInt)
  {
    if ((paramPoiResult != null) && (paramInt == 0)) {
      f.a(paramPoiResult.getPois());
    }
  }
  
  private void b(Context paramContext)
  {
    o = new GeocodeSearch(paramContext);
    o.setOnGeocodeSearchListener(this);
    p = new RegeocodeQuery(null, 50000.0F, "autonavi");
  }
  
  private void b(PoiItem paramPoiItem)
  {
    j = kx.a(paramPoiItem.getLatLonPoint());
    l = paramPoiItem.getSnippet();
    k = paramPoiItem.getTitle();
    g = paramPoiItem.getCityName();
    m = paramPoiItem.getPoiId();
    b(b());
    a(15, j);
    lb.b localb = f;
    LatLng localLatLng = j;
    if (TextUtils.isEmpty(k)) {}
    for (paramPoiItem = l;; paramPoiItem = k)
    {
      localb.a(localLatLng, paramPoiItem, l, false);
      return;
    }
  }
  
  private void b(PoiResult paramPoiResult, int paramInt)
  {
    if (paramPoiResult == null) {
      f.a(2131493351);
    }
    PoiItem localPoiItem;
    do
    {
      return;
      while (!paramPoiResult.hasNext()) {
        switch (paramInt)
        {
        default: 
          if ((paramPoiResult.getSearchSuggestionCitys() == null) || (paramPoiResult.getSearchSuggestionCitys().size() <= 0)) {
            break;
          }
          a(paramPoiResult);
          return;
        case 0: 
          if ((paramPoiResult.getPois() == null) || (paramPoiResult.getPois().size() <= 0)) {
            break label131;
          }
          f.a(paramPoiResult.getPois());
          paramPoiResult = paramPoiResult.getPois().iterator();
        }
      }
      localPoiItem = (PoiItem)paramPoiResult.next();
    } while (localPoiItem.getLatLonPoint() == null);
    b(localPoiItem);
    return;
    label131:
    f.a(2131493351);
    return;
    f.a(2131493351);
  }
  
  private void c(PoiResult paramPoiResult, int paramInt)
  {
    f.a(paramPoiResult, paramInt);
  }
  
  private void c(String paramString)
  {
    g = paramString;
    f.a(paramString);
    b(5);
  }
  
  public kr a(PoiItem paramPoiItem)
  {
    if ((!TextUtils.isEmpty(paramPoiItem.getSnippet())) || (!TextUtils.isEmpty(paramPoiItem.getTitle())))
    {
      if (!TextUtils.isEmpty(paramPoiItem.getSnippet())) {}
      for (Object localObject = paramPoiItem.getSnippet();; localObject = paramPoiItem.getTitle())
      {
        localObject = new ld((String)localObject, paramPoiItem.getCityName(), a(f.b(), paramPoiItem.getLatLonPoint()), paramPoiItem.getLatLonPoint());
        ((ld)localObject).b(false);
        ((ld)localObject).a(false);
        ((ld)localObject).d(paramPoiItem.getTitle());
        ((ld)localObject).c(true);
        ((ld)localObject).a(paramPoiItem);
        return (kr)localObject;
      }
    }
    return null;
  }
  
  public void a()
  {
    if ((q != null) && (q.isShowing())) {
      q.dismiss();
    }
    q = null;
    if (i != null)
    {
      i.removeMessages(11);
      i.removeMessages(9);
      i.removeMessages(5);
      i.removeMessages(2);
      i.removeMessages(4);
      i.removeMessages(6);
    }
    super.a();
  }
  
  public void a(int paramInt, LatLng paramLatLng)
  {
    a(paramInt);
    Message localMessage = i.obtainMessage(paramInt);
    obj = paramLatLng;
    i.sendMessage(localMessage);
  }
  
  public void a(LatLng paramLatLng)
  {
    if ((paramLatLng == null) || (o == null)) {
      return;
    }
    Log.i("amap/SearchUtils", "reverseGeocodeForLocationChange mSearchEvent = " + d);
    p.setPoint(new LatLonPoint(latitude, longitude));
    o.getFromLocationAsyn(p);
  }
  
  public void a(LatLng paramLatLng, String paramString1, String paramString2)
  {
    a(paramLatLng, paramString1, paramString2, null);
  }
  
  public void a(LatLng paramLatLng, String paramString1, String paramString2, String paramString3)
  {
    j = paramLatLng;
    k = paramString1;
    l = paramString2;
    m = paramString3;
  }
  
  public void a(String paramString)
  {
    g = paramString;
  }
  
  public void a(String paramString1, String paramString2)
  {
    a(true, paramString2, paramString1);
  }
  
  public void a(List<PoiItem> paramList, ArrayList<kr> paramArrayList)
  {
    int i1;
    if ((paramList != null) && (paramList.size() > 0)) {
      i1 = 0;
    }
    while (i1 < paramList.size())
    {
      kr localkr = a((PoiItem)paramList.get(i1));
      if (localkr != null) {
        paramArrayList.add(localkr);
      }
      i1 += 1;
      continue;
      Log.i("amap/SearchUtils", "createSuggestLocationForPoiList mSearchEvent = " + d + ", pois size is 0");
    }
  }
  
  public void a(kr paramkr)
  {
    a(false, paramkr.c(), paramkr.b());
  }
  
  public void a(lb.b paramb)
  {
    f = paramb;
  }
  
  public void a(ld paramld)
  {
    if (paramld == null) {
      return;
    }
    if ((paramld.i()) && (paramld.h() != null))
    {
      paramld = paramld.j();
      ArrayList localArrayList = new ArrayList();
      localArrayList.add(paramld);
      f.a(localArrayList);
      b(paramld);
      return;
    }
    a(false, paramld.c(), paramld.b());
  }
  
  public void a(boolean paramBoolean, String paramString1, String paramString2)
  {
    if (n == null) {
      return;
    }
    f.a(false);
    f.c();
    g = paramString1;
    h = paramString2;
    if (paramBoolean)
    {
      if (TextUtils.isEmpty(g))
      {
        a(4, f.b());
        return;
      }
      b(3);
      return;
    }
    b(9);
  }
  
  public ld b()
  {
    Log.i("amap/SearchUtils", "createSuggestLocation mAddress = " + l + " mName = " + k);
    if ((TextUtils.isEmpty(l)) && (TextUtils.isEmpty(k))) {
      return null;
    }
    ld localld = new ld(l, g, kx.b(f.b(), j), j);
    localld.b(true);
    localld.d(k);
    localld.c(false);
    localld.a(null);
    return localld;
  }
  
  public void b(int paramInt)
  {
    a(paramInt);
    Message localMessage = i.obtainMessage(paramInt);
    i.sendMessage(localMessage);
  }
  
  public void b(String paramString)
  {
    LatLng localLatLng = f.b();
    if ((localLatLng != null) && (n != null))
    {
      h = paramString;
      a(6, localLatLng);
    }
  }
  
  public void b(ld paramld)
  {
    if (paramld != null) {
      f.a(paramld);
    }
  }
  
  public String c()
  {
    StringBuilder localStringBuilder = new StringBuilder("http://m.amap.com/?");
    if (TextUtils.isEmpty(m))
    {
      localStringBuilder.append("q=");
      localStringBuilder.append(j.latitude);
      localStringBuilder.append(",");
      localStringBuilder.append(j.longitude);
    }
    for (;;)
    {
      return localStringBuilder.toString();
      localStringBuilder.append("pid=");
      localStringBuilder.append(m);
    }
  }
  
  public void onGeocodeSearched(GeocodeResult paramGeocodeResult, int paramInt) {}
  
  public boolean onMarkerClick(Marker paramMarker)
  {
    j = paramMarker.getPosition();
    l = paramMarker.getSnippet();
    k = paramMarker.getTitle();
    m = null;
    b(b());
    a(15, j);
    lb.b localb = f;
    LatLng localLatLng = j;
    if (TextUtils.isEmpty(k)) {}
    for (paramMarker = l;; paramMarker = k)
    {
      localb.a(localLatLng, paramMarker, l, false);
      return false;
    }
  }
  
  public void onPoiItemDetailSearched(PoiItemDetail paramPoiItemDetail, int paramInt) {}
  
  public void onPoiSearched(PoiResult paramPoiResult, int paramInt)
  {
    Log.i("amap/SearchUtils", "onPoiSearched mSearchEvent = " + d + ", result = " + paramPoiResult + ", resultCode = " + paramInt);
    if (f.a()) {
      return;
    }
    switch (d)
    {
    case 4: 
    case 7: 
    case 8: 
    case 10: 
    case 12: 
    case 13: 
    case 14: 
    default: 
      return;
    case 3: 
    case 5: 
    case 9: 
    case 11: 
      b(paramPoiResult, paramInt);
      return;
    case 6: 
      c(paramPoiResult, paramInt);
      return;
    }
    a(paramPoiResult, paramInt);
  }
  
  public void onRegeocodeSearched(RegeocodeResult paramRegeocodeResult, int paramInt)
  {
    if (f.a()) {}
    do
    {
      return;
      Log.i("amap/SearchUtils", "onRegeocodeSearched mSearchEvent = " + d + ", result = " + paramRegeocodeResult + ", resultCode = " + paramInt);
    } while ((paramInt != 0) || (paramRegeocodeResult == null) || (paramRegeocodeResult.getRegeocodeAddress() == null) || (paramRegeocodeResult.getRegeocodeAddress().getFormatAddress() == null));
    switch (d)
    {
    case 3: 
    default: 
      return;
    case 2: 
      b(a(paramRegeocodeResult, false));
      a(15, j);
      return;
    }
    c(paramRegeocodeResult.getRegeocodeAddress().getCity());
  }
  
  public class a
    extends Handler
  {
    public a(Looper paramLooper)
    {
      super();
    }
    
    public void handleMessage(Message paramMessage)
    {
      Log.i("amap/SearchUtils", "handleMessage msg.what = " + what);
      switch (what)
      {
      case 7: 
      case 8: 
      case 10: 
      case 12: 
      case 13: 
      case 14: 
      default: 
        return;
      case 6: 
        paramMessage = (LatLng)obj;
        lb.c(lb.this).setQuery(lb.a(lb.this, lb.a(lb.this), null, lb.b(lb.this)));
        lb.c(lb.this).setBound(new PoiSearch.SearchBound(new LatLonPoint(latitude, longitude), 50000));
        lb.c(lb.this).searchPOIAsyn();
        return;
      case 2: 
      case 4: 
        paramMessage = (LatLng)obj;
        a(paramMessage);
        return;
      case 15: 
      case 16: 
        paramMessage = (LatLng)obj;
        lb.c(lb.this).setQuery(lb.a(lb.this, "", "餐饮服务|生活服务|公司|购物|汽车|体育|医疗|政府|科教|建筑", lb.b(lb.this)));
        lb.c(lb.this).setBound(new PoiSearch.SearchBound(new LatLonPoint(latitude, longitude), 50000));
        lb.c(lb.this).searchPOIAsyn();
        return;
      }
      lb.c(lb.this).setQuery(new PoiSearch.Query(lb.a(lb.this), null, lb.b(lb.this)));
      lb.c(lb.this).searchPOIAsyn();
    }
  }
  
  public static abstract interface b
  {
    public abstract void a(int paramInt);
    
    public abstract void a(LatLng paramLatLng, String paramString1, String paramString2, boolean paramBoolean);
    
    public abstract void a(PoiResult paramPoiResult, int paramInt);
    
    public abstract void a(String paramString);
    
    public abstract void a(String paramString1, String paramString2);
    
    public abstract void a(ArrayList<PoiItem> paramArrayList);
    
    public abstract void a(List<PoiItem> paramList);
    
    public abstract void a(ld paramld);
    
    public abstract void a(boolean paramBoolean);
    
    public abstract boolean a();
    
    public abstract LatLng b();
    
    public abstract void c();
  }
}

/* Location:
 * Qualified Name:     lb
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */