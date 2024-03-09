package name.svetov.dialogue.adapter;

import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import name.svetov.dialogue.converter.DialogueConverter;
import name.svetov.dialogue.dto.DialogueMessageDto;
import name.svetov.dialogue.dto.DialogueRq;
import name.svetov.dialogue.dto.DialogueRs;
import name.svetov.dialogue.service.DialogueService;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Singleton
@RequiredArgsConstructor
public class DialogueWebAdapterImpl implements DialogueWebAdapter {
    private final DialogueService dialogueService;
    private final DialogueConverter converter;

    @Override
    public Publisher<DialogueRs> getDialogue(UUID dialogueId) {
        return Mono.from(dialogueService.getDialogue(dialogueId))
            .map(converter::map);
    }

    @Override
    public Publisher<DialogueRs> createDialogue(DialogueRq rq) {
        return Mono.from(dialogueService.createDialogue(converter.map(rq)))
            .map(converter::map);
    }

    @Override
    public Publisher<DialogueMessageDto> createMessage(DialogueMessageDto message) {
        return Mono.from(dialogueService.createMessage(converter.map(message)))
            .map(converter::map);
    }

    @Override
    public Publisher<DialogueMessageDto> findAllMessagesByDialogueId(UUID dialogueId) {
        return Flux.from(dialogueService.findAllMessagesByDialogueId(dialogueId))
            .map(converter::map);
    }
}
