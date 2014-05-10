package com.baoli.jisuanqi;


public class CalculatorCore {
	
	// private EditText screenText;
	public double mScreenNum = 0;
	public String mPreOpn = "=";
	public int mOpnNum = 0;
	public double mFirstNum = 0;
	
	
	public CalculatorCore(){
	}
	
	public String clickClear(){
		mScreenNum = 0;
		return "0";
	}
	
	public String clickNum(String num){

		double i = Double.valueOf(num).doubleValue();
		mScreenNum = mScreenNum * 10 + i;
		String result = String.valueOf(mScreenNum);
		return result;
	}

	public String clickOpt(String opt) {
		String result = "0";
		try {
			if (opt.equals("=") && mOpnNum == 0) {
				mFirstNum = mScreenNum;
				mScreenNum = 0;
				result = String.valueOf(mFirstNum);
			}

			else if (mOpnNum == 0
					&& (opt.equals("+") || opt.equals("-") || opt.equals("*") || opt
							.equals("/"))) {
				mFirstNum = mScreenNum;
				mScreenNum = 0;
				mPreOpn = opt;
				mOpnNum = 1;
				result = String.valueOf(mFirstNum);
			}

			else if (mOpnNum == 1
					&& (opt.equals("=") || opt.equals("+") || opt.equals("-")
							|| opt.equals("*") || opt.equals("/"))) {

				if (mPreOpn.equals("+")) {
					mFirstNum += mScreenNum;
				} else if (mPreOpn.equals("-")) {
					mFirstNum -= mScreenNum;
				} else if (mPreOpn.equals("*")) {
					mFirstNum *= mScreenNum;
				} else if (mPreOpn.equals("/")) {
					if (mScreenNum != 0)
						mFirstNum /= mScreenNum;
					else
						throw new ArithmeticException();
				}
				result = String.valueOf(mFirstNum);
				if (opt.equals("=")) {
					mOpnNum = 0;
					mScreenNum = 0;
				} else if (opt.equals("+") || opt.equals("-")
						|| opt.equals("*") || opt.equals("/")) {
					// screenNum = 0;
					mPreOpn = opt;
					mOpnNum = 1;
					mScreenNum = 0;
				}
			}
		} catch (NumberFormatException e) {
			result="Number Format ERROR!";
		} catch (ArithmeticException e) {
			result="Div Number CAN NOT a ZERO!";
			mPreOpn = "=";
		} finally {
		}
		return result;
	}

}
