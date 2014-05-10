package com.baoli.jisuanqi;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	private int[] mBtnNumId = { R.id.btn_num_0, R.id.btn_num_1, R.id.btn_num_2,
			R.id.btn_num_3, R.id.btn_num_4, R.id.btn_num_5, R.id.btn_num_6,
			R.id.btn_num_7, R.id.btn_num_8, R.id.btn_num_9, };
	private Button[] mBtnNum = new Button[10];

	private int[] mBtnOpnId = { R.id.btn_opt_div, R.id.btn_opt_mul,
			R.id.btn_opt_min, R.id.btn_opt_plus, R.id.btn_opt_equl, };
	private Button[] mBtnOpn = new Button[5];

	// private EditText screenText;
	public double mScreenNum = 0;
	public String mPreOpn = "=";
	protected TextView mScreen;
	public int mOpnNum = 0;
	public double mFirstNum = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.calc_main);

		blAttachButtonListener();
	}

	private void blAttachButtonListener() {

		mScreen = (TextView) findViewById(R.id.tv_calc_screen);
		for (int i = 0; i < mBtnOpnId.length; i++) {
			mBtnOpn[i] = (Button) findViewById(mBtnOpnId[i]);
			mBtnOpn[i].setOnClickListener(actionPerformed);
		}
		for (int i = 0; i < mBtnNumId.length; i++) {
			mBtnNum[i] = (Button) findViewById(mBtnNumId[i]);
			mBtnNum[i].setOnClickListener(actionPerformed);
		}
		Button btmCtlClear = (Button) findViewById(R.id.btn_ctrl_clear);
		btmCtlClear.setOnClickListener(actionPerformed);
	}

	private Button.OnClickListener actionPerformed = new Button.OnClickListener() {
		public void onClick(View v) {
			String command = ((Button) v).getText().toString();

			if (command.compareTo("c") == 0) {
				mScreen.setText("0");
				mScreenNum = 0;
			} else if ("+-*/=".indexOf(command) != -1) {
				wtOperater(command);
			} else if ("0123456789".indexOf(command) != -1) {
				double i = Double.valueOf(command).doubleValue();
				mScreenNum = mScreenNum * 10 + i;
				mScreen.setText(String.valueOf(mScreenNum));
			}

		}
	};

	private void wtOperater(String opt) {
		try {
			if (opt.equals("=") && mOpnNum == 0) {
				mFirstNum = Double.parseDouble(mScreen.getText().toString());
				mScreenNum = 0;
				mScreen.setText(String.valueOf(mFirstNum));
			}

			else if (mOpnNum == 0
					&& (opt.equals("+") || opt.equals("-") || opt.equals("*") || opt
							.equals("/"))) {
				mFirstNum = Double.parseDouble(mScreen.getText().toString());
				mScreenNum = 0;
				mPreOpn = opt;
				mOpnNum = 1;
				mScreen.setText(String.valueOf(mFirstNum));
			}

			else if (mOpnNum == 1
					&& (opt.equals("=") || opt.equals("+") || opt.equals("-")
							|| opt.equals("*") || opt.equals("/"))) {

				mScreenNum = Double.valueOf(mScreen.getText().toString())
						.doubleValue();

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

				mScreen.setText(String.valueOf(mFirstNum));
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
			mScreen.setText("Number Format ERROR!");
		} catch (ArithmeticException e) {
			mScreen.setText("Div Number CAN NOT a ZERO!");
			mPreOpn = "=";
		} finally {
		}
	}

}
