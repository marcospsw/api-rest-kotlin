insert into usuario (id, nome, email, password)
values (1, 'marcos', 'marcospsw96@gmail.com', '$2a$12$IMBC9cEOkjZhHsybi0e9ZuGYOGglUCOknfiI8xa6Cf4xm8Ce.SLSO');

insert into role(id, nome)
values (1, 'LEITURA_ESCRITA');

insert into usuario_role(id, usuario_id, role_id)
values (1, 1, 1)