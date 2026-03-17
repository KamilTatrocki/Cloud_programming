package org.example.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.databind.SerializationFeature;
//
//public class JsonMapper {
//    private static final ObjectMapper mapper = new ObjectMapper()
//            .registerModule(new JavaTimeModule())
//            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
//
//    public static byte[] toJson(Object obj) throws Exception {
//        return mapper.writeValueAsBytes(obj);
//    }
//
//    public static <T> T fromJson(byte[] data, Class<T> clazz) throws Exception {
//        return mapper.readValue(data, clazz);
//    }
//}