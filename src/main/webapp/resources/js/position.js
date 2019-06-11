/**
 * 
 */
$('#btn_ins').click(function(e) {
	e.preventDefault();
	var position = set_position("", "", "", "", "", "", "", "", "", "", "", "");
	data_to_dialog('#dlg_edit', position);
	openEditDialog('ins');
})

$('body').on('click', '[id^="btn_edt"]', function(e) {
	e.preventDefault();
	var row = $(this).closest("tr");
    
	$('.selected').removeClass('selected');
    $(row).addClass("selected");
    var id = $('.id', row).html(),
    	floor =$('.floor', row).html(),
	    brand =$('.brand', row).html(),
	    district =$('.district', row).html(),
	    block =$('.block', row).html(),
	    productName =$('.productName', row).html(),
	    shopUrl =$('.shopUrl', row).html(),
	    seq =$('.seq', row).html(),
	    floorE =$('.floorEqual', row).html(),
	    brandE =$('.brandEqual', row).html(),
	    districtE =$('.districtEqual', row).html(),
	    blockE =$('.blockEqual', row).html();
    
    var position = set_position(id, brand, floor, productName, shopUrl, seq, district, block, brandE, floorE, districtE, blockE);
    
	data_to_dialog('#dlg_edit', position);
	openEditDialog('edt');
});

$('body').on('click', '[id^="btn_del"]', function(e) {
	e.preventDefault();
	var row = $(this).closest("tr");
    
	$('.selected').removeClass('selected');
    $(row).addClass("selected");
    var id = $('.id', row).html(),
	    brand =$('.brand', row).html();
    
    var position = set_position(id, brand);
    
    data_to_dialog('#dlg_delete', position);
	
    $('#dlg_delete > div.content').html(`是否刪除此筆資料，品牌：${position.brand}?`);
    $('#dlg_delete').dialog({
	    title: "刪除資料",
	    modal: true,
	    width: 400,
	    open: function(event, ui) {
	        $(this).parent().children().children('.ui-dialog-titlebar-close').hide();
	    },
	    buttons: [{
	    	text: "確認刪除",
	    	click: function() {
	            btn_delete_click(()=>{
	            	$(this).dialog("close");
            	});
	        }
	    }, {
	        text: "取消",
	        click: function() {
	        	$('.selected').removeClass('selected');
	            $(this).dialog("close");
	        }
	    }]
	});
});

function openEditDialog(type) {
	var data = {};
	if (type == 'ins'){
		data['title'] = '新增資料';
		data['text'] = '確認新增';
	} else if (type == 'edt'){
		data['title'] = '更新資料';
		data['text'] = '確認修改';
	}
	
	$('#dlg_edit').dialog({
	    title: data.title,
	    modal: true,
	    width: 400,
	    open: function(event, ui) {
	        $(this).parent().children().children('.ui-dialog-titlebar-close').hide();
	    },
	    buttons: [{
	    	text: data.text,
	    	click: function() {
	            btn_edit_click(()=>{$(this).dialog("close");});
	        }
	    }, {
	        text: "取消",
	        click: function() {
	        	$('.selected').removeClass('selected');
	            $(this).dialog("close");
	        }
	    }]
	});
}

function set_position(id, brand, floor, productName, shopUrl, seq, district, block, brandE, floorE, districtE, blockE) {
	return data = {
		id: id,
		floor: floor,
		brand: brand,
		district: district,
		block: block,
		productName: productName,
		shopUrl: shopUrl,
		seq: seq,
		floorEqual: floorE,
		brandEqual: brandE,
		districtEqual: districtE,
		blockEqual: blockE
	};
}

function data_from_dialog(dlg_name) {
	var position = {};
	var $dlg = $(dlg_name);
	$("input", $dlg).each(function(){
		var name = $(this).attr('name');
		position[name] = $(`input[name=${name}]`, $dlg).val();
	});
	return position;
}

function data_to_dialog(dlg_name, data) {
	$dlg = $(dlg_name);
	for (p in data) {
		$(`input[name=${p}]`, $dlg).val(data[p]);
	}
}

function sync_to_table(position) {
	var row = $('tr.selected');
	for (p in position) {
		$(`.${p}`, row).html(position[p]);
	}
    $('tr.selected').removeClass('selected');
}

function btn_edit_click(cb){
	var position = data_from_dialog('#dlg_edit');
	
	if( position.brand == "" ){
		message("請輸入品牌");
	} else {
		$.ajax({
		    url: 'update',
		    method: 'POST',
		    dataType: 'text',
		    data: JSON.stringify(position),
		    contentType: 'application/json',
		    success: function(response) {
		        let res = JSON.parse(response);
		        sync_to_table(res);
				message(`更新成功: ${res.brand}`);
				cb();
		    }
		});
	}
}

function btn_delete_click(cb){
	var position = data_from_dialog('#dlg_delete');
	
	if( position.brand == "" ){
		message("請輸入品牌");
	} else {
		$.ajax({
		    url: 'delete',
		    method: 'POST',
		    dataType: 'text',
		    data: JSON.stringify(position),
		    contentType: 'application/json',
		    success: function(response) {
		        let res = JSON.parse(response);
				message(`刪除成功: ${res.brand}`);
				$('tr.selected').remove();
				cb();
		    }
		});
	}
}
