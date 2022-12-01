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
public class DialogicStarter extends DialogicPlayer {
    
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
            /*
            getInboundOpen();/// Mensajes que he recibido con una peticion y que esperan mi respuesta
            getOutboundDue();/// Mensajes que he enviado y que ya han reccibido todas las respuestas
            */
            answers = blockingDialogue(outbox); ///> outbox is automatically closed when done
            printXUI();
            return Status.SEND;
        } else {
            return Status.EXIT;
        }

    }
    
    
}
