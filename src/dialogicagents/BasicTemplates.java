/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dialogicagents;

import agents.BasicPlayer;
import jade.core.AID;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

/**
 *
 * @author Anatoli Grishenko <Anatoli.Grishenko@gmail.com>
 */
public class BasicTemplates extends Interleaved {

//    // La única diferencia del send es que marco el mensaje con
//    //una marca especial en Language ("MARCA") para luego detectar cuando llegan
//    // sus respuestas
//    @Override
//    public Status mySend() {
//        Players = findPlayers();
//        Receivers = selectReceivers(Players, true);
//        if (Receivers != null) {
//            nmessages = Receivers.size();
//            Word = selectWord(null);
//            if (Word == null) {
//                return Status.EXIT;
//            }
//            outbox = new ACLMessage(ACLMessage.QUERY_IF);
//            outbox.setContent(Word);
//            outbox.setReplyWith(Word);
//            outbox.setLanguage("MARCA"); // AQUI envío la marca
//            outbox.setConversationId("DBA");
//            outbox.setSender(getAID());
//            for (String s : Receivers) {
//                outbox.addReceiver(new AID(s, AID.ISLOCALNAME));
//            }
//            LARVAsend(outbox);
//            return Status.RECEIVE;
//        } else {
//            return Status.EXIT;
//        }
//    }
//
//    @Override
//    public Status myReceive() {
//        // En este enfoque, primero leo todas las respuestas a mi mensaje
//        // antes de responder yo a los demás
//        // Conforme se reciben los mensajes, mis respuestas se leen como de costumbre,
//        // pero, al usar la plantilla, el resto de mensajes en la cola de mensajes
//        // se ignoran temporalmente hasta que se deje de usar la plantilla
//        while (nmessages > 0) {
//            ACLMessage aux = LARVAblockingReceive( // Aquí recibo solo la marca
//                    MessageTemplate.MatchLanguage("MARCA"));
//            nmessages--;
//            printXUI();
//        }
//        return Status.ANSWER;
//    }
//
//    // Cuando llego aquí aún no he leído nada
//    @Override
//    public Status myAnswer() {
//        ACLMessage aux;
//        // Me veo obligado a poner un temporizador porque si no
//        // me podría quedar bloqueado y no volver a SEND nunca
//        // Respondo a todos los que se habían quedado esperando
//        inbox = LARVAblockingReceive(100);
//        if (inbox != null) {
//            aux = inbox.createReply();
//            aux.setPerformative(ACLMessage.INFORM);
//            Word = selectWord(inbox.getContent());
//            if (Word != null) {
//                aux.setContent(Word);
//                aux.setReplyWith(Word);
//                LARVAsend(aux);
//                return myStatus;
//            } else
//                return Status.EXIT;
//        } else {
//            return Status.SEND;
//        }
//    }
}
