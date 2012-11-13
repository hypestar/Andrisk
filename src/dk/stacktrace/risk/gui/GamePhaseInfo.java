package dk.stacktrace.risk.gui;

import android.content.Context;
import android.util.TypedValue;
import android.widget.RelativeLayout;
import android.widget.TextView;
import dk.stacktrace.risk.controller.Controller;
import dk.stacktrace.risk.game_logic.enumerate.GamePhase;

public class GamePhaseInfo extends RelativeLayout {
	private Controller control;
	
	private TextView gamePhaseText;
	private RelativeLayout.LayoutParams gamePhaseInfoLayoutParams;

	
	
	public GamePhaseInfo(Context context, Controller control, RelativeLayout mainLayout) {
		super(context);
		this.control = control;
		
		// GamePhaseInfo Info Layout Parameters 
		float density = getResources().getDisplayMetrics().density;
		gamePhaseInfoLayoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		gamePhaseInfoLayoutParams.rightMargin = (int) (5 * density);
		gamePhaseInfoLayoutParams.topMargin = (int) (5 * density);
		gamePhaseInfoLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		gamePhaseInfoLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
				
		// Player name
		gamePhaseText = new TextView(context);
		gamePhaseText.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 30);
		
		
		update();
	
		addView(gamePhaseText);
		
		mainLayout.addView(this, gamePhaseInfoLayoutParams);
	}
	

	public void update() 
	{
		String gp;
		
		switch (control.getGamePhase())
		{
		case INITIAL_REINFORCEMENT:
			gp = "Deployment Phase";
			break;
		case REINFORCEMENT:
			gp = "Reinforcement Phase";
			break;
		case ATTACK:
			gp = "Attack Phase";
			break;
		case TACTICALMOVE:
			gp = "Tactical Move Phase";
			break;
		default:
			gp = "Oops! Did I do that??";
			break;
		}
		gamePhaseText.setText(gp);
	}
}
