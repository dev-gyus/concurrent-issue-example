package com.example.stock.service;

import com.example.stock.domain.Stock;
import com.example.stock.repository.StockRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StockService {
    private final StockRepository repository;

    public StockService(StockRepository repository) {
        this.repository = repository;
    }

    // synchronized는 한번에 하나의 쓰레드에서만 메소드를 점유 할 수있음
    // 하지만 하나의 프로세스에서만 이를 보장하므로, 분산 시스템에서 동일 데이터에 대한
    // RaceCondition이 발생할 수 있음
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public synchronized void decrease(Long id, Long quantity) {
        // 재고 조회
        Stock stock = repository.findById(id).orElseThrow();
        // 재고 감소
        stock.decrease(quantity);
        // 갱신된 재고 저장
    }
}
