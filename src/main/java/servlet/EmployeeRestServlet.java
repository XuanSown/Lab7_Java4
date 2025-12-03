package servlet;

import java.io.IOException;
import java.util.Map;

import dao.EmployeeDAO;
import dao.impl.EmployeeDAOImpl;
import entity.Employee;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import util.RestIO;

@WebServlet("/employees/*")
public class EmployeeRestServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private EmployeeDAO dao = new EmployeeDAOImpl();

	// lấy ID từ url
	private String getIdFromPath(HttpServletRequest req) {
		String info = req.getPathInfo();
		return (info != null && info.length() > 1) ? info.substring(1).trim() : null;
	}

	// GET: lấy danh sách or 1 nhân viên
	// /employees or /employees/ID
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = getIdFromPath(req);

		try {
			if (id == null) { // GET: /employees
				RestIO.writeObject(resp, dao.findAll());
			} else {// GET: /employees/ID
				Employee emp = dao.findById(id);
				if (emp != null) {
					RestIO.writeObject(resp, emp);
				} else {
					resp.setStatus(HttpServletResponse.SC_NOT_FOUND); // 404
					RestIO.writeEmptyOject(resp);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); // 500
			RestIO.writeObject(resp, Map.of("error", "Lỗi khi lấy dữ liệu nhân viên: " + e.getMessage()));
		}
	}

	// POST: thêm nhân viên mới
	// /employees
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			Employee employee = RestIO.readObject(req, Employee.class); // đọc json thành object
			dao.create(employee);
			RestIO.writeObject(resp, employee); // trả về đối tượng vừa thêm
		} catch (Exception e) {
			// TODO: handle exception
			resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); // 500
			RestIO.writeObject(resp, Map.of("error", "Lỗi khi thêm nhân viên: " + e.getMessage()));
		}
	}

	// PUT: cập nhật nhân viên
	// /employees/ID
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = getIdFromPath(req);
		if (id == null) {
			resp.setStatus(HttpServletResponse.SC_BAD_REQUEST); // 400 nếu thiếu id
			RestIO.writeEmptyOject(resp);
			return;
		}
		try {
			Employee employee = RestIO.readObject(req, Employee.class);
			employee.setId(id); // đảm bảo ID đối tượng khớp với ID trong URL
			dao.update(employee);
			RestIO.writeEmptyOject(resp);
		} catch (Exception e) {
			// TODO: handle exception
			resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); // 500
			RestIO.writeObject(resp, Map.of("error", "Lỗi khi cập nhật nhân viên: " + e.getMessage()));
		}
	}

	// DELETE: xóa nhân viên
	// /employees/ID
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = getIdFromPath(req);
		if (id == null) {
			resp.setStatus(HttpServletResponse.SC_BAD_REQUEST); // 400 nếu thiếu id
			RestIO.writeEmptyOject(resp);
			return;
		}
		try {
			dao.delete(id);
			RestIO.writeEmptyOject(resp);
		} catch (Exception e) {
			// TODO: handle exception
			resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); // 500
			RestIO.writeObject(resp, Map.of("error", "Lỗi khi xóa nhân viên: " + e.getMessage()));

		}
	}
}
