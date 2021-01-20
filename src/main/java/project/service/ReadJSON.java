package project.service;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.io.*;

/**
 * 为了避免获取第三方网站的数据因为网络问题导致错误，事先将数据存入data.json文件中
 */

public class ReadJSON {

    /**
     * 读取JSON文件中的内容，返回字符串
     * @param countryName
     * @return
     */
    @Test
    public  String readJson(String countryName){
        //从给定位置获取文件
        File file = new File("src/main/java/project/service/data.json");
        BufferedReader reader = null;
        //返回值,使用StringBuffer
        StringBuffer data = new StringBuffer();
        //
        try {
            reader = new BufferedReader(new FileReader(file));
            //每次读取文件的缓存
            String temp = null;
            while((temp = reader.readLine()) != null){
                data.append(temp);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            //关闭文件流
            if (reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        String all=data.toString();

        JSONObject jsonObject=JSONObject.parseObject(all);
        String result=jsonObject.getString(countryName);


        return result;

    }
}
