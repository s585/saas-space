package name.svetov.dialogue.controller;

import io.micronaut.http.annotation.Controller;
import lombok.RequiredArgsConstructor;
import name.svetov.dialogue.adapter.DialogueWebAdapter;
import name.svetov.dialogue.dto.DialogueRq;
import name.svetov.dialogue.dto.DialogueRs;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class DialogueControllerImpl implements DialogueController {
    private final DialogueWebAdapter dialogueWebAdapter;

    @Override
    public Publisher<DialogueRs> get(UUID dialogueId) {
        return Mono.from(dialogueWebAdapter.getDialogue(dialogueId));
    }

    @Override
    public Publisher<DialogueRs> create(DialogueRq dialogue) {
        return dialogueWebAdapter.createDialogue(dialogue);
    }
}
