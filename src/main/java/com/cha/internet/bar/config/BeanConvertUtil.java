package com.cha.internet.bar.config;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
public class BeanConvertUtil {

    /**
     * 将源对象列表转换为目标对象列表
     * @param source 源对象列表
     * @param target 目标对象类型
     */
    public static <T> List<T> listConvert(List<?> source, Class<T> target) {
        try {
            if (CollectionUtils.isEmpty(source)) {
                return new ArrayList<>();
            }

            List<T> targetList = new ArrayList<>();
            for (Object object : source) {
                T instance = target.newInstance();
                BeanUtils.copyProperties(object, instance);
                targetList.add(instance);
            }
            return targetList;
        } catch (Exception e) {
            log.error("list copy convert exception,source:{}", JSON.toJSONString(source), e);
            return null;
        }

    }

    public static <T> T beanConvert(Object source, Class<T> target) {

        try {
            if (Objects.isNull(source)) {
                return null;
            }
            T result = target.newInstance();
            BeanUtils.copyProperties(source, result);
            return result;
        } catch (Exception e) {
            log.error("bean copy convert exception,source:{}", JSON.toJSONString(source), e);
            return null;
        }

    }


}
