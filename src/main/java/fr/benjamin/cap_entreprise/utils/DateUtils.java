package fr.benjamin.cap_entreprise.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class DateUtils {

    public static String convertLocalDateToFormat(LocalDate ld, String pattern){
        return convertLocalDateTimeToFormat(ld.atTime(0,0),pattern);
    }

    public static String convertLocalDateTimeToFormat(LocalDateTime ldt, String pattern){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return formatter.format(ldt);
    }

}
