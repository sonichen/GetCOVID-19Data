package project.dao;

import project.domain.CountryList;

import java.util.List;

public interface ICountryListDao {

    /**
     * 查询有哪些国家在数据库中
     * @return
     */
    List<CountryList> findAll();

    /**
     * 加入新国家到数据库
     * @param countryList
     */
    void saveCountryList(CountryList countryList);

    /**
     * 删除国家列表的所有数据
     */
    void deleteCountryList();
}
