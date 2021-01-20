package project.view;

import project.domain.CountryList;
import project.service.Connections;
import project.service.GetData;

import java.util.List;
import java.util.Scanner;


public class View {
    public static void main(String[] args) throws Exception {
        Connections connections=new Connections();
        GetData tool=new GetData();
        Scanner sc=new Scanner(System.in);
        /**
         * 预备工作
         * 存入中国，美国，英国，日本四个国家的数据
         */
        tool.operateOnSQL("China");
        tool.operateOnSQL("US");
        tool.operateOnSQL("Japan");
        tool.operateOnSQL("United Kingdom");
        System.out.println("完成预备工作");
        /**
         *
         */

        boolean loop=true;
        while (loop){
            System.out.println("------疫情数据查询系统------");
            System.out.println("1.查询系统疫情数据");
            System.out.println("2.向数据库添加新的国家");
            System.out.println("3.更新实时数据");
            System.out.println("4.退出系统");
            System.out.println("请输入您的操作");
            ///////////////////////////////////////
            int next=sc.nextInt();
            switch (next){
                /**
                 * 1.查询系统疫情数据
                 */
                case  1:
                    System.out.println("请选择您要查询信息的国家");
                    System.out.println("国家列表");
                    connections.findCountryList();
                    System.out.println("请输入国家的名字");
                    String name=sc.next();
                    connections.findCountryDetails(name);
                    System.out.println("是否查询该国家各个地区的疫情数据[yes/no]");
                    String choose=sc.next();
                    if(choose.equals("yes")){
                        connections.findProvinceDetails(name);
                        System.out.println("以上为该国家疫情数据");
                        System.out.println("本次查询结束。");
                    }else{
                        System.out.println("本次查询结束。");
                    }
                    break;

                /**
                 * 2.向数据库添加新的国家
                 */
                case 2:
                    System.out.println("请输入您要加入的国家的名字");
                    String newCountryName=sc.next();
                    //测试用例：Colombia
                    tool.operateOnSQL(newCountryName);
                    System.out.println("添加成功.");
                    break;

                /**
                 * 3.更新实时数据
                 */
                case 3:
                    System.out.println("正在为您更新实时数据中......");
                    List<CountryList> sets=connections.CountryNameList();
                    connections.deleteCountryDetails();
                    connections.deleteCountryList();
                    connections.deleteProvinceDetails();
                    System.out.println("成功删除旧数据");
                    System.out.println("开始导入新数据");

                    for(int i=0;i<sets.size();i++){
                        String getName=sets.get(i).getCountryName();
                        tool.operateOnSQL(getName);
                        System.out.println("更新"+getName+"的数据成功");
                    }

                    System.out.println("更新成功。");
                    break;

                /**
                 * 4.退出系统
                 */
                case 4:
                    System.out.println("退出系统中......");
                    loop=false;
                    break;
                default:
                    System.out.println("非法输入");
                    break;

            }

        }
        System.out.println("感谢您使用疫情数据查询系统");

    }

}
