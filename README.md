# Desafio Técnico Java | CWI Software - Rui Reis Jr

## Configurações
* Versões utilizadas/testadas: Java 8 e 15
* Banco de dados utilizado: PostgreSQL 10
* Porta utilizada: 8082
* Ferramentas utilizadas: STS 4.9.0, DBeaver 7.3.5, Postman 9.1.3
* Endereço OpenAPI/Swagger: http://localhost:8082/swagger-ui/#/
* Script de criação do Banco de Dados encontra-se ao fim do deste README.md

## Observações
* Poderia ter feito uso do Flyway para realizar a migração de dados, mas optei por uma abordagem mais simplificada para criação das tabelas e alimentação inicial de dados
* Não achei necessária a criação de endpoints de busca por ids de Pauta/Associado/Sessao/Voto e, por esse motivo, optei por retornar somente o "HttpStatus Created", sem o endpoint de busca correspondente (Uri)
* Optei pelo uso de queries naturais para resolver muitas das consultas, pois é o padrão utilizado em meu trabalho atual. JPA Criteria também poderia ter sido utilizada, mas demandaria um pouco mais de tempo
* Considerei que um Pauta só poderia ter uma Sessao xxx

## Script de Banco de Dados
* Nome da Database: cwi

```sql
CREATE DATABASE cwi;

CREATE TABLE public.pauta (
	id bigserial NOT NULL,
	descricao text NOT NULL,
	data date NOT NULL,
	CONSTRAINT pauta_pkey PRIMARY KEY (id)
);

CREATE TABLE public.sessao_votacao (
	id bigserial NOT NULL,
	pauta_id bigint NOT NULL,
	aberta boolean,
	tempo_de_abertura_em_segundos int8,
	CONSTRAINT sessao_votacao_pkey PRIMARY KEY (id),
	CONSTRAINT pauta_fkey_1 FOREIGN KEY (pauta_id) REFERENCES public.pauta(id)
);

CREATE TABLE public.associado (
	id bigserial NOT NULL,
	nome text NOT NULL,
	cpf text NOT NULL,
	CONSTRAINT associado_pkey PRIMARY KEY (id)
);

CREATE TABLE public.voto (
	id bigserial NOT NULL,
	pauta_id bigint NOT NULL,
	associado_id bigint NOT NULL,
	opcao text NOT NULL,
	CONSTRAINT voto_pkey PRIMARY KEY (id),
	CONSTRAINT pauta_fkey_2 FOREIGN KEY (pauta_id) REFERENCES public.pauta(id),
	CONSTRAINT associado_fkey_1 FOREIGN KEY (associado_id) REFERENCES public.associado(id)
);

INSERT INTO public.associado (nome, cpf) VALUES('Chaves', '39798586000');
INSERT INTO public.associado (nome, cpf) VALUES('Seu Madruga', '37295385002');
INSERT INTO public.associado (nome, cpf) VALUES('Chiquinha', '90518781097');
INSERT INTO public.associado (nome, cpf) VALUES('Professor Girafales', '82478826046');
INSERT INTO public.associado (nome, cpf) VALUES('Dona Florinda', '08741448090');
INSERT INTO public.associado (nome, cpf) VALUES('Dona Clotilde', '91759172030');
INSERT INTO public.associado (nome, cpf) VALUES('Kiko', '74702829041');
INSERT INTO public.associado (nome, cpf) VALUES('Doutor Chapatin', '65324328065');
INSERT INTO public.associado (nome, cpf) VALUES('Tripa Seca', '33818254028');
INSERT INTO public.associado (nome, cpf) VALUES('Quase Nada', '09993832057');
INSERT INTO public.associado (nome, cpf) VALUES('Poucas Trancas', '12312312300'); -- CPF inválido propositalmente
```
