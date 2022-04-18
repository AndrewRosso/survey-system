INSERT INTO SURVEY (id, name, start_Date, end_Date, description)
VALUES (1,'Про увлечения', '2022-04-13','2022-05-13','здесь будет описание');
INSERT INTO SURVEY (id, name, start_Date, end_Date, description)
VALUES (2,'Про машину', '2022-04-13','2022-06-13','здесь будет описание');

INSERT INTO QUESTION (id, content, type, survey_id)
VALUES (1,'Какие увлечения больше нравятся: активные или неактивные', 'ONEOPTION',1);
INSERT INTO QUESTION (id, content, type, survey_id)
VALUES (2,'Выберите что больше по душе: лыжи или сноуборд', 'ONEOPTION',1);