package dao;

public interface ITransactionManager {
    boolean beginTransaction();
    boolean endTransaction();
    String getLabel();
}
