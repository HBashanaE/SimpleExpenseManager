package lk.ac.mrt.cse.dbs.simpleexpensemanager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    static final String DB_NAME = "170160E";

    static final int DB_VERSION = 1;

    private static final String CREATE_ACCOUNT_TABLE = "CREATE TABLE " +
            "account ( " +
            "accountNo VARCHAR PRIMARY KEY, " +
            "bankName VARCHAR NOT NULL, " +
            "accountHolderName VARCHAR NOT NULL, " +
            "balance NUMERIC NOT NULL);";

    private static final String CREATE_TRANSACTION_TABLE = "CREATE TABLE " +
            "transactions ( " +
            "date VARCHAR NOT NULL, " +
            "accountNo VARCHAR NOT NULL, " +
            "expenseType VARCHAR NOT NULL, " +
            "amount NUMERIC NOT NULL, " +
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
        db.execSQL("DROP TABLE IF EXISTS transactions");

        onCreate(db);
    }
}
