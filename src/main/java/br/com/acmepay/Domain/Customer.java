package br.com.acmepay.Domain;

import br.com.acmepay.Exception.customerCreatedDuplicated;
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
public class Customer {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String document;
    //private List<Account> accounts;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    private List<Customer> customers = new ArrayList<>();

    public Customer create(Customer customer) throws customerCreatedDuplicated {
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

    public List<Customer> listCustomer()  {
        return this.customers;
    }

    public void delete(Customer customer) {
    }

    public void update(Customer customer) {
        this.name = customer.getName();
        this.email = customer.getEmail();
        this.phone = customer.getPhone();
        this.document = customer.getDocument();
        customer.setUpdated_at(LocalDateTime.now());
    }

    public Optional<Customer> getByCustomerDocument(String document) {
        return this.customers.stream().filter(c -> c.getDocument().equals(document)).findFirst();
    }

    public Boolean validCustomerCreated (Customer customer) {
        return this.customers.stream()
                .filter(c -> c.getEmail().equals(customer.email) && c.getDocument().equals(customer.document))
                .anyMatch(customer1 -> true);
    }

    public Boolean validDocumentCustomer (String document) {
        int length = document.length();
        return length == 11 || length == 20;
    }
}
