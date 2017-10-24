package bankpaaaa;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.*;

import com.mysql.jdbc.ResultSet;

import java.sql.SQLException;
import java.util.Vector;

public class AccountList extends JFrame implements ActionListener{
  DBManager db = new DBManager();
  ResultSet rs;
  Container c;
  JFrame AcList = new JFrame("账户列表");
  JButton home = new JButton("返回");
  JTable table;
  JPanel panel1;
  JLabel backlabel, upper;
  DefaultTableModel defaultModel = null;
  public AccountList() {
	ImageIcon background;
    background = new ImageIcon("src/AcList.jpg");
    backlabel = new JLabel(background);
    backlabel.setBounds(0, 0, background.getIconWidth(),background.getIconHeight());	
        
    AcList.setLocation(MainFrame.Locatewidth, MainFrame.Locateheight);
    AcList.setUndecorated(true);
    AcList.setSize(1120, 630);
    AcList.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    upper= new JLabel(MainFrame.privelege+" "+MainFrame.card, JLabel.LEFT);
    upper.setBounds(5, 0, 300, 25);
    upper.setFont(new Font("微软雅黑",Font.ITALIC,16));
    upper.setForeground(Color.WHITE);
    
    c = AcList.getContentPane();
    c.setLayout(null);
    panel1 = new JPanel();
    panel1.setSize(1120, 630);
    panel1.setLocation(0, 0);
    panel1.setLayout(null);
    panel1.setOpaque(false);
    
    String infob = "";
    try {
    	String strSQL = "select balance from deposit where card='" + MainFrame.card + "'";
    	rs = (ResultSet) db.getResult(strSQL);
    	rs.last();
        infob = rs.getString("balance");
	} catch (SQLException e) {
		e.printStackTrace();
	}
    
    JLabel info1 = new JLabel("尊敬的"+MainFrame.privelege);
    info1.setBounds(55, 230, 300, 25);
    info1.setFont(new Font("微软雅黑",Font.BOLD,15));
    info1.setForeground(Color.WHITE);
    
    JLabel info2 = new JLabel(MainFrame.name+"，您好！");
    info2.setBounds(55, 260, 300, 25);
    info2.setFont(new Font("微软雅黑",Font.BOLD,15));
    info2.setForeground(Color.WHITE);
    
    JLabel info3 = new JLabel("余额："+infob);
    info3.setBounds(55, 290, 300, 25);
    info3.setFont(new Font("微软雅黑",Font.BOLD,15));
    info3.setForeground(Color.RED);
    panel1.add(info1);
    panel1.add(info2);
    panel1.add(info3);
    
    String[] name = {"卡号", "姓名", "权限","状态̬"};
    String[][] data = new String[0][0];
    defaultModel = new DefaultTableModel(data, name);
    table = new JTable (defaultModel) {   
    	public boolean isCellEditable(int row, int column) {     
    		return false;   
    		}  
    	};
    DefaultTableCellRenderer render = new DefaultTableCellRenderer();
    render.setOpaque(false);
    table.getColumnModel().getColumn(3).setMaxWidth(50);
    table.setRowHeight(30);
    table.setDefaultRenderer(Object.class, render);
    table.setShowGrid(false);
    table.setOpaque(false);
    table.getTableHeader().setFont(new Font("微软雅黑",Font.BOLD,16)); 
    table.getTableHeader().setOpaque(false);
    table.getTableHeader().setForeground(new Color(225,53,53));
    table.getTableHeader().setBackground(new Color(64,131,255));
    table.setFont(new Font("微软雅黑",Font.ITALIC,16)); 
    table.setForeground(new Color(100,81,40));
    JScrollPane s = new JScrollPane();
    s.getViewport().setOpaque(false);
    s.setOpaque(false);
    s.setViewportView(table);
    s.setColumnHeaderView(table.getTableHeader());
    s.getColumnHeader().setOpaque(false);
    s.setBorder(new CompoundBorder(new EmptyBorder(8, 8, 8, 8), new EmptyBorder(8, 8, 8, 8)));
    s.setBounds(400, 130, 550, 330);
    
    home.setBounds(965, 545, 110, 30);
    home.addActionListener(this);
    home.setOpaque(false);
    home.setFont(new Font("微软雅黑",Font.PLAIN,18));
    home.setForeground(new Color(100,81,40));
    home.setContentAreaFilled(false);
    home.setFocusPainted(false);
    panel1.add(home);
    panel1.add(upper);
    panel1.add(s);
    c.add(panel1);
    c.add(backlabel);
    
    try {
      String strSql = "";
      if (MainFrame.privelege.equals("银行经理")) strSql = "select card,name,privelege,status from account";
      if (MainFrame.privelege.equals("银行职员")) strSql = "select card,name,privelege,status from account where privelege = '个人客户'";
      rs = (ResultSet) db.getResult(strSql);
      while (rs.next()) {
        Vector insertRow = new Vector();
        insertRow.addElement(rs.getString(1));
        insertRow.addElement(rs.getString(2));
        insertRow.addElement(rs.getString(3));
        insertRow.addElement(rs.getString(4));
        defaultModel.addRow(insertRow);
      }
      table.revalidate();
    }
    catch (SQLException sqle) {
      System.out.println(sqle.toString());
    }
    catch (Exception ex) {
      System.out.println(ex.toString());
    }
    
    AcList.setVisible(true);
  }
  @Override
  public void actionPerformed(ActionEvent e) {
	  if (e.getSource() == home){
		  AcList.dispose();
		  Select select = new Select();
	  }	
  }
}
