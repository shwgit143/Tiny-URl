package com.example25.demo.Controller;
import com.example25.demo.document.Urls;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.example25.demo.Service.*;

import javax.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("/tinyurls")
public class ConvertController {

    private final UserService u1;
    public ConvertController(UserService u1) {
        this.u1 = u1;
    }

    @PostMapping(value="/save")
    public Urls saveALl(@RequestBody String lurl, HttpServletResponse r){
        return u1.save(lurl,r);
    }

    @GetMapping("/searchtiny")
    public List<Urls> getLongUrl(@RequestBody String s,HttpServletResponse r)
    {
        r.setStatus(202);
        return u1.getLong(s);
    }
    @GetMapping("/searchlong")
    public List<Urls> getTinyUrl(@RequestBody String s,HttpServletResponse r)
    {
        r.setStatus(202);
        return u1.getTiny(s);
    }
    @DeleteMapping("/deletebytiny")
    @ResponseStatus(value = HttpStatus.OK)
    public String deletebyTiny(@RequestBody String s)
    {

        return u1.searchanddelete("tinyurl",s);
    }
    @DeleteMapping("/deletebylong")
    @ResponseStatus(value = HttpStatus.OK)
    public String deletebylong(@RequestBody String s)
    {

        return u1.searchanddelete("longurl",s);

    }
    @GetMapping("/getall")
    @ResponseStatus(value = HttpStatus.OK)
    public List<Urls> getAll(){
        return u1.getAllvalues();
    }
}
