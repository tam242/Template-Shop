package hu.elte.templateshop.webdomains;

import hu.elte.templateshop.webdomains.annotations.FilePathValid;

import javax.validation.constraints.*;
import java.math.BigDecimal;

public class ProductRequest {

    @NotEmpty(message = "Product name is required")
    private String name;
    @DecimalMin(value = "0.0")
    @DecimalMax(value = "10000.0")
    @Digits(integer=5, fraction=2)
    private BigDecimal price;
    @Size(max = 200, message = "Size max 200 words")
    private String description;
    @NotEmpty(message = "Template folder path is required")
    @FilePathValid
    private String fileName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
