CREATE TABLE users (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,

    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE,
    password VARCHAR(100) NOT NULL,
    phone VARCHAR(20) NOT NULL UNIQUE/*UNIQUE SE O PROFISSIONAL FIZER OUTRA CONTA COM O MESMO TELEFONE*/,
    photo_url VARCHAR(500),
    
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
        ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE providers (
    user_id BIGINT PRIMARY KEY, 
    plan ENUM('FREE', 'PREMIUM') NOT NULL DEFAULT 'FREE',
    plan_expires_at TIMESTAMP NULL,
    
    cpf_cnpj VARCHAR(20) NOT NULL,
    bio VARCHAR(500),
    experience SMALLINT DEFAULT 0, -- anos
    verified BOOLEAN DEFAULT FALSE,

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE professions (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL UNIQUE,
    active BOOLEAN DEFAULT TRUE
);

CREATE TABLE service_requests (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,

    client_id BIGINT NOT NULL,
    profession_id BIGINT NOT NULL,

    title VARCHAR(150) NOT NULL,
    description TEXT,

    estimated_price DECIMAL(10,2),
    estimated_duration_hours SMALLINT,

    status ENUM(
        'OPEN',
        'ASSIGNED',
        'CANCELLED',
        'EXPIRED'
    ) NOT NULL DEFAULT 'OPEN',

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (client_id) REFERENCES users(id),
    FOREIGN KEY (profession_id) REFERENCES professions(id)
);

CREATE TABLE service_request_interests (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,

    request_id BIGINT NOT NULL,
    provider_id BIGINT NOT NULL,

    status ENUM(
        'INTERESTED',
        'REJECTED',
        'SELECTED',
        'CANCELLED'
    ) NOT NULL DEFAULT 'INTERESTED',

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    UNIQUE (request_id, provider_id),

    FOREIGN KEY (request_id) REFERENCES service_requests(id),
    FOREIGN KEY (provider_id) REFERENCES providers(user_id)
);

CREATE TABLE jobs (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,

    request_id BIGINT NOT NULL,
    provider_id BIGINT NOT NULL,

    status ENUM(
        'ACCEPTED',
        'IN_PROGRESS',
        'COMPLETED',
        'CANCELLED'
    ) NOT NULL,

    final_price DECIMAL(10,2),

    accepted_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    started_at TIMESTAMP NULL,
    completed_at TIMESTAMP NULL,

    FOREIGN KEY (request_id) REFERENCES service_requests(id),
    FOREIGN KEY (provider_id) REFERENCES providers(user_id)
);

CREATE TABLE reviews (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,

    job_id BIGINT NOT NULL UNIQUE,

    user_id BIGINT NOT NULL,
    provider_id BIGINT NOT NULL,

    rating TINYINT NOT NULL,
    comment TEXT,

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (job_id) REFERENCES jobs(id),
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (provider_id) REFERENCES providers(user_id)
);

CREATE TABLE job_images (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,

    job_id BIGINT NOT NULL,

    image_url VARCHAR(500) NOT NULL,

    FOREIGN KEY (job_id) REFERENCES jobs(id)
);

CREATE TABLE provider_professions (
    provider_id BIGINT NOT NULL,
    profession_id BIGINT NOT NULL,

    description TEXT,


    PRIMARY KEY (provider_id, profession_id),

    FOREIGN KEY (provider_id) REFERENCES providers(user_id),
    FOREIGN KEY (profession_id) REFERENCES professions(id)
);

CREATE TABLE provider_profession_documents (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    
    provider_id BIGINT NOT NULL,
    profession_id BIGINT NOT NULL,

    url VARCHAR(500) NOT NULL,
    description TEXT,

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,


    FOREIGN KEY (provider_id, profession_id) REFERENCES provider_professions(provider_id, profession_id)
);

INSERT INTO professions (name, active) VALUES
    ('Pedreiro', true),
    ('Pintor', true),
    ('Eletricista', true),
    ('Encanador', true),
    ('Marceneiro', true),
    ('Serralheiro', true),
    ('Jardineiro', true),
    ('Azulejista', true),
    ('Gesseiro', true),
    ('Carpinteiro', true),
    ('Mestre de obras', true),
    ('Vidraceiro', true),
    ('Soldador', true),
    ('Telhadista', true),
    ('Impermeabilizador', true),
    ('Instalador de ar-condicionado', true),
    ('Técnico de informática', true),
    ('Montador de móveis', true),
    ('Pedreiro de acabamento', true),
    ('Paisagista', true),
    ('Limpeza residencial', true),
    ('Limpeza comercial', true),
    ('Diarista', true),
    ('Fotógrafo', true),
    ('Cinegrafista', true),
    ('Designer gráfico', true),
    ('Desenvolvedor de software', true),
    ('Técnico de celulares', true),
    ('Mecânico', true),
    ('Funileiro', true),
    ('Eletricista automotivo', true),
    ('Lavador de veículos', true),
    ('Manicure', true),
    ('Cabeleireiro', true),
    ('Barbeiro', true),
    ('Costureiro', true),
    ('Estofador', true),
    ('Decorador', true),
    ('Instalador de pisos', true),
    ('Instalador de portas e janelas', true);