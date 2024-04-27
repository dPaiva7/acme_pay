package br.com.acmepay.Domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cards {
    private Long id;
    private String flag;
    private BigDecimal card_limit;
    private Account card_account;

    public Cards create(){
        return this;
    }
}
