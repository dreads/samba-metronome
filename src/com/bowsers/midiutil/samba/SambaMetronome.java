/* 
#############################################################
# (C) COPYRIGHT Property of Roderick Clint Bowser 2013      #
#############################################################
 */

package com.bowsers.midiutil.samba;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MetaEventListener;
import javax.sound.midi.MetaMessage;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.Synthesizer;
import javax.sound.midi.Transmitter;

public class SambaMetronome implements Constants {
	
	private Sequence metronomeSequence = null;
	private Synthesizer synthesizer = null;
	private Sequencer sequencer = null;
	private int tempo = METRONOME_DEFAULT_TEMPO;

	public void play() throws MidiUnavailableException, InvalidMidiDataException {
	
		sequencer = MidiSystem.getSequencer(false);
		sequencer.open();
		
		if (metronomeSequence!=null) {
			sequencer.setSequence(metronomeSequence);
		} else {
			throw new InvalidMidiDataException("Must set sequence before running.");
		}

		synthesizer = MidiSystem.getSynthesizer();
		synthesizer.open();
		Receiver	synthesizerReceiver = synthesizer.getReceiver();
		Transmitter	sequencerTransmitter = sequencer.getTransmitter();
		sequencerTransmitter.setReceiver(synthesizerReceiver);

		sequencer.addMetaEventListener(new MetaEventListener() {
			@Override
			public void meta(MetaMessage event) {
				if (event.getType() == 47) {
					sequencer.close();
					if (synthesizer != null) {
						synthesizer.close();
					}
				}
			}
		});

		sequencer.setLoopStartPoint(0);
		sequencer.setLoopEndPoint(-1);
		sequencer.setLoopCount(Sequencer.LOOP_CONTINUOUSLY);

		sequencer.setTempoInBPM(tempo);
		sequencer.start();
		
		Runtime.getRuntime().addShutdownHook(new Thread()
        {
            @Override
            public void run()
            {
                System.out.println("Stopping...");
                sequencer.stop();
                sequencer.close();
                if (synthesizer != null) {
					synthesizer.close();
				}
            }
        });
	}
	
	public void play(Sequence sequence, int explicitTempo) throws MidiUnavailableException, InvalidMidiDataException {
		setMetronomeSquence(sequence);
		setTempo(explicitTempo);
		play();
	}

	protected void setMetronomeSquence(Sequence metronomeSquence) {
		this.metronomeSequence = metronomeSquence;
	}

	protected void setTempo(int tempo) {
		this.tempo = tempo;
	}
}




