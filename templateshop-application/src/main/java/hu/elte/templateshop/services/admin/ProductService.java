package hu.elte.templateshop.services.admin;

import hu.elte.templateshop.domain.Product;
import hu.elte.templateshop.exceptions.BusinessException;
import hu.elte.templateshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.transaction.Transactional;

@Service
public class ProductService {
    private final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);
    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public void saveProduct(Product product) {
        productRepository.save(product);
        LOGGER.info("Product with name " + product.getName() + " saved.");
    }

    public Iterable<Product> getProducts() {
       return productRepository.findAll();
    }

    public void deleteById(int id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
        }
        else {
            throw new BusinessException("The given product does not exist.");
        }
    }
}
