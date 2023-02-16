package dao;

import dao.daoFiles.AccountDao;
import dao.daoFiles.ClientDao;
import dao.daoFiles.LogDao;

public class DataBase {

    private static final ClientDao cliantsDao = new ClientDao();
    private static final AccountDao accountsDao = new AccountDao();
    private static final LogDao logsDao = new LogDao();

    public static AccountDao getAccountsDao() {
        return accountsDao;
    }

    public static ClientDao getCliantsDao() {
        return cliantsDao;
    }

    public static LogDao getLogsDao() {
        return logsDao;
    }
}
