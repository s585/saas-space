local db_initializer = {}

-- Importing the package and calling the model function
-- The config parameter is assigned a nil (empty) value
local dialogue = require('saas-dialogue.app.model.dialogue').model(nil)
local dialogue_message = require('saas-dialogue.app.model.dialogue_message').model(nil)

local function create_tarantool_user()
    box.schema.user.create(
            'tt_operator',
            {
                password = 'password',
                if_not_exists = true
            }
    )

    box.schema.user.grant(
            'tt_operator',
            'read,write,execute',
            'universe',
            nil,
            {
                if_not_exists = true
            }
    )
end

local function create_dialogue_space()
    local dialogue_space = box.schema.space.create(
            dialogue.SPACE_NAME,
            {
                if_not_exists = true
            }
    )

    dialogue_space.format(
            {
                { name = dialogue.ID, type = 'uuid', is_nullable = false },
                { name = dialogue.PARTICIPANTS, type = 'array', is_nullable = false },
                { name = dialogue.CREATED_DATE, type = 'datetime', is_nullable = false },
                { name = dialogue.UPDATED_DATE, type = 'datetime', is_nullable = false }
            }
    )

    dialogue_space.create_index(
            dialogue.PRIMARY_INDEX,
            { parts = { dialogue.ID },
              if_not_exists = true,
              type = 'HASH',
              unique = true
            }
    )

    dialogue_space.create_index(
            dialogue.PARTICIPANTS_INDEX,
            { parts = { dialogue.PARTICIPANTS },
              if_not_exists = true,
              type = 'TREE',
              unique = false
            }
    )
end

local function create_dialogue_message_space()
    local dialogue_message_space = box.schema.space.create(
            dialogue_message.SPACE_NAME,
            {
                if_not_exists = true
            }
    )

    dialogue_message_space.format(
            {
                { name = dialogue_message.ID, type = 'uuid', is_nullable = false },
                { name = dialogue_message.DIALOGUE_ID, type = 'uuid', is_nullable = false },
                { name = dialogue_message.AUTHOR_ID, type = 'uuid', is_nullable = false },
                { name = dialogue_message.RECIPIENT_ID, type = 'uuid', is_nullable = false },
                { name = dialogue_message.PAYLOAD, type = 'string', is_nullable = false },
                { name = dialogue_message.CREATED_DATE, type = 'datetime', is_nullable = false },
                { name = dialogue_message.UPDATED_DATE, type = 'datetime', is_nullable = false }
            }
    )

    dialogue_message_space.create_index(
            dialogue_message.PRIMARY_INDEX,
            { parts = { dialogue_message.ID },
              if_not_exists = true,
              type = 'HASH',
              unique = true
            }
    )

    dialogue_message_space.create_index(
            dialogue_message.DIALOGUE_INDEX,
            { parts = { dialogue_message.DIALOGUE_ID },
              if_not_exists = true,
              type = 'HASH',
              unique = false
            }
    )
end

function db_initializer.create_database()
    create_tarantool_user()
    create_dialogue_space()
    create_dialogue_message_space()
end

return db_initializer