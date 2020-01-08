package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
class JsonTest {
   /* *//**
     * 接收页面请求的JSON参数，并返回JSON格式的结果
     *//*
    @RequestMapping(".do")
    @ResponseBody
    public String testJson() {
        return "{name: 'lyc'}";
    }*/
    @RequestMapping("/jsonTest01")
    @ResponseBody
    @CrossOrigin(origins = "*", maxAge = 3600)
    public Map<String,Object> doMapJsonString(){
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("id", 100);
        map.put("name", "BeiJing");
        return map;
        //系统底层会基于返回值查找对应的转换,将对象转换为指定格式的字符串
    }

    @RequestMapping("/jsonTest02")
    @ResponseBody
    public List<Map<String,Object>> doMapJsonString02(){
        List<Map<String,Object>> list = new ArrayList<>();
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("id", 100);
        map.put("name", "BeiJing");
        list.add(map);
        map = new LinkedHashMap<>();
        map.put("id", 200);
        map.put("name", "ShangHai");
        list.add(map);
        return list;
        //系统底层会基于返回值查找对应的转换,将对象转换为指定格式的字符串
    }
}
