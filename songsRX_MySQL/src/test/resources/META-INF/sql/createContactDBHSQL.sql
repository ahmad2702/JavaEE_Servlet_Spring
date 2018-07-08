--User table
CREATE TABLE IF NOT EXISTS `User` (
  `id` int(5) NOT NULL,
  `userId` varchar(10) NOT NULL,
  `lastName` varchar(5000) NOT NULL,
  `firstName` varchar(5000) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

ALTER TABLE `User`
  ADD PRIMARY KEY (`userId`),
  ADD UNIQUE KEY `id` (`id`);

  ALTER TABLE `User`
  MODIFY `id` int(5) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;

  --Song table
  CREATE TABLE IF NOT EXISTS `Song` (
  `id` int(10) NOT NULL,
  `title` varchar(4000) NOT NULL,
  `artist` varchar(4000) NOT NULL,
  `album` varchar(4000) DEFAULT NULL,
  `released` int(255) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

ALTER TABLE `Song`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id` (`id`);

ALTER TABLE `Song`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=12;

 --SongsLists table
 CREATE TABLE IF NOT EXISTS `SongLists` (
  `id` int(10) NOT NULL,
  `securityType` int(10) NOT NULL COMMENT '1 is public, 0 is private',
  `user_id` varchar(100) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

ALTER TABLE `SongLists`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`);

  ALTER TABLE `SongLists`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=11;

--SongLIstsMapping table
CREATE TABLE IF NOT EXISTS `SongListsMapping` (
  `Songlists_id` int(10) NOT NULL,
  `Song_id` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE `SongListsMapping`
  ADD KEY `songlists_ID` (`Songlists_id`),
  ADD KEY `song_id` (`Song_id`);

ALTER TABLE `SongListsMapping`
  ADD CONSTRAINT `songs_fk231` FOREIGN KEY (`Song_id`) REFERENCES `Song` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `songs_lists_fk231` FOREIGN KEY (`Songlists_id`) REFERENCES `SongLists` (`id`) ON DELETE CASCADE;
