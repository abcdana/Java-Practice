package practice.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;


//자바의 주석
//1. 단일 라인 주석
/*
 2. 다중 라인 주석
 */
/**
 3. 자바 도큐먼트 주석
 */


//javadoc.ext - 자바 도큐먼트 생성 도구 -> 주석은 보통 public한테만 건다. private은 어차피 보이지도 않음


/**
 * DBUtil. 데이터베이스 연결 클래스입니다.
 * @author 김다은
 *
 */
public class DBUtil {
	
	private static Connection conn;
	
	/**
	 * 데이터 베이스 연결 메소드입니다.
	 * @return 	연결된 Connection 객체를 반환합니다.
	 */
	public static Connection open() {
		
		//자주 발생 오류 1. url에 오타가 났을 때,
		//java.sql.SQLException: 부적합한 Oracle URL이 지정되었습니다
		
		//자주 발생 오류 2. url에 @이후 호스트주소를 틀렸을 때,
		//java.sql.SQLRecoverableException: IO 오류: The Network Adapter could not establish the connection
		//java.net.ConnectException: Connection timed out: connect -> 타임아웃

		//자주 발생 오류 3. listener가 거절했다 => 9할은 오라클이 죽어있는것. 다시 살리면 됨(실행) 
		
		//자주 발생 오류 4. 포트번호가 틀렸을 때, 
		//java.net.ConnectException: Connection refused: connect

		//자주 발생 오류 5. 맨 마지막(xe)가 틀렸을 때, 
		//listener does not currently know of SID given in connect descriptor
		
		//-> 위에 발생하는 모든 오류메세지는 url 오타때문이다!!
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		
		//자주 발생 오류 6. id와 pw를 틀렸을 때,
		//ORA-01017: invalid username/password; logon denied
		String id = "hr";
		String pw = "java1234";
		
		try {
			
			//자주 발생 오류 7. 클래스명(패키지명)을 틀렸을 때,
			//java.lang.ClassNotFoundException:
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection(url, id, pw);
			
			return conn;
			
		} catch (Exception e) {
			System.out.println("DBUtil");
			e.printStackTrace();
		}
		
		
		return null;
		
	}
	
	/**
	 * 데이터 베이스 연결 메소드입니다.
	 * @param server 접속할 서버 주소입니다.
	 * @param id	 접속할 계정명입니다.
	 * @param pw	 접속할 비밀번호입니다.
	 * @return 		 연결된 Connection 객체를 반환합니다.
	 */
	public static Connection open(String server, String id, String pw) {
		
		String url = "jdbc:oracle:thin:@"+ server +":1521:xe";
		
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection(url, id, pw);
			
			return conn;
			
		} catch (Exception e) {
			System.out.println("DBUtil");
			e.printStackTrace();
		}
		
		
		return null;
		
	}

}
