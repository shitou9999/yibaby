package com.example.administrator.YiBaby.ybHttpURL;

/**
 * Created by Administrator on 2016/1/26.
 */
public class ybHttpURL {
    public static final String HOST=   //返回集合  实体学生StuInfos
            "http://www.meiyibaby.cn/";
    private static final String WEBVIEW="appbackend";
    public static final String URL=HOST+WEBVIEW+"/";
    public static final String BANNER=URL+"home/banner.jsp?sid=25";//ViewPager
    public static final String LIST_DT=URL+"forum/list_dt.jsp?sid=25";//评论
    public static final String LIST_TI=URL+"forum/list_tj.jsp?sid=25";//应该是最新推荐
    public static final String ALL_BBS =
            "http://www.meiyibaby.cn/appbackend/forum/list.jsp?is_page=1&page_size=10" +
                    "&start_page=1&sid=25&channel_id=7&user_id=-1&condition=&flag=0";
    public static final String NEAR_BBS =
            "http://www.meiyibaby.cn/appbackend/forum/list_fj.jsp?is_page=1&page_size=0" +
                    "&start_page=0&sid=25&province_id=100110&condition=";
    public static final String HEAD_DTETIL01=URL+"product/detail.jsp?product_id=5544&member_id=0&area=%E8%8E%B1%E5%B1%B1%E5%8C%BA" ;
    public static final String HEAD_DTETIL02=URL+"product/detail.jsp?product_id=5534&member_id=0&area=%E8%8E%B1%E5%B1%B1%E5%8C%BA" ;
    public static final String GO_SHOPING_CAR = HOST+"core/control/wcm_eshop_order_cart/control.jsp?goodsid=5542&amount=1&method=addToCart&areaid=100430";
    public static String getNetMilkJsonData(int flag){
        switch (flag){
            case 0:
                return GO;
            case 1:
                return GO_03;
            case 2:
                return GO_36;
            case 3:
                return GO_612;
            case 4:
                return GO_13;
            case 5:
                return GO_T;
        }
        return null;
    }
    public static final String GO=//
            URL+"product/list.jsp?is_page=1&page_size=20&start_page=1&sid=25&flag=0&area=";
    public static final String GO_03=  // 0-3
            URL+"product/list.jsp?is_page=1&page_size=20&start_page=1&sid=25&flag=1&area=";
    public static final String GO_36=  //3-6
            URL+"product/list.jsp?is_page=1&page_size=20&start_page=1&sid=25&flag=2&area=";
    public static final String GO_612=  //6-12
            URL+"product/list.jsp?is_page=1&page_size=20&start_page=1&sid=25&flag=3&area=";
    public static final String GO_13=  //1-3
            URL+"product/list.jsp?is_page=1&page_size=20&start_page=1&sid=25&flag=4&area=";
    public static final String GO_T=  //特殊配方
            URL+"product/list.jsp?is_page=1&page_size=20&start_page=1&sid=25&flag=5&area=";
    public static final String MORE_DETIL = URL+"/forum/detail.jsp?";
}



