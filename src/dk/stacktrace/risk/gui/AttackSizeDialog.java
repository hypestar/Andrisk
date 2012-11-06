package dk.stacktrace.risk.gui;

import dk.stacktrace.risk.Main;
import dk.stacktrace.risk.R;
import dk.stacktrace.risk.controller.Controller;
import dk.stacktrace.risk.game_logic.Territory;
import android.app.Dialog;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class AttackSizeDialog extends Dialog implements OnSeekBarChangeListener, android.view.View.OnClickListener{
	Controller control;
	Context context;
	LayoutInflater inflater;
	View layout;
	
	int attackingArmy;
	Territory source, target;
	
	SeekBar troopSeekBar;
	TextView sourceName, sourceTroops, targetName, targetTroops;
	Button attackBtn;
	

	public AttackSizeDialog(Context context,Controller control, ViewGroup rootElement)
	{
		super(context);
		
		this.context = context;
		this.control = control;
		this.source = control.getSourceSelection();
		this.target = control.getTargetSelection();
		
		setCancelable(false);
		
		inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		layout = inflater.inflate(R.layout.dialog_attack_size, rootElement);
		setContentView(layout);
		setTitle("Decide the size of the attacking army.");	
		
		troopSeekBar = (SeekBar) layout.findViewById(R.id.troopsSeekBar1);
		troopSeekBar.setMax(source.getArmySize() - 1);
		troopSeekBar.setProgress(0);
		troopSeekBar.setThumb(new BitmapDrawable(BitmapFactory.decodeResource(
		        context.getResources(), getPlayersColor())));
    	troopSeekBar.setOnSeekBarChangeListener(this);
		
		sourceName = (TextView) layout.findViewById(R.id.textSource);
		sourceName.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);
		sourceName.setText(this.source.getName());
		
		sourceTroops = (TextView) layout.findViewById(R.id.textSourceTroops);
		sourceTroops.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);
		sourceTroops.setText(this.source.getArmySize() + "");
		
		targetName = (TextView) layout.findViewById(R.id.textTarget);
		targetName.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);
		targetName.setText(this.target.getName());
		
		attackingArmy = troopSeekBar.getProgress();
		
		targetTroops = (TextView) layout.findViewById(R.id.textTargetTroops);
		targetTroops.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);
    	targetTroops.setText(attackingArmy + "");
    	
    	attackBtn = (Button) layout.findViewById(R.id.buttonMove);
    	attackBtn.setOnClickListener(this);
	}

	
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		
		attackingArmy = progress;
		sourceTroops.setText((source.getArmySize() - attackingArmy) + "");
		targetTroops.setText(attackingArmy + "");
	}

	public void onStartTrackingTouch(SeekBar seekBar) {
		
		
	}
	

	public void onStopTrackingTouch(SeekBar seekBar) {
		
		
	}


	public void onClick(View v) {
		if (((Button) v).getId() == R.id.buttonMove)
		{
			if (attackingArmy > 0)
			{
				source.moveTroops(attackingArmy);
				control.startBattle(attackingArmy);
				dismiss();
			}
			else
			{
				control.resetTerritorySelections();	
				((Main) context).update();
				dismiss();
			}
		}
	}
	
	// return the int from the R.java that matches the army image that corresponds to owners color
			private int getPlayersColor()
			{
				switch (source.getOwner().getArmyColor()) {
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
}
