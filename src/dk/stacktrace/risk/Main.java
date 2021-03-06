package dk.stacktrace.risk;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import dk.stacktrace.risk.controller.Controller;
import dk.stacktrace.risk.game_logic.Territory;
import dk.stacktrace.risk.game_logic.mission.Mission;
import dk.stacktrace.risk.gui.Army;
import dk.stacktrace.risk.gui.AttackDialog;
import dk.stacktrace.risk.gui.AttackSizeDialog;
import dk.stacktrace.risk.gui.DoneButton;
import dk.stacktrace.risk.gui.EndTurnDialog;
import dk.stacktrace.risk.gui.GamePhaseInfo;
import dk.stacktrace.risk.gui.MissionCardButton;
import dk.stacktrace.risk.gui.MissionCardDialog;
import dk.stacktrace.risk.gui.MissionWinnerDialog;
import dk.stacktrace.risk.gui.PlayerInfo;
import dk.stacktrace.risk.gui.PlayerStats;
import dk.stacktrace.risk.gui.QuitGameDialog;
import dk.stacktrace.risk.gui.TacticalMoveDialog;
import dk.stacktrace.risk.gui.TerritoryHighlight;
import dk.stacktrace.risk.gui.WinnerDialog;

public class Main extends Activity {

	private Controller control;
	private ArrayList<Army> armies;
	private RelativeLayout mainLayout;
	private PlayerInfo playerInfo;
	private PlayerStats playerStats;
	private ArrayList<TerritoryHighlight> selectedTerritories;
	private TerritoryHighlight selectedSourceTerritory, selectedTargetTerritory;
	private DoneButton doneButton;
	private MissionCardButton missionCardButton;
	private ArrayList<String> playerNames;
	private GamePhaseInfo gamePhaseInfo;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        Bundle extras = getIntent().getExtras();
        if(extras != null)
        {
        	playerNames = new ArrayList<String>();	
        	playerNames.add(extras.getString(CreatePlayers.PLAYER1NAME));
        	playerNames.add(extras.getString(CreatePlayers.PLAYER2NAME));
        	playerNames.add(extras.getString(CreatePlayers.PLAYER3NAME));
        	playerNames.add(extras.getString(CreatePlayers.PLAYER4NAME));
        	playerNames.add(extras.getString(CreatePlayers.PLAYER5NAME));
        	playerNames.add(extras.getString(CreatePlayers.PLAYER6NAME));
        }
        else
        {
        	System.out.println("Bummer, no extras received!");
        }
        
        GameSound.initSounds(this); // preload game sounds 
        
        control = new Controller(this);
        control.startNewGame(playerNames);
        //control.createTestGame();
        //control.dealTerritories();
         
        mainLayout = new RelativeLayout(this);
        playerInfo = new PlayerInfo(this, control, mainLayout);
        playerStats = new PlayerStats(this, control, mainLayout);
        gamePhaseInfo = new GamePhaseInfo(this, control, mainLayout);
        
        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Force landscape orientation
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        //Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        //Set the risk board image as background 
        mainLayout.setBackgroundResource(R.drawable.board_colored);

        doneButton = new DoneButton(this, mainLayout, control);
        missionCardButton = new MissionCardButton(this, mainLayout, control);
        
        createArmies();
        selectedSourceTerritory = null;
        selectedTargetTerritory = null;
        setContentView(mainLayout);
    }
    
    @Override
    public void onBackPressed() {
    	(new QuitGameDialog(this, control, (ViewGroup)getWindow().getCurrentFocus())).show();
    }

    public void rematch()
    {
    	createArmies();
        selectedSourceTerritory = null;
        selectedTargetTerritory = null;
    }
    
    private void createArmies() {
    	ArrayList<Territory> allTerritories = control.getAllTerritories();
    	Log.v("createArmies", "num of allTerritories " + allTerritories.size());
    	armies = new ArrayList<Army>();
    	
    	for(Territory territory : allTerritories)
    	{
    		armies.add(new Army(this, territory, mainLayout, control));
    	}
	}

    
    public PlayerInfo getPlayerInfo() {
		return playerInfo;
	}
    
    public void update()
    {
    	playerInfo.update();
    	playerStats.update();
    	gamePhaseInfo.update();
    	updateArmies();
    	updateTerritorySelections();
    }

    public void updateArmies()
    {
    	for (Army army : armies)
    	{
    		army.update();
    	}
    }
   
    public void updateTerritorySelections() {
		
		// Source selection
		if (control.getSourceSelection() != null)
		{
			if(selectedSourceTerritory != null)
			{
				selectedSourceTerritory.remove();
			}
			selectedSourceTerritory = new TerritoryHighlight(this, control.getSourceSelection(), mainLayout);
		}
		else if (control.getSourceSelection() == null && selectedSourceTerritory != null)
		{
			selectedSourceTerritory.remove();
			selectedSourceTerritory = null;
		}
		
		// Target selection
		if (control.getTargetSelection() != null)
		{
			if(selectedTargetTerritory != null)
			{
				selectedTargetTerritory.remove();
			}
			selectedTargetTerritory= new TerritoryHighlight(this, control.getTargetSelection(), mainLayout);
		}
		else if (control.getTargetSelection() == null && selectedTargetTerritory != null)
		{
			selectedTargetTerritory.remove();
			selectedTargetTerritory = null;
		}
	}

	public void tacticalMoveDialog(Territory source, Territory target) {
    	TacticalMoveDialog dialog = new TacticalMoveDialog(this, control, (ViewGroup)getCurrentFocus(), source, target);
    	dialog.show();
	}
	
	public void attackDialog() {
    	AttackDialog dialog = new AttackDialog(this, control, (ViewGroup)getWindow().getCurrentFocus());
    	dialog.show();
	}
	
	public void endTurnDialog() {
		EndTurnDialog dialog = new EndTurnDialog(this, control, (ViewGroup)getWindow().getCurrentFocus());
    	dialog.show();
	}
    
	public void attackSizeDialog() {
    	AttackSizeDialog dialog = new AttackSizeDialog(this, control, (ViewGroup)getWindow().getCurrentFocus());
    	dialog.show();
	}

	public void winnerDialog() {
    	WinnerDialog dialog = new WinnerDialog(this, control, (ViewGroup)getWindow().getCurrentFocus());
    	dialog.show();
	}
	
	public void missionWinnerDialog() {
    	MissionWinnerDialog dialog = new MissionWinnerDialog(this, control, (ViewGroup)getWindow().getCurrentFocus());
    	dialog.show();
	}
	
	public void missionCardDialog(Mission mission) {
    	MissionCardDialog dialog = new MissionCardDialog(this, control, (ViewGroup)getWindow().getCurrentFocus(), mission);
    	dialog.show();
	}
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
