package name.svetov.dialogue.service;

import name.svetov.dialogue.model.Dialogue;
import name.svetov.dialogue.model.DialogueMessage;
import org.reactivestreams.Publisher;

import java.util.UUID;

public interface DialogueService {
    Publisher<Dialogue> getDialogue(UUID dialogueId);
    Publisher<Dialogue> createDialogue(Dialogue dialogue);
    Publisher<DialogueMessage> createMessage(DialogueMessage message);
    Publisher<DialogueMessage> findAllMessagesByDialogueId(UUID dialogueId);
}
