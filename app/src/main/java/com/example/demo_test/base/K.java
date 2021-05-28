package com.example.demo_test.base;

public class K {
    public static class Request {
        public static final String DEVICE = "device";
        public static final String DEVICEUID = "deviceUid";
        public static final String VERSION = "version";
        public static final String APK_VERSION = "apkVersion";
        public static final String M_TOKEN = "mtoken";
        public static final String C_TOKEN = "ctoken";
        public static final String S_KEY = "cKey";

        public static final String PAGE = "page";
        public static final String PAGE_SIZE = "pageSize";
        public static final String SORT = "sortType";
        public static final String TOPIC = "topicType";
        public static final String UID = "uid";
        public static final String CONTENT = "content";
        public static final String IS_ENGLISH = "isEnglish";
        public static final String REQUEST_TIMESTAMP = "requestTimestamp";
    }

    public static class Value {
        public static final String DEVICE = "3";
        // 情感
        public static final String TOPIC_qinggan = "1";
        // 婚姻
        public static final String TOPIC_hunyin = "2";
        // 职场
        public static final String TOPIC_zhichang = "3";
        // 家庭
        public static final String TOPIC_jiating = "4";
        // 亲子 -- 知命
        public static final String TOPIC_qinzi = "8";
        // 婆媳
        public static final String TOPIC_poxi = "6";
        // 其他
        public static final String TOPIC_qita = "100";

        /**
         * 时长
         */
        public static final String SORT_TIME = "2";
        /**
         * 评分
         */
        public static final String SORT_SCORE = "3";
        /**
         * 价格
         */
        public static final String SORT_PRICE = "1";
    }

    public static final int PAGE_SIZE = 20;          //参数最少设置为20!!!!!要不然会产生问题！！！！
    public static final int PAGE_SIZE_TEN = 10;
    public static final int THROTTLE_TIME = 500;    //设定手抖时间

}
