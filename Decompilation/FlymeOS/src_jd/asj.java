import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;

public class asj
{
  static final String[] a = { "订单号\tBookingNo.", "订票人\tBookingPerson", "出发时间\tStartTime", "车次\tTrainNo.", "座位号\tSeatNo.", "出发地\tStartCity", "乘客\tPassenger", "取票凭证\tBookCertif", "取票处\tTicketOffice", "观影时间\tMovieTime", "电影院\tCinema", "电影名\tMovie", "影厅\tFilmRoom", "影院地址\tCinemaAddress", "取票号\tCertifNumber", "到达地\tArrivalCity", "车票详情\tTicketDetail", "起飞时间\tDepartureTime", "到达时间\tArriveTime", "航班\tFlightNo.", "金额\tMoney", "票号\tTicketNumber", "机票来源\tTicketFrom", "客服电话\tCustomerHotline", "提示\tTips", "序列号\tSerialNumber", "客服\tCustomerService", "价格\tPrice", "票数\tNumberOfTickets", "总票价\tTotalPrice", "类型\tType", "有效期\tExpiryDate", "卖家服务电话\tSellerServicePhone", "淘宝旅行热线\tTaobaoTravelHotline", "证件号\tID", "渠道\tChannel", "航空公司\tAirCompany" };
  private static final Map<String, String> b = new HashMap();
  private static final Map<String, String> c = new HashMap();
  
  static
  {
    String[] arrayOfString1 = a;
    int j = arrayOfString1.length;
    int i = 0;
    for (;;)
    {
      if (i >= j) {
        return;
      }
      String[] arrayOfString2 = arrayOfString1[i].split("\t");
      if (arrayOfString2.length == 2)
      {
        b.put(arrayOfString2[1], arrayOfString2[0]);
        c.put(arrayOfString2[0], arrayOfString2[1]);
      }
      i += 1;
    }
  }
  
  public static String a(String paramString)
  {
    String str = (String)c.get(paramString);
    if (!TextUtils.isEmpty(str)) {
      return str;
    }
    return paramString;
  }
}

/* Location:
 * Qualified Name:     asj
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */