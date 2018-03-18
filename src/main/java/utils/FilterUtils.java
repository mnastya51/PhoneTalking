package utils;

import java.util.ArrayList;
import java.util.List;

public class FilterUtils {

    public static class FilterFormatter {
        private List<String> values;
        public FilterFormatter() {
            values = new ArrayList<>() ;
        }

        public void addValue(String key, String value) {
            if (value != null && !value.isEmpty())
                values.add(String.format(" UPPER(REPLACE(%s,' ','')) LIKE(UPPER(REPLACE(\'%s\',' ','')))", key, "%" + value + "%"));
        }

        public void addValue(String key, boolean value) {
            values.add(String.format(" %s = \'%b\'", key, value));
        }

        public String getFormattedRequestForAbonentDB() {
            String res = String.format("SELECT *  FROM \"phoneTalking\".\"abonent\" where");
            if (values.size() == 0) return null;
            res += values.get(0);
            for (int i = 1; i < values.size(); i++) {
                res+= " AND " + values.get(i);
            }
            return res;
        }
    }
}
