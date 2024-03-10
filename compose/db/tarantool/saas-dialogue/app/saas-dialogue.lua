box.cfg {
    pid_file = nil,
    background = false,
    log_level = 1
}

box.once("init_db",
        function()
            local db_initializer = require('db.db_initializer')
            if not db_initializer then print("can't load db_initializer") end
            db_initializer:create_database()
        end)