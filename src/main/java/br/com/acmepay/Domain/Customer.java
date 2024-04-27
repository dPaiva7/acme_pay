package br.com.acmepay.Domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String document;
    private List<Account> accounts;

    public Customer create(){
        return this;
    }
}
