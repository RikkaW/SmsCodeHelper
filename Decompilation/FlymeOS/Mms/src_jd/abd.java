import org.json.JSONObject;

public class abd
{
  private String a;
  private int b;
  private boolean c;
  
  public abd(JSONObject paramJSONObject)
  {
    a = paramJSONObject.getString("addr");
    b = paramJSONObject.getInt("ability");
    c = paramJSONObject.getString("st").equals("ONLINE");
  }
  
  public String a()
  {
    return a;
  }
  
  public int b()
  {
    return b;
  }
  
  public boolean c()
  {
    return c;
  }
}

/* Location:
 * Qualified Name:     abd
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */