package sdk.meizu.traffic.auth;

import org.json.JSONObject;

public class OAuthToken
{
  public static final String SERVER_KEY_ACCESS_TOKEN = "access_token";
  public static final String SERVER_KEY_EXPIRES_IN = "expires_in";
  public static final String SERVER_KEY_REFRESH_TOKEN = "refresh_token";
  public static final String SERVER_KEY_SCOPE = "scope";
  public static final String SERVER_KEY_TOKEN_TYPE = "token_type";
  private long mExpireIn;
  private String mRefreshToken;
  private String mScope;
  private String mToken;
  private String mTokenType;
  
  public OAuthToken(JSONObject paramJSONObject)
  {
    mToken = paramJSONObject.getString("access_token");
    mRefreshToken = paramJSONObject.getString("refresh_token");
    mExpireIn = paramJSONObject.getLong("expires_in");
    mScope = paramJSONObject.getString("scope");
    mTokenType = paramJSONObject.getString("token_type");
  }
  
  public String getRefreshToken()
  {
    return mRefreshToken;
  }
  
  public String getToken()
  {
    return mToken;
  }
  
  public String toString()
  {
    return "[" + mToken + " , " + mRefreshToken + " , " + mExpireIn + " , " + mScope + " , " + mTokenType + "]";
  }
}

/* Location:
 * Qualified Name:     sdk.meizu.traffic.auth.OAuthToken
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */