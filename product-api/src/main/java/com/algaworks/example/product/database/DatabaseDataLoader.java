package com.algaworks.example.product.database;

import com.algaworks.example.product.domain.Product;
import com.algaworks.example.product.domain.ProductRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class DatabaseDataLoader implements ApplicationRunner {

    private final ProductRepository productRepository;

    public DatabaseDataLoader(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (isDatabaseEmpty()) {
            loadDefaultData();
        }
    }

    private void loadDefaultData() {
        var products = List.of(
                new Product("Computador Gamer RZ3600", new BigDecimal("2999.00")),
                new Product("Monitor 22p", new BigDecimal("1500.00")),
                new Product("Microfone FT342", new BigDecimal("300.00")),
                new Product("Notebook AW Office RV342", new BigDecimal("4370.00")),
                new Product("Nobreak PowerTec", new BigDecimal("498.00"))
        );
        productRepository.saveAll(products);
    }

    private boolean isDatabaseEmpty() {
        return productRepository.count() == 0;
    }
}
