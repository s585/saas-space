local dialogue = {}

local uuid = require('uuid')

function dialogue.model(config)
    local model = {}

    model.SPACE_NAME = 'dialogue'
    model.PRIMARY_INDEX = 'primary'
    model.BUCKET_INDEX = 'bucket_id'
    model.PARTICIPANTS_INDEX = 'participants'

    model.BUCKET_ID = 1
    model.ID = 2
    model.PARTICIPANTS = 3
    model.CREATED_DATE = 4
    model.UPDATED_DATE = 5

    function model.get_space()
        return box.space[model.SPACE_NAME]
    end

    function model.get_by_id(id)
        return model.get_space():select(id)
    end

    function model.create(bucket_id, dialogue_tuple)
        return model.get_space():insert{
            bucket_id,
            dialogue_tuple[model.ID],
            dialogue_tuple[model.PARTICIPANTS],
            os.date(),
            os.date()
        }
    end

    return model
end

return dialogue
