package com.bowsers.midiutil.samba;

/* 
#############################################################
# (C) COPYRIGHT Property of Roderick Clint Bowser 2013      #
#############################################################
 */
public interface Constants {
	
	@Deprecated int VELOCITY_NON_REFERENCE_NOTES 	= 32; 
	@Deprecated int VELOCITY_REFERENCE_NOTES 		= 64;
	
	int METRONOME_DEFAULT_TEMPO = 120;
	
	/* BEGIN SWINGPATTERN DEFAULTS */
	/* ==================================================================== */
	/* ============ Resolution defaults ============== */
	
	//Pulses per quarter
	int PATTERN_DEFAULT_PPQ = 48;
	
	//Scaling factor 
	int PATTERN_DEFAULT_RESOLUTION = 1;
	
	/* ============ ONE defaults ============== */
	//One never has a swing offset 
	
	//Default ONE channel. "9" is percussion
	int PATTERN_DEFAULT_ONE_CHANNEL 	= 9;
	
	//Default ONE instrument. "75" is clave
	int PATTERN_DEFAULT_ONE_INST 		= 75;
	
	//The ONE count velocity
	int PATTERN_DEFAULT_ONE_VELOCITY 	= 64;
	
	/* ============ E defaults ============== */
	//Default E channel. "9" is percussion
	int PATTERN_DEFAULT_E_CHANNEL 	= 9;
	
	//Default E instrument. "75" is clave
	int PATTERN_DEFAULT_E_INST 		= 75;
	
	//The E count velocity
	int PATTERN_DEFAULT_E_VELOCITY 	= 32;
	
	//The E swing offset
	int PATTERN_DEFAULT_E_SWING 	= 2;
	
	/* ============ AND defaults ============== */
	//Default AND channel. "9" is percussion
	int PATTERN_DEFAULT_AND_CHANNEL 	= 9;
	
	//Default AND instrument. "75" is clave
	int PATTERN_DEFAULT_AND_INST 		= 75;
	
	//The AND count velocity
	int PATTERN_DEFAULT_AND_VELOCITY 	= 32;
	
	//The AND swing offset
	int PATTERN_DEFAULT_AND_SWING 		= -2;
	
	/* ============ AH defaults ============== */
	//Default AH channel. "9" is percussion
	int PATTERN_DEFAULT_AH_CHANNEL 		= 9;
	
	//Default AH instrument. "75" is clave
	int PATTERN_DEFAULT_AH_INST 		= 75;
	
	//The AH count velocity
	int PATTERN_DEFAULT_AH_VELOCITY 	= 32;
	
	//The AH swing offset
	int PATTERN_DEFAULT_AH_SWING 		= -3;
	/* ==================================================================== */
	/* END SWINGPATTERN DEFAULTS */

}
