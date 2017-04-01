import data.User;
import mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by xyq on 17/3/31.
 */
public class TestMybatis extends Assert {
    @Test
    public void test() {
        String resource = "mybatis-config.xml";
        SqlSessionFactory sqlSessionFactory = initSqlSessionFactory(resource);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = new User();
        user.setUserId("id");
        user.setUserName("name");
        assertEquals(userMapper.insertUser(user), 1);
        User res = userMapper.getUser("id");
        assertEquals(res.getUserId(), "id");
        assertEquals(res.getUserName(), "name");
        user.setUserName("NAME");
        assertEquals(userMapper.updateUser(user), 1);
        assertEquals(userMapper.deleteUser("id"), 1);
    }

    public static SqlSessionFactory initSqlSessionFactory(String resource) {
        InputStream is = null;
        try {
            is = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            System.out.println("can not load resource " + resource);
        }
        return new SqlSessionFactoryBuilder().build(is);
    }
}
