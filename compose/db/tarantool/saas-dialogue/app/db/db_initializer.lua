local db_initializer = {}

local dialogue = require('model.dialogue').model(nil)
local dialogue_message = require('model.dialogue_message').model(nil)

local function create_dialogue_space()
    local dialogue_space = box.schema.space.create(
            dialogue.SPACE_NAME,
            {
                if_not_exists = true
            }
    )

    dialogue_space:format(
            {
                { name = 'id', type = 'uuid', is_nullable = false },
                { name = 'participants', type = 'array', is_nullable = false },
                { name = 'created_date', type = 'datetime', is_nullable = false },
                { name = 'updated_date', type = 'datetime', is_nullable = false }
            }
    )

    dialogue_space:create_index(
            dialogue.PRIMARY_INDEX,
            { parts = { dialogue.ID },
              if_not_exists = true,
              type = 'HASH',
              unique = true
            }
    )

    --dialogue_space:create_index(
    --        dialogue.PARTICIPANTS_INDEX,
    --        { parts = { 'participants[*]' },
    --          if_not_exists = true,
    --          type = 'TREE',
    --          unique = false
    --        }
    --)
end

local function create_dialogue_message_space()
    local dialogue_message_space = box.schema.space.create(
            dialogue_message.SPACE_NAME,
            {
                if_not_exists = true
            }
    )

    dialogue_message_space:format(
            {
                { name = 'id', type = 'uuid', is_nullable = false },
                { name = 'dialogue_id', type = 'uuid', is_nullable = false },
                { name = 'author_id', type = 'uuid', is_nullable = false },
                { name = 'recipient_id', type = 'uuid', is_nullable = false },
                { name = 'payload', type = 'string', is_nullable = false },
                { name = 'created_date', type = 'datetime', is_nullable = false },
                { name = 'updated_date', type = 'datetime', is_nullable = false }
            }
    )

    dialogue_message_space:create_index(
            dialogue_message.PRIMARY_INDEX,
            { parts = { dialogue_message.ID },
              if_not_exists = true,
              type = 'HASH',
              unique = true
            }
    )

    dialogue_message_space:create_index(
            dialogue_message.DIALOGUE_INDEX,
            { parts = { dialogue_message.DIALOGUE_ID },
              if_not_exists = true,
              type = 'TREE',
              unique = false
            }
    )
end

function db_initializer.create_database()
    create_dialogue_space()
    create_dialogue_message_space()
end

return db_initializer