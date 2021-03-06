package com.ayt.utils.lambdaUtil;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

/**
 * Description
 * Author ayt  on 20190110
 */
@Test
public class test {
    private static final Logger logger = LoggerFactory.getLogger(test.class);

    public void test1(){
        String riderCode="20配送费、82准时送达奖励、85场景任务奖励、86新手跑单奖励、87人工派单奖励、" +
                "92恶劣天气补贴、93时段奖励、94准时到店奖励、99困难单奖励、101优质订单加价、102大单补贴、" +
                "76其他临时调价、79随机红包奖励、72配送费动态调价、26人工派单奖励、27配送难度补贴、9充值、" +
                "49押金返还、89垫付返还、90小费、97余额支付退款、103保证金转入、107揽件补贴、108揽件佣金、" +
                "109电子面单寄件费扣除、43排行榜奖励、13提现失败返还、17虚拟货币兑换余额、48押金扣除、96余额支付、104转出至保证金、105菜鸟技术服务费";
        List<String> riderTradeList = Arrays.asList(riderCode.split("、"));
        riderTradeList.forEach(item ->splitNotNumber(item));
        //取出类型值
        List<String> riderTradeMean=
        riderTradeList.stream().map(item->splitNotNumber(item)).collect(Collectors.toList());
//        System.out.println("mean值是"+JSON.toJSONString(riderTradeMean));
        logger.info("mean值是"+JSON.toJSONString(riderTradeMean));
        //取出code
        List<String> riderTradeCode =
                riderTradeList.stream().map(item ->getNumbers(item) ).collect(Collectors.toList());
        logger.info("code值是"+JSON.toJSONString(riderTradeCode));

        List<RiderTradeDto> riderTradeDtos = riderTradeList.stream().map(item ->
                new RiderTradeDto(getNumbers(item),splitNotNumber(item))).collect(Collectors.toList());
        logger.info("riderTradeDto是："+JSON.toJSONString(riderTradeDtos));
        //排序结果是
        riderTradeDtos.sort(comparing(RiderTradeDto::getCode));
        logger.info("根据Code排序结果是："+JSON.toJSONString(riderTradeDtos));

    }
    // 截取非数字
    public String splitNotNumber(String content) {
        Pattern pattern = Pattern.compile("\\D+");
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            return matcher.group(0);
        }
        return "";
    }
    //截取数字
    public String getNumbers(String content) {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            return matcher.group(0);
        }
        return "";
    }

    public void test(){
        List<Student> list = new ArrayList<>();
        Student student1 = new Student();student1.setAge("12");student1.setSex(0);student1.setName("学生一号");
        Student student2 = new Student();student2.setAge("13");student2.setSex(2);student2.setName("学生二号");
        Student student3 = new Student();student3.setAge("11");student3.setSex(1);student3.setName("学生三号");
        Student student4 = new Student();student4.setAge("18");student4.setSex(1);student4.setName("学生四号");
        Student student5 = new Student();student5.setAge("18");student5.setSex(0);student5.setName("学生五号");
        Student student6 = new Student();student6.setAge("18");student6.setSex(2);student6.setName("学生六号");
        Student student7 = new Student();student7.setAge("18");student7.setSex(2);student7.setName("学生七号");
        list.add(student1);
        list.add(student2);
        list.add(student3);
        list.add(student4);
        list.add(student5);
        list.add(student6);
        list.add(student7);

        List<Demo> demos = new ArrayList<Demo>();


        //list  to list
        demos=list.stream().map(student ->
                new Demo( student.getAge(),student.getName())).collect(Collectors.toList());
        //遍历list
        demos.forEach(demo ->
                System.out.println("所有的demo为："+JSON.toJSONString(demo)));
        //只取年龄为18
        List<Demo> ageDemo = demos.stream().filter(demo -> demo.getAge().equals("18")).collect(Collectors.toList());
        ageDemo.forEach(demo ->
                System.out.println("所有年龄为18的demo为"+JSON.toJSONString(demo)));
        //选项年龄大于17的demo
        List<Demo> ageDemoBiger = demos.stream().filter(demo -> Integer.valueOf(demo.getAge()) > 17).collect(Collectors.toList());
        System.out.println("年龄大于17的demo为："+JSON.toJSONString(ageDemoBiger));
        //排序
        List<Demo> sortDemo = demos.stream().sorted((s1,s2) -> s1.getAge().compareTo(s2.getAge())).collect(Collectors.toList());
        System.out.println("排序后为："+JSON.toJSONString(sortDemo));
        //排序，无返回值
        demos.sort(comparing(Demo::getAge));
        System.out.println("排序后为："+JSON.toJSONString(demos));

        System.out.println(JSON.toJSONString(demos));





    }

    public void testListNum(){
        String  filters = "offlineRiderFilter,\n" +
                "                riderWithImportantOrderFilter,\n" +
                "                importantOrderRiderLevelFilter,\n" +
                "                normalRiderLevelFilter,\n" +
                "                contractOrderFilter,\n" +
                "                riderTagFilter,\n" +
                "                switchOrderFilter,\n" +
                "                takeModeFilter,\n" +
                "                orderCellingFilter,\n" +
                "                replaceOrderExcludeSwitchRiderFilter,\n" +
                "                riderUnAuditedFilter,\n" +
                "                riderDepositGrabFilter,\n" +
                "                riderRiskTagFilter,\n" +
                "                byWayOrderDistanceFilter,\n" +
                "                notFreeRiderFilter";
        List<String> riderTradeList = Arrays.asList(filters.split(","));
        System.out.println(riderTradeList.size());

    }
public  void test22(){
        String name ="实时抢单单量，实时派单单量，实时新增总单量，" +
                "每秒派单数，实时未派出订单数，平均每轮找人次数，" +
                "非空闲骑手平均新增距离，空闲骑手选中比例，派单骑手平均并单数量，" +
                "派单骑手平均整体顺路度，区域外派单比例，离店派单比例，同店同址比例，平均命中骑手数，" +
                "实时触发兜底强推荐的数量，实时未派出驻店订单的数量，实时未派出半定向订单的数量，兜底强推荐的接单量，" +
                "饿了么降级单比例，困难单单量占比，命中0人轮数比例，派单成功订单平均派单次数";
        List<String> namelist = Arrays.asList(name.split("，"));
    System.out.println(JSON.toJSONString(namelist));


}


    public void test2(){
        String string= "[\n" +
                "    {\n" +
                "      \"item_name\": \"米饭\",\n" +
                "      \"quantity\": 1,\n" +
                "      \"unit\": \"份\",\n" +
                "      \"unit_price\": 250\n" +
                "    },\n" +
                "    {\n" +
                "      \"item_name\": \"酸辣藕丁\",\n" +
                "      \"quantity\": 1,\n" +
                "      \"unit\": \"份\",\n" +
                "      \"unit_price\": 800\n" +
                "    },\n" +
                "    {\n" +
                "      \"item_name\": \"糖醋里脊\",\n" +
                "      \"quantity\": 1,\n" +
                "      \"unit\": \"份\",\n" +
                "      \"unit_price\": 1400\n" +
                "    }\n" +
                "  ]";
        JSON json= JSON.parseArray(string);
        String string1= json.toString();
        String string2= json.toString();
        logger.info(string2);
        logger.info(json.toJSONString());


    }



}
