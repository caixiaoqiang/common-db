

CREATE TABLE `test` (
  `id` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



CREATE TABLE `sample_a` (
  `title` varchar(32) DEFAULT NULL,
  UNIQUE KEY `title` (`title`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE `sample_b` (
  `title` varchar(32) DEFAULT NULL,
  UNIQUE KEY `title` (`title`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;