package name.svetov.dialogue.repository;

import name.svetov.dialogue.model.Dialogue;
import org.reactivestreams.Publisher;

import java.util.UUID;

public interface DialogueRepository {
    Publisher<Dialogue> getOneById(UUID dialogueId);
    Publisher<Dialogue> findAllByParticipantId(UUID userId);
    Publisher<Dialogue> add(Dialogue dialogue);
}
