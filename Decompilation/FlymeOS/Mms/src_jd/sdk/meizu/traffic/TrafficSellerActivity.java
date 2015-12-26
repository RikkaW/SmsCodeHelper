package sdk.meizu.traffic;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Pair;
import android.webkit.WebView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import sdk.meizu.traffic.auth.CUserManager;
import sdk.meizu.traffic.hybird.HyBirdView;
import sdk.meizu.traffic.hybird.JsCmd;
import sdk.meizu.traffic.hybird.method.BaseNativeInterface;
import sdk.meizu.traffic.util.ContactUtil;

public class TrafficSellerActivity
  extends AppCompatActivity
{
  private static final int MESSAGE_SEARCH_CONTACT = 10000;
  private static final String TAG = TrafficSellerActivity.class.getSimpleName();
  private final int REQUEST_CODE_LOGIN = 100;
  private final int REQUEST_CONTACT = 1000;
  private JsCmd mGetNumberCmd;
  private HyBirdView mHybirdView;
  private BaseNativeInterface mNativeInterface;
  private String mPageData;
  private String mPageUrl;
  protected ProgressDialog mProgressDialog;
  protected SearchContactThread mSearchContactThread;
  protected Handler mUiHandler = new TrafficSellerActivity.1(this);
  
  protected void initContentView()
  {
    mNativeInterface = new BaseNativeInterface();
    mNativeInterface.setLoadingHandler(new TrafficSellerActivity.2(this));
    mNativeInterface.setToastHandler(new TrafficSellerActivity.3(this));
    mNativeInterface.setAuthHandler(new TrafficSellerActivity.4(this));
    mNativeInterface.setPhoneHandler(new TrafficSellerActivity.5(this));
    mNativeInterface.setPayHandler(new TrafficSellerActivity.6(this));
    mNativeInterface.setImHandler(new TrafficSellerActivity.7(this));
    mHybirdView = new HyBirdView(this);
    mHybirdView.setFitsSystemWindows(true);
    mHybirdView.getWebView().addJavascriptInterface(mNativeInterface.getJsToAndroidBridge(), "androidJs");
    mHybirdView.getWebView().setWebViewClient(new TrafficSellerActivity.8(this));
    setContentView(mHybirdView);
    mHybirdView.getWebView().loadUrl(mPageUrl);
  }
  
  protected void initParams()
  {
    Bundle localBundle = getIntent().getExtras();
    if (localBundle != null)
    {
      mPageUrl = localBundle.getString("url");
      mPageData = localBundle.getString("data");
    }
    if (TextUtils.isEmpty(mPageUrl)) {
      finish();
    }
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if (paramInt1 == 100) {
      if (paramInt2 != -1)
      {
        Toast.makeText(this, "请登陆账户并授权", 0).show();
        finish();
      }
    }
    Pair localPair;
    do
    {
      do
      {
        return;
        if (paramInt1 != 1000) {
          break;
        }
      } while (paramInt2 != -1);
      localPair = ContactUtil.loadPhoneData(this, paramIntent.getData());
      if (localPair == null) {
        break;
      }
    } while ((mGetNumberCmd == null) || (mHybirdView == null) || (mHybirdView.getWebView() == null));
    paramIntent = new JSONObject();
    try
    {
      paramIntent.put("pName", first);
      paramIntent.put("pNum", second);
      mGetNumberCmd.setMethodArgs(new String[] { paramIntent.toString() }).execute(mHybirdView.getWebView());
      return;
    }
    catch (JSONException localJSONException)
    {
      for (;;)
      {
        localJSONException.printStackTrace();
      }
    }
    Toast.makeText(this, "联系人信息为空", 0).show();
    return;
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    mProgressDialog = new ProgressDialog(this);
    mProgressDialog.setMessage("请稍等");
    CUserManager.getInstance(this).resetToken();
    initParams();
    initContentView();
  }
  
  public class SearchContactThread
    extends Thread
  {
    private String mQueryPhone = "";
    private JsCmd mSearchCallback;
    
    public SearchContactThread(String paramString, JsCmd paramJsCmd)
    {
      mQueryPhone = paramString;
      mSearchCallback = paramJsCmd;
    }
    
    public void run()
    {
      Object localObject = ContactUtil.searchContactsByNum(TrafficSellerActivity.this, mQueryPhone);
      JSONArray localJSONArray = new JSONArray();
      try
      {
        localObject = ((ArrayList)localObject).iterator();
        while (((Iterator)localObject).hasNext())
        {
          Pair localPair = (Pair)((Iterator)localObject).next();
          JSONObject localJSONObject = new JSONObject();
          localJSONObject.put("pName", first);
          localJSONObject.put("pNums", second);
          localJSONArray.put(localJSONObject);
        }
        return;
      }
      catch (JSONException localJSONException)
      {
        localJSONException.printStackTrace();
        mUiHandler.post(new TrafficSellerActivity.SearchContactThread.1(this, localJSONArray));
      }
    }
  }
}

/* Location:
 * Qualified Name:     sdk.meizu.traffic.TrafficSellerActivity
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */