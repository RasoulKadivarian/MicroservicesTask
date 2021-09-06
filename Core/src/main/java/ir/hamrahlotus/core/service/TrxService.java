package ir.hamrahlotus.core.service;

import ir.hamrahlotus.core.model.Transaction;

import java.util.List;

public interface TrxService {

    Transaction buy(Long userId, Long amount);

    Transaction charge(Long userId, Long amount);

    List<Transaction> all();
}
