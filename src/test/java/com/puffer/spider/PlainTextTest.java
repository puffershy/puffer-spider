package com.puffer.spider;

import us.codecraft.webmagic.selector.PlainText;

/**
 * @author puffer
 * @date 2020年05月14日 15:55:55
 * @since
 */
public class PlainTextTest {

    public static void main(String[] args) {

        String text = "https://www.amazon.com/Best-Sellers-Toys-Games-Kids-Party-Balloons/zgbs/toys-and-games/274321011/ref=zg_bs_nav_t_2_1266203011";
        PlainText plainText = PlainText.create(text);

//        String url = "https://www.amazon.com/Best-Sellers-Toys-Games-Kids-Party-Balloons/zgbs/toys-and-games/274321011/ref=zg_bs_nav_t_2_1266203011";
//        System.out.println(plainText.regex(url).match());
//
//        url = "https://www.amazon.com/Best-Sellers-Toys-Games-Kids-Party-Balloons/zgbs/toys-and-games/274321011/ref=*";
//        System.out.println(plainText.regex(url).match());
//
//        url = "https://www.amazon.com/Best-Sellers-Toys-Games-Kids-Party-Balloons/zgbs/toys-and-games/274321011/ref=zg_bs_pg_*";
//        System.out.println(plainText.regex(url).match());


//        String url = "https://www.amazon.com/Best-Sellers-Kitchen-Dining-Novelty-Coffee-Mugs/zgbs/kitchen/9302388011/ref=zg_bs_pg_1?_encoding=UTF8&pg=1";


        System.out.println(plainText.regex(".*Best-Sellers.*").match());


    }
}
