-- ==========================================
-- CRIAÇÃO DOS TIPOS ENUM
-- ==========================================
CREATE TYPE plan_enum AS ENUM ('FREE', 'PREMIUM');
CREATE TYPE request_status_enum AS ENUM ('OPEN', 'ASSIGNED', 'CANCELLED', 'EXPIRED');
CREATE TYPE interest_status_enum AS ENUM ('INTERESTED', 'REJECTED');
CREATE TYPE job_status_enum AS ENUM ('ACCEPTED', 'IN_PROGRESS', 'COMPLETED', 'CANCELLED');

-- ==========================================
-- FUNÇÃO PARA ATUALIZAR O updated_at (TRIGGER)
-- ==========================================
CREATE OR REPLACE FUNCTION update_updated_at_column()
RETURNS TRIGGER AS $$
BEGIN
    NEW.updated_at = CURRENT_TIMESTAMP;
    RETURN NEW;
END;
$$ language 'plpgsql';


-- ==========================================
-- TABELAS
-- ==========================================

CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,

    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE,
    password VARCHAR(100) NOT NULL,
    phone VARCHAR(20) NOT NULL UNIQUE, -- UNIQUE SE O PROFISSIONAL FIZER OUTRA CONTA COM O MESMO TELEFONE
    photo_url VARCHAR(500),
    
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TRIGGER update_users_updated_at
    BEFORE UPDATE ON users
    FOR EACH ROW
    EXECUTE FUNCTION update_updated_at_column();


