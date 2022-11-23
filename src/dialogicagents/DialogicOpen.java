/**
 * @file DialogicStarter
 */
package dialogicagents;

import agents.DialogicPlayer;
import jade.core.AID;
import jade.lang.acl.ACLMessage;
import java.util.ArrayList;

/**
 *
 * @author Anatoli Grishenko <Anatoli.Grishenko@gmail.com>
 */
public class DialogicOpen extends DialogicFollower {

    @Override
    public Status myWait() {
        Players = findPlayers();
        if (!isOpen(outbox)) {
            return Status.SEND;
        }
        if (isComplete(outbox)) {
            return Status.RECEIVE;
        }
        if (getInboundOpen().size() > 0) {
            return Status.ANSWER;
        }
        printXUI();
        return Status.WAIT;
    }

    @Override
    public Status mySend() {
        String word;
        Receivers = selectReceivers(Players, true);
        if (Receivers != null) {
            nmessages = Receivers.size(); ///> To keep track of future answers
            word = selectWord(Dict, null);
            if (word == null) {
                return Status.EXIT;
            }
            outbox = new ACLMessage(ACLMessage.QUERY_IF);
            outbox.setContent(word);
            outbox.setReplyWith(word); ///> In order to recognize the answer asn soon as it arrives
            outbox.setConversationId(Conversation);
            outbox.setSender(getAID());
            for (String s : Receivers) {
                outbox.addReceiver(new AID(s, AID.ISLOCALNAME));
            }
            printXUI();
            Dialogue(outbox);
            return Status.WAIT;
        } else {
            return Status.EXIT;
        }

    }


    @Override
    public Status myReceive() {
        if (isComplete(outbox)) {
            answers=getAnswersTo(outbox);
            for (ACLMessage m : answers) {
                printXUI();
            }
        }
        return Status.WAIT;
    }


    @Override
    public Status myAnswer() {
        ACLMessage aux;
        for (ACLMessage m : getInboundOpen()) {
            aux = answerTo(m);
            if (aux != null) {
                aux.setPerformative(ACLMessage.INFORM);
                aux.setReplyWith(aux.getContent());
                aux.setConversationId(Conversation);
                Dialogue(aux); ///> Answering to a open Request implies closgin the request
                printXUI();
                return Status.WAIT;
            } else {
                return Status.EXIT;
            }
        }
        return Status.WAIT;
    }

}
