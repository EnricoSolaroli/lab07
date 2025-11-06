package it.unibo.nestedenum;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

/**
 * Implementation of {@link MonthSorter}.
 */
public final class MonthSorterNested implements MonthSorter {

    public enum Month {
        JANUARY(31), FEBRUARY(28), MARCH(31), APRIL(30), 
        MAY(31), JUNE(30), JULY(31), AUGUST(31),
        SEPTEMBER(30), OCTOBER(31), NOVEMBER(30), DECEMBER(31);

        private final int dayOfMonth; 

        private Month(final int dayOfMonth){
            this.dayOfMonth = dayOfMonth;
        }

        public static Month fromString (String nameMonth){
            if(nameMonth == null){
                throw new IllegalArgumentException("il nome del mese non puo' essere nullo");
            }
            final String searchPrefix = nameMonth.trim().toUpperCase();

            List<Month> matches = new ArrayList<>();
            
            for(Month month: Month.values()){
                if(month.name().startsWith(searchPrefix)){
                    matches.add(month);
                }
            }
            if(matches.size()>1){
                throw new IllegalArgumentException("Input ambiguo " + nameMonth + " puo' corrispondere a diversi mesi");    
            } else if(matches.size() == 0){
                throw new IllegalArgumentException("nessuno mese corrisponde " + nameMonth);
            }
            return matches.get(0);
        }

        public int getDayOfMonth() {
            return this.dayOfMonth;
        }
    }

    public static class SortByMonthOrder implements Comparator<String>{
        @Override
        public int compare(String o1, String o2) {
            Month m1 = Month.fromString(o1);
            Month m2 = Month.fromString(o2);
            return m1.compareTo(m2);
        } 
    }

    public static class SortByData implements Comparator<String>{
        @Override
        public int compare(String o1, String o2) {
            Month m1 = Month.fromString(o1);
            Month m2 = Month.fromString(o2);
            return Integer.compare(m1.getDayOfMonth(), m2.getDayOfMonth());
        }
    }

    @Override
    public Comparator<String> sortByDays() {
        return new SortByData();
    }

    @Override
    public Comparator<String> sortByOrder() {
        return new SortByMonthOrder();
    }
}
