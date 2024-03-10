#!/usr/bin/env tarantool

local yaml = require('yaml')
local ddl = require('ddl')
local log = require('log')

local function create_tt_user()
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
    box.schema.space.create(
            'dialogue',
            {
                if_not_exists = true
            }
    )

    box.space.dialogue:format({
        { name = 'id', type = 'uuid', is_nullable = false },
        { name = 'participants', type = 'array', is_nullable = false },
        { name = 'created_date', type = 'datetime', is_nullable = false },
        { name = 'updated_date', type = 'datetime', is_nullable = false }
    })

    box.space.dialogue:create_index(
            'primary',
            { parts = { 'id' },
              if_not_exists = true,
              type = 'HASH',
              unique = true
            }
    )
    box.space.dialogue:create_index(
            'participants',
            { parts = { 'participants[*]' },
              if_not_exists = true,
              type = 'TREE',
              unique = false
            }
    )
end

local function create_dialogue_message_space()
    box.schema.space.create(
            'dialogue_message',
            {
                if_not_exists = true
            }
    )

    box.space.dialogue_message:format({
        { name = 'id', type = 'uuid', is_nullable = false },
        { name = 'dialogue_id', type = 'uuid', is_nullable = false },
        { name = 'author_id', type = 'uuid', is_nullable = false },
        { name = 'recipient_id', type = 'uuid', is_nullable = false },
        { name = 'created_date', type = 'datetime', is_nullable = false },
        { name = 'updated_date', type = 'datetime', is_nullable = false }
    })

    box.space.dialogue_message:create_index(
            'primary',
            { parts = { 'id' },
              if_not_exists = true,
              type = 'HASH',
              unique = true
            }
    )
end

box.cfg {
    pid_file = nil,
    background = false,
    log_level = 5
}

box.once('create_tt_user', create_tt_user)
box.once('create_dialogue_space', create_dialogue_space)
box.once('create_dialogue_message_space', create_dialogue_message_space)
