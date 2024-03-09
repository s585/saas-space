package name.svetov.dialogue.adapter;

import name.svetov.dialogue.dto.DialogueMessageDto;
import name.svetov.dialogue.dto.DialogueRq;
import name.svetov.dialogue.dto.DialogueRs;
import org.reactivestreams.Publisher;

import java.util.UUID;

public interface DialogueWebAdapter {
    Publisher<DialogueRs> getDialogue(UUID dialogueId);
    Publisher<DialogueRs> createDialogue(DialogueRq rq);
    Publisher<DialogueMessageDto> createMessage(DialogueMessageDto message);
    Publisher<DialogueMessageDto> findAllMessagesByDialogueId(UUID dialogueId);
}
