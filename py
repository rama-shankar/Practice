-----util.py

def get_date_from_string(date_string):
	if date_string is not None: 
		return datetime.datetime.strptime(date_string, "%Y-%m-%d")
	return datetime.date.today()
  
  ---main2.pu
  
  def main():
	ln = len(sys.argv)	
	if ln == 2:
		_date = sys.argv[1]	
	_date = util.get_date_from_string(_date)	
	print('Prediction Analysis')
	exchanges = config.get('exchanges') 
	for exchange in exchanges:
		analyser.start(exchange, _date)		
if __name__ == '__main__':
	main()
	
