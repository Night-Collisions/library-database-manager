-- Типы публикаций:
-- 0 - книга
-- 1 - сборник трудов
-- 2 - статья
-- 3 - тезисы
-- 4 - техническая документация

-- Пол:
-- 0 - женщина
-- 1 - мужчина
-- 2 - что-то промежуточное

-- Тип пользователя:
-- 0 - администратор
-- 1 - библиотекарь
-- 2 - издательство
-- 3 - автор
-- 4 - читатель

CREATE TABLE publications (
  publications_id SERIAL NOT NULL PRIMARY KEY,
  type SMALLINT NOT NULL CHECK (type >= 0),
  title VARCHAR(1000) NOT NULL
);

CREATE TABLE authors (
  authors_id SERIAL NOT NULL PRIMARY KEY,
  name VARCHAR(50) NOT NULL,
  surname VARCHAR(50),
  patronymic VARCHAR(50),
  sex SMALLINT NOT NULL CHECK (sex IN (0, 1, 2)),
  birth_date DATE,
  death_date DATE,
  phone_number BIGINT,
  email VARCHAR(100),
  CHECK (birth_date <= death_date)
);

CREATE TABLE organizations (
  organizations_id SERIAL NOT NULL PRIMARY KEY,
  title VARCHAR(1000) NOT NULL,
  legal_address VARCHAR(1000) NOT NULL,
  phone_number BIGINT,
  email VARCHAR(100)
);

