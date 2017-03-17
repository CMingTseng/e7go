package tw.haotek.app.e7go.wxapi;

//import android.app.Activity;
//import android.content.Context;
//import android.content.Intent;
//import android.os.Bundle;
//import android.util.Log;
//
//import com.tencent.mm.sdk.openapi.BaseReq;
//import com.tencent.mm.sdk.openapi.BaseResp;
//import com.tencent.mm.sdk.openapi.IWXAPI;
//import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
//import com.tencent.mm.sdk.openapi.SendAuth;
//
//import tw.haotek.managers.WeChatControl;
//
///**
// * Created by Neo on 2016/1/8 0008.
// */
//public class WXEntryActivity_old extends Activity implements IWXAPIEventHandler {
//    private static final String TAG = WXEntryActivity_old.class.getSimpleName();
////    private static final int TIMELINE_SUPPORTED_VERSION = 0x21020001 ;
////    private static final int RETURN_MSG_TYPE_LOGIN = 1 ;
////    private static final int RETURN_MSG_TYPE_SHARE = 2 ;
////    private String appId;
////    private String secret;
//
////    @Override
////    public void onCreate(Bundle savedInstanceState) {
////        super.onCreate(savedInstanceState);
////
////        WeChatControl.getsControl().getiIWXAPI( this).handleIntent(getIntent(), this);
////
////
////    }
////
////    @Override
////    protected void onNewIntent(Intent intent) {
////        super.onNewIntent(intent);
////        Log.e("onNewIntent", "!!!" + intent);
////        setIntent(intent);
////        WeChatControl.getsControl().getiIWXAPI( this).handleIntent(getIntent(), this);
////    }
////
////    // 微信发送请求到第三方应用时，会回调到该方法
////    @Override
////    public void onReq(BaseReq req) {
////        Log.e( "onReq", "!!!" + req);
////        switch (req.getType()) {
////            case ConstantsAPI.COMMAND_GETMESSAGE_FROM_WX:
////                //goToGetMsg();
////                break;
////            case ConstantsAPI.COMMAND_SHOWMESSAGE_FROM_WX:
////                //goToShowMsg((ShowMessageFromWX.Req) req);
////                break;
////            default:
////                break;
////        }
////    }
////
////    // 第三方应用发送到微信的请求处理后的响应结果，会回调到该方法
////    @Override
////    public void onResp(BaseResp resp) {
////        int result = 0;
////        Log.e( "onResp", "!!!" + resp.getType());
////        switch (resp.errCode) {
////            case BaseResp.ErrCode.ERR_OK:
////
////                switch (resp.getType()) {
////                    case RETURN_MSG_TYPE_LOGIN:
////
////                        SendAuth.Resp sendResp = (SendAuth.Resp) resp;
//////                        Log. e("resp.errCode", "!!!" + sendResp.errCode);
//////                        Log. e("resp.state", "!!!" + sendResp.state);
//////                        Log. e("resp.token", "!!!" + sendResp.token);
////
////                        //loadingStart(this);
//////                        secret = getResources().getString(R.string.com_wechat_api_wechat_API_SECRET );
//////                        appId = WeChatControl.getInstance().getAppKey( this);
//////                        getAccessToken(sendResp. token);
////
////                        //官方文件寫錯，是token，不是 code
////                        // 拿到了微信返回的 code,立马再去请求access_token
////                        //String code = ((SendAuth.Resp) resp).code;
////
////                        // 就在这个地方，用网络库什么的或者自己封的网络 api，发请求去咯，注意是 get请求
////
////                        break;
////
////                    case RETURN_MSG_TYPE_SHARE:
////                        //"微信分享成功";
////                        finish();
////                        break;
////                }
////
////                break;
////            case BaseResp.ErrCode.ERR_USER_CANCEL:
//////                result = com.example.wechatsdk.party3control.R.string.errcode_cancel;
////                break;
////            case BaseResp.ErrCode.ERR_AUTH_DENIED:
//////                result = com.example.wechatsdk.party3control.R.string.errcode_deny;
////                break;
////            default:
//////                result = com.example.wechatsdk.party3control.R.string.errcode_unknown;
////                break;
////        }
////
////    }
////
////    //stpe1
////    public void getAccessToken(String toekn) {
////
////        String apiUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + appId + "&secret=" + secret + "&code=" + toekn + "&grant_type=authorization_code" ;
////
//////        (new JsonGetTask(apiUrl) {
//////            @Override
//////            protected void onPostExecute(ArrayList<hashmap<string, object="">> arrayList) {
//////                if ((arrayList.size() == 1 && null != arrayList.get(0).get(NETWORK_ERROR ))) {
//////
//////                    Log.e("getAccessToken", "NETWORK_ERROR");
//////
//////                } else {
//////
//////                    //我要做的事情
//////                    Log.e( "getAccessToken", arrayList.toString());
//////
//////                    if (!arrayList.get( 0).containsKey( "errcode")) {
//////
//////                        String refreshToken = (String) arrayList.get(0).get("refresh_token" );
//////                        String accessToken = (String) arrayList.get(0).get("access_token" );
//////                        String openid = (String) arrayList.get( 0).get( "openid");
//////                        getUserInfo(refreshToken, accessToken, openid);
//////
//////                    } else {
//////                        Log. e("error", arrayList.toString());
//////                    }
//////
//////                }
//////
//////                //loadingFinish();
//////
//////            }
//////        }).execute();
////    }
////
////
////    //stpe2
////    public void getUserInfo(String refreshToken, String accessToken, String openid) {
////
////        String apiUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=" + accessToken + "&openid=" + openid;
////
//////        (new JsonGetTask(apiUrl) {
//////            @Override
//////            protected void onPostExecute(ArrayList<hashmap<string, object="">> arrayList) {
//////                if ((arrayList.size() == 1 && null != arrayList.get(0).get(NETWORK_ERROR ))) {
//////                    Log.e("getUserInfo", "NETWORK_ERROR");
//////                } else {
//////
//////                    Log.e("getAccessToken", arrayList.toString());
//////
//////                    if (!arrayList.get( 0).containsKey( "errcode")) {
//////
//////                        String nickname = (String) arrayList.get(0).get("nickname" );
//////                        String unionid = (String) arrayList.get( 0).get( "unionid");
//////                        String headimgurl = (String) arrayList.get(0).get("headimgurl" );
//////
//////                        Log.e("userinfo", "nickname:"+nickname+"===unionid:"+unionid+"===headimgurl"+headimgurl);
//////
//////                    } else {
//////                        Log. e("error", arrayList.toString());
//////                    }
//////
//////
//////                }
//////
//////                //loadingFinish();
//////                finish();
//////            }
//////        }).execute();
////    }
//
//    //FIXME  20160109
////    private Context mContext = WXEntryActivity_old.this;
////
////
////    private void handleIntent(Intent paramIntent) {
////        WeChatControl.getsControl().getiIWXAPI(mContext).handleIntent(paramIntent, this);
////
////    }
////
////    @Override
////
////    protected void onCreate(Bundle savedInstanceState) {
////        super.onCreate(savedInstanceState);
////        handleIntent(getIntent());
////    }
////
////    @Override
////
////    protected void onNewIntent(Intent intent) {
////        super.onNewIntent(intent);
////        setIntent(intent);
////        handleIntent(intent);
////    }
////
////    @Override
////
////    public void onReq(BaseReq arg0) {
////        finish();
////    }
////
////
////    @Override
////
////    public void onResp(BaseResp resp) {
////        Log.d(TAG, "onResp : " + resp.toString());
////        switch (resp.errCode) {
////            case BaseResp.ErrCode.ERR_OK:
////                if (ConstantsAPI.COMMAND_SENDMESSAGE_TO_WX == resp.getType()) {
////                    Toast.makeText(mContext, "分享成功", Toast.LENGTH_LONG).show();
////                    break;
////                }
//////                String code = ((SendAuth.Resp) resp).code;
//////
//////                new SharedPreferencesclass(49, code, mContext);
//////
//////                System.out.println("微信确认登录返回的code：" + code);
//////
//////                Toast.makeText(mContext, "微信确认登录返回的code：" + code, Toast.LENGTH_LONG).show();
//////
//////                MyResumeLoginView.loginHandler.sendEmptyMessage(MyResumeLoginView.WEIXIN_LOGIN_SUCCESS);
////                break;
////            case BaseResp.ErrCode.ERR_USER_CANCEL:
////                break;
////            case BaseResp.ErrCode.ERR_AUTH_DENIED:
////                break;
////
////            default:
////                break;
////
////        }
////        finish();
////    }
//    private static final int RETURN_MSG_TYPE_LOGIN = 1;
//    private static final int RETURN_MSG_TYPE_SHARE = 2;
//    private Context mContext = WXEntryActivity_old.this;
//    public static IWXAPI api;
//
//    private void handleIntent(Intent paramIntent) {
//        WeChatControl.getsControl().getiIWXAPI(mContext).handleIntent(paramIntent, this);
//
//    }
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        Log.d(TAG, "onCreate");
////        setContentView(R.layout.wxentry);
////        api= WXAPIFactory.createWXAPI(mContext, C.WECHAT_APP_ID, true);
////        api.registerApp(C.WECHAT_APP_ID);
//        Intent ii = getIntent();
//        api=  WeChatControl.getsControl().getiIWXAPI(mContext);
////        Intent ii = getIntent();
////        api.handleIntent(ii, this);
//
//        //如果没回调onResp，八成是这句没有写
////        MTNApplication.api.handleIntent(getIntent(), this);
////        handleIntent(getIntent());
////
////        WeChatControl.login(mContext);
//        final SendAuth.Req req = new SendAuth.Req();
////        req.scope = "snsapi_userinfo";
//        req.scope = "snsapi_base";
//        req.state = "diandi_wx_login";
//        api.sendReq(req);
//    }
//
//    @Override
//    protected void onNewIntent(Intent intent) {
//        super.onNewIntent(intent);
//        Log.d(TAG, "onNewIntent");
//        setIntent(intent);
//        api.handleIntent(intent, this);
//    }
//
//
//    @Override
//    protected void onStart() {
//       super.onStart();
//        Log.d(TAG, "onStart");
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        Log.d(TAG,"onResume");
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        Log.d(TAG,"onPause");
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        Log.d(TAG,"onStop");
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        Log.d(TAG,"onDestroy");
//    }
//
//    //微信发送消息给app，app接受并处理的回调函数
//    @Override
//    public void onReq(BaseReq baseReq) {
//        Log.d(TAG,"onReq");
//    }
//
//    //app发送消息给微信，微信返回的消息回调函数,根据不同的返回码来判断操作是否成功
//    @Override
//    public void onResp(BaseResp resp) {
//        Log.d(TAG,"onResp");
//        switch (resp.errCode) {
//            case BaseResp.ErrCode.ERR_AUTH_DENIED:
//            case BaseResp.ErrCode.ERR_USER_CANCEL:
////                showToast("微信失败");
//                break;
//
//            case BaseResp.ErrCode.ERR_OK:
//                switch (resp.getType()) {
//                    case RETURN_MSG_TYPE_SHARE:
////                        showToast("微信分享成功");
//                        finish();
//                        break;
//                }
//                break;
//        }
//    }
//
//}
