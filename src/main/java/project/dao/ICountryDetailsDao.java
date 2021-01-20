package project.dao;

import project.domain.CountryDetails;

import java.util.List;

public interface ICountryDetailsDao {

    /**
     * 根据名字查询国家信息
     * @param countryName
     * @return
     */
    List<CountryDetails>findAll(String countryName);

    /**
     * 插入国家数据到数据库
     * @param countryDetails
     */
    void saveCountryDetails(CountryDetails countryDetails);

    /**
     * 删除国家信息表所有数据
     */
    void deleteCountryDetails();

}