CREATE TABLE providers (
    user_id BIGINT PRIMARY KEY, 
    plan plan_enum NOT NULL DEFAULT 'FREE',
    plan_expires_at TIMESTAMP NULL,
    
    cpf_cnpj VARCHAR(20) NOT NULL,
    bio VARCHAR(500),
    experience SMALLINT DEFAULT 0, -- anos
    verified BOOLEAN DEFAULT FALSE,

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TRIGGER update_providers_updated_at
    BEFORE UPDATE ON providers
    FOR EACH ROW
    EXECUTE FUNCTION update_updated_at_column();


CREATE TABLE professions (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE,
    active BOOLEAN DEFAULT TRUE
);


CREATE TABLE service_requests (
    id BIGSERIAL PRIMARY KEY,

    client_id BIGINT NOT NULL,

    title VARCHAR(150) NOT NULL,
    description TEXT,

    estimated_price DECIMAL(10,2),
    estimated_duration_hours SMALLINT,

    status request_status_enum NOT NULL DEFAULT 'OPEN',

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (client_id) REFERENCES users(id)
);


CREATE TABLE service_request_professions (
    id BIGSERIAL PRIMARY KEY,

    profession_id BIGINT NOT NULL,
    request_id BIGINT NOT NULL,

    FOREIGN KEY (request_id)
        REFERENCES service_requests(id)
        ON DELETE CASCADE,

    FOREIGN KEY (profession_id)
        REFERENCES professions(id)
        ON DELETE CASCADE,

    UNIQUE (request_id, profession_id)
);


CREATE TABLE service_request_interests (
    id BIGSERIAL PRIMARY KEY,

    request_id BIGINT NOT NULL,
    provider_id BIGINT NOT NULL,

    status interest_status_enum NOT NULL,

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    UNIQUE (request_id, provider_id),

    FOREIGN KEY (request_id) REFERENCES service_requests(id),
    FOREIGN KEY (provider_id) REFERENCES providers(user_id)
);

CREATE TRIGGER update_service_request_interests_updated_at
    BEFORE UPDATE ON service_request_interests
    FOR EACH ROW
    EXECUTE FUNCTION update_updated_at_column();


CREATE TABLE jobs (
    id BIGSERIAL PRIMARY KEY,

    request_id BIGINT NOT NULL,
    provider_id BIGINT NOT NULL,

    status job_status_enum NOT NULL,

    final_price DECIMAL(10,2),

    accepted_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    started_at TIMESTAMP NULL,
    completed_at TIMESTAMP NULL,

    FOREIGN KEY (request_id) REFERENCES service_requests(id),
    FOREIGN KEY (provider_id) REFERENCES providers(user_id)
);


CREATE TABLE reviews (
    id BIGSERIAL PRIMARY KEY,

    job_id BIGINT NOT NULL UNIQUE,

    user_id BIGINT NOT NULL,
    provider_id BIGINT NOT NULL,

    rating SMALLINT NOT NULL, -- TINYINT não existe no Postgres, usa-se SMALLINT
    comment TEXT,

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (job_id) REFERENCES jobs(id),
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (provider_id) REFERENCES providers(user_id)
);


CREATE TABLE job_images (
    id BIGSERIAL PRIMARY KEY,

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
    id BIGSERIAL PRIMARY KEY,
    
    provider_id BIGINT NOT NULL,
    profession_id BIGINT NOT NULL,

    url VARCHAR(500) NOT NULL,
    description TEXT,

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (provider_id, profession_id) REFERENCES provider_professions(provider_id, profession_id)
);


-- ==========================================
-- INSERTS DE DADOS
-- ==========================================

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

-- ==========================================
-- CLIENTES DE TESTE
-- ==========================================

INSERT INTO users (
    name,
    email,
    password,
    phone,
    photo_url
) VALUES
(
    'Carlos Oliveira',
    'carlos.cliente@teste.com',
    '123456',
    '11988887777',
    NULL
),
(
    'Mariana Santos',
    'mariana.cliente@teste.com',
    '123456',
    '11977776666',
    NULL
);

-- ==========================================
-- SOLICITAÇÃO DO CARLOS
-- ==========================================

INSERT INTO service_requests (
    client_id,
    title,
    description,
    estimated_price,
    estimated_duration_hours,
    status
)
SELECT
    id,
    'Instalação elétrica residencial',
    'Preciso instalar novas tomadas e revisar a instalação elétrica de dois quartos.',
    850.00,
    8,
    'OPEN'
FROM users
WHERE email = 'carlos.cliente@teste.com';

-- ==========================================
-- PROFISSÃO ACEITA PELA SOLICITAÇÃO (Eletricista)
-- ==========================================

INSERT INTO service_request_professions (
    request_id,
    profession_id
)
SELECT
    sr.id,
    p.id
FROM service_requests sr
JOIN professions p
    ON p.name = 'Eletricista'
WHERE sr.title = 'Instalação elétrica residencial'
  AND sr.client_id = (
      SELECT id
      FROM users
      WHERE email = 'carlos.cliente@teste.com'
  );

-- ==========================================
-- SOLICITAÇÃO DA MARIANA (Pintura)
-- ==========================================

INSERT INTO service_requests (
    client_id,
    title,
    description,
    estimated_price,
    estimated_duration_hours,
    status
)
SELECT
    id,
    'Pintura da sala e dos quartos',
    'Preciso pintar a sala e dois quartos. As paredes já estão preparadas e preciso apenas da pintura.',
    1200.00,
    12,
    'OPEN'
FROM users
WHERE email = 'mariana.cliente@teste.com';

-- ==========================================
-- PROFISSÃO ACEITA PELA SOLICITAÇÃO (Pintor)
-- ==========================================

INSERT INTO service_request_professions (
    request_id,
    profession_id
)
SELECT
    sr.id,
    p.id
FROM service_requests sr
JOIN professions p
    ON p.name = 'Pintor'
WHERE sr.title = 'Pintura da sala e dos quartos'
  AND sr.client_id = (
      SELECT id
      FROM users
      WHERE email = 'mariana.cliente@teste.com'
  );

-- ==========================================
-- SOLICITAÇÃO DA MARIANA (Muro)
-- ==========================================

INSERT INTO service_requests (
    client_id,
    title,
    description,
    estimated_price,
    estimated_duration_hours,
    status
)
SELECT
    id,
    'Construção de muro no quintal',
    'Preciso construir um muro de aproximadamente 15 metros no quintal, incluindo levantamento das paredes e acabamento básico.',
    2500.00,
    24,
    'OPEN'
FROM users
WHERE email = 'mariana.cliente@teste.com';

INSERT INTO service_request_professions (
    request_id,
    profession_id
)
SELECT
    sr.id,
    p.id
FROM service_requests sr
JOIN professions p
    ON p.name = 'Pedreiro'
WHERE sr.title = 'Construção de muro no quintal'
  AND sr.client_id = (
      SELECT id
      FROM users
      WHERE email = 'mariana.cliente@teste.com'
  );