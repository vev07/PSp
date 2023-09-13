package de.telran.lesson3.controller;

//import de.telran.lesson3.domain_layer.entity.common.CommonProduct;
import de.telran.lesson3.domain_layer.entity.Product;
import de.telran.lesson3.domain_layer.entity.jpa.JpaProduct;
import de.telran.lesson3.exeption_layer.Response;
import de.telran.lesson3.exeption_layer.exeptions.EntityValidationException;
import de.telran.lesson3.exeption_layer.exeptions.FirstTestException;
import de.telran.lesson3.exeption_layer.exeptions.SecondTestException;
import de.telran.lesson3.exeption_layer.exeptions.ThirdTestException;
import de.telran.lesson3.service_layer.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController implements Controller {
    @Autowired
    private ProductService productService;
    @GetMapping
    public List<Product> getAll() {
        List<Product> products = productService.getAll();
        if (products.size() == 8)
            throw new ThirdTestException("List products is empty ");
        return products;

    }

    @GetMapping("/{id}")
    public Product getById(@PathVariable int id){
        return productService.getById(id);
    }

    @PostMapping
    public void add(@Valid @RequestBody JpaProduct product) {
        try {
            productService.add(product);
        } catch (Exception e) {
            throw new EntityValidationException(e.getMessage());
        }


    }
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id) {
        productService.deleteById(id);
    }
    @DeleteMapping("/delete_by_name/{name}")
    public void deleteByName(@PathVariable String name) {
        productService.deleteByName(name);
    }
    @GetMapping("/get_count")
    public int getCount() {
        return productService.getCount();
    }
    @GetMapping("/get_total_price")
    public double getTotalPrice() {
        return productService.getTotalPrice();
    }

  @GetMapping("/get_average_price")
  public double getAveragePrice() {
      return productService.getAveragePrice();
  }



}
