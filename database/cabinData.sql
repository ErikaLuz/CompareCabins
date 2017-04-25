insert into cabin ( description, title ) values
	( 'this cabin is so pretty 1. Describe describe describe', 'cabin1' ),
    ( 'describe text2. Describe describe describe', 'cabin2' ),
	( 'describe text3. Describe describe describe', 'cabin3' ),
	( 'describe text4. Describe describe describe', 'cabin4' ),   
    ( 'describe text5. Describe describe describe', 'cabin5' ),
	( 'describe text6. Describe describe describe', 'cabin6' ),
	( 'describe text7. Describe describe describe', 'cabin7' ),
	( 'describe text8. Describe describe describe', 'cabin8' ),
	( 'describe text9. Describe describe describe', 'cabin9' ),
	( 'describe text10. Describe describe describe', 'cabin10' ),
	( 'describe text11. Describe describe describe', 'cabin11' );
    
insert into cabin_picture ( file_path, priority, cabin_id ) values
	( 'cabinPictures/BanffLogCabinNightShot.jpeg', 1, 1 ),
    ( 'cabinPictures/lodgeCabin.jpeg', 1, 2 ),
    ( 'cabinPictures/cabin1.jpeg', 1, 3 ),
    ( 'cabinPictures/cabin2.jpeg', 1, 4 ),
    ( 'cabinPictures/cabin3.jpeg', 1, 5 ),
    ( 'cabinPictures/cabin4.jpeg', 1, 6 ),
    ( 'cabinPictures/cabin5.jpeg', 1, 7 ),
    ( 'cabinPictures/cabin6.jpeg', 1, 8 ),
    ( 'cabinPictures/cabin8.jpeg', 1, 9 ),
    ( 'cabinPictures/cabin9.jpeg', 1, 10 ),
    ( 'cabinPictures/cabin11.jpeg', 1, 11 );
    
delete from cabin_picture
	where id < 100;
    
select * from cabin;

select * from cabin_picture;
