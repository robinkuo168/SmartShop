<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<head>
		<jsp:include page="meta.jsp"></jsp:include>
		<title>${ title }</title>
		<jsp:include page="css.jsp"></jsp:include>
		<link rel='stylesheet' href='resources/css/position.css' />
		<link rel='stylesheet' href='resources/css/carousel.css' />
		<link rel='stylesheet' href='resources/css/introSpot.css' />
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
								<th>名稱</th>
								<th>樓層</th>
								<th>照片</th>
								<th>預覽</th>
								<th>內容</th>
								<th>功能</th>
							</tr>
						<c:forEach items="${introSpotList}" var="introSpot" varStatus="loop">
					        <tr>
					            <td class="id col-hidden">${introSpot.introSpotId}</td>
					            <td>${loop.index+1}</td>
					            <td class="title">${introSpot.title}</td>
					            <td class="floor">${introSpot.floor}</td>
					            <td class="photo">${introSpot.photo}</td>
					            <td>
					            	<img src="https://drive.google.com/uc?id=${introSpot.photo}"
					            		alt="${introSpot.title}"
					            		class="carousel">
					            </td>
					            <td class="description">${introSpot.description}</td>
					            <td>
									<div class="table-row-func btn-in-table btn-gray"><i class="fa fa-ellipsis-h"></i>
										<div class="table-function-list">
											<a href="#" id="btn_edt_${introSpot.introSpotId}" class="btn-in-table btn-green"><i class="fa fa-pencil-alt"></i></a>
											<a href="#" id="btn_del_${introSpot.introSpotId}" class="btn-in-table btn-alert"><i class="fa fa-trash"></i></a>
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
					<label for="edt_1">名稱
						<input type="text" name="title" id="edt_1">
					</label>
				</div>
				<div class="form-row">
					<label for="edt_2">樓層
						<input type="text" name="floor" id="edt_2">
					</label>
				</div>
				<div class="form-row">
					<label for="edt_3">照片
						<input type="text" name="photo" id="edt_3">
					</label>
				</div>
				<div class="form-row">
					<label for="edt_4">內容</label>
				</div>
				<textarea name="description" id="edt_4" rows="5"></textarea>
			</div>
		</div>
		
		<div id="dlg_delete" class="dlg_hidden">
			<div>
				<input type="hidden" name="id">
				<input type="hidden" name="title">
			</div>
			<div class="content">
			</div>
		</div>
		
		<jsp:include page="js.jsp"></jsp:include>
		<script type="text/javascript" src="resources/js/introSpot.js"></script>
	</body>
</html>