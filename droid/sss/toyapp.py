#!/usr/bin/env python
import sss
import random
import sys
import json
def generate_secrets(n, k, s):
	#_16byteNo_start = 1000000000000000
	#_16byteNo_end   = 9999999999999999
	#secret = random.randrange(_16byteNo_start,_16byteNo_end)
	prime  = 18895749970915969007
	secret = s
	shares = sss.deal(n,k,prime,secret)
	return shares

def reconstruct(shares):
	prime  = 18895749970915969007
	key = sss.reconstruct(prime,shares)
	#print key
	return key

if sys.argv[1] == 'encrypt':
	shares = generate_secrets(int(sys.argv[2]), int(sys.argv[3]), int(sys.argv[4]))
	print shares
	#shares = (shares)
	print json.dumps(shares)
	#print shares
	#print reconstruct(shares)

if sys.argv[1] == 'decrypt':
	#print sys.argv[2]
	shares = json.loads(sys.argv[2])
	print reconstruct(shares)
