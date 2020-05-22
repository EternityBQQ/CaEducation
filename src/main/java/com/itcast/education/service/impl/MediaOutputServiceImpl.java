package com.itcast.education.service.impl;

import com.itcast.education.config.GeneralConstant;
import com.itcast.education.controller.dto.HomePageDto;
import com.itcast.education.mapper.MediaOutputMapper;
import com.itcast.education.model.course.Course;
import com.itcast.education.model.course.Tag;
import com.itcast.education.model.media.MediaOutput;
import com.itcast.education.service.CourseService;
import com.itcast.education.service.MediaOutputService;
import com.itcast.education.service.TagService;
import com.itcast.education.utils.JsonUtil;
import com.itcast.education.utils.ValidateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MediaOutputServiceImpl implements MediaOutputService {
    @Resource
    private MediaOutputMapper mediaOutputMapper;
    @Autowired
    private CourseService courseService;
    @Autowired
    private TagService tagService;

    @Cacheable(cacheNames = "homePageData")
    @Override
    public HomePageDto queryHomePageData(Integer carouseSize, Integer categorySize) {
        // 轮播图
        List<MediaOutput> carouselList = mediaOutputMapper.queryMediaByType(GeneralConstant.IMAGE, carouseSize);
        // 标签列表
        List<MediaOutput> categoryIcons = mediaOutputMapper.queryMediaByType(GeneralConstant.ICON, categorySize);
        // 课程列表
        List<Course> courses = courseService.queryCourses(GeneralConstant.COURSE_LIMIT_NUMBER);
        HomePageDto homePageDto = convertPageData(carouselList, categoryIcons, courses);
        return homePageDto;
    }

    @Override
    public MediaOutput findMediaById (Integer mediaId) {
        MediaOutput findMediaOutput = new MediaOutput();
        findMediaOutput.setMediaId(mediaId);
        List<MediaOutput> mediaOutputs = mediaOutputMapper.find(findMediaOutput);
        if (!ValidateUtil.listIsEmpty(mediaOutputs)) {
            return mediaOutputs.get(0);
        } else {
            return null;
        }
    }

    /**
     * 将媒体数据集合转换为图片数据集
     * @param carouselList 轮播图
     * @param categoryTag 分类标签
     * @param courseList 课程列表
     * @return 首页DTO对象
     */
    public HomePageDto convertPageData(List<MediaOutput> carouselList, List<MediaOutput> categoryTag, List<Course> courseList) {
        HomePageDto homePageDto = new HomePageDto();
        // 过滤非轮播图字段
        for (MediaOutput mediaOutput: carouselList) {
            Map<String, Object> carouseObj = new HashMap<>();
            carouseObj.put(GeneralConstant.ID, mediaOutput.getMediaId());
            carouseObj.put(GeneralConstant.TYPE, mediaOutput.getMediaType());
            carouseObj.put(GeneralConstant.URL, mediaOutput.getUrl());
            homePageDto.getCarouselList().add(carouseObj);
        }
        // 过滤得分类标签字段
        for (MediaOutput mediaOutput: categoryTag) {
            Map<String, Object> categoryObj = new HashMap<>();
            categoryObj.put(GeneralConstant.CU_ICON, mediaOutput.getMediaIcon());
            categoryObj.put(GeneralConstant.COLOR, mediaOutput.getMediaIconColor());
            categoryObj.put(GeneralConstant.BADGE, mediaOutput.getMediaIconBadge());
            categoryObj.put(GeneralConstant.NAME, mediaOutput.getMediaIconName());
            homePageDto.getCategoryTag().add(categoryObj);
        }
        // 添加课程列表
        for (Course course : courseList) {
            Map<String, Object> courseObj = new HashMap<>();
            courseObj.put(GeneralConstant.TITLE, course.getCourseName());
            courseObj.put(GeneralConstant.DISCOUNT_PRICE, course.getDiscountPrice());
            courseObj.put(GeneralConstant.SALE_PRICE, course.getCoursePrice());
            courseObj.put(GeneralConstant.SALE_AMOUNT, course.getSaleCourse());
            courseObj.put(GeneralConstant.IMG_URL, course.getCourseUrl());
            List<Integer> tagIds = (List<Integer>) JsonUtil.strToPojo(course.getTags(), List.class);
            List<String> tags = new ArrayList<>();
            for (Integer id : tagIds) {
                // 通过Id获取标签对象
                Tag tag = tagService.find(new Tag(id));
                if (tag != null) {
                    String tagName = tag.getTagName();
                    tags.add(tagName);
                }
            }
            courseObj.put(GeneralConstant.TAGS, tags);
            homePageDto.getCourseList().add(courseObj);
        }
        return homePageDto;
    }
}
