package ir.hamrahlotus.core.repository;

import ir.hamrahlotus.core.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet, Long> {

    /*
    I tried used these methods but not worked and throw exception
    @Query("update Wallet set balance=balance + ?2 where id=?1")
    void incrementWalletBalance(Long id, Long amount);

    @Query("update Wallet set balance=balance - ?2 where id=?1")
    void decrementWalletBalance(Long id, Long amount);*/
}
