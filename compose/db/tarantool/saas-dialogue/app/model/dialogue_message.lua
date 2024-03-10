local dialogue_message = {}

local uuid = require('uuid')

function dialogue_message.model(config)
    local model = {}

    model.SPACE_NAME = 'dialogue_message'
    model.PRIMARY_INDEX = 'primary'
    model.DIALOGUE_INDEX = 'dialogue'

    model.ID = 1
    model.DIALOGUE_ID = 2
    model.AUTHOR_ID = 3
    model.RECIPIENT_ID = 4
    model.PAYLOAD = 5
    model.CREATED_DATE = 6
    model.UPDATED_DATE = 7

    function model.get_space()
        return box.space[model.SPACE_NAME]
    end

    function model.find_all_by_dialogue_id(dialogue_id)
        return model.get_space().index[model.DIALOGUE_INDEX]:select(dialogue_id)
    end

    function model.create(dialogue_message_tuple)
        local id = uuid.str()
        return model.get_space():insert({
            id,
            dialogue_message_tuple[model.DIALOGUE_ID],
            dialogue_message_tuple[model.AUTHOR_ID],
            dialogue_message_tuple[model.RECIPIENT_ID],
            dialogue_message_tuple[model.PAYLOAD],
            os.date(),
            os.date()
        })
    end

    return model
end

return dialogue_message