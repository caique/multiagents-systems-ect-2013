package br.unb.sma.utils.broadcast.agents;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

import java.util.ArrayList;

import br.unb.sma.utils.broadcast.components.Messages;

public class Broadcaster extends Agent {

	private static final long serialVersionUID = 1L;

	private String name;
	protected ArrayList<String> events;

	protected void setup() {
		Object[] args = getArguments();

		if (args != null && args.length > 0) {
			this.name = (String) args[0];
			new AID(name, AID.ISLOCALNAME);
		} else {
			this.name = "broadcaster";
			new AID(name, AID.ISLOCALNAME);
		}

		DFAgentDescription dfd = new DFAgentDescription();
		dfd.setName(getAID());

		ServiceDescription sd = new ServiceDescription();
		sd.setType("broadcast");
		sd.setName(name);
		dfd.addServices(sd);

		try {
			DFService.register(this, dfd);

			if (events == null) {
				events = new ArrayList<String>();
			}
		} catch (Exception e) {
			System.out.println(Messages.TRANSMISSION_CLOSED);
		}

		waitForReport();
	}

	private void waitForReport() {

		addBehaviour(new CyclicBehaviour(this) {

			private static final long serialVersionUID = 1L;

			@Override
			public void action() {
				MessageTemplate template = MessageTemplate.and(
						MessageTemplate.MatchPerformative(ACLMessage.INFORM),
						MessageTemplate.MatchLanguage("report"));

				ACLMessage report = myAgent.receive(template);

				if (report != null) {
//					System.out.println(report.getSender() + " did a report!");
//					events.add(report.getContent());
					System.out.println(report.getContent());
				}
			}
		});

		addBehaviour(new CyclicBehaviour(this) {

			private static final long serialVersionUID = 1L;

			@Override
			public void action() {
				MessageTemplate template = MessageTemplate.and(
						MessageTemplate.MatchPerformative(ACLMessage.INFORM),
						MessageTemplate.MatchLanguage("over"));

				ACLMessage over = myAgent.receive(template);

				if (over != null) {
					for (String message : events) {
						System.out.println(message);
					}

					 myAgent.doDelete();
				}
			}
		});
	}

	protected void takeDown() {
		System.out.println(Messages.TRANSMISSION_CLOSED);

		try {
			DFService.deregister(this);
		} catch (FIPAException e) {
		}
	}

}
