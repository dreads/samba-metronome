/* 
#############################################################
# (C) COPYRIGHT Property of Roderick Clint Bowser 2013      #
#############################################################
 */

package com.bowsers.midiutil.samba;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;

public class MetronomePatternBuilder implements Constants {

	public static void main(String[] args) {
		
		// Try to read in pattern config properties fed in via VM args, such as:
		// -Dcom.bowsers.midiutil.config="/path/to/input/props.properties"
		Properties inputPatternProps = new Properties();
		SwingPattern metronomePattern;
		int tempo = METRONOME_DEFAULT_TEMPO;
		
		if (System.getProperty("com.bowsers.midiutil.config") != null) {
			String patternConfigFilename = System.getProperty("com.bowsers.midiutil.config");
			File patternConfigFile = new File(patternConfigFilename);
			
			//Check that file is readable and not a directory
			if (!patternConfigFile.canRead() || patternConfigFile.isDirectory()) {
				System.out.println("Filename specified \"" + patternConfigFilename + "\" is not readable. Using defaults.");
			} else {
		    	try {
		     		inputPatternProps.load(new FileInputStream(patternConfigFilename));
		     		System.out.println("Input pattern properties = " + inputPatternProps);
		    	} catch (IOException ex) {
		    		System.out.println("Encountered error when reading \"" + patternConfigFilename + "\". Using defaults.");
		    		ex.printStackTrace();
		    	}
			}
		}
		
		String midiOutFilename = null;
		// Try to get the output filename via VM args, such as:
		// -Dcom.bowsers.midiutil.outfile="/path/to/output/midi.out"
		if (System.getProperty("com.bowsers.midiutil.outfile") != null) {
			midiOutFilename = System.getProperty("com.bowsers.midiutil.outfile");
		}
		
		
		if (System.getProperty("com.bowsers.midiutil.tempo") != null) {
			try {
				tempo = Integer.parseInt(System.getProperty("com.bowsers.midiutil.tempo"));
			} catch(NumberFormatException nfe) {
				System.out.println("Encountered error when setting tempo to \"" + System.getProperty("com.bowsers.midiutil.tempo") + "\". Using default.");
			}
		}


		
		MetronomePatternBuilder builder = new MetronomePatternBuilder();
		metronomePattern = new SwingPattern(inputPatternProps);
		
		/* Delegate to createMetronomeMidiFile to create the output.
		 * Exceptions bubbled up to here.
		 */
		try {
			builder.runMetronome(metronomePattern, midiOutFilename,tempo);
		} catch (Exception builderException) {
			System.out.println("Encountered a error when generating metronome pattern.");
			builderException.printStackTrace();
			System.exit(1);
		}
	}
	
	
	private void runMetronome(SwingPattern swingPattern, String midiOutFilename, int tempo) throws Exception {

		Sequence outSequence;
		
		try {
			outSequence = generateSwingSequence(swingPattern);
		} catch (InvalidMidiDataException invalidMidiEx) {
			System.out.println("The provided MIDI data was invalid.");
			throw invalidMidiEx;
		}
		
		if (outSequence != null) {
			try {
				if (midiOutFilename!=null) {
					File outputPatternFile = new File(midiOutFilename);
					
					//Check that file is writable and we're not overwriting a directory
					if (!outputPatternFile.canWrite() || outputPatternFile.isDirectory()) {
						System.out.println("Filename specified \"" + midiOutFilename + "\" is not writable.");
					}

					MidiSystem.write(outSequence, 0, outputPatternFile);
				}
			}
			catch (IOException ioError) {
				System.out.println("Encountered error while writing output file");
				throw ioError;
			}
			SambaMetronome player = new SambaMetronome();
			player.play(outSequence,tempo);
		}
	}
	
	private  static Sequence generateSwingSequence(SwingPattern pattern) throws InvalidMidiDataException{
		//System.out.println("Generating Swing sequence with: ");
		pattern.outputSettings();
		
		Sequence sequence;
		
		sequence = new Sequence(Sequence.PPQ, pattern.getPPQ() * pattern.getResolution());

		Track track = sequence.createTrack();
		
		track.add(createProgramEvent(0));
		//Perform 4 iterations, generating each 16th note in one beat
		for (int count = 0; count < 4; count++) {
			if (count == 0) {
				/***** ONE ******/
				//System.out.println("Tick " + count + " ");
				track.add(noteOn(pattern.getOneChannel(),
											pattern.getOneInst(),
											(count * 12 * pattern.getResolution()), 
											pattern.getOneVelocity()));
				track.add(noteOff(pattern.getOneChannel(),
											pattern.getOneInst(),
											(count * 12 * pattern.getResolution()) + 1));
				
			} else if (count == 1) {
				/***** E ******/
				//System.out.println("Tick " + count);
				track.add(noteOn(pattern.getEChannel(),
											pattern.getEInst(),
											(count * 12 * pattern.getResolution() + pattern.getESwing()),
											pattern.getEVelocity()));
				track.add(noteOff(pattern.getEChannel(),
											pattern.getEInst(),
											(count * 12 * pattern.getResolution()) + pattern.getESwing() + 1));
				
			} else if (count == 2) {
				/***** AND ******/
				//System.out.println("Tick " + count);
				track.add(noteOn(pattern.getAndChannel(),
											pattern.getAndInst(),
											(count * 12 * pattern.getResolution() + pattern.getAndSwing()),
											pattern.getAndVelocity()));
				track.add(noteOff(pattern.getAndChannel(),
											pattern.getAndInst(),
											(count * 12 * pattern.getResolution()) + pattern.getAndSwing() + 1));
				
			} else if (count == 3) {
				/***** AH ******/
				//System.out.println("Tick " + count);
				track.add(noteOn(pattern.getAhChannel(),
											pattern.getAhInst(),
											(count * 12  * pattern.getResolution() + pattern.getAhSwing()), 
											pattern.getAhVelocity()));
				track.add(noteOff(pattern.getAhChannel(),
											pattern.getAhInst(),
											(count * 12)  * pattern.getResolution() + pattern.getAhSwing() + 1));
			}
		}
		track.add(createProgramEvent(pattern.getPPQ() * pattern.getResolution() - 1));
		return sequence;

	}

	private static MidiEvent noteOn(int channel, int noteValue, long tickValue, int velocity) throws InvalidMidiDataException {
		return buildMidiEvent(ShortMessage.NOTE_ON, channel, noteValue, velocity, tickValue);
	}

	private static MidiEvent noteOff(int channel, int noteValue, long tickValue) throws InvalidMidiDataException {
		return buildMidiEvent(ShortMessage.NOTE_OFF, channel, noteValue, 0, tickValue);
	}
	
	private static MidiEvent buildMidiEvent(int noteCommand, int channel, int noteValue, int velocity, long tickValue) throws InvalidMidiDataException {
		ShortMessage message = new ShortMessage();
		message.setMessage(noteCommand, channel, noteValue, velocity);
		MidiEvent	event = new MidiEvent(message, tickValue);
		return event;
	}

	private static MidiEvent createProgramEvent(long tick) throws InvalidMidiDataException {
        ShortMessage message = new ShortMessage();
        message.setMessage(ShortMessage.PROGRAM_CHANGE, 9, 1, 0); 
        MidiEvent event = new MidiEvent( message, tick );
        return event;
	}
}
