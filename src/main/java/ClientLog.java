import com.opencsv.CSVWriter;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ClientLog {

    String log = "productNum,amount\n";

    public void log(int productNum, int amount) {
        log += String.format("%d,%d\n", productNum, amount);
    }

    public void exportAsCSV(File txtFile) throws IOException {
        FileWriter writer = new FileWriter(txtFile);
        writer.write(log);
        writer.close();
    }
}