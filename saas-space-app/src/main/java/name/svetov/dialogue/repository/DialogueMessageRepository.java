package name.svetov.dialogue.repository;

import name.svetov.dialogue.model.DialogueMessage;
import org.reactivestreams.Publisher;

import java.util.UUID;

public interface DialogueMessageRepository {
    Publisher<DialogueMessage> findAllByDialogueId(UUID dialogueId);
    Publisher<DialogueMessage> add(DialogueMessage message);
}
