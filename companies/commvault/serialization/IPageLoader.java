package interview.commvault.serialization;

public interface IPageLoader {
    String DATA_FILE = "test.dat";
    /* Make the data between rows start and end available */
    void setDataAvailable(int start, int end);
    /* Show the data in the specified row */
    MyObject getRowAt(int row);
    /* Return how many data has been generated */
    int getRowCount();
}
