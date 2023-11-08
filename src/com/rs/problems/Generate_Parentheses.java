package com.rs.problems;

import javax.xml.transform.Result;
import java.util.ArrayList;
import java.util.List;

public class Generate_Parentheses {

    List<String> generate(int num){
        List<String> s = new ArrayList<>();
        generate(num, num, "", s);
        return s;
    }

    private void generate(int nOpen, int nClose, String gString, List<String> result) {
        if(nOpen == nClose && nOpen == 0){
            result.add(gString);
            return;
        }
        if(nOpen > 0){
            gString += "(";
            generate(nOpen - 1, nClose, gString, result);
        }
        if(nOpen < nClose){
            gString += ")";
            generate(nOpen , nClose - 1, gString, result);
        }
    }

    public static void main(String[] args){
        Generate_Parentheses o = new Generate_Parentheses();
        System.out.println(o.generate(3));
    }
}
