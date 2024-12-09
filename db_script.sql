-- This script was generated by the ERD tool in pgAdmin 4.
-- Please log an issue at https://github.com/pgadmin-org/pgadmin4/issues/new/choose if you find any bugs, including reproduction steps.
BEGIN;


CREATE TABLE IF NOT EXISTS public.abd_impact_analysis
(
    id uuid NOT NULL,
    code character varying(255) COLLATE pg_catalog."default" NOT NULL,
    description text COLLATE pg_catalog."default",
    impact_analysis_id uuid NOT NULL,
    change_configuration_baseline_id uuid NOT NULL,
    implementation_configuration_baseline_id uuid NOT NULL,
    implementation_object_id uuid NOT NULL,
    status_id uuid NOT NULL,
    approximation_id uuid NOT NULL,
    criticality_id uuid NOT NULL,
    created_by character varying(255) COLLATE pg_catalog."default",
    created_date timestamp with time zone,
    deleted_by character varying(255) COLLATE pg_catalog."default",
    deleted_date timestamp with time zone,
    CONSTRAINT pk_abd_impact_analysis PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.approximation
(
    id uuid NOT NULL,
    justification text COLLATE pg_catalog."default" NOT NULL,
    limitation_id uuid,
    file character varying(255) COLLATE pg_catalog."default" NOT NULL,
    revision character varying(255) COLLATE pg_catalog."default" NOT NULL,
    tag_id uuid NOT NULL,
    tag_attribute_id uuid NOT NULL,
    calc_value numeric(19, 2) NOT NULL,
    created_by character varying(255) COLLATE pg_catalog."default",
    created_date timestamp with time zone,
    deleted_by character varying(255) COLLATE pg_catalog."default",
    deleted_date timestamp with time zone,
    CONSTRAINT pk_approximation PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.building
(
    id uuid NOT NULL,
    kks_code character varying(255) COLLATE pg_catalog."default" NOT NULL,
    pbs_code_id uuid NOT NULL,
    description text COLLATE pg_catalog."default",
    created_by character varying(255) COLLATE pg_catalog."default",
    created_date timestamp with time zone,
    deleted_by character varying(255) COLLATE pg_catalog."default",
    deleted_date timestamp with time zone,
    CONSTRAINT pk_building PRIMARY KEY (id)
);

COMMENT ON TABLE public.building
    IS 'Description of the structure block - building.';

COMMENT ON COLUMN public.building.kks_code
    IS 'KKS code of this structural unit.';

COMMENT ON COLUMN public.building.pbs_code_id
    IS 'PBS code of this structural unit.';

COMMENT ON COLUMN public.building.description
    IS 'Description of this structural unit.';

CREATE TABLE IF NOT EXISTS public.change_request
(
    id uuid NOT NULL,
    text text COLLATE pg_catalog."default" NOT NULL,
    created_by character varying(255) COLLATE pg_catalog."default",
    created_date timestamp with time zone,
    deleted_by character varying(255) COLLATE pg_catalog."default",
    deleted_date timestamp with time zone,
    CONSTRAINT pk_change_request PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.criticality
(
    id uuid NOT NULL,
    name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    created_by character varying(255) COLLATE pg_catalog."default",
    created_date timestamp with time zone,
    deleted_by character varying(255) COLLATE pg_catalog."default",
    deleted_date timestamp with time zone,
    CONSTRAINT pk_criticality PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.databasechangelog
(
    id character varying(255) COLLATE pg_catalog."default" NOT NULL,
    author character varying(255) COLLATE pg_catalog."default" NOT NULL,
    filename character varying(255) COLLATE pg_catalog."default" NOT NULL,
    dateexecuted timestamp without time zone NOT NULL,
    orderexecuted integer NOT NULL,
    exectype character varying(10) COLLATE pg_catalog."default" NOT NULL,
    md5sum character varying(35) COLLATE pg_catalog."default",
    description character varying(255) COLLATE pg_catalog."default",
    comments character varying(255) COLLATE pg_catalog."default",
    tag character varying(255) COLLATE pg_catalog."default",
    liquibase character varying(20) COLLATE pg_catalog."default",
    contexts character varying(255) COLLATE pg_catalog."default",
    labels character varying(255) COLLATE pg_catalog."default",
    deployment_id character varying(10) COLLATE pg_catalog."default"
);

CREATE TABLE IF NOT EXISTS public.databasechangeloglock
(
    id integer NOT NULL,
    locked boolean NOT NULL,
    lockgranted timestamp without time zone,
    lockedby character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT databasechangeloglock_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.design_condition
(
    id uuid NOT NULL,
    name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    created_by character varying(255) COLLATE pg_catalog."default",
    created_date timestamp with time zone,
    deleted_by character varying(255) COLLATE pg_catalog."default",
    deleted_date timestamp with time zone,
    CONSTRAINT pk_design_condition PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.document
(
    id uuid NOT NULL,
    code character varying(255) COLLATE pg_catalog."default" NOT NULL,
    created_by character varying(255) COLLATE pg_catalog."default",
    revision character varying(255) COLLATE pg_catalog."default" NOT NULL,
    document_type_id uuid NOT NULL,
    created_date timestamp with time zone,
    deleted_by character varying(255) COLLATE pg_catalog."default",
    deleted_date timestamp with time zone,
    CONSTRAINT pk_document PRIMARY KEY (id)
);

COMMENT ON COLUMN public.document.code
    IS 'Document code.';

COMMENT ON COLUMN public.document.revision
    IS 'Document revision.';

CREATE TABLE IF NOT EXISTS public.document_initial_event_link
(
    document_id uuid NOT NULL,
    initial_event_id uuid NOT NULL,
    CONSTRAINT pk_document_initial_event_link PRIMARY KEY (document_id, initial_event_id)
);

CREATE TABLE IF NOT EXISTS public.document_type
(
    id uuid NOT NULL,
    name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    created_by character varying(255) COLLATE pg_catalog."default",
    created_date timestamp with time zone,
    deleted_by character varying(255) COLLATE pg_catalog."default",
    deleted_date timestamp with time zone,
    CONSTRAINT pk_document_type PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.file
(
    id uuid NOT NULL,
    name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    created_by character varying(255) COLLATE pg_catalog."default",
    created_date timestamp with time zone,
    deleted_by character varying(255) COLLATE pg_catalog."default",
    deleted_date timestamp with time zone,
    CONSTRAINT pk_file PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.flowui_filter_configuration
(
    id uuid NOT NULL,
    component_id character varying(255) COLLATE pg_catalog."default" NOT NULL,
    configuration_id character varying(255) COLLATE pg_catalog."default" NOT NULL,
    username character varying(255) COLLATE pg_catalog."default",
    root_condition text COLLATE pg_catalog."default",
    sys_tenant_id character varying(255) COLLATE pg_catalog."default",
    name character varying(255) COLLATE pg_catalog."default",
    default_for_all boolean,
    CONSTRAINT "FLOWUI_FILTER_CONFIGURATION_pkey" PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.flowui_user_settings
(
    id uuid NOT NULL,
    create_ts timestamp without time zone,
    created_by character varying(255) COLLATE pg_catalog."default",
    username character varying(255) COLLATE pg_catalog."default",
    key_ character varying(255) COLLATE pg_catalog."default",
    value_ text COLLATE pg_catalog."default",
    CONSTRAINT "FLOWUI_USER_SETTINGS_pkey" PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.impact_analysis
(
    id uuid NOT NULL,
    code character varying(255) COLLATE pg_catalog."default" NOT NULL,
    description character varying(255) COLLATE pg_catalog."default" NOT NULL,
    change_request_id uuid NOT NULL,
    change_configuration_baseline_id uuid NOT NULL,
    implementation_configuration_baseline_id uuid NOT NULL,
    implementation_object_id uuid NOT NULL,
    status_id uuid NOT NULL,
    document_id uuid NOT NULL,
    revision character varying(255) COLLATE pg_catalog."default" NOT NULL,
    deleted_by character varying(255) COLLATE pg_catalog."default",
    deleted_date timestamp with time zone,
    created_by character varying(255) COLLATE pg_catalog."default",
    created_date timestamp with time zone,
    CONSTRAINT pk_impact_analysis PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.initial_event
(
    id uuid NOT NULL,
    code character varying(255) COLLATE pg_catalog."default" NOT NULL,
    description text COLLATE pg_catalog."default",
    initialevent_group_id uuid NOT NULL,
    created_by character varying(255) COLLATE pg_catalog."default",
    created_date timestamp with time zone,
    deleted_by character varying(255) COLLATE pg_catalog."default",
    deleted_date timestamp with time zone,
    CONSTRAINT pk_initial_event PRIMARY KEY (id)
);

COMMENT ON COLUMN public.initial_event.code
    IS 'Initial event code.';

COMMENT ON COLUMN public.initial_event.description
    IS 'Initial event description.';

CREATE TABLE IF NOT EXISTS public.initial_event_group
(
    id uuid NOT NULL,
    name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    created_by character varying(255) COLLATE pg_catalog."default",
    created_date timestamp with time zone,
    deleted_by character varying(255) COLLATE pg_catalog."default",
    deleted_date timestamp with time zone,
    CONSTRAINT pk_initial_event_group PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.limitation
(
    id uuid NOT NULL,
    deleted_by character varying(255) COLLATE pg_catalog."default",
    deleted_date timestamp with time zone,
    created_by character varying(255) COLLATE pg_catalog."default",
    created_date timestamp with time zone,
    CONSTRAINT pk_limitation PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.pbs_code
(
    id uuid NOT NULL,
    code character varying(255) COLLATE pg_catalog."default" NOT NULL,
    description text COLLATE pg_catalog."default",
    created_by character varying(255) COLLATE pg_catalog."default",
    created_date timestamp with time zone,
    deleted_by character varying(255) COLLATE pg_catalog."default",
    deleted_date timestamp with time zone,
    CONSTRAINT pk_pbs_code PRIMARY KEY (id)
);

COMMENT ON COLUMN public.pbs_code.id
    IS 'PBS code database identifier.';

COMMENT ON COLUMN public.pbs_code.code
    IS 'PBS code value.';

COMMENT ON COLUMN public.pbs_code.description
    IS 'PBS code description. What object does the PBS code belong to ?';

CREATE TABLE IF NOT EXISTS public.persistent_logins
(
    series character varying(64) COLLATE pg_catalog."default" NOT NULL,
    username character varying(64) COLLATE pg_catalog."default" NOT NULL,
    token character varying(64) COLLATE pg_catalog."default" NOT NULL,
    last_used timestamp without time zone NOT NULL,
    CONSTRAINT persistent_logins_pkey PRIMARY KEY (series)
);

CREATE TABLE IF NOT EXISTS public.report
(
    id uuid NOT NULL,
    created_by character varying(255) COLLATE pg_catalog."default",
    created_date timestamp with time zone,
    deleted_by character varying(255) COLLATE pg_catalog."default",
    deleted_date timestamp with time zone,
    author character varying(255) COLLATE pg_catalog."default" NOT NULL,
    code character varying(255) COLLATE pg_catalog."default" NOT NULL,
    description text COLLATE pg_catalog."default",
    last_modified_by character varying(255) COLLATE pg_catalog."default",
    last_modified_date timestamp with time zone,
    file_id uuid NOT NULL,
    next_report_id uuid NOT NULL,
    prev_report_id uuid NOT NULL,
    revision character varying(255) COLLATE pg_catalog."default" NOT NULL,
    status_id uuid NOT NULL,
    CONSTRAINT pk_report PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.requirements
(
    id uuid NOT NULL,
    CONSTRAINT pk_requirements PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.sec_resource_policy
(
    id uuid NOT NULL,
    version integer NOT NULL DEFAULT 1,
    create_ts timestamp without time zone,
    created_by character varying(50) COLLATE pg_catalog."default",
    update_ts timestamp without time zone,
    updated_by character varying(50) COLLATE pg_catalog."default",
    delete_ts timestamp without time zone,
    deleted_by character varying(50) COLLATE pg_catalog."default",
    type_ character varying(255) COLLATE pg_catalog."default" NOT NULL,
    policy_group character varying(255) COLLATE pg_catalog."default",
    resource_ character varying(1000) COLLATE pg_catalog."default" NOT NULL,
    action_ character varying(255) COLLATE pg_catalog."default" NOT NULL,
    effect character varying(255) COLLATE pg_catalog."default" NOT NULL,
    role_id uuid NOT NULL,
    CONSTRAINT "SEC_RESOURCE_POLICY_pkey" PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.sec_resource_role
(
    id uuid NOT NULL,
    version integer NOT NULL DEFAULT 1,
    create_ts timestamp without time zone,
    created_by character varying(50) COLLATE pg_catalog."default",
    update_ts timestamp without time zone,
    updated_by character varying(50) COLLATE pg_catalog."default",
    delete_ts timestamp without time zone,
    deleted_by character varying(50) COLLATE pg_catalog."default",
    name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    code character varying(255) COLLATE pg_catalog."default" NOT NULL,
    child_roles text COLLATE pg_catalog."default",
    sys_tenant_id character varying(255) COLLATE pg_catalog."default",
    description text COLLATE pg_catalog."default",
    scopes character varying(1000) COLLATE pg_catalog."default",
    CONSTRAINT "SEC_RESOURCE_ROLE_pkey" PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.sec_role_assignment
(
    id uuid NOT NULL,
    version integer NOT NULL DEFAULT 1,
    create_ts timestamp without time zone,
    created_by character varying(50) COLLATE pg_catalog."default",
    update_ts timestamp without time zone,
    updated_by character varying(50) COLLATE pg_catalog."default",
    delete_ts timestamp without time zone,
    deleted_by character varying(50) COLLATE pg_catalog."default",
    username character varying(255) COLLATE pg_catalog."default" NOT NULL,
    role_code character varying(255) COLLATE pg_catalog."default" NOT NULL,
    role_type character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT "SEC_ROLE_ASSIGNMENT_pkey" PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.sec_row_level_policy
(
    id uuid NOT NULL,
    version integer NOT NULL DEFAULT 1,
    create_ts timestamp without time zone,
    created_by character varying(50) COLLATE pg_catalog."default",
    update_ts timestamp without time zone,
    updated_by character varying(50) COLLATE pg_catalog."default",
    delete_ts timestamp without time zone,
    deleted_by character varying(50) COLLATE pg_catalog."default",
    type_ character varying(255) COLLATE pg_catalog."default" NOT NULL,
    action_ character varying(255) COLLATE pg_catalog."default" NOT NULL,
    entity_name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    where_clause text COLLATE pg_catalog."default",
    join_clause text COLLATE pg_catalog."default",
    script_ text COLLATE pg_catalog."default",
    role_id uuid NOT NULL,
    CONSTRAINT "SEC_ROW_LEVEL_POLICY_pkey" PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.sec_row_level_role
(
    id uuid NOT NULL,
    version integer NOT NULL DEFAULT 1,
    create_ts timestamp without time zone,
    created_by character varying(50) COLLATE pg_catalog."default",
    update_ts timestamp without time zone,
    updated_by character varying(50) COLLATE pg_catalog."default",
    delete_ts timestamp without time zone,
    deleted_by character varying(50) COLLATE pg_catalog."default",
    name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    code character varying(255) COLLATE pg_catalog."default" NOT NULL,
    child_roles text COLLATE pg_catalog."default",
    sys_tenant_id character varying(255) COLLATE pg_catalog."default",
    description text COLLATE pg_catalog."default",
    CONSTRAINT "SEC_ROW_LEVEL_ROLE_pkey" PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.sec_user_substitution
(
    id uuid NOT NULL,
    version integer NOT NULL DEFAULT 1,
    create_ts timestamp without time zone,
    created_by character varying(50) COLLATE pg_catalog."default",
    update_ts timestamp without time zone,
    updated_by character varying(50) COLLATE pg_catalog."default",
    delete_ts timestamp without time zone,
    deleted_by character varying(50) COLLATE pg_catalog."default",
    username character varying(255) COLLATE pg_catalog."default" NOT NULL,
    substituted_username character varying(255) COLLATE pg_catalog."default" NOT NULL,
    start_date timestamp without time zone,
    end_date timestamp without time zone,
    CONSTRAINT "SEC_USER_SUBSTITUTION_pkey" PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.stage
(
    id uuid NOT NULL,
    name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    created_by character varying(255) COLLATE pg_catalog."default",
    created_date timestamp with time zone,
    deleted_by character varying(255) COLLATE pg_catalog."default",
    deleted_date timestamp with time zone,
    CONSTRAINT pk_stage PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.status
(
    id uuid NOT NULL,
    deleted_by character varying(255) COLLATE pg_catalog."default",
    deleted_date timestamp with time zone,
    CONSTRAINT pk_status PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.system_
(
    id uuid NOT NULL,
    kks_code character varying(255) COLLATE pg_catalog."default" NOT NULL,
    pbs_code_id uuid NOT NULL,
    description text COLLATE pg_catalog."default",
    created_by character varying(255) COLLATE pg_catalog."default",
    created_date timestamp with time zone,
    deleted_by character varying(255) COLLATE pg_catalog."default",
    deleted_date timestamp with time zone,
    CONSTRAINT pk_system_ PRIMARY KEY (id)
);

COMMENT ON TABLE public.system_
    IS 'Description of the structure block - system.';

COMMENT ON COLUMN public.system_.kks_code
    IS 'KKS code of this structural unit.';

COMMENT ON COLUMN public.system_.pbs_code_id
    IS 'PBS code of this structural unit.';

COMMENT ON COLUMN public.system_.description
    IS 'Description of this structural unit.';

CREATE TABLE IF NOT EXISTS public.tag
(
    id uuid NOT NULL,
    kks_code character varying(255) COLLATE pg_catalog."default" NOT NULL,
    description text COLLATE pg_catalog."default",
    building_id uuid,
    system_id uuid NOT NULL,
    pbs_code_id uuid NOT NULL,
    revision character varying(255) COLLATE pg_catalog."default" NOT NULL,
    created_by character varying(255) COLLATE pg_catalog."default",
    created_date timestamp with time zone,
    deleted_by character varying(255) COLLATE pg_catalog."default",
    deleted_date timestamp with time zone,
    CONSTRAINT pk_tag PRIMARY KEY (id)
);

COMMENT ON COLUMN public.tag.kks_code
    IS 'Project position kks code.';

COMMENT ON COLUMN public.tag.description
    IS 'Description of the project position.';

CREATE TABLE IF NOT EXISTS public.tag_attribute
(
    id uuid NOT NULL,
    attribute character varying(255) COLLATE pg_catalog."default" NOT NULL,
    deleted_by character varying(255) COLLATE pg_catalog."default",
    deleted_date timestamp with time zone,
    created_by character varying(255) COLLATE pg_catalog."default",
    created_date timestamp with time zone,
    tag_id uuid NOT NULL,
    CONSTRAINT pk_tag_attribute PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.tag_attribute_value
(
    id uuid NOT NULL,
    tag_id uuid NOT NULL,
    attribute_id uuid NOT NULL,
    created_by character varying(255) COLLATE pg_catalog."default",
    created_date timestamp with time zone,
    deleted_by character varying(255) COLLATE pg_catalog."default",
    deleted_date timestamp with time zone,
    CONSTRAINT pk_tag_attribute_value PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.task
(
    id uuid NOT NULL,
    code character varying(255) COLLATE pg_catalog."default" NOT NULL,
    annotation text COLLATE pg_catalog."default",
    revision character varying(255) COLLATE pg_catalog."default" NOT NULL,
    stage_id uuid NOT NULL,
    initial_event_id uuid NOT NULL,
    created_by character varying(255) COLLATE pg_catalog."default",
    created_date timestamp with time zone,
    deleted_by character varying(255) COLLATE pg_catalog."default",
    deleted_date timestamp with time zone,
    CONSTRAINT pk_task PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.task_approximation_link
(
    approximation_id uuid NOT NULL,
    task_id uuid NOT NULL,
    CONSTRAINT pk_task_approximation_link PRIMARY KEY (approximation_id, task_id)
);

CREATE TABLE IF NOT EXISTS public.task_document_link
(
    document_id uuid NOT NULL,
    task_id uuid NOT NULL,
    CONSTRAINT pk_task_document_link PRIMARY KEY (document_id, task_id)
);

CREATE TABLE IF NOT EXISTS public.task_report_link
(
    report_id uuid NOT NULL,
    task_id uuid NOT NULL,
    CONSTRAINT pk_task_report_link PRIMARY KEY (report_id, task_id)
);

CREATE TABLE IF NOT EXISTS public.user_
(
    id uuid NOT NULL,
    version integer NOT NULL,
    username character varying(255) COLLATE pg_catalog."default" NOT NULL,
    first_name character varying(255) COLLATE pg_catalog."default",
    last_name character varying(255) COLLATE pg_catalog."default",
    password character varying(255) COLLATE pg_catalog."default",
    email character varying(255) COLLATE pg_catalog."default",
    active boolean,
    time_zone_id character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT "USER__pkey" PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS public.abd_impact_analysis
    ADD CONSTRAINT fk_abd_impact_analysis_on_approximation FOREIGN KEY (approximation_id)
    REFERENCES public.approximation (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;
CREATE INDEX IF NOT EXISTS idx_abd_impact_analysis_approximation
    ON public.abd_impact_analysis(approximation_id);


ALTER TABLE IF EXISTS public.abd_impact_analysis
    ADD CONSTRAINT fk_abd_impact_analysis_on_change_configuration_baseline FOREIGN KEY (change_configuration_baseline_id)
    REFERENCES public.stage (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;
CREATE INDEX IF NOT EXISTS idx_abd_impact_analysis_change_configuration_baseline
    ON public.abd_impact_analysis(change_configuration_baseline_id);


ALTER TABLE IF EXISTS public.abd_impact_analysis
    ADD CONSTRAINT fk_abd_impact_analysis_on_criticality FOREIGN KEY (criticality_id)
    REFERENCES public.criticality (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;
CREATE INDEX IF NOT EXISTS idx_abd_impact_analysis_criticality
    ON public.abd_impact_analysis(criticality_id);


ALTER TABLE IF EXISTS public.abd_impact_analysis
    ADD CONSTRAINT fk_abd_impact_analysis_on_impact_analysis FOREIGN KEY (impact_analysis_id)
    REFERENCES public.impact_analysis (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;
CREATE INDEX IF NOT EXISTS idx_abd_impact_analysis_impact_analysis
    ON public.abd_impact_analysis(impact_analysis_id);


ALTER TABLE IF EXISTS public.abd_impact_analysis
    ADD CONSTRAINT fk_abd_impact_analysis_on_implementation_configuration_baseline FOREIGN KEY (implementation_configuration_baseline_id)
    REFERENCES public.stage (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;
CREATE INDEX IF NOT EXISTS idx_abd_impact_analysis_implementation_configuration_baseline
    ON public.abd_impact_analysis(implementation_configuration_baseline_id);


ALTER TABLE IF EXISTS public.abd_impact_analysis
    ADD CONSTRAINT fk_abd_impact_analysis_on_implementation_object FOREIGN KEY (implementation_object_id)
    REFERENCES public.approximation (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;
CREATE INDEX IF NOT EXISTS idx_abd_impact_analysis_implementation_object
    ON public.abd_impact_analysis(implementation_object_id);


ALTER TABLE IF EXISTS public.abd_impact_analysis
    ADD CONSTRAINT fk_abd_impact_analysis_on_status FOREIGN KEY (status_id)
    REFERENCES public.status (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;
CREATE INDEX IF NOT EXISTS idx_abd_impact_analysis_status
    ON public.abd_impact_analysis(status_id);


ALTER TABLE IF EXISTS public.approximation
    ADD CONSTRAINT fk_approximation_on_limitation FOREIGN KEY (limitation_id)
    REFERENCES public.limitation (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;
CREATE INDEX IF NOT EXISTS idx_approximation_limitation
    ON public.approximation(limitation_id);


ALTER TABLE IF EXISTS public.approximation
    ADD CONSTRAINT fk_approximation_on_tag FOREIGN KEY (tag_id)
    REFERENCES public.tag (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;
CREATE INDEX IF NOT EXISTS idx_approximation_tag
    ON public.approximation(tag_id);


ALTER TABLE IF EXISTS public.approximation
    ADD CONSTRAINT fk_approximation_on_tag_attribute FOREIGN KEY (tag_attribute_id)
    REFERENCES public.tag_attribute (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;
CREATE INDEX IF NOT EXISTS idx_approximation_tag_attribute
    ON public.approximation(tag_attribute_id);


ALTER TABLE IF EXISTS public.document
    ADD CONSTRAINT fk_document_on_document_type FOREIGN KEY (document_type_id)
    REFERENCES public.document (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;
CREATE INDEX IF NOT EXISTS idx_document_document_type
    ON public.document(document_type_id);


ALTER TABLE IF EXISTS public.document_initial_event_link
    ADD CONSTRAINT fk_docinieve_on_document FOREIGN KEY (document_id)
    REFERENCES public.document (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;


ALTER TABLE IF EXISTS public.document_initial_event_link
    ADD CONSTRAINT fk_docinieve_on_initial_event FOREIGN KEY (initial_event_id)
    REFERENCES public.initial_event (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;


ALTER TABLE IF EXISTS public.impact_analysis
    ADD CONSTRAINT fk_impact_analysis_on_change_configuration_baseline FOREIGN KEY (change_configuration_baseline_id)
    REFERENCES public.stage (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;
CREATE INDEX IF NOT EXISTS idx_impact_analysis_change_configuration_baseline
    ON public.impact_analysis(change_configuration_baseline_id);


ALTER TABLE IF EXISTS public.impact_analysis
    ADD CONSTRAINT fk_impact_analysis_on_change_request FOREIGN KEY (change_request_id)
    REFERENCES public.change_request (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;
CREATE INDEX IF NOT EXISTS idx_impact_analysis_change_request
    ON public.impact_analysis(change_request_id);


ALTER TABLE IF EXISTS public.impact_analysis
    ADD CONSTRAINT fk_impact_analysis_on_document FOREIGN KEY (document_id)
    REFERENCES public.document (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;
CREATE INDEX IF NOT EXISTS idx_impact_analysis_document
    ON public.impact_analysis(document_id);


ALTER TABLE IF EXISTS public.impact_analysis
    ADD CONSTRAINT fk_impact_analysis_on_implementation_configuration_baseline FOREIGN KEY (implementation_configuration_baseline_id)
    REFERENCES public.stage (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;
CREATE INDEX IF NOT EXISTS idx_impact_analysis_implementation_configuration_baseline
    ON public.impact_analysis(implementation_configuration_baseline_id);


ALTER TABLE IF EXISTS public.impact_analysis
    ADD CONSTRAINT fk_impact_analysis_on_implementation_object FOREIGN KEY (implementation_object_id)
    REFERENCES public.approximation (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;
CREATE INDEX IF NOT EXISTS idx_impact_analysis_implementation_object
    ON public.impact_analysis(implementation_object_id);


ALTER TABLE IF EXISTS public.impact_analysis
    ADD CONSTRAINT fk_impact_analysis_on_status FOREIGN KEY (status_id)
    REFERENCES public.status (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;
CREATE INDEX IF NOT EXISTS idx_impact_analysis_status
    ON public.impact_analysis(status_id);


ALTER TABLE IF EXISTS public.initial_event
    ADD CONSTRAINT fk_initial_event_on_initialevent_group FOREIGN KEY (initialevent_group_id)
    REFERENCES public.initial_event_group (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;
CREATE INDEX IF NOT EXISTS idx_initial_event_initialevent_group
    ON public.initial_event(initialevent_group_id);


ALTER TABLE IF EXISTS public.report
    ADD CONSTRAINT fk_report_on_file FOREIGN KEY (file_id)
    REFERENCES public.file (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;
CREATE INDEX IF NOT EXISTS idx_report_file
    ON public.report(file_id);


ALTER TABLE IF EXISTS public.report
    ADD CONSTRAINT fk_report_on_next_report FOREIGN KEY (next_report_id)
    REFERENCES public.report (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;
CREATE INDEX IF NOT EXISTS idx_report_next_report
    ON public.report(next_report_id);


ALTER TABLE IF EXISTS public.report
    ADD CONSTRAINT fk_report_on_prev_report FOREIGN KEY (prev_report_id)
    REFERENCES public.report (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;
CREATE INDEX IF NOT EXISTS idx_report_prev_report
    ON public.report(prev_report_id);


ALTER TABLE IF EXISTS public.report
    ADD CONSTRAINT fk_report_on_status FOREIGN KEY (status_id)
    REFERENCES public.status (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;
CREATE INDEX IF NOT EXISTS idx_report_status
    ON public.report(status_id);


ALTER TABLE IF EXISTS public.sec_resource_policy
    ADD CONSTRAINT fk_res_policy_role FOREIGN KEY (role_id)
    REFERENCES public.sec_resource_role (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;


ALTER TABLE IF EXISTS public.sec_row_level_policy
    ADD CONSTRAINT fk_row_level_policy_role FOREIGN KEY (role_id)
    REFERENCES public.sec_row_level_role (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;


ALTER TABLE IF EXISTS public.tag
    ADD CONSTRAINT fk_tag_on_building FOREIGN KEY (building_id)
    REFERENCES public.building (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;
CREATE INDEX IF NOT EXISTS idx_tag_building
    ON public.tag(building_id);


ALTER TABLE IF EXISTS public.tag
    ADD CONSTRAINT fk_tag_on_system FOREIGN KEY (system_id)
    REFERENCES public.system_ (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;
CREATE INDEX IF NOT EXISTS idx_tag_system
    ON public.tag(system_id);


ALTER TABLE IF EXISTS public.tag_attribute
    ADD CONSTRAINT fk_tag_attribute_on_tag FOREIGN KEY (tag_id)
    REFERENCES public.tag (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;
CREATE INDEX IF NOT EXISTS idx_tag_attribute_tag
    ON public.tag_attribute(tag_id);


ALTER TABLE IF EXISTS public.tag_attribute_value
    ADD CONSTRAINT fk_tag_attribute_value_on_attribute FOREIGN KEY (attribute_id)
    REFERENCES public.tag_attribute (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;
CREATE INDEX IF NOT EXISTS idx_tag_attribute_value_attribute
    ON public.tag_attribute_value(attribute_id);


ALTER TABLE IF EXISTS public.tag_attribute_value
    ADD CONSTRAINT fk_tag_attribute_value_on_tag FOREIGN KEY (tag_id)
    REFERENCES public.tag (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;
CREATE INDEX IF NOT EXISTS idx_tag_attribute_value_tag
    ON public.tag_attribute_value(tag_id);


ALTER TABLE IF EXISTS public.task
    ADD CONSTRAINT fk_task_on_initial_event FOREIGN KEY (initial_event_id)
    REFERENCES public.initial_event (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;
CREATE INDEX IF NOT EXISTS idx_task_initial_event
    ON public.task(initial_event_id);


ALTER TABLE IF EXISTS public.task
    ADD CONSTRAINT fk_task_on_stage FOREIGN KEY (stage_id)
    REFERENCES public.stage (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;
CREATE INDEX IF NOT EXISTS idx_task_stage
    ON public.task(stage_id);


ALTER TABLE IF EXISTS public.task_approximation_link
    ADD CONSTRAINT fk_tasapp_on_approximation FOREIGN KEY (approximation_id)
    REFERENCES public.approximation (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;


ALTER TABLE IF EXISTS public.task_approximation_link
    ADD CONSTRAINT fk_tasapp_on_task FOREIGN KEY (task_id)
    REFERENCES public.task (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;


ALTER TABLE IF EXISTS public.task_document_link
    ADD CONSTRAINT fk_tasdoc_on_document FOREIGN KEY (document_id)
    REFERENCES public.document (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;


ALTER TABLE IF EXISTS public.task_document_link
    ADD CONSTRAINT fk_tasdoc_on_task FOREIGN KEY (task_id)
    REFERENCES public.task (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;


ALTER TABLE IF EXISTS public.task_report_link
    ADD CONSTRAINT fk_tasrep_on_report FOREIGN KEY (report_id)
    REFERENCES public.report (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;


ALTER TABLE IF EXISTS public.task_report_link
    ADD CONSTRAINT fk_tasrep_on_task FOREIGN KEY (task_id)
    REFERENCES public.task (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;

END;