package name.svetov.dialogue.repository;

import jakarta.inject.Named;
import jakarta.inject.Singleton;
import name.svetov.dialogue.model.DialogueMessage;
import org.jooq.DSLContext;
import org.jooq.InsertSetMoreStep;
import org.jooq.generated.tables.records.DialogueMessageRecord;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.OffsetDateTime;
import java.util.UUID;

import static org.jooq.generated.Tables.DIALOGUE_MESSAGE;

@Singleton
public class DialogueMessageRepositoryImpl implements DialogueMessageRepository {
    private final DSLContext context;

    public DialogueMessageRepositoryImpl(@Named("r2dbc") DSLContext context) {
        this.context = context;
    }

    @Override
    public Publisher<DialogueMessage> findAllByDialogueId(UUID dialogueId) {
        return Flux.from(
            context.selectFrom(DIALOGUE_MESSAGE)
                .where(DIALOGUE_MESSAGE.DIALOGUE_ID.eq(dialogueId))
        ).map(response -> response.into(DialogueMessage.class));
    }

    @Override
    public Publisher<DialogueMessage> add(DialogueMessage message) {
        return Mono.from(insert(message).returning())
            .map(response -> response.into(DialogueMessage.class));
    }

    private InsertSetMoreStep<DialogueMessageRecord> insert(DialogueMessage message) {
        var now = OffsetDateTime.now();
        return context.insertInto(DIALOGUE_MESSAGE)
            .set(DIALOGUE_MESSAGE.DIALOGUE_ID, message.getDialogueId())
            .set(DIALOGUE_MESSAGE.AUTHOR_ID, message.getAuthorId())
            .set(DIALOGUE_MESSAGE.RECIPIENT_ID, message.getRecipientId())
            .set(DIALOGUE_MESSAGE.BODY, message.getPayload())
            .set(DIALOGUE_MESSAGE.CREATED_DATE, now)
            .set(DIALOGUE_MESSAGE.UPDATED_DATE, now);
    }
}
