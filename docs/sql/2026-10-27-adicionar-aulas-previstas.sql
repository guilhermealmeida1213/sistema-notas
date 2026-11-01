-- Execute no schema mydb antes de iniciar a versão atualizada da aplicação.
-- A coluna antiga dias_letivos_previstos é preservada para não apagar dados.
-- Os valores antigos NÃO são copiados, pois representam dias, não aulas.
ALTER TABLE turma_has_disciplina
    ADD COLUMN aulas_previstas INT NULL;

-- Depois de revisar o calendário/carga horária de cada vínculo, configure o total correto:
-- UPDATE turma_has_disciplina SET aulas_previstas = <TOTAL_DE_AULAS_DO_SEMESTRE> WHERE id_turma_disciplina = <ID_DO_VINCULO>;
