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
public class DialogicBatch extends DialogicFollower {
    
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
            printXUI();
            answers = blockingDialogue(outbox);
            printXUI();
            return Status.RECEIVE;
        } else {
            return Status.EXIT;
        }

    }
    
    
}
