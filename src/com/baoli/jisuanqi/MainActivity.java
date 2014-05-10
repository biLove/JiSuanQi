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


	protected TextView mScreen;
	private CalculatorCore mCore;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.calc_main);

		blAttachButtonListener();
		mCore = new CalculatorCore();
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
			String result = "0";
			String command = ((Button) v).getText().toString();

			if (command.compareTo("c") == 0) {				
				result = mCore.clickClear();
				
			} else if ("+-*/=".indexOf(command) != -1) {
				result = mCore.clickOpt(command);
			} else if ("0123456789".indexOf(command) != -1) {
				result = mCore.clickNum(command);
			}
			mScreen.setText(result);

			
		}
	};
	


}
