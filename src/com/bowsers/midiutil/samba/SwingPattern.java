/* 
#############################################################
# (C) COPYRIGHT Property of Roderick Clint Bowser 2013      #
#############################################################
 */
package com.bowsers.midiutil.samba;

import java.util.Properties;

public class SwingPattern implements Constants{

	//Initialize members to default values specified in Constants class
	private int PPQ 		= PATTERN_DEFAULT_PPQ;
	private int resolution 	= PATTERN_DEFAULT_RESOLUTION;
	
	private int oneChannel 	= PATTERN_DEFAULT_ONE_CHANNEL;
	private int oneInst 	= PATTERN_DEFAULT_ONE_INST;
	private int oneVelocity = PATTERN_DEFAULT_ONE_VELOCITY;

	private int eChannel 	= PATTERN_DEFAULT_E_CHANNEL;
	private int eInst 		= PATTERN_DEFAULT_E_INST;
	private int eVelocity 	= PATTERN_DEFAULT_E_VELOCITY;
	private int eSwing 		= PATTERN_DEFAULT_E_SWING;

	private int andChannel 		= PATTERN_DEFAULT_AND_CHANNEL;
	private int andInst 		= PATTERN_DEFAULT_AND_INST;
	private int andVelocity 	= PATTERN_DEFAULT_AND_VELOCITY;
	private int andSwing 		= PATTERN_DEFAULT_AND_SWING;

	private int ahChannel 		= PATTERN_DEFAULT_AH_CHANNEL;
	private int ahInst 			= PATTERN_DEFAULT_AH_INST;
	private int ahVelocity 		= PATTERN_DEFAULT_AH_VELOCITY;
	private int ahSwing 		= PATTERN_DEFAULT_AH_SWING;
	
	public SwingPattern(Properties inputProps) {
		
		String propNames[] ={ 
				"PPQ", "RESOLUTION", "ONE_CHANNEL", "ONE_INST", "ONE_VELOCITY", 
				"E_CHANNEL", "E_INST", "E_VELOCITY", "E_SWING", "AND_CHANNEL", 
				"AND_INST", "AND_VELOCITY", "AND_SWING", "AH_CHANNEL", "AH_INST", 
				"AH_VELOCITY", "AH_SWING"
		};
		
		for (int propCounter = 0; propCounter < propNames.length; propCounter++) {
			if (inputProps.getProperty(propNames[propCounter]) != null) {
				try {
					if (propNames[propCounter].equals("PPQ")) {
						setPPQ(Integer.parseInt(inputProps.getProperty(propNames[propCounter])));
					} else if (propNames[propCounter].equals("RESOLUTION")) {
						setResolution(Integer.parseInt(inputProps.getProperty(propNames[propCounter])));
					} else if (propNames[propCounter].equals("ONE_CHANNEL")) {
						setOneChannel(Integer.parseInt(inputProps.getProperty(propNames[propCounter])));
					} else if (propNames[propCounter].equals("ONE_INST")) {
						setOneInst(Integer.parseInt(inputProps.getProperty(propNames[propCounter])));
					} else if (propNames[propCounter].equals("ONE_VELOCITY")) {
						setOneVelocity(Integer.parseInt(inputProps.getProperty(propNames[propCounter])));
					} else if (propNames[propCounter].equals("E_CHANNEL")) {
						setEChannel(Integer.parseInt(inputProps.getProperty(propNames[propCounter])));
					} else if (propNames[propCounter].equals("E_INST")) {
						setEInst(Integer.parseInt(inputProps.getProperty(propNames[propCounter])));
					} else if (propNames[propCounter].equals("E_VELOCITY")) {
						setEVelocity(Integer.parseInt(inputProps.getProperty(propNames[propCounter])));
					} else if (propNames[propCounter].equals("E_SWING")) {
						setESwing(Integer.parseInt(inputProps.getProperty(propNames[propCounter])));
					} else if (propNames[propCounter].equals("AND_CHANNEL")) {
						setAndChannel(Integer.parseInt(inputProps.getProperty(propNames[propCounter])));
					} else if (propNames[propCounter].equals("AND_INST")) {
						setAndInst(Integer.parseInt(inputProps.getProperty(propNames[propCounter])));
					} else if (propNames[propCounter].equals("AND_VELOCITY")) {
						setAndVelocity(Integer.parseInt(inputProps.getProperty(propNames[propCounter])));
					} else if (propNames[propCounter].equals("AND_SWING")) {
						setAndSwing(Integer.parseInt(inputProps.getProperty(propNames[propCounter])));
					} else if (propNames[propCounter].equals("AH_CHANNEL")) {
						setAhChannel(Integer.parseInt(inputProps.getProperty(propNames[propCounter])));
					} else if (propNames[propCounter].equals("AH_INST")) {
						setAhInst(Integer.parseInt(inputProps.getProperty(propNames[propCounter])));
					} else if (propNames[propCounter].equals("AH_VELOCITY")) {
						setAhVelocity(Integer.parseInt(inputProps.getProperty(propNames[propCounter])));
					} else if (propNames[propCounter].equals("AH_SWING")) {
						setAhSwing(Integer.parseInt(inputProps.getProperty(propNames[propCounter])));
					}
				} catch (NumberFormatException nfe) {
					outputParsingError(propNames[propCounter], inputProps.getProperty(propNames[propCounter]));
				}
			}
		}
	}
	
