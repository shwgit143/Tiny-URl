package com.example25.demo.convertionBase64;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.example25.demo.convertionBase64.CheckingWords;

public class Base64 {
      public static String keys1=new String("qwertyuiopasdfghjklzxcvbnmQWERTYUIO.PASDFGHJKLZXC-VBNM0987654321");

      private static CheckingWords c1=new CheckingWords();
      public static String getTinyBase64()
      {
          SimpleDateFormat dtf=new SimpleDateFormat("ddmmyyyyHHmmMMssSS");
          Date date=new Date();
          String z=dtf.format(date);
          BigInteger b1=new BigInteger(z);
          BigInteger b2=new BigInteger("0");
          BigInteger b3=new BigInteger("64");
          String st=new String("http://");
          BigInteger b4,b5;
          int c=0;
          while(b1.compareTo(b2)!=0)
          {
              b4=b1.mod(b3);
              c=b4.intValue();
              st+=keys1.charAt(c);
              b1=b1.divide(b3);
          }
          if(c1.checking(st)) {
              st=getTinyBase64();
          }
          return  st;
      }

}
