package examples.stateless;

public interface AuditService {
    public void logTransaction(int empNo, String action);
    public void logTransaction2(int empNo, String action);
}
