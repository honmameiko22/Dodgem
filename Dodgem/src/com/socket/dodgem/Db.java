package com.socket.dodgem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Db {
	private String driverName = "com.mysql.jdbc.Driver"; //驱动名称
	private String DBName="os";
	private String DBUser="root";
	private String DBPassword="4400576";
	private String connUrl;
	private Connection conn;
	private Statement stmt;

	public Db() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		Class.forName(driverName).newInstance(); 
		connUrl = "jdbc:mysql://localhost/" + DBName + "?user=" + DBUser + "&password=" + DBPassword;  
		conn = DriverManager.getConnection(connUrl);  
        stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
	}
	
	public int CreateUsr(String myname, String pw) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
        System.out.println("create user...");

		String query_sql="select password from usr where name='"+myname+"'" ;
        ResultSet rs= stmt.executeQuery(query_sql); 
        int flag=0;
        while(rs.next()){
        	flag=1;
        	break;
        }
        System.out.println("flag="+flag);
        //不存在该用户，则存入数据库
        if(flag==0){
        	 String insert_sql = "insert into usr values('"+myname+"','"+pw+"')";
        	 stmt.execute(insert_sql);  
        }
      	conn.close();
    	stmt.close();
    //	rs.close();
        return flag;
	}
	public int login(String myname, String pw)throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
        System.out.println("finding user...");
		
		String sql = "select password from usr where name='"+myname+"'" ;//拼接进去
		ResultSet rs = stmt.executeQuery(sql);  //查询语句
		System.out.println("1n");
		int flag=-1;
		 while(rs.next()){
			 flag=0;
			  if(rs.getString("password").equals(pw)) flag=1;
		 }
	
		conn.close(); 
		stmt.close();
		return flag;
	}
}
