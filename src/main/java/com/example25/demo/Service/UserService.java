package com.example25.demo.Service;
import com.example25.demo.convertionBase64.Base64;
import com.example25.demo.document.Urls;
import com.example25.demo.exception.UrlNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example25.demo.repository.*;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Service
@CacheConfig(cacheNames = "rediscache")
public class UserService {
    @Autowired
    MongoOperations mongoOperations;
    private static convertRepository c1;
    public UserService(convertRepository c1) {
        this.c1 = c1;
    }

        public Urls save (String lurl, HttpServletResponse r){
        Urls u1=search("longurl",lurl);
        if(u1==null) {
            String tny = Base64.getTinyBase64();
            u1 = new Urls( tny, lurl);
            r.setStatus(201);
        }
        else
            r.setStatus(302);
        return c1.save(u1);

    }

        public List<Urls> searchLong(String s)
        {
           Urls u1=search("tinyurl",s);
           List<Urls> allUrls=new ArrayList<Urls>();


               while(u1!=null)
               {
                   u1.incCnt();
                   allUrls.add(u1);
                   c1.save(u1);
                   u1=search("tinyurl",u1.getLongurl());
               }

           return allUrls;
        }

       @CachePut(value = "Urls",key = "#s")
        public List<Urls> getLong(String s)
        {
            List<Urls> u=searchLong(s);
            if(u.size()==0)
                throw new UrlNotFoundException("");
            return u;
        }

        @CachePut(value = "Urls",key = "#s")
        public List<Urls> getTiny(String s)
        {
            List<Urls> u=searchTiny(s);
            if(u.size()==0)
                throw new UrlNotFoundException("");
            else
             return u;
        }

        public List<Urls> searchTiny (String s)
        {
            Urls u1=search("longurl",s);
            List<Urls> urls=new ArrayList<Urls>();


                while(u1!=null)
                {
                    u1.incCnt();
                    urls.add(u1);
                    c1.save(u1);
                    u1=search("longurl",u1.getTinyurl());
                }

            return urls;

        }



        @CacheEvict(value = "Urls",allEntries = true)
        public String searchanddelete (String s1,String s2)
        {

                Query query = new Query(Criteria.where(s1).is(s2));
                List<Urls> r1=searchLong(s2);
                List<Urls> r2=searchTiny(s2);

            mongoOperations.remove(query, Urls.class);
              if(r1.size()==0 && r2.size()==0)
              {
                throw new UrlNotFoundException("");
              }
              else {
                  for (Urls a : r2)
                      deleteUrl(a.getLongurl());

                  if (r1.size() < 2 || (r1.size()<=2 && s1.equals("tinyurl"))) {
                      for (Urls a : r1)
                          deleteUrl(a.getLongurl());
                  }

              }

            return "Remove Sucessfull";

        }


        public void deleteUrl(String a)
        {
            Query query=new Query(Criteria.where("longurl").is(a));
            mongoOperations.remove(query,Urls.class);
            return;

        }


        public List<Urls> getAllvalues () {

        List<Urls> one;
        one = c1.findAll();
        return one;
    }

    public Urls search(String u1,String u2)
    {
        Urls ul;
        try {
            Query query = new Query(Criteria.where(u1).is(u2));
            ul = (Urls) mongoOperations.findOne(query, Urls.class);

        }
        catch (NullPointerException e)
        {
            return null;
        }
        return ul;
    }


}
