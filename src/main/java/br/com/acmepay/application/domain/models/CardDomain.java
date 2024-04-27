package br.com.acmepay.application.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardDomain {
    private Long id;
    private String flag;
    private BigDecimal card_limit;
    private AccountDomain card_account;

    public CardDomain create(){
        return this;
    }
}
