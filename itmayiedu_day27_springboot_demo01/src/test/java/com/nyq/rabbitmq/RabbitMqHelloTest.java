package com.nyq.rabbitmq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import com.nyq.rabbitmq.simpl.HelloSender;

@RunWith(SpringRunner.class)
@SpringBootTest
@ComponentScan({"com"})
public class RabbitMqHelloTest {
	@Autowired
    private HelloSender helloSender;

    @Test
    public void hello() throws Exception {
        helloSender.send();
    }
}
