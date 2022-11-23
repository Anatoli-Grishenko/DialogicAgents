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
public class Batch extends Starter {

    int maxAnswers = 5, nAnswers = 0;

    @Override
    public Status myReceive() {
        while (nmessages > 0) {
            printXUI();
            inbox = LARVAblockingReceive(
                    MessageTemplate.MatchInReplyTo(outbox.getContent()));
            nmessages--;
        }
        return Status.ANSWER;
    }

    /**
     * It takes the word received in inbox, builds an answer to it and sends it
     * back
     *
     * @return The next status. If the popup is escaped, then if moves to EXIT
     */
    @Override
    public Status myAnswer() {
        // Me veo obligado a poner un temporizador porque si no
        // me podría quedar bloqueado y no volver a SEND nunca
        // Respondo a todos los que se habían quedado esperando
        inbox = LARVAblockingReceive(100);
        if (inbox != null) {
            printXUI();
            outbox = answerTo(inbox);
            if (outbox != null) {
                outbox.setPerformative(ACLMessage.INFORM);
                outbox.setReplyWith(outbox.getContent());
                outbox.setConversationId(Conversation);
                LARVAsend(inbox);
                nAnswers++;
                if (nAnswers < maxAnswers) {
                    return myStatus;
                } else {
                    nAnswers = 0;
                    return Status.SEND;
                }
            } else {
                return Status.EXIT;
            }
        } else {
            return Status.EXIT;
        }
    }

}
