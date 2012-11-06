package dk.stacktrace.risk.gui;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import dk.stacktrace.risk.R;
import dk.stacktrace.risk.controller.Controller;
import dk.stacktrace.risk.game_logic.mission.Mission;

public class MissionCardDialog extends Dialog implements OnClickListener{
	private Controller control;
	
	private LayoutInflater inflater;
	private View layout;

	private ImageView card;
	private Button okBtn;
	private boolean back;
	private Mission mission;
	
	
	
	public MissionCardDialog(Context context,Controller control, ViewGroup rootElement, Mission mission)
	{
		super(context);
		this.control = control;
		this.mission = mission;

		back = true;
		
		inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		layout = inflater.inflate(R.layout.dialog_mission, rootElement);
		setContentView(layout);
		
		setCanceledOnTouchOutside(true);
		
		setTitle("Secret Andrisk Mission has been viewed " + mission.getNumberOfViews() + " times.");
		
		
		card = (ImageView) findViewById(R.id.missionCard);
		card.setOnClickListener(this);
		
		okBtn = (Button) findViewById(R.id.ok);
		okBtn.setOnClickListener(this);
	}

	
	public void onClick(View v) {
		
		if(v instanceof ImageView)
		{
			if(back){
				card.setImageResource(getMissionCardImageResource());
				back = false;
				mission.registerMissionView();
				setTitle("Secret Andrisk Mission has been viewed " + mission.getNumberOfViews() + " times.");
			}
			else 
			{
				card.setImageResource(dk.stacktrace.risk.R.drawable.mission_cards_back);
				back = true;
			}
		}
		else if (v instanceof Button && ((Button)v).getId() == R.id.ok)
		{
			dismiss();
		}
	}
	
	private int getMissionCardImageResource()
	{
		String missionId = mission.getMissionId();
		
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
