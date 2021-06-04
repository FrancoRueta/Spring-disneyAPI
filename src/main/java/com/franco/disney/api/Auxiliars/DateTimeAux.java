package com.franco.disney.api.Auxiliars;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;



//Conversor auxiliar para pasar string a localDate y viceversa.
public class DateTimeAux {
    public String dtStr(LocalDate localDate){
        /*Transforma un localDate a string.*/

        String stringDate = localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return stringDate;
    }

    public LocalDate strDt(String sDate){
        /*Transforma un string a localDate.*/
        LocalDate localDate = LocalDate.parse(sDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return localDate;
    }
}


