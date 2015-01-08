package interview.commvault.serialization;

import java.io.File;

public class PagingTableTest {
    private static final int count = 10000;

    public static void main(String[] args) {
        GenerateData.generateData(new File(IPageLoader.DATA_FILE), count);

        PagingTable pt = new PagingTable();
        
        while(true) {
            /* read input from console */
            
        }
    }
}
