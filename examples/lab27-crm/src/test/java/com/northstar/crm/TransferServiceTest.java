package com.northstar.crm;

import com.northstar.crm.account.AccountRepository;
import com.northstar.crm.service.TransferService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TransferServiceTest {
  @Autowired TransferService transferService;
  @Autowired AccountRepository accounts;

  @BeforeEach
  void resetAccounts() {
    accounts.findById("ACC-MAIN-1001").ifPresent(a -> {
      a.setBalance(new BigDecimal("100.00"));
      accounts.save(a);
    });
  }

  @Test
  void forceFailRollsBack() {
    BigDecimal before = accounts.findById("ACC-MAIN-1001").orElseThrow().getBalance();
    assertThrows(Exception.class, () ->
        transferService.transfer("ACC-MAIN-1001", "ACC-FORCE-FAIL", new BigDecimal("10.00")));
    // TODO: assert MAIN balance equals before after rollback (passes once @Transactional works)
    assertEquals(before, accounts.findById("ACC-MAIN-1001").orElseThrow().getBalance());
  }

  @Test
  void happyPathMovesFunds() {
    // transfer 5.00 from MAIN to LOYALTY

    // get expected balances after transfer
    BigDecimal targetMain = accounts.findById("ACC-MAIN-1001").orElseThrow().getBalance().subtract(new BigDecimal("5.00"));
    BigDecimal targetLoyalty = accounts.findById("ACC-LOYALTY-1001").orElseThrow().getBalance().add(new BigDecimal("5.00"));

    // transfer
    transferService.transfer("ACC-MAIN-1001", "ACC-LOYALTY-1001", new BigDecimal("5.00"));

    // assert balances after transfer
    assertEquals(targetMain, accounts.findAll().stream().filter(a -> a.getId().equals("ACC-MAIN-1001")).findFirst().orElseThrow().getBalance());
    assertEquals(targetLoyalty, accounts.findAll().stream().filter(a -> a.getId().equals("ACC-LOYALTY-1001")).findFirst().orElseThrow().getBalance());
  }
}
