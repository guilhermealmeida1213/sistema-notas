-- MySQL migration: histórico de professores e autoria de lançamentos.
-- Faça backup do banco antes de executar este script.
-- Registros antigos não recebem um autor artificial: usuario_lancamento_id_usuario fica NULL.

CREATE TABLE IF NOT EXISTS historico_professor_turma_disciplina (
    id_historico_professor INT NOT NULL AUTO_INCREMENT,
    turma_has_disciplina_id_turma_disciplina INT NOT NULL,
    professor_id_professor INT NOT NULL,
    data_inicio DATETIME(6) NULL,
    data_fim DATETIME(6) NULL,
    registrado_em DATETIME(6) NOT NULL,
    registrado_por_email VARCHAR(100) NULL,
    motivo VARCHAR(500) NULL,
    PRIMARY KEY (id_historico_professor),
    KEY idx_hist_prof_td (turma_has_disciplina_id_turma_disciplina),
    KEY idx_hist_prof_professor (professor_id_professor),
    CONSTRAINT fk_hist_prof_turma_disciplina
        FOREIGN KEY (turma_has_disciplina_id_turma_disciplina)
        REFERENCES turma_has_disciplina (id_turma_disciplina),
    CONSTRAINT fk_hist_prof_professor
        FOREIGN KEY (professor_id_professor)
        REFERENCES professor (id_professor)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

ALTER TABLE nota
    ADD COLUMN usuario_lancamento_id_usuario INT NULL,
    ADD KEY idx_nota_usuario_lancamento (usuario_lancamento_id_usuario),
    ADD CONSTRAINT fk_nota_usuario_lancamento
        FOREIGN KEY (usuario_lancamento_id_usuario)
        REFERENCES usuario (id_usuario);

ALTER TABLE falta
    ADD COLUMN usuario_lancamento_id_usuario INT NULL,
    ADD KEY idx_falta_usuario_lancamento (usuario_lancamento_id_usuario),
    ADD CONSTRAINT fk_falta_usuario_lancamento
        FOREIGN KEY (usuario_lancamento_id_usuario)
        REFERENCES usuario (id_usuario);

-- Os vínculos e lançamentos antigos permanecem intactos.
-- O histórico anterior à implantação não possui data inicial nem autoria recuperável;
-- ao abrir o modal, o sistema informa explicitamente essa limitação.
