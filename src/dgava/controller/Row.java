package dgava.controller;

public class Row {

    private final String col1;
    private final String col2;
    private final String col3;
    private final String col4;
    private final String col5;
    private final String col6;
    private final String col7;
    private final String col8;

    public Row(String row1, String row2, String row3, String row4, String row5, String row6, String row7, String row8){
        this.col1 = row1;
        this.col2 = row2;
        this.col3 = row3;
        this.col4 = row4;
        this.col5 = row5;
        this.col6 = row6;
        this.col7 = row7;
        this.col8 = row8;
    }

    public String getCol1() {
        return col1;
    }
    public String getCol2() { return col2; }
    public String getCol3() {
        return col3;
    }
    public String getCol4() { return col4; }
    public String getCol5() {
        return col5;
    }
    public String getCol6() {
        return col6;
    }
    public String getCol7() {
        return col7;
    }
    public String getCol8() {
        return col8;
    }
}