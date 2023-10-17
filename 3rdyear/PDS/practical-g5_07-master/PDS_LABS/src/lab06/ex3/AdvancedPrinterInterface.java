package lab06.ex3;

import java.util.List;

public interface AdvancedPrinterInterface {
    public int print(Document doc);
    public List<Integer> print(List<Document> docs);
    public void showQueuedJobs();
    public boolean cancelJob(int jobId) ;
    public void cancelAll();
}
