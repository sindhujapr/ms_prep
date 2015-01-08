package interview.commvault.serialization;

import java.io.Serializable;

public class MyObject implements Serializable {
    private static final long serialVersionUID = 6902342342324L;
    
    /* Serializable class with 4 fields of different types */
    private String column1;
    private int column2;
    private boolean column3;
    private long column4;
    
    public static MyObject parseMyObject(String string) {
        MyObject object = new MyObject();
        /* this doesn't work if field column1 contains the separator */
        String[] fields = string.split(" ");
        System.out.println(fields);
        
        String c1 = fields[0].substring(fields[0].indexOf(":")+1);
        object.setColumn1(c1);
        int c2 = Integer.parseInt(fields[1].substring(fields[1].indexOf(":")+1));
        object.setColumn2(c2);
        boolean c3 = Boolean.parseBoolean(fields[2].substring(fields[2].indexOf(":")+1));
        object.setColumn3(c3);

        String subString = fields[3].substring(fields[3].indexOf(":")+1);
        long c4 = Long.parseLong(subString.trim());
        object.setColumn4(c4);
        
        return object;
    }

    public String getColumn1() {
        return column1;
    }
    public void setColumn1(String column) {
        this.column1 = column;
    }
    
    public int getColumn2() {
        return column2;
    }
    public void setColumn2(int column) {
        this.column2 = column;
    }
    
    public boolean getColumn3() {
        return column3;
    }
    public void setColumn3(boolean column) {
        this.column3 = column;
    }
    
    public long getColumn4() {
        return column4;
    }
    public void setColumn4(long column) {
        this.column4 = column;
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("column1:");
        sb.append(column1);
        sb.append(" column2:");
        sb.append(column2);
        sb.append(" column3:");
        sb.append(column3);
        sb.append(" column4:");
        sb.append(column4);
        return sb.toString();
    }
}