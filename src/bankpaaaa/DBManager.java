package bankpaaaa;

import java.sql.*;

public class DBManager {
  Connection con;
  ResultSet rs;
  Statement stmt;

  public DBManager() {
    try {
    	final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
  	  final String DB_URL = "jdbc:mysql://localhost:3306/ibank?characterEncoding=utf-8";

  	  // ��ݿ���û��������룬��Ҫ����Լ�������
  	  final String USER = "root";
  	  final String PASS = "";
  	try {
		Class.forName(JDBC_DRIVER);
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    con = DriverManager.getConnection(DB_URL,USER,PASS);
	  
	  stmt = con.createStatement();
      System.out.println("ok!");
    }
    catch (SQLException sqle) {
      System.out.println(sqle.toString());
    }
  }

  public ResultSet getResult(String strSQL) {
    try {
    	  
      rs = stmt.executeQuery(strSQL);
      System.out.println(strSQL);
      return rs;
    }
    catch (SQLException sqle) {
      System.out.println(sqle.toString());
      return null;
    }
  }

  public int updateSql(String strSQL) {
    try {
      
    	con.setAutoCommit(false);
        
    	int i=stmt.executeUpdate(strSQL);
      
      con.commit();
      return i;
    }
    catch (SQLException sqle) {
      System.out.println(sqle.toString());
      return -1;
    }
  }

  public void closeConnection() {
    try {
    	con.close();      
    }
    catch (SQLException sqle) {
      System.out.println(sqle.toString());
    }
  }

}
