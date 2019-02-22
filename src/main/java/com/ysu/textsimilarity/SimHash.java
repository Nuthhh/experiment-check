package com.ysu.textsimilarity;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.common.Term;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Auther: han jianguo
 * @Date: 2019/2/21 15:23
 * @Description: 计算文本的 SimHash值
 **/
public class SimHash {

    private String tokens; // 待检测文本

    private BigInteger intSimHash;

    private String strSimHash; // SimHash算法结果

    private Integer hashbits = 64; // hash位数

    HashMap<String, Integer> wordMap = new HashMap<>(); // 单词权重

    public SimHash(String tokens) {
        this.tokens = tokens;
        this.intSimHash = this.simHash();
    }

    /**
     * 功能描述: 得到tokens的SimHash值
     *
     * @auther: han jianguo
     * @date: 2019/2/13 10:50
     */
    public BigInteger simHash() {
        // 定义特征向量/数组 64位
        int[] v = new int[this.hashbits];

        String word = null;
        String nature = null;
        // 采用HanLP进行中文分词
        List<Term> terms = HanLP.segment(this.tokens); // 对整个输入进行分词
        //System.out.println("terms:" + terms);
        for (Term term : terms) {

            word = term.word; // 得到单词
            nature = String.valueOf(term.nature.firstChar()); // 得到词性
            //System.out.println(word + ":" + nature);

            // 将每一个分词hash为一组固定长度的数列.比如 64bit 的一个整数.
            BigInteger t = this.hash(word);
            for (int i = 0; i < this.hashbits; i++) {

                BigInteger bitmask = new BigInteger("1").shiftLeft(i);

                // 建立一个长度为64的整数数组(假设要生成64位的数字指纹,也可以是其它数字),
                // 对每一个分词hash后的数列进行判断,如果是1000...1,那么数组的第一位和末尾一位加1,
                // 中间的62位减一,也就是说,逢1加1,逢0减1.一直到把所有的分词hash数列全部判断完毕.

                // todo 这里需要进行分词后权重赋值,需确定一个较为合适的权重赋值算法   词性or词频

                // Math.signum(x) //如果x大于0则返回1.0，小于0则返回-1.0，等于0则返回0
                if (t.and(bitmask).signum() != 0) {
                    // 这里是计算整个文档的所有特征的向量和
                    // 这里实际使用中需要 +- 权重，比如词频，而不是简单的 +1/-1，
                    v[i] += 1;
                } else {
                    v[i] -= 1;
                }
            }
        }

        BigInteger fingerprint = new BigInteger("0");
        StringBuffer simHashBuffer = new StringBuffer();
        for (int i = 0; i < this.hashbits; i++) {
            // 4、最后对数组进行判断,大于0的记为1,小于等于0的记为0,得到一个 64bit 的数字指纹/签名.
            if (v[i] >= 0) {
                fingerprint = fingerprint.add(new BigInteger("1").shiftLeft(i));
                simHashBuffer.append("1");
            } else {
                simHashBuffer.append("0");
            }
        }
        this.strSimHash = simHashBuffer.toString();
        return fingerprint;
    }

    /**
     *
     * 功能描述: 获得字符串的hash值
     *
     * @auther: han jianguo
     * @date: 2019/2/21 17:02
     */
    private BigInteger hash(String source) {
        if (source == null || source.length() == 0) {
            return new BigInteger("0");
        } else {
            // todo 这里对单词进行hash 不是很明白其各个运算的作用，待考究
            char[] sourceArray = source.toCharArray();
            BigInteger x = BigInteger.valueOf(((long) sourceArray[0]) << 7);
            //System.out.println("x:" + x);
            BigInteger m = new BigInteger("1000003");
            // 掩码 mask = 2^64-1
            BigInteger mask = new BigInteger("2").pow(this.hashbits).subtract(new BigInteger("1"));
            for (char item : sourceArray) {
                BigInteger temp = BigInteger.valueOf((long) item);
                x = x.multiply(m).xor(temp).and(mask);
            }
            x = x.xor(new BigInteger(String.valueOf(source.length())));
            if (x.equals(new BigInteger("-1"))) {
                x = new BigInteger("-2");
            }
            return x;
        }
    }


    /**
     *
     * 功能描述: TODO
     *
     * @auther: han jianguo
     * @date: 2019/2/21 17:01
     */
    public List<BigInteger> subByDistance(SimHash simHash, int distance) {
        // 分成几组来检查
        int numEach = this.hashbits / (distance + 1);
        List<BigInteger> characters = new ArrayList<>();

        StringBuffer buffer = new StringBuffer();

        System.out.println("bitLength:" + this.getIntSimHash().bitLength());

        for (int i = 0; i < this.intSimHash.bitLength(); i++) {
            // 当且仅当设置了指定的位时，返回 true
            // 验证第i位是否为1， 是返回true
            boolean sr = simHash.getIntSimHash().testBit(i);

            if (sr) {
                buffer.append("1");
            } else {
                buffer.append("0");
            }

            if ((i + 1) % numEach == 0) {
                // 将二进制转为BigInteger
                BigInteger eachValue = new BigInteger(buffer.toString(), 2);
                // 将buffer置null
                buffer.delete(0, buffer.length());
                characters.add(eachValue);
            }
        }

        return characters;
    }

    public String getTokens() {
        return tokens;
    }

    public void setTokens(String tokens) {
        this.tokens = tokens;
    }

    public BigInteger getIntSimHash() {
        return intSimHash;
    }

    public void setIntSimHash(BigInteger intSimHash) {
        this.intSimHash = intSimHash;
    }

    public String getStrSimHash() {
        return strSimHash;
    }

    public void setStrSimHash(String strSimHash) {
        this.strSimHash = strSimHash;
    }

    public Integer getHashbits() {
        return hashbits;
    }

    public void setHashbits(Integer hashbits) {
        this.hashbits = hashbits;
    }
}
