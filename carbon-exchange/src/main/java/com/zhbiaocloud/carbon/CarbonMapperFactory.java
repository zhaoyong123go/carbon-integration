/*
 * Copyright (c) 2018-2023. Chengdu WeiSiFan Technology Co., Ltd.
 * Carbon Integration SDK is licensed under Mulan PSL v2.
 *
 * You can use this software according to the terms and conditions of the Mulan PSL v2.
 * You may obtain a copy of Mulan PSL v2 at: http://license.coscl.org.cn/MulanPSL2
 *
 * THIS SOFTWARE IS PROVIDED ON AN "AS IS" BASIS, WITHOUT WARRANTIES OF ANY KIND,
 * EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO NON-INFRINGEMENT,
 * MERCHANTABILITY OR FIT FOR A PARTICULAR PURPOSE.
 * See the Mulan PSL v2 for more details.
 */

package com.zhbiaocloud.carbon;

import static java.time.format.DateTimeFormatter.ofPattern;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import lombok.RequiredArgsConstructor;

/**
 * 与服务端通信的时的序列化规则
 *
 * @author jun
 */
@RequiredArgsConstructor
public class CarbonMapperFactory {

  private static final DateTimeFormatter TIME_PTN = ofPattern("HH:mm:ss");
  private static final DateTimeFormatter DATE_PTN = ofPattern("yyyy-MM-dd");
  private static final DateTimeFormatter DATETIME_PTN = ofPattern("yyyy-MM-dd HH:mm:ss");

  private final boolean isProd;

  public ObjectMapper create() {
    return JsonMapper.builder()
        .configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, true)
        .configure(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS, false)
        // 开发、测试环境则进行报错。识别未知字段，可以及时发现问题
        // 生产环境，则忽略未知字段
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, !isProd)
        .build()
        .registerModule(
            new JavaTimeModule()
                .addSerializer(LocalDate.class, new LocalDateSerializer(DATE_PTN))
                .addDeserializer(LocalDate.class, new LocalDateDeserializer(DATE_PTN))

                .addSerializer(LocalTime.class, new LocalTimeSerializer(TIME_PTN))
                .addDeserializer(LocalTime.class, new LocalTimeDeserializer(TIME_PTN))

                .addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DATETIME_PTN))
                .addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DATETIME_PTN))
        )
        .setSerializationInclusion(Include.NON_NULL)
        .setSerializationInclusion(Include.NON_EMPTY);
  }
}
