package com.data.big.mq;


import com.data.big.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Sender {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void sendDirectQueue() {
        User user = new User();
        user.setUserId("123456");
        user.setName("张三");
        log.info("【sendDirectQueue已发送消息】");
        // 第一个参数是指要发送到哪个队列里面， 第二个参数是指要发送的内容
        this.amqpTemplate.convertAndSend(RabbitMQConfig.QUEUE, user);
    }
    public void sendTopic() {
        User user1 = new User();
        user1.setUserId("123456");
        user1.setName("张三");

        User user2 = new User();
        user2.setUserId("456789");
        user2.setName("李四");

        log.info("【sendTopic已发送消息】");
        // 第一个参数：TopicExchange名字
        // 第二个参数：Route-Key
        // 第三个参数：要发送的内容
        this.amqpTemplate.convertAndSend(RabbitMQConfig.TOPIC_EXCHANGE, "topic.message", user1 );
        this.amqpTemplate.convertAndSend(RabbitMQConfig.TOPIC_EXCHANGE, "topic.topic", user2);
    }

    public void sendFanout() {
        User user = new User();
        user.setUserId("123456");
        user.setName("张三");
        log.info("【sendFanout已发送消息】");
        // 注意， 这里的第2个参数为空。
        // 因为fanout 交换器不处理路由键，只是简单的将队列绑定到交换器上，
        // 每个发送到交换器的消息都会被转发到与该交换器绑定的所有队列上
        this.amqpTemplate.convertAndSend(RabbitMQConfig.FANOUT_EXCHANGE, "", user);
    }

}