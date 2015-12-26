import java.util.HashSet;
import java.util.Set;

public class atb
{
  public static Set<ast> a = new HashSet();
  
  public static void a()
  {
    ast localast1 = new ast();
    localast1.a("(?:回复)(?:短信)?(?:指令)?([\"'])([^#-]+?)\\1");
    localast1.a(Integer.valueOf(5));
    localast1.b(Integer.valueOf(2));
    localast1.a(new asw());
    ast localast2 = new ast();
    localast2.a("(?:回复)(?:[\"'])([0-9]+-[0-9]+)(?:[\"'])");
    localast2.d(Boolean.valueOf(true));
    localast2.a(Integer.valueOf(5));
    localast2.b(Integer.valueOf(1));
    ast localast3 = new ast();
    localast3.a("(?:回复)(?: )?(?:[\"'])(.+?#.+?)(?:[\"'])");
    localast3.a(Integer.valueOf(5));
    localast3.b(Integer.valueOf(1));
    localast3.d(Boolean.valueOf(true));
    ast localast4 = new ast();
    localast4.a("(?:回复|(?<=[请,;.!\\[\\]]|退订)回)(?:短信)?(?:指令|数字|字母)?(?: *)([a-zA-Z0-9位]+)(?: *)?");
    localast4.a(Integer.valueOf(4));
    localast4.b(Integer.valueOf(1));
    ast localast5 = new ast();
    localast5.a("(?:回复|回)?(@5TD|TD|T)退订");
    localast5.b(Integer.valueOf(1));
    localast5.a(Integer.valueOf(5));
    ast localast6 = new ast();
    localast6.a("(?:回复)(?:数字)?([0-9]{1,2}[\\-到][0-9]{1,2})");
    localast6.d(Boolean.valueOf(true));
    localast6.a(Integer.valueOf(5));
    localast6.b(Integer.valueOf(1));
    ast localast7 = new ast();
    localast7.a("(?:回复)([a-zA-Z0-9]+#[a-zA-Z0-9]+)");
    localast7.b(Integer.valueOf(1));
    localast7.a(Integer.valueOf(5));
    localast7.d(Boolean.valueOf(true));
    ast localast8 = new ast();
    localast8.a("(?:回复)(?::)?([^\"]{1,6}[#\\+][^,.;\"]+)");
    localast8.b(Integer.valueOf(1));
    localast8.a(Integer.valueOf(6));
    localast8.d(Boolean.valueOf(true));
    ast localast9 = new ast();
    localast9.a("(?:回复|回)([a-zA-Z0-9]+)(?:[/\\.])([a-zA-Z0-9]+)(?:[/\\.])([a-zA-Z0-9]+)(?:[/\\.])?([a-zA-Z0-9]+)?");
    localast9.a(Integer.valueOf(6));
    localast9.b(Integer.valueOf(1));
    localast9.a(Boolean.valueOf(true));
    ast localast10 = new ast();
    localast10.a("(?:发短信)(?:\")?([a-zA-Z0-9]+)(?:[到至])([0-9]{4,})(?:\")?");
    localast10.d(Boolean.valueOf(true));
    localast10.a(Integer.valueOf(5));
    localast10.b(Boolean.valueOf(true));
    localast10.b(Integer.valueOf(1));
    localast10.c(Integer.valueOf(2));
    ast localast11 = new ast();
    localast11.a("(?:编辑|发送|回复|发)(?: )?(?:免费)?(?:指令|短信|汉字|数字|字母|内容)?(?:模板)?(?:免费)?(?:发送)?(?::)?(?:数字)?(?:\")?([^.,;]*?)(?:\")?(?: )?(?:免费)?(?:发)?(?:送)?(?:到|至)(?: *)(?:\")?([0-9]{4,})");
    localast11.d(Boolean.valueOf(true));
    localast11.a(Integer.valueOf(6));
    localast11.b(Boolean.valueOf(true));
    localast11.b(Integer.valueOf(1));
    localast11.c(Integer.valueOf(2));
    localast11.a(new asy());
    ast localast12 = new ast();
    localast12.a("(?<=[:\\?;!.,;?@\\r\\n\\t\\] ]|序号|^)(?:\")?([0-9]+ *|[A-Z])([:\\./ 为是对－\\-\"])(?![0-9]+[:|.|,|)| |\\-|在|M|元|年|小时|折])(?:[ \\[]?)([^;()\\r\\n\\t\\.?\\[, ]+)");
    localast12.a(new asz());
    localast12.c(Boolean.valueOf(true));
    localast12.b(Integer.valueOf(1));
    localast12.c(Integer.valueOf(3));
    localast12.a(Integer.valueOf(6));
    ast localast13 = new ast();
    localast13.a("(?:回复)?([0-9]{1,2})(?:及以下)?(?:为|对)([^,.;]+)");
    localast13.a(new asx());
    localast13.b(Integer.valueOf(1));
    localast13.c(Integer.valueOf(2));
    localast13.a(Integer.valueOf(7));
    localast13.c(Boolean.valueOf(true));
    a.add(localast10);
    a.add(localast11);
    a.add(localast12);
    a.add(localast13);
    a.add(localast1);
    a.add(localast2);
    a.add(localast6);
    a.add(localast9);
    a.add(localast4);
    a.add(localast5);
    a.add(localast3);
    a.add(localast7);
    a.add(localast8);
  }
}

/* Location:
 * Qualified Name:     atb
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */