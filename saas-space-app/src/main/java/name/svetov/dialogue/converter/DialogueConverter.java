package name.svetov.dialogue.converter;

import name.svetov.config.SaasMapperDefaultConfig;
import name.svetov.dialogue.dto.DialogueMessageDto;
import name.svetov.dialogue.dto.DialogueRq;
import name.svetov.dialogue.dto.DialogueRs;
import name.svetov.dialogue.model.Dialogue;
import name.svetov.dialogue.model.DialogueMessage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = SaasMapperDefaultConfig.class)
public interface DialogueConverter {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "participant", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "updatedDate", ignore = true)
    Dialogue map(DialogueRq source);

    DialogueRs map(Dialogue source);

    DialogueMessage map(DialogueMessageDto source);

    DialogueMessageDto map(DialogueMessage source);
}
