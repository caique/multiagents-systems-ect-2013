package br.unb.sma.utils.broadcast.behaviours;

import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

public class ReportBehaviour extends OneShotBehaviour {

	private static final long serialVersionUID = 1L;

	private String message;
	private AID broadcaster;

	public ReportBehaviour(String message, AID broadcaster) {
		super();
		this.message = message;
		this.broadcaster = broadcaster;
	}

	@Override
	public void action() {
		ACLMessage report = new ACLMessage(ACLMessage.INFORM);
		report.addReceiver(broadcaster);
		report.setLanguage("report");
		report.setContent(message);
		myAgent.send(report);
	}
}
