package com.hu.lottery.network;

import android.content.Context;
import android.util.Base64;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.hu.lottery.bean.DetailLottery;
import com.hu.lottery.bean.HistoryLottery;
import com.hu.lottery.bean.LostLottery;
import com.hu.lottery.utils.LogUtis;
import com.hu.lottery.utils.XmlParseUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by huqiucheng on 2017/9/8 0008.
 */

public class NetTools {

    //    private static String pathHead = "http://mirror.micronavi.cn/weather/mainquery.php?city=";
    private static String pathHead = "aHR0cDovL21pcnJvci5taWNyb25hdmkuY24vd2VhdGhlci9tYWlucXVlcnkucGhwP2NpdHk9";
    private static String lostDataUrl = "http://mobile.9188.com/trade/lotteryInfo.go";
    private static NetTools netTools;
    private RequestQueue mQueue;

    private NetTools(Context mContext) {
        mQueue = Volley.newRequestQueue(mContext);
    }


    /**
     * 获取历史开奖记录
     *
     * @param gid  彩票类型 01：双色球  50：大乐透
     * @param page 分页页数
     * @return
     */
    public String getHistoryListUrl(String gid, int page) {
        return "http://mobile.9188.com/trade/list.go?gid=" + gid + "&ps=10&pn=" + page;
    }

    /**
     * 获取某一期开奖具体信息
     *
     * @param gid 彩票类型 01：双色球  50：大乐透
     * @param pid 某一期期数
     * @return
     */
    public String getDetialUrl(String gid, String pid) {
        return "http://mobile.9188.com/trade/detail.go?gid=" + gid + "&pid=" + pid;
    }

    public static NetTools getInstance(Context mContext) {
        if (null == netTools) {
            netTools = new NetTools(mContext);
        }
        return netTools;
    }


    public void getHistoryData(String gid, int page) {
        String path = getHistoryListUrl(gid, page);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, path, new Response.Listener<String>() {

            @Override
            public void onResponse(String s) {
                List<HistoryLottery> list = XmlParseUtils.getInstance().parseHistoryLotteryXmlByPull(s);
                if (null != listener) {
                    listener.onSuccess(list);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
        mQueue.add(stringRequest);
    }


    public void getDetailData(String gid, String pid) {
        String path = getDetialUrl(gid, pid);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, path, new Response.Listener<String>() {

            @Override
            public void onResponse(String s) {
                DetailLottery detailLottery = XmlParseUtils.getInstance().parseDetailLotteryXmlByPull(s);
                if (null != listener) {
                    listener.onSuccess(detailLottery);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
        mQueue.add(stringRequest);
    }

    public void getLostData(final String gid) {
        StringRequest request = new StringRequest(Request.Method.POST, lostDataUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                LogUtis.logi(s);
                LostLottery lostLottery = XmlParseUtils.getInstance().parseLostLotteryXmlByPull(s);
                if (null != listener) {
                    listener.onSuccess(lostLottery);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("appversion", "14520");
                params.put("source", "1056");
                params.put("logintype", "2");
                params.put("mtype", "1");
                params.put("rversion", "4.5.2");
                params.put("imei", "sdfIJJ5bibiusdfcBuhii9Bbisbsc");
                params.put("osversion", "7.0.0");
                params.put("gid", gid);
                return params;
            }
        };
        mQueue.add(request);
    }


    public void getString(String cityName) {
        String path = "";
        try {
            path = getPathHead(pathHead) + URLEncoder.encode(cityName, "utf-8");
            LogUtis.logi("path = " + path);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            if (null != listener) {
                listener.onError(RequestNetDataResultListener.PARAMETER_ERROR);
            }
            return;
        }
        StringRequest stringRequest = new StringRequest(path, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                LogUtis.logi("onResponse:" + s);
                try {
                    /*WeatherData weatherData = JSON.parseObject(s, WeatherData.class);
                    LogUtis.log(weatherData.toString());
                    if (null != listener) {
                        listener.onSuccess(weatherData);
                    }*/
                } catch (Exception e) {
                    if (null != listener) {
                        listener.onError(RequestNetDataResultListener.PARSE_ERROR);
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                LogUtis.loge("onErrorResponse:" + volleyError.getMessage());
                if (null != listener) {
                    listener.onError(RequestNetDataResultListener.NETWORK_ERROR);
                }
            }
        });
        mQueue.add(stringRequest);
    }


    public void onDestory() {
        if (null != listener) {
            listener = null;
        }

        if (null != mQueue) {
            mQueue.stop();
            mQueue = null;
        }
        if (null != netTools) {
            netTools = null;
        }
    }

    private String getPathHead(String encodedString) {
        return new String(Base64.decode(encodedString, Base64.DEFAULT));
    }


    private RequestNetDataResultListener listener;

    public void setNetDataResultListener(RequestNetDataResultListener listener) {
        this.listener = listener;
    }

    public interface RequestNetDataResultListener {
        int NETWORK_ERROR = 1;
        int PARSE_ERROR = 2;
        int PARAMETER_ERROR = 3;

        void onSuccess(Object obj);

        void onError(int errorType);

    }
}
