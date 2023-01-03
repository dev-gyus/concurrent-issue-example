package com.example.stock.facade;

import com.example.stock.service.OptimisticLockStockService;
import org.springframework.stereotype.Service;

@Service
public class OptimisticLockStockFacade {
    private OptimisticLockStockService service;

    public OptimisticLockStockFacade(OptimisticLockStockService service) {
        this.service = service;
    }

    public void decrease(Long id, Long quantity) throws InterruptedException {
        while(true) {
            try {
                service.decrease(id, quantity);
                break;
            } catch (Exception e) {
                Thread.sleep(50);
            }
        }
    }
}
