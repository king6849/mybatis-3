package Demo;

import Demo.dao.CustomerDao;
import Demo.pojo.Customer;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class DemoMainTest {

  SqlSessionFactory factory;
  SqlSession sqlSession;

  @Before
  public void before() {
    try {
      InputStream inputStream = Resources.getResourceAsStream("demo/SqlMapConfig.xml");
      factory = new SqlSessionFactoryBuilder().build(inputStream);
      sqlSession = factory.openSession();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

  }

  @Test
  public void test1() {

    //这⾥不调⽤SqlSession的api,⽽是获得了接⼝对象，调⽤接⼝中的⽅法。使用JDK动态代理产生代理对象
    CustomerDao mapper = sqlSession.getMapper(CustomerDao.class);
    // 代理对象执行方法的时候，调用的最终是底层的invoke方法
    List<Customer> list = mapper.findAll();
    list.forEach(user -> {
      System.out.println(user.toString());
    });
  }

}
