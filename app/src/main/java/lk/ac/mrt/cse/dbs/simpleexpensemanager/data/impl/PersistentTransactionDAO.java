package lk.ac.mrt.cse.dbs.simpleexpensemanager.data.impl;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lk.ac.mrt.cse.dbs.simpleexpensemanager.DatabaseManager;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.control.PersistentExpenseManager;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.TransactionDAO;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.model.Account;
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
        Cursor transactionCursor = dbManager.fetchAccountData();
        while (transactionCursor.moveToNext()) {
            String accNo = String.valueOf(transactionCursor.getInt(0));
            String bankName = transactionCursor.getString(1);
            String accHolder = transactionCursor.getString(2);
            double balance = transactionCursor.getDouble(3);
            Transaction t = new Transaction(accNo, bankName,accHolder,balance);
            transactionList.add(t);
        }
        return transactionList;
    }

    @Override
    public List<Transaction> getPaginatedTransactionLogs(int limit) {
        List<Transaction> transactionList = new ArrayList<>();
        Cursor transactionCursor = dbManager.fetchAccountData();
        while (transactionCursor.moveToNext()) {
            String accNo = String.valueOf(transactionCursor.getInt(0));
            String bankName = transactionCursor.getString(1);
            String accHolder = transactionCursor.getString(2);
            double balance = transactionCursor.getDouble(3);
            Transaction t = new Transaction(accNo, bankName,accHolder,balance);
            transactionList.add(t);
        }
        return transactionList;
    }
}
