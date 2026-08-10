package com.northstar.crm.service;

import com.northstar.crm.account.Account;
import com.northstar.crm.account.AccountRepository;
import com.northstar.crm.account.TransactionLog;
import com.northstar.crm.account.TransactionLogRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class TransferService {
  private final AccountRepository accountRepository;
  private final TransactionLogRepository transactionLogRepository;

  public TransferService(AccountRepository accountRepository,
                         TransactionLogRepository transactionLogRepository) {
    this.accountRepository = accountRepository;
    this.transactionLogRepository = transactionLogRepository;
  }

  // TODO: add @Transactional on this method (service-layer boundary)
  @Transactional
  public void transfer(String fromAccountId, String toAccountId, BigDecimal amount) {
    Account from = accountRepository.findById(fromAccountId)
        .orElseThrow(() -> new IllegalArgumentException("Unknown from account"));
    // TODO: if toAccountId equals "ACC-FORCE-FAIL" → throw IllegalStateException to force rollback
    Account to = accountRepository.findById(toAccountId)
        .orElseThrow(() -> new IllegalArgumentException("Unknown to account"));
      if ("ACC-FORCE-FAIL".equals(toAccountId)) {
          throw new IllegalStateException("Forced failure for rollback");
        }

    // TODO: debit from, credit to, save both
    if (from.getBalance().compareTo(amount) < 0) {
      throw new IllegalArgumentException("Insufficient funds in from account");
    }

    from.setBalance(from.getBalance().subtract(amount));
    to.setBalance(to.getBalance().add(amount));
    accountRepository.save(from);
    accountRepository.save(to);

    // TODO: write TransactionLog row
    logTransaction(fromAccountId, toAccountId, amount);
  }

  private void logTransaction(String fromAccountId, String toAccountId, BigDecimal amount) {
    // Helper method for logging transaction
    var transactionLog = new TransactionLog();

    transactionLog.setFromAccountId(fromAccountId);
    transactionLog.setToAccountId(toAccountId);

    transactionLog.setAmount(amount);

    transactionLogRepository.save(transactionLog);
  }
}
