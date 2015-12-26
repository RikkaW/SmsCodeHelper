package com.ted.sdk.smscard;

public class CardPlaneTicket
  extends CardBase
{
  public static final String KEY_AIRLINES = "航空公司";
  public static final String KEY_ARRIVAL_DATE = "到达日期";
  public static final String KEY_ARRIVAL_TIME = "到达时间";
  public static final String KEY_BOARDING_NUMBER = "登机序号";
  public static final String KEY_BUSINESS_NUMBER = "商家电话";
  public static final String KEY_CHANGE_DATE = "改后日期";
  public static final String KEY_CHANGE_FLIGHT = "改后航班";
  public static final String KEY_CHANGE_TIME = "改后时间";
  public static final String KEY_COD3 = "值机验证码";
  public static final String KEY_CONTENT = "内容";
  public static final String KEY_DEPARTURE_DATE = "起飞日期";
  public static final String KEY_DEPARTURE_TIME = "起飞时间";
  public static final String KEY_END_PLACE = "到达地";
  public static final String KEY_END_TERMINAL = "到达航站楼";
  public static final String KEY_E_BOARDING = "电子登机牌";
  public static final String KEY_FLIGHT = "航班";
  public static final String KEY_GATE = "登机口";
  public static final String KEY_KEEP_DATE = "保留日期";
  public static final String KEY_KEEP_TIME = "保留时间";
  public static final String KEY_ORDER = "订单号";
  public static final String KEY_ORIGINAL_DATE = "原订日期";
  public static final String KEY_ORIGINAL_TIME = "原订时间";
  public static final String KEY_PEOPLE = "乘客";
  public static final String KEY_PRICE = "票价";
  public static final String KEY_RANGE = "航程";
  public static final String KEY_REMIND = "提醒";
  public static final String KEY_RESERVED_SEATING = "预留座位";
  public static final String KEY_RETURN_ARRIVAL_TIME = "返程到达时间";
  public static final String KEY_RETURN_DATE = "返程日期";
  public static final String KEY_RETURN_FLIGHT = "返程航班";
  public static final String KEY_RETURN_PLACE = "返程到达地";
  public static final String KEY_RETURN_START_PLACE = "返程出发地";
  public static final String KEY_RETURN_TIME = "返程时间";
  public static final String KEY_SEAT_NUMBER = "座位号";
  public static final String KEY_SERVICE_NUMBER = "客服电话";
  public static final String KEY_SOURCE = "机票来源";
  public static final String KEY_START_PLACE = "出发地";
  public static final String KEY_START_TERMINAL = "出发航站楼";
  public static final String KEY_STATUS = "状态";
  public static final String KEY_TERMINAL = "航站楼";
  public static final String KEY_TICKET_NUMBER = "票号";
  public static final String KEY_TYPE = "类型";
  public static final String KEY_VALID_TIME = "有效期";
  private static final long serialVersionUID = -8758775277351706767L;
  
  public String getEndPlace()
  {
    return find("到达地");
  }
  
  public String getEndTime()
  {
    return find("到达时间");
  }
  
  public String getFlightNo()
  {
    return find("航班");
  }
  
  public String getPassenger()
  {
    return find("乘客");
  }
  
  public String getStartPlace()
  {
    return find("出发地");
  }
  
  public String getStartTime()
  {
    return find("起飞时间");
  }
}

/* Location:
 * Qualified Name:     com.ted.sdk.smscard.CardPlaneTicket
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */