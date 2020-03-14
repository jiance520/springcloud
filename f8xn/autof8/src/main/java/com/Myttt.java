package com;

import com.utils.JdbcUtil;

public class Myttt {
    public static void main(String[] args) {
//        JdbcUtil jdbcUtil = new JdbcUtil("oracle.jdbc.driver.OracleDriver","jdbc:oracle:thin:@106.13.100.117:1521:helowin","qshop","qshop");
//        String sql = "select * from ADVERT where AID = 102";
        JdbcUtil jdbcUtil = new JdbcUtil();
        String sql = "SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.Columns WHERE table_name='t_user' AND table_schema='shiro'";
        Object object = jdbcUtil.executeUpdate(sql);
        System.out.println("-----object:"+object.toString());
    }
}
