INSERT INTO `User` (`id`, `userId`, `lastName`, `firstName`) VALUES
(2, 'eschuler', 'Schuler', 'Elena'),
(1, 'mmuster', 'Muster', 'Maxime');

INSERT INTO `Song` (`id`, `title`, `artist`, `album`, `released`) VALUES
(1, 'Can’t Stop the Feeling', 'Justin Timberlake', 'Trolls', 2016),
(2, 'Mom', 'Meghan Trainor, Kelli Trainor', 'Thank You', 2016),
(3, 'Team', 'Iggy Azalea', 'Iggy Azalea', 2016),
(4, 'Ghostbusters (I''m not a fraid)', 'Fall Out Boy, Missy Elliott', 'Ghostbusters', 2016),
(5, 'Bad Things', 'Camila Cabello, Machine Gun Kelly', 'Bloom', 2017),
(6, 'I Took a Pill in Ibiza', 'Mike Posner', 'At Night, Alone.', 2016),
(7, 'i hate u, i love u', 'Gnash', 'Top Hits 2017', 2017),
(8, 'No', 'Meghan Trainor', 'Thank You', 2016),
(9, 'Private Show', 'Britney Spears', 'Glory', 2016),
(10, '7 Years', 'Lukas Graham', 'Lukas Graham (Blue Album)', 2015),
(11, 'Meine Klasur', 'Student', 'HTW-Best-Album', 2018);

INSERT INTO `SongLists` (`id`, `securityType`, `user_id`) VALUES
(6, 1, 'eschuler'),
(7, 0, 'eschuler'),
(8, 1, 'mmuster'),
(9, 0, 'mmuster'),
(10, 1, 'eschuler');

INSERT INTO `SongListsMapping` (`Songlists_id`, `Song_id`) VALUES
(6, 1),
(7, 1),
(8, 3),
(9, 7),
(8, 4),
(6, 2),
(7, 5),
(9, 4),
(9, 4),
(9, 10),
(8, 7),
(10, 3),
(9, 8),
(10, 10);
