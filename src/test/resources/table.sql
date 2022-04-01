drop table if exists person;
CREATE TABLE IF NOT EXISTS person (
                                      id INTEGER  PRIMARY KEY AUTO_INCREMENT,
                                      address varchar(255) ,
                                      birthday date ,
                                      firstname varchar(255) ,
                                      lastname varchar(255)
);

insert into person(firstname, lastname, address, birthday)
values
    ('mouhssine','rh', 'test', parsedatetime('29-12-1993','dd-MM-yyyy')),
    ('mouhssine33','rh33', 'test33', parsedatetime('29-12-1992','dd-MM-yyyy')),
    ('mouhssine2','rh2', 'test2', parsedatetime('29-12-1993','dd-MM-yyyy'));