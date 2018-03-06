-----util.py

def get_date_from_string(date_string):
	if date_string is not None: 
		return datetime.datetime.strptime(date_string, "%Y-%m-%d")
	return datetime.date.today()
  
  ---main2.py
import config
from core.utilty import util 
from core.analyser import analyser
import sys, argparse

def main():
	parser = argparse.ArgumentParser()	
	
	parser.add_argument('--date', '-d',required=True, help='Date of the prediction')
	
	parser.add_argument('--max', '-m', default=10, type=int, help='Number of rows')
	
	parser.add_argument('--exchange', '-e', default=['NSE', 'BSE'],  nargs='+')
	
	parser.add_argument('--range', '-r', default=[-1],  nargs='+' ,  type=int)
	
	parser.add_argument('--between', '-b', nargs=2,  type=int)
	
	parser.add_argument('--symbol', '-s', nargs='+')
	
	args = parser.parse_args()
	
	analyser.start(args)	
		
if __name__ == '__main__':
	main()
	
ana.py

import pandas as pd
import numpy as np
import config
from core.utilty import util 


def start(args):
	date = util.get_date_from_string(args.date)
	#print(args)
	for exchange in args.exchange:
		#print("(%s)(%s)/(%s)/(%s)/(%s)/(%s)" %(exchange,exchange,exchange,exchange,exchange,exchange))
		_start(exchange, date, args.max, args.range, between=args.between, symbol=args.symbol)	
		#print("(%s)__-(%s)(%s)(%s)\(%s)-__\(%s)"%(exchange,exchange,exchange,exchange,exchange,exchange))

def _start(exchange, date, max, range, **kwargs):
	print("<h4>Result for %s</h4>" %(exchange))	
	_file = config.get("%s_result" %(exchange.lower())) + "//" + date.strftime('%d_%b_%Y') + ".csv"
	if(util.is_file_exist(_file)):
		frame = pd.read_csv( _file,index_col=None, header=0)		
		#frame.set_index('SYMBOL', inplace=True)
		for top in range:
			if(top == -1):
				break;
			printAnalysis(frame, max, top)
		between = kwargs.get('between')
		if between is not None:
			printTopBetween(frame, max, between[0], between[1])
		
		symbol = kwargs.get('symbol')
		if symbol is not None:
			printSelected(frame, symbol)
	else:
		print('Not Have predition for data', date)
		
	
def printAnalysis(frame, max, top):
	if(top == 0):
		printTop(frame, max)
	else:
		printTopUnder(frame, max, top)	
	
def printSelected(frame, _list):
	#df[df['A'].isin([3, 6])]
	qry = frame['SYMBOL'].isin(_list)
	print("<h5>SYMBOL</h5>")
	printFrame(frame[qry])
	print("<h5>SYMBOL</h5>")
	
def printTop(frame, max):
	print("<h5>TOP ERNER</h5>")
	printFrame(frame.nlargest(max, 'DIFF_VALUE').sort_values(['DIFF_VALUE', 'LAST_VALUE'],ascending=False))
	#print("<h5>TOP ERNER</h5>")
	
def printTopUnder(frame, max, top):
	print("<h5>TOP under  %s</h5>" %(top))
	qry1 = frame['LAST_VALUE'] <= top	
	printFrame(frame[qry1].nlargest(max, 'DIFF_VALUE').sort_values(['PREDICTED_SHARE'],ascending=[False]))
	#print("<h5>TOP Under %s</h5>" %(top))
	
def printTopBetween(frame, max, left, right):	
	print("<h5>TOP under  %s-%s</h5>" %(left, right))
	qry1 = (frame['LAST_VALUE'] >= left) & (frame['LAST_VALUE'] <= right)
	printFrame(frame[qry1].nlargest(max, 'DIFF_VALUE').sort_values(['PREDICTED_SHARE'],ascending=[False]))
	#print("<h5>TOP Under %s-%s</h5>" %(left, right))
	
def printFrame(frame):
	frame = frame.set_index('SYMBOL')		
	print(frame)

	

	
	
@GetMapping("/api/demo")
	public void welcome(@RequestParam Map<String, String> params, HttpServletResponse response)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException {// Welcome page, non-rest
		System.out.println("dates" + params);

		String program = "C:\\Applications\\python\\python";

		String script = "C:\\Applications\\python\\TestPython_tute\\main2.py";

		List<String> command = new ArrayList<String>();
		command.add(program);
		command.add(script);

		String date = params.get("date");
		if (date != null) {
			command.add("-d");
			command.add(date);
		}
		String exchange = params.get("exchange");
		if (date != null) {
			command.add("-e");
			String rangeA[] = exchange.split(",");
			for (String string : rangeA) {
				command.add(string);				
			}
		}
		String max = params.get("max");
		if (max != null) {
			command.add("-m");
			command.add(max);
		}
		
		String range = params.get("range");
		if (range != null) {
			command.add("-r");
			String rangeA[] = range.split(",");
			for (String string : rangeA) {
				command.add(string);				
			}
			
		}
		
		String between = params.get("between");
		if (between != null) {
			command.add("-b");
			String rangeA[] = between.split(",");
			for (String string : rangeA) {
				command.add(string);				
			}
			
		}
		
		String symbols = params.get("symbols");
		if (symbols != null) {
			command.add("-s");
			String ss[] = symbols.split(",");
			for (String s : ss) {
				command.add(s);				
			}
			
		}
		String[] cmd = command.toArray(new String[0]);
		System.out.println(Arrays.toString(cmd));
		try {

			Process proc = Runtime.getRuntime().exec(cmd);
			IOUtils.copy(proc.getErrorStream(), response.getOutputStream());
			IOUtils.copy(proc.getInputStream(), response.getOutputStream());
			response.flushBuffer();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
