<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<head>
		<jsp:include page="meta.jsp"></jsp:include>
		<title>${ title }</title>
		<jsp:include page="css.jsp"></jsp:include>
		<link rel='stylesheet' href='resources/css/position.css' />
		<link rel='stylesheet' href='resources/css/carousel.css' />
	</head>
	<body>
		<div class="page-wrapper">
			<jsp:include page="header.jsp"></jsp:include>
			
			<div class="content-wrap">
				<h2 class="page-title">${ pageTitle }</h2>
				
				<div class="search-result-wrap">
					<div class="form-row">
						<a href="#" id="btn_ins" class="btn btn-primary">新增資料</a>
					</div>
					<div class="result-table-wrap">
						<table class="result-table">
							<tr>
								<th class="col-hidden">代碼</th>
								<th>序號</th>
								<th>檔名</th>
								<th>Google Drive</th>
								<th>播放順序</th>
								<th>預覽</th>
								<th>功能</th>
							</tr>
						<c:forEach items="${carouselList}" var="carousel" varStatus="loop">
					        <tr>
					            <td class="id col-hidden">${carousel.carouselId}</td>
					            <td>${loop.index+1}</td>
					            <td class="photoName">${carousel.photoName}</td>
					            <td class="googleDrive">${carousel.googleDrive}</td>
					            <td class="seq">${carousel.seq}</td>
					            <td>
					            	<img src="https://drive.google.com/uc?id=${carousel.googleDrive}"
					            		alt="${carousel.photoName}"
					            		class="carousel">
					            </td>
					            <td>
									<div class="table-row-func btn-in-table btn-gray"><i class="fa fa-ellipsis-h"></i>
										<div class="table-function-list">
											<a href="#" id="btn_edt_${carousel.carouselId}" class="btn-in-table btn-green"><i class="fa fa-pencil-alt"></i></a>
											<a href="#" id="btn_del_${carousel.carouselId}" class="btn-in-table btn-alert"><i class="fa fa-trash"></i></a>
										</div>
									</div>
								</td>
					        </tr>
					    </c:forEach>
						
						</table>
					</div>
				</div>
			</div>
			
			<jsp:include page="footer.jsp"></jsp:include>
		</div>
		
		<div id="dlg_edit" class="dlg_hidden">
			<div class="form-wrap">
				<input type="hidden" name="id">
				<div class="form-row">
					<label for="edt_1">檔名
						<input type="text" name="photoName" id="edt_1">
					</label>
				</div>
				<div class="form-row">
					<label for="edt_2">Google Drive
						<input type="text" name="googleDrive" id="edt_2">
					</label>
				</div>
				<div class="form-row">
					<label for="edt_3">播放順序
						<input type="text" name="seq" id="edt_3">
					</label>
				</div>
			</div>
		</div>
		
		<div id="dlg_delete" class="dlg_hidden">
			<div>
				<input type="hidden" name="id">
				<input type="hidden" name="googleDrive">
			</div>
			<div class="content">
			</div>
		</div>
		
		<jsp:include page="js.jsp"></jsp:include>
		<script type="text/javascript" src="resources/js/carousel.js"></script>
	</body>
</html>