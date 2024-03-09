package name.svetov.dialogue.repository;

import jakarta.inject.Named;
import jakarta.inject.Singleton;
import name.svetov.dialogue.model.Dialogue;
import org.jooq.DSLContext;
import org.jooq.InsertSetMoreStep;
import org.jooq.generated.tables.records.DialogueRecord;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.OffsetDateTime;
import java.util.UUID;

import static org.jooq.generated.Tables.DIALOGUE;

@Singleton
public class DialogueRepositoryImpl implements DialogueRepository {
    private final DSLContext context;

    public DialogueRepositoryImpl(@Named("r2dbc") DSLContext context) {
        this.context = context;
    }

    @Override
    public Publisher<Dialogue> getOneById(UUID dialogueId) {
        return Mono.from(
            context.selectFrom(DIALOGUE)
                .where(DIALOGUE.ID.eq(dialogueId))
        ).map(response -> response.into(Dialogue.class));
    }

    @Override
    public Publisher<Dialogue> findAllByParticipantId(UUID userId) {
        return Flux.from(
            context.selectFrom(DIALOGUE)
                .where(DIALOGUE.PARTICIPANTS.contains(new UUID[]{userId}))
        ).map(response -> response.into(Dialogue.class));
    }

    @Override
    public Publisher<Dialogue> add(Dialogue dialogue) {
        return Mono.from(insert(dialogue).returning())
            .map(response -> response.into(Dialogue.class));
    }

    private InsertSetMoreStep<DialogueRecord> insert(Dialogue dialogue) {
        var now = OffsetDateTime.now();
        return context.insertInto(DIALOGUE)
            .set(DIALOGUE.PARTICIPANTS, dialogue.getParticipants().toArray(UUID[]::new))
            .set(DIALOGUE.CREATED_DATE, now)
            .set(DIALOGUE.UPDATED_DATE, now);
    }
}
