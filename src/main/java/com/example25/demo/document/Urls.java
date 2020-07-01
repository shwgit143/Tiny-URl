package com.example25.demo.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.annotation.Generated;
import java.io.Serializable;

@Document
public class Urls implements Serializable {
    @Id
    private String longurl;
    private String tinyurl;
    private Integer cnt;


    public Urls(String tinyurl, String longurl) {
        this.tinyurl = tinyurl;
        this.longurl = longurl;
        this.cnt=0;
    }


    public String getTinyurl() {
        return tinyurl;
    }

    public String getLongurl() {
        return longurl;
    }

    public Integer getCnt() {
        return cnt;
    }

    public void incCnt() {
        this.cnt += 1;
    }
}
