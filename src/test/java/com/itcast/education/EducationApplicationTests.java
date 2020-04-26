package com.itcast.education;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

@SpringBootTest
class EducationApplicationTests {

    @Test
    void contextLoads() {
        ObjectMapper mapper = new ObjectMapper();
        String str = "[1, 2, 3]";
        try {
            List list = mapper.readValue(str, List.class);
            System.out.println(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
