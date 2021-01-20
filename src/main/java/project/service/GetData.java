package project.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
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

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.util.*;

public class GetData {
    public static void main(String[] args) throws Exception {
        String msg=getData("https://covid-api.mmediagroup.fr/v1/cases");
        System.out.println(msg);
    }


    /**
     * 访问该网址的接口获取数据
     * @param urlPath
     * @return 疫情数据
     * @throws Exception
     */
    public static String getData(String urlPath) throws Exception {
        try {
            // 1. 得到访问地址的URL
            URL url = new URL(urlPath);
            // 2. 得到网络访问对象java.net.HttpURLConnection
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            /* 3. 设置请求参数（过期时间，输入、输出流、访问方式），以流的形式进行连接 */
            // 设置是否向HttpURLConnection输出
            connection.setDoOutput(false);
            // 设置是否从httpUrlConnection读入
            connection.setDoInput(true);
            // 设置请求方式
            connection.setRequestMethod("GET");
            // 设置是否使用缓存
            connection.setUseCaches(true);
            // 设置此 HttpURLConnection 实例是否应该自动执行 HTTP 重定向
            connection.setInstanceFollowRedirects(true);
            // 设置超时时间
            connection.setConnectTimeout(3000);
            // 连接
            connection.connect();
            // 4. 得到响应状态码的返回值 responseCode
            int code = connection.getResponseCode();
            // 5. 如果返回值正常，数据在网络中是以流的形式得到服务端返回的数据
            String msg = "";
            if (code == 200) { // 正常响应
                // 从流中读取响应信息
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(connection.getInputStream()));
                String line = null;

                while ((line = reader.readLine()) != null) { // 循环从流中读取
                    msg += line + "\n";
                }
                reader.close(); // 关闭流
            }
            // 6. 断开连接，释放资源
            connection.disconnect();
            return msg;
            // 显示响应结果`
//            System.out.println(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }



    /**
     * 插入数据到数据库中
     * @param countryName
     * @throws Exception
     */
    public  void operateOnSQL(String countryName) throws Exception {

       String url = "https://covid-api.mmediagroup.fr/v1/cases?country=" + countryName;

        try {
            /**
             * 用变量data存储getData()中读取的数据
             * 为了防止网络原因，以下选择将已经读取的数据存入data.json中并利用其中的数据进行测试
             */
        //    String data=getData(url);

            //读取数据
            ReadJSON readJSON=new ReadJSON();
            String data=readJSON.readJson(countryName);

            JSONObject jsonObject = JSON.parseObject(data);

            //存储国家的整体信息
            String[] countryMessage = new String[15];
            //存储地区的信息
            String[] provinceMessage = new String[8];
            //连接数据库
            Connections connections=new Connections();
            //判断是否是国家信息
            boolean isAll = false;

            int iso = 0;

            for (Map.Entry<String, Object> stringObjectEntry : jsonObject.entrySet()) {

                String key = stringObjectEntry.getKey();

                JSONObject value = (JSONObject) stringObjectEntry.getValue();

                if (key == "All") {
                    isAll = true;//是国家整体信息
                }

                JSONObject insideJson = value;

                int index = 0;

                for (Map.Entry<String, Object> insideEntry : insideJson.entrySet()) {

                    Object getValue = insideEntry.getValue();

                    //存入数据
                    if (isAll) {
                        countryMessage[index++] = getValue.toString();
                    } else {
                        provinceMessage[index++] = getValue.toString();
                    }

                }



                if (isAll) {//是国家整体数据时

                    //建立对象CountryList
                    CountryList countryList = new CountryList();
                    iso = Integer.parseInt(countryMessage[2]);
                    countryList.setCountryName(countryMessage[1]);
                    countryList.setIso(iso);
                    //并存入数据库
                    connections.saveCountryListMethod(countryList);

                    isAll = false;
                    //建立对象CountryDetails
                    CountryDetails countryDetails=new CountryDetails();
                    countryDetails.setIso_country(iso);
                    countryDetails.setContinent(countryMessage[0]);
                    countryDetails.setCapital_city(countryMessage[3]);
                    countryDetails.setLife_expectancy(Double.parseDouble(countryMessage[4]));
                    countryDetails.setAbbreviation(countryMessage[5]);
                    countryDetails.setConfirmed(Integer.parseInt(countryMessage[6]));
                    countryDetails.setPopulation(Integer.parseInt(countryMessage[7]));
                    countryDetails.setSq_km_area(Integer.parseInt(countryMessage[8]));
                    countryDetails.setRecovered(Integer.parseInt(countryMessage[9]));
                    countryDetails.setElevation_in_meters(countryMessage[10]);
                    countryDetails.setLocation(countryMessage[11]);
                    countryDetails.setDeaths(Integer.parseInt(countryMessage[12]));
                    //存入数据库
                    connections.saveCountryDetailsMethod(countryDetails);

                } else {


                    //存储地区信息
                    ProvinceDetails provinceDetails=new ProvinceDetails();
                    provinceDetails.setIso_pro(iso);
                    provinceDetails.setProvinceName(key);
                    provinceDetails.setRecovered(Integer.parseInt(provinceMessage[0]));
                    provinceDetails.setConfirmed(Integer.parseInt(provinceMessage[1]));
                    provinceDetails.setUpdated(provinceMessage[2]);
                    provinceDetails.setLatitude("".equals(provinceMessage[3]) ? null : Double.parseDouble(provinceMessage[3]));
                    provinceDetails.setLongitude("".equals(provinceMessage[4]) ? null : Double.parseDouble(provinceMessage[4]));
                    provinceDetails.setDeaths(Integer.parseInt(provinceMessage[5]));
                    //存入数据库
                    connections.saveProvinceDetailsMethod(provinceDetails);
                }

            }

            System.out.println(countryName+"的数据导入成功。");

        } catch (Exception e) {

            System.out.println(e.getMessage());
            throw new RuntimeException(countryName+"的数据导入失败。");

        }

    }



}
