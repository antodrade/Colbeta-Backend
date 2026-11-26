-- Agregar el código EPS a los usuarios
ALTER TABLE public.usuario
ADD COLUMN codigo_eps VARCHAR(20);

-- Migrar los valores existentes al código PILA
UPDATE public.usuario
SET codigo_eps = CASE
    WHEN LOWER(TRIM(eps)) = 'sanitas' THEN 'EPS005'
    WHEN LOWER(TRIM(eps)) = 'saludtotal' THEN 'EPS002'
    ELSE NULL
END
WHERE eps IS NOT NULL;

-- Evitar continuar si algún usuario no pudo ser asociado
DO $$
BEGIN
    IF EXISTS (
        SELECT 1
        FROM public.usuario
        WHERE eps IS NOT NULL
          AND codigo_eps IS NULL
    ) THEN
        RAISE EXCEPTION 'Hay usuarios con EPS sin correspondencia en el catálogo';
    END IF;
END $$;

-- Crear la llave foránea
ALTER TABLE public.usuario
ADD CONSTRAINT fk_usuario_eps
FOREIGN KEY (codigo_eps)
REFERENCES public.eps(codigo_eps);

-- Crear índice para las consultas por EPS
CREATE INDEX idx_usuario_codigo_eps
ON public.usuario(codigo_eps);

-- Eliminar la columna antigua
ALTER TABLE public.usuario
DROP COLUMN eps;
