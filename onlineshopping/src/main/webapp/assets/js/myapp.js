$(function() {
	// Code to solve active menu problem
	switch (menu) {
	case 'About Us':
		$('#about').addClass('active');
		break;
	case 'View Products':
		$('#listProducts').addClass('active');
		break;
	case 'Manage Products':
		$('#manageProducts').addClass('active');
		break;
	case 'Contact Us':
		$('#contact').addClass('active');
		break;
	default:
		if(menu == "Home") break;
		$('#listProducts').addClass('active');
		$('#a_' + menu).addClass('active');
		break;
	}
	//Code to create dataSet to be used in jquery table
	var $table = $('#productListTable');
	//The below code needs to be executed where the above table is present
	if($table.length) {
		var jsonUrl = '';
		if(window.categoryId == '') {
			jsonUrl = window.contextRoot + '/json/data/all/products';
		} else {
			jsonUrl = window.contextRoot + '/json/data/category/' + window.categoryId + '/products';
		}
		$table.DataTable({
			lengthMenu: [[3, 5, 10, -1], ['3 Records', '5 Records', '10 Records', 'ALL']],
			pageLength: 5,
			ajax: {
				url: jsonUrl,
				dataSrc: ''
			},
			columns: [
				{
					data: 'code',
					mRender: function(data, type, row) {
						return '<img src="'+window.contextRoot+'/resources/images/'+data+'.jpg" style="width:100px;height:100px;"/>';
					}
				},
				{
					data: 'name'
				},
				{
					data: 'brand'
				},
				{
					data: 'unitPrice',
					mRender: function(data, type, row) {
						return '&#8377;' + data
					}
				},
				{
					data: 'quantity',
					mRender: function(data, type, row) {
						if(data < 1) {
							return '<span style="color:red">Product is Out of Stock</span>';
						}
						return data;
					}
				},
				{
					data: 'id',
					bSortable: false,
					mRender: function(data, type, row) {
						var str = '';
						if(row.quantity < 1) {
							str += '<a href="javascript:void(0)" class="btn btn-success disabled"><span class="glyphicon glyphicon-shopping-cart"></span></a>';
						} else {
							str += '<a href="'+window.contextRoot+'/cart/add/'+data+'/product" class="btn btn-success"><span class="glyphicon glyphicon-shopping-cart"></span></a>';
						}
						str += '<a href="'+window.contextRoot+'/cart/add/'+data+'/product" class="btn btn-success"><span class="glyphicon glyphicon-shopping-cart"></span></a>';
						return str;
					}
				}
			]
		});
	}
	//Dismissing the alert after 3 seconds
	var $alert = $('.alert');
	if($alert.length) {
		setTimeout(function() {
			$alert.fadeOut('slow');
		}, 3000);
	}
		
	$('.switch input[type="checkbox"]').change(function() {
		var checkbox = $(this);
		var checked = checkbox.prop('checked');
		var dMsg = (checked) ? 'You want to activate the product?' : 'You want to deactivate the product?';
		var value = checkbox.prop('value');
		bootbox.confirm({
			size: 'medium',
			title: 'Product Activation & Deactivation',
			message: dMsg,
			callback: function(confirmed) {
				if(confirmed) {
					bootbox.alert({
						size: 'medium',
						title: 'Information',
						message: 'You\'re going to perform operation on product ' + value 
					});
				} else {
					checkbox.prop('checked', !checked);
				}
			}
		});
	});
	
	//Data Table for Admin
	var $productsTable = $('#productsTable');
	//The below code needs to be executed where the above table is present
	if($productsTable.length) {
		var jsonUrl = window.contextRoot + '/json/data/admin/all/products';
		$productsTable.DataTable({
			lengthMenu: [[10, 30, 50, -1], ['10 Records', '30 Records', '50 Records', 'ALL']],
			pageLength: 30,
			ajax: {
				url: jsonUrl,
				dataSrc: ''
			},
			columns: [
				{ data: 'id' },
				{
					data: 'code',
					mRender: function(data, type, row) {
						return '<img src="'+window.contextRoot+'/resources/images/'+data+'.jpg" class="dataTableImg"/>';
					}
				},
				{ data: 'name' },
				{ data: 'brand'	},
				{
					data: 'quantity',
					mRender: function(data, type, row) {
						if(data < 1) {
							return '<span style="color:red">Product is Out of Stock</span>';
						}
						return data;
					}
				},
				{
					data: 'unitPrice',
					mRender: function(data, type, row) {
						return '&#8377;' + data
					}
				},
				{
					data: 'active',
					bSortable: false,
					mRender: function(data, type, row) {
						var str = '';
						if(data) {
							str += '<label class="switch"><input type="checkbox" checked="checked" value="'+row.id+'"/><div class="slider"></div></label>';
						} else {
							str += '<label class="switch"><input type="checkbox" value="'+row.id+'"/><div class="slider"></div></label>';
						}
						return str;
					}
				},
				{
					data: 'id',
					bSortable: false,
					mRender: function(data, type, row) {
						var str = '<a href="'+window.contextRoot+'/manage/'+data+'/product" class="btn btn-warning"><span class="glyphicon glyphicon-pencil"></span></a>';
						return str;
					}
				}
			],
			initComplete: function() {
				var api = this.api();
				api.$('.switch input[type="checkbox"]').change(function() {
					var checkbox = $(this);
					var checked = checkbox.prop('checked');
					var dMsg = (checked) ? 'You want to activate the product?' : 'You want to deactivate the product?';
					var value = checkbox.prop('value');
					bootbox.confirm({
						size: 'medium',
						title: 'Product Activation & Deactivation',
						message: dMsg,
						callback: function(confirmed) {
							if(confirmed) {
								var activationUrl = window.contextRoot + '/manage/product' + value + '/activation';
								$.post(activationUrl, function(data) {
									bootbox.alert({
										size: 'medium',
										title: 'Information',
										message: data 
									});
								});
							} else {
								checkbox.prop('checked', !checked);
							}
						}
					});
				});
			}
		});
	}
	
	//Validation Code For Category
	var $categoryForm = $('#categoryForm');
	if($categoryForm.length) {
		$categoryForm.validate({
			rules: {
				name: {
					required: true,
					minLength: 2
				},
				description: {
					required: true
				},
				messages: {
					name: {
						required: 'Please add the category name!',
						minLength: 'Category length should be atleast 2 characters'
					},
					description: {
						required: true
					}
				},
				errorElement: 'em',
				errorPlacement: function(error, element) {
					error.addClass('help-block');  //Adding the class of help-block to the error element
					error.insertAfter(element);  //Adding the error element after input element
				}
			}
		});
	}
});