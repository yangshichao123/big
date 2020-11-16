package com.data.big.gw;


public class main {

    /**@Author:李亚峰
     * @Date:2017-9-11 下午4:47:43
     * @Tag:@param args
     * @TODO:TODO
     */
    public static void main(String[] args) {
        try {
            GwaqscJxglService service = new GwaqscJxglService();//创建接口方法类
            GwaqscJxglServicePortType portType = service. getGwaqscJxglServiceHttpSoap12Endpoint ();//获取接口对象
            String testString= portType.getHcsj("2020-09-08", "2020-09-08", "3","","1","10000");
            System.out.println(testString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
