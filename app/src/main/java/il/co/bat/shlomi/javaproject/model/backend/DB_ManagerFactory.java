package il.co.bat.shlomi.javaproject.model.backend;

import il.co.bat.shlomi.javaproject.model.datasource.DBManager_Firebase;

public class DB_ManagerFactory {

    static DB_manager db_manager = null;

    public static DB_manager getBL()
    {
        if (db_manager == null)
            db_manager = new DBManager_Firebase();
        return db_manager;
    }
}
