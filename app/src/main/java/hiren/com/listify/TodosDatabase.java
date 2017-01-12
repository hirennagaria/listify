package hiren.com.listify;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by vieva on 15-Dec-16.
 */

@Database(name = TodosDatabase.NAME, version = TodosDatabase.VERSION)

public class TodosDatabase {

    public static final String NAME = "TodoDatabase";

    public static final int VERSION = 1;
}
