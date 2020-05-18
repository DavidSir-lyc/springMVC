package lyc.java.SSM.controller;
import com.alibaba.fastjson.JSON;
import lyc.java.SSM.POJO.User;
import lyc.java.SSM.dao.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/*  常用注解
    @Controller：标识这个类是一个控制器
    @RequestMapping：给控制器方法绑定一个uri
    @ResponseBody：将java对象转成json，并且发送给客户端
    @RequestBody：将客户端请求过来的json转成java对象
    @RequestParam：当表单参数和方法形参名字不一致时，做一个名字映射
    @PathVarible：用于获取uri中的参数,比如user/1中1的值

    Rest风格的新api
    @RestController相当于@Controller+ @ResponseBody
    @GetMapping@DeleteMapping@PostMapping@PutMapping

    其他注解
    @SessionAttribute：声明将什么模型数据存入session
    @CookieValue：获取cookie值
    @ModelAttribute：将方法返回值存入model中
    @HeaderValue：获取请求头中的值
*/

@Controller
public class GetUserById {
    @GetMapping("/getUserById")
    @ResponseBody
    @CrossOrigin(origins = "*", maxAge = 3600)
    public JSON getUserById(HttpServletRequest request) throws IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        User resJson = null;
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            User resObj = userMapper.selectUser(id);

            System.out.println(resObj);
            resJson = resObj;
            // HashMap<String, Object> resObj = new LinkedHashMap<>();
        } catch (Exception e){
            sqlSession.rollback();  // 回滚事务
        } finally {
            sqlSession.close();
        }
        return (JSON) JSON.toJSON(resJson);
    }
}
