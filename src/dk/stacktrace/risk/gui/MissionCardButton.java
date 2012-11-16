package dk.stacktrace.risk.gui;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import dk.stacktrace.risk.R;
import dk.stacktrace.risk.controller.Controller;
import dk.stacktrace.risk.game_logic.Territory;

public class MissionCardButton extends RelativeLayout
{
	private Controller control;
	private RelativeLayout.LayoutParams buttonLayoutParams, buttonImageLayoutParams;
	private ImageView buttonImage;
	
	public MissionCardButton(Context context, RelativeLayout mainLayout, Controller control) {
		super(context);
		
		this.control = control;
		
		
		float density = getResources().getDisplayMetrics().density;
		buttonLayoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		buttonLayoutParams.rightMargin = (int) (5 * density);
		buttonLayoutParams.bottomMargin = (int) (5 * density);
		buttonLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		buttonLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		this.setLayoutParams(buttonLayoutParams);

		buttonImage = new ImageView(context);
		buttonImage.setImageResource(R.drawable.mission_card_button);
		
		addView(buttonImage);
		
		mainLayout.addView(this);
		setOnClickListener(control);		
	}
}
