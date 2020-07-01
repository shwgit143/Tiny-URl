package com.example25.demo.convertionBase64;
import java.io.FileReader;
import java.io.*;
import java.util.logging.Logger;

public class CheckingWords {
    //check and Remove vulgar words 
    public static boolean checking(String s){
        String str="nal,anus,arse,ass,ass fuck,ass hole,assfucker,asshole,assshole,bastard,bitch,black cock,bloody hell,boong,cock,cockfucker,cocksuck,cocksucker,coon,coonnass,crap,cunt,cyberfuck,damn,darn,dick,dirty,douche,dummy,erect,erection,erotic,escort,fag,faggot,fuck,Fuck off,fuck you,fuckass,fuckhole,god damn,gook,hard core,hardcore,homoerotic,hore,lesbian,lesbians,mother fucker,motherfuck,motherfucker,negro,nigger,orgasim,orgasm,penis,penisfucker,piss,piss off,porn,porno,pornography,pussy,retard,sadist,sex,sexy,shit,slut,son of a bitch,suck,tits,viagra,whore,xxx";
        String st=str.replace(" ","");
        String[] ss=st.split(",");
        int ct=0;
        for(String aa : ss){
            if(s.contains(aa))
            {
                ct=1;
                break;
            }
        }
        if(ct==1)
            return true;
        return false;
    }


}

