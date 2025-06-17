package com.example.transactions.analytics.controller;

import com.example.transactions.analytics.dto.TransactionRequest;
import com.example.transactions.analytics.model.Transaction;
import com.example.transactions.analytics.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;

@RestController
@RequestMapping("/transacao")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService){
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<Void> createTransaction(@RequestBody TransactionRequest request){
        OffsetDateTime now = OffsetDateTime.now();
        if (request.getDataHora().isAfter(now) || request.getValor() <= 0){
            return ResponseEntity.unprocessableEntity().build();
        }
        Transaction transaction = new Transaction(request.getValor(), request.getDataHora());
        transactionService.addTransaction(transaction);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping
    public ResponseEntity<Void> clearTransacions(){
        transactionService.clearTransaction();
        return ResponseEntity.ok().build();
    }

}
