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
public class DialogicFollower extends DialogicPlayer {

    /**
     * Basic reception. Just receives and moves on. It ignores the messages that
     * are not marked appropriatedly. Leaves the word received in inbox
     *
     * @return The next status
     */
    @Override
    public Status myReceive() {
//        answers = getInboundOpen();
        if (!getInboundOpen().isEmpty()) {
            return Status.ANSWER;
        } else {
            return Status.RECEIVE;
        }
    }

    /**
     * It explores the list of received requests and answers one by one
     *
     * @return The next status. If the popup is escaped, then if moves to EXIT
     */
    @Override
    public Status myAnswer() {
        ACLMessage aux;
        if (!getInboundOpen().isEmpty()) {
            for (ACLMessage m : getInboundOpen()) {
                aux = answerTo(m);
                if (aux != null) {
                    aux.setPerformative(ACLMessage.INFORM);
                    aux.setReplyWith(aux.getContent());
                    aux.setConversationId(Conversation);
                    Dialogue(aux); ///> Answering to a open Request implies closgin the request
                    printXUI();
                } else {
                    return Status.EXIT;
                }
            }
        }
        return Status.WAIT;
    }
}
