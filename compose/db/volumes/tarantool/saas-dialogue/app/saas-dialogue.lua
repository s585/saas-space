box.cfg {
    pid_file = nil,
    background = false,
    log_level = 5
}

local create_database = require('saas-dialogue.app.db.db_initializer').create_database()
box.once('create_database', create_database)