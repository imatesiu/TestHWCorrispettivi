import socket
import ssl

# Set up a TCP/IP socket
s = socket.socket(socket.AF_INET,socket.SOCK_STREAM)

# Connect as client to a selected server
# on a specified port
#s.connect(("v-apid-ivaservizi.agenziaentrate.gov.it",443))
s.connect(("localhost",443))

s = ssl.wrap_socket(s, keyfile=None, certfile=None, server_side=False, cert_reqs=ssl.CERT_NONE, ssl_version=ssl.PROTOCOL_SSLv23)

# Protocol exchange - sends and receives
s.send("POST /v1/dispositivi/corrispettivi/ HTTP/1.1\r\nHost: localhost\r\nConnection: Keep-Alive\r\n\r\n")
print s
while True:
        resp = s.recv(512)
        if not resp: 
        	print "\n"
        	print type(resp),resp
        	break
        print resp,

# Close the connection when completed
s.close()
print "\ndone"