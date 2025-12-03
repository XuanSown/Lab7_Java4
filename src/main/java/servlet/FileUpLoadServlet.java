package servlet;

import java.io.File;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet("/upload")
@MultipartConfig
public class FileUpLoadServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final String UPLOAD_DIR = "files";

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		String jsonResponse = "";

		try {
			// lấy đường dẫn thực tế trên servlet
			String applicationPath = req.getServletContext().getRealPath("");
			String uploadfilePath = applicationPath + File.separator + UPLOAD_DIR;

			// tạo thư mục files nếu chưa tồn tại
			File uploadDir = new File(uploadfilePath);
			if (!uploadDir.exists()) {
				uploadDir.mkdirs();
			}

			// lấy Part từ request, file là tên input trong html
			Part filePart = req.getPart("file");
			String fileName = filePart.getSubmittedFileName();
			// lưu vào thư mục file
			filePart.write(uploadfilePath + File.separator + fileName);

			String fileType = filePart.getContentType();// kiểu file
			long fileSize = filePart.getSize();// kích thước file

			// tạo chuỗi json trả về client
			jsonResponse = String.format("{\"name\": \"%s\", \"type\": \"%s\", \"size\": %d}", fileName, fileType,
					fileSize);
		} catch (Exception e) {
			// TODO: handle exception
			// error
			jsonResponse = "{\"error\": \"Lỗi upload: " + e.getMessage().replaceAll("\"", "'") + "\"}";
			resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		resp.getWriter().write(jsonResponse);
	}
}
