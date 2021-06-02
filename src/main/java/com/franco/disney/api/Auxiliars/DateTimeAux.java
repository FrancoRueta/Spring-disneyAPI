package com.franco.disney.api.Auxiliars;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;



//Conversor auxiliar para pasar string a localDate y viceversa.
public class DateTimeAux {
    public String dtStr(LocalDate localDate){

        String stringDate = localDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        return stringDate;
    }

    public LocalDate strDt(String sDate){
        LocalDate localDate = LocalDate.parse(sDate, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        return localDate;
    }
}


