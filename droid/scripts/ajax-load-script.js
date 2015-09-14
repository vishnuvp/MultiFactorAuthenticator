$(document).ready(function() {

	$.ajax({
			dataType : 'text',
			url: '/mfa/includes/sendsms.inc.php',
			type: 'get',
			cache: false,
			async: true,
			success: function(data) {	
				//alert(data['status']+' '+ data['data']);
				//ndata = JSON.parse(data);	
				

				if(data == 'success') {
					$("#ajax-update-row-sms .jqch").toggleClass('show hide');
					$("#ajax-update-row-submit input").show();
				}
				else {
					$("#ajax-update-row-sms .ajax-status").html('SMS sending failed');
				}
		},
			error: function(data) {
					$("#ajax-update-row-sms .ajax-status").html('SMS sending failed');
			}

		});

	$.ajax({
			dataType : 'text',
			url: '/mfa/includes/sendapptoken.inc.php',
			type: 'get',
			cache: false,
			async: true,
			success: function(data) {	
				//alert(data['status']+' '+ data['data']);
				//ndata = JSON.parse(data);	
				

				if(data == 'success') {
					$("#ajax-update-row-app .jqch").toggleClass('show hide');
					$("#ajax-update-row-submit input").show();
				}
				else {
					$("#ajax-update-row-app .ajax-status").html('App token sending failed');
				}
			},
			error: function(data) {
					$("#ajax-update-row-app .ajax-status").html('App token sending failed');
			}

		});
	
	$.ajax({
			dataType : 'text',
			url: '/mfa/includes/sendemail.inc.php',
			type: 'get',
			cache: false,
			async: true,
			success: function(data) {	
				//alert(data['status']+' '+ data['data']);
				//ndata = JSON.parse(data);	
				

				if(data == 'success') {
					$("#ajax-update-row-email .jqch ").toggleClass('show hide');
					$("#ajax-update-row-submit input").show();
				}
				else {
					$("#ajax-update-row-email .ajax-status").html('Email sending failed');
				}
		},

		error: function(data) {
			$("#ajax-update-row-email .ajax-status").html('Email sending failed');
		}

		});
});

