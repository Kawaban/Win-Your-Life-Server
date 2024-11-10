DROP
    SCHEMA IF EXISTS winyourlife CASCADE;

CREATE
    SCHEMA winyourlife;

DROP
    TABLE
        IF EXISTS winyourlife.users;

CREATE
    TABLE
        winyourlife.users(
            created_date TIMESTAMP(6) WITH TIME ZONE,
            last_modified_date TIMESTAMP(6) WITH TIME ZONE,
            version BIGINT NOT NULL DEFAULT 0,
            uuid UUID NOT NULL,
            email VARCHAR(255) UNIQUE NOT NULL,
            password VARCHAR(255) NOT NULL,
            is_enabled BOOLEAN DEFAULT FALSE NOT NULL,
            ROLE VARCHAR(10) NOT NULL,
            PRIMARY KEY(uuid)
        );

DROP
    TABLE
        IF EXISTS winyourlife.users_info;

CREATE
    TABLE
        winyourlife.users_info(
            created_date TIMESTAMP(6) WITH TIME ZONE,
            last_modified_date TIMESTAMP(6) WITH TIME ZONE,
            version BIGINT NOT NULL DEFAULT 0,
            uuid UUID NOT NULL,
            email VARCHAR(255) UNIQUE NOT NULL,
            name VARCHAR(255) NOT NULL,
            streak INT,
            longest_streak INT,
            completed_tasks INT,
            avatar bytea,
            PRIMARY KEY(uuid)
        );

DROP
    TABLE
        IF EXISTS winyourlife.notifications;

CREATE
    TABLE
        winyourlife.notifications(
            created_date TIMESTAMP(6) WITH TIME ZONE,
            last_modified_date TIMESTAMP(6) WITH TIME ZONE,
            version BIGINT NOT NULL DEFAULT 0,
            uuid UUID NOT NULL,
            TYPE VARCHAR(255) NOT NULL,
            email_sender VARCHAR(255) NOT NULL,
            email_recipient VARCHAR(255) NOT NULL,
            is_read BOOLEAN DEFAULT FALSE NOT NULL,
            PRIMARY KEY(uuid)
        );

DROP
    TABLE
        IF EXISTS winyourlife.friend_requests;

CREATE
    TABLE
        winyourlife.friend_requests(
            created_date TIMESTAMP(6) WITH TIME ZONE,
            last_modified_date TIMESTAMP(6) WITH TIME ZONE,
            version BIGINT NOT NULL DEFAULT 0,
            uuid UUID NOT NULL,
            sender UUID NOT NULL,
            recipient UUID NOT NULL,
            FOREIGN KEY(sender) REFERENCES winyourlife.users_info(uuid),
            FOREIGN KEY(recipient) REFERENCES winyourlife.users_info(uuid),
            PRIMARY KEY(uuid)
        );

DROP
    TABLE
        IF EXISTS winyourlife.friends;

CREATE
    TABLE
        winyourlife.friends(
            created_date TIMESTAMP(6) WITH TIME ZONE,
            last_modified_date TIMESTAMP(6) WITH TIME ZONE,
            version BIGINT NOT NULL DEFAULT 0,
            uuid UUID NOT NULL,
            user_id UUID NOT NULL,
            friend_id UUID NOT NULL,
            FOREIGN KEY(user1) REFERENCES winyourlife.users_info(uuid),
            FOREIGN KEY(user2) REFERENCES winyourlife.users_info(uuid),
            PRIMARY KEY(uuid)
        );
