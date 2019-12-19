package lk.ac.mrt.cse.dbs.simpleexpensemanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseManager {

    private DatabaseHelper dbHelper;
    private Context context;
    private SQLiteDatabase database;

    public DatabaseManager(Context c) {
        context = c;
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
        ContentValues content = new ContentValues();
        content.put("accountNumber", Integer.parseInt(accNo));
        content.put("bankName", bankName);
        content.put("accountHolderName", accHolder);
        content.put("balance", balance);
        database.insert("account", null, content);
    }

    public Cursor fetchAccountData() {
        Cursor cursor = database.query("Account",new String [] {"accountNo", "bankName", "accountHolderName","balance"}, null, null,null, null, null);
        System.out.println("Work so far");
        return cursor;
    }

    public Cursor fetchAccountDataByAccNo(String accNo) {
        Cursor cursor = database.query("Account",new String [] {"accountNo", "bankName", "accountHolderName","balance"}, "accountNo = ?", new String [] {accNo},null, null, null);
        return cursor;
    }

    public void insertTransactionData(String date, String accNo, String type, double amount) {
        ContentValues content = new ContentValues();
        content.put("date", date);
        content.put("accountNumber", Integer.parseInt(accNo));
        content.put("expenseType", type);
        content.put("amount", amount);
        database.insert("account", null, content);
    }

    public Cursor fetchTransactionData() {
        Cursor cursor = database.query("Transaction",new String [] {"date", "accountNo", "expenseType","amount"}, null, null,null, null, null);
        return cursor;
    }
}
