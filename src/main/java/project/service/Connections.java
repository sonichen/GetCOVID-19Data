package project.service;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import project.dao.ICountryDetailsDao;
import project.dao.ICountryListDao;
import project.dao.IProvinceDetailsDao;
import project.domain.CountryDetails;
import project.domain.CountryList;
import project.domain.ProvinceDetails;

import java.io.InputStream;
import java.util.List;

/**
 * 连接数据库
 */
public class Connections {

    InputStream in ;
    SqlSessionFactoryBuilder builder ;
    SqlSessionFactory factory;
    SqlSession session ;
    static ICountryListDao countryListDao;
    static ICountryDetailsDao countryDetailsDao;
    static IProvinceDetailsDao provinceDetailsDao;



    @Before
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
        countryListDao = session.getMapper(ICountryListDao.class);
        countryDetailsDao=session.getMapper(ICountryDetailsDao.class);
        provinceDetailsDao=session.getMapper(IProvinceDetailsDao.class);
    }

    //最后，关闭资源
    @After
    public void destory()throws Exception{
        //6.释放资源
        in.close();
    }


    /**
     * 在countryList表中插入数据
     * @param countryList
     * @throws Exception
     */
    public void saveCountryListMethod(CountryList countryList) throws Exception {
        init();
        countryListDao.saveCountryList(countryList);

    }
/////////////////////////插入数据///////////////////////////////////
    /**
     * 在countryDetails表中插入数据
     * @param countryDetails
     * @throws Exception
     */
    public void saveCountryDetailsMethod(CountryDetails countryDetails) throws Exception {
        init();
        countryDetailsDao.saveCountryDetails(countryDetails);
    }

    /**
     * 在provinceDetails表中插入数据
     * @param provinceDetails
     * @throws Exception
     */
    public void saveProvinceDetailsMethod(ProvinceDetails provinceDetails) throws Exception {
        init();
        provinceDetailsDao.saveProvinceDetails(provinceDetails);
    }

    //////////////////////查找数据/////////////////////////

    /**
     * 在countyList中查找数据
     * @throws Exception
     */
    public  void findCountryList() throws Exception {
        init();
        List<CountryList>list=countryListDao.findAll();
        for(CountryList country:list){
            System.out.println(country);

        }
    }


    /**
     * 在countryDetails表中国家名查找数据
     * @param countryName
     * @throws Exception
     */
    public  void findCountryDetails(String countryName) throws Exception {
        init();
        List<CountryDetails>list=countryDetailsDao.findAll(countryName);
        for(CountryDetails country:list){
            System.out.println(country);

        }
    }


    /**
     * 在provinceDetails表中根据国家名字查找数据
     * @param countryName
     * @throws Exception
     */
    public  void findProvinceDetails(String countryName) throws Exception {
        init();
        List<ProvinceDetails>list=provinceDetailsDao.findAll(countryName);
        for(ProvinceDetails province:list){
            System.out.println(province);

        }
    }
//////////////////////////删除数据//////////////////////////////////////
    @Test
    public void deleteCountryDetails() throws Exception {
        init();
        countryDetailsDao.deleteCountryDetails();
    }
    @Test
    public void deleteCountryList() throws Exception {
        init();
       countryListDao.deleteCountryList();
    }

    @Test
    public void deleteProvinceDetails() throws Exception {
        init();
        provinceDetailsDao.deleteProvinceDetails();
    }

    /**
     *
     * @return 返回国家列表
     * @throws Exception
     */
    @Test
    public List<CountryList> CountryNameList() throws Exception {

        List<CountryList>list=countryListDao.findAll();
        return list;
    }
}
