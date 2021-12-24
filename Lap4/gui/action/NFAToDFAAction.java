/* -- JFLAP 4.0 --
 *
 * Copyright information:
 *
 * Susan H. Rodger, Thomas Finley
 * Computer Science Department
 * Duke University
 * April 24, 2003
 * Supported by National Science Foundation DUE-9752583.
 *
 * Copyright (c) 2003
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms are permitted
 * provided that the above copyright notice and this paragraph are
 * duplicated in all such forms and that any documentation,
 * advertising materials, and other materials related to such
 * distribution and use acknowledge that the software was developed
 * by the author.  The name of the author may not be used to
 * endorse or promote products derived from this software without
 * specific prior written permission.
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND WITHOUT ANY EXPRESS OR
 * IMPLIED WARRANTIES, INCLUDING, WITHOUT LIMITATION, THE IMPLIED
 * WARRANTIES OF MERCHANTIBILITY AND FITNESS FOR A PARTICULAR PURPOSE.
 */
 
package gui.action;

import automata.fsa.FiniteStateAutomaton;
import automata.fsa.NFAToDFA;
import automata.AutomatonChecker;
import gui.environment.*;
import gui.environment.tag.CriticalTag;
import gui.deterministic.ConversionPane;
import javax.swing.JOptionPane;
import java.io.Serializable;
import java.awt.event.ActionEvent;
import java.awt.Dimension;

/**
 * This is a simple action for showing the DFA form of an NFA.
 * 
 * @author Thomas Finley
 */

public class NFAToDFAAction extends FSAAction {
    /**
     * Instantiates a new <CODE>NFAToDFAAction</CODE>.
     * @param automaton the automaton that input will be simulated on
     * @param environment the environment object that we shall add our
     * simulator pane to
     */
    public NFAToDFAAction(FiniteStateAutomaton automaton,
			  Environment environment) {
	super("Convert to DFA", null);
	this.automaton = automaton;
	this.environment = environment;
	/*putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke
	  (KeyEvent.VK_R, MAIN_MENU_MASK+InputEvent.SHIFT_MASK));*/
    }

    /**
     * Puts the DFA form in another window.
     * @param e the action event
     */
    public void actionPerformed(ActionEvent e) {
	if (automaton.getInitialState() == null) {
	    JOptionPane
		.showMessageDialog(environment, 
				   "The automaton needs an initial state.",
				   "No Initial State",
				   JOptionPane.ERROR_MESSAGE);
	    return;
	}

	AutomatonChecker ac = new AutomatonChecker();
	if(!ac.isNFA(automaton)) {
	    JOptionPane
		.showMessageDialog(environment,"This is not an NFA!",
				   "Not an NFA",
				   JOptionPane.ERROR_MESSAGE);
	    return;
	}
	
	ConversionPane convert = new ConversionPane
	    ((FiniteStateAutomaton)automaton.clone(),environment);
	environment.add(convert, "NFA to DFA", new CriticalTag() {});
	environment.setActive(convert);
    }

    /** The automaton. */
    private FiniteStateAutomaton automaton;
    /** The environment. */
    private Environment environment;
}
