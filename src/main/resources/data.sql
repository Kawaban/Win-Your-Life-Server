CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- INSERT INTO winyourlife.users (created_date, last_modified_date, version, uuid, email, password, is_enabled, role) VALUES
--     (NOW(), NOW(), 0, uuid_generate_v4(), 'user1@example.com', '$2a$12$LdZFfuccCDIwzNqn3jLg3.Z8Aw6g6bePh9W4Z8ipsSJvLSRp6H6FO', TRUE, 'USER'),
--     (NOW(), NOW(), 0, uuid_generate_v4(), 'user2@example.com', '$2a$12$LdZFfuccCDIwzNqn3jLg3.Z8Aw6g6bePh9W4Z8ipsSJvLSRp6H6FO', TRUE, 'USER'),
--     (NOW(), NOW(), 0, uuid_generate_v4(), 'user3@example.com', '$2a$12$LdZFfuccCDIwzNqn3jLg3.Z8Aw6g6bePh9W4Z8ipsSJvLSRp6H6FO', TRUE, 'USER'),
--     (NOW(), NOW(), 0, uuid_generate_v4(), 'user4@example.com', '$2a$12$LdZFfuccCDIwzNqn3jLg3.Z8Aw6g6bePh9W4Z8ipsSJvLSRp6H6FO', TRUE, 'USER'),
--     (NOW(), NOW(), 0, uuid_generate_v4(), 'user5@example.com', '$2a$12$LdZFfuccCDIwzNqn3jLg3.Z8Aw6g6bePh9W4Z8ipsSJvLSRp6H6FO', TRUE, 'USER');
--
-- INSERT INTO winyourlife.users_info (created_date, last_modified_date, version, uuid, email, name, streak, longest_streak, completed_tasks, avatar, is_friend_notification_active, is_daily_reminder_active) VALUES
--     (NOW(), NOW(), 0, uuid_generate_v4(), 'user1@example.com', 'User One', 5, 10, 50, NULL, TRUE, TRUE),
--     (NOW(), NOW(), 0, uuid_generate_v4(), 'user2@example.com', 'User Two', 3, 5, 30, NULL, FALSE, TRUE),
--     (NOW(), NOW(), 0, uuid_generate_v4(), 'user3@example.com', 'User Three', 7, 12, 70, NULL, TRUE, FALSE),
--     (NOW(), NOW(), 0, uuid_generate_v4(), 'user4@example.com', 'User Four', 2, 4, 15, NULL, FALSE, FALSE),
--     (NOW(), NOW(), 0, uuid_generate_v4(), 'user5@example.com', 'User Five', 6, 9, 60, NULL, TRUE, TRUE);


