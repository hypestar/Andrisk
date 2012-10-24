package dk.stacktrace.risk;

import java.util.ArrayList;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import dk.stacktrace.risk.controller.Controller;
import dk.stacktrace.risk.game_logic.Territory;
import dk.stacktrace.risk.gui.Army;
import dk.stacktrace.risk.gui.PlayerInfo;
import dk.stacktrace.risk.gui.TerritoryHighlight;

public class Main extends Activity {

	private Controller control;
	private ArrayList<Army> armies;
	private RelativeLayout mainLayout;
	private PlayerInfo playerInfo;
	private ArrayList<TerritoryHighlight> selectedTerritories;
	private TerritoryHighlight selectedTerritory;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        control = new Controller(this);
        control.createTestGame();
        control.dealTerritories();
        Log.v("MAIN", "after controller");
        
        
        mainLayout = new RelativeLayout(this);
        playerInfo = new PlayerInfo(this, control, mainLayout);
        
        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Force landscape orientation
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        //Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        //Set the risk board image as background 
        mainLayout.setBackgroundResource(R.drawable.board_colored);


        createArmies();
        
        setContentView(mainLayout);
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
    
    private void createTerritoryHighlight()
    {
    	ArrayList<Territory> allTerritories = control.getAllTerritories();
    	selectedTerritories = new ArrayList<TerritoryHighlight>();
    	
    	for(Territory territory : allTerritories)
    	{
    		selectedTerritories.add(new TerritoryHighlight(this, territory, mainLayout));
    	}
    }
    
    public void select(Territory territory)
	{
    	if(selectedTerritory !=null)
    	{
    		selectedTerritory.remove();
    	}
    	selectedTerritory = new TerritoryHighlight(this, territory, mainLayout);
	}
    
    
    public void select(ArrayList<Territory> territories)
    {

    	if(selectedTerritories != null)
    	{
    		for(TerritoryHighlight territory : selectedTerritories)
        	{
        		territory.remove();
        	}	
    	}
    	
    	selectedTerritories = new ArrayList<TerritoryHighlight>();
    	
    	for(Territory territory : territories)
    	{
    		selectedTerritories.add(new TerritoryHighlight(this, territory, mainLayout));
    	}
    }
    
    public PlayerInfo getPlayerInfo() {
		return playerInfo;
	}
    
    public void update()
    {
    	playerInfo.update();
    	updateArmies();
    }

    public void updateArmies()
    {
    	for (Army army : armies)
    	{
    		army.update();
    	}
    }
   
    
    

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
