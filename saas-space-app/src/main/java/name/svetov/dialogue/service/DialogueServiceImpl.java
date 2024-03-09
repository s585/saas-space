package name.svetov.dialogue.service;

import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import name.svetov.dialogue.model.Dialogue;
import name.svetov.dialogue.model.DialogueMessage;
import name.svetov.dialogue.repository.DialogueMessageRepository;
import name.svetov.dialogue.repository.DialogueRepository;
import name.svetov.userdetails.service.CurrentUserService;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Singleton
@RequiredArgsConstructor
public class DialogueServiceImpl implements DialogueService {
    private final CurrentUserService currentUserService;
    private final DialogueRepository dialogueRepository;
    private final DialogueMessageRepository dialogueMessageRepository;

    @Override
    public Publisher<Dialogue> getDialogue(UUID dialogueId) {
        return Mono.from(dialogueRepository.getOneById(dialogueId));
    }

    @Override
    public Publisher<Dialogue> createDialogue(Dialogue dialogue) {
        return Mono.from(currentUserService.getCurrentUser())
            .flatMap(user ->
                Mono.from(
                    dialogueRepository.add(
                        dialogue.toBuilder()
                            .participant(user.getId())
                            .build()
                    )
                )
            );
    }

    @Override
    public Publisher<DialogueMessage> createMessage(DialogueMessage message) {
        return dialogueMessageRepository.add(message);
    }

    @Override
    public Publisher<DialogueMessage> findAllMessagesByDialogueId(UUID dialogueId) {
        return dialogueMessageRepository.findAllByDialogueId(dialogueId);
    }
}
