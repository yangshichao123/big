package com.data.big.queue;

import com.data.big.service.Service;
import com.data.big.util.Properties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 定义装苹果的篮子
 */
@Component
public  class TaskQueue {

    @Autowired
    private Service service;

    // 篮子，能够容纳3个苹果
    private final static BlockingQueue<String> basket = new ArrayBlockingQueue<>(1000);



    public TaskQueue() {
        this.init();
    }

    // 生产苹果，放入篮子
    public void produce(String ms) throws InterruptedException{
        // put方法放入一个苹果，若basket满了，等到basket有位置
        basket.put(ms);
    }
    // 消费苹果，从篮子中取走
    public String consume() throws InterruptedException{
        // get方法取出一个苹果，若basket为空，等到basket有苹果为止
        String apple = basket.take();
        return apple;
    }

    public int getAppleNumber(){
        return basket.size();
    }
    private  void init(){
        new Thread(()-> {
           while (true){
               try {
                   String consume = this.consume();
                   String lfSaveAddress = Properties.getLfSaveAddress();
                   lfSaveAddress=lfSaveAddress+consume.substring(consume.lastIndexOf("/"));
                   service.download(consume,lfSaveAddress);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
        }).start();
    }


}
