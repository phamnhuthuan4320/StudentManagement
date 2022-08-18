import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.Scanner;
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
public class Chucnang {
/**************************************************************************************************************************************************************/	
	Scanner kb = new Scanner(System.in);
/**************************************************************************************************************************************************************/
	//Xay dung
	public Chucnang(){
		 
	}/////xay dung
/**************************************************************************************************************************************************************/	
	//Xem danh sach sinh vien
	public void sp_XemDANHSACH() {
		
		Connection conn=null;
		try{
	           Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
	           conn=(Connection)DriverManager.getConnection("jdbc:mysql://127.0.0.1/QUANLISINHVIEN?"+"user=root&password=root");
	           //System.out.println("Noi ket thanh cong");
	           
			try{
				CallableStatement cStmt = null;
			
			        cStmt = conn.prepareCall("{CALL sp_XemDANHSACH()}");
			        ResultSet rs = cStmt.executeQuery();
			        
			        System.out.println("---DANH SACH SINH VIEN---");
			        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			        System.out.println("| STT | MSSV         | HO VA TEN           | GIOITINH  | NGAY SINH      | NOI SINH       | DIA CHI                                                | TEN KHOA                                 |");
			        System.out.println("|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|");
			        int i = 1;
			        while (rs.next()) {
				        String mssv = rs.getString("mssv");
				        String hoten = rs.getString("hoTen");
				        String gioiTinh = rs.getString("gioiTinh");
				        String ngaySinh = rs.getString("ngaySinh");
				        String noiSinh = rs.getString("noiSinh");
				        String diaChi = rs.getString("diaChi");
				        String tenKhoa = rs.getString("tenKhoa");
				        System.out.printf("| %-4s| %-13s| %-20s| %-10s| %-15s| %-15s| %-55s| %-40s |\n",i,mssv,hoten,gioiTinh,ngaySinh,noiSinh,diaChi,tenKhoa);
				        i++;
				        }
				        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
			}//try 
			catch( SQLException e ){
				System.out.println("LOI XEM DANH SACH!!!");
				System.out.println("SQLError:" + e.getMessage() + "\n");
			}//catch
			
		}//try ket noi
		catch(Exception e){
	           //System.out.println("Khong the noi ket");
	           System.out.println("Error:" + e.getMessage());
		}//catch ket noi
		
	}//xem danh sach
/**************************************************************************************************************************************************************/
	//Kiem tra MSSV
	public int kTraMSSV(String MSSV) {
		
		int n=0;
		
		Connection conn=null;
		try{
	           Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
	           conn=(Connection)DriverManager.getConnection("jdbc:mysql://127.0.0.1/QUANLISINHVIEN?"+"user=root&password=root");
	           //System.out.println("Noi ket thanh cong");
	           
	           try{
	        	   PreparedStatement pStmt = null;
	 
	        	   pStmt = conn.prepareStatement("SELECT mssv FROM sinhVien");
	        	   ResultSet rs = pStmt.executeQuery();
	        	   
	        	   while (rs.next()) {
	        		   if(MSSV.equals(rs.getString("mssv")))	n++;
	        		   //System.out.println("mssv: " + rs.getString("mssv"));
	        	   }
	           }//try
	           catch( SQLException e) {
	        	   System.out.println("LOI KIEM TRA MSSV!!!");
	        	   System.out.println("SQLError: " + e.getMessage() + "\n");
	           }//catch
	        	   
		}//try ket noi
		catch(Exception e){
	           //System.out.println("Khong the noi ket");
	           System.out.println("Error:" + e.getMessage());
		}//catch ket noi
		
	return n;
	}//kiem tra mssv
/**************************************************************************************************************************************************************/
	//Tim kiem sinh vien
	public void sp_TimKiemSINHVIEN(String MSSV) {
		
		Connection conn=null;
		try{
	           Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
	           conn=(Connection)DriverManager.getConnection("jdbc:mysql://127.0.0.1/QUANLISINHVIEN?"+"user=root&password=root");
	           //System.out.println("Noi ket thanh cong");
		
			try{
	       		CallableStatement cStmt = null;
	       		
	               cStmt = conn.prepareCall("{CALL sp_TimKiemSINHVIEN(?)}");
	               cStmt.setString(1, MSSV);
	               ResultSet rs = cStmt.executeQuery();
	               
	               System.out.println("---THONG TIN SINH VIEN---");
	               System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
	               System.out.println("| MSSV         | HO VA TEN           | GIOITINH  | NGAY SINH      | NOI SINH       | DIA CHI                                                | TEN KHOA                                 |");
	               System.out.println("|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|");
	               while (rs.next()) {
		               String mssv = rs.getString("mssv");
		               String hoten = rs.getString("hoTen");
		               String gioiTinh = rs.getString("gioiTinh");
		               String ngaySinh = rs.getString("ngaySinh");
		               String noiSinh = rs.getString("noiSinh");
		               String diaChi = rs.getString("diaChi");
		               String tenKhoa = rs.getString("tenKhoa");
		               System.out.printf("| %-13s| %-20s| %-10s| %-15s| %-15s| %-55s| %-40s |\n",mssv,hoten,gioiTinh,ngaySinh,noiSinh,diaChi,tenKhoa);
	               }
	               System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
	       		}//try 
	       		catch( SQLException e ){
	       			System.out.println("LOI TIM KIEM SINH VIEN!!!");
	       			System.out.println("SQLError:" + e.getMessage() + "\n");
	       		}//catch
			
		}//try ket noi
		catch(Exception e){
	           //System.out.println("Khong the noi ket");
	           System.out.println("Error:" + e.getMessage());
		}//catch ket noi
		
	}//tim kiem sinh vien
/**************************************************************************************************************************************************************/	
	//Them sinh vien
	public void sp_ThemSINHVIEN(String	MSSV, String HOTEN, String GIOITINH, String NGAYSINH, String NOISINH, String DIACHI, String TENKHOA) {
		
		Connection conn=null;
		try{
	           Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
	           conn=(Connection)DriverManager.getConnection("jdbc:mysql://127.0.0.1/QUANLISINHVIEN?"+"user=root&password=root");
	           //System.out.println("Noi ket thanh cong");
		
			try{
	       		CallableStatement cStmt = null;
	       		
	               cStmt = conn.prepareCall("{CALL sp_ThemSINHVIEN(?,?,?,?,?,?,?)}");
	               cStmt.setString(1, MSSV);
	               cStmt.setString(2, HOTEN);
	               cStmt.setString(3, GIOITINH);
	               cStmt.setString(4, NGAYSINH);
	               cStmt.setString(5, NOISINH);
	               cStmt.setString(6, DIACHI);
	               cStmt.setString(7, TENKHOA);
	               cStmt.executeQuery();
	               
	               System.out.println("---THEM THANH CONG---\n");
	       		}//try 
	       		catch( SQLException e ){
	       			System.out.println("LOI THEM SINH VIEN!!!");
	       	        System.out.println("SQLError:" + e.getMessage() + "\n");
	       		}//catch
			
		}//try ket noi
		catch(Exception e){
	           //System.out.println("Khong the noi ket");
	           System.out.println("Error:" + e.getMessage());
		}//catch ket noi
		
	}//them sinh vien
/**************************************************************************************************************************************************************/
	//Chinh sua dia chi sinh vien
	public void sp_ChinhSuaDIACHI(String MSSV, String DIACHI) {
		
		Connection conn=null;
		try{
	           Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
	           conn=(Connection)DriverManager.getConnection("jdbc:mysql://127.0.0.1/QUANLISINHVIEN?"+"user=root&password=root");
	           //System.out.println("Noi ket thanh cong");
		
			try{
	       		CallableStatement cStmt = null;
	       		
	               cStmt = conn.prepareCall("{CALL sp_ChinhSuaDIACHI(?,?)}");
	               cStmt.setString(1, MSSV);
	               cStmt.setString(2, DIACHI);
	               cStmt.executeQuery();
	               
	               System.out.println("---CHINH SUA DIA CHI THANH CONG---\n");
	       		}//try 
	       		catch( SQLException e ){
	       			System.out.println("LOI CHINH SUA DIA CHI SINH VIEN!!!");
	       	        System.out.println("SQLError:" + e.getMessage() + "\n");
	       		}//catch
			
		}//try ket noi
		catch(Exception e){
	           //System.out.println("Khong the noi ket");
	           System.out.println("Error:" + e.getMessage());
		}//catch ket noi
		
	}//chinh sua dia chi sinh vien 
/**************************************************************************************************************************************************************/	
	//Xoa sinh vien
	public void sp_XoaSINHVIEN(String MSSV) {
		
		Connection conn=null;
		try{
	           Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
	           conn=(Connection)DriverManager.getConnection("jdbc:mysql://127.0.0.1/QUANLISINHVIEN?"+"user=root&password=root");
	           //System.out.println("Noi ket thanh cong");
		
			try{
	       		CallableStatement cStmt = null;
	       		
	               cStmt = conn.prepareCall("{CALL sp_XoaSINHVIEN(?)}");
	               cStmt.setString(1, MSSV);
	               cStmt.executeQuery();
	               
	               System.out.println("---XOA THONG TIN SINH VIEN THANH CONG---\n");
	       		}//try 
	       		catch( SQLException e ){
	       			System.out.println("LOI XOA SINH VIEN!!!");
	       	        System.out.println("SQLError:" + e.getMessage() + "\n");
	       		}//catch
			
		}//try ket noi
		catch(Exception e){
	           //System.out.println("Khong the noi ket");
	           System.out.println("Error:" + e.getMessage());
		}//catch ket noi
		
	}//xoa sinh vien
/**************************************************************************************************************************************************************/
	//Kiem tra MAHP
	public int kTraMAHP(String MAHP, String MSSV) {
			
		int n=0;
			
		Connection conn=null;
		try{
		    	Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
		        conn=(Connection)DriverManager.getConnection("jdbc:mysql://127.0.0.1/QUANLISINHVIEN?"+"user=root&password=root");
		        //System.out.println("Noi ket thanh cong");
		           
		        try{
		        	PreparedStatement pStmt = null;
		 
		        	pStmt = conn.prepareStatement("SELECT maHP FROM ketQua k WHERE k.mssv=?");
		        	pStmt.setString(1, MSSV);
		        	ResultSet rs = pStmt.executeQuery();
		        	   
		        	while (rs.next()) {
		        		if(MAHP.equals(rs.getString("maHP")))	n++;
		        		//System.out.println("maHP: " + rs.getString("maHP"));
		        	}
		        }//try
		        catch( SQLException e) {
		        	System.out.println("LOI KIEM TRA MA HOC PHAN!!!");
		        	System.out.println("SQLError: " + e.getMessage() + "\n");
		        }//catch
		        	   
		}//try ket noi
		catch(Exception e){
		     	//System.out.println("Khong the noi ket");
		        System.out.println("Error:" + e.getMessage());
		}//catch ket noi
			
	return n;
	}//kiem tra mahp
/**************************************************************************************************************************************************************/
	//Them mon hoc cua mot sinh vien
	public void sp_ThemMOMHOC(String MSSV, String MAHP) {
			
		Connection conn=null;
		try{
		        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
		        conn=(Connection)DriverManager.getConnection("jdbc:mysql://127.0.0.1/QUANLISINHVIEN?"+"user=root&password=root");
		        //System.out.println("Noi ket thanh cong");
			
			try{
		       	CallableStatement cStmt = null;
		       		
		            cStmt = conn.prepareCall("{CALL sp_ThemMOMHOC(?,?)}");
		            cStmt.setString(1, MSSV);
		            cStmt.setString(2, MAHP);
		            cStmt.executeQuery();
		               
		            System.out.println("---THEM MON HOC THANH CONG---\n");
		       	}//try 
		       	catch( SQLException e ){
		       		System.out.println("LOI THEM MON HOC CUA SINH VIEN!!!");
		       	    System.out.println("SQLError:" + e.getMessage() + "\n");
		       	}//catch
				
		}//try ket noi
		catch(Exception e){
		       	//System.out.println("Khong the noi ket");
		        System.out.println("Error:" + e.getMessage());
		}//catch ket noi
			
	}//them mon hoc cua mot sinh vien
/**************************************************************************************************************************************************************/
	//Chinh sua diem cua mot sinh vien
	public void sp_ChinhSuaDIEM(String MSSV, String MAHP, Float DIEM) {
		
		Connection conn=null;
		try{
		        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
		        conn=(Connection)DriverManager.getConnection("jdbc:mysql://127.0.0.1/QUANLISINHVIEN?"+"user=root&password=root");
		        //System.out.println("Noi ket thanh cong");
			
			try{
		       	CallableStatement cStmt = null;
		       		
		            cStmt = conn.prepareCall("{CALL sp_ChinhSuaDIEM(?,?,?)}");
		            cStmt.setString(1, MSSV);
		            cStmt.setString(2, MAHP);
		            cStmt.setFloat(3, DIEM);
		            cStmt.executeQuery();
		               
		            System.out.println("---CHINH SUA DIEM MON HOC THANH CONG---\n");
		       	}//try 
		       	catch( SQLException e ){
		       		System.out.println("LOI CHINH SUA DIEM MON HOC CUA SINH VIEN!!!");
		       	    System.out.println("SQLError:" + e.getMessage() + "\n");
		       	}//catch
				
		}//try ket noi
		catch(Exception e){
		       	//System.out.println("Khong the noi ket");
		        System.out.println("Error:" + e.getMessage());
		}//catch ket noi
			
	}//chinh sua diem cua mot sinh vien
/**************************************************************************************************************************************************************/
	//Xoa mon hoc cua mot sinh vien
	public void sp_XoaMONHOC(String MSSV, String MAHP) {
		
		Connection conn=null;
		try{
		        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
		        conn=(Connection)DriverManager.getConnection("jdbc:mysql://127.0.0.1/QUANLISINHVIEN?"+"user=root&password=root");
		        //System.out.println("Noi ket thanh cong");
			
			try{
		       	CallableStatement cStmt = null;
		       		
		            cStmt = conn.prepareCall("{CALL sp_XoaMONHOC(?,?)}");
		            cStmt.setString(1, MSSV);
		            cStmt.setString(2, MAHP);
		            cStmt.executeQuery();
		               
		            System.out.println("---XOA MON HOC THANH CONG---\n");
		       	}//try 
		       	catch( SQLException e ){
		       		System.out.println("LOI XOA MON HOC CUA SINH VIEN!!!");
		       	    System.out.println("SQLError:" + e.getMessage() + "\n");
		       	}//catch
				
		}//try ket noi
		catch(Exception e){
		       	//System.out.println("Khong the noi ket");
		        System.out.println("Error:" + e.getMessage());
		}//catch ket noi
			
	}//xoa mon hoc cua mot sinh vien
/**************************************************************************************************************************************************************/
	//Xem bang diem cua mot sinh vien
	public void sp_BANGDIEM( String MSSV) {
		
		Connection conn=null;
		try{
		        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
		        conn=(Connection)DriverManager.getConnection("jdbc:mysql://127.0.0.1/QUANLISINHVIEN?"+"user=root&password=root");
		        //System.out.println("Noi ket thanh cong");
			
			try{
				PreparedStatement pStmt = null;
		   		 
	        		pStmt = conn.prepareStatement("SELECT s.mssv,s.hoTen,k.tenKhoa FROM sinhVien s, khoa k WHERE s.maKhoa=k.maKhoa and s.mssv=?");
	        		pStmt.setString(1, MSSV);
	        		ResultSet rss = pStmt.executeQuery();
	        		
	        		while(rss.next()) {
	        			System.out.println("---BANG DIEM CUA SINH VIEN---");
			            String mssv = rss.getString("mssv");
				        String hoten = rss.getString("hoTen");
				        String tenKhoa = rss.getString("tenKhoa");
				        System.out.println("------------------------------------------------------------------------------------------------");
				        System.out.println("| MSSV: " + mssv + " | HO VA TEN: " + hoten + " | TEN KHOA: " + tenKhoa + " |");
				        System.out.println("------------------------------------------------------------------------------------------------\n");
	        		}
	        		
		       	CallableStatement cStmt = null;
		       		
		            cStmt = conn.prepareCall("{CALL sp_BANGDIEM(?)}");
		            cStmt.setString(1, MSSV);
		            ResultSet rs = cStmt.executeQuery();
		            
		            System.out.println("----------------------------------------------------------------");
			        System.out.println("| STT | MA HOC PHAN    | TEN HOC PHAN                  | DIEM  |");
			        System.out.println("|--------------------------------------------------------------|");
			        int i = 1;
			        while (rs.next()) {
				        String maHP = rs.getString("maHP");
				        String tenHP = rs.getString("tenHP");
				        Float diem = rs.getFloat("diem");
				        System.out.printf("| %-4d| %-15s| %-30s| %-5.2f |\n",i,maHP,tenHP,diem);
				        i++;
			        }
			        System.out.println("----------------------------------------------------------------\n");
		       	}//try 
		       	catch( SQLException e ){
		       		System.out.println("LOI XEM BANG DIEM CUA SINH VIEN!!!");
		       	    System.out.println("SQLError:" + e.getMessage() + "\n");
		       	}//catch
				
		}//try ket noi
		catch(Exception e){
		       	//System.out.println("Khong the noi ket");
		        System.out.println("Error:" + e.getMessage());
		}//catch ket noi
			
	}//xem bang diem cua mot sinh vien
/**************************************************************************************************************************************************************/
	//Xem diem trung binh cua mot sinh vien
	public void fn_DiemTB( String MSSV) {
		
		Connection conn=null;
		try{
		        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
		        conn=(Connection)DriverManager.getConnection("jdbc:mysql://127.0.0.1/QUANLISINHVIEN?"+"user=root&password=root");
		        //System.out.println("Noi ket thanh cong");
			
			try{
				PreparedStatement pStmt = null;
		   		 
        		pStmt = conn.prepareStatement("SELECT mssv,hoTen FROM sinhVien s WHERE s.mssv=?");
        		pStmt.setString(1, MSSV);
        		ResultSet rss = pStmt.executeQuery();
        		
        		while(rss.next()) {
        			System.out.println("---DIEM TRUNG BINH CUA SINH VIEN---");
		            String mssv = rss.getString("mssv");
			        String hoten = rss.getString("hoTen");
			        System.out.println("-------------------------------------------------");
			        System.out.println("| MSSV: " + mssv + " | HO VA TEN: " + hoten + " |");
			        System.out.println("-------------------------------------------------");
        		}
				
		       	CallableStatement cStmt = null;
		       	
		            cStmt = conn.prepareCall("{? = CALL fn_DiemTB(?)}");
		            cStmt.setString(2, MSSV);
		            ResultSet rs = cStmt.executeQuery();
		               
		            while(rs.next()) {
		            	Float diemTB = rs.getFloat(1);
	        			System.out.println("->DIEM TRUNG BINH CUA SINH VIEN: " + diemTB);
	        			System.out.println("");
	        		}
		       	}//try 
		       	catch( SQLException e ){
		       		System.out.println("LOI XEM DIEM TRUNG BINH CUA SINH VIEN!!!");
		       	    System.out.println("SQLError:" + e.getMessage() + "\n");
		       	}//catch
				
		}//try ket noi
		catch(Exception e){
		       	//System.out.println("Khong the noi ket");
		        System.out.println("Error:" + e.getMessage());
		}//catch ket noi
			
	}//xem diem trung binh cua mot sinh vien
/**************************************************************************************************************************************************************/
	//Xet dieu kien tot nghiep cua sinh vien
	public void fn_DuDieuKienTN( String MSSV) {
		
		Connection conn=null;
		try{
		        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
		        conn=(Connection)DriverManager.getConnection("jdbc:mysql://127.0.0.1/QUANLISINHVIEN?"+"user=root&password=root");
		        //System.out.println("Noi ket thanh cong");
			
			try{
				PreparedStatement pStmt = null;
		   		 
        		pStmt = conn.prepareStatement("SELECT mssv,hoTen FROM sinhVien s WHERE s.mssv=?");
        		pStmt.setString(1, MSSV);
        		ResultSet rss = pStmt.executeQuery();
        		
        		while(rss.next()) {
        			System.out.println("---XET DIEU KIEN TOT NGHIEP CUA SINH VIEN---");
		            String mssv = rss.getString("mssv");
			        String hoten = rss.getString("hoTen");
			        System.out.println("-------------------------------------------------");
			        System.out.println("| MSSV: " + mssv + " | HO VA TEN: " + hoten + " |");
			        System.out.println("-------------------------------------------------");
        		}
				
		       	CallableStatement cStmt = null;
		       		
		            cStmt = conn.prepareCall("{? = CALL fn_DuDieuKienTN(?)}");
		            cStmt.setString(2, MSSV);
		            ResultSet rs = cStmt.executeQuery();
		               
		            while(rs.next()) {
		            int TN = rs.getInt(1);
	        			if(TN == 1)	{
	        				System.out.println("->SINH VIEN DU DIEU KIEN TOT NGHIEP");
	        				System.out.println("");
	        			}
	        			else {
	        				System.out.println("->SINH VIEN KHONG DU DIEU KIEN TOT NGHIEP");
	        				System.out.println("");
	        			}
		            }
		       	}//try 
		       	catch( SQLException e ){
		       		System.out.println("LOI XET DIEU KIEN TOT NGHIEP CUA SINH VIEN!!!");
		       	    System.out.println("SQLError:" + e.getMessage() + "\n");
		       	}//catch
				
		}//try ket noi
		catch(Exception e){
		       	//System.out.println("Khong the noi ket");
		        System.out.println("Error:" + e.getMessage());
		}//catch ket noi
			
	}//xet dieu kien tot nghiep cua sinh vien
/**************************************************************************************************************************************************************/
	//Xem loai tot nghiep cua sinh vien
	public void fn_LoaiTN( String MSSV) {
		
		Connection conn=null;
		try{
		        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
		        conn=(Connection)DriverManager.getConnection("jdbc:mysql://127.0.0.1/QUANLISINHVIEN?"+"user=root&password=root");
		        //System.out.println("Noi ket thanh cong");
			
			try{
				PreparedStatement pStmt = null;
		   		 
        		pStmt = conn.prepareStatement("SELECT mssv,hoTen FROM sinhVien s WHERE s.mssv=?");
        		pStmt.setString(1, MSSV);
        		ResultSet rss = pStmt.executeQuery();
        		
        		while(rss.next()) {
        			System.out.println("---LOAI TOT NGHIEP CUA SINH VIEN---");
		            String mssv = rss.getString("mssv");
			        String hoten = rss.getString("hoTen");
			        System.out.println("-------------------------------------------------");
			        System.out.println("| MSSV: " + mssv + " | HO VA TEN: " + hoten + " |");
			        System.out.println("-------------------------------------------------");
        		}
				
		       	CallableStatement cStmt = null;
		       
		            cStmt = conn.prepareCall("{? = CALL fn_LoaiTN(?)}");
		            cStmt.setString(2, MSSV);
		            ResultSet rs = cStmt.executeQuery();
		               
		            while(rs.next()) {
		            String loaiTN = rs.getString(1);
	        			System.out.println("SINH VIEN TOT NGHIEP LOAI: " + loaiTN);
	        			System.out.println("");
		            }
		       	}//try 
		       	catch( SQLException e ){
		       		System.out.println("LOI XEM LOAI TOT NGHIEP CUA SINH VIEN!!!");
		       	    System.out.println("SQLError:" + e.getMessage() + "\n");
		       	}//catch
				
		}//try ket noi
		catch(Exception e){
		       	//System.out.println("Khong the noi ket");
		        System.out.println("Error:" + e.getMessage());
		}//catch ket noi
			
	}//xem loai tot nghiep cua sinh vien
/**************************************************************************************************************************************************************/
	//Chon chuc nang
		public int chonChucNang() {
			System.out.println("CAC CHUC NANG:\n"
								+ "0: THOAT RA MAN HINH DANG NHAP\n"
								+ "1: XEM DANH SACH SINH VIEN\n"
								+ "2: TIM KIEM SINH VIEN\n"
								+ "3: THEM SINH VIEN\n"
								+ "4: CHINH SUA DIA CHI CUA SINH VIEN\n"
								+ "5: XOA SINH VIEN\n"
								+ "6: XEM BANG DIEM CUA MOT SINH VIEN\n"
								+ "7: THEM MON HOC CUA MOT SINH VIEN\n"
								+ "8: CHINH SUA DIEM MOT MON HOC CUA MOT SINH VIEN\n"
								+ "9: XOA MON HOC CUA MOT SINH VIEN\n"
								+ "10: XEM DIEM TRUNG BINH CUA MOT SINH VIEN\n"
								+ "11: XET DIEU KIEN TOT NGHIEP CUA MOT SINH VIEN\n"
								+ "12: XEM LOAI TOT NGHIEP CUA MOT SINH VIEN\n"
			);
			
			int x;
			do {
				System.out.print("CHON: ");
				x = kb.nextInt();
				if(x<0 || x>12)	System.out.println("NHAP SAI!!! NHAP LAI!\n");
			} while(x<0 || x>12);
			
		return x;
		}//chon chuc nang
/**************************************************************************************************************************************************************/
		//Chay chuc nang
		public void chayChucNang() {
			
			int chonCN = chonChucNang();
	
				if(chonCN != 0) {
					while(chonCN >= 1 && chonCN <= 12) {
						switch(chonCN){
						case 1: {
						    sp_XemDANHSACH();
						    System.out.println("CHON CHUC NANG TIEP THEO!"); chonCN = chonChucNang();
						    break;
						}//case 1
						
						case 2: {
							System.out.print("NHAP MA SO SINH VIEN( MSSV ): ");
							kb.nextLine();
							String MSSV = kb.nextLine();
							if(kTraMSSV(MSSV) == 0) {
								System.out.println("SAI MSSV!( MSSV KHONG CO TRONG DANH SACH )\n");
								System.out.println("CHON CHUC NANG TIEP THEO!"); chonCN = chonChucNang();
							}
							else {
								sp_TimKiemSINHVIEN(MSSV);
								System.out.println("CHON CHUC NANG TIEP THEO!"); chonCN = chonChucNang();
							}
							break;
						}//case 2
						
						case 3:{
							System.out.print("NHAP MA SO SINH VIEN( VI DU: B1809188): ");
							kb.nextLine();
							String MSSV = kb.nextLine();
							if(kTraMSSV(MSSV) == 0) {
								System.out.print("NHAP HO VA TEN SINH VIEN( VI DU: PHAM NHU THUAN): "); String HOTEN = kb.nextLine();
								System.out.print("NHAP GIOI TINH SINH VIEN( VI DU: NAM/NU): "); String GIOITINH = kb.nextLine();
								System.out.print("NHAP NGAY SINH SINH VIEN( VI DU: 2000-03-04): "); String NGAYSINH = kb.nextLine();
								System.out.print("NHAP NOI SINH SINH VIEN( VI DU: VINH LONG): "); String NOISINH = kb.nextLine();
								System.out.print("NHAP DIA CHI SINH VIEN( VI DU: TRAN QUANG DIEU, BINH THUY, CAN THO): "); String DIACHI = kb.nextLine();
								System.out.print("NHAP TEN KHOA SINH VIEN( VI DU: CONG NGHE THONG TIN VA TRUYEN THONG): "); String TENKHOA = kb.nextLine();
								sp_ThemSINHVIEN(MSSV, HOTEN, GIOITINH, NGAYSINH, NOISINH, DIACHI, TENKHOA);
								System.out.println("CHON CHUC NANG TIEP THEO!"); chonCN = chonChucNang();
							}
							else {
								System.out.println("LOI!( MSSV DA CO TRONG DANH SACH )\n");
								System.out.println("CHON CHUC NANG TIEP THEO!"); chonCN = chonChucNang();
							}
							break;
						}//case 3
						
						case 4:{
							System.out.print("NHAP MA SO SINH VIEN( VI DU: B1809188): ");
							kb.nextLine();
							String MSSV = kb.nextLine();
							if(kTraMSSV(MSSV) != 0) {
								System.out.print("NHAP DIA CHI MOI CUA SINH VIEN( VI DU: 287/13, TRAN QUANG DIEU): "); String DIACHI = kb.nextLine();
								sp_ChinhSuaDIACHI(MSSV,DIACHI);
								System.out.println("CHON CHUC NANG TIEP THEO!"); chonCN = chonChucNang();
							}
							else {
								System.out.println("LOI!( MSSV KHONG CO TRONG DANH SACH )\n");
								System.out.println("CHON CHUC NANG TIEP THEO!"); chonCN = chonChucNang();
							}
							break;
						}//case 4
						
						case 5:{
							System.out.print("NHAP MA SO SINH VIEN( VI DU: B1809188): ");
							kb.nextLine();
							String MSSV = kb.nextLine();
							if(kTraMSSV(MSSV) != 0) {
								sp_XoaSINHVIEN(MSSV);
								System.out.println("CHON CHUC NANG TIEP THEO!"); chonCN = chonChucNang();
							}
							else {
								System.out.println("LOI!( MSSV KHONG CO TRONG DANH SACH )\n");
								System.out.println("CHON CHUC NANG TIEP THEO!"); chonCN = chonChucNang();
							}
							break;
						}//case 5
						
						case 6:{
							System.out.print("NHAP MA SO SINH VIEN( VI DU: B1234567): ");
							kb.nextLine();
							String MSSV = kb.nextLine();
							if(kTraMSSV(MSSV) != 0) {
								sp_BANGDIEM(MSSV);
								System.out.println("CHON CHUC NANG TIEP THEO!"); chonCN = chonChucNang();
							}
							else {
								System.out.println("LOI!( MSSV KHONG CO TRONG DANH SACH )\n");
								System.out.println("CHON CHUC NANG TIEP THEO!"); chonCN = chonChucNang();
							}
							break;
						}//case 6
						
						case 7:{
							System.out.print("NHAP MA SO SINH VIEN( VI DU: B1234567): ");
							kb.nextLine();
							String MSSV = kb.nextLine();
							if(kTraMSSV(MSSV) != 0) {
								System.out.print("NHAP MA MON HOC( VI DU: SP012): "); String MAHP = kb.nextLine();
								if(kTraMAHP(MAHP,MSSV) == 0){
									sp_ThemMOMHOC(MSSV, MAHP);
									System.out.println("CHON CHUC NANG TIEP THEO!"); chonCN = chonChucNang();
								}
								else {
									System.out.println("LOI!( SINH VIEN DA HOC MON HOC NAY )\n");
									System.out.println("CHON CHUC NANG TIEP THEO!"); chonCN = chonChucNang();
								}
							}
							else {
								System.out.println("LOI!( MSSV KHONG CO TRONG DANH SACH )\n");
								System.out.println("CHON CHUC NANG TIEP THEO!"); chonCN = chonChucNang();
							}
							break;
						}//case 7
						
						case 8:{
							System.out.print("NHAP MA SO SINH VIEN( VI DU: B1234567): ");
							kb.nextLine();
							String MSSV = kb.nextLine();
							if(kTraMSSV(MSSV) != 0) {
								System.out.print("NHAP MA MON HOC( VI DU: SP012): "); String MAHP = kb.nextLine();
								if(kTraMAHP(MAHP,MSSV) != 0){
									System.out.print("NHAP DIEM MON HOC( VI DU: 0 <= DIEM <= 10): "); Float DIEM = kb.nextFloat();
									if(DIEM >= 0 || DIEM <= 10) {
										sp_ChinhSuaDIEM(MSSV,MAHP,DIEM);
										System.out.println("CHON CHUC NANG TIEP THEO!"); chonCN = chonChucNang();
									}
									else {
										System.out.println("LOI!( NHAP SAI GIA TRI DIEM: 0 <= DIEM <= 10 )\n ");
										System.out.println("CHON CHUC NANG TIEP THEO!"); chonCN = chonChucNang();
									}
								}
								else {
									System.out.println("LOI!( SINH VIEN KHONG HOC MON HOC NAY )\n");
									System.out.println("CHON CHUC NANG TIEP THEO!"); chonCN = chonChucNang();
								}
							}
							else {
								System.out.println("LOI!( MSSV KHONG CO TRONG DANH SACH )\n");
								System.out.println("CHON CHUC NANG TIEP THEO!"); chonCN = chonChucNang();
							}
							break;
						}//case 8
						
						case 9:{
							System.out.print("NHAP MA SO SINH VIEN( VI DU: B1234567): ");
							kb.nextLine();
							String MSSV = kb.nextLine();
							if(kTraMSSV(MSSV) != 0) {
								System.out.print("NHAP MA MON HOC( VI DU: SP012): "); String MAHP = kb.nextLine();
								if(kTraMAHP(MAHP,MSSV) != 0){
									sp_XoaMONHOC(MSSV, MAHP);
									System.out.println("CHON CHUC NANG TIEP THEO!"); chonCN = chonChucNang();
								}
								else {
									System.out.println("LOI!( SINH VIEN KHONG HOC MON HOC NAY )\n");
									System.out.println("CHON CHUC NANG TIEP THEO!"); chonCN = chonChucNang();
								}
							}
							else {
								System.out.println("LOI!( MSSV KHONG CO TRONG DANH SACH )\n");
								System.out.println("CHON CHUC NANG TIEP THEO!"); chonCN = chonChucNang();
							}
							break;
						}//case 9
						
						case 10:{
							System.out.print("NHAP MA SO SINH VIEN( VI DU: B1234567): ");
							kb.nextLine();
							String MSSV = kb.nextLine();
							if(kTraMSSV(MSSV) != 0) {
								fn_DiemTB(MSSV);
								System.out.println("CHON CHUC NANG TIEP THEO!"); chonCN = chonChucNang();
							}
							else {
								System.out.println("LOI!( MSSV KHONG CO TRONG DANH SACH )\n");
								System.out.println("CHON CHUC NANG TIEP THEO!"); chonCN = chonChucNang();
							}
							break;
						}//case 10
						
						case 11:{
							System.out.print("NHAP MA SO SINH VIEN( VI DU: B1234567): ");
							kb.nextLine();
							String MSSV = kb.nextLine();
							if(kTraMSSV(MSSV) != 0) {
								fn_DuDieuKienTN(MSSV);
								System.out.println("CHON CHUC NANG TIEP THEO!"); chonCN = chonChucNang();
							}
							else {
								System.out.println("LOI!( MSSV KHONG CO TRONG DANH SACH )\n");
								System.out.println("CHON CHUC NANG TIEP THEO!"); chonCN = chonChucNang();
							}
							break;
						}//case 11
						
						case 12:{
							System.out.print("NHAP MA SO SINH VIEN( VI DU: B1234567): ");
							kb.nextLine();
							String MSSV = kb.nextLine();
							if(kTraMSSV(MSSV) != 0) {
								fn_LoaiTN(MSSV);
								System.out.println("CHON CHUC NANG TIEP THEO!"); chonCN = chonChucNang();
							}
							else {
								System.out.println("LOI!( MSSV KHONG CO TRONG DANH SACH )\n");
								System.out.println("CHON CHUC NANG TIEP THEO!"); chonCN = chonChucNang();
							}
							break;
						}//case 12
						
						default: break;
						}//switch
					}//while
				}
				if(chonCN == 0) System.out.println("---DA THOAT RA MAN HINH DANG NHAP---\n");
			
		}//chay chuc nang
/**************************************************************************************************************************************************************/
		//Dang nhap
		public int dangNhap() {
			int n=0;
			String tk,mk;
			
			kb.nextLine();
			System.out.println("NHAP TAI KHOAN: ");	tk = kb.nextLine();
			System.out.println("NHAP MAT KHAU: ");	mk = kb.nextLine();
			
			if(tk.equals("root1") && mk.equals("root1") || tk.equals("root2") && mk.equals("root2"))
				n++;
		return n;
		
		}//dang nhap
/**************************************************************************************************************************************************************/
		//Chon dang nhap
		public int chonDangNhap() {
			System.out.println("CAC CHUC NANG:\n"
					+ "0: THOAT CHUONG TRINH\n"
					+ "1: DANG NHAP\n"
			);

			int x;
			do {
				System.out.print("CHON: ");
				x = kb.nextInt();
				if(x<0 || x>1)	System.out.println("NHAP SAI!!! NHAP LAI!\n");
			} while(x<0 || x>1);

		return x;
		}
/**************************************************************************************************************************************************************/
		//Chay dang nhap
		public void chayDangNhap() {
			
			int chonDN = chonDangNhap();
	
				while(chonDN == 1) {
					if( dangNhap() == 1) {
						System.out.println("");
						chayChucNang(); chonDN = chonDangNhap();
					}
					else {
						System.out.println("NHAP SAI TAI KHOAN HOAC MAT KHAU!!!\n");
						chonDN = chonDangNhap();
					}
				}
				
				if(chonDN == 0) System.out.print("---DA THOAT CHUONG TRINH---");
			
		}//chay chuc nang
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
}//class