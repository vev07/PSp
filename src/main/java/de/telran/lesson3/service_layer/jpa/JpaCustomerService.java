package de.telran.lesson3.service_layer.jpa;

import de.telran.lesson3.domain_layer.entity.Cart;
import de.telran.lesson3.domain_layer.entity.Customer;
import de.telran.lesson3.domain_layer.entity.Product;
import de.telran.lesson3.domain_layer.entity.jpa.JpaCart;
import de.telran.lesson3.domain_layer.entity.jpa.JpaCustomer;
import de.telran.lesson3.repository_layer.jpa.JpaCustomerRepository;
import de.telran.lesson3.repository_layer.jpa.JpaProductRepository;
import de.telran.lesson3.service_layer.CustomerService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JpaCustomerService implements CustomerService {

    @Autowired
    private JpaCustomerRepository jpaCustomerRepository;
    @Autowired
    private JpaProductRepository jpaProductRepository;

    @Override
    public List<Customer> getAll() {
        return new ArrayList<>(jpaCustomerRepository.findAll());
    }

    @Override
    public Customer getById(int id) {
        return jpaCustomerRepository.findById(id).orElse(null);
    }

    @Override
    public void add(Customer customer) {
        jpaCustomerRepository.save(new JpaCustomer(0,customer.getName(), customer.getAge(),customer.getEmail(), (JpaCart) customer.getCart()));
    }

    @Override
    public void deleteById(int id) {
        jpaCustomerRepository.deleteById(id);
    }

    @Override
    public void deleteByName(String name) {
        jpaCustomerRepository.deleteByName(name);
    }

    @Override
    public int getCount() {
        return (int) jpaCustomerRepository.count();
    }

    @Override
    public double getTotalPriceById(int id) {
        return getById(id).getCart().getTotalPrice();
    }

    @Override
    public double getAveragePriceById(int id) {
        int countProducts = getById(id).getCart().getProducts().size();
        return getTotalPriceById(id) / countProducts;
    }

    @Transactional
    @Override
    public void addToCartById(int customerId, int productId) {

        Customer customer = jpaCustomerRepository.findById(customerId).orElse(null);
        Product product = jpaProductRepository.findById(productId).orElse(null);

        if (customer != null && product != null) {
            Cart cart = customer.getCart();
            cart.addProduct(product);
        }
    }

    @Transactional
    @Override
    public void deleteFromCart(int customerId, int productId) {

       ((JpaCart) getById(customerId).getCart()).removeProduct(jpaProductRepository.findById(productId).orElse(null));
    }

    @Transactional
    @Override
    public void clearCart(int customerId) {

        ((JpaCart) getById(customerId).getCart()).clear();
    }
}
