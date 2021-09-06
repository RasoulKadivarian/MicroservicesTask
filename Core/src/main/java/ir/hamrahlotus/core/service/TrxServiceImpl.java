package ir.hamrahlotus.core.service;

import ir.hamrahlotus.core.model.Transaction;
import ir.hamrahlotus.core.model.Type;
import ir.hamrahlotus.core.model.User;
import ir.hamrahlotus.core.model.Wallet;
import ir.hamrahlotus.core.repository.TransactionRepository;
import ir.hamrahlotus.core.repository.UserRepository;
import ir.hamrahlotus.core.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TrxServiceImpl implements TrxService {

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;
    private final WalletRepository walletRepository;
    private final Transaction transaction;

    @Transactional
    @Override
    public Transaction buy(Long userId, Long amount) {
        Optional<User> founded = userRepository.findById(userId);
        Wallet wallet = founded.get().getWallet();
        if (wallet.getBalance() >= amount) {
            wallet.decrementWalletBalance(amount);
            walletRepository.save(wallet);
        } else {
            throw new RuntimeException("wallet balance is less than entered amount!");
        }
        return transactionRepository.save(transaction.builder().userId(userId).amount(amount).type(Type.BUY).build());
    }

    @Transactional
    @Override
    public Transaction charge(Long userId, Long amount) {
        Optional<User> founded = userRepository.findById(userId);
        Wallet wallet = founded.get().getWallet();
        wallet.incrementWalletBalance(amount);
        walletRepository.save(wallet);
        return transactionRepository.save(transaction.builder().userId(userId).amount(amount).type(Type.CHARGE).build());
    }

    @Transactional(readOnly = true)
    @Override
    public List<Transaction> all() {
        return transactionRepository.findAll();
    }
}
