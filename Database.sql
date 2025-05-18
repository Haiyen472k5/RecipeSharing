drop database if exists cnpm;
create database cnpm;
use cnpm;

create table id_seq(
    id varchar(10) unique not null
);

INSERT INTO id_seq (id)
VALUES ('ID00000000');

create table users (
    user_id varchar(10)unique primary key,
    username varchar(25) not null unique,
    firstname varchar(25),
    lastname varchar(25),
    email varchar(25) not null,
    password varchar(255) not null,
    role varchar(5) not null,
    avatar_url varchar(255),
    date_of_birth timestamp
);

CREATE TABLE categories (
  category_id varchar(10) unique PRIMARY KEY,
  category_name VARCHAR(100) NOT NULL UNIQUE,
  usage_count BIGINT NOT NULL DEFAULT 0
);

create table recipes (
    recipe_id varchar(10) unique primary key,
    name varchar(100),
    instruction varchar(1000) not null,
    description text,
    category_name varchar(100),
    category_id varchar(10),
    ingredients text not null,
    author_name varchar(100),
    author_id varchar(10),
    created_at timestamp,
    comment_count int default 0,
    like_count int default 0,
    rate_count int default 0,
    average_rating float,
    save_count int default 0,
    avatar_url varchar(255),
    constraint fk_author foreign key (author_id) references users(user_id) on delete cascade on update cascade,
    constraint fk_recipe_category foreign key (category_id) references categories(category_id) on delete cascade on update cascade
);

create table likes (
    like_id varchar(10) unique primary key,
    recipe_id varchar(10) not null,
    user_id varchar(10),
    created_at timestamp default current_timestamp,
    constraint fk_recipe foreign key (recipe_id) references recipes(recipe_id) on delete cascade on update cascade,
    constraint fk_user foreign key (user_id) references users(user_id) on delete set null on update cascade
);

create table comments (
    comment_id varchar(10) unique primary key,
    recipe_id varchar(10) not null,
    user_id varchar(10),
    comment_content text not null,
    created_at timestamp default current_timestamp,
    constraint fk_recipe_comment foreign key (recipe_id) references recipes(recipe_id) on delete cascade on update cascade,
    constraint fk_user_comment foreign key (user_id) references users(user_id) on delete set null on update cascade
);

create table rates (
    rate_id varchar(10) unique primary key,
    recipe_id varchar(10) not null,
    user_id varchar(10),
    rating int check (rating >= 1 and rating <= 5),
    created_at timestamp default current_timestamp,
    constraint fk_rate_recipe foreign key (recipe_id) references recipes(recipe_id) on delete cascade on update cascade,
    constraint fk_rate_user foreign key (user_id) references users(user_id) on delete set null on update cascade
);

create table saved (
    save_id varchar(10) unique primary key,
    recipe_id varchar(10) not null,
    user_id varchar(10),
    created_at timestamp default current_timestamp,
    constraint fk_save_recipe foreign key (recipe_id) references recipes(recipe_id) on delete cascade on update cascade,
    constraint fk_save_user foreign key (user_id) references users(user_id) on delete set null on update cascade
);

create table media (
    media_id varchar(10) unique primary key,
    recipe_id varchar(10) not null,
    file_url varchar(255) not null,
    media_type varchar(20) check (media_type in ('image', 'video')),
    upload_time timestamp default current_timestamp,
    constraint fk_media_recipe foreign key (recipe_id) references recipes(recipe_id) on delete cascade on update cascade
);

create table follows (
    follow_id varchar(10) unique primary key,
    follower_id varchar(10) not null,
    following_id varchar(10) not null,
    follow_time timestamp default current_timestamp,
    constraint fk_follower foreign key (follower_id) references users(user_id) on delete cascade on update cascade,
    constraint fk_following foreign key (following_id) references users(user_id) on delete cascade on update cascade,
    constraint unique_follow unique (follower_id, following_id)
);
