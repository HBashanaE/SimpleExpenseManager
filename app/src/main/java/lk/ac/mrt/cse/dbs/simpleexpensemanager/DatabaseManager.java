package lk.ac.mrt.cse.dbs.simpleexpensemanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DatabaseManager {

    private DatabaseHelper dbHelper;
    private Context context;
    private SQLiteDatabase database;

    public DatabaseManager(Context c) {
        this.context = c;
        this.dbHelper = new DatabaseHelper(context);
    }

    public DatabaseManager open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public void insertAccountData(String accNo, String bankName, String accHolder, double balance) {
        database = dbHelper.getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put("accountNumber", accNo);
        content.put("bankName", bankName);
        content.put("accountHolderName", accHolder);
        content.put("balance", balance);
        database.insert("account", null, content);
    }

    public Cursor fetchAccountData() {
        database = dbHelper.getReadableDatabase();
        Log.d("Work so far","Worked");
//        Cursor cursor = database.query("Account",new String [] {"accountNo", "bankName", "accountHolderName","balance"}, null, null,null, null, null);
        Log.d("Work so far","Worked");
        Cursor cursor = database.rawQuery("SELECT * from account" , null);
        return cursor;
    }

    public Cursor fetchAccountDataByAccNo(String accNo) {
        database = dbHelper.getReadableDatabase();
        Cursor cursor = database.query("account",new String [] {"accountNo", "bankName", "accountHolderName","balance"}, "accountNo = ?", new String [] {accNo},null, null, null);
        return cursor;
    }

    public void insertTransactionData(String date, String accNo, String type, double amount) {
        database = dbHelper.getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put("date", date);
        content.put("accountNumber", accNo);
        content.put("expenseType", type);
        content.put("amount", amount);
        database.insert("account", null, content);
    }

    public Cursor fetchTransactionData() {
        database = dbHelper.getReadableDatabase();
        Cursor cursor = database.query("transactions",new String [] {"date", "accountNo", "expenseType","amount"}, null, null,null, null, null);
        return cursor;
    }

    public Cursor fetchTransactionDataLimit(String limit) {
        database = dbHelper.getReadableDatabase();
        Cursor cursor = database.query("transactions",new String [] {"date", "accountNo", "expenseType","amount"}, null, null,null, null, null, limit);
        return cursor;
    }
}
