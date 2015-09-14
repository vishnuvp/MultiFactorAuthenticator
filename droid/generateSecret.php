<?php

//echo "Hi, you are here to generate the secret";

require_once("includes/generatesecret.inc"); 
require_once("includes/session.inc"); 

echo generate_secret(3,2); // generate_secret(n,k)


?>