package dk.stacktrace.risk;

import java.util.HashMap;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

public class GameSound
{
	    public static final int DEPLOY_CLICK = dk.stacktrace.risk.R.raw.deploy_click;
	    public static final int SELECT_TERRITORY = dk.stacktrace.risk.R.raw.select;
	    public static final int VICTORY = dk.stacktrace.risk.R.raw.victory;
	    public static final int TACTICAL_MOVE_PHASE = dk.stacktrace.risk.R.raw.tactical_move_phase; 
	    public static final int ATTACK_PHASE = dk.stacktrace.risk.R.raw.attack_phase; 
	    public static final int TROOP_DEPLOYMENT = dk.stacktrace.risk.R.raw.deployment; 
	    public static final int REINFORCEMENT_PHASE = dk.stacktrace.risk.R.raw.reinforcement_phase; 
	    public static final int GUNSHOT = dk.stacktrace.risk.R.raw.ak47; 

	    
	    private static SoundPool soundPool;
	    private static HashMap<Integer,Integer> soundPoolMap;

	    /** Populate the SoundPool*/
	    public static void initSounds(Context context) {
	    soundPool = new SoundPool(2, AudioManager.STREAM_MUSIC, 100);
	    soundPoolMap = new HashMap<Integer,Integer>(8);     

	    soundPoolMap.put( TROOP_DEPLOYMENT, soundPool.load(context, dk.stacktrace.risk.R.raw.deployment, 6) );
	    soundPoolMap.put( ATTACK_PHASE, soundPool.load(context, dk.stacktrace.risk.R.raw.attack_phase, 5) );
	    soundPoolMap.put( DEPLOY_CLICK, soundPool.load(context, dk.stacktrace.risk.R.raw.deploy_click, 1) );
	    soundPoolMap.put( SELECT_TERRITORY, soundPool.load(context, dk.stacktrace.risk.R.raw.select, 2) );
	    soundPoolMap.put( VICTORY, soundPool.load(context, dk.stacktrace.risk.R.raw.victory, 3) );
	    soundPoolMap.put( TACTICAL_MOVE_PHASE, soundPool.load(context, dk.stacktrace.risk.R.raw.tactical_move_phase, 4) );
	    soundPoolMap.put( REINFORCEMENT_PHASE, soundPool.load(context, dk.stacktrace.risk.R.raw.reinforcement_phase, 7) );
	    soundPoolMap.put( GUNSHOT, soundPool.load(context, dk.stacktrace.risk.R.raw.ak47, 8) );
	    }
	    
	    
	    
	    /** Play a given sound in the soundPool */
	    public static void playSound(Context context, int soundID) {
	   if(soundPool == null || soundPoolMap == null){
	      initSounds(context);
	   }
	       float volume = 0.5f;// whatever in the range = 0.0 to 1.0

	       // play sound with same right and left volume, with a priority of 1, 
	       // zero repeats (i.e play once), and a playback rate of 1f
	      //soundPool.play(soundID, leftVolume, rightVolume, priority, loop, rate)
	        soundPool.play(soundPoolMap.get(soundID), volume, volume, 1, 0, 1f);
	    }

}
	

