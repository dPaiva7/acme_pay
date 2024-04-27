package br.com.acmepay.Domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transactions {
    private Long id;
    private LocalDateTime data_transaction;
    private Integer source_account;
    private Integer destination_account;
    private BigDecimal ammout;
}
