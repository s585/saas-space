box.cfg {
    pid_file = nil,
    background = false,
    log_level = 1
}

box.once("init_db",
        function()
            local db_initializer = require('db.db_initializer')
            db_initializer:create_database()
        end)