<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
</head>

<script src="resources/js/jquery-3.4.1.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.7/jquery.js"></script> 
<script src="https://malsup.github.io/jquery.form.js"></script> 

<script type="text/javascript">
	$(document).ready(function() {

	});

	function checkFileType(filePath) {
		var fileFormat = filePath.split(".");
		if (fileFormat.indexOf("xls") > -1) {
			return true;
		} else if (fileFormat.indexOf("xlsx") > -1) {
			return true;
		} else {
			return false;
		}
	}

	function check() {
		var file = $("#excel").val();
		if (file == "" || file == null) {
			alert("파일을 선택하세요.");
			return false;
		} else if (!checkFileType(file)) {
			alert("엑셀 파일(.xls, .xlsx)만 업로드 해주세요.");
			return false;
		}
		var fileFormat = file.split(".");
		var fileType = fileFormat[1];
		if (confirm("업로드 하시겠습니까?")) {
			$("#excelUpForm").attr("action", "${cp}/excel/upload");
			var options = {
				success : function(data) {
					alert("업로드가 완료되었습니다.");
					$("#ajax-content").html(data);
				},
				type : "POST",
				data : {
					"excelType" : fileType
				}
			};
			$("form").ajaxSubmit(options);
		}
	}
</script>



<body>
	<h1>엑셀 일괄등록 테스트</h1>

	<form id="excelUpForm" method="post" action="" role="form" enctype="multipart/form-data">
		<div class="col-sm-12">
			<div class="row" id="regGoodsImgArea">
				<div class="col-sm-4">
					<input id="excel" name="excel" class="file" type="file" multiple data-show-upload="false" data-show-caption="true" />
				</div>
			</div>
		</div>
		<button type="button" id="excelUp" onclick="check()">등록</button>
	</form>
</body>
</html>