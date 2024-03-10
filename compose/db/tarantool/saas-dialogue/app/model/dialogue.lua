local dialogue = {}

local uuid = require('uuid')

function dialogue.model(config)
    local model = {}

    model.SPACE_NAME = 'dialogue'
    model.PRIMARY_INDEX = 'primary'
    model.PARTICIPANTS_INDEX = 'participants'

    model.ID = 1
    model.PARTICIPANTS = 2
    model.CREATED_DATE = 3
    model.UPDATED_DATE = 4

    function model.get_space()
        return box.space[model.SPACE_NAME]
    end

    function model.get_by_id(id)
        return model.get_space():select(id)
    end

    function model.create(dialogue_tuple)
        local id = uuid.str()
        return model.get_space():insert{
            id,
            dialogue_tuple[model.PARTICIPANTS],
            os.date(),
            os.date()
        }
    end

    return model
end

return dialogue