package dk.stacktrace.risk.gui;

import java.util.ArrayList;

import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import dk.stacktrace.risk.Main;
import dk.stacktrace.risk.R;
import dk.stacktrace.risk.controller.Controller;
import dk.stacktrace.risk.game_logic.Territory;
import dk.stacktrace.risk.game_logic.battle.Battle;
import dk.stacktrace.risk.game_logic.battle.Dice;

public class MissionWinnerDialog extends Dialog implements OnClickListener{
	Controller control;
	
	LayoutInflater inflater;
	View layout;

	Button rematchBtn, newGameBtn, quitBtn;
	TextView winnerText;
	Context context;
	ImageView card;
	
	public MissionWinnerDialog(Context context,Controller control, ViewGroup rootElement)
	{
		super(context);
		
		this.context = context;
		this.control = control;
		
		setCanceledOnTouchOutside(false);
		
		inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		layout = inflater.inflate(R.layout.dialog_mission_winner, rootElement);
		setContentView(layout);
		
		
		setTitle(control.getGameWinner().getName() + " completed his mission.");	
		
		rematchBtn = (Button) findViewById(R.id.missionRematchBtn);
		rematchBtn.setOnClickListener(this);
		
		newGameBtn = (Button) findViewById(R.id.missionNewGameBtn);
		newGameBtn.setOnClickListener(this);
		
		quitBtn = (Button) findViewById(R.id.missionQuitBtn);
		quitBtn.setOnClickListener(this);
		
		winnerText = (TextView) findViewById(R.id.missionWinnerText);
		winnerText.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 40);
		winnerText.setText("The winner is " + control.getGameWinner().getName());
		
		card = (ImageView) findViewById(R.id.winnerMissionCard);
		card.setImageResource(getMissionCardImageResource());
	}

	
	public void onClick(View v) {
		if(((Button) v).getId() == R.id.missionRematchBtn)
		{
			control.rematch();
			dismiss();
		}
		else if (((Button) v).getId() == R.id.missionNewGameBtn)
		{
			dismiss();
		}
		else if (((Button) v).getId() == R.id.missionQuitBtn)
		{
			dismiss();
		}
	}
	
	private int getMissionCardImageResource()
	{
		String missionId = control.getGameWinner().getMission().getMissionId();
		
		if(missionId.equals("continent_eu_au"))
		{
			return dk.stacktrace.risk.R.drawable.mission_europe_australia_plus_one;
		} 
		else if(missionId.equals("continent_eu_sa"))
		{
			return dk.stacktrace.risk.R.drawable.mission_europe_s_america_plus_one;
		} 
		else if(missionId.equals("continent_na_af"))
		{
			return dk.stacktrace.risk.R.drawable.mission_n_america_africa;
		} 
		else if(missionId.equals("continent_na_au"))
		{
			return dk.stacktrace.risk.R.drawable.mission_n_america_australia;
		} 
		else if(missionId.equals("continent_as_sa"))
		{
			return dk.stacktrace.risk.R.drawable.mission_asia_s_america;
		} 
		else if(missionId.equals("continent_as_af"))
		{
			return dk.stacktrace.risk.R.drawable.mission_asia_africa;
		} 
		else if(missionId.equals("18"))
		{
			return dk.stacktrace.risk.R.drawable.mission_18_territories;
		} 
		else if(missionId.equals("24"))
		{
			return dk.stacktrace.risk.R.drawable.mission_24_territories;
		}
		else if(missionId.equals("kill_orange"))
		{
			return dk.stacktrace.risk.R.drawable.mission_kill_red;
		}
		else if(missionId.equals("kill_green"))
		{
			return dk.stacktrace.risk.R.drawable.mission_kill_green;
		}
		else if(missionId.equals("kill_purple"))
		{
			return dk.stacktrace.risk.R.drawable.mission_kill_pink;
		}
		else if(missionId.equals("kill_yellow"))
		{
			return dk.stacktrace.risk.R.drawable.mission_kill_yellow;
		}
		else if(missionId.equals("kill_cyan"))
		{
			return dk.stacktrace.risk.R.drawable.mission_kill_cyan;
		}
		else if(missionId.equals("kill_blue"))
		{
			return dk.stacktrace.risk.R.drawable.mission_kill_blue;
		}
		else
		{
			return dk.stacktrace.risk.R.drawable.mission_cards_back;			
		}
	}
}
