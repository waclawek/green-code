package pl.waclawek.transactions;

import org.openapi.quarkus.transactions_json.api.DefaultApi;
import org.openapi.quarkus.transactions_json.model.Account;
import org.openapi.quarkus.transactions_json.model.Transaction;

import java.util.List;

public class TransactionService implements DefaultApi {


    @Override
    public List<Account> report(List<Transaction> transaction) {
        var accountService = new AccountService();

        transaction.forEach(accountService::makeTransaction);

        return AccountService.getAccountList();
    }


}
