package de.telran.lesson3.schedule_layer;

import de.telran.lesson3.domain_layer.entity.jpa.Task;
import de.telran.lesson3.repository_layer.jpa.JpaTaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.DefaultManagedTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.time.Instant;

@EnableScheduling
@Component
@EnableAsync
public class ScheduleExecutor {
    @Autowired
    private JpaTaskRepository repository;
    private static Logger logger = LoggerFactory.getLogger(ScheduleExecutor.class);
//    @Scheduled(fixedDelay = 5000)
//    public void fixedDelayTask() {
//        Task task = new Task("fixed delay task");
//        logger.info(task.getDescription());
//        repository.save(task);
//    }
//    @Scheduled(fixedDelay = 5000)
//    public void fixedDelayTask() {
//        Task task = new Task("fixed delay task");
//        logger.info(task.getDescription());
//        repository.save(task);
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//    }
//    @Scheduled(fixedRate = 5000)
//    public void fixedRateTask() {
//        Task task = new Task("fixed rate task");
//        logger.info(task.getDescription());
//        repository.save(task);
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//    }
//    @Scheduled(fixedRate = 8000)
//    public void fixedRateTask() {
//        Task task = new Task("fixed rate task");
//        logger.info(task.getDescription());
//        repository.save(task);
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//    }
//@Scheduled(fixedRate = 5000)
//@Async
//public void fixedRateTask() {
//    Task task = new Task("fixed rate task");
//    logger.info(task.getDescription());
//    repository.save(task);
//    try {
//        Thread.sleep(8000);
//    } catch (InterruptedException e) {
//        throw new RuntimeException(e);
//    }
//}
//@Scheduled(fixedRate = 5000, initialDelay = 15000)
//@Async
//public void initialDelayTask() {
//    Task task = new Task("initial delay task");
//    logger.info(task.getDescription());
//    repository.save(task);
//    try {
//        Thread.sleep(8000);
//    } catch (InterruptedException e) {
//        throw new RuntimeException(e);
//    }
//}
//    @Scheduled(fixedDelayString = "PT07S") // "PT02H" = 2 hour or 7200000 sec
//    public void anotherDelayFormatTask() {
//        Task task = new Task("another Delay Format Task");
//        logger.info(task.getDescription());
//        repository.save(task);
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//    }
//@Scheduled(fixedDelayString = "${interval}")
//public void delayInPropertyTask() {
//    Task task = new Task("delay In Property Task");
//    logger.info(task.getDescription());
//    repository.save(task);
//    try {
//        Thread.sleep(2000);
//    } catch (InterruptedException e) {
//        throw new RuntimeException(e);
//    }
//}
    // 0 15 9-17 * * MON-FRI task is doing every 15 minute from mon-fri
//@Scheduled(cron = "${cron-interval}")
//public void cronIntervalTask() {
//    Task task = new Task("cron Interval Task");
//    logger.info(task.getDescription());
//    repository.save(task);
//    try {
//        Thread.sleep(2000);
//    } catch (InterruptedException e) {
//        throw new RuntimeException(e);
//    }
//}
public static void taskSchedulerTaskWithTrigger(Task task) {
    TaskScheduler scheduler = new DefaultManagedTaskScheduler();
    scheduler.schedule(() -> logger.info(task.getDescription()),
            new CronTrigger("0,10,20,30,40,50 * * * * *"));
}
public static void taskSchedulerTaskWithInstant(Task task) {

    TaskScheduler scheduler = new DefaultManagedTaskScheduler();
    Instant instant = Instant.now().plusSeconds(20);
    scheduler.schedule(() -> logger.info(task.getDescription()),
            instant);
}
}
/*
1. Реализовать вывод в консоль каждые 30 секунд списка последних пяти выполненных задач.
   Время выполнения предыдущей задачи не должно влиять на старт следующей.
2. Реализовать вывод в консоль последнего добавленного в БД товара.
   Вывод должен производиться в 15 и 45 секунд каждой минуты.
   Задача должна быть сохранена в БД.
3. После запроса конкретного продукта через 15 секунд отправить персональное предложение
   на этот продукт с ценой на 5-10% (рандомно) ниже, чем базовая цена.
   Имитировать отправку в виде вывода в консоль и логирования.
   Данная задача должна выполняться при помощи АОП и сохраняться в БД.
4. После того как покупатель очистил корзину, через 20 секунд выбрать из корзины случайный товар,
   который там был, сделать на него скидку 15% и предложить покупателю всё-таки
   приобрести все эти товары, вывести все товары (один с новой ценой), а также старую и новую стоимость корзины.
   Данная задача должна выполняться при помощи АОП и сохраняться в БД.
 */
