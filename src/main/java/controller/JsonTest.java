package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSON;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
class JsonTest {
    @RequestMapping("/jsonTest01")
    @ResponseBody
    @CrossOrigin(origins = "*", maxAge = 3600)
    public Map<String,Object> doMapJsonString(){
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("id", 100);
        map.put("name", "BeiJing");
        // return JSON.parseObject(String.valueOf(map));
        // System.out.println(JSON.toJSON(map));
        // System.out.println(JSON.toJSONString(map));
        return (Map<String, Object>) JSON.toJSON(map);
    }

    @RequestMapping("/jsonTest02")
    @ResponseBody
    @CrossOrigin(origins = "*", maxAge = 3600)
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
        // System.out.println(JSON.toJSONString(list));
        return list;
    }

    /**
     * 通过形参接收请求参数，形参名称与请求参数名称完全相同
     * 改参数名就要改形参名，H5传参名错误，java接受不到抛500，不完美
     */
    @RequestMapping("/testParams01")
    @ResponseBody
    @CrossOrigin(origins = "*", maxAge = 3600)
    public Map<String,Object> testParamsFun01(String name, int age){
        HashMap<String, Object> map = new LinkedHashMap<>();
        map.put("returnName", name);
        map.put("returnAge", age);
        // return JSON.parseObject(String.valueOf(map));
        // System.out.println(JSON.toJSON(map));
        // System.out.println(JSON.toJSONString(map));
        return (Map<String, Object>) JSON.toJSON(map);
    }

    /**
     * 通过HttpServletRequest接收请求参数
     */
    @RequestMapping("/testParams02")
    @ResponseBody
    @CrossOrigin(origins = "*", maxAge = 3600)
    public Map<String,Object> testParamsFun02(HttpServletRequest request){
        HashMap<String, Object> map = new LinkedHashMap<>();
        String name = request.getParameter("name");
        String age = request.getParameter("age");
        map.put("returnName", name);
        map.put("returnAge", age);
        return (Map<String, Object>) JSON.toJSON(map);
    }
}
