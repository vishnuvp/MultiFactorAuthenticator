<?php
require_once("db.class.inc");
function start_new_session($user, $name, $time) {

	if(isset($_SESSION['user'])) {
		session_destroy();
	}
	session_start();
	$_SESSION['user'] = $user;
	$_SESSION['time'] = $time;
	$_SESSION['name'] = $name;
	

}
function revive_session() {

	@session_start();
	
}
function get_user_info() {
	revive_session();
	if(isset($_SESSION['user']))
		return $_SESSION['user'];
	return false;
}
function is_session_active() {
	revive_session();
	if(isset($_SESSION['user']))
		return true;
	return false;
}

function destroy_session() {
	revive_session();
	session_destroy();
}
?>