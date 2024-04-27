package br.com.acmepay.Domain;

import br.com.acmepay.Exception.BalanceToWithdrawException;
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
public class Account {
    private Long id;
    private Integer number;
    private Integer agency;
    private BigDecimal balance;
    private Customer customer;
    private List<Cards> cards;
    private LocalDateTime created_at;
    private LocalDateTime update_at;
    private Boolean closed;

    private List<Account> createAcount = new ArrayList<>();

    public Account create(Account  accout) {
        this.setId(1L);
        this.setNumber(accout.number);
        this.setAgency(accout.agency);
        this.setBalance(BigDecimal.ZERO);
        this.setCards(new ArrayList<>());
        this.setCustomer(null);
        this.setCreated_at(LocalDateTime.now());
        this.setUpdate_at(null);
        this.setClosed(Boolean.FALSE);
        this.createAcount.add(this);

        return accout;
    }

    public void deposit(BigDecimal amount) {
        this.balance.add(amount);
    }

    public void withdraw(BigDecimal amount)  throws BalanceToWithdrawException {
        if (this.balance.compareTo(amount) >= 0){
            this.balance.subtract(amount);
        } else {
            throw new BalanceToWithdrawException("Transaction failed");
        }

    }

    public void transfer(BigDecimal amount, Account accountDest) throws BalanceToWithdrawException {
        if (this.balance.compareTo(amount) >= 0){
            this.withdraw(amount);
            accountDest.deposit(amount);
        } else {
            throw new BalanceToWithdrawException("Transaction failed");
        }
    }

}
