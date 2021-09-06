package ir.hamrahlotus.core.controller;

import ir.hamrahlotus.core.model.Transaction;
import ir.hamrahlotus.core.service.TrxService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/core/trx")
public class TrxController {

    private final TrxService trxService;

    @PostMapping("/charge")
    public ResponseEntity<Transaction> chargeWallet(@RequestParam("userid") Long userId, @RequestParam("amount") Long amount) throws Exception {
        Transaction charge = trxService.charge(userId, amount);
        return ResponseEntity.ok(charge);
    }

    @PostMapping("/buy")
    public ResponseEntity<?> buySomeThing(@RequestParam("userid") Long userId, @RequestParam("amount") Long amount) throws Exception {
        Transaction buy = trxService.buy(userId, amount);
        return ResponseEntity.ok(buy);
    }

    @GetMapping
    public ResponseEntity<List<Transaction>> all() {
        return ResponseEntity.ok(trxService.all());
    }
}
