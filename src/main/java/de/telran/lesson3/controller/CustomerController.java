package de.telran.lesson3.controller;
//
//import de.telran.lesson3.domain_layer.entity.common.CommonCustomer;
import de.telran.lesson3.domain_layer.entity.Customer;
import de.telran.lesson3.domain_layer.entity.jpa.JpaCustomer;
import de.telran.lesson3.exeption_layer.customerException.CustomerExceptionHandler;
import de.telran.lesson3.exeption_layer.customerException.MyException;
import de.telran.lesson3.exeption_layer.exceptionIAmTeapot.ExceptionIAmTeapot;
import de.telran.lesson3.exeption_layer.exeptions.EntityValidationException;
import de.telran.lesson3.exeption_layer.exeptions.ThirdTestException;
import de.telran.lesson3.service_layer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController implements Controller {
    @Autowired
    private CustomerService customerService;
    @GetMapping
    public List<Customer> getAll() {
        List<Customer> customers = customerService.getAll();
        if (customers.size() < 10)
            throw new ExceptionIAmTeapot("List customers is lower then 10 customers");
        return customers;
    }
    @GetMapping("/{id}")
    public Customer getById(@PathVariable int id) {
        Customer customer = customerService.getById(id);
        if (customer == null)
            throw new MyException("Customer with id -" + id + " doesn't exist");
        return customer;
    }

    @PostMapping
    public void add(@RequestBody JpaCustomer customer) {
        try {
            customerService.add(customer);
        }catch (Exception e) {
            throw new EntityValidationException(e.getMessage());
        }

    }
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id) {
        try {
        customerService.deleteById(id);
        }catch (Exception e) {
            throw new EntityValidationException(e.getMessage());
        }
    }
    @DeleteMapping("/delete_by_name/{name}")
    public void deleteByName(@PathVariable String name) {
        try {
        customerService.deleteByName(name);
        }catch (Exception e) {
            throw new EntityValidationException(e.getMessage());
        }
    }
    @GetMapping("/get_count")
    public int getCount() {
        return customerService.getCount();
    }
    @GetMapping("/get_total_price_by_id/{id}")
    public double getTotalPriceById(@PathVariable int id) {
        return customerService.getTotalPriceById(id);
    }
    @GetMapping("/get_average_price_by_id/{id}")
    public double getAveragePriceById(@PathVariable int id) {
        return customerService.getAveragePriceById(id);
    }
    @PatchMapping("/add_to_cart_by_id/{customerId}/{productId}")
    public void addToCartById(@PathVariable int customerId, @PathVariable int productId) {
        try {
      customerService.addToCartById(customerId,productId);
    }catch (Exception e) {
        throw new EntityValidationException(e.getMessage());
    }
    }
    @DeleteMapping("/delete_from_cart/{customerId}/{productId}")
    public void deleteFromCart(@PathVariable int customerId, @PathVariable int productId) {
        customerService.deleteFromCart(customerId, productId);
    }
    @DeleteMapping("/clear_cart/{customerId}")
    public void clearCart(@PathVariable int customerId) {
        customerService.clearCart(customerId);
    }

}
