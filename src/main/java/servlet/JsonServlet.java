package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/employee-json")
public class JsonServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String JSON_DATA = "{" + "\"manv\":\"TeoNV\"," + "\"hoTen\":\"Nguyễn Văn Tèo\","
			+ "\"gioiTinh\":true," + "\"luong\":950.5" + "}";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		// gửi chuỗi json về client
		resp.getWriter().write(JSON_DATA);
	}
}
