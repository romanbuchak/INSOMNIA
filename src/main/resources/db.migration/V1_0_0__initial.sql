create table tracker_info
(
    id        int PRIMARY KEY AUTO_INCREMENT NOT NULL,
    longitude DOUBLE PRECISION,
    latitude  DOUBLE PRECISION,
    date_time TIMESTAMP NOT NULL
)