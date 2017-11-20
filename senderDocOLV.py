import requests
import time

set_ip_apparato = "192.168.0.54"

def send_post(content):
	response = requests.post('http://'+set_ip_apparato+':80/oli_webservice.cgi',data=content,headers={"Content-Type": "text/xml"})
	print response.text
	assert response.status_code == 200

documentocommerciale = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:soap=\"http://www.w3.org/2001/12/soap-envelope\"><soap:Body><EcrTickets><CMD>...<E_SALE>..<OPTYPE>1</OPTYPE>....<BARCODETYPE></BARCODETYPE>..<NUMBER>1</NUMBER>..<LISTPRICE>1</LISTPRICE>..<SALETYPE>1</SALETYPE>..<DESCRIPTION></DESCRIPTION>..<AMOUNT>1</AMOUNT>..<QUANTITY_1></QUANTITY_1>..<QUANTITY_2></QUANTITY_2>..<QUANTITY_3></QUANTITY_3>..<QUANTITY_4></QUANTITY_4>..<M_QUANTITY></M_QUANTITY>..<S_QUANTITY></S_QUANTITY>..<RAEETYPE></RAEETYPE>..<RAEEVALUE></RAEEVALUE>.</E_SALE></CMD><CMD>...<E_PAYMENT>..<P_TYPE>1</P_TYPE>...<NUMBER>1</NUMBER>...<DESCRIPTION>PAGAMENTO</DESCRIPTION>...<AMOUNT></AMOUNT>....<CUSTOMERACCOUNT></CUSTOMERACCOUNT>...</E_PAYMENT></CMD></EcrTickets></soap:Body></soap:Envelope>"

zfiscale = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:soap=\"http://www.w3.org/2001/12/soap-envelope\"><soap:Body><ExecuteReport>.<PRINTREP>1</PRINTREP>.<REPTYPE>1</REPTYPE>.<REPNUM>10</REPNUM>.</ExecuteReport></soap:Body></soap:Envelope>"

changedate = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:soap=\"http://www.w3.org/2001/12/soap-envelope\"><soap:Body><EcrTickets><CMD><GETDATE></GETDATE></CMD></EcrTickets></soap:Body></soap:Envelope>"

day = 6
data2 = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:soap=\"http://www.w3.org/2001/12/soap-envelope\"><soap:Body><SetEcrDate><SETDATE><DATE>08102017</DATE><TIME>010000</TIME></SETDATE></SetEcrDate></soap:Body></soap:Envelope>"

def create_data(day):
	da = str(day).zfill(2)
	data4 = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:soap=\"http://www.w3.org/2001/12/soap-envelope\"><soap:Body><SetEcrDate><SETDATE><DATE>"+da+"112017</DATE><TIME>010000</TIME></SETDATE></SetEcrDate></soap:Body></soap:Envelope>"
	return data4

day = 12
num = 2
while True:
	if num == 1 or num == 80:
			send_post(changedate)
			day += 1
			se = create_data(day)
			send_post(se)
			send_post(se)
			num = 2
	send_post(documentocommerciale)
	send_post(zfiscale)
	print num 
	print day
	time.sleep(5)
	num +=1
