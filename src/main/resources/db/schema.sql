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

CREATE TABLE plans (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    description VARCHAR(500),
)

CREATE TABLE providers (
    user_id BIGINT PRIMARY KEY, 
    plan_id BIGINT NOT NULL,
    plan_expires_at TIMESTAMP NULL,
    cpf_cnpj VARCHAR(20) NOT NULL,
    bio VARCHAR(500),

    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (plan_id) REFERENCES plans(id),

    -- {experience
    -- verified
    -- rating} ISSO ELE SÓ VAI TER SE PAGAR
);

CREATE TABLE services (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    description VARCHAR(500) NOT NULL,
);

CREATE TABLE provider_services (
    provider_id BIGINT NOT NULL,
    service_id BIGINT NOT NULL,

    PRIMARY KEY (provider_id, service_id),

    FOREIGN KEY (provider_id) REFERENCES providers(user_id),
    FOREIGN KEY (service_id) REFERENCES services(id)
);

