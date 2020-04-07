CREATE DATABASE parking_lot DEFAULT CHARSET utf8 COLLATE utf8_general_ci;
USE parking_lot;

CREATE TABLE lot
(                                                      # 停车位
    id                 int PRIMARY KEY AUTO_INCREMENT, # 停车位ID, 主键索引
    parking_lot_tag    CHAR(1) NOT NULL,               # 停车场编号
    parking_log_number int     NOT NULL,               # 停车位编号
    car_number         VARCHAR(10) DEFAULT NULL        # 车牌号
);
