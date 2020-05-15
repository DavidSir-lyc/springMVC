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
