package com.example.demo.configs;

import org.hibernate.EmptyInterceptor;
import org.hibernate.Interceptor;

/**
 * Created by chenzhi
 * <p>
 * Date:2018/4/28
 * Time:19:14
 * 分库分表的拦截器，用于替换hibernate的表名
 */
public class DaoAccessInterceptor extends EmptyInterceptor {
    private String originTableName;// 目标母表名
    private String targetTableName;// 操作子表名

    //为其在spring好好利用 我们生成公用无参构造方法
    public DaoAccessInterceptor() {
    }

    public String onPrepareStatement(String sql) {
        sql = sql.replaceAll(originTableName, targetTableName);
        return sql;

    }
    public String getOriginTableName() {
        return originTableName;
    }

    public void setOriginTableName(String originTableName) {
        this.originTableName = originTableName;
    }

    public String getTargetTableName() {
        return targetTableName;
    }

    public void setTargetTableName(String targetTableName) {
        this.targetTableName = targetTableName;
    }

    public static Interceptor getInterceptor(String originTable, String targetTable) {
        DaoAccessInterceptor interceptor = new DaoAccessInterceptor();//我们的拦截器
        interceptor.setOriginTableName(originTable);//要拦截的目标表名,也就是需要分表的原始表名
        interceptor.setTargetTableName(targetTable);  //要替换的子表名
        return interceptor;
    }
}
