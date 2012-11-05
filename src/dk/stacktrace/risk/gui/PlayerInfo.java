package dk.stacktrace.risk.gui;

import dk.stacktrace.risk.controller.Controller;
import dk.stacktrace.risk.game_logic.Territory;
import android.content.Context;
import android.util.TypedValue;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.RelativeLayout.LayoutParams;

public class PlayerInfo extends RelativeLayout {
	private Controller control;
	private ImageView playerImage;
	private TextView playerName;
	private TextView expectedReinforcement;
	private TextView deploy;
	private TextView numTerritories;
	private RelativeLayout.LayoutParams playerInfoLayoutParams;
	private RelativeLayout mainLayout;
	
	
	public PlayerInfo(Context context, Controller control, RelativeLayout mainLayout) {
		super(context);
		this.control = control;
		this.mainLayout = mainLayout;
		
		// Player Info Layout Parameters 
		float density = getResources().getDisplayMetrics().density;
		playerInfoLayoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
		playerInfoLayoutParams.leftMargin = (int) (5 * density);
		playerInfoLayoutParams.topMargin = (int) (5 * density);
		playerInfoLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		playerInfoLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		this.setLayoutParams(playerInfoLayoutParams);
		
		
		// Players army image
		playerImage = new ImageView(context);
		playerImage.setId(1);
		RelativeLayout.LayoutParams armyImage_lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		armyImage_lp.addRule(RelativeLayout.ALIGN_TOP);
		armyImage_lp.addRule(RelativeLayout.ALIGN_LEFT);
		playerImage.setLayoutParams(armyImage_lp);
				
		// Player name
		playerName = new TextView(context);
		playerName.setId(2);
		playerName.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 30);
		RelativeLayout.LayoutParams playerName_lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		playerName_lp.addRule(RelativeLayout.RIGHT_OF, playerImage.getId());
		playerName_lp.addRule(RelativeLayout.ALIGN_TOP);
		playerName.setLayoutParams(playerName_lp);
		
		// Expected reinforcement
		expectedReinforcement = new TextView(context);
		expectedReinforcement.setId(3);
		RelativeLayout.LayoutParams expectRein_lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		expectRein_lp.addRule(RelativeLayout.BELOW, playerImage.getId());
		expectRein_lp.addRule(ALIGN_LEFT);
		expectedReinforcement.setLayoutParams(expectRein_lp);

		// Number of troops to deploy
		deploy = new TextView(context);
		deploy.setId(4);
		RelativeLayout.LayoutParams deploy_lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		deploy_lp.addRule(RelativeLayout.BELOW, expectedReinforcement.getId());
		deploy_lp.addRule(ALIGN_LEFT);
		deploy.setLayoutParams(deploy_lp);
		
		/*// Number of territories
		numTerritories = new TextView(context);
		numTerritories.setId(5);
		RelativeLayout.LayoutParams numTerritories_lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		deploy_lp.addRule(RelativeLayout.BELOW, deploy.getId());
		deploy_lp.addRule(ALIGN_LEFT);
		deploy.setLayoutParams(numTerritories_lp);*/
		
		update();
		
		addView(playerImage);
		addView(playerName);
		addView(expectedReinforcement);		
		addView(deploy);
		//addView(numTerritories);
		
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
		expectedReinforcement.setText("Expected Reinforcement : " + control.calcReinforcementBonus(control.getActivePlayer()));
		deploy.setText("Troops to deploy : " + control.getActivePlayer().getNumOfTroopsToDeploy());
		//numTerritories.setText("Territories : " + control.getNumberOfTerritoriesOwnedBy(control.getActivePlayer()));
	}
}
