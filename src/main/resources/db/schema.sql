CREATE TABLE users (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,

    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    phone VARCHAR(20) NOT NULL /*UNIQUE SE O PROFISSIONAL FIZER OUTRA CONTA COM O MESMO TELEFONE*/,
    photo_url VARCHAR(500),
    
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_at TIMESTAMP NOT NULL
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
    name VARCHAR(100) NOT NULL,
    active BOOLEAN DEFAULT FALSE
);


CREATE TABLE jobs (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,

    client_id BIGINT NOT NULL,
    provider_id BIGINT NOT NULL,

    service_id BIGINT NOT NULL,

    title VARCHAR(150),
    description TEXT,

    status ENUM(
        'REQUESTED',
        'ACCEPTED',
        'IN_PROGRESS',
        'COMPLETED',
        'CANCELLED'
    ) NOT NULL,

    estimated_price DECIMAL(10,2),
    final_price DECIMAL(10,2),

    estimated_duration_hours SMALLINT,
    actual_duration_hours SMALLINT,

    requested_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    started_at TIMESTAMP NULL,
    completed_at TIMESTAMP NULL,

    FOREIGN KEY (client_id) REFERENCES users(id),
    FOREIGN KEY (provider_id) REFERENCES providers(user_id),
    FOREIGN KEY (service_id) REFERENCES services(id)
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

CREATE TABLE services (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL UNIQUE,
    description TEXT
);

CREATE TABLE job_images (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,

    job_id BIGINT NOT NULL,

    image_url VARCHAR(500) NOT NULL,

    FOREIGN KEY (job_id) REFERENCES jobs(id)
);

CREATE TABLE provider_services (
    provider_id BIGINT NOT NULL,
    service_id BIGINT NOT NULL,

    description TEXT,


    PRIMARY KEY (provider_id, service_id),

    FOREIGN KEY (provider_id) REFERENCES providers(user_id),
    FOREIGN KEY (service_id) REFERENCES services(id)
);

CREATE TABLE provider_service_documents (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    
    provider_id BIGINT NOT NULL,
    service_id BIGINT NOT NULL,

    url VARCHAR(500) NOT NULL,
    description TEXT,

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,


    FOREIGN KEY (provider_id, service_id) REFERENCES provider_services(provider_id, service_id)
);

