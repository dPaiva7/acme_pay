package br.com.acmepay.application.domain.models;

import br.com.acmepay.application.domain.exception.BalanceToWithdrawException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDomain {
    private Long id;
    private Integer number;
    private Integer agency;
    private BigDecimal balance;
    private CustomerDomain customer;
    private List<CardDomain> cards;
    private LocalDateTime created_at;
    private LocalDateTime update_at;
    private Boolean closed;

    private List<String> transactions = new ArrayList<>();

    public AccountDomain create(AccountDomain account) {
        this.setId(1L);
        this.setNumber(null);
        this.setAgency(null);
        this.setBalance(BigDecimal.ZERO);
        this.setCards(new ArrayList<>());
        this.setCustomer(null);
        this.setCreated_at(LocalDateTime.now());
        this.setUpdate_at(null);
        this.setClosed(Boolean.FALSE);
        this.transactions.add("Account created sucessfully" + LocalDateTime.now().toString());

        return account;
    }

    public void deposit(BigDecimal amount) {
        this.balance = this.balance.add(amount);
        this.transactions.add("Deposit successful" + LocalDateTime.now() + " " + amount.toString() + "amount deposit from account");
    }

    public void withdraw(BigDecimal amount)  throws BalanceToWithdrawException {
        if (checkBalanceAuthorized(amount)){
            this.balance = this.balance.subtract(amount);
            this.transactions.add("Withdraw successful" + LocalDateTime.now() + " " + amount.toString() + "amount withdrawn from account");
        } else {
            throw new BalanceToWithdrawException("Transaction failed");
        }
    }

    public void transfer(BigDecimal amount, AccountDomain accountDest) throws BalanceToWithdrawException {
        this.withdraw(amount);
        accountDest.deposit(amount);
    }

    public List<String> accountStatement(AccountDomain accout) {
        return this.transactions;
    }

    private boolean checkBalanceAuthorized(BigDecimal amount) {
        return this.balance.compareTo(amount) >= 0;
    }
}
