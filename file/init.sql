
CREATE TABLE "public"."sys_user" (
                                     "id" varchar(32) COLLATE "pg_catalog"."default" NOT NULL,
                                     "create_by" varchar(32) COLLATE "pg_catalog"."default" NOT NULL DEFAULT ''::character varying,
                                     "create_at" timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                     "update_by" varchar(32) COLLATE "pg_catalog"."default",
                                     "update_at" timestamp(6) DEFAULT CURRENT_TIMESTAMP,
                                     "name" varchar(32) COLLATE "pg_catalog"."default" NOT NULL,
                                     "password" varchar(64) COLLATE "pg_catalog"."default" NOT NULL,
                                     "delete_at" timestamp(6) DEFAULT NULL::timestamp without time zone,
                                     CONSTRAINT "sys_user_id_pkey" PRIMARY KEY ("id")
)
;

ALTER TABLE "public"."sys_user"
    OWNER TO "postgres";