/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dialogiclient;

import jade.core.AID;
import jade.lang.acl.ACLMessage;

/**
 *
 * @author Anatoli Grishenko <Anatoli.Grishenko@gmail.com>
 */
public class Interleaved extends Follower {
    
    // Este estado es nuevo. Ahora en vez de escuchar solamente, también envía y
    // espera respuestas
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

    // Rerceive se vuelve más complejo pues debe dstinguir si lo que ha llegado es una respuesta
    // a mis mensajes enviados (INFORM) o es una peticion nueva (QUERY-IF)
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

}
