package com.kot.kotmybatis;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * @Author yangyu
 * @create 2019/11/20 15:52
 */
public class ScheduleTest {
    public static void main(String[] args) throws InterruptedException {

        while (true) {
            ResponseEntity<String> result1 = new RestTemplate().getForEntity("http://localhost:9898/list", String.class);
            System.err.println(JSONObject.toJSONString(result1.getBody(), SerializerFeature.PrettyFormat));
            Thread.sleep(5000);
            ResponseEntity<String> result2 = new RestTemplate().getForEntity("http://localhost:9898/getCar", String.class);
            System.err.println(JSONObject.toJSONString(result2.getBody(), true));
            Thread.sleep(5000);
        }


    }

}
