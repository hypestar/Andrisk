package dk.stacktrace.risk.gui;

import dk.stacktrace.risk.controller.Controller;
import dk.stacktrace.risk.game_logic.Territory;
import android.content.Context;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.RelativeLayout.LayoutParams;

public class PlayerInfo extends RelativeLayout {
	private Controller control;
	private ImageView playerImage;
	private TextView playerName;
	private RelativeLayout.LayoutParams playerInfoLayoutParams;
	private RelativeLayout mainLayout;
	
	public PlayerInfo(Context context, Controller control, RelativeLayout mainLayout) {
		super(context);
		this.control = control;
		this.mainLayout = mainLayout;
		playerImage = new ImageView(context);
		playerImage.setId(0);
		playerName = new TextView(context);
		update();
		
		
		// Player Info Layout Parameters 
		float density = getResources().getDisplayMetrics().density;
		playerInfoLayoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		playerInfoLayoutParams.leftMargin = (int) (5 * density);
		playerInfoLayoutParams.topMargin = (int) (5 * density);
		playerInfoLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		playerInfoLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
				
		this.setLayoutParams(playerInfoLayoutParams);
		
				
		// TextView Layout Parameters
		RelativeLayout.LayoutParams playerNamelp = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
		playerNamelp.addRule(RelativeLayout.LEFT_OF, playerImage.getId());
		playerNamelp.addRule(RelativeLayout.ALIGN_PARENT_TOP);
	
		addView(playerImage);
		addView(playerName, playerNamelp);
				
		mainLayout.addView(this);
	}

			
	private int getPlayerImage() 
	{
		switch (control.getActivePlayer().getArmyColor()) {
		case BLUE:
			return dk.stacktrace.risk.R.drawable.army_blue;
		case CYAN:
			return dk.stacktrace.risk.R.drawable.army_cyan;
		case GREEN:
			return dk.stacktrace.risk.R.drawable.army_green;
		case ORANGE:
			return dk.stacktrace.risk.R.drawable.army_orange;
		case PURPLE:
			return dk.stacktrace.risk.R.drawable.army_purple;
		case YELLOW:
			return dk.stacktrace.risk.R.drawable.army_yellow;
		default:
			return dk.stacktrace.risk.R.drawable.army_cyan;
		}
	}

	public void update() 
	{
		playerImage.setImageResource(getPlayerImage());
		playerName.setText(control.getActivePlayer().getName());
	}
}
