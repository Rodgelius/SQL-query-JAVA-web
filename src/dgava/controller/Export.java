package dgava.controller;

import jdk.nashorn.internal.codegen.ObjectClassGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class Export extends HttpServlet{

    private static List<Row> listexp = new ArrayList<>();

    public static void setList (List<Row> list){
        listexp = list;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=\"userDirectory.csv\"");
        try {
            OutputStream outStream = response.getOutputStream();
            String outputResult = "id;event_time;host;ip;trigger;item;status;priority\n";
            for (Row row : listexp) {
                outputResult += row.getCol1() + ";" + row.getCol2() + ";" + row.getCol3() + ";" + row.getCol4() + ";" + row.getCol5() + ";" + row.getCol6() + ";" + row.getCol7() + ";" + row.getCol8() + "\n";
            }
            outStream.write(outputResult.getBytes());
            outStream.flush();
            outStream.close();
        }
        catch (Exception e){ System.out.println("export error");}
    }
}
