package dk.stacktrace.risk.gui;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import dk.stacktrace.risk.Main;
import dk.stacktrace.risk.R;
import dk.stacktrace.risk.controller.Controller;

public class QuitGameDialog extends Dialog implements OnClickListener{
	Controller control;
	
	LayoutInflater inflater;
	View layout;

	Button quitBtn, cancelBtn;
	Context context;
	
	public QuitGameDialog(Context context,Controller control, ViewGroup rootElement)
	{
		super(context);
		
		this.context = context;
		this.control = control;
		
		inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		layout = inflater.inflate(R.layout.dialog_quit_game, rootElement);
		setContentView(layout);
		setTitle("Quit Game ?");	
		
		
		cancelBtn = (Button) findViewById(R.id.cancel_quit_game_btn);
		cancelBtn.setOnClickListener(this);
		
		
		quitBtn = (Button) findViewById(R.id.quit_game_btn);
		quitBtn.setOnClickListener(this);
	}				

	
	public void onClick(View v) {
		if(((Button) v).getId() == R.id.cancel_quit_game_btn)
		{
			dismiss();
		}
		else if (((Button) v).getId() == R.id.quit_game_btn)
		{
			dismiss();
			((Main) context).finish();
		}
	}
}
