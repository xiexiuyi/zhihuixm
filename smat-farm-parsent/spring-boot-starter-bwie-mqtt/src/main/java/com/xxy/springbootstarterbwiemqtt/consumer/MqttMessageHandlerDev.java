package com.xxy.springbootstarterbwiemqtt.consumer;

//@Service
public class MqttMessageHandlerDev{

    public void receiveMessage(String topic, String payload) {
        System.out.println(topic + "接受到了消息:" + payload);
        //自己的业务逻辑

    }
}
