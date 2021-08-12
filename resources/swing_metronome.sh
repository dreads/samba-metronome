#!/bin/sh
#############################################################
# (C) COPYRIGHT Property of Roderick Clint Bowser 2013      #
#############################################################
# Script to run the metronome for samba feel
# Tempo may be changed by modifying the com.bowsers.midiutil.tempo value
# 100 BPM is the default tempo
java -cp ./lib/metronome.jar -Dcom.bowsers.midiutil.config="./config/swing_pattern_input.properties" -Dcom.bowsers.midiutil.tempo=100 com.bowsers.midiutil.samba.MetronomePatternBuilder