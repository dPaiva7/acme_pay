package br.com.acmepay.Domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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
    private BigDecimal createAcount;

    public Account create() {
        return this;
    }
}
