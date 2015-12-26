package com.amap.api.services.core;

import com.amap.api.services.busline.BusLineItem;
import com.amap.api.services.busline.BusStationItem;
import com.amap.api.services.district.DistrictItem;
import com.amap.api.services.geocoder.BusinessArea;
import com.amap.api.services.geocoder.GeocodeAddress;
import com.amap.api.services.geocoder.RegeocodeAddress;
import com.amap.api.services.geocoder.RegeocodeRoad;
import com.amap.api.services.geocoder.StreetNumber;
import com.amap.api.services.help.Tip;
import com.amap.api.services.poisearch.Cinema;
import com.amap.api.services.poisearch.Dining;
import com.amap.api.services.poisearch.Discount;
import com.amap.api.services.poisearch.Groupbuy;
import com.amap.api.services.poisearch.Hotel;
import com.amap.api.services.poisearch.Photo;
import com.amap.api.services.poisearch.PoiItemDetail;
import com.amap.api.services.poisearch.PoiItemDetail.DeepType;
import com.amap.api.services.poisearch.Scenic;
import com.amap.api.services.road.Crossroad;
import com.amap.api.services.route.BusPath;
import com.amap.api.services.route.BusRouteResult;
import com.amap.api.services.route.BusStep;
import com.amap.api.services.route.District;
import com.amap.api.services.route.Doorway;
import com.amap.api.services.route.DrivePath;
import com.amap.api.services.route.DriveRouteResult;
import com.amap.api.services.route.DriveStep;
import com.amap.api.services.route.RouteBusLineItem;
import com.amap.api.services.route.RouteBusWalkItem;
import com.amap.api.services.route.RouteSearchCity;
import com.amap.api.services.route.WalkPath;
import com.amap.api.services.route.WalkRouteResult;
import com.amap.api.services.route.WalkStep;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class j
{
  public static Scenic a(PoiItemDetail paramPoiItemDetail, JSONObject paramJSONObject1, JSONObject paramJSONObject2)
  {
    paramJSONObject2 = new Scenic();
    paramJSONObject2.setIntro(b(paramJSONObject1, "intro"));
    paramJSONObject2.setRating(b(paramJSONObject1, "rating"));
    paramJSONObject2.setDeepsrc(b(paramJSONObject1, "deepsrc"));
    paramJSONObject2.setLevel(b(paramJSONObject1, "level"));
    paramJSONObject2.setPrice(b(paramJSONObject1, "price"));
    paramJSONObject2.setSeason(b(paramJSONObject1, "season"));
    paramJSONObject2.setRecommend(b(paramJSONObject1, "recommend"));
    paramJSONObject2.setTheme(b(paramJSONObject1, "theme"));
    paramJSONObject2.setOrderWapUrl(b(paramJSONObject1, "ordering_wap_url"));
    paramJSONObject2.setOrderWebUrl(b(paramJSONObject1, "ordering_web_url"));
    paramJSONObject2.setOpentimeGDF(b(paramJSONObject1, "opentime_GDF"));
    paramJSONObject2.setOpentime(b(paramJSONObject1, "opentime"));
    paramJSONObject2.setPhotos(l(paramJSONObject1));
    paramPoiItemDetail.setDeepType(PoiItemDetail.DeepType.SCENIC);
    paramPoiItemDetail.setScenic(paramJSONObject2);
    return paramJSONObject2;
  }
  
  public static ArrayList<SuggestionCity> a(JSONObject paramJSONObject)
  {
    ArrayList localArrayList = new ArrayList();
    if (!paramJSONObject.has("cities")) {
      return localArrayList;
    }
    paramJSONObject = paramJSONObject.optJSONArray("cities");
    if (paramJSONObject == null) {
      return localArrayList;
    }
    int i = 0;
    if (i < paramJSONObject.length())
    {
      JSONObject localJSONObject = paramJSONObject.optJSONObject(i);
      if (localJSONObject == null) {}
      for (;;)
      {
        i += 1;
        break;
        localArrayList.add(new SuggestionCity(b(localJSONObject, "name"), b(localJSONObject, "citycode"), b(localJSONObject, "adcode"), i(b(localJSONObject, "num"))));
      }
    }
    return localArrayList;
  }
  
  public static List<BusPath> a(JSONArray paramJSONArray)
  {
    ArrayList localArrayList1 = new ArrayList();
    if (paramJSONArray == null) {
      return localArrayList1;
    }
    int i = 0;
    if (i < paramJSONArray.length())
    {
      BusPath localBusPath = new BusPath();
      Object localObject1 = paramJSONArray.optJSONObject(i);
      if (localObject1 == null) {}
      for (;;)
      {
        i += 1;
        break;
        localBusPath.setCost(j(b((JSONObject)localObject1, "cost")));
        localBusPath.setDuration(k(b((JSONObject)localObject1, "duration")));
        localBusPath.setNightBus(l(b((JSONObject)localObject1, "nightflag")));
        localBusPath.setWalkDistance(j(b((JSONObject)localObject1, "walking_distance")));
        localObject1 = ((JSONObject)localObject1).optJSONArray("segments");
        if (localObject1 != null)
        {
          ArrayList localArrayList2 = new ArrayList();
          int j = 0;
          float f2 = 0.0F;
          float f3 = 0.0F;
          if (j < ((JSONArray)localObject1).length())
          {
            Object localObject2 = ((JSONArray)localObject1).optJSONObject(j);
            float f5;
            float f4;
            if (localObject2 == null)
            {
              f5 = f3;
              f4 = f2;
            }
            for (;;)
            {
              j += 1;
              f2 = f4;
              f3 = f5;
              break;
              localObject2 = q((JSONObject)localObject2);
              f4 = f2;
              f5 = f3;
              if (localObject2 != null)
              {
                localArrayList2.add(localObject2);
                float f1 = f2;
                if (((BusStep)localObject2).getWalk() != null) {
                  f1 = f2 + ((BusStep)localObject2).getWalk().getDistance();
                }
                f4 = f1;
                f5 = f3;
                if (((BusStep)localObject2).getBusLine() != null)
                {
                  f5 = f3 + ((BusStep)localObject2).getBusLine().getDistance();
                  f4 = f1;
                }
              }
            }
          }
          localBusPath.setSteps(localArrayList2);
          localBusPath.setBusDistance(f3);
          localBusPath.setWalkDistance(f2);
          localArrayList1.add(localBusPath);
        }
      }
    }
    return localArrayList1;
  }
  
  public static void a(Discount paramDiscount, JSONObject paramJSONObject)
  {
    paramDiscount.initPhotos(l(paramJSONObject));
  }
  
  public static void a(Groupbuy paramGroupbuy, JSONObject paramJSONObject)
  {
    paramGroupbuy.initPhotos(l(paramJSONObject));
  }
  
  public static void a(PoiItemDetail paramPoiItemDetail, JSONObject paramJSONObject)
  {
    if (paramJSONObject == null) {}
    do
    {
      return;
      if (paramPoiItemDetail.isGroupbuyInfo()) {
        b(paramPoiItemDetail, paramJSONObject);
      }
    } while (!paramPoiItemDetail.isDiscountInfo());
    c(paramPoiItemDetail, paramJSONObject);
  }
  
  public static void a(DriveStep paramDriveStep, JSONObject paramJSONObject)
  {
    for (;;)
    {
      ArrayList localArrayList;
      int i;
      try
      {
        localArrayList = new ArrayList();
        paramJSONObject = paramJSONObject.optJSONArray("cities");
        if (paramJSONObject != null) {
          break label125;
        }
        return;
      }
      catch (JSONException paramDriveStep)
      {
        RouteSearchCity localRouteSearchCity;
        JSONObject localJSONObject;
        d.a(paramDriveStep, "JSONHelper", "parseCrossCity");
        return;
      }
      if (i < paramJSONObject.length())
      {
        localRouteSearchCity = new RouteSearchCity();
        localJSONObject = paramJSONObject.optJSONObject(i);
        if (localJSONObject != null)
        {
          localRouteSearchCity.setSearchCityName(b(localJSONObject, "name"));
          localRouteSearchCity.setSearchCitycode(b(localJSONObject, "citycode"));
          localRouteSearchCity.setSearchCityhAdCode(b(localJSONObject, "adcode"));
          a(localRouteSearchCity, localJSONObject);
          localArrayList.add(localRouteSearchCity);
        }
      }
      else
      {
        paramDriveStep.setRouteSearchCityList(localArrayList);
        return;
        label125:
        i = 0;
        continue;
      }
      i += 1;
    }
  }
  
  public static void a(RouteSearchCity paramRouteSearchCity, JSONObject paramJSONObject)
  {
    if (!paramJSONObject.has("districts")) {
      return;
    }
    ArrayList localArrayList;
    try
    {
      localArrayList = new ArrayList();
      paramJSONObject = paramJSONObject.optJSONArray("districts");
      if (paramJSONObject == null)
      {
        paramRouteSearchCity.setDistricts(localArrayList);
        return;
      }
    }
    catch (JSONException paramRouteSearchCity)
    {
      d.a(paramRouteSearchCity, "JSONHelper", "parseCrossDistricts");
      return;
    }
    int i = 0;
    for (;;)
    {
      if (i < paramJSONObject.length())
      {
        District localDistrict = new District();
        JSONObject localJSONObject = paramJSONObject.optJSONObject(i);
        if (localJSONObject != null)
        {
          localDistrict.setDistrictName(b(localJSONObject, "name"));
          localDistrict.setDistrictAdcode(b(localJSONObject, "adcode"));
          localArrayList.add(localDistrict);
        }
      }
      else
      {
        paramRouteSearchCity.setDistricts(localArrayList);
        return;
      }
      i += 1;
    }
  }
  
  public static void a(JSONArray paramJSONArray, RegeocodeAddress paramRegeocodeAddress)
  {
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    if (i < paramJSONArray.length())
    {
      Crossroad localCrossroad = new Crossroad();
      JSONObject localJSONObject = paramJSONArray.optJSONObject(i);
      if (localJSONObject == null) {}
      for (;;)
      {
        i += 1;
        break;
        localCrossroad.setId(b(localJSONObject, "id"));
        localCrossroad.setDirection(b(localJSONObject, "direction"));
        localCrossroad.setDistance(j(b(localJSONObject, "distance")));
        localCrossroad.setCenterPoint(c(localJSONObject, "location"));
        localCrossroad.setFirstRoadId(b(localJSONObject, "first_id"));
        localCrossroad.setFirstRoadName(b(localJSONObject, "first_name"));
        localCrossroad.setSecondRoadId(b(localJSONObject, "second_id"));
        localCrossroad.setSecondRoadName(b(localJSONObject, "second_name"));
        localArrayList.add(localCrossroad);
      }
    }
    paramRegeocodeAddress.setCrossroads(localArrayList);
  }
  
  public static void a(JSONArray paramJSONArray, ArrayList<DistrictItem> paramArrayList, DistrictItem paramDistrictItem)
  {
    if (paramJSONArray == null) {}
    do
    {
      return;
      int i = 0;
      if (i < paramJSONArray.length())
      {
        JSONObject localJSONObject = paramJSONArray.optJSONObject(i);
        if (localJSONObject == null) {}
        for (;;)
        {
          i += 1;
          break;
          paramArrayList.add(m(localJSONObject));
        }
      }
    } while (paramDistrictItem == null);
    paramDistrictItem.setSubDistrict(paramArrayList);
  }
  
  public static void a(JSONObject paramJSONObject, RegeocodeAddress paramRegeocodeAddress)
  {
    paramRegeocodeAddress.setProvince(b(paramJSONObject, "province"));
    paramRegeocodeAddress.setCity(b(paramJSONObject, "city"));
    paramRegeocodeAddress.setCityCode(b(paramJSONObject, "citycode"));
    paramRegeocodeAddress.setAdCode(b(paramJSONObject, "adcode"));
    paramRegeocodeAddress.setDistrict(b(paramJSONObject, "district"));
    paramRegeocodeAddress.setTownship(b(paramJSONObject, "township"));
    paramRegeocodeAddress.setNeighborhood(b(paramJSONObject.optJSONObject("neighborhood"), "name"));
    paramRegeocodeAddress.setBuilding(b(paramJSONObject.optJSONObject("building"), "name"));
    StreetNumber localStreetNumber = new StreetNumber();
    JSONObject localJSONObject = paramJSONObject.optJSONObject("streetNumber");
    localStreetNumber.setStreet(b(localJSONObject, "street"));
    localStreetNumber.setNumber(b(localJSONObject, "number"));
    localStreetNumber.setLatLonPoint(c(localJSONObject, "location"));
    localStreetNumber.setDirection(b(localJSONObject, "direction"));
    localStreetNumber.setDistance(j(b(localJSONObject, "distance")));
    paramRegeocodeAddress.setStreetNumber(localStreetNumber);
    paramRegeocodeAddress.setBusinessAreas(p(paramJSONObject));
  }
  
  public static boolean a(String paramString)
  {
    try
    {
      int i;
      if (!paramString.equals(""))
      {
        i = Integer.parseInt(paramString);
        if (i != 0) {
          break label21;
        }
      }
      label21:
      while (i != 1) {
        return false;
      }
      return true;
    }
    catch (NumberFormatException paramString)
    {
      d.a(paramString, "JSONHelper", "ordingStr2Boolean");
      return false;
    }
    catch (Exception paramString)
    {
      d.a(paramString, "JSONHelper", "ordingStr2BooleanException");
    }
    return false;
  }
  
  public static boolean a(JSONObject paramJSONObject, String paramString)
  {
    return a(b(paramJSONObject.optJSONObject("biz_ext"), paramString));
  }
  
  public static BusRouteResult b(String paramString)
  {
    try
    {
      Object localObject = new JSONObject(paramString);
      if (!((JSONObject)localObject).has("route")) {
        return null;
      }
      paramString = new BusRouteResult();
      localObject = ((JSONObject)localObject).optJSONObject("route");
      if (localObject != null)
      {
        paramString.setStartPos(c((JSONObject)localObject, "origin"));
        paramString.setTargetPos(c((JSONObject)localObject, "destination"));
        paramString.setTaxiCost(j(b((JSONObject)localObject, "taxi_cost")));
        if (((JSONObject)localObject).has("transits"))
        {
          localObject = ((JSONObject)localObject).optJSONArray("transits");
          if (localObject != null)
          {
            paramString.setPaths(a((JSONArray)localObject));
            return paramString;
          }
        }
      }
    }
    catch (JSONException paramString)
    {
      throw new AMapException("协议解析错误 - ProtocolException");
    }
    return paramString;
  }
  
  public static String b(JSONObject paramJSONObject, String paramString)
  {
    if (paramJSONObject == null) {}
    while ((!paramJSONObject.has(paramString)) || (paramJSONObject.getString(paramString).equals("[]"))) {
      return "";
    }
    return paramJSONObject.optString(paramString);
  }
  
  public static ArrayList<String> b(JSONObject paramJSONObject)
  {
    ArrayList localArrayList = new ArrayList();
    paramJSONObject = paramJSONObject.optJSONArray("keywords");
    if (paramJSONObject == null) {
      return localArrayList;
    }
    int i = 0;
    while (i < paramJSONObject.length())
    {
      localArrayList.add(paramJSONObject.optString(i));
      i += 1;
    }
    return localArrayList;
  }
  
  public static void b(PoiItemDetail paramPoiItemDetail, JSONObject paramJSONObject)
  {
    if (paramJSONObject == null) {}
    do
    {
      return;
      paramJSONObject = paramJSONObject.optJSONArray("groupbuys");
    } while (paramJSONObject == null);
    int i = 0;
    label19:
    JSONObject localJSONObject;
    if (i < paramJSONObject.length())
    {
      localJSONObject = paramJSONObject.optJSONObject(i);
      if (localJSONObject != null) {
        break label44;
      }
    }
    for (;;)
    {
      i += 1;
      break label19;
      break;
      label44:
      Groupbuy localGroupbuy = new Groupbuy();
      localGroupbuy.setTypeCode(b(localJSONObject, "typecode"));
      localGroupbuy.setTypeDes(b(localJSONObject, "type"));
      localGroupbuy.setDetail(b(localJSONObject, "detail"));
      localGroupbuy.setStartTime(d.c(b(localJSONObject, "start_time")));
      localGroupbuy.setEndTime(d.c(b(localJSONObject, "end_time")));
      localGroupbuy.setCount(i(b(localJSONObject, "num")));
      localGroupbuy.setSoldCount(i(b(localJSONObject, "sold_num")));
      localGroupbuy.setOriginalPrice(j(b(localJSONObject, "original_price")));
      localGroupbuy.setGroupbuyPrice(j(b(localJSONObject, "groupbuy_price")));
      localGroupbuy.setDiscount(j(b(localJSONObject, "discount")));
      localGroupbuy.setTicketAddress(b(localJSONObject, "ticket_address"));
      localGroupbuy.setTicketTel(b(localJSONObject, "ticket_tel"));
      localGroupbuy.setUrl(b(localJSONObject, "url"));
      localGroupbuy.setProvider(b(localJSONObject, "provider"));
      a(localGroupbuy, localJSONObject);
      paramPoiItemDetail.addGroupbuy(localGroupbuy);
    }
  }
  
  public static void b(PoiItemDetail paramPoiItemDetail, JSONObject paramJSONObject1, JSONObject paramJSONObject2)
  {
    Cinema localCinema = new Cinema();
    localCinema.setIntro(b(paramJSONObject1, "intro"));
    localCinema.setRating(b(paramJSONObject1, "rating"));
    localCinema.setDeepsrc(b(paramJSONObject1, "deepsrc"));
    localCinema.setParking(b(paramJSONObject1, "parking"));
    localCinema.setOpentimeGDF(b(paramJSONObject1, "opentime_GDF"));
    localCinema.setOpentime(b(paramJSONObject1, "opentime"));
    localCinema.setPhotos(l(paramJSONObject1));
    if (k(paramJSONObject2)) {
      localCinema.setSeatOrdering(a(paramJSONObject2, "seat_ordering"));
    }
    paramPoiItemDetail.setDeepType(PoiItemDetail.DeepType.CINEMA);
    paramPoiItemDetail.setCinema(localCinema);
  }
  
  public static void b(JSONArray paramJSONArray, RegeocodeAddress paramRegeocodeAddress)
  {
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    if (i < paramJSONArray.length())
    {
      RegeocodeRoad localRegeocodeRoad = new RegeocodeRoad();
      JSONObject localJSONObject = paramJSONArray.optJSONObject(i);
      if (localJSONObject == null) {}
      for (;;)
      {
        i += 1;
        break;
        localRegeocodeRoad.setId(b(localJSONObject, "id"));
        localRegeocodeRoad.setName(b(localJSONObject, "name"));
        localRegeocodeRoad.setLatLngPoint(c(localJSONObject, "location"));
        localRegeocodeRoad.setDirection(b(localJSONObject, "direction"));
        localRegeocodeRoad.setDistance(j(b(localJSONObject, "distance")));
        localArrayList.add(localRegeocodeRoad);
      }
    }
    paramRegeocodeAddress.setRoads(localArrayList);
  }
  
  public static LatLonPoint c(JSONObject paramJSONObject, String paramString)
  {
    if (paramJSONObject == null) {}
    while (!paramJSONObject.has(paramString)) {
      return null;
    }
    return f(paramJSONObject.optString(paramString));
  }
  
  public static DriveRouteResult c(String paramString)
  {
    for (;;)
    {
      ArrayList localArrayList1;
      int i;
      int j;
      try
      {
        Object localObject1 = new JSONObject(paramString);
        if (!((JSONObject)localObject1).has("route")) {
          return null;
        }
        paramString = new DriveRouteResult();
        localObject1 = ((JSONObject)localObject1).optJSONObject("route");
        if (localObject1 == null) {
          break label522;
        }
        paramString.setStartPos(c((JSONObject)localObject1, "origin"));
        paramString.setTargetPos(c((JSONObject)localObject1, "destination"));
        paramString.setTaxiCost(j(b((JSONObject)localObject1, "taxi_cost")));
        if (!((JSONObject)localObject1).has("paths")) {
          break label522;
        }
        localObject1 = ((JSONObject)localObject1).optJSONArray("paths");
        if (localObject1 == null) {
          break label522;
        }
        localArrayList1 = new ArrayList();
        i = 0;
        if (i < ((JSONArray)localObject1).length())
        {
          localDrivePath = new DrivePath();
          Object localObject2 = ((JSONArray)localObject1).optJSONObject(i);
          if (localObject2 == null) {
            break label524;
          }
          localDrivePath.setDistance(j(b((JSONObject)localObject2, "distance")));
          localDrivePath.setDuration(k(b((JSONObject)localObject2, "duration")));
          localDrivePath.setStrategy(b((JSONObject)localObject2, "strategy"));
          localDrivePath.setTolls(j(b((JSONObject)localObject2, "tolls")));
          localDrivePath.setTollDistance(j(b((JSONObject)localObject2, "toll_distance")));
          localObject2 = ((JSONObject)localObject2).optJSONArray("steps");
          if (localObject2 == null) {
            break label524;
          }
          localArrayList2 = new ArrayList();
          j = 0;
          if (j < ((JSONArray)localObject2).length())
          {
            DriveStep localDriveStep = new DriveStep();
            JSONObject localJSONObject = ((JSONArray)localObject2).optJSONObject(j);
            if (localJSONObject == null) {
              break label531;
            }
            localDriveStep.setInstruction(b(localJSONObject, "instruction"));
            localDriveStep.setOrientation(b(localJSONObject, "orientation"));
            localDriveStep.setRoad(b(localJSONObject, "road"));
            localDriveStep.setDistance(j(b(localJSONObject, "distance")));
            localDriveStep.setTolls(j(b(localJSONObject, "tolls")));
            localDriveStep.setTollDistance(j(b(localJSONObject, "toll_distance")));
            localDriveStep.setTollRoad(b(localJSONObject, "toll_road"));
            localDriveStep.setDuration(j(b(localJSONObject, "duration")));
            localDriveStep.setPolyline(d(localJSONObject, "polyline"));
            localDriveStep.setAction(b(localJSONObject, "action"));
            localDriveStep.setAssistantAction(b(localJSONObject, "assistant_action"));
            a(localDriveStep, localJSONObject);
            localArrayList2.add(localDriveStep);
          }
        }
      }
      catch (JSONException paramString)
      {
        DrivePath localDrivePath;
        ArrayList localArrayList2;
        d.a(paramString, "JSONHelper", "parseDriveRoute");
        throw new AMapException("协议解析错误 - ProtocolException");
        localDrivePath.setSteps(localArrayList2);
        localArrayList1.add(localDrivePath);
        break label524;
      }
      catch (Throwable paramString)
      {
        d.a(paramString, "JSONHelper", "parseDriveRouteThrowable");
        throw new AMapException("协议解析错误 - ProtocolException");
      }
      paramString.setPaths(localArrayList1);
      label522:
      return paramString;
      label524:
      i += 1;
      continue;
      label531:
      j += 1;
    }
  }
  
  public static ArrayList<PoiItem> c(JSONObject paramJSONObject)
  {
    ArrayList localArrayList = new ArrayList();
    if (paramJSONObject == null) {
      return localArrayList;
    }
    paramJSONObject = paramJSONObject.optJSONArray("pois");
    if ((paramJSONObject == null) || (paramJSONObject.length() == 0)) {
      return localArrayList;
    }
    int i = 0;
    if (i < paramJSONObject.length())
    {
      JSONObject localJSONObject = paramJSONObject.optJSONObject(i);
      if (localJSONObject == null) {}
      for (;;)
      {
        i += 1;
        break;
        localArrayList.add(d(localJSONObject));
      }
    }
    return localArrayList;
  }
  
  public static void c(PoiItemDetail paramPoiItemDetail, JSONObject paramJSONObject)
  {
    paramJSONObject = paramJSONObject.optJSONArray("discounts");
    if (paramJSONObject == null) {
      return;
    }
    int i = 0;
    label15:
    JSONObject localJSONObject;
    if (i < paramJSONObject.length())
    {
      localJSONObject = paramJSONObject.optJSONObject(i);
      if (localJSONObject != null) {
        break label40;
      }
    }
    for (;;)
    {
      i += 1;
      break label15;
      break;
      label40:
      Discount localDiscount = new Discount();
      localDiscount.setTitle(b(localJSONObject, "title"));
      localDiscount.setDetail(b(localJSONObject, "detail"));
      localDiscount.setStartTime(d.c(b(localJSONObject, "start_time")));
      localDiscount.setEndTime(d.c(b(localJSONObject, "end_time")));
      localDiscount.setSoldCount(i(b(localJSONObject, "sold_num")));
      localDiscount.setUrl(b(localJSONObject, "url"));
      localDiscount.setProvider(b(localJSONObject, "provider"));
      a(localDiscount, localJSONObject);
      paramPoiItemDetail.addDiscount(localDiscount);
    }
  }
  
  public static void c(PoiItemDetail paramPoiItemDetail, JSONObject paramJSONObject1, JSONObject paramJSONObject2)
  {
    paramJSONObject2 = new Hotel();
    paramJSONObject2.setStar(b(paramJSONObject1, "star"));
    paramJSONObject2.setIntro(b(paramJSONObject1, "intro"));
    paramJSONObject2.setRating(b(paramJSONObject1, "rating"));
    paramJSONObject2.setLowestPrice(b(paramJSONObject1, "lowest_price"));
    paramJSONObject2.setDeepsrc(b(paramJSONObject1, "deepsrc"));
    paramJSONObject2.setFaciRating(b(paramJSONObject1, "faci_rating"));
    paramJSONObject2.setHealthRating(b(paramJSONObject1, "health_rating"));
    paramJSONObject2.setEnvironmentRating(b(paramJSONObject1, "environment_rating"));
    paramJSONObject2.setServiceRating(b(paramJSONObject1, "service_rating"));
    paramJSONObject2.setTraffic(b(paramJSONObject1, "traffic"));
    paramJSONObject2.setAddition(b(paramJSONObject1, "addition"));
    paramJSONObject2.setPhotos(l(paramJSONObject1));
    paramPoiItemDetail.setDeepType(PoiItemDetail.DeepType.HOTEL);
    paramPoiItemDetail.setHotel(paramJSONObject2);
  }
  
  public static PoiItemDetail d(JSONObject paramJSONObject)
  {
    PoiItemDetail localPoiItemDetail = new PoiItemDetail(b(paramJSONObject, "id"), c(paramJSONObject, "location"), b(paramJSONObject, "name"), b(paramJSONObject, "address"));
    localPoiItemDetail.setAdCode(b(paramJSONObject, "adcode"));
    localPoiItemDetail.setProvinceName(b(paramJSONObject, "pname"));
    localPoiItemDetail.setCityName(b(paramJSONObject, "cityname"));
    localPoiItemDetail.setAdName(b(paramJSONObject, "adname"));
    localPoiItemDetail.setCityCode(b(paramJSONObject, "citycode"));
    localPoiItemDetail.setProvinceCode(b(paramJSONObject, "pcode"));
    localPoiItemDetail.setDirection(b(paramJSONObject, "direction"));
    String str;
    if (paramJSONObject.has("distance"))
    {
      str = b(paramJSONObject, "distance");
      if (h(str)) {}
    }
    try
    {
      localPoiItemDetail.setDistance((int)Float.parseFloat(str));
      if (localPoiItemDetail.getDistance() == 0) {
        localPoiItemDetail.setDistance(-1);
      }
      localPoiItemDetail.setTel(b(paramJSONObject, "tel"));
      localPoiItemDetail.setTypeDes(b(paramJSONObject, "type"));
      localPoiItemDetail.setEnter(c(paramJSONObject, "entr_location"));
      localPoiItemDetail.setExit(c(paramJSONObject, "exit_location"));
      localPoiItemDetail.setWebsite(b(paramJSONObject, "website"));
      localPoiItemDetail.setPostcode(b(paramJSONObject, "citycode"));
      localPoiItemDetail.setEmail(b(paramJSONObject, "email"));
      if (g(b(paramJSONObject, "groupbuy_num")))
      {
        localPoiItemDetail.setGroupbuyInfo(false);
        if (!g(b(paramJSONObject, "discount_num"))) {
          break label324;
        }
        localPoiItemDetail.setDiscountInfo(false);
        if (!g(b(paramJSONObject, "indoor_map"))) {
          break label332;
        }
        localPoiItemDetail.setIndoorMap(false);
        return localPoiItemDetail;
      }
    }
    catch (NumberFormatException localNumberFormatException)
    {
      for (;;)
      {
        d.a(localNumberFormatException, "JSONHelper", "parseBasePoi");
      }
    }
    catch (Exception localException)
    {
      for (;;)
      {
        d.a(localException, "JSONHelper", "parseBasePoi");
        continue;
        localPoiItemDetail.setGroupbuyInfo(true);
        continue;
        label324:
        localPoiItemDetail.setDiscountInfo(true);
      }
      label332:
      localPoiItemDetail.setIndoorMap(true);
    }
    return localPoiItemDetail;
  }
  
  public static WalkRouteResult d(String paramString)
  {
    label116:
    int j;
    try
    {
      Object localObject1 = new JSONObject(paramString);
      if (!((JSONObject)localObject1).has("route")) {
        return null;
      }
      paramString = new WalkRouteResult();
      try
      {
        localObject2 = ((JSONObject)localObject1).optJSONObject("route");
        paramString.setStartPos(c((JSONObject)localObject2, "origin"));
        paramString.setTargetPos(c((JSONObject)localObject2, "destination"));
        if (!((JSONObject)localObject2).has("paths")) {
          break label408;
        }
        localObject1 = new ArrayList();
        localObject2 = ((JSONObject)localObject2).optJSONArray("paths");
        if (localObject2 != null) {
          break label116;
        }
        paramString.setPaths((List)localObject1);
        return paramString;
      }
      catch (JSONException localJSONException1) {}
    }
    catch (JSONException localJSONException2)
    {
      for (;;)
      {
        Object localObject2;
        WalkPath localWalkPath;
        Object localObject3;
        ArrayList localArrayList;
        paramString = null;
      }
    }
    d.a(localJSONException1, "JSONHelper", "parseWalkRoute");
    return paramString;
    int i = 0;
    if (i < ((JSONArray)localObject2).length())
    {
      localWalkPath = new WalkPath();
      localObject3 = ((JSONArray)localObject2).optJSONObject(i);
      if (localObject3 == null) {
        break label410;
      }
      localWalkPath.setDistance(j(b((JSONObject)localObject3, "distance")));
      localWalkPath.setDuration(k(b((JSONObject)localObject3, "duration")));
      if (((JSONObject)localObject3).has("steps"))
      {
        localObject3 = ((JSONObject)localObject3).optJSONArray("steps");
        localArrayList = new ArrayList();
        if (localObject3 == null) {
          break label410;
        }
        j = 0;
      }
    }
    for (;;)
    {
      if (j < ((JSONArray)localObject3).length())
      {
        WalkStep localWalkStep = new WalkStep();
        JSONObject localJSONObject = ((JSONArray)localObject3).optJSONObject(j);
        if (localJSONObject != null)
        {
          localWalkStep.setInstruction(b(localJSONObject, "instruction"));
          localWalkStep.setOrientation(b(localJSONObject, "orientation"));
          localWalkStep.setRoad(b(localJSONObject, "road"));
          localWalkStep.setDistance(j(b(localJSONObject, "distance")));
          localWalkStep.setDuration(j(b(localJSONObject, "duration")));
          localWalkStep.setPolyline(d(localJSONObject, "polyline"));
          localWalkStep.setAction(b(localJSONObject, "action"));
          localWalkStep.setAssistantAction(b(localJSONObject, "assistant_action"));
          localArrayList.add(localWalkStep);
        }
      }
      else
      {
        localWalkPath.setSteps(localArrayList);
        localJSONException1.add(localWalkPath);
        break label410;
        paramString.setPaths(localJSONException1);
        return paramString;
        label408:
        return paramString;
        label410:
        i += 1;
        break;
      }
      j += 1;
    }
  }
  
  public static ArrayList<LatLonPoint> d(JSONObject paramJSONObject, String paramString)
  {
    if (paramJSONObject.has(paramString)) {
      return e(paramJSONObject.getString(paramString));
    }
    return null;
  }
  
  public static void d(PoiItemDetail paramPoiItemDetail, JSONObject paramJSONObject1, JSONObject paramJSONObject2)
  {
    Dining localDining = new Dining();
    localDining.setCuisines(b(paramJSONObject1, "cuisines"));
    localDining.setTag(b(paramJSONObject1, "tag"));
    localDining.setIntro(b(paramJSONObject1, "intro"));
    localDining.setRating(b(paramJSONObject1, "rating"));
    localDining.setCpRating(b(paramJSONObject1, "cp_rating"));
    localDining.setDeepsrc(b(paramJSONObject1, "deepsrc"));
    localDining.setTasteRating(b(paramJSONObject1, "taste_rating"));
    localDining.setEnvironmentRating(b(paramJSONObject1, "environment_rating"));
    localDining.setServiceRating(b(paramJSONObject1, "service_rating"));
    localDining.setCost(b(paramJSONObject1, "cost"));
    localDining.setRecommend(b(paramJSONObject1, "recommend"));
    localDining.setAtmosphere(b(paramJSONObject1, "atmosphere"));
    localDining.setOrderingWapUrl(b(paramJSONObject1, "ordering_wap_url"));
    localDining.setOrderingWebUrl(b(paramJSONObject1, "ordering_web_url"));
    localDining.setOrderinAppUrl(b(paramJSONObject1, "ordering_app_url"));
    localDining.setOpentimeGDF(b(paramJSONObject1, "opentime_GDF"));
    localDining.setOpentime(b(paramJSONObject1, "opentime"));
    localDining.setAddition(b(paramJSONObject1, "addition"));
    localDining.setPhotos(l(paramJSONObject1));
    if (k(paramJSONObject2)) {
      localDining.setMealOrdering(a(paramJSONObject2, "meal_ordering"));
    }
    paramPoiItemDetail.setDeepType(PoiItemDetail.DeepType.DINING);
    paramPoiItemDetail.setDining(localDining);
  }
  
  public static ArrayList<LatLonPoint> e(String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    paramString = paramString.split(";");
    int i = 0;
    while (i < paramString.length)
    {
      localArrayList.add(f(paramString[i]));
      i += 1;
    }
    return localArrayList;
  }
  
  public static ArrayList<BusStationItem> e(JSONObject paramJSONObject)
  {
    ArrayList localArrayList = new ArrayList();
    if (paramJSONObject == null) {
      return localArrayList;
    }
    paramJSONObject = paramJSONObject.optJSONArray("busstops");
    if ((paramJSONObject == null) || (paramJSONObject.length() == 0)) {
      return localArrayList;
    }
    int i = 0;
    if (i < paramJSONObject.length())
    {
      JSONObject localJSONObject = paramJSONObject.optJSONObject(i);
      if (localJSONObject == null) {}
      for (;;)
      {
        i += 1;
        break;
        localArrayList.add(f(localJSONObject));
      }
    }
    return localArrayList;
  }
  
  public static void e(PoiItemDetail paramPoiItemDetail, JSONObject paramJSONObject1, JSONObject paramJSONObject2)
  {
    if (paramJSONObject1 == null) {}
    String str;
    do
    {
      return;
      str = b(paramJSONObject1, "type");
      if (str.equalsIgnoreCase("hotel")) {
        c(paramPoiItemDetail, paramJSONObject1, paramJSONObject2);
      }
      if (str.equalsIgnoreCase("dining")) {
        d(paramPoiItemDetail, paramJSONObject1, paramJSONObject2);
      }
      if (str.equalsIgnoreCase("cinema")) {
        b(paramPoiItemDetail, paramJSONObject1, paramJSONObject2);
      }
    } while (!str.equalsIgnoreCase("scenic"));
    a(paramPoiItemDetail, paramJSONObject1, paramJSONObject2);
  }
  
  public static BusStationItem f(JSONObject paramJSONObject)
  {
    BusStationItem localBusStationItem = g(paramJSONObject);
    if (localBusStationItem == null) {
      return localBusStationItem;
    }
    localBusStationItem.setAdCode(b(paramJSONObject, "adcode"));
    localBusStationItem.setCityCode(b(paramJSONObject, "citycode"));
    paramJSONObject = paramJSONObject.optJSONArray("buslines");
    ArrayList localArrayList = new ArrayList();
    if (paramJSONObject == null)
    {
      localBusStationItem.setBusLineItems(localArrayList);
      return localBusStationItem;
    }
    int i = 0;
    if (i < paramJSONObject.length())
    {
      JSONObject localJSONObject = paramJSONObject.optJSONObject(i);
      if (localJSONObject == null) {}
      for (;;)
      {
        i += 1;
        break;
        localArrayList.add(h(localJSONObject));
      }
    }
    localBusStationItem.setBusLineItems(localArrayList);
    return localBusStationItem;
  }
  
  public static LatLonPoint f(String paramString)
  {
    if ((paramString == null) || (paramString.equals("")) || (paramString.equals("[]"))) {}
    do
    {
      return null;
      paramString = paramString.split(",");
    } while (paramString.length != 2);
    double d = Double.parseDouble(paramString[0]);
    return new LatLonPoint(Double.parseDouble(paramString[1]), d);
  }
  
  public static BusStationItem g(JSONObject paramJSONObject)
  {
    BusStationItem localBusStationItem = new BusStationItem();
    localBusStationItem.setBusStationId(b(paramJSONObject, "id"));
    localBusStationItem.setLatLonPoint(c(paramJSONObject, "location"));
    localBusStationItem.setBusStationName(b(paramJSONObject, "name"));
    return localBusStationItem;
  }
  
  public static boolean g(String paramString)
  {
    return (paramString == null) || (paramString.equals("")) || (paramString.equals("0"));
  }
  
  public static BusLineItem h(JSONObject paramJSONObject)
  {
    BusLineItem localBusLineItem = new BusLineItem();
    localBusLineItem.setBusLineId(b(paramJSONObject, "id"));
    localBusLineItem.setBusLineType(b(paramJSONObject, "type"));
    localBusLineItem.setBusLineName(b(paramJSONObject, "name"));
    localBusLineItem.setDirectionsCoordinates(d(paramJSONObject, "polyline"));
    localBusLineItem.setCityCode(b(paramJSONObject, "citycode"));
    localBusLineItem.setOriginatingStation(b(paramJSONObject, "start_stop"));
    localBusLineItem.setTerminalStation(b(paramJSONObject, "end_stop"));
    return localBusLineItem;
  }
  
  public static boolean h(String paramString)
  {
    return (paramString == null) || (paramString.equals(""));
  }
  
  public static int i(String paramString)
  {
    if ((paramString == null) || (paramString.equals("")) || (paramString.equals("[]"))) {
      return 0;
    }
    try
    {
      int i = Integer.parseInt(paramString);
      return i;
    }
    catch (NumberFormatException paramString)
    {
      d.a(paramString, "JSONHelper", "str2int");
    }
    return 0;
  }
  
  public static ArrayList<BusLineItem> i(JSONObject paramJSONObject)
  {
    ArrayList localArrayList = new ArrayList();
    paramJSONObject = paramJSONObject.optJSONArray("buslines");
    if (paramJSONObject == null) {
      return localArrayList;
    }
    int i = 0;
    if (i < paramJSONObject.length())
    {
      JSONObject localJSONObject = paramJSONObject.optJSONObject(i);
      if (localJSONObject == null) {}
      for (;;)
      {
        i += 1;
        break;
        localArrayList.add(j(localJSONObject));
      }
    }
    return localArrayList;
  }
  
  public static float j(String paramString)
  {
    if ((paramString == null) || (paramString.equals("")) || (paramString.equals("[]"))) {
      return 0.0F;
    }
    try
    {
      float f = Float.parseFloat(paramString);
      return f;
    }
    catch (NumberFormatException paramString)
    {
      d.a(paramString, "JSONHelper", "str2float");
    }
    return 0.0F;
  }
  
  public static BusLineItem j(JSONObject paramJSONObject)
  {
    BusLineItem localBusLineItem = h(paramJSONObject);
    if (localBusLineItem == null) {
      return localBusLineItem;
    }
    localBusLineItem.setFirstBusTime(d.d(b(paramJSONObject, "start_time")));
    localBusLineItem.setLastBusTime(d.d(b(paramJSONObject, "end_time")));
    localBusLineItem.setBusCompany(b(paramJSONObject, "company"));
    localBusLineItem.setDistance(j(b(paramJSONObject, "distance")));
    localBusLineItem.setBasicPrice(j(b(paramJSONObject, "basic_price")));
    localBusLineItem.setTotalPrice(j(b(paramJSONObject, "total_price")));
    localBusLineItem.setBounds(d(paramJSONObject, "bounds"));
    ArrayList localArrayList = new ArrayList();
    paramJSONObject = paramJSONObject.optJSONArray("busstops");
    if (paramJSONObject == null)
    {
      localBusLineItem.setBusStations(localArrayList);
      return localBusLineItem;
    }
    int i = 0;
    if (i < paramJSONObject.length())
    {
      JSONObject localJSONObject = paramJSONObject.optJSONObject(i);
      if (localJSONObject == null) {}
      for (;;)
      {
        i += 1;
        break;
        localArrayList.add(g(localJSONObject));
      }
    }
    localBusLineItem.setBusStations(localArrayList);
    return localBusLineItem;
  }
  
  public static long k(String paramString)
  {
    if ((paramString == null) || (paramString.equals("")) || (paramString.equals("[]"))) {
      return 0L;
    }
    try
    {
      long l = Long.parseLong(paramString);
      return l;
    }
    catch (NumberFormatException paramString)
    {
      d.a(paramString, "JSONHelper", "str2long");
    }
    return 0L;
  }
  
  public static boolean k(JSONObject paramJSONObject)
  {
    if (paramJSONObject == null) {}
    while (!paramJSONObject.has("biz_ext")) {
      return false;
    }
    return true;
  }
  
  public static List<Photo> l(JSONObject paramJSONObject)
  {
    localArrayList = new ArrayList();
    if (paramJSONObject == null) {}
    for (;;)
    {
      return localArrayList;
      if (!paramJSONObject.has("photos")) {
        continue;
      }
      try
      {
        paramJSONObject = paramJSONObject.optJSONArray("photos");
        int i = 0;
        while (i < paramJSONObject.length())
        {
          JSONObject localJSONObject = paramJSONObject.optJSONObject(i);
          Photo localPhoto = new Photo();
          localPhoto.setTitle(b(localJSONObject, "title"));
          localPhoto.setUrl(b(localJSONObject, "url"));
          localArrayList.add(localPhoto);
          i += 1;
        }
        return localArrayList;
      }
      catch (Exception paramJSONObject)
      {
        d.a(paramJSONObject, "JSONHelper", "getPhotoList");
      }
    }
  }
  
  public static boolean l(String paramString)
  {
    if ((paramString == null) || (paramString.equals("")) || (paramString.equals("[]")) || (paramString.equals("0"))) {}
    while (!paramString.equals("1")) {
      return false;
    }
    return true;
  }
  
  public static DistrictItem m(JSONObject paramJSONObject)
  {
    DistrictItem localDistrictItem = new DistrictItem();
    localDistrictItem.setCitycode(b(paramJSONObject, "citycode"));
    localDistrictItem.setAdcode(b(paramJSONObject, "adcode"));
    localDistrictItem.setName(b(paramJSONObject, "name"));
    localDistrictItem.setLevel(b(paramJSONObject, "level"));
    localDistrictItem.setCenter(c(paramJSONObject, "center"));
    if (paramJSONObject.has("polyline"))
    {
      String str = paramJSONObject.getString("polyline");
      if ((str != null) && (str.length() > 0)) {
        localDistrictItem.setDistrictBoundary(str.split("\\|"));
      }
    }
    a(paramJSONObject.optJSONArray("districts"), new ArrayList(), localDistrictItem);
    return localDistrictItem;
  }
  
  public static ArrayList<GeocodeAddress> n(JSONObject paramJSONObject)
  {
    ArrayList localArrayList = new ArrayList();
    if (paramJSONObject == null) {
      return localArrayList;
    }
    paramJSONObject = paramJSONObject.optJSONArray("geocodes");
    if ((paramJSONObject == null) || (paramJSONObject.length() == 0)) {
      return localArrayList;
    }
    int i = 0;
    if (i < paramJSONObject.length())
    {
      JSONObject localJSONObject = paramJSONObject.optJSONObject(i);
      if (localJSONObject == null) {}
      for (;;)
      {
        i += 1;
        break;
        GeocodeAddress localGeocodeAddress = new GeocodeAddress();
        localGeocodeAddress.setFormatAddress(b(localJSONObject, "formatted_address"));
        localGeocodeAddress.setProvince(b(localJSONObject, "province"));
        localGeocodeAddress.setCity(b(localJSONObject, "city"));
        localGeocodeAddress.setDistrict(b(localJSONObject, "district"));
        localGeocodeAddress.setTownship(b(localJSONObject, "township"));
        localGeocodeAddress.setNeighborhood(b(localJSONObject.optJSONObject("neighborhood"), "name"));
        localGeocodeAddress.setBuilding(b(localJSONObject.optJSONObject("building"), "name"));
        localGeocodeAddress.setAdcode(b(localJSONObject, "adcode"));
        localGeocodeAddress.setLatLonPoint(c(localJSONObject, "location"));
        localGeocodeAddress.setLevel(b(localJSONObject, "level"));
        localArrayList.add(localGeocodeAddress);
      }
    }
    return localArrayList;
  }
  
  public static ArrayList<Tip> o(JSONObject paramJSONObject)
  {
    ArrayList localArrayList = new ArrayList();
    paramJSONObject = paramJSONObject.optJSONArray("tips");
    if (paramJSONObject == null) {
      return localArrayList;
    }
    int i = 0;
    if (i < paramJSONObject.length())
    {
      Tip localTip = new Tip();
      JSONObject localJSONObject = paramJSONObject.optJSONObject(i);
      if (localJSONObject == null) {}
      for (;;)
      {
        i += 1;
        break;
        localTip.setName(b(localJSONObject, "name"));
        localTip.setDistrict(b(localJSONObject, "district"));
        localTip.setAdcode(b(localJSONObject, "adcode"));
        localArrayList.add(localTip);
      }
    }
    return localArrayList;
  }
  
  public static List<BusinessArea> p(JSONObject paramJSONObject)
  {
    ArrayList localArrayList = new ArrayList();
    paramJSONObject = paramJSONObject.optJSONArray("businessAreas");
    if ((paramJSONObject == null) || (paramJSONObject.length() == 0)) {
      return localArrayList;
    }
    int i = 0;
    if (i < paramJSONObject.length())
    {
      BusinessArea localBusinessArea = new BusinessArea();
      JSONObject localJSONObject = paramJSONObject.optJSONObject(i);
      if (localJSONObject == null) {}
      for (;;)
      {
        i += 1;
        break;
        localBusinessArea.setCenterPoint(c(localJSONObject, "location"));
        localBusinessArea.setName(b(localJSONObject, "name"));
        localArrayList.add(localBusinessArea);
      }
    }
    return localArrayList;
  }
  
  public static BusStep q(JSONObject paramJSONObject)
  {
    if (paramJSONObject == null) {
      paramJSONObject = null;
    }
    BusStep localBusStep;
    JSONObject localJSONObject;
    do
    {
      return paramJSONObject;
      localBusStep = new BusStep();
      localJSONObject = paramJSONObject.optJSONObject("walking");
      if (localJSONObject != null) {
        localBusStep.setWalk(r(localJSONObject));
      }
      localJSONObject = paramJSONObject.optJSONObject("bus");
      if (localJSONObject != null) {
        localBusStep.setBusLines(s(localJSONObject));
      }
      localJSONObject = paramJSONObject.optJSONObject("entrance");
      if (localJSONObject != null) {
        localBusStep.setEntrance(t(localJSONObject));
      }
      localJSONObject = paramJSONObject.optJSONObject("exit");
      paramJSONObject = localBusStep;
    } while (localJSONObject == null);
    localBusStep.setExit(t(localJSONObject));
    return localBusStep;
  }
  
  public static RouteBusWalkItem r(JSONObject paramJSONObject)
  {
    if (paramJSONObject == null) {
      return null;
    }
    RouteBusWalkItem localRouteBusWalkItem = new RouteBusWalkItem();
    localRouteBusWalkItem.setOrigin(c(paramJSONObject, "origin"));
    localRouteBusWalkItem.setDestination(c(paramJSONObject, "destination"));
    localRouteBusWalkItem.setDistance(j(b(paramJSONObject, "distance")));
    localRouteBusWalkItem.setDuration(k(b(paramJSONObject, "duration")));
    if (!paramJSONObject.has("steps")) {
      return localRouteBusWalkItem;
    }
    paramJSONObject = paramJSONObject.optJSONArray("steps");
    if (paramJSONObject == null) {
      return localRouteBusWalkItem;
    }
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    if (i < paramJSONObject.length())
    {
      JSONObject localJSONObject = paramJSONObject.optJSONObject(i);
      if (localJSONObject == null) {}
      for (;;)
      {
        i += 1;
        break;
        localArrayList.add(u(localJSONObject));
      }
    }
    localRouteBusWalkItem.setSteps(localArrayList);
    return localRouteBusWalkItem;
  }
  
  public static List<RouteBusLineItem> s(JSONObject paramJSONObject)
  {
    ArrayList localArrayList = new ArrayList();
    if (paramJSONObject == null) {
      return localArrayList;
    }
    paramJSONObject = paramJSONObject.optJSONArray("buslines");
    if (paramJSONObject == null) {
      return localArrayList;
    }
    int i = 0;
    if (i < paramJSONObject.length())
    {
      JSONObject localJSONObject = paramJSONObject.optJSONObject(i);
      if (localJSONObject == null) {}
      for (;;)
      {
        i += 1;
        break;
        localArrayList.add(v(localJSONObject));
      }
    }
    return localArrayList;
  }
  
  public static Doorway t(JSONObject paramJSONObject)
  {
    Doorway localDoorway = new Doorway();
    localDoorway.setName(b(paramJSONObject, "name"));
    localDoorway.setLatLonPoint(c(paramJSONObject, "location"));
    return localDoorway;
  }
  
  public static WalkStep u(JSONObject paramJSONObject)
  {
    WalkStep localWalkStep = new WalkStep();
    localWalkStep.setInstruction(b(paramJSONObject, "instruction"));
    localWalkStep.setOrientation(b(paramJSONObject, "orientation"));
    localWalkStep.setRoad(b(paramJSONObject, "road"));
    localWalkStep.setDistance(j(b(paramJSONObject, "distance")));
    localWalkStep.setDuration(j(b(paramJSONObject, "duration")));
    localWalkStep.setPolyline(d(paramJSONObject, "polyline"));
    localWalkStep.setAction(b(paramJSONObject, "action"));
    localWalkStep.setAssistantAction(b(paramJSONObject, "assistant_action"));
    return localWalkStep;
  }
  
  public static RouteBusLineItem v(JSONObject paramJSONObject)
  {
    if (paramJSONObject == null) {
      return null;
    }
    RouteBusLineItem localRouteBusLineItem = new RouteBusLineItem();
    localRouteBusLineItem.setDepartureBusStation(x(paramJSONObject.optJSONObject("departure_stop")));
    localRouteBusLineItem.setArrivalBusStation(x(paramJSONObject.optJSONObject("arrival_stop")));
    localRouteBusLineItem.setBusLineName(b(paramJSONObject, "name"));
    localRouteBusLineItem.setBusLineId(b(paramJSONObject, "id"));
    localRouteBusLineItem.setBusLineType(b(paramJSONObject, "type"));
    localRouteBusLineItem.setDistance(j(b(paramJSONObject, "distance")));
    localRouteBusLineItem.setDuration(j(b(paramJSONObject, "duration")));
    localRouteBusLineItem.setPolyline(d(paramJSONObject, "polyline"));
    localRouteBusLineItem.setFirstBusTime(d.d(b(paramJSONObject, "start_time")));
    localRouteBusLineItem.setLastBusTime(d.d(b(paramJSONObject, "end_time")));
    localRouteBusLineItem.setPassStationNum(i(b(paramJSONObject, "via_num")));
    localRouteBusLineItem.setPassStations(w(paramJSONObject));
    return localRouteBusLineItem;
  }
  
  public static List<BusStationItem> w(JSONObject paramJSONObject)
  {
    ArrayList localArrayList = new ArrayList();
    if (paramJSONObject == null) {
      return localArrayList;
    }
    paramJSONObject = paramJSONObject.optJSONArray("via_stops");
    if (paramJSONObject == null) {
      return localArrayList;
    }
    int i = 0;
    if (i < paramJSONObject.length())
    {
      JSONObject localJSONObject = paramJSONObject.optJSONObject(i);
      if (localJSONObject == null) {}
      for (;;)
      {
        i += 1;
        break;
        localArrayList.add(x(localJSONObject));
      }
    }
    return localArrayList;
  }
  
  public static BusStationItem x(JSONObject paramJSONObject)
  {
    BusStationItem localBusStationItem = new BusStationItem();
    localBusStationItem.setBusStationName(b(paramJSONObject, "name"));
    localBusStationItem.setBusStationId(b(paramJSONObject, "id"));
    localBusStationItem.setLatLonPoint(c(paramJSONObject, "location"));
    return localBusStationItem;
  }
}

/* Location:
 * Qualified Name:     com.amap.api.services.core.j
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */