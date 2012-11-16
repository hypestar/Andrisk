package dk.stacktrace.risk.gui;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import dk.stacktrace.risk.controller.Controller;
import dk.stacktrace.risk.game_logic.Territory;

public class Army extends RelativeLayout
{
	private Controller control;
	private Territory territory;
	private ImageView armyImage;
	private TextView armyCount;
	private int leftMargin = 0, rightMargin = 0, topMargin = 0, bottomMargin = 0;
	private RelativeLayout.LayoutParams armyLayoutParams;
	
	public Army(Context context, Territory terrritory, RelativeLayout mainLayout, Controller control) {
		super(context);
		this.territory = terrritory;
		this.control = control;
		
		
		// Army Layout Parameters 
		float density = getResources().getDisplayMetrics().density;
		armyLayoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		setArmyPosition();		
		armyLayoutParams.leftMargin = (int) (leftMargin * density); 
		armyLayoutParams.rightMargin = (int) (rightMargin * density);
		armyLayoutParams.topMargin = (int) (topMargin * density);
		armyLayoutParams.bottomMargin = (int)(bottomMargin * density);
		
		this.setLayoutParams(armyLayoutParams);

		
		// TextView Layout Parameters
		RelativeLayout.LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		lp.addRule(RelativeLayout.CENTER_IN_PARENT);
		
		armyCount = new TextView(context);
		armyImage = new ImageView(context);
		
		update();
		
		addView(armyImage);
		addView(armyCount, lp);
		
		mainLayout.addView(this);
		setOnClickListener(control);		
	}
	
	public void reinforce(int numOfTroops)
	{
		territory.reinforce(numOfTroops);
		armyCount.setText(territory.getArmySize() + "");
	}

	public void update()
	{
		armyCount.setText("" + this.territory.getArmySize());
		armyImage.setImageResource(getArmyColor());
	}
	
	private void setArmyPosition() {
		switch (territory.getId()) {
		case SIB:
			rightMargin = 366;
			topMargin = 129;
			armyLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
			armyLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
			break;
		case YAK:
			rightMargin = 288;
			topMargin = 79;
			armyLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
			armyLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
			break;
		case KAM:
			rightMargin = 201;
			topMargin = 78;
			armyLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
			armyLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);			
			break;
		case URA:
			rightMargin = 425;
			topMargin = 155;
			armyLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
			armyLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
			break;
		case IRK:
			rightMargin = 296;
			topMargin = 170;
			armyLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
			armyLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
			break;
		case MON:
			rightMargin = 280;
			topMargin = 241;
			armyLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
			armyLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
			break;
		case JAP:
			rightMargin = 170;
			topMargin = 243;
			armyLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
			armyLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
			break;
		case AFG:
			rightMargin = 445;
			topMargin = 259;
			armyLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
			armyLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
			break;
		case CHI:
			rightMargin = 321;
			topMargin = 312;
			armyLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
			armyLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
			break;
		case MID:
			bottomMargin = 335;
			rightMargin = 515;
			armyLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
			armyLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
			break;
		case IDA:
			bottomMargin = 336;
			rightMargin = 376;
			armyLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
			armyLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
			break;
		case SIA:
			bottomMargin = 299;
			rightMargin = 284;
			armyLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
			armyLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
			break;
		case NAF:
			bottomMargin = 271;
			leftMargin = 561;
			armyLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
			armyLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
			break;
		case EGY:
			bottomMargin = 304;
			rightMargin = 593;
			armyLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
			armyLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
			break;
		case EAF:
			bottomMargin = 221;
			rightMargin = 539;
			armyLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
			armyLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
			break;
		case CON:
			bottomMargin = 179;
			rightMargin = 591;
			armyLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
			armyLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
			break;
		case SAF:
			bottomMargin = 77;
			rightMargin = 586;
			armyLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
			armyLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
			break;
		case MAD:
			bottomMargin = 81;
			rightMargin = 484;
			armyLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
			armyLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
			break;
		case IDO:
			bottomMargin = 181;
			rightMargin = 272;
			armyLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
			armyLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
			break;
		case NGU:
			bottomMargin = 220;
			rightMargin = 178;
			armyLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
			armyLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
			break;
		case WAU:
			bottomMargin = 77;
			rightMargin = 223;
			armyLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
			armyLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
			break;
		case EAU:
			bottomMargin = 95;
			rightMargin = 130;
			armyLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
			armyLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
			break;
		case ICE:
			leftMargin = 519;
			topMargin = 129;
			armyLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
			armyLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
			break;
		case SCA:
			topMargin = 115;
			armyLayoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
			armyLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
			break;
		case GBR:
			leftMargin = 492;
			topMargin = 211;
			armyLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
			armyLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
			break;
		case NEU:
			leftMargin = 599;
			topMargin = 227;
			armyLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
			armyLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
			break;
		case SEU:
			leftMargin = 600;
			topMargin = 304;
			armyLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
			armyLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
			break;
		case WEU:
			leftMargin = 526;
			topMargin = 313;
			armyLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
			armyLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
			break;
		case UKR:
			rightMargin = 538;
			topMargin = 187;
			armyLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
			armyLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
			break;
		case ALA:
			leftMargin = 119;
			topMargin = 91;
			armyLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
			armyLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
			break;
		case NWT:
			leftMargin = 219;
			topMargin = 90;
			armyLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
			armyLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
			break;
		case GRE:
			leftMargin = 428;
			topMargin = 54;
			armyLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
			armyLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
			break;
		case ALB:
			leftMargin = 208;
			topMargin = 152;
			armyLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
			armyLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
			break;
		case ONT:
			leftMargin = 288;
			topMargin = 167;
			armyLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
			armyLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
			break;
		case QUE:
			leftMargin = 356;
			topMargin = 172;
			armyLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
			armyLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
			break;
		case WUS:
			leftMargin = 213;
			topMargin = 239;
			armyLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
			armyLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
			break;
		case EUS:
			leftMargin = 298;
			topMargin = 260;
			armyLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
			armyLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
			break;
		case CEN:
			leftMargin = 221;
			topMargin = 330;
			armyLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
			armyLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
			break;
		case VEN:
			bottomMargin = 317;
			leftMargin = 305;
			armyLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
			armyLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
			break;
		case BRA:
			bottomMargin = 244;
			leftMargin = 382;
			armyLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
			armyLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
			break;
		case PER:
			bottomMargin = 216;
			leftMargin = 308;
			armyLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
			armyLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
			break;
		case ARG:
			bottomMargin = 120;
			leftMargin = 320;
			armyLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
			armyLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
			break;
		default:
			break;
		}
		
	}

	// return the int from the R.java that matches the army image that corresponds to owners color
	private int getArmyColor()
	{
		switch (territory.getOwner().getArmyColor()) {
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
	
	public Territory getTerritory() {
		return territory;
	}
	
	
}
