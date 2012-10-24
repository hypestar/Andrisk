package dk.stacktrace.risk.gui;

import dk.stacktrace.risk.game_logic.Territory;
import android.content.Context;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RelativeLayout;


public class TerritoryHighlight extends ImageView{
	private Context context;
	private Territory territory;
	private RelativeLayout mainLayout;
	
	public TerritoryHighlight(Context context, Territory territory, RelativeLayout mainLayout) {
		super(context);
		this.context = context;
		this.territory = territory;
		this.mainLayout = mainLayout;
		setImageResource(getHighliteImageId());
		//setVisibility(INVISIBLE);
		mainLayout.addView(this, 1);
		Log.v("TerritoryHighlight", "created");
	}
	
	
	private int getHighliteImageId()
	{
		
		String territoryIdName = territory.getId().toString().toLowerCase();
		return getResources().getIdentifier("territory_" + territoryIdName + "_hl", "drawable",context.getPackageName());
	}
	
	public void remove()
	{
		mainLayout.removeView(this);
	}
	
	public void toggleHighlight()
	{
		if (getVisibility() == INVISIBLE)
		{
			setVisibility(VISIBLE);
		}
		else if(getVisibility() == VISIBLE)
		{
			setVisibility(INVISIBLE);
		}
	}
	
	public Territory getTerritory() {
		return territory;
	}

}
