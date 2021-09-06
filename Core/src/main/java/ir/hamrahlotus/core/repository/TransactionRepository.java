package ir.hamrahlotus.core.repository;

import ir.hamrahlotus.core.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
