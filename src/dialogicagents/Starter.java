/**
 * @file BasicReceiver.java
 */
package dialogicagents;

import agents.BasicPlayer;
import jade.core.AID;
import jade.lang.acl.ACLMessage;

/**
 *
 * Redefines @a BasicPlayer with a simle behaviour, it listen and then answer
 */
public class Starter extends BasicPlayer {

    /**
     * It first selects the receivers amongst all the registered players, then
     * chooses the fisrt word and send it to them. It remembers how many
     * receivers has injcluded
     *
     * @return The next statusl. If the popup is escaped, then it moves to EXIT
     */
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
            LARVAsend(outbox);
            return Status.RECEIVE;
        } else {
            return Status.EXIT;
        }

    }

    /**
     * Basic reception. Just receives and moves on. It ignores the messages that
     * are not marked appropriatedly. Leaves the word received in inbox. It counts
     * the number of receptions. When the number of receptions is equal to
     * the number of receptors, it sends another word
     *
     * @return The next status
     */
    @Override
    public Status myReceive() {
        inbox = LARVAblockingReceive();
        if (inbox.getPerformative() == ACLMessage.INFORM && 
                inbox.getInReplyTo().equals(outbox.getContent()) ///> Detect an answer
                && inbox.getConversationId().equals(Conversation)) {
            nmessages--;
        }
        if (nmessages > 0) { 
            return Status.RECEIVE;
        } else {
            return Status.SEND;
        }
    }

}
