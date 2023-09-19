package de.telran.lesson3.service_layer.jpa;

import de.telran.lesson3.domain_layer.entity.Product;
import de.telran.lesson3.domain_layer.entity.jpa.JpaProduct;
import de.telran.lesson3.domain_layer.entity.jpa.Task;
import de.telran.lesson3.repository_layer.jpa.JpaProductRepository;
import de.telran.lesson3.repository_layer.jpa.JpaTaskRepository;
import de.telran.lesson3.schedule_layer.ScheduleExecutor;
import de.telran.lesson3.service_layer.ProductService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JpaProductService implements ProductService {
    private static final Logger LOGGER = LogManager.getLogger(JpaProductService.class);
    @Autowired
    private JpaProductRepository repository;

    @Autowired
    private JpaTaskRepository taskRepository;

    @Override
    public List<Product> getAll() {
//        Task task = new Task("Task scheduled after getting all products");
      //  Task task2 = new Task("Task scheduled for single executing after getting all products");

//        ScheduleExecutor.taskSchedulerTaskWithTrigger(task);
      //  ScheduleExecutor.taskSchedulerTaskWithInstant(task2);
        return new ArrayList<>(repository.findAll());

    }

    @Override
    public Product getById(int id) {
//        LOGGER.log(Level.INFO, String.format("info product requested with id - %d.", id));
//        LOGGER.log(Level.WARN, String.format("info product requested with id - %d.", id));
//        LOGGER.log(Level.ERROR, String.format("info product requested with id - %d.", id));
//        LOGGER.info(String.format("info product requested with id - %d.", id));
//        LOGGER.warn(String.format("warn product requested with id - %d.", id));
//        LOGGER.error(String.format("error product requested with id - %d.", id));
        return repository.findById(id).orElse(null);
    }

    @Override
    public void add(Product product) {
        repository.save(new JpaProduct(0, product.getName(), product.getPrice()));
    }

    @Override
    public void deleteById(int id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteByName(String name) {
        repository.deleteByName(name);
    }

    @Override
    public int getCount() {
        return (int)repository.count();
    }

    @Override
    public double getTotalPrice() {
        return repository.getTotalPrice();
    }

    @Override
    public double getAveragePrice() {
        return getTotalPrice() / getCount();
    }

}
