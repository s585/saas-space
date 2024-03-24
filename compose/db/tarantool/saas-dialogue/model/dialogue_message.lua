local dialogue_message = {}

local uuid = require('uuid')

function dialogue_message.model(config)
    local model = {}

    model.SPACE_NAME = 'dialogue_message'
    model.PRIMARY_INDEX = 'primary'
    model.BUCKET_ID = 'bucket_id'
    model.DIALOGUE_INDEX = 'dialogue'

    model.BUCKET_ID = 1
    model.ID = 2
    model.DIALOGUE_ID = 3
    model.AUTHOR_ID = 4
    model.RECIPIENT_ID = 5
    model.PAYLOAD = 6
    model.CREATED_DATE = 7
    model.UPDATED_DATE = 8

    function model.get_space()
        return box.space[model.SPACE_NAME]
    end

    function model.find_all_by_dialogue_id(dialogue_id)
        return model.get_space().index[model.DIALOGUE_INDEX]:select(dialogue_id)
    end

    function model.create(bucket_id, dialogue_message_tuple)
        return model.get_space():insert{
            bucket_id,
            dialogue_message_tuple[model.ID],
            dialogue_message_tuple[model.DIALOGUE_ID],
            dialogue_message_tuple[model.AUTHOR_ID],
            dialogue_message_tuple[model.RECIPIENT_ID],
            dialogue_message_tuple[model.PAYLOAD],
            os.date(),
            os.date()
        }
    end

    return model
end

return dialogue_message
