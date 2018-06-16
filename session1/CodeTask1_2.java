/*
 * Copyright 2016 Ollie Bown
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.happybrackets.assignment_tasks.session1;

import javafx.application.Application;
import javafx.stage.Stage;
import net.beadsproject.beads.core.AudioContext;
import net.beadsproject.beads.data.Buffer;
import net.beadsproject.beads.ugens.Gain;
import net.beadsproject.beads.ugens.Noise;
import net.beadsproject.beads.ugens.WavePlayer;
import net.happybrackets.controller.gui.WaveformVisualiser;
import net.happybrackets.extras.assignment_autograding.BeadsChecker;

/**
 * This code is initialised exactly the same as in CodeTask1_1.
 *
 * Modify the code so that you have two WavePlayers, one is a sine tone at 500hz, and the other one is a square wave
 * tone at 750hz. The sine tone should be sending to the left channel with a gain of 0.1 and the right channel with a
 * gain of 0.2. The square tone should be connected to the right channel with a gain of 0.1 and the left channel with a
 * gain of 0.2.
 */
public class CodeTask1_2 extends Application implements BeadsChecker.BeadsCheckable {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        //the AudioContext
        AudioContext ac = new AudioContext();
        ac.start();
        //a StringBuffer used to record anything you want to print out
        StringBuffer buf = new StringBuffer();
        //do your work here, using the function below
        task(ac, buf);
        //say something to the console output
        System.out.println(buf.toString());
        //finally, this creates a window to visualise the waveform
        WaveformVisualiser.open(ac);
    }

    @Override
    public void task(AudioContext ac, StringBuffer buf, Object... args) {
        //********** do your work here ONLY **********
        WavePlayer sine = new WavePlayer(ac, 500f, Buffer.SINE);
        WavePlayer square = new WavePlayer(ac, 750f, Buffer.SQUARE);
        Gain g0 = new Gain(ac, 2, 0.1f);
        Gain g1 = new Gain(ac, 2, 0.2f);
        g0.addInput(0,sine,0); //Left channel sine tone, gain: 0.1
        g0.addInput(1,square,0); //Right channel square, gain: 0.1
        g1.addInput(0,square,0); // Left channel square tone, gain: 0.2
        g1.addInput(1,sine,0); //Right channel sine tone, gain: 0.2
        ac.out.addInput(g0);
        ac.out.addInput(g1);
        //********** do your work here ONLY **********
    }
}
