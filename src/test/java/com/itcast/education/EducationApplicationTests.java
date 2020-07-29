package com.itcast.education;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itcast.education.model.dto.CourseDto;
import com.itcast.education.model.pojo.course.Course;
import com.itcast.education.utils.CommonUtil;
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

    @Test
    void convertTest() {
        CourseDto dto = new CourseDto();
        dto.setCourseName("测试课程");
        dto.setCoursePrice(250d);
        dto.setDiscountPrice(200d);
        dto.setSaleCourse(25);
        dto.setTags(null);
        dto.setCourseUrl("http://test");
        Object model = CommonUtil.convertDto2Entity(dto, Course.class);
        System.out.println(model);
    }

}