	private void outputParsingError(String propertyName, String propertyValue) {
		System.out.println("Could not parse \"" + propertyValue + "\" into integer value for \"" + propertyName + "\"" + ". Using default.");
	}
	
	protected void outputSettings() {
			System.out.println("PPQ:        " + getPPQ());
			System.out.println("Resolution: " + getResolution());
			System.out.println("One Channel:    " + getOneChannel());
			System.out.println("One Instrument: " + getOneInst());
			System.out.println("One Velocity:   " + getOneVelocity());
			System.out.println("E Channel:    " + getEChannel());
			System.out.println("E Instrument: " + getEInst());
			System.out.println("E Velocity:   " + getEVelocity());
			System.out.println("E Swing:      " + getESwing());
			System.out.println("And Channel:    " + getAndChannel());
			System.out.println("And Instrument: " + getAndInst());
			System.out.println("And Velocity:   " + getAndVelocity());
			System.out.println("And Swing:      " + getAndSwing());
			System.out.println("Ah Channel:    " + getAhChannel());
			System.out.println("Ah Instrument: " + getAhInst());
			System.out.println("Ah Velocity:   " + getAhVelocity());
			System.out.println("Ah Swing:      " + getAhSwing());
	}
	
	protected int getPPQ() {
		return PPQ;
	}
	protected void setPPQ(int pPQ) {
		PPQ = pPQ;
	}
	protected int getResolution() {
		return resolution;
	}
	protected void setResolution(int resolution) {
		this.resolution = resolution;
	}
	protected int getOneChannel() {
		return oneChannel;
	}
	protected void setOneChannel(int oneChannel) {
		this.oneChannel = oneChannel;
	}
	protected int getOneInst() {
		return oneInst;
	}
	protected void setOneInst(int oneInst) {
		this.oneInst = oneInst;
	}
	protected int getOneVelocity() {
		return oneVelocity;
	}
	protected void setOneVelocity(int oneVelocity) {
		this.oneVelocity = oneVelocity;
	}
	protected int getEChannel() {
		return eChannel;
	}
	protected void setEChannel(int eChannel) {
		this.eChannel = eChannel;
	}
	protected int getEInst() {
		return eInst;
	}
	protected void setEInst(int eInst) {
		this.eInst = eInst;
	}
	protected int getEVelocity() {
		return eVelocity;
	}
	protected void setEVelocity(int eVelocity) {
		this.eVelocity = eVelocity;
	}
	protected int getESwing() {
		return eSwing;
	}
	protected void setESwing(int eSwing) {
		this.eSwing = eSwing;
	}
	protected int getAndChannel() {
		return andChannel;
	}
	protected void setAndChannel(int andChannel) {
		this.andChannel = andChannel;
	}
	protected int getAndInst() {
		return andInst;
	}
	protected void setAndInst(int andInst) {
		this.andInst = andInst;
	}
	protected int getAndVelocity() {
		return andVelocity;
	}
	protected void setAndVelocity(int andVelocity) {
		this.andVelocity = andVelocity;
	}
	protected int getAndSwing() {
		return andSwing;
	}
	protected void setAndSwing(int andSwing) {
		this.andSwing = andSwing;
	}
	protected int getAhChannel() {
		return ahChannel;
	}
	protected void setAhChannel(int ahChannel) {
		this.ahChannel = ahChannel;
	}
	protected int getAhInst() {
		return ahInst;
	}
	protected void setAhInst(int ahInst) {
		this.ahInst = ahInst;
	}
	protected int getAhVelocity() {
		return ahVelocity;
	}
	protected void setAhVelocity(int ahVelocity) {
		this.ahVelocity = ahVelocity;
	}
	protected int getAhSwing() {
		return ahSwing;
	}
	protected void setAhSwing(int ahSwing) {
		this.ahSwing = ahSwing;
	}

}
