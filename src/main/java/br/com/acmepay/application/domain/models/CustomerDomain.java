package br.com.acmepay.application.domain.models;

import br.com.acmepay.application.domain.exception.customerCreatedDuplicated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDomain {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String document;
    //private List<Account> accounts;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    private List<CustomerDomain> customers = new ArrayList<>();

    public CustomerDomain create(CustomerDomain customer) throws customerCreatedDuplicated {
        if (validCustomerCreated(customer)) {
            throw new customerCreatedDuplicated("Already registered customer");
        }
            if (validDocumentCustomer(customer.document)) {
                this.setId(1L);
                this.setName(customer.name);
                this.setEmail(customer.email);
                this.setPhone(customer.phone);
                this.setDocument(customer.document);
                this.setCreated_at(LocalDateTime.now());
                this.setUpdated_at(null);
                this.customers.add(customer);
            }
        return this;
    }

    public List<CustomerDomain> listCustomer()  {
        return this.customers;
    }

    public void delete(CustomerDomain customer) {
    }

    public void update(CustomerDomain customer) {
        this.name = customer.getName();
        this.email = customer.getEmail();
        this.phone = customer.getPhone();
        this.document = customer.getDocument();
        customer.setUpdated_at(LocalDateTime.now());
    }

    public Optional<CustomerDomain> getByCustomerDocument(String document) {
        return this.customers.stream().filter(c -> c.getDocument().equals(document)).findFirst();
    }

    public Boolean validCustomerCreated (CustomerDomain customer) {
        return this.customers.stream()
                .filter(c -> c.getEmail().equals(customer.email) && c.getDocument().equals(customer.document))
                .anyMatch(customer1 -> true);
    }

    public Boolean validDocumentCustomer (String document) {
        int length = document.length();
        return length == 11 || length == 20;
    }
}
