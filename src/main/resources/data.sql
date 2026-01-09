INSERT INTO users
(name, email, login, password, type, last_updated, street, number, district, city, state, zip_code, complement)
VALUES
    (
        'João Silva',
        'joao.silva@email.com',
        'joaosilva',
        '123456',
        'CUSTOMER',
        now(),
        'Rua das Flores',
        '123',
        'Centro',
        'São Paulo',
        'SP',
        '01000-000',
        'Apto 12'
    );

INSERT INTO users
(name, email, login, password, type, last_updated, street, number, district, city, state, zip_code, complement)
VALUES
    (
        'Maria Oliveira',
        'maria.oliveira@email.com',
        'mariaoliveira',
        '123456',
        'OWNER',
        now(),
        'Av. Paulista',
        '1000',
        'Bela Vista',
        'São Paulo',
        'SP',
        '01310-100',
        'Sala 101'
    );
