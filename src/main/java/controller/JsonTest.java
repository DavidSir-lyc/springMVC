package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
class JsonTest {
    /**
     * 接收页面请求的JSON参数，并返回JSON格式的结果
     */
    @RequestMapping(".do")
    @ResponseBody
    public String testJson() {
        return "{name: 'lyc'}";
    }
}
