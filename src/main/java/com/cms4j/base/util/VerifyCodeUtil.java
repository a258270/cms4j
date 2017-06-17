package com.cms4j.base.util;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * description:
 *
 * @author: zmj
 * @create: 2017/5/31
 */
public class VerifyCodeUtil {

    public static BufferedImage createImage(String strCode) {
        int iLength = strCode.length();
        int width = 16 * iLength;
        int height = 37;
        Random random = new Random();
        //生成缓冲区image类
        BufferedImage image = new BufferedImage(width, height, 1);
        //产生image类的Graphics用于绘制操作
        Graphics g = image.getGraphics();
        //Graphics类的样式
        g.setColor(getRandColor(200, 250));
        g.setFont(new Font("Times New Roman",0,28));
        g.fillRect(0, 0, width, height);
        //绘制干扰线
        for(int i=0;i<40;i++){
            g.setColor(getRandColor(130, 200));
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int x1 = random.nextInt(12);
            int y1 = random.nextInt(12);
            g.drawLine(x, y, x + x1, y + y1);
        }

        //绘制字符
        for(int i = 0; i < iLength; i++){
            String rand = strCode.substring(i, i + 1);
            g.setColor(new Color(20+random.nextInt(110),20+random.nextInt(110),20+random.nextInt(110)));
            g.drawString(rand, 8 * i + 8, 28);
        }
        g.dispose();

        return image;
    }

    public static Color getRandColor(int fc, int bc){
        Random random = new Random();
        if(fc>255)
            fc = 255;
        if(bc>255)
            bc = 255;
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r,g,b);
    }

    public static String getCode(int iLength) {
        String strCode = "";
        for(int i = 0; i < iLength; i++){
            Random random = new Random();
            strCode += random.nextInt(10);
        }

        return strCode;
    }
}
