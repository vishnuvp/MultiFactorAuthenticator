<?php
require_once("includes/db.class.inc");
require_once("includes/mailapi.inc");
require_once("includes/session.inc");

if(is_session_active()) {
	
		$db = new DBConnection;
		$db->connect();
		$user = get_user_info();
		$secretshare = $db->query('shares','share',"uid='$user' AND share LIKE '[3,%'",null,null,null)[0]['share'];
		//print_r($secretshare);
		$db->delete("shares", "share='$secretshare'");
		$db->disconnect();
		echo $secretshare;	
	
}

else {
	//header("Location: ../");
	echo "fail";

}

?>
