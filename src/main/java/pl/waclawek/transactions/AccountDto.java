package pl.waclawek.transactions;

import java.math.BigDecimal;

public class AccountDto {

    private String accountNumber;
    private int debitCount;
    private int creditCount;
    private BigDecimal balance;

    public AccountDto(String accountNumber, int debitCount, int creditCount, BigDecimal balance) {
        this.accountNumber = accountNumber;
        this.debitCount = debitCount;
        this.creditCount = creditCount;
        this.balance = balance;
    }

    public AccountDto() {

    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getDebitCount() {
        return debitCount;
    }

    public void setDebitCount(int debitCount) {
        this.debitCount = debitCount;
    }

    public int getCreditCount() {
        return creditCount;
    }

    public void setCreditCount(int creditCount) {
        this.creditCount = creditCount;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
