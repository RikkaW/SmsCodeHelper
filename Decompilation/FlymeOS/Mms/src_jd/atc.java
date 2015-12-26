import java.util.HashMap;
import java.util.Map;

public class atc
{
  public static Map<Character, Character> a = new HashMap();
  
  static
  {
    a.put(Character.valueOf(65292), Character.valueOf(','));
    a.put(Character.valueOf('。'), Character.valueOf('.'));
    a.put(Character.valueOf('”'), Character.valueOf('"'));
    a.put(Character.valueOf('“'), Character.valueOf('"'));
    a.put(Character.valueOf(65307), Character.valueOf(';'));
    a.put(Character.valueOf(65306), Character.valueOf(':'));
    a.put(Character.valueOf('、'), Character.valueOf('/'));
    a.put(Character.valueOf(65311), Character.valueOf('?'));
    a.put(Character.valueOf(65281), Character.valueOf('!'));
    a.put(Character.valueOf(65288), Character.valueOf('('));
    a.put(Character.valueOf(65289), Character.valueOf(')'));
    a.put(Character.valueOf('‘'), Character.valueOf('\''));
    a.put(Character.valueOf('’'), Character.valueOf('\''));
    a.put(Character.valueOf('《'), Character.valueOf('<'));
    a.put(Character.valueOf('》'), Character.valueOf('>'));
    a.put(Character.valueOf(65283), Character.valueOf('#'));
    a.put(Character.valueOf(65291), Character.valueOf('+'));
    a.put(Character.valueOf('【'), Character.valueOf('['));
    a.put(Character.valueOf('】'), Character.valueOf(']'));
    a.put(Character.valueOf('—'), Character.valueOf('-'));
  }
  
  public static String a(String paramString)
  {
    paramString = ate.a(paramString.replaceAll("(?<=^|[^0-9a-zA-Z\\-_/?=&\\.@\\*]|(?:^|[^0-9a-zA-Z\\-_/?=&@\\*])\\.)(?:https?://)?(?:(?:(?:[0-9a-zA-Z\\-_]+)(?:(?:(?:\\.[0-9a-zA-Z\\-_]+)+(?::\\d{1,5})?/[0-9a-zA-Z\\-_/?=&\\.]*[0-9a-zA-Z\\-_/?=&])|(?:(?:\\.[0-9a-zA-Z\\-_]+)*\\.(?:com|cn|gov|net|org|edu|cc)/?(?=$|[^0-9a-zA-Z\\-_/?=&\\.]|\\.(?:$|[^0-9a-zA-Z\\-_/?=&])))))|(?:www\\.[0-9a-zA-Z\\-_/?=&\\.]*[0-9a-zA-Z\\-_/?=&]))", "")).replaceAll(" +", " ");
    char[] arrayOfChar = new char[paramString.length()];
    int i = 0;
    if (i < paramString.length())
    {
      Character localCharacter1 = Character.valueOf(paramString.charAt(i));
      Character localCharacter2 = (Character)a.get(localCharacter1);
      if (localCharacter2 != null) {
        arrayOfChar[i] = localCharacter2.charValue();
      }
      for (;;)
      {
        i += 1;
        break;
        arrayOfChar[i] = localCharacter1.charValue();
      }
    }
    return new String(arrayOfChar).replaceAll(".*请直接回复序号", "回复序号").replaceAll("(?![0-9])[0-2]?[0-9]:[0-6](?<![0-9])", " ").replaceAll("\"10\"分才是满意", "").replaceAll("回复免费", "").replaceAll("(?<=[\\u4E00-\\u9FFF\\)](?<!回复))(\\d{1,4}[\\.:为/ ])(?!$)", " $1");
  }
}

/* Location:
 * Qualified Name:     atc
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */