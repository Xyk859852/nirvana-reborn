package com.phoenix.nirvana.order;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
import org.apache.ibatis.annotations.Mapper;

import java.util.Collections;

/**
 * @author xuyongkang
 * @version 1.0
 * @description: TODO
 * @date 2022/9/13 3:40 PM
 */
public class Generator {

    //    /**
//     * <p>
//     * 读取控制台内容
//     * </p>
//     */
//    public static String scanner(String tip) {
//        Scanner scanner = new Scanner(System.in);
//        StringBuilder help = new StringBuilder();
//        help.append("请输入" + tip + "：");
//        System.out.println(help.toString());
//        if (scanner.hasNext()) {
//            String ipt = scanner.next();
//            if (StringUtils.isNotEmpty(ipt)) {
//                return ipt;
//            }
//        }
//        throw new MybatisPlusException("请输入正确的" + tip + "！");
//    }

    public static void main(String[] args) {
        String projectPath = System.getProperty("user.dir");

        // 全局配置
        GlobalConfig gc = new GlobalConfig.Builder()
                .author("xuyongkang")
                .enableSwagger()
                .fileOverride()
                .outputDir(projectPath + "/nirvana-module-mall/mall-order/mall-order-biz/src/main/java")
                .dateType(DateType.ONLY_DATE)
                .build();

        // 包配置
        PackageConfig pc = new PackageConfig.Builder()
                .parent("com.phoenix.nirvana.order")
                .controller("controller")
                .service("service")
                .serviceImpl("service.impl")
                .mapper("dal.mysql.mapper")
                .entity("dal.mysql.dataobject")
                .pathInfo(Collections.singletonMap(OutputFile.xml, projectPath + "/nirvana-module-mall/mall-order/mall-order-biz/src/main/resources/mapper"))
                .build();


        // 策略配置
        StrategyConfig strategy = new StrategyConfig.Builder()
                .addInclude("product_order_discount")
                .enableCapitalMode()
                .entityBuilder()
                .formatFileName("%sDO")
//                .superClass(TenantBaseDO.class)
                .enableLombok()
                .enableFileOverride()
                .idType(IdType.AUTO)
                .columnNaming(NamingStrategy.underline_to_camel)
                .naming(NamingStrategy.underline_to_camel)
                .enableTableFieldAnnotation()
                .serviceBuilder()
                .formatServiceFileName("%sService")
                .enableFileOverride()
                .formatServiceImplFileName("%sServiceImpl")
                .mapperBuilder()
                .mapperAnnotation(Mapper.class)
                .enableFileOverride()
                .enableFileOverride()
                .formatMapperFileName("%sMapper")
                .formatXmlFileName("%sMapper")
                .controllerBuilder().enableRestStyle()
                .build();

        // 代码生成器
        AutoGenerator mpg = new AutoGenerator(new DataSourceConfig
                .Builder("jdbc:mysql://localhost:3306/nirvana_reborn?useSSL=false&allowPublicKeyRetrieval=true&useUnicode=true&characterEncoding=UTF-8&serverTimezone=CTT"
                , "root", "12345678")
                .build())
                .global(gc)
                .packageInfo(pc)
                .strategy(strategy);
        VelocityTemplateEngine v = new VelocityTemplateEngine();
        v.templateFilePath("/templates/mapper.xml.vm");
        mpg.execute(v);


    }

}
