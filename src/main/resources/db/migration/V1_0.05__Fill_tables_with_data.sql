CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE OR REPLACE FUNCTION create_user(name_val VARCHAR, email_val VARCHAR, phone_val VARCHAR, balance_val DECIMAL,
                                       birthday_val DATE) RETURNS VOID AS
$$
DECLARE
    user_id_val BIGINT;
BEGIN
    INSERT INTO users (uuid, name, date_of_birth, password)
    VALUES (uuid_generate_v4(), name_val, birthday_val, '$2a$12$0NHkuefxVB/cr4qZE9gO7.ZaqV4fiNqQByOdpWhM3u7PkdgZJE96C')
    RETURNING id INTO user_id_val;

    INSERT INTO account (uuid, user_id, balance)
    VALUES (uuid_generate_v4(), user_id_val, balance_val);

    INSERT INTO email_data (uuid, user_id, email)
    VALUES (uuid_generate_v4(), user_id_val, email_val);

    INSERT INTO phone_data (uuid, user_id, phone)
    VALUES (uuid_generate_v4(), user_id_val, phone_val);
END;
$$ LANGUAGE 'plpgsql';

SELECT create_user('Вася Пупкин', 'test@gmail.com', '79207865432', 10,
                   '01.01.2000');
SELECT create_user('Гриша Лепс', 'mega.test@gmail.com', '79207865443', 5,
                   '01.01.2001');
SELECT create_user('Игорь Мышкин', 'super.duper@gmail.com', '79207865454', 1,
                   '01.01.2002');
SELECT create_user('Леша Попович', 'ultra@gmail.com', '79207865465', 100,
                   '01.01.2003');
SELECT create_user('Иван Царевич', 'respect.team@gmail.com', '79207865476', 1000,
                   '01.01.2004');