<?php
require_once("db.class.inc");
require_once("session.inc");
function generate_16digit_random() {
	/*
	 * Generating cryptographically safe random secret
	 */

	$fp = @fopen('/dev/urandom','rb');
	$pr_bits = "";
	if ($fp !== FALSE) {
    	$pr_bits .= @fread($fp,8);
    	@fclose($fp);
	}
	$random_no = "";
	foreach (str_split($pr_bits) as $char) {
		$random_no .= (ord($char)%10 + 48);# code...
	}

	return $random_no;

}
function generate_secret(){

	$random_no = generate_16digit_random();
	$db = new DBConnection;
	$db->connect();
	$uid = get_user_info();
	$db->insert("secret",'uid,secret',"'$uid','$random_no'");
	$N = 3;
	$K = 2;
	$command = '/usr/bin/python /var/www/html/mfa/sss/toyapp.py encrypt '.$N.' '.$K.' '.$random_no;
	//var_dump($command);
	//echo exec($command, $status);
	$result = exec($command, $status);
	//echo '<hr />';
	//var_dump($result);
	$result = json_decode($result, true);
	//echo '<hr />';
	//var_dump($result);

	$secretshare = '[1,'.$result['1']."]\n[2,".$result['2']."]\n[3,".$result['3']."]\n Secret: $random_no";

	//smtpmailer("vishnu_m140163cs@nitc.ac.in", "mfautc.testmail@gmail.com", "SecureLogin", "Your secret shares", "Hi, your secrets:\n $secretshare");
	$db->insert("shares",'uid,share',"'$uid','[1,$result[1]]'");
	$db->insert("shares",'uid,share',"'$uid','[2,$result[2]]'");
	$db->insert("shares",'uid,share',"'$uid','[3,$result[3]]'");
	$db->disconnect();
	return $secretshare;
}
?>