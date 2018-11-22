package il.co.bat.shlomi.javaproject.model.backend;

import il.co.bat.shlomi.javaproject.model.datasource.List_DBManger;

public class DB_ManagerFactory {

    static DB_manager db_manager = null;

    public static DB_manager getBL()
    {
        if (db_manager == null)
            db_manager = new List_DBManger();
        return db_manager;
    }
}
