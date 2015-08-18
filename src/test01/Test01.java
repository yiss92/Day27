package test01;

import java.sql.*;
import java.util.Scanner;

public class Test01 {

	public static void main(String[] args) {

		Connection con = null;
		Statement stmt = null;
		
		// 1. ����̹� �ε�(���α׷� ����� 1ȸ)
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("����̹� ���� ����!");
			
			// 2. Ŀ�ؼ� ����(��ɾ� ���� ������ �����ؾ� �ϸ� ���α׷� ����� Ƚ���� 1ȸ�̻��ϼ� �� ����
			//203,236,209.81:3306/book_db
			String url = "jdbc:mysql://127.0.0.1:3306/book_db";       //127.0.0.1:3306/book_db";
			String db_id = "root";
			String dp_pw = "hanbit";

			con = DriverManager.getConnection(url, db_id, dp_pw);
			System.out.println("Ŀ�ؼ� ���� ����!");
			
			//3.Ŀ�ؼ��� �̿��Ͽ� Statement / / PreparedStatement ��ü ����
			stmt = con.createStatement();
			
			Scanner input = new Scanner(System.in);
			
			System.out.print("����:");
			String title= input.nextLine();
			System.out.print("���ǻ�:");
			String publisher = input.nextLine();
			System.out.print("����:");
			int price = input.nextInt();
			System.out.print("���࿬��:");
			int year = input.nextInt();
			
			//4.SQL�� �ۼ� �� ���� ����
			String sql ="insert into books(title, publisher, price, year)"
					+ "values('"+title+"','"+ publisher+"',"+price+","+year+")";
			//String sql = "delete from books where book_id=4";
			
			//4.2 excuteUpdate(): insert, update, delete ��ɾ� ���� -���
			int result = stmt.executeUpdate(sql);
			System.out.println("���� ���� �Ϸ�: " + result);
			input.close();

		} catch (ClassNotFoundException e) {
			System.out.println("����̹� �ε� ����");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
			if(stmt != null){
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(con != null){
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}

	}

}
