<?php
require_once("includes/session.inc");
require_once("includes/db.class.inc");
function reconstruct($shares) {
	$command = '/usr/bin/python /var/www/html/mfa/sss/toyapp.py decrypt '.json_encode($shares);//implode(',',$shares).']';
	$result = exec($command, $status);
	return $result;
}

if($_SERVER['REQUEST_METHOD'] == 'POST') {
	if(is_session_active()) {
		//$shares = array($_POST['1'] , $_POST['2'], $_POST['3']);
		$shares = array();
		if(!empty($_POST['1'])) {
			array_push($shares, $_POST['1']);
		}

		if(!empty($_POST['2'])) {
			array_push($shares, $_POST['2']);
		}

		if(!empty($_POST['3'])) {
			array_push($shares, $_POST['3']);
		}
		//var_dump($shares);
		$reconstructed_secret = reconstruct($shares);
		$db = new DBConnection;
		$db->connect();
		$user = get_user_info();
		$secret = $db->query('secret','secret',"uid='$user'",null,null,null)[0]['secret'];
		$db->delete('secret',"uid='$user'");
		$db->disconnect();
		if($secret == $reconstructed_secret) {
			echo "Secrets matching. Access granted.";
		}
		else {
			echo "$secret != $reconstructed_secret. Access denied";
		}
	}

	else {
		echo "fail";
	}
}

else {
	echo "fail";
}
/* [1,8683146895150534502] [2,17361141234745553751] [3,7143385603424603993]
 * Secret 5152555555515253
 *
 */

?>