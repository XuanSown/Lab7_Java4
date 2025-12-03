<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Fetch JSON từ Servlet</title>
</head>
<body>
	<h1>Đọc JSON từ Servlet</h1>
	<button onclick="fetchData()">Tải dữ liệu nhân viên</button>
	<p>Kết quả hiện ra ở console của trình duyệt</p>
	
	<!-- mã js -->
	<script>
		function fetchData() {
			const url = 'http://localhost:8080/Lab7/employee-json';
			
			//sử dụng Fetch API để gửi yêu cầu GET
			fetch(url).then(response => { //chuyển response thành chuỗi json
				if (!response.ok) {
					throw new Error('phản hồi của mạng không ổn');
				}
			return response.json(); //trả về promise với dữ liệu json
			}).then(employeeData => {
				console.log("Dữ liệu JSON nhận được: ",employeeData); //toàn bộ đối tượng
				console.log("Mã NV: ",employeeData.manv);
				console.log("Họ tên NV: ",employeeData.hoTen);
				console.log("Giới tính NV (true = Nam): ",employeeData.gioiTinh);
				console.log("Lương: ",employeeData.luong);
			}).catch(error => {
				console.error('Đã xảy ra lỗi khi fetch: ',error);
			});
		}
		
	</script>
</body>
</html>