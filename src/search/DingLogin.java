package search;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import java.io.BufferedReader;

/**
 * @author 丛爽
 * @create 2018-11-02 12:506
 **/
public class DingLogin {
    public String goDingLogin(String code) {
        //System.out.println(code);

        try{
            //获取accesstoken
            String accessToken = getAccesstoken();
            //获取用户授权的持久授权码
            JSONObject json = getPersistentCode(accessToken, code);
            String openId = "";
            String persistentCode = "";
            if(null!=json){
                openId = json.getString("openid");
                persistentCode = json.getString("persistent_code");
            }
            //获取用户授权的SNS_TOKEN
            String snsToken = getSnsToken(accessToken, openId, persistentCode);
            //获取用户unionid
            String nick = getUnionId(snsToken);
            //根据unionid获取用户userId
            //String appAccessToken = getAppAccesstoken();
            //String nick = getUserId(accessToken, unionId);
            //获取用户详细数据
            //com.alibaba.fastjson.JSONObject userData = getUserData(appAccessToken, userId);
            System.out.println(nick);
            return nick;
        }catch (Exception e){

        }
        return null;
    }
    public String getAccesstoken() {
        String url = "https://oapi.dingtalk.com/sns/gettoken?appid=dingoav8ulanmhofptrhio&appsecret=r2fbezOVDZBdRxdW5mylJpz5CnWb1SZL40lFjgoi4e3K-TrMDbuTsWEwV7Rz879w";
        JSONObject json = ossHttpGetUtil(url);
        if(null!=json){
            if (Integer.parseInt(json.get("errcode").toString()) == 0) {
                String accessToken = json.getString("access_token");
                return accessToken;
            }
        }
        return "";
    }

    public String getAppAccesstoken() {
        String url = "https://oapi.dingtalk.com/gettoken?corpid=dingbbe74ca7c844b45f35c2f4657eb6378f&corpsecret=MERA4ADae_62lU05Y9fbU9Z43NZBoIBTHnam1SGiKgblcO_NKIzEts-2G3Ji5njw";
        JSONObject json = ossHttpGetUtil(url);
        if(null!=json){
            if (Integer.parseInt(json.get("errcode").toString()) == 0) {
                String appAccessToken = json.getString("access_token");
                return appAccessToken;
            }
        }
        return "";
    }

    public JSONObject getPersistentCode(String accessToken,String code) {
        String url = "https://oapi.dingtalk.com/sns/get_persistent_code?access_token=" + accessToken;
        JSONObject jsonData = new JSONObject();
        jsonData.put("tmp_auth_code", code);
        JSONObject json = ossHttpPostUtil(url, jsonData);
        if(null!=json){
            if (Integer.parseInt(json.get("errcode").toString()) == 0) {
                return json;
            }
        }
        return null;
    }

    public String getSnsToken(String accesstoken, String openid, String persistent_code) {
        String url = "https://oapi.dingtalk.com/sns/get_sns_token?access_token="+accesstoken;
        //HttpPost httpPost = new HttpPost(url);
        JSONObject jsonData = new JSONObject();
        jsonData.put("openid", openid);
        jsonData.put("persistent_code", persistent_code);
        JSONObject json = ossHttpPostUtil(url, jsonData);
        if(null!=json){
            if (Integer.parseInt(json.get("errcode").toString()) == 0) {
                String snsToken = json.getString("sns_token");
                return snsToken;
            }
        }
        return null;
    }

    public String getUnionId(String snsToken) {
        String url = "https://oapi.dingtalk.com/sns/getuserinfo?sns_token="+snsToken;
        JSONObject json = ossHttpGetUtil(url);
        if(null!=json){
            if (Integer.parseInt(json.get("errcode").toString()) == 0) {
                JSONObject jsonUser = json.getJSONObject("user_info");
                String nick = jsonUser.getString("nick");
                return nick;
            }
        }
        return "";
    }

    public String getUserId(String accessToken, String unionId) {
        String url = "https://oapi.dingtalk.com/user/getUseridByUnionid?unionid="+unionId+"&access_token="+accessToken;
        //String url ="https://oapi.dingtalk.com/user/getuserinfo?access_token="+accessToken+"&code="+authCode;
        JSONObject json = ossHttpGetUtil(url);
        if(null!=json){
            if (Integer.parseInt(json.get("errcode").toString()) == 0) {
                JSONObject jsonUser = json.getJSONObject("user_info");
                String nick = jsonUser.getString("nick");
                //String nick = json.getString("nick");
                return nick;
            }
        }
        return "";
    }

    public JSONObject getUserData(String accessToken, String userId) {
        String url = "https://oapi.dingtalk.com/user/get?access_token="+accessToken+"&userid="+userId;
        //https://oapi.dingtalk.com/sns/getuserinfo?sns_token=SNS_TOKEN
        JSONObject json = ossHttpGetUtil(url);
        if(null!=json){
            if (Integer.parseInt(json.get("errcode").toString()) == 0) {
                return json;
            }
        }
        return null;
    }

    private JSONObject ossHttpGetUtil(String url){
        HttpGet httpGet = new HttpGet(url);
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        CloseableHttpClient httpClient = httpClientBuilder.build();
        CloseableHttpResponse httpResponse = null;
        try {
            httpResponse = httpClient.execute(httpGet);
        } catch (Exception e) {
        }
        BufferedReader bufferedReader = null;
        StringBuilder entityStringBuilder = new StringBuilder();
        //得到httpResponse的状态响应码
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        JSONObject jsonObject = null;
        String access_token = "";
        if (statusCode == HttpStatus.SC_OK) {
            //得到httpResponse的实体数据
            HttpEntity httpEntity = httpResponse.getEntity();
            if (httpEntity != null) {
                try {
                    jsonObject = JSONObject.parseObject(EntityUtils.toString(httpEntity));
                    //System.out.println(jsonObject.getString("nick"));
                    return jsonObject;
                } catch (Exception e) {

                }
            }
        }
        return null;
    }

    private JSONObject ossHttpPostUtil(String url, JSONObject json){
        HttpPost httpPost = new HttpPost(url);
        HttpEntity httpEntity = null;
        httpEntity = new StringEntity(json.toString(), "UTF-8");
        httpPost.setEntity(httpEntity);
        CloseableHttpResponse httpResponse = null;
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        CloseableHttpClient httpClient = httpClientBuilder.build();
        try {
            httpResponse = httpClient.execute(httpPost);
        } catch (Exception e) {

        }
        StringBuilder entityStringBuilder = new StringBuilder();
        //得到httpResponse的状态响应码
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        if (statusCode == HttpStatus.SC_OK) {
            //得到httpResponse的实体数据
            HttpEntity httpEntity2 = httpResponse.getEntity();
            JSONObject jsonObject = null;
            if (httpEntity2 != null) {
                try {
                    jsonObject = jsonObject.parseObject(EntityUtils.toString(httpEntity2));
                    return jsonObject;
                } catch (Exception e) {

                }
            }
        }
        return null;
    }
}
