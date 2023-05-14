package pl.waclawek.transactions;

import org.openapi.quarkus.transactions_json.model.Account;
import org.openapi.quarkus.transactions_json.model.Transaction;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;

public class AccountService {
    static HashMap<String, AccountDto> accountMap;

    public AccountService() {
        this.accountMap = new HashMap<>();
    }

    public HashMap<String, AccountDto> getAccountMap() {
        return accountMap;
    }

    static Comparator<Account> accountComparator = Comparator.comparing(a -> new BigInteger(a.getAccount()));

    public static List<Account> getAccountList() {
        var list = Collections.list(Collections.enumeration(accountMap.values()));

        return list.stream().map(mapToExternal()).sorted(accountComparator).toList();
    }

    public void makeTransaction(Transaction transaction) {
        checkAccountsExistance(transaction);
        subtractFromAccount(transaction.getDebitAccount(), transaction.getAmount());
        addToAccount(transaction.getCreditAccount(), transaction.getAmount());
    }

    private void addToAccount(String creditAccount, Float amount) {
        var account = accountMap.get(creditAccount);
        var balance = account.getBalance().add(BigDecimal.valueOf(amount));
        account.setBalance(balance);
        account.setCreditCount(account.getCreditCount() + 1);
    }

    private static void subtractFromAccount(String debitAccount, Float amount) {
        var account = accountMap.get(debitAccount);
        var balance = account.getBalance().subtract(BigDecimal.valueOf(amount));
        account.setBalance(balance);
        account.setDebitCount(account.getDebitCount() + 1);
    }

    private void checkAccountsExistance(Transaction transaction) {
        if (accountMap.get(transaction.getCreditAccount()) == null) {
            createNewAccount(transaction.getCreditAccount());
        }
        if (accountMap.get(transaction.getDebitAccount()) == null) {
            createNewAccount(transaction.getDebitAccount());
        }
    }

    private void createNewAccount(String accountNumber) {
        var newAccount = new AccountDto();
        newAccount.setAccountNumber(accountNumber);
        newAccount.setCreditCount(0);
        newAccount.setDebitCount(0);
        newAccount.setBalance(BigDecimal.ZERO);
        accountMap.put(accountNumber, newAccount);
    }

    static Function<AccountDto, Account> mapToExternal() {
        return accountDto -> new AccountBuilder()
                .setAccountNumber(accountDto.getAccountNumber())
                .setCreditCount(accountDto.getCreditCount())
                .setDebitCount(accountDto.getDebitCount())
                .setBalance(accountDto.getBalance().setScale(2, RoundingMode.HALF_EVEN).floatValue())
                .build();
    }
}
