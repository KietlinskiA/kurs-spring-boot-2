package pl.kietlinski.kursspringboot2;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.Schedules;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@Aspect
public class MainService {


    private List<Car> carList;

    private Logger logger;

    public MainService() {
        this.logger = LoggerFactory.getLogger("Global logger");
        this.carList = new ArrayList<>();
        init();
    }

    public void init() {
        carList.add(new Car("BMW", "X1"));
        carList.add(new Car("Audi", "A6"));
        carList.add(new Car("Citroen", "C5"));
    }

    public List<Car> getCarList() {
        return carList;
    }

    @Before("@annotation(BeforeCarAspect)")
    public void getBefore() {
        logger.info("Clearing car list...");
    }

    @Around("@annotation(AroundCarAspect)")
    public Object getAround(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info("Returning car list...");
        Object proceed = joinPoint.proceed();
        logger.info("Car list returned");
        return proceed;
    }

    @After("@annotation(AfterCarAspect)")
    public void getAfter() {
        logger.info("New car added");
    }

    @Schedules({
            @Scheduled(fixedDelay = 2000, initialDelay = 5000),
            @Scheduled(fixedRate = 5000, initialDelay = 5000)
    })
    public void prepareHalfBackup() {
        logger.info("Preparing half data backup "+new Random().nextGaussian());
    }

    @Scheduled(cron = "50 2 13 * * ?")
    public void prepareFullBackup() {
        logger.info("Preparing full data backup...");
    }

}
