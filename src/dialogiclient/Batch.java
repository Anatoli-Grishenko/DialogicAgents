/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dialogiclient;

import agents.BasicPlayer;
import jade.core.AID;
import jade.lang.acl.ACLMessage;

/**
 *
 * @author Anatoli Grishenko <Anatoli.Grishenko@gmail.com>
 */
public class Batch extends BasicPlayer {

    @Override
    public Status mySend() {
        String word;
        Receivers = selectReceivers(Players, true);
        if (Receivers != null) {
            nmessages = Receivers.size();
            word = selectWord(Dict, null);
            if (word == null) {
                return Status.EXIT;
            }
            outbox = new ACLMessage(ACLMessage.QUERY_IF);
            outbox.setContent(word);
            outbox.setReplyWith(word);
            outbox.setConversationId("DBA");
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

    @Override
    public Status myAnswer() {
        // Proceso el mensaje que se había quedado almacenado
        // en inbox y respondo
        ACLMessage aux = answerTo(inbox, Dict, true);
        // En caso de que hala fallado la construcción de la respuesta, se procede a salir.
        if (aux != null) {
            aux.setPerformative(ACLMessage.INFORM);
            aux.setReplyWith(aux.getContent());
            aux.setConversationId(Conversation);
            aux.setProtocol(Protocol);
//            if (useDeadlines) {
//                aux.setReplyByDate(getDeadline(secs, 2 * secs));
//            }
            LARVAsend(aux);
            return Status.RECEIVE;
        } else {
            return Status.EXIT;
        }

    }

}
