package project.test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import project.dao.IProvinceDetailsDao;
import project.domain.ProvinceDetails;

import java.io.InputStream;
import java.util.List;

public class TestProvinceDetails {

    InputStream in ;
    SqlSessionFactoryBuilder builder ;
    SqlSessionFactory factory;
    SqlSession session ;
    IProvinceDetailsDao provinceDetailsDao;


    //初始化，配置
    @Before//用于测试方法执行之前执行
    public void init() throws Exception{
        //1.读取配置文件
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.创建 SqlSessionFactory 的构建者对象
        builder = new SqlSessionFactoryBuilder();
        //3.使用构建者创建工厂对象 SqlSessionFactory
        factory = builder.build(in);
        //4.使用 SqlSessionFactory 生产 SqlSession 对象
        session = factory.openSession();
        //5.使用 SqlSession 创建 dao 接口的代理对象
        provinceDetailsDao = session.getMapper(IProvinceDetailsDao.class);
    }
    //最后，关闭资源
    @After//用于测试方法执行之后执行
    public void destory()throws Exception{
        //6.释放资源
        in.close();
    }
    @Test
    public void findAll() {

        //5.使用代理对象执行方法
        List<ProvinceDetails> lists = provinceDetailsDao.findAll("China");
        for(ProvinceDetails provinceDetails : lists){
            System.out.println(provinceDetails);
        }


    }
//    @Test
//    public void saveProvince(){
//        GetData getData=new GetData();
//        ArrayList<User> users=getData.push();
//        for(int i=0;i<users.size();i++){
//            User user=users.get(i);
//            userDao.saveUser(user);
//            System.out.println("保存成功");
//        }
//
//    }

}
