package hu.elte.templateshop.webdomains.mappers;

import hu.elte.templateshop.domain.Product;
import hu.elte.templateshop.webdomains.ProductRequest;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public Product map(ProductRequest request) {
        Product product = new Product();
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setDescription(request.getDescription());
        product.setFileName(request.getFileName());
        return product;
    }
}
