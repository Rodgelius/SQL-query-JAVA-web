package dgava.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class MainController extends HttpServlet{

    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;
    private String query;
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH.mm.ss");
    public static String timeFromTxt = LocalDateTime.now().minusDays(3).format(formatter).toString();
    public static String timeToTxt = LocalDateTime.now().format(formatter).toString();
    public static String name = "%";

    private static List<Row> list = new ArrayList<>();

    public List getList() {

        try {
            DriverManager.registerDriver(new org.mariadb.jdbc.Driver());
            con = DriverManager.getConnection("jdbc:mysql://zabbix2:3306/zabbix", "read", "");
            stmt = con.createStatement();

            if (LocalDateTime.parse(timeFromTxt, formatter).isAfter(LocalDateTime.parse(timeToTxt, formatter))) {   // timefrom <-> timeto
                String t = timeFromTxt;
                timeFromTxt = timeToTxt;
                timeToTxt = t;
            }
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
                    "hosts.name LIKE '%" + name + "%' AND " +
                    "events.clock " +
                    "BETWEEN UNIX_TIMESTAMP( '" + timeFromTxt + "' ) AND " +
                    "UNIX_TIMESTAMP( '" + timeToTxt + "' ) " +
                    "ORDER BY id ASC LIMIT 15;";

            rs = stmt.executeQuery(query);
            list.clear();

            while(rs.next()) {                                              // Считываем все доступные строки
                String col1 = rs.getString("id");
                String col2 = rs.getString("event_time");
                String col3 = rs.getString("name");
                String col4 = rs.getString("ip");
                String col5 = rs.getString("trigger");
                String col6 = rs.getString("item");
                String col7 = rs.getString("status");
                String col8 = rs.getString("priority");
                list.add(new Row(col1.trim(), col2.trim(), col3.trim(), col4.trim(), col5.trim(), col6.trim(), col7.trim(), col8.trim())); // Добавляем очередную строку в list
            }
            Export.setList(list);
        }
        catch (SQLException e){
                System.out.println("!что-то глюкануло");
                return getList();
        }
        return list;
    }

    private static void setName(String hostname) { name = hostname; }
    private static void setTimeFromTxt(String timeFrom) { timeFromTxt = timeFrom; }
    private static void setTimeToTxt(String timeTo) { timeToTxt = timeTo; }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MainController.setName(request.getParameter("host"));
        MainController.setTimeFromTxt(request.getParameter("from"));
        MainController.setTimeToTxt(request.getParameter("to"));
        response.sendRedirect("http://localhost:8080");
    }
}

