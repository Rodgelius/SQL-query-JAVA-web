package dgava.controller;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 08.02.17.
 */
public class MainController {

//    private static final String url = "jdbc:mysql://zabbix2.cod.sz.rt.ru:3306/zabbix";
//    private static final String user = "read";
//    private static final String password = "654321";
    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;
    private String query;
    String timetotxt = "2017-01-30 13.00.00";
    String timefromtxt = "2017-01-10 13.00.00";
    String textfield = "bis";
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH.mm.ss");


    private List list;

    public MainController()  {
        list = new ArrayList();

        try {
            DriverManager.registerDriver(new org.mariadb.jdbc.Driver());
            con = DriverManager.getConnection("jdbc:mysql://zabbix2.cod.sz.rt.ru:3306/zabbix", "read", "");
            stmt = con.createStatement();

System.out.print("");
//            if (!timefrom.getValue().equals(null))
//                timefromtxt = timefrom.getDateTimeValue().format(formatter);         //if timefrom not blank
//            if (!timeto.getValue().equals(null))
//                timetotxt = timeto.getDateTimeValue().format(formatter);               //if timeto not blank
//            if (LocalDateTime.parse(timefromtxt, formatter).isAfter(LocalDateTime.parse(timetotxt, formatter))) {        // timefrom <-> timeto
//                String t = timefromtxt;
//                timefromtxt = timetotxt;
//                timetotxt = t;
//            }
            query = "SELECT events.eventid AS id, " +
                    "from_unixtime( clock, '%Y-%m-%d %H.%i.%s' ) AS event_time, " +
                    "triggers.description AS 'trigger', " +
                    "items.name AS item, " +
                    "events.value AS status, triggers.priority, hosts.name, interface.ip " +
                    "FROM zabbix.triggers AS triggers, " +
                    "zabbix.events AS events, " +
                    "zabbix.functions AS functions, " +
                    "zabbix.items AS items, " +
                    "zabbix.hosts AS hosts, " +
                    "zabbix.interface AS interface " +
                    "WHERE triggers.triggerid = events.objectid AND " +
                    "functions.triggerid = triggers.triggerid AND " +
                    "functions.itemid = items.itemid AND " +
                    "items.hostid = hosts.hostid AND " +
                    "interface.hostid = hosts.hostid AND " +
                    "events.source = 0 AND " +
                    "interface.main = 1 AND " +
                    "hosts.name LIKE '%%' AND " +
                    "events.clock " +
                    "BETWEEN UNIX_TIMESTAMP( '2017-01-10 13.00.00' ) AND " +
                    "UNIX_TIMESTAMP( '2017-01-30 13.00.00' ) " +
                    "ORDER BY id ASC LIMIT 15;";
            rs = stmt.executeQuery(query);

            System.out.println(textfield);
            System.out.println(timefromtxt);
            System.out.println(timetotxt);
            System.out.println("отправил запрос, собираю данные");
            System.out.println(query);

while(rs.next()) {
    String col1 = rs.getString("id");
    String col2 = rs.getString("event_time");
    String col3 = rs.getString("name");
    String col4 = rs.getString("ip");
    String col5 = rs.getString("trigger");
    String col6 = rs.getString("item");
    String col7 = rs.getString("status");
    String col8 = rs.getString("priority");

    System.out.println(col1 + col2);
    System.out.println("добавляю в список");

    this.list.add(new Row(col1.trim(), col2.trim(), col3.trim(), col4.trim(), col5.trim(), col6.trim(), col7.trim(), col8.trim()));

}
        }
        catch (SQLException e){
            try {
                rs = stmt.executeQuery("select * from hosts limit 1;");
                System.out.println("!попытка вызвать простой запрос");
            } catch (SQLException e1) {
                System.out.println("!что-то не так c сервером");
            }
            System.out.println("!что-то не так в запросе");
        }

//        System.out.print(list.get(0));
    }

    public List getList() {


        return list;
    }
}
