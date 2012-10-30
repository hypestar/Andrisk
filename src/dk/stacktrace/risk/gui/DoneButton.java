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

public class DoneButton extends RelativeLayout
{
	private Controller control;
	private RelativeLayout.LayoutParams buttonLayoutParams, buttonImageLayoutParams;
	private ImageView buttonImage;
	
	public DoneButton(Context context, RelativeLayout mainLayout, Controller control) {
		super(context);
		
		this.control = control;
		
		buttonLayoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		buttonLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		buttonLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		
		this.setLayoutParams(buttonLayoutParams);

		/*buttonImageLayoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		buttonImageLayoutParams.addRule(RelativeLayout.ALIGN_BOTTOM);
		buttonImageLayoutParams.addRule(RelativeLayout.ALIGN_LEFT);*/
		
		buttonImage = new ImageView(context);
		buttonImage.setImageResource(R.drawable.done_button);
		//buttonImage.setLayoutParams(buttonImageLayoutParams);
		
		addView(buttonImage);
		
		mainLayout.addView(this);
		setOnTouchListener(control);		
	}
}
