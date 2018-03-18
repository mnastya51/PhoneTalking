package utils;

import java.util.ArrayList;
import java.util.List;

public class FilterUtils {

    public static class FilterFormatter {
        private List<String> values;
        public FilterFormatter() {
            values = new ArrayList<>() ;
        }

        public void addValueWithRegisters(String key, String value) {
            if (value != null && !value.isEmpty())
                values.add(String.format(" UPPER(REPLACE(%s,' ','')) LIKE(UPPER(REPLACE(\'%s\',' ','')))", key, "%" + value + "%"));
        }

        public void addValue(String key, boolean value) {
            values.add(String.format(" %s = \'%b\'", key, value));
        }

        public void addValue(String key, String value) {
            values.add(String.format(" %s = \'%s\'", key, value));
        }

        public void addValue(String key, int value) {
            values.add(String.format(" %s = \'%d\'", key, value));
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

        public String getFormattedRequestForTarifDB() {
            String res = String.format("SELECT \"phoneTalking\".\"city\".cityname, \"phoneTalking\".\"tarif\".periodStart," +
                    "\"phoneTalking\".\"tarif\".periodend,  \"phoneTalking\".\"tarif\".mincost FROM \"phoneTalking\".\"tarif\" JOIN " +
                    "\"phoneTalking\".\"city\" on \"phoneTalking\".\"city\".cityid = \"phoneTalking\".\"tarif\".cityid where");
            if (values.size() == 0) return null;
            res += values.get(0);
            for (int i = 1; i < values.size(); i++) {
                res+= " AND " + values.get(i);
            }
            return res;
        }

        public String getFormattedRequestForTalkingDB() {
            String res = String.format("SELECT \"phoneTalking\".\"talking\".talkid, \"phoneTalking\".\"abonent\".phone, " +
                    " \"phoneTalking\".\"city\".cityname, \"phoneTalking\".\"talking\".mincount,\"phoneTalking\".\"talking\".talkdate," +
                    " \"phoneTalking\".\"talking\".talktime, \"phoneTalking\".\"talking\".talkcost FROM \"phoneTalking\".\"talking\"" +
                    " JOIN \"phoneTalking\".\"abonent\" on \"phoneTalking\".\"abonent\".abonentid = \"phoneTalking\".\"talking\".abonentid " +
                    "JOIN \"phoneTalking\".\"city\" on \"phoneTalking\".\"city\".cityid = \"phoneTalking\".\"talking\".cityid where");
            if (values.size() == 0) return null;
            res += values.get(0);
            for (int i = 1; i < values.size(); i++) {
                res+= " AND " + values.get(i);
            }
            return res;
        }
    }
}
