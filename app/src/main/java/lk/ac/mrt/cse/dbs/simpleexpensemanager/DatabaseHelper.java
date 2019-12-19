package lk.ac.mrt.cse.dbs.simpleexpensemanager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    static final String DB_NAME = "170160E";

    static final int DB_VERSION = 1;

    private static final String CREATE_ACCOUNT_TABLE = "CREATE TABLE " +
            "account ( " +
            "accountNo INTEGER PRIMARY KEY, " +
            "bankName VARCHAR(255) NOT NULL, " +
            "accountHolderName VARCHAR(255) NOT NULL, " +
            "balance NUMERIC(20,2) NOT NULL);";

    private static final String CREATE_TRANSACTION_TABLE = "CREATE TABLE " +
            "`transaction` ( " +
            "date VARCHAR(10) NOT NULL, " +
            "accountNo INTEGER NOT NULL, " +
            "expenseType VARCHAR(7) NOT NULL, " +
            "amount NUMERIC(20,2) NOT NULL, " +
            "FOREIGN KEY (accountNo) REFERENCES account(accountNo));";

    public DatabaseHelper(Context context ) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_ACCOUNT_TABLE);
        db.execSQL(CREATE_TRANSACTION_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS account");
        db.execSQL("DROP TABLE IF EXISTS `transaction`");
    }
}
