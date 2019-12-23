package lk.ac.mrt.cse.dbs.simpleexpensemanager.data.impl;

import android.content.Context;
import android.database.Cursor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lk.ac.mrt.cse.dbs.simpleexpensemanager.DatabaseManager;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.control.PersistentExpenseManager;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.TransactionDAO;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.model.ExpenseType;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.model.Transaction;

public class PersistentTransactionDAO implements TransactionDAO {

    private Context context;
    private DatabaseManager dbManager;

    public PersistentTransactionDAO(Context c) {
        this.context = c;
        dbManager = new DatabaseManager(c);
    }

    @Override
    public void logTransaction(Date date, String accountNo, ExpenseType expenseType, double amount) {
        dbManager.insertTransactionData(date.toString(),accountNo, expenseType.toString(),amount);
    }

    @Override
    public List<Transaction> getAllTransactionLogs() {
        List<Transaction> transactionList = new ArrayList<>();
        Cursor transactionCursor = dbManager.fetchTransactionData();
        if (transactionCursor != null) {
            transactionCursor.moveToFirst();
        }
        while (transactionCursor.moveToNext()) {
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-YYYY");
            String date = transactionCursor.getString(0);
            String accountNo = String.valueOf(transactionCursor.getInt(1));
            String expenseType = transactionCursor.getString(2);
            double amount = transactionCursor.getDouble(3);
            Transaction t = null;
            try {
                t = new Transaction(formatter.parse(date), accountNo, ExpenseType.valueOf(expenseType),amount);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            transactionList.add(t);
        }
        return transactionList;
    }

    @Override
    public List<Transaction> getPaginatedTransactionLogs(int limit) {
        List<Transaction> transactionList = new ArrayList<>();
        Cursor transactionCursor = dbManager.fetchTransactionDataLimit(String.valueOf(limit));
        if (transactionCursor != null) {
            transactionCursor.moveToFirst();
        }
        while (transactionCursor.moveToNext()) {
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-YYYY");
            String date = transactionCursor.getString(0);
            String accountNo = String.valueOf(transactionCursor.getInt(1));
            String expenseType = transactionCursor.getString(2);
            double amount = transactionCursor.getDouble(3);
            Transaction t = null;
            try {
                t = new Transaction(formatter.parse(date), accountNo, ExpenseType.valueOf(expenseType),amount);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            transactionList.add(t);
        }
        return transactionList;
    }
}
