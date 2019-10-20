package com.puffer.spider;

import org.testng.annotations.Test;
import org.testng.collections.Lists;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式测试<br>
 * 推荐阅读文档：https://www.runoob.com/java/java-regular-expressions.html
 * https://www.runoob.com/regexp/regexp-intro.html
 *
 * @author buyi
 * @date 2019年10月20日 15:22:49
 * @since 1.0.0
 */
public class PatternTest {


    @Test
    public void getNumber() {
        List<String> list = Lists.newArrayList("SF123.134JKJ", "SDF123.SDF", "SDF123SDF");
        list.add("123,113.123");
        list.add("123,13.123");

        //获取数字
        String rule = "(\\d+)(,(\\d){3})?(\\.\\d+)?";
        Pattern pattern = Pattern.compile(rule);

        for (String s : list) {
            Matcher matcher = pattern.matcher(s);
            int i = matcher.groupCount();
            if (matcher.find()) {
//                System.out.println(s+"-"+i+"-"+);
                System.out.println(s + ":");
                System.out.println(matcher.group(0));
                System.out.println(matcher.group(1));
                System.out.println(matcher.group(2));
                System.out.println(matcher.group(3));
                System.out.println("——————————————————————");

            }
        }


    }
}
