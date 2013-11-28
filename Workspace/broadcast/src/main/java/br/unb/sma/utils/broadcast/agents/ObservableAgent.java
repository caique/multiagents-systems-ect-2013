package br.unb.sma.utils.broadcast.agents;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import br.unb.sma.utils.broadcast.behaviours.ReportBehaviour;
import br.unb.sma.utils.broadcast.components.Messages;

public abstract class ObservableAgent extends Agent {

	private static final long serialVersionUID = 1L;
	protected AID broadcaster = null;

	protected void setup() {
	}

	protected void findBroadcast() {
		addBehaviour(new OneShotBehaviour(this) {

			private static final long serialVersionUID = 1L;

			@Override
			public void action() {
				System.out.println(myAgent.getAID()
						+ " is finding a Broadcaster!");

				DFAgentDescription template = new DFAgentDescription();
				ServiceDescription sd = new ServiceDescription();
				sd.setType("broadcast");
				template.addServices(sd);

				try {
					DFAgentDescription[] broadcastersFounded = DFService
							.search(myAgent, template);

					if (broadcastersFounded.length > 0) {
						System.out.println(myAgent.getAID()
								+ " found a Broadcaster!");
						broadcaster = broadcastersFounded[0].getName();
						done();
					} else {
						System.out.println(myAgent.getAID()
								+ " didn't find a Broadcaster!");
						block(1000);
						myAgent.addBehaviour(this);
					}
				} catch (FIPAException e) {
					System.out.println(Messages.BROADCASTER_NOT_FOUND);
				}
			}
		});
	}

	protected void reportThat(final String content) {
		if (broadcaster == null) {
			findBroadcast();
		}

		addBehaviour(new ReportBehaviour(content, broadcaster));

	}

	protected void takeDown() {
	}
}
