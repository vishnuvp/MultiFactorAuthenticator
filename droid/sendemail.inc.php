<?php
require_once("includes/db.class.inc");
require_once("includes/mailapi.inc");
require_once("includes/session.inc");

if(is_session_active()) {
	
		$db = new DBConnection;
		$db->connect();
		$user = get_user_info();
		$secretshare = $db->query('shares','share',"uid='$user' AND share LIKE '[1,%'",null,null,null)[0]['share'];
		//print_r($secretshare);
		$db->delete("shares", "share='$secretshare'");
		$db->disconnect();
		//smtpmailer("vishnu_m140163cs@nitc.ac.in", "mfautc.testmail@gmail.com", "SecureLogin", "Your secret shares", "Hi, your secrets:\n $secretshare");
		echo "success";	
	
}

else {
	//header("Location: ../");
	echo "fail";

}
?>
