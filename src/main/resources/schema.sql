DROP TABLE IF EXISTS pagamento;

CREATE TABLE IF NOT EXISTS pagamento(
    id BIGSERIAL PRIMARY KEY,
    valor DECIMAL(4, 2) NOT NULL,
    nome VARCHAR(100) NOT NULL,
    numero VARCHAR(19) NOT NULL,
    expiracao VARCHAR(7) NOT NULL,
    codigo VARCHAR(3) NOT NULL,
    status VARCHAR(255) NOT NULL,
    pedido_id BIGINT NOT NULL,
    forma_de_pagamento BIGINT NOT NULL,

    CHECK (valor >= 0),
    CHECK (status in ('CRIADO', 'CONFIRMADO', 'CANCELADO')),
    CHECK (forma_de_pagamento >= 0)
);

INSERT INTO pagamento(valor, nome, numero, expiracao, codigo, status, pedido_id, forma_de_pagamento)
    VALUES (20.50, 'Pagamento', '4111.1111.1111.1111', '24/2002', '123', 'CRIADO', 1, 1);