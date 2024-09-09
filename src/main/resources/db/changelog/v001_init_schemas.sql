-- public.coffeeshop definition

-- Drop table

-- DROP TABLE public.coffeeshop;

CREATE TABLE public.coffeeshop (
	id uuid NOT NULL,
	shop_name varchar(150) NOT NULL,
	latitude double precision NOT NULL,
	longitude double precision NOT NULL,
	address varchar(255) NOT NULL,
	created_at timestamp(6) NOT NULL,
	modified_at timestamp(6) NULL,
	active bool NOT NULL,
	CONSTRAINT coffeeshop_name_key UNIQUE (shop_name),
	CONSTRAINT coffeeshop_pkey PRIMARY KEY (id)
);


-- public.customer definition

-- Drop table

-- DROP TABLE public.customer;

CREATE TABLE public.customer (
	id uuid NOT NULL,
	customer_name varchar(150) NOT NULL,
	country_code varchar(3) NOT NULL,
	mobile_number varchar(10) NOT NULL,
	address varchar(255) NULL,
	otp_pending bool NULL default false,
	device_id varchar(36) NULL,
	created_at timestamp(6) NOT NULL,
	modified_at timestamp(6) NULL,
	active bool NOT NULL,
	CONSTRAINT customer_pkey PRIMARY KEY (id)
);

CREATE INDEX customer_mobile_number_idx ON public.customer (country_code,mobile_number);

-- public.customer_order definition

-- Drop table

-- DROP TABLE public.customer_order;

CREATE TABLE public.customer_order (
	id uuid NOT NULL,
	customer_id uuid NOT NULL,
	queue_id uuid NOT NULL,
	queue_position int4 NOT NULL,
	sub_total numeric(38, 2) NOT NULL,
	total_price numeric(38, 2) NOT NULL,
	order_status varchar(255) NOT NULL,
	voucher_code varchar(255) NULL,
	notes varchar(255) NULL,
	created_at timestamp(6) NOT NULL,
	modified_at timestamp(6) NULL,
	active bool NOT NULL,
	CONSTRAINT customer_order_order_status_check CHECK (((order_status)::text = ANY ((ARRAY['PENDING'::character varying, 'IN_QUEUE'::character varying, 'CANCELED'::character varying, 'READY'::character varying, 'COMPLETED'::character varying])::text[]))),
	CONSTRAINT customer_order_pkey PRIMARY KEY (id)
);


-- public.customer_setting definition

-- Drop table

-- DROP TABLE public.customer_setting;

CREATE TABLE public.customer_setting (
	id uuid NOT NULL,
	customer_id uuid NOT NULL,
	preferred_language varchar(3) NOT NULL,
	currency varchar(3) NOT NULL,
	sms_enabled bool NULL,
	created_at timestamp(6) NOT NULL,
	modified_at timestamp(6) NULL,
	active bool NOT NULL,
	CONSTRAINT customer_setting_customer_id_key UNIQUE (customer_id),
	CONSTRAINT customer_setting_pkey PRIMARY KEY (id)
);


-- public.menu definition

-- Drop table

-- DROP TABLE public.menu;

CREATE TABLE public.menu (
	id uuid NOT NULL,
	shop_id uuid NOT NULL,
	menu_name varchar(150) NOT NULL,
	created_at timestamp(6) NOT NULL,
	modified_at timestamp(6) NULL,
	active bool NOT NULL,
	CONSTRAINT menu_pkey PRIMARY KEY (id)
);


-- public.menu_item definition

-- Drop table

-- DROP TABLE public.menu_item;

CREATE TABLE public.menu_item (
	id uuid NOT NULL,
	menu_id uuid NOT NULL,
	item_name varchar(255) NOT NULL,
	description varchar(255) NOT NULL,
	price numeric(38, 2) NOT NULL,
	created_at timestamp(6) NOT NULL,
	modified_at timestamp(6) NULL,
	active bool NOT NULL,
	CONSTRAINT menu_item_pkey PRIMARY KEY (id)
);


-- public.order_item definition

-- Drop table

-- DROP TABLE public.order_item;

CREATE TABLE public.order_item (
	id uuid NOT NULL,
	order_id uuid NOT NULL,
	menu_item_id uuid NOT NULL,
	price_snapshot numeric(38, 2) NOT NULL,
	quantity int4 NOT NULL,
	created_at timestamp(6) NOT NULL,
	modified_at timestamp(6) NULL,
	active bool NOT NULL,
	CONSTRAINT order_item_pkey PRIMARY KEY (id)
);


-- public.queue definition

-- Drop table

-- DROP TABLE public.queue;

CREATE TABLE public.queue (
	id uuid NOT NULL,
	shop_id uuid NOT NULL,
	queue_name varchar(150) NOT NULL,
	capacity int4 NOT NULL,
	created_at timestamp(6) NOT NULL,
	modified_at timestamp(6) NULL,
	active bool NOT NULL,
	CONSTRAINT queue_pkey PRIMARY KEY (id)
);

-- public.queue_condition definition

-- Drop table

-- DROP TABLE public.queue_condition;

CREATE TABLE public.queue_condition (
	id uuid NOT NULL,
	order_id uuid NOT NULL,
	queue_id uuid NOT NULL,
	created_at timestamp(6) NOT NULL,
	modified_at timestamp(6) NULL,
	active bool NOT NULL,
	CONSTRAINT queue_condition_pkey PRIMARY KEY (id)
);