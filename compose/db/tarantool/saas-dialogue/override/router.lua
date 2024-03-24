local vshard = require('vshard')
local uuid = require('uuid')
local log = require('log')

while true do
    local ok, err = vshard.router.bootstrap({
        if_not_bootstrapped = true,
    })
    if ok then
        break
    end
    log.info(('Router bootstrap error: %s'):format(err))
end

function get_dialogue_by_id(dialogue_id)
    local bucket_id = vshard.router.bucket_id_mpcrc32(dialogue_id)
    return vshard.router.callro(
        bucket_id,
        'get_dialogue_by_id',
        { dialogue_id }
    )
end

function create_dialogue(participants)
    local dialogue_id = uuid.str()
    local bucket_id = vshard.router.bucket_id_mpcrc32(dialogue_id)
    local _, err = vshard.router.callrw(
        bucket_id,
        'create_dialogue',
        { bucket_id, { dialogue_id, participants } }
    )

    if err ~= nil then
        log.error(err)
        return nil
    end

    return true
end

function find_all_dialogue_messages(dialogue_id)
    local bucket_id = vshard.router.bucket_id_mpcrc32(dialogue_id)
    return vshard.router.callbro(
        bucket_id,
        'find_all_dialogue_messages',
        { dialogue_id }
    )
end

function create_dialogue_message(dialogue_id, author_id, recipient_id, payload)
    local bucket_id = vshard.router.bucket_id_mpcrc32(dialogue_id)
    local id = uuid.str()
    local _, err = vshard.router.callrw(
        bucket_id,
        'create_dialogue_message',
        { bucket_id, { id, dialogue_id, author_id, recipient_id, payload } }
    )
    if err ~= nil then
        log.error(err)
        return nil
    end

    return true
end

