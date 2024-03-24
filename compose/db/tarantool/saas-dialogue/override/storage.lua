local dialogue = require('model.dialogue'):model()
local dialogue_message = require('model.dialogue_message'):model()

box.once(
        dialogue.SPACE_NAME,
        function()
            local dialogue_space = box.schema.space.create(
                    dialogue.SPACE_NAME, {
                        format = {
                            { name = 'bucket_id', type = 'unsigned', is_nullable = false },
                            { name = 'id', type = 'uuid', is_nullable = false },
                            { name = 'participants', type = 'array', is_nullable = false },
                            { name = 'created_date', type = 'datetime', is_nullable = false },
                            { name = 'updated_date', type = 'datetime', is_nullable = false }
                        },
                        if_not_exists = true
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

            dialogue_space:create_index(
                    dialogue.BUCKET_INDEX,
                    { parts = { dialogue.BUCKET_ID },
                      if_not_exists = true,
                      type = 'HASH',
                      unique = true
                    }
            )
        end
)

box.once(
        dialogue_message.SPACE_NAME,
        function()
            local dialogue_message_space = box.schema.space.create(
                    dialogue_message.SPACE_NAME, {
                        format = {
                            { name = 'bucket_id', type = 'unsigned', is_nullable = false },
                            { name = 'id', type = 'uuid', is_nullable = false },
                            { name = 'dialogue_id', type = 'uuid', is_nullable = false },
                            { name = 'author_id', type = 'uuid', is_nullable = false },
                            { name = 'recipient_id', type = 'uuid', is_nullable = false },
                            { name = 'payload', type = 'string', is_nullable = false },
                            { name = 'created_date', type = 'datetime', is_nullable = false },
                            { name = 'updated_date', type = 'datetime', is_nullable = false }
                        },
                        if_not_exists = true
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
                    dialogue_message.BUCKET_INDEX,
                    { parts = { dialogue_message.BUCKET_ID },
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
)

function get_dialogue_by_id(dialogue_id)
    return dialogue.get_by_id { dialogue_id }
end

function create_dialogue(bucket_id, dialogue_tuple)
    return dialogue.create_dialogue { bucket_id, dialogue_tuple }
end

function find_all_dialogue_messages(dialogue_id)
    return dialogue_message.find_all_by_dialogue_id {dialogue_id}
end

function create_dialogue_message(bucket_id, dialogue_message_tuple)
    return dialogue_message.create(bucket_id, dialogue_message_tuple)
end
