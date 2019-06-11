<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<head>
		<jsp:include page="meta.jsp"></jsp:include>
		<title>${ title }</title>
		<jsp:include page="css.jsp"></jsp:include>
		<link rel='stylesheet' href='resources/css/position.css' />
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
								<th>樓層</th>
								<th>品牌</th>
								<th>區塊</th>
								<th>櫃位</th>
								<th>商品名稱</th>
								<th>線上商城連結</th>
								<th>優先次序</th>
								<th>樓層(查找用)</th>
								<th>品牌(查找用)</th>
								<th>區塊(查找用)</th>
								<th>櫃位(查找用)</th>
								<th>功能</th>
							</tr>
						<c:forEach items="${positionList}" var="position" varStatus="loop">
					        <tr>
					            <td class="id col-hidden">${position.positionId}</td>
					            <td>${loop.index+1}</td>
					            <td class="floor">${position.floor}</td>
					            <td class="brand">${position.brand}</td>
					            <td class="district">${position.district}</td>
					            <td class="block">${position.block}</td>
					            <td class="productName">${position.productName}</td>
					            <td class="shopUrl">${position.shopUrl}</td>
					            <td class="seq">${position.seq}</td>
					            <td class="floorEqual">${position.floorEqual}</td>
					            <td class="brandEqual">${position.brandEqual}</td>
					            <td class="districtEqual">${position.districtEqual}</td>
					            <td class="blockEqual">${position.blockEqual}</td>
					            <td>
									<div class="table-row-func btn-in-table btn-gray"><i class="fa fa-ellipsis-h"></i>
										<div class="table-function-list">
											<a href="#" id="btn_edt_${position.positionId}" class="btn-in-table btn-green"><i class="fa fa-pencil-alt"></i></a>
											<a href="#" id="btn_del_${position.positionId}" class="btn-in-table btn-alert"><i class="fa fa-trash"></i></a>
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
					<label for="edt_floor">樓層
						<input type="text" name="floor" id="edt_floor">
					</label>
				</div>
				<div class="form-row">
					<label for="edt_brand">品牌
						<input type="text" name="brand" id="edt_brand">
					</label>
				</div>
				<div class="form-row">
					<label for="edt_district">區塊
						<input type="text" name="district" id="edt_district">
					</label>
				</div>
				<div class="form-row">
					<label for="edt_block">櫃位
						<input type="text" name="block" id="edt_block">
					</label>
				</div>
				<div class="form-row">
					<label for="edt_product_name">商品名稱
						<input type="text" name="productName" id="edt_product_name">
					</label>
				</div>
				<div class="form-row">
					<label for="edt_shop_url">線上商城連結
						<input type="text" name="shopUrl" id="edt_shop_url">
					</label>
				</div>
				<div class="form-row">
					<label for="edt_seq">優先次序
						<input type="text" name="seq" id="edt_seq">
					</label>
				</div>
				<div class="form-row">
					<label for="edt_floorEqual">樓層(查找用)
						<input type="text" name="floorEqual" id="edt_floorEqual">
					</label>
				</div>
				<div class="form-row">
					<label for="edt_brandEqual">品牌(查找用)
						<input type="text" name="brandEqual" id="edt_brandEqual">
					</label>
				</div>
				<div class="form-row">
					<label for="edt_districtEqual">區塊(查找用)
						<input type="text" name="districtEqual" id="edt_districtEqual">
					</label>
				</div>
				<div class="form-row">
					<label for="edt_blockEqual">櫃位(查找用)
						<input type="text" name="blockEqual" id="edt_blockEqual">
					</label>
				</div>
			</div>
		</div>
		
		<div id="dlg_delete" class="dlg_hidden">
			<div>
				<input type="hidden" name="id">
				<input type="hidden" name="brand">
			</div>
			<div class="content">
			</div>
		</div>
		
		<jsp:include page="js.jsp"></jsp:include>
		<script type="text/javascript" src="resources/js/position.js"></script>
	</body>
</html>