package dk.stacktrace.risk;

import java.util.ArrayList;

import dk.stacktrace.risk.gui.QuitGameDialog;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Process;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnKeyListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class CreatePlayers extends Activity implements OnClickListener,  TextWatcher {

	public final static String PLAYER1NAME = "dk.stacktrace.risk.PLAYER1";
	public final static String PLAYER2NAME = "dk.stacktrace.risk.PLAYER2";
	public final static String PLAYER3NAME = "dk.stacktrace.risk.PLAYER3";
	public final static String PLAYER4NAME = "dk.stacktrace.risk.PLAYER4";
	public final static String PLAYER5NAME = "dk.stacktrace.risk.PLAYER5";
	public final static String PLAYER6NAME = "dk.stacktrace.risk.PLAYER6";
	
	EditText p1, p2, p3, p4, p5, p6;
	ImageView battleBtn;
	ArrayList<EditText> playerNames;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Force landscape orientation
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        //Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        setContentView(R.layout.activity_create_players);
        
        p1 = (EditText) findViewById(R.id.player1);
        p2 = (EditText) findViewById(R.id.player2);
        p3 = (EditText) findViewById(R.id.player3);
        p4 = (EditText) findViewById(R.id.player4);
        p5 = (EditText) findViewById(R.id.player5);
        p6 = (EditText) findViewById(R.id.player6);
        
        playerNames = new ArrayList<EditText>();
        playerNames.add(p1);
        playerNames.add(p2);
        playerNames.add(p3);
        playerNames.add(p4);
        playerNames.add(p5);
        playerNames.add(p6);
        
        for (EditText playerName : playerNames)
        {
        	playerName.setTextColor(Color.parseColor("#d82323"));
        	playerName.addTextChangedListener(this);
        }
        
        battleBtn = (ImageView) findViewById(R.id.startBattleBtn);
        battleBtn.setOnClickListener(this);
    }

    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_create_players, menu);
        return true;
    }

	private boolean isReadyToBattle()
	{
		int numOfPlayers = 0;
		for (TextView playerName : playerNames)
		{
			numOfPlayers += (!playerName.getText().toString().matches("")) ? 1 : 0; 
		}
		System.out.println("numOfPlayers : " + numOfPlayers);
		return (numOfPlayers >= 2) ? true : false;
	}

	public void onClick(View arg0)
	{
		Log.v("onClick", "Start RISK activity");
		if(isReadyToBattle())
		{
			Intent intent = new Intent(this, Main.class);
			intent.putExtra(PLAYER1NAME, p1.getText().toString());
		    intent.putExtra(PLAYER2NAME, p2.getText().toString());
			intent.putExtra(PLAYER3NAME, p3.getText().toString());
			intent.putExtra(PLAYER4NAME, p4.getText().toString());
			intent.putExtra(PLAYER5NAME, p5.getText().toString());
			intent.putExtra(PLAYER6NAME, p6.getText().toString());
		    startActivity(intent);	
		}
	}

	public void afterTextChanged(Editable s)
	{
		if(isReadyToBattle())
		{
			battleBtn.setOnClickListener(this);
			battleBtn.setImageResource(R.drawable.start_battle_btn);
		}
		else
		{
			battleBtn.setOnClickListener(null);
			battleBtn.setImageResource(R.drawable.start_battle_btn_inactive);
		}
	}

	public void beforeTextChanged(CharSequence s, int start, int count,
			int after)
	{
		// TODO Auto-generated method stub
		
	}

	public void onTextChanged(CharSequence s, int start, int before, int count)
	{
		// TODO Auto-generated method stub
		
	}
}
