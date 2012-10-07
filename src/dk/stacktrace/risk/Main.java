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

public class Main extends Activity {

	private Controller control;
	private ArrayList<Army> allArmies;
	private RelativeLayout mainLayout;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v("MAIN", "1");
        control = new Controller();
        Log.v("MAIN", "2");
        control.create2PlayerTestGame();
        Log.v("MAIN", "3");
        control.dealTerritories();
        Log.v("MAIN", "After control");
        
        
        mainLayout = new RelativeLayout(this);
        
        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Force landscape orientation
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        //Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        //Set the risk board image as background 
        mainLayout.setBackgroundResource(R.drawable.board_colored);

        createArmies();
        //mainLayout.addView(new Army(this, new Territory("Hej")));
        setContentView(mainLayout);
        //setContentView(R.layout.activity_main);
    }

    private void createArmies() {
    	ArrayList<Territory> allTerritories = control.getAllTerritories();
    	Log.v("createArmies", "num of allTerritories " + allTerritories.size());
    	allArmies = new ArrayList<Army>();
    	
    	for(Territory territory : allTerritories)
    	{
    		allArmies.add(new Army(this, territory, mainLayout));
    	}
		
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
