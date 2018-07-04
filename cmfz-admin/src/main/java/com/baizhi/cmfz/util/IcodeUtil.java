package com.baizhi.cmfz.util;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

public class IcodeUtil {
     public static char[] getRandomChars(){
    	 char[]  chs="0123456789abcdefghijklmn".toCharArray();
    	 char[]  vcode= new char[4];
    	 Random random=new Random();
    	 
    	 for(int i=0;i<vcode.length;i++){
    		 vcode[i]=chs[random.nextInt(chs.length)];
    	 }
    	 return vcode;
     }
     public static BufferedImage getImage(char[] icode){
    	 BufferedImage buffer =new BufferedImage(90,25,BufferedImage.TYPE_INT_BGR);
    	 
    	 //画布
    	 Graphics g=buffer.getGraphics();
    	 
    	 //背景色
    	 g.setColor(Color.BLACK);
    	 g.drawRect(0, 0, 90, 40);
    	 
    	 //前景色
    	 
    	 g.setColor(Color.WHITE);
    	 g.drawString(icode[0]+"", 10, 15);
    	 g.drawString(icode[1]+"", 30, 15);
    	 g.drawString(icode[2]+"", 50, 15);
    	 g.drawString(icode[3]+"", 70, 15);
    	 
    	 //生成图片
    	 g.dispose();
    	 return buffer;
     }
}   
