package com.hu.lottery.utils;

import android.text.TextUtils;
import android.util.Xml;

import com.hu.lottery.bean.DetailLottery;
import com.hu.lottery.bean.HistoryLottery;
import com.hu.lottery.bean.LostBall;
import com.hu.lottery.bean.LostLottery;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by huqiucheng on 2017/10/31 0031.
 */

public class XmlParseUtils {

    private static XmlParseUtils xmlParseUtils;

    private XmlParseUtils() {

    }

    public static XmlParseUtils getInstance() {
        if (null == xmlParseUtils) {
            xmlParseUtils = new XmlParseUtils();
        }
        return xmlParseUtils;
    }

    public List<HistoryLottery> parseHistoryLotteryXmlByPull(String string) {
        List<HistoryLottery> historyLotteries = null;
        if (!TextUtils.isEmpty(string)) {
            XmlPullParser xmlPullParser = Xml.newPullParser();
            try {
                xmlPullParser.setInput(new StringReader(string));
                int type = xmlPullParser.getEventType();
                HistoryLottery historyLottery = null;
                while (type != XmlPullParser.END_DOCUMENT) {
                    switch (type) {
                        case XmlPullParser.START_DOCUMENT:
                            historyLotteries = new ArrayList<>();
                            break;
                        case XmlPullParser.START_TAG:
                            if ("row".equals(xmlPullParser.getName())) {
                                historyLottery = new HistoryLottery();
                                int count = xmlPullParser.getAttributeCount();
                                for (int i = 0; i < count; i++) {
                                    String key = xmlPullParser.getAttributeName(i);
                                    if ("pid".equals(key)) {
                                        historyLottery.setPdi(xmlPullParser.getAttributeValue(i));
                                    } else if ("atime".equals(key)) {
                                        historyLottery.setAtime(xmlPullParser.getAttributeValue(i));
                                    } else if ("acode".equals(key)) {
                                        historyLottery.setAcode(xmlPullParser.getAttributeValue(i));
                                    } else if ("trycode".equals(key)) {
                                        historyLottery.setTrycode(xmlPullParser.getAttributeValue(i));
                                    }
                                }
                                historyLotteries.add(historyLottery);
                                LogUtis.logi("parseXmlByPull() " + historyLottery.toString());
                            }
                            break;
                        case XmlPullParser.END_TAG:
                            break;
                    }
                    type = xmlPullParser.next();
                }
            } catch (XmlPullParserException e) {
                e.printStackTrace();
                LogUtis.loge("parseXmlByPull() XmlPullParserException" + e.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
                LogUtis.loge("parseXmlByPull() IOException" + e.getMessage());
            }
        }
        return historyLotteries;
    }

    public DetailLottery parseDetailLotteryXmlByPull(String string) {
        DetailLottery detailLottery = null;
        if (!TextUtils.isEmpty(string)) {
            XmlPullParser xmlPullParser = Xml.newPullParser();
            try {
                xmlPullParser.setInput(new StringReader(string));
                int type = xmlPullParser.getEventType();
                while (type != XmlPullParser.END_DOCUMENT) {
                    switch (type) {
                        case XmlPullParser.START_DOCUMENT:
                            break;
                        case XmlPullParser.START_TAG:
                            if ("rows".equals(xmlPullParser.getName())) {
                                detailLottery = new DetailLottery();
                                int count = xmlPullParser.getAttributeCount();
                                for (int i = 0; i < count; i++) {
                                    String key = xmlPullParser.getAttributeName(i);
                                    if ("gid".equals(key)) {
                                        detailLottery.setGid(xmlPullParser.getAttributeValue(i));
                                    } else if ("pid".equals(key)) {
                                        detailLottery.setPid(xmlPullParser.getAttributeValue(i));
                                    } else if ("acode".equals(key)) {
                                        detailLottery.setAcode(xmlPullParser.getAttributeValue(i));
                                    } else if ("code".equals(key)) {
                                        detailLottery.setCode(xmlPullParser.getAttributeValue(i));
                                    } else if ("gsale".equals(key)) {
                                        detailLottery.setGsale(xmlPullParser.getAttributeValue(i));
                                    } else if ("ginfo".equals(key)) {
                                        detailLottery.setGinfo(xmlPullParser.getAttributeValue(i));
                                    } else if ("ninfo".equals(key)) {
                                        detailLottery.setNinfo(xmlPullParser.getAttributeValue(i));
                                    } else if ("gpool".equals(key)) {
                                        detailLottery.setGpool(xmlPullParser.getAttributeValue(i));
                                    } else if ("atime".equals(key)) {
                                        detailLottery.setAtime(xmlPullParser.getAttributeValue(i));
                                    }
                                }
                                LogUtis.logi("parseDetailLotteryXmlByPull() " + detailLottery.toString());
                            }
                            break;
                        case XmlPullParser.END_TAG:
                            break;
                    }
                    type = xmlPullParser.next();
                }
            } catch (XmlPullParserException e) {
                e.printStackTrace();
                LogUtis.loge("parseDetailLotteryXmlByPull() XmlPullParserException" + e.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
                LogUtis.loge("parseDetailLotteryXmlByPull() IOException" + e.getMessage());
            }
        }
        return detailLottery;
    }


    public LostLottery parseLostLotteryXmlByPull(String string) {
        LostLottery lostLottery = null;
        List<HistoryLottery> historyLotteries = new ArrayList<>();
        HistoryLottery historyLottery = null;
        List<LostBall> lostBalls = new ArrayList<>();
        LostBall lostBall = null;
        if (!TextUtils.isEmpty(string)) {
            XmlPullParser xmlPullParser = Xml.newPullParser();
            try {
                xmlPullParser.setInput(new StringReader(string));
                int type = xmlPullParser.getEventType();
                while (type != XmlPullParser.END_DOCUMENT) {
                    switch (type) {
                        case XmlPullParser.START_DOCUMENT:
                            break;
                        case XmlPullParser.START_TAG:
                            if ("rows".equals(xmlPullParser.getName())) {
                                lostLottery = new LostLottery();
                                int count = xmlPullParser.getAttributeCount();
                                for (int i = 0; i < count; i++) {
                                    String key = xmlPullParser.getAttributeName(i);
                                    if ("pid".equals(key)) {
                                        lostLottery.setPid(xmlPullParser.getAttributeValue(i));
                                    } else if ("atime".equals(key)) {
                                        lostLottery.setAtime(xmlPullParser.getAttributeValue(i));
                                    } else if ("gpool".equals(key)) {
                                        lostLottery.setGpool(xmlPullParser.getAttributeValue(i));
                                    } else if ("tn".equals(key)) {
                                        lostLottery.setTn(xmlPullParser.getAttributeValue(i));
                                    } else if ("periods".equals(key)) {
                                        lostLottery.setPeriods(xmlPullParser.getAttributeValue(i));
                                    } else if ("trycode".equals(key)) {
                                        lostLottery.setTrycode(xmlPullParser.getAttributeValue(i));
                                    }
                                }
                            } else if ("rowp".equals(xmlPullParser.getName())) {
                                historyLottery = new HistoryLottery();
                                int count = xmlPullParser.getAttributeCount();
                                for (int i = 0; i < count; i++) {
                                    String key = xmlPullParser.getAttributeName(i);
                                    if ("pid".equals(key)) {
                                        historyLottery.setPdi(xmlPullParser.getAttributeValue(i));
                                    } else if ("atime".equals(key)) {
                                        historyLottery.setAtime(xmlPullParser.getAttributeValue(i));
                                    } else if ("acode".equals(key)) {
                                        historyLottery.setAcode(xmlPullParser.getAttributeValue(i));
                                    } else if ("tn".equals(key)) {
                                        historyLottery.setTrycode(xmlPullParser.getAttributeValue(i));
                                    }
                                }
                                historyLotteries.add(historyLottery);
                            } else if ("row".equals(xmlPullParser.getName())) {
                                lostBall = new LostBall();
                                int count = xmlPullParser.getAttributeCount();
                                for (int i = 0; i < count; i++) {
                                    String key = xmlPullParser.getAttributeName(i);
                                    if ("color".equals(key)) {
                                        lostBall.setColor(xmlPullParser.getAttributeValue(i));
                                    } else if ("curyl".equals(key)) {
                                        lostBall.setCuryl(xmlPullParser.getAttributeValue(i));
                                    }
                                }
                                lostBalls.add(lostBall);
                            }
                            break;
                        case XmlPullParser.END_TAG:
                            if ("rows".equals(xmlPullParser.getName()) && null != lostLottery) {
                                lostLottery.setHistoryLotteryList(historyLotteries);
                                lostLottery.setBallList(lostBalls);
                                LogUtis.logi(lostLottery.toString());
                            }
                            break;
                    }
                    type = xmlPullParser.next();
                }
            } catch (XmlPullParserException e) {
                e.printStackTrace();
                LogUtis.loge("parseLostLotteryXmlByPull() XmlPullParserException" + e.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
                LogUtis.loge("parseLostLotteryXmlByPull() IOException" + e.getMessage());
            }
        }
        return lostLottery;
    }
}
