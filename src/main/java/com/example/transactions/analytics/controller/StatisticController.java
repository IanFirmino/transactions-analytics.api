package com.example.transactions.analytics.controller;

import com.example.transactions.analytics.dto.StatisticResponse;
import com.example.transactions.analytics.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.DoubleSummaryStatistics;

@RestController
@RequestMapping("/estatistica")
public class StatisticController {

    private final TransactionService transactionService;
    public StatisticController(TransactionService transactionService){
        this.transactionService = transactionService;
    }

    @GetMapping
    public ResponseEntity<StatisticResponse> getStatistics(){
        DoubleSummaryStatistics stats = transactionService.getStatistics();
        StatisticResponse responseStats = new StatisticResponse(stats);
        return ResponseEntity.ok(responseStats);
    }

}
