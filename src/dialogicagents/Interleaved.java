/**
 * @file Interleaved.java
 */
package dialogicagents;

import jade.lang.acl.ACLMessage;

/**
 *
 * @author Anatoli Grishenko <Anatoli.Grishenko@gmail.com>
 */
public class Interleaved extends Starter {
    
    /**
     * THis receptions is not blocking. It just reads messages and redirect them to the most appropriate 
     * destination. If it is my answer, then I coiutn it, and I keep in this state. 
     * If it is a request, then I jump to another state to process it and then back to here
     * @return 
     */
    @Override
    public Status myReceive() {
        inbox = LARVAblockingReceive();
        if (inbox.getPerformative() == ACLMessage.INFORM) {
            nmessages--; // Cuento las respuestas recibidas
            if (nmessages > 0) {
                return myStatus; // Mientras no lleguen las respuestas no cambio de estado
            } else {
                return Status.SEND; // Cuando llegan todas, cambio de estado  para enviar de nuevo
            }
        } else {
            // Si se cuela algo entre las respuestas, lo redirecciono provisionalmente a responder
            return Status.ANSWER;
        }
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
            LARVAsend(aux);
            return Status.RECEIVE; ///> This is important here
        } else {
            return Status.EXIT;
        }

    }

}
