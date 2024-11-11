CREATE
    EXTENSION IF NOT EXISTS "uuid-ossp";

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
            notification_object_uuid UUID,
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
            sender_uuid UUID NOT NULL,
            receiver_uuid UUID NOT NULL,
            FOREIGN KEY(sender_uuid) REFERENCES winyourlife.users_info(uuid),
            FOREIGN KEY(receiver_uuid) REFERENCES winyourlife.users_info(uuid),
            PRIMARY KEY(uuid)
        );

DROP
    TABLE
        IF EXISTS winyourlife.friends;

CREATE
    TABLE
        winyourlife.friends(
            created_date TIMESTAMP(6) WITH TIME ZONE DEFAULT NOW(),
            last_modified_date TIMESTAMP(6) WITH TIME ZONE DEFAULT NOW(),
            version BIGINT NOT NULL DEFAULT 0,
            uuid UUID NOT NULL DEFAULT uuid_generate_v4(),
            user_id UUID NOT NULL,
            friend_id UUID NOT NULL,
            FOREIGN KEY(user_id) REFERENCES winyourlife.users_info(uuid),
            FOREIGN KEY(friend_id) REFERENCES winyourlife.users_info(uuid),
            PRIMARY KEY(uuid)
        );
