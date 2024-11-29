--
-- PostgreSQL database dump
--

-- Dumped from database version 16.2 (Debian 16.2-1.pgdg120+2)
-- Dumped by pg_dump version 16.2 (Debian 16.2-1.pgdg120+2)

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

--
-- Name: public; Type: SCHEMA; Schema: -; Owner: pg_database_owner
--
DROP SCHEMA public;

CREATE SCHEMA public;


ALTER SCHEMA public OWNER TO pg_database_owner;

--
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: pg_database_owner
--

COMMENT ON SCHEMA public IS 'standard public schema';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: orders; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.orders (
    id bigint NOT NULL,
    complete boolean,
    quantity integer,
    ship_date timestamp(6) without time zone,
    status character varying(255),
    pet_id bigint,
    CONSTRAINT orders_status_check CHECK (((status)::text = ANY ((ARRAY['PLACED'::character varying, 'APPROVED'::character varying, 'DELIVERED'::character varying])::text[])))
);


ALTER TABLE public.orders OWNER TO postgres;

--
-- Name: orders_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.orders ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.orders_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: pets; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.pets (
    pet_id bigint NOT NULL,
    name character varying(255),
    status character varying(255),
    CONSTRAINT pets_status_check CHECK (((status)::text = ANY ((ARRAY['AVAILABLE'::character varying, 'NOT_AVAILABLE'::character varying])::text[])))
);


ALTER TABLE public.pets OWNER TO postgres;

--
-- Name: pets_pet_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.pets ALTER COLUMN pet_id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.pets_pet_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Data for Name: orders; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.orders (id, complete, quantity, ship_date, status, pet_id) VALUES (1, true, 1, '2024-11-29 10:37:25', 'DELIVERED', 1);
INSERT INTO public.orders (id, complete, quantity, ship_date, status, pet_id) VALUES (2, false, 2, '2024-11-07 13:57:00', 'APPROVED', 1);


--
-- Data for Name: pets; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.pets (pet_id, name, status) VALUES (1, 'Cat', 'AVAILABLE');
INSERT INTO public.pets (pet_id, name, status) VALUES (2, 'Dog', 'AVAILABLE');
INSERT INTO public.pets (pet_id, name, status) VALUES (3, 'Snake', 'NOT_AVAILABLE');
INSERT INTO public.pets (pet_id, name, status) VALUES (4, 'Tiger', 'NOT_AVAILABLE');
INSERT INTO public.pets (pet_id, name, status) VALUES (5, 'Capibara', 'AVAILABLE');


--
-- Name: orders_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.orders_id_seq', 2, true);


--
-- Name: pets_pet_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.pets_pet_id_seq', 6, true);


--
-- Name: orders orders_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT orders_pkey PRIMARY KEY (id);


--
-- Name: pets pets_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pets
    ADD CONSTRAINT pets_pkey PRIMARY KEY (pet_id);


--
-- Name: orders fkfgmv00qw4bfdwcsbigsr68xvj; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT fkfgmv00qw4bfdwcsbigsr68xvj FOREIGN KEY (pet_id) REFERENCES public.pets(pet_id);


--
-- PostgreSQL database dump complete
--