CREATE TABLE subjects (
  subjects_id SERIAL NOT NULL PRIMARY KEY,
  title VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE magazines (
  magazines_id SERIAL NOT NULL PRIMARY KEY,
  subjects_id INT REFERENCES subjects (subjects_id) ON DELETE RESTRICT,
  organizations_id INT NOT NULL REFERENCES organizations (organizations_id) ON DELETE RESTRICT
);

CREATE TABLE authors_organizations (
  authors_id INT NOT NULL REFERENCES authors (authors_id) ON DELETE CASCADE,
  organizations_id INT NOT NULL REFERENCES organizations (organizations_id) ON DELETE RESTRICT,
  start DATE NOT NULL,
  finish DATE NOT NULL,
  CHECK (start <= finish)
);

CREATE TABLE keywords (
  keywords_id SERIAL NOT NULL PRIMARY KEY,
  keyword VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE publications_keywords (
  publications_id INT NOT NULL REFERENCES publications (publications_id) ON DELETE CASCADE,
  keywords_id INT NOT NULL REFERENCES keywords (keywords_id) ON DELETE RESTRICT,
  UNIQUE (publications_id, keywords_id)
);

CREATE TABLE udc_codes (
  udc_codes_id SERIAL NOT NULL PRIMARY KEY,
  udc_code VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE publications_udc_codes (
  publications_id INT NOT NULL REFERENCES publications (publications_id) ON DELETE CASCADE,
  udc_codes_id INT NOT NULL REFERENCES udc_codes (udc_codes_id) ON DELETE RESTRICT,
  UNIQUE (publications_id, udc_codes_id)
);

CREATE TABLE authors_publications (
  publications_id INT NOT NULL REFERENCES publications (publications_id) ON DELETE CASCADE,
  authors_id INT NOT NULL REFERENCES authors (authors_id) ON DELETE RESTRICT,
  UNIQUE (publications_id, authors_id)
);

CREATE TABLE editors (
  editors_id SERIAL NOT NULL PRIMARY KEY,
  name VARCHAR(50) NOT NULL,
  surname VARCHAR(50),
  patronymic VARCHAR(50),
  sex SMALLINT NOT NULL CHECK (sex IN (0, 1, 2)),
  birth_date DATE,
  death_date DATE,
  phone_number BIGINT,
  email VARCHAR(100),
  CHECK (birth_date <= death_date)
);

CREATE TABLE editors_publications (
  publications_id INT NOT NULL REFERENCES publications (publications_id) ON DELETE CASCADE,
  editors_id INT NOT NULL REFERENCES editors (editors_id) ON DELETE RESTRICT,
  UNIQUE (publications_id, editors_id)
);

CREATE TABLE magazines_publications (
  publications_id INT NOT NULL REFERENCES publications (publications_id) ON DELETE CASCADE,
  magazines_id INT NOT NULL REFERENCES magazines (magazines_id) ON DELETE RESTRICT,
  UNIQUE (publications_id, magazines_id)
);

CREATE TABLE digests (
  digests_id SERIAL NOT NULL PRIMARY KEY,
  subjects_id INT REFERENCES subjects (subjects_id) ON DELETE RESTRICT,
  organizations_id INT NOT NULL REFERENCES organizations (organizations_id) ON DELETE RESTRICT
);

CREATE TABLE digests_publications (
  publications_id INT NOT NULL REFERENCES publications (publications_id) ON DELETE CASCADE,
  digests_id INT NOT NULL REFERENCES digests (digests_id) ON DELETE RESTRICT,
  UNIQUE (publications_id, digests_id)
);

CREATE TABLE publishing_houses (
  publishing_houses_id SERIAL NOT NULL PRIMARY KEY,
  title VARCHAR(1000) NOT NULL,
  legal_address VARCHAR(1000) NOT NULL,
  phone_number BIGINT,
  email VARCHAR(100)
);

CREATE TABLE publishing_houses_publications (
  publications_id INT NOT NULL REFERENCES publications (publications_id) ON DELETE CASCADE,
  publishing_houses_id INT NOT NULL REFERENCES publishing_houses (publishing_houses_id) ON DELETE RESTRICT,
  UNIQUE (publications_id, publishing_houses_id)
);

CREATE TABLE organizations_publications (
  publications_id INT NOT NULL REFERENCES publications (publications_id) ON DELETE CASCADE,
  organizations_id INT NOT NULL REFERENCES organizations (organizations_id) ON DELETE RESTRICT,
  UNIQUE (publications_id, organizations_id)
);

CREATE TABLE magazine_article_info (
  publications_id INT NOT NULL REFERENCES publications (publications_id) ON DELETE CASCADE UNIQUE,
  volume INT NOT NULL CHECK (volume > 0),
  release_number INT NOT NULL CHECK (release_number > 0)
);

CREATE TABLE publishing_years (
  publications_id INT NOT NULL REFERENCES publications (publications_id) ON DELETE CASCADE UNIQUE,
  year INT NOT NULL CHECK (year > 0)
);

CREATE TABLE users (
  users_id SERIAL NOT NULL PRIMARY KEY,
  name VARCHAR(50) NOT NULL,
  surname VARCHAR(50),
  patronymic VARCHAR(50),
  sex SMALLINT NOT NULL CHECK (sex IN (0, 1, 2)),
  login VARCHAR(15) NOT NULL UNIQUE,
  password VARCHAR(50) NOT NULL CHECK (LENGTH(password) >= 8),
  birth_date DATE,
  type SMALLINT NOT NULL CHECK (type IN (0, 1, 2, 3, 4)),
  phone_number BIGINT,
  email VARCHAR(100)
);

CREATE TABLE users_authors (
  users_id INT REFERENCES users (users_id) ON DELETE CASCADE UNIQUE,
  authors_id INT NOT NULL REFERENCES authors (authors_id) ON DELETE RESTRICT
);

CREATE TABLE users_publishing_houses (
  users_id INT REFERENCES users (users_id) ON DELETE CASCADE UNIQUE,
  publishing_houses_id INT NOT NULL REFERENCES publishing_houses (publishing_houses_id) ON DELETE RESTRICT
);

CREATE TABLE verifications (
  verifications_id SERIAL NOT NULL PRIMARY KEY,
  users_id INT REFERENCES users (users_id) ON DELETE CASCADE UNIQUE,
  to_type SMALLINT NOT NULL CHECK (to_type IN (0, 1, 2, 3, 4))
);
