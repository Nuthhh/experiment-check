package com.ysu.textsimilarity;

/**
 * @Auther: han jianguo
 * @Date: 2019/2/21 15:24
 * @Description: 汉明距离
 **/
public class HammingDistance {

    /**
     * 功能描述: 计算两个SimHash之间的海明距离
     *
     * @auther: han jianguo
     * @date: 2019/2/21 15:55
     */
    public static int getDistance(String str1, String str2) {

        int distance;
        if (str1.length() != str2.length()) {
            distance = -1;
        } else {
            distance = 0;
            for (int i = 0; i < str1.length(); i++) {
                if (str1.charAt(i) != str2.charAt(i)) {
                    distance++;
                }
            }
        }
        return distance;
    }

}
