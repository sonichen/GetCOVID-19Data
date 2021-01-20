package project.dao;

import project.domain.ProvinceDetails;

import java.util.List;

public interface IProvinceDetailsDao {
    /**
     * 查询所有地区的信息
     * @param countryName
     * @return
     */
    List<ProvinceDetails>findAll(String countryName);

    /**
     * 将国家的地区信息插入
     * @param provinceDetails
     */
    void saveProvinceDetails(ProvinceDetails provinceDetails);

    /**
     * 删除所有地区信息
     */
    void deleteProvinceDetails();
}
