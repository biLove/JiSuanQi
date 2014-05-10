package com.baoli.jisuanqi;

import java.text.DecimalFormat;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;

public class MainActivity extends Activity {
	
    private int[] btnNumId = {
    		R.id.btn_num_0,
    		R.id.btn_num_1,
    		R.id.btn_num_2,
    		R.id.btn_num_3,
    		R.id.btn_num_4,
    		R.id.btn_num_5,
    		R.id.btn_num_6,
    		R.id.btn_num_7,
    		R.id.btn_num_8,
    		R.id.btn_num_9,
    };
    private Button[] btnNum = new Button[10];
    
    private int[] btnOpnId = {
    		R.id.btn_opt_div,
    		R.id.btn_opt_mul,
    		R.id.btn_opt_min,
    		R.id.btn_opt_plus,
    		R.id.btn_opt_equl,
    };
    private Button[] btnOpn = new Button[5];
    
    private String[] btnOpnText = {
    		"/",
    		"*",
    		"-",
    		"+",
    		"=",
    };
    
    //private EditText screenText;
    public double screenNum = 0;
    public String preOpn = "=";
    protected TextView screen;
    public int opnNum = 0;
    public double firstNum = 0;
        
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

        setContentView(R.layout.calc_main);
        
        blAttachButtonListener();
    }
        
    private void blAttachButtonListener() {
    	
    	screen = (TextView)findViewById(R.id.tv_calc_screen);
    	for (int i = 0; i < btnOpnId.length; i++) {
    		btnOpn[i] = (Button) findViewById(btnOpnId[i]);
    		btnOpn[i].setOnClickListener(actionPerformed);
    	}
    	for (int i = 0; i < btnNumId.length; i++) {
    		btnNum[i] = (Button) findViewById(btnNumId[i]);
    		btnNum[i].setOnClickListener(actionPerformed);
    	}
    	Button btmCtlClear = (Button) findViewById(R.id.btn_ctrl_clear);
    	btmCtlClear.setOnClickListener(actionPerformed);

    	
//    	final TextView screen = (TextView) findViewById(R.id.tv_calc_screen);
//    	Button btnClear = (Button)findViewById(R.id.btn_ctrl_clear);   	
//    	Button.OnClickListener listener = new Button.OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				//Toast.makeText(MainActivity.this, "click clear", Toast.LENGTH_SHORT).show();
//				screen.setText("0");
//			} 		
//    	};    	  	
//    	btnClear.setOnClickListener(listener);
//    	
//    	
//    	
//    	for (int i = 0; i < btnOpnId.length; i++) {
//    		final int finalI = i;
//    		btnOpn[i] = (Button) findViewById(btnOpnId[i]);
//    		btnOpn[i].setOnClickListener(new Button.OnClickListener() {
//    			@Override
//    			public void onClick(View v) {
//    				Log.d("BtnOpn", "Click " + btnOpnText[finalI]);
//    				screen.setText(btnOpnText[finalI]);
//    			}
//    		});
//    	}
//
//    	
//    	for (int i = 0; i < btnNumId.length; i++) {
//    		final int textI = i;
//    		btnNum[i] = (Button) findViewById(btnNumId[i]);
//    		btnNum[i].setOnClickListener(new Button.OnClickListener() {
//    			@Override
//    			public void onClick(View v) {
//    				screen.setText(String.valueOf(textI));
//    			}
//    		});
//    	}
    	
    }
    
    private Button.OnClickListener actionPerformed = new Button.OnClickListener() {
    	public void onClick(View v) {
    		String            
    		command = ((Button)v).getText().toString();
    		
    		if(command.compareTo("c") == 0) {
    			screen.setText("0"); 
    			screenNum = 0;
    		}
    		else if("+-*/=".indexOf(command) != -1){
    			wtOperater(command);    			
    		}
    		else if("0123456789".indexOf(command) != -1){
    	    	double i = Double.valueOf(command).doubleValue();
    	    	screenNum = screenNum * 10 + i;
    	    	screen.setText(String.valueOf(screenNum));
    		}
    			
    	}
    };
    
    private void wtOperater(String opt) {
    	try {
    		if(opt.equals("=") && opnNum == 0){
    			firstNum = Double.parseDouble(screen.getText().toString());
    			screenNum = 0;
				screen.setText(String.valueOf(firstNum));
    		}
    		    		
    		else if(  opnNum == 0  && (opt.equals("+") || opt.equals("-") || opt.equals("*") || opt.equals("/")) )
    		{
    			firstNum = Double.parseDouble(screen.getText().toString());
    			screenNum = 0;
    			preOpn = opt;
   				opnNum = 1;
				screen.setText(String.valueOf(firstNum));
    			}
    		
    		else if(  opnNum == 1 && ( opt.equals("=") || opt.equals("+") || opt.equals("-") || opt.equals("*") || opt.equals("/")) ) {
    	
    			screenNum = Double.valueOf(screen.getText().toString()).doubleValue();
    		
    			if (preOpn.equals("+")){
    				firstNum += screenNum;
    			}else if(preOpn.equals("-")){
    				firstNum -= screenNum;
    			}else if(preOpn.equals("*")){
    				firstNum *= screenNum;
    			}else if(preOpn.equals("/")){
    				if(screenNum != 0)
    					firstNum /= screenNum;
    				else
    					throw new ArithmeticException();
    				} 
    			//firstNum=(double)(Math.round(firstNum*1000000)/1000000.0); //除不断的保留6位小数
    			
    			screen.setText(String.valueOf(firstNum));
    			if (opt.equals("=")){
    				opnNum = 0;
    				screenNum = 0;
    			}else if(opt.equals("+") || opt.equals("-") || opt.equals("*") || opt.equals("/")){
        			//screenNum = 0;
        			preOpn = opt;
       				opnNum = 1;
    				screenNum = 0;
    			}		
    			}
    		}catch(NumberFormatException e){
    			screen.setText("Number Format ERROR!"); 
    			}catch(ArithmeticException e) {
    				screen.setText("Div Number CAN NOT a ZERO!");
    				preOpn = "=";    			
    			}finally {
    			}
    	}

}
