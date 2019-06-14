/**
 * 
 */
$('#btn_ins').click(function(e) {
	e.preventDefault();
	var data = set_data("", "", "", "", "");
	data_to_dialog('#dlg_edit', data);
	openEditDialog('ins');
})

$('body').on('click', '[id^="btn_edt"]', function(e) {
	e.preventDefault();
	var row = $(this).closest("tr");
    
	$('.selected').removeClass('selected');
    $(row).addClass("selected");
    var id = $('.id', row).html(),
	    title =$('.title', row).html(),
	    floor =$('.floor', row).html(),
	    photo =$('.photo', row).html(),
	    description =$('.description', row).html();
    
    var data = set_data(id, title, floor, photo, description);
    
	data_to_dialog('#dlg_edit', data);
	openEditDialog('edt');
});

$('body').on('click', '[id^="btn_del"]', function(e) {
	e.preventDefault();
	var row = $(this).closest("tr");
    
	$('.selected').removeClass('selected');
    $(row).addClass("selected");
    var id = $('.id', row).html(),
    	title =$('.title', row).html();
    
    var data = set_data(id, title);
    
    data_to_dialog('#dlg_delete', data);
	
    $('#dlg_delete > div.content').html(`是否刪除此筆資料，名稱：${data.title}?`);
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

function set_data(id, title, floor, photo, description) {
	return data = {
		id: id,
		title: title,
		floor: floor,
		photo: photo,
		description: description
	};
}

function data_from_dialog(dlg_name) {
	var data = {};
	var $dlg = $(dlg_name);
	$("input", $dlg).each(function(){
		var name = $(this).attr('name');
		data[name] = $(`input[name=${name}]`, $dlg).val();
	});
	$("textarea", $dlg).each(function(){
		var name = $(this).attr('name');
		data[name] = $(`textarea[name=${name}]`, $dlg).val();
	});
	return data;
}

function data_to_dialog(dlg_name, data) {
	$dlg = $(dlg_name);
	for (p in data) {
		$(`input[name=${p}]`, $dlg).val(data[p]);
		$(`textarea[name=${p}]`, $dlg).val(data[p]);
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
	var data = data_from_dialog('#dlg_edit');
	
	if( data.name == "" ){
		message("請輸入名稱");
	} else {
		$.ajax({
		    url: 'introSpot/update',
		    method: 'POST',
		    dataType: 'text',
		    data: JSON.stringify(data),
		    contentType: 'application/json',
		    success: function(response) {
		        let res = JSON.parse(response);
		        sync_to_table(res);
				message(`更新成功: ${res.title}`);
				cb();
		    }
		});
	}
}

function btn_delete_click(cb){
	var data = data_from_dialog('#dlg_delete');
	
	if( data.name == "" ){
		message("請輸入名稱");
	} else {
		$.ajax({
		    url: 'introSpot/delete',
		    method: 'POST',
		    dataType: 'text',
		    data: JSON.stringify(data),
		    contentType: 'application/json',
		    success: function(response) {
		        let res = JSON.parse(response);
				message(`刪除成功: ${res.title}`);
				$('tr.selected').remove();
				cb();
		    }
		});
	}
}

$('img').click(function() {
    $(this).toggleClass('zoom-in'); 
})
