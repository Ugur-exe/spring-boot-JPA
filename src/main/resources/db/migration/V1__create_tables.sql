--
-- Name: cities; Type: TABLE; Schema: student; Owner: postgres
--
CREATE TABLE student.cities (
    id character varying(255) NOT NULL primary key ,
    created_at timestamp without time zone NOT NULL,
    updated_at timestamp without time zone,
    code character varying(3) NOT NULL constraint uk_cities_unique_code unique,
    name character varying(50) NOT NULL constraint uk_cities_unique_name unique
);
