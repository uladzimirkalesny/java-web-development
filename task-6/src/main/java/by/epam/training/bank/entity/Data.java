package by.epam.training.bank.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@lombok.Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Data {
    private List<User> users;
    private List<Credit> credits;
    private List<Discount> discounts;
    private List<Event> events;
    private List<Transaction> transactions;
}
