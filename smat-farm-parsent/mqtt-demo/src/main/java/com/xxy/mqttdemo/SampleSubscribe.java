package com.xxy.mqttdemo;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 * 测试网页版mqtt15675
 *
 * @author wlz on 2020/4/4
 */
public class SampleSubscribe {

    public static void main(String[] args) throws MqttException {
        String broker = "tcp://localhost:1883";
        String clientId = "xxy";
        String username = "guest";
        String password = "guest";
        String content = "gggddd";
        int qos = 1;
        String topic = "yjs/1705c/#";

        MqttClient mqttClient = new MqttClient(broker,clientId,new MemoryPersistence());

        //创建连接属性
        MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
        mqttConnectOptions.setCleanSession(true);
        mqttConnectOptions.setUserName(username);
        mqttConnectOptions.setPassword(password.toCharArray());
        //设置超时时间
        mqttConnectOptions.setConnectionTimeout(10);
        //设置回话时间
        mqttConnectOptions.setKeepAliveInterval(20);

        mqttClient.setCallback(new MqttCallback() {
            @Override
            public void connectionLost(Throwable throwable) {
                System.out.println("数据丢失");
            }

            @Override
            public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
                System.out.println("topic:"+topic);
                System.out.println("Qos:"+mqttMessage.getQos());
                System.out.println("message content:"+new String(mqttMessage.getPayload()));
                System.out.println("消息已到");
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
                System.out.println("消息发送完成----->"+iMqttDeliveryToken.isComplete());
            }
        });
        //建立连接
        mqttClient.connect();
        //订阅
        mqttClient.subscribe(topic,qos);

    }
}
