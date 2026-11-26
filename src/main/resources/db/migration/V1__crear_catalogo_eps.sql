CREATE TABLE eps (
    codigo_eps VARCHAR(20) PRIMARY KEY,
    nombre VARCHAR(150) NOT NULL,
    regimen VARCHAR(20) NOT NULL,
    activo BOOLEAN NOT NULL DEFAULT TRUE,

    CONSTRAINT chk_eps_regimen
        CHECK (regimen IN ('CONTRIBUTIVO', 'SUBSIDIADO'))
);
