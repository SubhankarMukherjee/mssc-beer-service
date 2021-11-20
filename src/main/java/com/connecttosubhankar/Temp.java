package com.connecttosubhankar;

import org.springframework.scheduling.annotation.Scheduled;

//@Service
public class Temp {

    @Scheduled(fixedRate = 2000)
    public void test()
    {
        System.out.println("Hello from schduler");
    }
}
