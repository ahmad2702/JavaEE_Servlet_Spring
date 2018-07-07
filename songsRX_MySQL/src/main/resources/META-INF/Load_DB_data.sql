insert into Song values(1,"Can’t Stop the Feeling","Justin Timberlake","Trolls",2016);
insert into Song values(2,"Mom","Meghan Trainor, Kelli Trainor","Thank You",2016);
insert into Song values(3,"Team","Iggy Azalea","Iggy Azalea",2016);
insert into Song values(4,"Ghostbusters (I’m not a fraid)","Fall Out Boy, Missy Elliott","Ghostbusters",2016);
insert into Song values(5,"Bad Things","Camila Cabello, Machine Gun Kelly","Bloom",2017);
insert into Song values(6,"I Took a Pill in Ibiza","Mike Posner","At Night, Alone.",2016);
insert into Song values(7,"i hate u, i love u","Gnash","Top Hits 2017",2017);
insert into Song values(8,"No","Meghan Trainor","Thank You",2016);
insert into Song values(9,"Private Show","Britney Spears","Glory",2016);
insert into Song values(10,"7 Years","Lukas Graham","Lukas Graham (Blue Album)",2015);

insert into User(userId,firstName,lastName) values("mmuster","Muster","Maxime");
insert into User(userId,firstName,lastName) values("eschuler","Elena","Schuler");

insert into SongLists(securityType,user_id) values(0,1);
insert into SongLists(securityType,user_id) values(1,1);
insert into SongLists(securityType,user_id) values(0,2);
insert into SongLists(securityType,user_id) values(1,2);

insert into SongListsMapping values(1,9);
insert into SongListsMapping values(1,10);
insert into SongListsMapping values(2,1);
insert into SongListsMapping values(2,2);
insert into SongListsMapping values(3,4);
insert into SongListsMapping values(3,6);
insert into SongListsMapping values(4,7);
insert into SongListsMapping values(4,3);
