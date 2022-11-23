/**
 * @file BasicReceiver.java
 */
package dialogicagents;

import agents.BasicPlayer;
import jade.lang.acl.ACLMessage;

/**
 *
 * Redefines @a BasicPlayer with a simle behaviour, it listen and then answer
 */
public class Follower extends BasicPlayer {

     /**
     * Basic reception. Just receives and moves on. It ignores the messages
     * that are not marked appropriatedly. Leaves the word received in inbox
     * @return The next status
     */
    @Override
    public Status myReceive() {
        inbox = LARVAblockingReceive();
        if (inbox.getConversationId().equals(Conversation) && inbox.getProtocol().equals(Protocol)) {
            return Status.ANSWER;
        } else
            return Status.RECEIVE;
    }

    /**
     * It takes the word received in inbox, builds an answer to it 
     * and sends it back
     * @return The next status. If the popup is escaped, then if moves to EXIT
     */
    @Override
    public Status myAnswer() {
        // Proceso el mensaje que se había quedado almacenado
        // en inbox y respondo
        ACLMessage aux = answerTo(inbox);
        // En caso de que hala fallado la construcción de la respuesta, se procede a salir.
        if (aux != null) {
            aux.setPerformative(ACLMessage.INFORM);
            aux.setReplyWith(aux.getContent());
            aux.setConversationId(Conversation);
//            if (useDeadlines) {
//                aux.setReplyByDate(getDeadline(secs, 2 * secs));
//            }
            LARVAsend(aux);
            return Status.WAIT;
        } else {
            return Status.EXIT;
        }

    }

}
