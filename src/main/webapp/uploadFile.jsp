<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Upload File</title>
</head>
<body>
	<h1>Upload file</h1>
	<input type="file" id="uploadFile" name="fileInput">
	<button onclick="upload()">Upload</button>
	<p>Kết quả hiện ra ở console của trình duyệt</p>
	
	<!-- mã js -->
	<script>
		function upload() {
			//lấy file đã chọn
			const fileInput = document.getElementById("uploadFile");
			const file = fileInput.files[0];
			
			if(!file){
				alter("Vui lòng chọn một file upload");
				return;
			}
			//formData để đóng gói file
			const formData = new FormData();
			formData.append("file", file);
			
			//gửi yêu cầu POST
			const url = 'http://localhost:8080/Lab7/upload';
			const options = {method: 'POST', body: formData};
			fetch(url, options)
			.then(response => {
				if(!response.ok) {
					throw new Error('Upload không thành công: ' +responese.status);
				}
				return response.json();
			})
			.then(success => {
				console.log("Upload thành công");
				console.log("Thông tin JSON nhận được: ",success);
				console.log("Tên file: ",success.name);
				console.log("Loại file: ",success.type);
				console.log("Kích thước file: ",success.size);
			}).catch(error => {
				console.log('Đã xảy ra lỗi: ', error);
			});
		}
	</script>
</body>
</html>