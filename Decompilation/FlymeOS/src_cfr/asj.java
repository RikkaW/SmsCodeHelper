/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.text.TextUtils
 *  java.lang.Object
 *  java.lang.String
 *  java.util.HashMap
 */
import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;

public class asj {
    static final String[] a = new String[]{"\u8ba2\u5355\u53f7\tBookingNo.", "\u8ba2\u7968\u4eba\tBookingPerson", "\u51fa\u53d1\u65f6\u95f4\tStartTime", "\u8f66\u6b21\tTrainNo.", "\u5ea7\u4f4d\u53f7\tSeatNo.", "\u51fa\u53d1\u5730\tStartCity", "\u4e58\u5ba2\tPassenger", "\u53d6\u7968\u51ed\u8bc1\tBookCertif", "\u53d6\u7968\u5904\tTicketOffice", "\u89c2\u5f71\u65f6\u95f4\tMovieTime", "\u7535\u5f71\u9662\tCinema", "\u7535\u5f71\u540d\tMovie", "\u5f71\u5385\tFilmRoom", "\u5f71\u9662\u5730\u5740\tCinemaAddress", "\u53d6\u7968\u53f7\tCertifNumber", "\u5230\u8fbe\u5730\tArrivalCity", "\u8f66\u7968\u8be6\u60c5\tTicketDetail", "\u8d77\u98de\u65f6\u95f4\tDepartureTime", "\u5230\u8fbe\u65f6\u95f4\tArriveTime", "\u822a\u73ed\tFlightNo.", "\u91d1\u989d\tMoney", "\u7968\u53f7\tTicketNumber", "\u673a\u7968\u6765\u6e90\tTicketFrom", "\u5ba2\u670d\u7535\u8bdd\tCustomerHotline", "\u63d0\u793a\tTips", "\u5e8f\u5217\u53f7\tSerialNumber", "\u5ba2\u670d\tCustomerService", "\u4ef7\u683c\tPrice", "\u7968\u6570\tNumberOfTickets", "\u603b\u7968\u4ef7\tTotalPrice", "\u7c7b\u578b\tType", "\u6709\u6548\u671f\tExpiryDate", "\u5356\u5bb6\u670d\u52a1\u7535\u8bdd\tSellerServicePhone", "\u6dd8\u5b9d\u65c5\u884c\u70ed\u7ebf\tTaobaoTravelHotline", "\u8bc1\u4ef6\u53f7\tID", "\u6e20\u9053\tChannel", "\u822a\u7a7a\u516c\u53f8\tAirCompany"};
    private static final Map<String, String> b = new HashMap();
    private static final Map<String, String> c = new HashMap();

    static {
        String[] arrstring = a;
        int n2 = arrstring.length;
        int n3 = 0;
        while (n3 < n2) {
            String[] arrstring2 = arrstring[n3].split("\t");
            if (arrstring2.length == 2) {
                b.put(arrstring2[1], arrstring2[0]);
                c.put(arrstring2[0], arrstring2[1]);
            }
            ++n3;
        }
        return;
    }

    public static String a(String string2) {
        String string3 = c.get(string2);
        if (!TextUtils.isEmpty((CharSequence)string3)) {
            return string3;
        }
        return string2;
    }
}

