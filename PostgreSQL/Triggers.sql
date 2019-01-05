CREATE OR REPLACE FUNCTION check_intervals() RETURNS TRIGGER AS $$
BEGIN
  PERFORM 1
  FROM authors_organizations ao
  WHERE NEW.start BETWEEN ao.start AND ao.finish
     OR NEW.finish BETWEEN ao.start AND ao.finish
     OR ao.start BETWEEN NEW.start AND NEW.finish
     OR ao.finish BETWEEN NEW.start AND NEW.finish;

  IF FOUND THEN
    RAISE EXCEPTION 'Автор в это время уже работал.';
  END IF;

  RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER authors_organizations_insert
BEFORE INSERT ON authors_organizations
  FOR EACH ROW EXECUTE PROCEDURE check_intervals();

CREATE OR REPLACE FUNCTION check_authors_publications() RETURNS TRIGGER AS $$
BEGIN
  PERFORM 1
  FROM publications p
  WHERE p.publications_id = NEW.publications_id
    AND p.type IN (0, 2, 3);

  IF NOT FOUND THEN
    RAISE EXCEPTION 'Неверный тип публикации.';
  END IF;

  RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER authors_publications_insert
BEFORE INSERT ON authors_publications
  FOR EACH ROW EXECUTE PROCEDURE check_authors_publications();

CREATE OR REPLACE FUNCTION check_editors_publications() RETURNS TRIGGER AS $$
BEGIN
  PERFORM 1
  FROM publications p
  WHERE p.publications_id = NEW.publications_id
    AND p.type IN (0, 1);

  IF NOT FOUND THEN
    RAISE EXCEPTION 'Неверный тип публикации.';
  END IF;

  RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER editors_publications_insert
BEFORE INSERT ON editors_publications
  FOR EACH ROW EXECUTE PROCEDURE check_editors_publications();

CREATE OR REPLACE FUNCTION check_magazines_publications() RETURNS TRIGGER AS $$
BEGIN
  PERFORM 1
  FROM publications p
  WHERE p.publications_id = NEW.publications_id
    AND p.type IN (2, 3);

  IF NOT FOUND THEN
    RAISE EXCEPTION 'Неверный тип публикации.';
  END IF;

  RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER magazines_publications_insert
BEFORE INSERT ON magazines_publications
  FOR EACH ROW EXECUTE PROCEDURE check_magazines_publications();

CREATE OR REPLACE FUNCTION check_digests_publications() RETURNS TRIGGER AS $$
BEGIN
  PERFORM 1
  FROM publications p
  WHERE p.publications_id = NEW.publications_id
    AND p.type IN (2, 3);

  IF NOT FOUND THEN
    RAISE EXCEPTION 'Неверный тип публикации.';
  END IF;

  RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER digests_publications_insert
BEFORE INSERT ON digests_publications
  FOR EACH ROW EXECUTE PROCEDURE check_digests_publications();

CREATE OR REPLACE FUNCTION check_publishing_houses_publications() RETURNS TRIGGER AS $$
BEGIN
  PERFORM 1
  FROM publications p
  WHERE p.publications_id = NEW.publications_id
    AND p.type IN (0, 1);

  IF NOT FOUND THEN
    RAISE EXCEPTION 'Неверный тип публикации.';
  END IF;

  RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER publishing_houses_publications_insert
BEFORE INSERT ON publishing_houses_publications
  FOR EACH ROW EXECUTE PROCEDURE check_publishing_houses_publications();

CREATE OR REPLACE FUNCTION check_organizations_publications() RETURNS TRIGGER AS $$
BEGIN
  PERFORM 1
  FROM publications p
  WHERE p.publications_id = NEW.publications_id
    AND p.type = 4;

  IF NOT FOUND THEN
    RAISE EXCEPTION 'Неверный тип публикации.';
  END IF;

  RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER organizations_publications_insert
BEFORE INSERT ON organizations_publications
  FOR EACH ROW EXECUTE PROCEDURE check_organizations_publications();

CREATE OR REPLACE FUNCTION check_magazine_article_info() RETURNS TRIGGER AS $$
BEGIN
  PERFORM 1
  FROM publications p
  JOIN magazines_publications mp ON p.publications_id = mp.publications_id
  WHERE p.publications_id = NEW.publications_id
    AND p.type = 2;

  IF NOT FOUND THEN
    RAISE EXCEPTION 'Неверный тип публикации.';
  END IF;

  RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER magazine_article_info_insert
BEFORE INSERT ON magazine_article_info
  FOR EACH ROW EXECUTE PROCEDURE check_magazine_article_info();

CREATE OR REPLACE FUNCTION check_publishing_years() RETURNS TRIGGER AS $$
BEGIN
  PERFORM 1
  FROM publications p
  WHERE p.publications_id = NEW.publications_id
    AND p.type IN (0, 1);

  IF NOT FOUND THEN
    RAISE EXCEPTION 'Неверный тип публикации.';
  END IF;

  RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER publishing_years_insert
BEFORE INSERT ON publishing_years
  FOR EACH ROW EXECUTE PROCEDURE check_publishing_years();

CREATE OR REPLACE FUNCTION check_users_authors() RETURNS TRIGGER AS $$
BEGIN
  PERFORM 1
  FROM users_publishing_houses uph
  WHERE uph.users_id = NEW.users_id;

  IF FOUND THEN
    RAISE EXCEPTION 'Пользователь уже привязан к издательству.';
  END IF;

  RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER users_authors_insert
BEFORE INSERT ON users_authors
  FOR EACH ROW EXECUTE PROCEDURE check_users_authors();

CREATE OR REPLACE FUNCTION check_users_publishing_houses() RETURNS TRIGGER AS $$
BEGIN
  PERFORM 1
  FROM users_authors ua
  WHERE ua.users_id = NEW.users_id;

  IF FOUND THEN
    RAISE EXCEPTION 'Пользователь уже привязан к автору.';
  END IF;

  RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER users_publishing_houses_insert
BEFORE INSERT ON users_publishing_houses
  FOR EACH ROW EXECUTE PROCEDURE check_users_publishing_houses();