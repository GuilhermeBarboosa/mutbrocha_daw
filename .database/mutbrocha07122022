--
-- PostgreSQL database dump
--

-- Dumped from database version 15.1
-- Dumped by pg_dump version 15.1

-- Started on 2022-12-07 12:35:13

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 214 (class 1259 OID 16985)
-- Name: authorities; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.authorities (
    username text NOT NULL,
    authority text NOT NULL
);


ALTER TABLE public.authorities OWNER TO postgres;

--
-- TOC entry 215 (class 1259 OID 16990)
-- Name: categorias; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.categorias (
    id integer NOT NULL,
    categoria text NOT NULL,
    status text
);


ALTER TABLE public.categorias OWNER TO postgres;

--
-- TOC entry 216 (class 1259 OID 16995)
-- Name: categorias_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.categorias_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 9999
    CACHE 1;


ALTER TABLE public.categorias_id_seq OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 16996)
-- Name: produtos; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.produtos (
    id integer NOT NULL,
    produto text NOT NULL,
    img text NOT NULL,
    categoria bigint NOT NULL,
    status text NOT NULL,
    situacao text NOT NULL,
    id_usuario bigint
);


ALTER TABLE public.produtos OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 17001)
-- Name: produtos_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.produtos_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 999999
    CACHE 1;


ALTER TABLE public.produtos_id_seq OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 17002)
-- Name: reserva_produto; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.reserva_produto (
    id bigint NOT NULL,
    reserva bigint,
    produto bigint,
    status character varying(255)
);


ALTER TABLE public.reserva_produto OWNER TO postgres;

--
-- TOC entry 220 (class 1259 OID 17005)
-- Name: reserva_produto_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.reserva_produto_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.reserva_produto_id_seq OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 17006)
-- Name: reservas; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.reservas (
    id bigint NOT NULL,
    usuario bigint NOT NULL,
    data_reserva date NOT NULL,
    data_retirada date NOT NULL,
    status text NOT NULL
);


ALTER TABLE public.reservas OWNER TO postgres;

--
-- TOC entry 222 (class 1259 OID 17011)
-- Name: reservas_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.reservas_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 99999
    CACHE 1;


ALTER TABLE public.reservas_id_seq OWNER TO postgres;

--
-- TOC entry 223 (class 1259 OID 17012)
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    id bigint NOT NULL,
    enabled boolean,
    nome character varying(255),
    password character varying(255),
    roles character varying(255),
    status character varying(255),
    username character varying(255)
);


ALTER TABLE public.users OWNER TO postgres;

--
-- TOC entry 224 (class 1259 OID 17017)
-- Name: user_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.user_id_seq OWNER TO postgres;

--
-- TOC entry 3377 (class 0 OID 0)
-- Dependencies: 224
-- Name: user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.user_id_seq OWNED BY public.users.id;


--
-- TOC entry 3361 (class 0 OID 16985)
-- Dependencies: 214
-- Data for Name: authorities; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.authorities (username, authority) FROM stdin;
gui	ROLE_ADM
adm	ROLE_ADM
user	ROLE_USER
\.


--
-- TOC entry 3362 (class 0 OID 16990)
-- Dependencies: 215
-- Data for Name: categorias; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.categorias (id, categoria, status) FROM stdin;
\.


--
-- TOC entry 3364 (class 0 OID 16996)
-- Dependencies: 217
-- Data for Name: produtos; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.produtos (id, produto, img, categoria, status, situacao, id_usuario) FROM stdin;
\.


--
-- TOC entry 3366 (class 0 OID 17002)
-- Dependencies: 219
-- Data for Name: reserva_produto; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.reserva_produto (id, reserva, produto, status) FROM stdin;
\.


--
-- TOC entry 3368 (class 0 OID 17006)
-- Dependencies: 221
-- Data for Name: reservas; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.reservas (id, usuario, data_reserva, data_retirada, status) FROM stdin;
\.


