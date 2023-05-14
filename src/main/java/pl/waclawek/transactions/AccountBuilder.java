package pl.waclawek.transactions;

import org.openapi.quarkus.transactions_json.model.Account;

public class AccountBuilder {

    private String accountNumber;
    private Integer debitCount;
    private Integer creditCount;
    private Float balance;

    public AccountBuilder() {
    }

    public AccountBuilder setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
        return this;
    }

    public AccountBuilder setDebitCount(Integer debitCount) {
        this.debitCount = debitCount;
        return this;
    }

    public AccountBuilder setCreditCount(Integer creditCount) {
        this.creditCount = creditCount;
        return this;
    }

    public AccountBuilder setBalance(Float balance) {
        this.balance = balance;
        return this;
    }

    public Account build() {
        var account = new Account();
        account.setAccount(this.accountNumber);
        account.setDebitCount(this.debitCount);
        account.setCreditCount(this.creditCount);
        account.setBalance(this.balance);
        return account;
    }

}
