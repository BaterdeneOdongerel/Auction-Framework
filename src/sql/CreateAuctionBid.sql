USE auction_system;


CREATE TABLE IF NOT EXISTS `bid` (
  `id`      BIGINT NOT NULL AUTO_INCREMENT,
  `amount`  DOUBLE,
  `bidDate` DATE,
  `auction` BIGINT,
  `user`    BIGINT,
  PRIMARY KEY (`id`)
)
  ENGINE MyISAM
  DEFAULT CHARACTER SET utf8
  COLLATE utf8_general_ci;


CREATE TABLE IF NOT EXISTS `auction` (

  `id`                BIGINT NOT NULL AUTO_INCREMENT,
  `minimumPrice`      DOUBLE,
  `startDate`         DATE,
  `endDate`           DATE,
  `isrunning`         INT             DEFAULT 1,
  `bidOwner`          BIGINT,
  `product`           BIGINT,
  `currentWinner`     BIGINT REFERENCES User (id),
  `currentWinningBid` BIGINT REFERENCES bid (id),
  `winner`            BIGINT REFERENCES User (id),

  PRIMARY KEY (`id`)
)
  ENGINE MyISAM
  DEFAULT CHARACTER SET utf8
  COLLATE utf8_general_ci;


CREATE TABLE IF NOT EXISTS `bidAuctionNotUsed` (

  `bidId`             BIGINT REFERENCES bid (id),
  `auctionId`         BIGINT REFERENCES auction (id),
  `currentWinner`     BIGINT REFERENCES User (id),
  `currentWinningBid` BIGINT REFERENCES bid (id),
  `winner`            BIGINT REFERENCES User (id),

  PRIMARY KEY (`bidId`, `auctionId`)
)
  ENGINE MyISAM
  DEFAULT CHARACTER SET utf8
  COLLATE utf8_general_ci;


