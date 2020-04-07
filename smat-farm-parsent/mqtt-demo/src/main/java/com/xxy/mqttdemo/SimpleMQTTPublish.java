package com.xxy.mqttdemo;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 * 测试mqtt协议
 *
 * @author wlz on 2020/4/4
 */
public class SimpleMQTTPublish {

    public static void main(String[] args) {
        String broker = "tcp://localhost:1883";
        String clientId = "xxy";
        String username = "guest";
        String password = "guest";
        String content = "wwwww";
        int qos = 1;
        String topic = "yjs/1705c/xxy";
        try {
            //链接地址  客户端id自定义
            MqttClient mqttClient = new MqttClient(broker,clientId,new MemoryPersistence());

            //携带属性
            MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
            //在重新启动和链接是记住状态
            mqttConnectOptions.setCleanSession(true);
            //设置连接是的用户名和密码
            mqttConnectOptions.setUserName(username);
            mqttConnectOptions.setPassword(password.toCharArray());
            //进行连接
            mqttClient.connect(mqttConnectOptions);
            MqttMessage mqttMessage = new MqttMessage(content.getBytes());
            mqttMessage.setQos(qos);
            //发布消息
            mqttClient.publish(topic,mqttMessage);
            //断开连接
            mqttClient.disconnect();
            mqttClient.close();
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}
