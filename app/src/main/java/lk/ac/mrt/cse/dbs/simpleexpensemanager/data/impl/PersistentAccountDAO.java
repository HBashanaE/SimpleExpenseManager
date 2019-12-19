package lk.ac.mrt.cse.dbs.simpleexpensemanager.data.impl;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lk.ac.mrt.cse.dbs.simpleexpensemanager.DatabaseManager;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.AccountDAO;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.exception.InvalidAccountException;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.model.Account;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.model.ExpenseType;

public class PersistentAccountDAO implements AccountDAO {
    private DatabaseManager dbManager ;
    private Context context;

    public PersistentAccountDAO(Context c) {
        this.context = c;
        dbManager = new DatabaseManager(c);
    }

    @Override
    public List<String> getAccountNumbersList() {
        List<String> accountNumbers = new ArrayList<>();
        Cursor accountCursor = dbManager.fetchAccountData();
        while (accountCursor.moveToNext()) {
            String accNo = String.valueOf(accountCursor.getInt(0));
            accountNumbers.add(accNo);
        }
        return accountNumbers;
    }

    @Override
    public List<Account> getAccountsList() {
        List<Account> accountList = new ArrayList<>();
        Cursor accountCursor = dbManager.fetchAccountData();
        if (accountCursor != null) {
            accountCursor.moveToFirst();
        }
        while (accountCursor.moveToNext()) {
            String accNo = String.valueOf(accountCursor.getInt(0));
            String bankName = accountCursor.getString(1);
            String accHolder = accountCursor.getString(2);
            double balance = accountCursor.getDouble(3);
            Account a = new Account(accNo, bankName,accHolder,balance);
            accountList.add(a);
        }
        return accountList;
    }

    @Override
    public Account getAccount(String accountNo) throws InvalidAccountException {
        Cursor accountCursor = dbManager.fetchAccountDataByAccNo(accountNo);
        if (accountCursor != null) {
            accountCursor.moveToFirst();
        }
        String accNo = String.valueOf(accountCursor.getInt(0));
        String bankName = accountCursor.getString(1);
        String accHolder = accountCursor.getString(2);
        double balance = accountCursor.getDouble(3);
        Account a = new Account(accNo, bankName,accHolder,balance);
        return a;
    }

    @Override
    public void addAccount(Account account) {
        dbManager.insertAccountData(account.getAccountNo(), account.getBankName(), account.getAccountHolderName(), account.getBalance());
    }

    @Override
    public void removeAccount(String accountNo) throws InvalidAccountException {

    }

    @Override
    public void updateBalance(String accountNo, ExpenseType expenseType, double amount) throws InvalidAccountException {

    }
}
