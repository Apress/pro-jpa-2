package examples.stateless;

import java.util.List;

import examples.model.PrintJob;
import examples.model.PrintQueue;

public interface PrintService {
    public PrintQueue createPrinter(String name);
    public PrintJob createPrintJob(int jobId, String queueName);
    public PrintJob removePrintJob(int jobId, String queueName);
    public List<PrintQueue> listAllPrintQueues();
    public List<PrintJob> listAllJobs(String queueName);
}
