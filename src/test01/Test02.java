package test01;

import java.sql.*;
import java.util.Scanner;

public class Test02 {

	public static void main(String[] args) {

		Connection con = null;
		PreparedStatement pstmt = null;
		
		// 1. 드라이버 로딩(프로그램 실행시 1회)
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("드라이버 적제 성공!");
			
			// 2. 커넥션 생성(명령어 실행 이전에 생성해야 하며 프로그램 진행상 횟수는 1회이상일수 도 있음
			//203,236,209.81:3306/book_db
			String url = "jdbc:mysql://127.0.0.1:3306/book_db";       //127.0.0.1:3306/book_db";
			String db_id = "root";
			String dp_pw = "hanbit";

			con = DriverManager.getConnection(url, db_id, dp_pw);
			System.out.println("커넥션 생성 성공!");
			
			String sql ="insert into books(title, publisher, price, year)"
					+ "values(?,?,?,?)";
			
			//3.커넥션을 이용하여 Statement / / PreparedStatement 객체 생성
			pstmt = con.prepareStatement(sql);
			
			Scanner input = new Scanner(System.in);
			
			System.out.print("제목:");
			String title= input.nextLine();
			System.out.print("출판사:");
			String publisher = input.nextLine();
			System.out.print("가격:");
			int price = input.nextInt();
			System.out.print("발행연도:");
			String year = input.nextLine();
						
			//4.SQL문 작성 및 쿼리 실행
			pstmt.setString(1, title);
			pstmt.setString(2, publisher);
			pstmt.setInt(3, price);
			pstmt.setString(4, year);
			//String sql = "delete from books where book_id=4";
			
			//4.2 excuteUpdate(): insert, update, delete 명령어 실행 -결과
			int result = pstmt.executeUpdate();
			System.out.println("쿼리 실행 완료: " + result);

		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 오류");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
			if(pstmt != null){
				try {
					pstmt.close();
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
