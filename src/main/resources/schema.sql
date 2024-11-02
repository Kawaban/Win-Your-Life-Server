DROP SCHEMA IF EXISTS winyourlife CASCADE;
CREATE SCHEMA winyourlife;

DROP TABLE IF EXISTS winyourlife.users;

CREATE TABLE winyourlife.users
(
    created_date       TIMESTAMP(6) WITH TIME ZONE,
    last_modified_date TIMESTAMP(6) WITH TIME ZONE,
    version            BIGINT              NOT NULL DEFAULT 0,
    uuid               UUID                NOT NULL,
    email              VARCHAR(255) UNIQUE NOT NULL,
    password           VARCHAR(255)        NOT NULL,
    is_enabled         BOOLEAN                      DEFAULT FALSE NOT NULL,
    role               VARCHAR(10)         NOT NULL,
    PRIMARY KEY (uuid)
);

DROP TABLE IF EXISTS winyourlife.users_info;

CREATE TABLE winyourlife.users_info
(
    created_date       TIMESTAMP(6) WITH TIME ZONE,
    last_modified_date TIMESTAMP(6) WITH TIME ZONE,
    version            BIGINT              NOT NULL DEFAULT 0,
    uuid               UUID                NOT NULL,
    email              VARCHAR(255) UNIQUE NOT NULL,
    name          VARCHAR(255)        NOT NULL,
    streak           INT        ,
    longest_streak           INT        ,
    completed_tasks           INT        ,
    avatar           bytea        ,
    is_friend_notification_active           BOOLEAN  NOT NULL      ,
    is_daily_reminder_active           BOOLEAN   NOT NULL      ,

    PRIMARY KEY (uuid)
);


