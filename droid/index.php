<?php
	require_once("includes/session.inc");
	require_once("includes/authenticate.inc");
	//if(is_session_active()) 
	//	header("Location:views/home.php");
	if($_SERVER['REQUEST_METHOD'] == 'POST') {
		$result = authenticate_user($_POST['username'], $_POST['password']);
		if($result) {
			start_new_session($_POST['username'],$_POST['username'], mktime());
			//header("Location:views/home.php");
			echo 'success';
		}
		else {
			echo 'Invalid Login Credentials';
			//$error_code = array('msg' => 'Invalid Login Credentials. Please try again.', 'type' => 'error');
		}
	}
?>