--
-- TOC entry 3370 (class 0 OID 17012)
-- Dependencies: 223
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users (id, enabled, nome, password, roles, status, username) FROM stdin;
2	t	adm	$2a$10$vUj4Dz0KvKQ1bkrrDbzEqOq3OEulBNjqkMEbDfW.5/8fFCcf79Uo2	ROLE_ADM	ATIVO	adm
1	t	Guilherme	$2a$10$HHd1wKsOo4ulldMD5JWZZ.eoeAgTENtVMnXzrCU9nzIlMomB/65O2	ROLE_ADM	ATIVO	gui
3	t	user	$2a$10$Hkqr0fp3HVDREHdfFOD2xOzP.gVhPn5gkfLeeG8pkGpXgAUDFsr56	ROLE_USER	ATIVO	user
\.


--
-- TOC entry 3378 (class 0 OID 0)
-- Dependencies: 216
-- Name: categorias_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.categorias_id_seq', 1, true);


--
-- TOC entry 3379 (class 0 OID 0)
-- Dependencies: 218
-- Name: produtos_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.produtos_id_seq', 1, true);


--
-- TOC entry 3380 (class 0 OID 0)
-- Dependencies: 220
-- Name: reserva_produto_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.reserva_produto_id_seq', 1, true);


--
-- TOC entry 3381 (class 0 OID 0)
-- Dependencies: 222
-- Name: reservas_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.reservas_id_seq', 1, true);


--
-- TOC entry 3382 (class 0 OID 0)
-- Dependencies: 224
-- Name: user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.user_id_seq', 3, true);


--
-- TOC entry 3198 (class 2606 OID 17019)
-- Name: authorities authorities_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.authorities
    ADD CONSTRAINT authorities_pkey PRIMARY KEY (username);


--
-- TOC entry 3200 (class 2606 OID 17021)
-- Name: categorias categorias_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.categorias
    ADD CONSTRAINT categorias_pkey PRIMARY KEY (id);


--
-- TOC entry 3202 (class 2606 OID 17023)
-- Name: produtos produtos_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.produtos
    ADD CONSTRAINT produtos_pkey PRIMARY KEY (id);


--
-- TOC entry 3204 (class 2606 OID 17025)
-- Name: reserva_produto reserva_produto_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reserva_produto
    ADD CONSTRAINT reserva_produto_pkey PRIMARY KEY (id);


--
-- TOC entry 3206 (class 2606 OID 17027)
-- Name: reservas reservas_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reservas
    ADD CONSTRAINT reservas_pkey PRIMARY KEY (id);


--
-- TOC entry 3208 (class 2606 OID 17064)
-- Name: users ukr43af9ap4edm43mmtq01oddj6; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT ukr43af9ap4edm43mmtq01oddj6 UNIQUE (username);


--
-- TOC entry 3210 (class 2606 OID 17029)
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- TOC entry 3212 (class 2606 OID 17031)
-- Name: users users_username_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_username_key UNIQUE (username);


--
-- TOC entry 3213 (class 2606 OID 17032)
-- Name: authorities authorities_username_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.authorities
    ADD CONSTRAINT authorities_username_fkey FOREIGN KEY (username) REFERENCES public.users(username) NOT VALID;


--
-- TOC entry 3214 (class 2606 OID 17037)
-- Name: produtos produtos_categoria_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.produtos
    ADD CONSTRAINT produtos_categoria_fkey FOREIGN KEY (categoria) REFERENCES public.categorias(id) NOT VALID;


--
-- TOC entry 3215 (class 2606 OID 17042)
-- Name: produtos produtos_id_usuario_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.produtos
    ADD CONSTRAINT produtos_id_usuario_fkey FOREIGN KEY (id_usuario) REFERENCES public.users(id) NOT VALID;


--
-- TOC entry 3216 (class 2606 OID 17047)
-- Name: reserva_produto reserva_produto_produto_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reserva_produto
    ADD CONSTRAINT reserva_produto_produto_fkey FOREIGN KEY (produto) REFERENCES public.produtos(id);


--
-- TOC entry 3217 (class 2606 OID 17052)
-- Name: reserva_produto reserva_produto_reserva_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reserva_produto
    ADD CONSTRAINT reserva_produto_reserva_fkey FOREIGN KEY (reserva) REFERENCES public.reservas(id);


--
-- TOC entry 3218 (class 2606 OID 17057)
-- Name: reservas reservas_usuario_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reservas
    ADD CONSTRAINT reservas_usuario_fkey FOREIGN KEY (usuario) REFERENCES public.users(id) NOT VALID;


-- Completed on 2022-12-07 12:35:13

--
-- PostgreSQL database dump complete
--

