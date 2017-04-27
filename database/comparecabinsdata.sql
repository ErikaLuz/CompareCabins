use compare_cabins;
delete from review where id < 1000;
delete from rent_record where id < 1000;
delete from availability where id < 1000;
delete from feature where id < 1000;
delete from cabin_picture where id < 1000;
delete from cabin where id < 1000; 
delete from amenities where id < 1000;
delete from user where id < 1000;

ALTER TABLE review AUTO_INCREMENT = 1;
ALTER TABLE rent_record AUTO_INCREMENT = 1;
ALTER TABLE availability AUTO_INCREMENT = 1;
ALTER TABLE feature AUTO_INCREMENT = 1;
ALTER TABLE cabin_picture AUTO_INCREMENT = 1;
ALTER TABLE cabin AUTO_INCREMENT = 1;
ALTER TABLE amenities AUTO_INCREMENT = 1;
ALTER TABLE user AUTO_INCREMENT = 1;

insert into amenities (has_lake, has_river, has_pool, has_hot_tub, has_wifi, has_air_conditioning, has_washer_dryer, allows_pets, allows_smoking) Values(0, 0, 1, 1, 0, 1, 1, 1, 0);
insert into amenities (has_lake, has_river, has_pool, has_hot_tub, has_wifi, has_air_conditioning, has_washer_dryer, allows_pets, allows_smoking) Values(1, 0, 0, 1, 1, 1, 0, 1, 0);
insert into amenities (has_lake, has_river, has_pool, has_hot_tub, has_wifi, has_air_conditioning, has_washer_dryer, allows_pets, allows_smoking) Values(0, 1, 1, 0, 1, 1, 1, 1, 1);
insert into amenities (has_lake, has_river, has_pool, has_hot_tub, has_wifi, has_air_conditioning, has_washer_dryer, allows_pets, allows_smoking) Values(0, 1, 1, 0, 1, 0, 1, 1, 0);
insert into amenities (has_lake, has_river, has_pool, has_hot_tub, has_wifi, has_air_conditioning, has_washer_dryer, allows_pets, allows_smoking) Values(0, 0, 1, 0, 1, 1, 0, 1, 0);
insert into amenities (has_lake, has_river, has_pool, has_hot_tub, has_wifi, has_air_conditioning, has_washer_dryer, allows_pets, allows_smoking) Values(0, 0, 1, 0, 1, 1, 1, 0, 1);
insert into amenities (has_lake, has_river, has_pool, has_hot_tub, has_wifi, has_air_conditioning, has_washer_dryer, allows_pets, allows_smoking) Values(0, 1, 1, 1, 1, 1, 1, 1, 0);
insert into amenities (has_lake, has_river, has_pool, has_hot_tub, has_wifi, has_air_conditioning, has_washer_dryer, allows_pets, allows_smoking) Values(1, 0, 1, 0, 1, 1, 1, 1, 0);
insert into amenities (has_lake, has_river, has_pool, has_hot_tub, has_wifi, has_air_conditioning, has_washer_dryer, allows_pets, allows_smoking) Values(0, 1, 1, 0, 1, 1, 0, 1, 0);
insert into amenities (has_lake, has_river, has_pool, has_hot_tub, has_wifi, has_air_conditioning, has_washer_dryer, allows_pets, allows_smoking) Values(0, 0, 1, 0, 1, 0, 1, 1, 1);
insert into amenities (has_lake, has_river, has_pool, has_hot_tub, has_wifi, has_air_conditioning, has_washer_dryer, allows_pets, allows_smoking) Values(0, 0, 1, 0, 1, 1, 0, 1, 0);
insert into amenities (has_lake, has_river, has_pool, has_hot_tub, has_wifi, has_air_conditioning, has_washer_dryer, allows_pets, allows_smoking) Values(0, 1, 1, 0, 1, 1, 1, 0, 1);
insert into amenities (has_lake, has_river, has_pool, has_hot_tub, has_wifi, has_air_conditioning, has_washer_dryer, allows_pets, allows_smoking) Values(0, 0, 0, 1, 1, 1, 1, 1, 0);
insert into amenities (has_lake, has_river, has_pool, has_hot_tub, has_wifi, has_air_conditioning, has_washer_dryer, allows_pets, allows_smoking) Values(1, 0, 1, 0, 1, 0, 1, 1, 0);
insert into amenities (has_lake, has_river, has_pool, has_hot_tub, has_wifi, has_air_conditioning, has_washer_dryer, allows_pets, allows_smoking) Values(0, 1, 1, 0, 1, 1, 1, 1, 0);
insert into amenities (has_lake, has_river, has_pool, has_hot_tub, has_wifi, has_air_conditioning, has_washer_dryer, allows_pets, allows_smoking) Values(0, 0, 1, 1, 1, 1, 1, 1, 0);
insert into amenities (has_lake, has_river, has_pool, has_hot_tub, has_wifi, has_air_conditioning, has_washer_dryer, allows_pets, allows_smoking) Values(0, 0, 1, 0, 1, 1, 0, 1, 0);
insert into amenities (has_lake, has_river, has_pool, has_hot_tub, has_wifi, has_air_conditioning, has_washer_dryer, allows_pets, allows_smoking) Values(0, 0, 1, 1, 1, 1, 1, 1, 1);
insert into amenities (has_lake, has_river, has_pool, has_hot_tub, has_wifi, has_air_conditioning, has_washer_dryer, allows_pets, allows_smoking) Values(0, 0, 1, 1, 1, 0, 1, 1, 0);
insert into amenities (has_lake, has_river, has_pool, has_hot_tub, has_wifi, has_air_conditioning, has_washer_dryer, allows_pets, allows_smoking) Values(1, 0, 1, 1, 1, 1, 1, 1, 1);
insert into amenities (has_lake, has_river, has_pool, has_hot_tub, has_wifi, has_air_conditioning, has_washer_dryer, allows_pets, allows_smoking) Values(0, 1, 1, 0, 1, 1, 1, 1, 0);
insert into amenities (has_lake, has_river, has_pool, has_hot_tub, has_wifi, has_air_conditioning, has_washer_dryer, allows_pets, allows_smoking) Values(0, 1, 1, 0, 1, 1, 1, 1, 0);
insert into amenities (has_lake, has_river, has_pool, has_hot_tub, has_wifi, has_air_conditioning, has_washer_dryer, allows_pets, allows_smoking) Values(0, 0, 1, 0, 1, 1, 0, 1, 0);
insert into amenities (has_lake, has_river, has_pool, has_hot_tub, has_wifi, has_air_conditioning, has_washer_dryer, allows_pets, allows_smoking) Values(1, 0, 1, 0, 1, 1, 0, 0, 0);

insert into user (username, password, first_name, last_name, email) Values ("John1","johnpw","John","Weatherford","john@gmail.com");
insert into user (username, password, first_name, last_name, email) Values ("Chase2","chasepw","Chase","Williams","chase@gmail.com");
insert into user (username, password, first_name, last_name, email) Values ("Erika3","erikapw","Erika","Luz","erika@gmail.com");
insert into user (username, password, first_name, last_name, email) Values ("Austin4","austinpw","Austin","Salyers","austin@gmail.com");
insert into user (username, password, first_name, last_name, email) Values ("Shep5","sheppw","Shep","Ogden","shep@gmail.com");
insert into user (username, password, first_name, last_name, email) Values ("Jessica6","jessicapw","Jessica","Aspinwall","jessica@gmail.com");
insert into user (username, password, first_name, last_name, email) Values ("Justin7","justinpw","Justin","Jackson","justin@gmail.com");
insert into user (username, password, first_name, last_name, email) Values ("Heather8","heatherpw","Heather","Jackson","heather@gmail.com");
insert into user (username, password, first_name, last_name, email) Values ("Taylor9","taylorpw","Taylor","Johns","taylor@gmail.com");
insert into user (username, password, first_name, last_name, email) Values ("Emma10","emmapw","Emma","Freeman","emma@gmail.com");
insert into user (username, password, first_name, last_name, email) Values ("Josh11","joshpw","Josh","Dawson","josh@gmail.com");
insert into user (username, password, first_name, last_name, email) Values ("Cameron12","cameronpw","Cameron","Brown","camera@gmail.com");
insert into user (username, password, first_name, last_name, email) Values ("Tessa13","tessapw","Tessa","Shanklin","tessa@gmail.com");
insert into user (username, password, first_name, last_name, email) Values ("Nancy14","nancypw","Nancy","Ogden","nancy@gmail.com");
insert into user (username, password, first_name, last_name, email) Values ("Dan15","danpw","Dan","Ogden","dan@gmail.com");
insert into user (username, password, first_name, last_name, email) Values ("Christy16","christypw","Christy","Ogden","christy@gmail.com");
insert into user (username, password, first_name, last_name, email) Values ("Jess17","jesspw","Jess","Franklin","jess@gmail.com");
insert into user (username, password, first_name, last_name, email) Values ("Bob18","bobpw","Bob","Builder","bob@gmail.com");
insert into user (username, password, first_name, last_name, email) Values ("Evan19","evanpw","Evan","Courson","evan@gmail.com");
insert into user (username, password, first_name, last_name, email) Values ("Mitch20","mitchpw","Mitch","Pearson","mitch@gmail.com");
insert into user (username, password, first_name, last_name, email) Values ("Haley21","haleypw","Haley","Wise","haley@gmail.com");
insert into user (username, password, first_name, last_name, email) Values ("Caroline22","carolinepw","Caroline","Thomas","caroline@gmail.com");
insert into user (username, password, first_name, last_name, email) Values ("Mallory23","mallorypw","Mallory","Brinson","mallory@gmail.com");
insert into user (username, password, first_name, last_name, email) Values ("Frank24","frankpw","Frank","Romano","frank@gmail.com");
insert into cabin (address, city, state, description, title, bedroom_count, bath_count, max_occupancy,user_id,amenities_id) Values("1 broad street", "Athens", "GA", "If you like mountain views, then look no further. From practically every room in the cabin, you have incredible views of pastureland and mountains stretching to the sky. The location is incredibly peaceful, the cabin is nestled in the woods and the sounds of songbirds will greet you every morning.", "Mountain Cabin", 1, 1, 1, 1, 1);
insert into cabin (address, city, state, description, title, bedroom_count, bath_count, max_occupancy,user_id,amenities_id) Values("2 broad street", "Athens", "GA", "RATES ARE BOOK BY BEDROOM book from 1 bedroom to 5 bedrooms (this is not a shared house rate). Base Rate is for 1 bedroom (2 guests) each additional bedroom is $50/nt. To book the Entire Cabin enter max occupancy including infants 10 guests. Genuine log home on the upper Toccoa River, 170 ft of river frontage in Aska Adventure Area. Great for couples retreats cabin has 4 King size beds!", "Toccoa Cabin", 1, 3, 2, 2, 2);
insert into cabin (address, city, state, description, title, bedroom_count, bath_count, max_occupancy,user_id,amenities_id) Values("3 broad street", "Athens", "GA", "The perfect relaxing getaway awaits you in a cozy 1880’s cabin with panoramic mountain views. Nestled down in Wolffork Valley, the cabin is located on a small farm, Wolffork Valley Farm. With chickens, sheep and a famous watch dog named Mac! This pastoral setting is a perfect place to unwind, sit on the porch and watch the animals, or stargaze! Come enjoy this Farm to Table community, hiking, antiquing, and more! This is wild flower season! We look forward to meeting you!", "Antique Cabin", 2, 2, 3, 3, 3);
insert into cabin (address, city, state, description, title, bedroom_count, bath_count, max_occupancy,user_id,amenities_id) Values("4 broad street", "Athens", "GA", "Accommodation-Queen Bd/Pvt Bath in our 3 bd home atop a little Mtn at the end of a gravel rd in the Chattahoochee Forest. Additional sitting room with sofa (not hide a bed). Beautiful sunset views off the front deck and blazing sunrises from the rear of mountain. Its quiet, peaceful, surrounded by hundreds of undeveloped acres. Guests say we have the best views of Airbnb options in this area.", "Quiet Cabin", 2, 2, 4, 4, 4);
insert into cabin (address, city, state, description, title, bedroom_count, bath_count, max_occupancy,user_id,amenities_id) Values("5 broad street", "Athens", "GA", "Come enjoy our beautiful Cozy Cabin with a mountain view right off Aska Road. Modern amenities with a antique touch. One mile away from the historic town of Blue Ridge and close to all the outdoor activities on Aska Road.", "Aska Cabin", 3, 3, 5, 5, 5);
insert into cabin (address, city, state, description, title, bedroom_count, bath_count, max_occupancy,user_id,amenities_id) Values("6 broad street", "Athens", "GA", "Remarkable large hand-hewn log sided cabin nestled in the beautiful Blue Ridge Mountains has 3 bedrooms/3 baths one with a jacuzzi, large living room, media room and a wonderful outdoor fireplace for those cool mountain evenings, and decks galore. Great weekend getaway! Please note a 12% Georgia lodging tax will be added to all reservations after booking. We will send a reservation modification to include the taxes.", "Southern Cabin", 4, 4, 6, 6, 6);
insert into cabin (address, city, state, description, title, bedroom_count, bath_count, max_occupancy,user_id,amenities_id) Values("7 broad street", "Athens", "GA", "3BR/2Bath cabin with large back deck overlooking gorgeous stream fed pond. Borders 25 wooded acres that we own and the national forest within walking distance. Close to apple orchards, the Cartecay River, vineyards, and 30 mins to downtown Ellijay.", "Cartecay Cabin", 4, 5, 7, 7, 7);
insert into cabin (address, city, state, description, title, bedroom_count, bath_count, max_occupancy,user_id,amenities_id) Values("8 broad street", "Athens", "GA", "Renew your soul in this creek front cabin nestled 90 minutes from Atlanta in Blue Ridge in the N. Georgia mountains 15 minutes from town and 20 from Ellijay. The noisy sounds of Fightingtown Creek greet you as you step into the delightful decor of our mountain hideaway. Fully equipped kitchen, Wi-fi, cable TV, W/D. $50 cleaning fee per stay. Check-in is 3pm; check-out is noon (these times can be adjusted based on bookings.", "Creek Cabin", 4, 3, 8, 8, 8);
insert into cabin (address, city, state, description, title, bedroom_count, bath_count, max_occupancy,user_id,amenities_id) Values("9 broad street", "Athens", "GA", "A small space, with huge possibilities -- Enjoy the view of a beautiful stocked pond while you relax in this comfy cabin. A king loft sleeps 2 comfortably, and there is a twin bunk on main level. Full kitchen and bath. Fishing available!", "Tiny Cabin", 5, 4, 9, 9, 9);
insert into cabin (address, city, state, description, title, bedroom_count, bath_count, max_occupancy,user_id,amenities_id) Values("10 broad street", "Athens", "GA", "the best cabin10", "awesome", 5, 6, 10, 10, 10);
insert into cabin (address, city, state, description, title, bedroom_count, bath_count, max_occupancy,user_id,amenities_id) Values("11 broad street", "Athens", "GA", "the best cabin11", "awesome", 5, 4, 11, 11,11);
insert into cabin (address, city, state, description, title, bedroom_count, bath_count, max_occupancy,user_id,amenities_id) Values("12 broad street", "Athens", "GA", "the best cabin12", "awesome", 5, 3, 12, 12, 12);
insert into cabin (address, city, state, description, title, bedroom_count, bath_count, max_occupancy,user_id,amenities_id) Values("13 broad street", "Athens", "GA", "the best cabin13", "awesome", 7, 8, 13, 13, 13);
insert into cabin (address, city, state, description, title, bedroom_count, bath_count, max_occupancy,user_id,amenities_id) Values("14 broad street", "Athens", "GA", "the best cabin14", "awesome", 4, 4, 14, 14, 14);
insert into cabin (address, city, state, description, title, bedroom_count, bath_count, max_occupancy,user_id,amenities_id) Values("15 broad street", "Athens", "GA", "the best cabin15", "awesome", 7, 5, 15, 15, 15);
insert into cabin (address, city, state, description, title, bedroom_count, bath_count, max_occupancy,user_id,amenities_id) Values("16 broad street", "Athens", "GA", "the best cabin16", "awesome", 8, 3, 16, 16, 16);
insert into cabin (address, city, state, description, title, bedroom_count, bath_count, max_occupancy,user_id,amenities_id) Values("17 broad street", "Athens", "GA", "the best cabin17", "awesome", 8, 6, 17, 17, 17);
insert into cabin (address, city, state, description, title, bedroom_count, bath_count, max_occupancy,user_id,amenities_id) Values("18 broad street", "Athens", "GA", "the best cabin18", "awesome", 8, 5, 18, 18, 18);
insert into cabin (address, city, state, description, title, bedroom_count, bath_count, max_occupancy,user_id,amenities_id) Values("19 broad street", "Athens", "GA", "the best cabin19", "awesome", 7, 4, 19, 19, 19);
insert into cabin (address, city, state, description, title, bedroom_count, bath_count, max_occupancy,user_id,amenities_id) Values("20 broad street", "Athens", "GA", "the best cabin20", "awesome", 6, 3, 20, 20, 20);
insert into cabin (address, city, state, description, title, bedroom_count, bath_count, max_occupancy,user_id,amenities_id) Values("21 broad street", "Athens", "GA", "the best cabin21", "awesome", 10, 7, 21, 21, 21);
insert into cabin (address, city, state, description, title, bedroom_count, bath_count, max_occupancy,user_id,amenities_id) Values("22 broad street", "Athens", "GA", "the best cabin22", "awesome", 9, 7, 22, 22, 22);
insert into cabin (address, city, state, description, title, bedroom_count, bath_count, max_occupancy,user_id,amenities_id) Values("23 broad street", "Athens", "GA", "the best cabin23", "awesome", 8, 6, 23, 23, 23);
insert into cabin (address, city, state, description, title, bedroom_count, bath_count, max_occupancy,user_id,amenities_id) Values("24 broad street", "Athens", "GA", "the best cabin23", "awesome", 10, 8, 24, 24, 24);

insert into feature (feature_string, cabin_id) Values("Our cabin has a pool table, dartboard, and kayaking right down the road!", 1);
insert into feature (feature_string, cabin_id) Values("Prettiest cabin in the mountains, and our town has the best restaurants in Georgia!", 2);
insert into feature (feature_string, cabin_id) Values("Our cabin has a pool table, dartboard, and kayaking right down the road!", 3);
insert into feature (feature_string, cabin_id) Values("Our cabin has a pool table, dartboard, and kayaking right down the road!", 4);
insert into feature (feature_string, cabin_id) Values("Our cabin has a pool table, dartboard, and kayaking right down the road!", 5);
insert into feature (feature_string, cabin_id) Values("Our cabin has a pool table, dartboard, and kayaking right down the road!", 6);
insert into feature (feature_string, cabin_id) Values("Our cabin has a pool table, dartboard, and kayaking right down the road!", 7);
insert into feature (feature_string, cabin_id) Values("Our cabin has a pool table, dartboard, and kayaking right down the road!", 8);
insert into feature (feature_string, cabin_id) Values("Our cabin has a pool table, dartboard, and kayaking right down the road!", 9);
insert into feature (feature_string, cabin_id) Values("Our cabin has a pool table, dartboard, and kayaking right down the road!", 10);
insert into feature (feature_string, cabin_id) Values("Our cabin has a pool table, dartboard, and kayaking right down the road!", 11);
insert into feature (feature_string, cabin_id) Values("Our cabin has a pool table, dartboard, and kayaking right down the road!", 11);
insert into feature (feature_string, cabin_id) Values("Our cabin has a pool table, dartboard, and kayaking right down the road!", 13);
insert into feature (feature_string, cabin_id) Values("Our cabin has a pool table, dartboard, and kayaking right down the road!", 14);
insert into feature (feature_string, cabin_id) Values("Our cabin has a pool table, dartboard, and kayaking right down the road!", 15);
insert into feature (feature_string, cabin_id) Values("Our cabin has a pool table, dartboard, and kayaking right down the road!", 16);
insert into feature (feature_string, cabin_id) Values("Our cabin has a pool table, dartboard, and kayaking right down the road!", 17);
insert into feature (feature_string, cabin_id) Values("Our cabin has a pool table, dartboard, and kayaking right down the road!", 18);
insert into feature (feature_string, cabin_id) Values("Our cabin has a pool table, dartboard, and kayaking right down the road!", 19);
insert into feature (feature_string, cabin_id) Values("Our cabin has a pool table, dartboard, and kayaking right down the road!", 20);
insert into feature (feature_string, cabin_id) Values("Our cabin has a pool table, dartboard, and kayaking right down the road!", 21);
insert into feature (feature_string, cabin_id) Values("Our cabin has a pool table, dartboard, and kayaking right down the road!", 22);
insert into feature (feature_string, cabin_id) Values("Our cabin has a pool table, dartboard, and kayaking right down the road!", 23);
insert into feature (feature_string, cabin_id) Values("Our cabin has a pool table, dartboard, and kayaking right down the road!", 24);

insert into rent_record (total_price, start_date, end_date, cabin_id, user_id) Values(400, '2017-04-21', '2017-04-25', 1, 10);
insert into rent_record (total_price, start_date, end_date, cabin_id, user_id) Values(400, '2017-03-21', '2017-03-25', 1, 2);
insert into rent_record (total_price, start_date, end_date, cabin_id, user_id) Values(400, '2017-02-21', '2017-02-25', 1, 3);
insert into rent_record (total_price, start_date, end_date, cabin_id, user_id) Values(400, '2017-04-21', '2017-04-25', 2, 20);
insert into rent_record (total_price, start_date, end_date, cabin_id, user_id) Values(400, '2017-03-21', '2017-03-25', 2, 3);
insert into rent_record (total_price, start_date, end_date, cabin_id, user_id) Values(400, '2017-02-21', '2017-02-25', 2, 4);
insert into rent_record (total_price, start_date, end_date, cabin_id, user_id) Values(400, '2017-04-21', '2017-04-25', 3, 13);
insert into rent_record (total_price, start_date, end_date, cabin_id, user_id) Values(400, '2017-03-21', '2017-03-25', 3, 4);
insert into rent_record (total_price, start_date, end_date, cabin_id, user_id) Values(400, '2017-02-21', '2017-02-25', 3, 5);
insert into rent_record (total_price, start_date, end_date, cabin_id, user_id) Values(400, '2017-04-21', '2017-04-25', 4, 14);
insert into rent_record (total_price, start_date, end_date, cabin_id, user_id) Values(400, '2017-03-21', '2017-03-25', 4, 5);
insert into rent_record (total_price, start_date, end_date, cabin_id, user_id) Values(400, '2017-02-21', '2017-02-25', 4, 6);
insert into rent_record (total_price, start_date, end_date, cabin_id, user_id) Values(400, '2017-04-21', '2017-04-25', 5, 15);
insert into rent_record (total_price, start_date, end_date, cabin_id, user_id) Values(400, '2017-03-21', '2017-03-25', 5, 6);
insert into rent_record (total_price, start_date, end_date, cabin_id, user_id) Values(400, '2017-02-21', '2017-02-25', 5, 7);
insert into rent_record (total_price, start_date, end_date, cabin_id, user_id) Values(400, '2017-04-21', '2017-04-25', 6, 16);
insert into rent_record (total_price, start_date, end_date, cabin_id, user_id) Values(400, '2017-03-21', '2017-03-25', 6, 7);
insert into rent_record (total_price, start_date, end_date, cabin_id, user_id) Values(400, '2017-02-21', '2017-02-25', 6, 8);
insert into rent_record (total_price, start_date, end_date, cabin_id, user_id) Values(400, '2017-04-21', '2017-04-25', 7, 17);
insert into rent_record (total_price, start_date, end_date, cabin_id, user_id) Values(400, '2017-03-21', '2017-03-25', 7, 8);
insert into rent_record (total_price, start_date, end_date, cabin_id, user_id) Values(400, '2017-02-21', '2017-02-25', 7, 9);
insert into rent_record (total_price, start_date, end_date, cabin_id, user_id) Values(400, '2017-04-21', '2017-04-25', 8, 18);
insert into rent_record (total_price, start_date, end_date, cabin_id, user_id) Values(400, '2017-03-21', '2017-03-25', 8, 9);
insert into rent_record (total_price, start_date, end_date, cabin_id, user_id) Values(400, '2017-02-21', '2017-02-25', 8, 10);
insert into rent_record (total_price, start_date, end_date, cabin_id, user_id) Values(400, '2017-04-21', '2017-04-25', 9, 19);
insert into rent_record (total_price, start_date, end_date, cabin_id, user_id) Values(400, '2017-03-21', '2017-03-25', 9, 10);
insert into rent_record (total_price, start_date, end_date, cabin_id, user_id) Values(400, '2017-02-21', '2017-02-25', 9, 11);
insert into rent_record (total_price, start_date, end_date, cabin_id, user_id) Values(400, '2017-04-21', '2017-04-25', 10, 11);
insert into rent_record (total_price, start_date, end_date, cabin_id, user_id) Values(400, '2017-04-21', '2017-04-25', 11, 10);
insert into rent_record (total_price, start_date, end_date, cabin_id, user_id) Values(400, '2017-04-21', '2017-04-25', 12, 1);
insert into rent_record (total_price, start_date, end_date, cabin_id, user_id) Values(400, '2017-04-21', '2017-04-25', 13, 3);
insert into rent_record (total_price, start_date, end_date, cabin_id, user_id) Values(400, '2017-04-21', '2017-04-25', 14, 4);
insert into rent_record (total_price, start_date, end_date, cabin_id, user_id) Values(400, '2017-04-21', '2017-04-25', 15, 5);
insert into rent_record (total_price, start_date, end_date, cabin_id, user_id) Values(400, '2017-04-21', '2017-04-25', 16, 6);
insert into rent_record (total_price, start_date, end_date, cabin_id, user_id) Values(400, '2017-04-21', '2017-04-25', 17, 7);
insert into rent_record (total_price, start_date, end_date, cabin_id, user_id) Values(400, '2017-04-21', '2017-04-25', 18, 8);
insert into rent_record (total_price, start_date, end_date, cabin_id, user_id) Values(400, '2017-04-21', '2017-04-25', 19, 9);
insert into rent_record (total_price, start_date, end_date, cabin_id, user_id) Values(400, '2017-04-21', '2017-04-25', 20, 24);
insert into rent_record (total_price, start_date, end_date, cabin_id, user_id) Values(400, '2017-04-21', '2017-04-25', 21, 23);
insert into rent_record (total_price, start_date, end_date, cabin_id, user_id) Values(400, '2017-04-21', '2017-04-25', 22, 22);
insert into rent_record (total_price, start_date, end_date, cabin_id, user_id) Values(400, '2017-04-21', '2017-04-25', 23, 21);
insert into rent_record (total_price, start_date, end_date, cabin_id, user_id) Values(400, '2017-04-21', '2017-04-25', 24, 19);

insert into review (num_stars, title, description, rent_record_id) Values(5, "Great cabin!!", "Probably the best cabin I've ever stayed in", 1);
insert into review (num_stars, title, description, rent_record_id) Values(5, "Gorgeous Stay!", "This cabin had the best location, and the best hosts!", 2);
insert into review (num_stars, title, description, rent_record_id) Values(5, "Loved the outdoors!", "Wow, I will definitely be back!", 3);
insert into review (num_stars, title, description, rent_record_id) Values(5, "Great cabin!!", "Probably the best cabin I've ever stayed in", 4);
insert into review (num_stars, title, description, rent_record_id) Values(5, "Gorgeous Stay!", "This cabin had the best location, and the best hosts!", 5);
insert into review (num_stars, title, description, rent_record_id) Values(5, "Loved the outdoors!", "Wow, I will definitely be back!", 6);
insert into review (num_stars, title, description, rent_record_id) Values(5, "Great cabin!!", "Probably the best cabin I've ever stayed in", 7);
insert into review (num_stars, title, description, rent_record_id) Values(5, "Gorgeous Stay!", "This cabin had the best location, and the best hosts!", 8);
insert into review (num_stars, title, description, rent_record_id) Values(5, "Loved the outdoors!", "Wow, I will definitely be back!", 9);
insert into review (num_stars, title, description, rent_record_id) Values(5, "Great cabin!!", "Probably the best cabin I've ever stayed in", 10);
insert into review (num_stars, title, description, rent_record_id) Values(5, "Gorgeous Stay!", "This cabin had the best location, and the best hosts!", 11);
insert into review (num_stars, title, description, rent_record_id) Values(5, "Loved the outdoors!", "Wow, I will definitely be back!", 12);
insert into review (num_stars, title, description, rent_record_id) Values(5, "Great cabin!!", "Probably the best cabin I've ever stayed in", 13);
insert into review (num_stars, title, description, rent_record_id) Values(5, "Gorgeous Stay!", "This cabin had the best location, and the best hosts!", 14);
insert into review (num_stars, title, description, rent_record_id) Values(5, "Loved the outdoors!", "Wow, I will definitely be back!", 15);
insert into review (num_stars, title, description, rent_record_id) Values(5, "Great cabin!!", "Probably the best cabin I've ever stayed in", 16);
insert into review (num_stars, title, description, rent_record_id) Values(5, "Gorgeous Stay!", "This cabin had the best location, and the best hosts!",17);
insert into review (num_stars, title, description, rent_record_id) Values(5, "Loved the outdoors!", "Wow, I will definitely be back!", 18);
insert into review (num_stars, title, description, rent_record_id) Values(5, "Great cabin!!", "Probably the best cabin I've ever stayed in", 19);
insert into review (num_stars, title, description, rent_record_id) Values(5, "Gorgeous Stay!", "This cabin had the best location, and the best hosts!", 20);
insert into review (num_stars, title, description, rent_record_id) Values(5, "Loved the outdoors!", "Wow, I will definitely be back!", 21);
insert into review (num_stars, title, description, rent_record_id) Values(5, "Great cabin!!", "Probably the best cabin I've ever stayed in",22);
insert into review (num_stars, title, description, rent_record_id) Values(5, "Gorgeous Stay!", "This cabin had the best location, and the best hosts!", 23);
insert into review (num_stars, title, description, rent_record_id) Values(5, "Loved the outdoors!", "Wow, I will definitely be back!", 24);
insert into review (num_stars, title, description, rent_record_id) Values(5, "Great cabin!!", "Probably the best cabin I've ever stayed in", 25);
insert into review (num_stars, title, description, rent_record_id) Values(5, "Gorgeous Stay!", "This cabin had the best location, and the best hosts!", 26);
insert into review (num_stars, title, description, rent_record_id) Values(5, "Loved the outdoors!", "Wow, I will definitely be back!", 27);
insert into review (num_stars, title, description, rent_record_id) Values(5, "Great cabin!!", "Probably the best cabin I've ever stayed in", 28);
insert into review (num_stars, title, description, rent_record_id) Values(5, "Great cabin!!", "Probably the best cabin I've ever stayed in", 29);
insert into review (num_stars, title, description, rent_record_id) Values(5, "Great cabin!!", "Probably the best cabin I've ever stayed in", 30);
insert into review (num_stars, title, description, rent_record_id) Values(5, "Great cabin!!", "Probably the best cabin I've ever stayed in", 31);
insert into review (num_stars, title, description, rent_record_id) Values(5, "Great cabin!!", "Probably the best cabin I've ever stayed in", 32);
insert into review (num_stars, title, description, rent_record_id) Values(5, "Great cabin!!", "Probably the best cabin I've ever stayed in", 33);
insert into review (num_stars, title, description, rent_record_id) Values(5, "Great cabin!!", "Probably the best cabin I've ever stayed in", 34);
insert into review (num_stars, title, description, rent_record_id) Values(5, "Great cabin!!", "Probably the best cabin I've ever stayed in", 35);
insert into review (num_stars, title, description, rent_record_id) Values(5, "Great cabin!!", "Probably the best cabin I've ever stayed in", 36);
insert into review (num_stars, title, description, rent_record_id) Values(5, "Great cabin!!", "Probably the best cabin I've ever stayed in", 37);
insert into review (num_stars, title, description, rent_record_id) Values(5, "Great cabin!!", "Probably the best cabin I've ever stayed in", 38);
insert into review (num_stars, title, description, rent_record_id) Values(5, "Great cabin!!", "Probably the best cabin I've ever stayed in", 39);
insert into review (num_stars, title, description, rent_record_id) Values(5, "Great cabin!!", "Probably the best cabin I've ever stayed in", 40);
insert into review (num_stars, title, description, rent_record_id) Values(5, "Great cabin!!", "Probably the best cabin I've ever stayed in", 41);
insert into review (num_stars, title, description, rent_record_id) Values(5, "Great cabin!!", "Probably the best cabin I've ever stayed in", 42);

insert into availability (price, date, cabin_id) Values (100, '2017-04-27', 1);
insert into availability (price, date, cabin_id) Values (100, '2017-04-28', 1);
insert into availability (price, date, cabin_id) Values (100, '2017-04-29', 1);
insert into availability (price, date, cabin_id) Values (100, '2017-04-30', 1);
insert into availability (price, date, cabin_id) Values (100, '2017-05-01', 1);
insert into availability (price, date, cabin_id) Values (100, '2017-05-02', 1);
insert into availability (price, date, cabin_id) Values (100, '2017-05-03', 1);
insert into availability (price, date, cabin_id) Values (100, '2017-05-04', 1);
insert into availability (price, date, cabin_id) Values (100, '2017-05-05', 1);
insert into availability (price, date, cabin_id) Values (100, '2017-05-06', 1);
insert into availability (price, date, cabin_id) Values (100, '2017-05-07', 1);
insert into availability (price, date, cabin_id) Values (100, '2017-05-08', 1);
insert into availability (price, date, cabin_id) Values (100, '2017-05-09', 1);
insert into availability (price, date, cabin_id) Values (100, '2017-05-10', 1);
insert into availability (price, date, cabin_id) Values (100, '2017-05-11', 1);
insert into availability (price, date, cabin_id) Values (100, '2017-05-12', 1);
insert into availability (price, date, cabin_id) Values (100, '2017-05-13', 1);
insert into availability (price, date, cabin_id) Values (100, '2017-05-14', 1);
insert into availability (price, date, cabin_id) Values (100, '2017-05-15', 1);
insert into availability (price, date, cabin_id) Values (100, '2017-05-16', 1);
insert into availability (price, date, cabin_id) Values (100, '2017-05-17', 1);
insert into availability (price, date, cabin_id) Values (100, '2017-05-18', 1);
insert into availability (price, date, cabin_id) Values (100, '2017-05-19', 1);
insert into availability (price, date, cabin_id) Values (100, '2017-05-20', 1);
insert into availability (price, date, cabin_id) Values (100, '2017-05-21', 1);
insert into availability (price, date, cabin_id) Values (100, '2017-05-22', 1);
insert into availability (price, date, cabin_id) Values (100, '2017-05-23', 1);
insert into availability (price, date, cabin_id) Values (100, '2017-05-24', 1);
insert into availability (price, date, cabin_id) Values (100, '2017-05-25', 1);
insert into availability (price, date, cabin_id) Values (100, '2017-05-26', 1);
insert into availability (price, date, cabin_id) Values (100, '2017-05-27', 1);
insert into availability (price, date, cabin_id) Values (100, '2017-05-28', 1);
insert into availability (price, date, cabin_id) Values (100, '2017-05-29', 1);
insert into availability (price, date, cabin_id) Values (100, '2017-05-30', 1);
insert into availability (price, date, cabin_id) Values (100, '2017-05-31', 1);

insert into availability (price, date, cabin_id) Values (90, '2017-04-27', 2);
insert into availability (price, date, cabin_id) Values (90, '2017-04-28', 2);
insert into availability (price, date, cabin_id) Values (90, '2017-04-29', 2);
insert into availability (price, date, cabin_id) Values (90, '2017-04-30', 2);
insert into availability (price, date, cabin_id) Values (90, '2017-05-01', 2);
insert into availability (price, date, cabin_id) Values (90, '2017-05-02', 2);
insert into availability (price, date, cabin_id) Values (90, '2017-05-03', 2);
insert into availability (price, date, cabin_id) Values (90, '2017-05-04', 2);
insert into availability (price, date, cabin_id) Values (90, '2017-05-05', 2);
insert into availability (price, date, cabin_id) Values (90, '2017-05-06', 2);
insert into availability (price, date, cabin_id) Values (90, '2017-05-07', 2);
insert into availability (price, date, cabin_id) Values (90, '2017-05-08', 2);
insert into availability (price, date, cabin_id) Values (90, '2017-05-09', 2);
insert into availability (price, date, cabin_id) Values (90, '2017-05-10', 2);
insert into availability (price, date, cabin_id) Values (90, '2017-05-11', 2);
insert into availability (price, date, cabin_id) Values (90, '2017-05-12', 2);
insert into availability (price, date, cabin_id) Values (90, '2017-05-13', 2);
insert into availability (price, date, cabin_id) Values (90, '2017-05-14', 2);
insert into availability (price, date, cabin_id) Values (90, '2017-05-15', 2);
insert into availability (price, date, cabin_id) Values (90, '2017-05-16', 2);
insert into availability (price, date, cabin_id) Values (90, '2017-05-17', 2);
insert into availability (price, date, cabin_id) Values (90, '2017-05-18', 2);
insert into availability (price, date, cabin_id) Values (90, '2017-05-19', 2);
insert into availability (price, date, cabin_id) Values (90, '2017-05-20', 2);
insert into availability (price, date, cabin_id) Values (90, '2017-05-21', 2);
insert into availability (price, date, cabin_id) Values (90, '2017-05-22', 2);
insert into availability (price, date, cabin_id) Values (90, '2017-05-23', 2);
insert into availability (price, date, cabin_id) Values (90, '2017-05-24', 2);
insert into availability (price, date, cabin_id) Values (90, '2017-05-25', 2);
insert into availability (price, date, cabin_id) Values (90, '2017-05-26', 2);
insert into availability (price, date, cabin_id) Values (90, '2017-05-27', 2);
insert into availability (price, date, cabin_id) Values (90, '2017-05-28', 2);
insert into availability (price, date, cabin_id) Values (90, '2017-05-29', 2);
insert into availability (price, date, cabin_id) Values (90, '2017-05-30', 2);
insert into availability (price, date, cabin_id) Values (90, '2017-05-31', 2);

insert into availability (price, date, cabin_id) Values (110, '2017-04-27', 3);
insert into availability (price, date, cabin_id) Values (110, '2017-04-28', 3);
insert into availability (price, date, cabin_id) Values (110, '2017-04-29', 3);
insert into availability (price, date, cabin_id) Values (110, '2017-04-30', 3);
insert into availability (price, date, cabin_id) Values (110, '2017-05-01', 3);
insert into availability (price, date, cabin_id) Values (110, '2017-05-02', 3);
insert into availability (price, date, cabin_id) Values (110, '2017-05-03', 3);
insert into availability (price, date, cabin_id) Values (110, '2017-05-04', 3);
insert into availability (price, date, cabin_id) Values (110, '2017-05-05', 3);
insert into availability (price, date, cabin_id) Values (110, '2017-05-06', 3);
insert into availability (price, date, cabin_id) Values (110, '2017-05-07', 3);
insert into availability (price, date, cabin_id) Values (110, '2017-05-08', 3);
insert into availability (price, date, cabin_id) Values (110, '2017-05-09', 3);
insert into availability (price, date, cabin_id) Values (110, '2017-05-10', 3);
insert into availability (price, date, cabin_id) Values (110, '2017-05-11', 3);
insert into availability (price, date, cabin_id) Values (110, '2017-05-12', 3);
insert into availability (price, date, cabin_id) Values (110, '2017-05-13', 3);
insert into availability (price, date, cabin_id) Values (110, '2017-05-14', 3);
insert into availability (price, date, cabin_id) Values (110, '2017-05-15', 3);
insert into availability (price, date, cabin_id) Values (110, '2017-05-16', 3);
insert into availability (price, date, cabin_id) Values (110, '2017-05-17', 3);
insert into availability (price, date, cabin_id) Values (110, '2017-05-18', 3);
insert into availability (price, date, cabin_id) Values (110, '2017-05-19', 3);
insert into availability (price, date, cabin_id) Values (110, '2017-05-20', 3);
insert into availability (price, date, cabin_id) Values (110, '2017-05-21', 3);
insert into availability (price, date, cabin_id) Values (110, '2017-05-22', 3);
insert into availability (price, date, cabin_id) Values (110, '2017-05-23', 3);
insert into availability (price, date, cabin_id) Values (110, '2017-05-24', 3);
insert into availability (price, date, cabin_id) Values (110, '2017-05-25', 3);
insert into availability (price, date, cabin_id) Values (110, '2017-05-26', 3);
insert into availability (price, date, cabin_id) Values (110, '2017-05-27', 3);
insert into availability (price, date, cabin_id) Values (110, '2017-05-28', 3);
insert into availability (price, date, cabin_id) Values (110, '2017-05-29', 3);
insert into availability (price, date, cabin_id) Values (110, '2017-05-30', 3);
insert into availability (price, date, cabin_id) Values (110, '2017-05-31', 3);

insert into availability (price, date, cabin_id) Values (105, '2017-04-27', 4);
insert into availability (price, date, cabin_id) Values (105, '2017-04-28', 4);
insert into availability (price, date, cabin_id) Values (105, '2017-04-29', 4);
insert into availability (price, date, cabin_id) Values (105, '2017-04-30', 4);
insert into availability (price, date, cabin_id) Values (105, '2017-05-01', 4);
insert into availability (price, date, cabin_id) Values (105, '2017-05-02', 4);
insert into availability (price, date, cabin_id) Values (105, '2017-05-03', 4);
insert into availability (price, date, cabin_id) Values (105, '2017-05-04', 4);
insert into availability (price, date, cabin_id) Values (105, '2017-05-05', 4);
insert into availability (price, date, cabin_id) Values (105, '2017-05-06', 4);
insert into availability (price, date, cabin_id) Values (105, '2017-05-07', 4);
insert into availability (price, date, cabin_id) Values (105, '2017-05-08', 4);
insert into availability (price, date, cabin_id) Values (105, '2017-05-09', 4);
insert into availability (price, date, cabin_id) Values (105, '2017-05-10', 4);
insert into availability (price, date, cabin_id) Values (105, '2017-05-11', 4);
insert into availability (price, date, cabin_id) Values (105, '2017-05-12', 4);
insert into availability (price, date, cabin_id) Values (105, '2017-05-13', 4);
insert into availability (price, date, cabin_id) Values (105, '2017-05-14', 4);
insert into availability (price, date, cabin_id) Values (105, '2017-05-15', 4);
insert into availability (price, date, cabin_id) Values (105, '2017-05-16', 4);
insert into availability (price, date, cabin_id) Values (105, '2017-05-17', 4);
insert into availability (price, date, cabin_id) Values (105, '2017-05-18', 4);
insert into availability (price, date, cabin_id) Values (105, '2017-05-19', 4);
insert into availability (price, date, cabin_id) Values (105, '2017-05-20', 4);
insert into availability (price, date, cabin_id) Values (105, '2017-05-21', 4);
insert into availability (price, date, cabin_id) Values (105, '2017-05-22', 4);
insert into availability (price, date, cabin_id) Values (105, '2017-05-23', 4);
insert into availability (price, date, cabin_id) Values (105, '2017-05-24', 4);
insert into availability (price, date, cabin_id) Values (105, '2017-05-25', 4);
insert into availability (price, date, cabin_id) Values (105, '2017-05-26', 4);
insert into availability (price, date, cabin_id) Values (105, '2017-05-27', 4);
insert into availability (price, date, cabin_id) Values (105, '2017-05-28', 4);
insert into availability (price, date, cabin_id) Values (105, '2017-05-29', 4);
insert into availability (price, date, cabin_id) Values (105, '2017-05-30', 4);
insert into availability (price, date, cabin_id) Values (105, '2017-05-31', 4);

insert into availability (price, date, cabin_id) Values (80, '2017-04-27', 5);
insert into availability (price, date, cabin_id) Values (80, '2017-04-28', 5);
insert into availability (price, date, cabin_id) Values (80, '2017-04-29', 5);
insert into availability (price, date, cabin_id) Values (80, '2017-04-30', 5);
insert into availability (price, date, cabin_id) Values (80, '2017-05-01', 5);
insert into availability (price, date, cabin_id) Values (80, '2017-05-02', 5);
insert into availability (price, date, cabin_id) Values (80, '2017-05-03', 5);
insert into availability (price, date, cabin_id) Values (80, '2017-05-04', 5);
insert into availability (price, date, cabin_id) Values (80, '2017-05-05', 5);
insert into availability (price, date, cabin_id) Values (80, '2017-05-06', 5);
insert into availability (price, date, cabin_id) Values (80, '2017-05-07', 5);
insert into availability (price, date, cabin_id) Values (80, '2017-05-08', 5);
insert into availability (price, date, cabin_id) Values (80, '2017-05-09', 5);
insert into availability (price, date, cabin_id) Values (80, '2017-05-10', 5);
insert into availability (price, date, cabin_id) Values (80, '2017-05-11', 5);
insert into availability (price, date, cabin_id) Values (80, '2017-05-12', 5);
insert into availability (price, date, cabin_id) Values (80, '2017-05-13', 5);
insert into availability (price, date, cabin_id) Values (80, '2017-05-14', 5);
insert into availability (price, date, cabin_id) Values (80, '2017-05-15', 5);
insert into availability (price, date, cabin_id) Values (80, '2017-05-16', 5);
insert into availability (price, date, cabin_id) Values (80, '2017-05-17', 5);
insert into availability (price, date, cabin_id) Values (80, '2017-05-18', 5);
insert into availability (price, date, cabin_id) Values (80, '2017-05-19', 5);
insert into availability (price, date, cabin_id) Values (80, '2017-05-20', 5);
insert into availability (price, date, cabin_id) Values (80, '2017-05-21', 5);
insert into availability (price, date, cabin_id) Values (80, '2017-05-22', 5);
insert into availability (price, date, cabin_id) Values (80, '2017-05-23', 5);
insert into availability (price, date, cabin_id) Values (80, '2017-05-24', 5);
insert into availability (price, date, cabin_id) Values (80, '2017-05-25', 5);
insert into availability (price, date, cabin_id) Values (80, '2017-05-26', 5);
insert into availability (price, date, cabin_id) Values (80, '2017-05-27', 5);
insert into availability (price, date, cabin_id) Values (80, '2017-05-28', 5);
insert into availability (price, date, cabin_id) Values (80, '2017-05-29', 5);
insert into availability (price, date, cabin_id) Values (80, '2017-05-30', 5);
insert into availability (price, date, cabin_id) Values (80, '2017-05-31', 5);

insert into availability (price, date, cabin_id) Values (110, '2017-04-27', 6);
insert into availability (price, date, cabin_id) Values (110, '2017-04-28', 6);
insert into availability (price, date, cabin_id) Values (110, '2017-04-29', 6);
insert into availability (price, date, cabin_id) Values (110, '2017-04-30', 6);
insert into availability (price, date, cabin_id) Values (110, '2017-05-01', 6);
insert into availability (price, date, cabin_id) Values (110, '2017-05-02', 6);
insert into availability (price, date, cabin_id) Values (110, '2017-05-03', 6);
insert into availability (price, date, cabin_id) Values (110, '2017-05-04', 6);
insert into availability (price, date, cabin_id) Values (110, '2017-05-05', 6);
insert into availability (price, date, cabin_id) Values (110, '2017-05-06', 6);
insert into availability (price, date, cabin_id) Values (110, '2017-05-07', 6);
insert into availability (price, date, cabin_id) Values (110, '2017-05-08', 6);
insert into availability (price, date, cabin_id) Values (110, '2017-05-09', 6);
insert into availability (price, date, cabin_id) Values (110, '2017-05-10', 6);
insert into availability (price, date, cabin_id) Values (110, '2017-05-11', 6);
insert into availability (price, date, cabin_id) Values (110, '2017-05-12', 6);
insert into availability (price, date, cabin_id) Values (110, '2017-05-13', 6);
insert into availability (price, date, cabin_id) Values (110, '2017-05-14', 6);
insert into availability (price, date, cabin_id) Values (110, '2017-05-15', 6);
insert into availability (price, date, cabin_id) Values (110, '2017-05-16', 6);
insert into availability (price, date, cabin_id) Values (110, '2017-05-17', 6);
insert into availability (price, date, cabin_id) Values (110, '2017-05-18', 6);
insert into availability (price, date, cabin_id) Values (110, '2017-05-19', 6);
insert into availability (price, date, cabin_id) Values (110, '2017-05-20', 6);
insert into availability (price, date, cabin_id) Values (110, '2017-05-21', 6);
insert into availability (price, date, cabin_id) Values (110, '2017-05-22', 6);
insert into availability (price, date, cabin_id) Values (110, '2017-05-23', 6);
insert into availability (price, date, cabin_id) Values (110, '2017-05-24', 6);
insert into availability (price, date, cabin_id) Values (110, '2017-05-25', 6);
insert into availability (price, date, cabin_id) Values (110, '2017-05-26', 6);
insert into availability (price, date, cabin_id) Values (110, '2017-05-27', 6);
insert into availability (price, date, cabin_id) Values (110, '2017-05-28', 6);
insert into availability (price, date, cabin_id) Values (110, '2017-05-29', 6);
insert into availability (price, date, cabin_id) Values (110, '2017-05-30', 6);
insert into availability (price, date, cabin_id) Values (110, '2017-05-31', 6);

insert into availability (price, date, cabin_id) Values (130, '2017-04-27', 7);
insert into availability (price, date, cabin_id) Values (130, '2017-04-28', 7);
insert into availability (price, date, cabin_id) Values (130, '2017-04-29', 7);
insert into availability (price, date, cabin_id) Values (130, '2017-04-30', 7);
insert into availability (price, date, cabin_id) Values (130, '2017-05-01', 7);
insert into availability (price, date, cabin_id) Values (130, '2017-05-02', 7);
insert into availability (price, date, cabin_id) Values (130, '2017-05-03', 7);
insert into availability (price, date, cabin_id) Values (130, '2017-05-04', 7);
insert into availability (price, date, cabin_id) Values (130, '2017-05-05', 7);
insert into availability (price, date, cabin_id) Values (130, '2017-05-06', 7);
insert into availability (price, date, cabin_id) Values (130, '2017-05-07', 7);
insert into availability (price, date, cabin_id) Values (130, '2017-05-08', 7);
insert into availability (price, date, cabin_id) Values (130, '2017-05-09', 7);
insert into availability (price, date, cabin_id) Values (130, '2017-05-10', 7);
insert into availability (price, date, cabin_id) Values (130, '2017-05-11', 7);
insert into availability (price, date, cabin_id) Values (130, '2017-05-12', 7);
insert into availability (price, date, cabin_id) Values (130, '2017-05-13', 7);
insert into availability (price, date, cabin_id) Values (130, '2017-05-14', 7);
insert into availability (price, date, cabin_id) Values (130, '2017-05-15', 7);
insert into availability (price, date, cabin_id) Values (130, '2017-05-16', 7);
insert into availability (price, date, cabin_id) Values (130, '2017-05-17', 7);
insert into availability (price, date, cabin_id) Values (130, '2017-05-18', 7);
insert into availability (price, date, cabin_id) Values (130, '2017-05-19', 7);
insert into availability (price, date, cabin_id) Values (130, '2017-05-20', 7);
insert into availability (price, date, cabin_id) Values (130, '2017-05-21', 7);
insert into availability (price, date, cabin_id) Values (130, '2017-05-22', 7);
insert into availability (price, date, cabin_id) Values (130, '2017-05-23', 7);
insert into availability (price, date, cabin_id) Values (130, '2017-05-24', 7);
insert into availability (price, date, cabin_id) Values (130, '2017-05-25', 7);
insert into availability (price, date, cabin_id) Values (130, '2017-05-26', 7);
insert into availability (price, date, cabin_id) Values (130, '2017-05-27', 7);
insert into availability (price, date, cabin_id) Values (130, '2017-05-28', 7);
insert into availability (price, date, cabin_id) Values (130, '2017-05-29', 7);
insert into availability (price, date, cabin_id) Values (130, '2017-05-30', 7);
insert into availability (price, date, cabin_id) Values (130, '2017-05-31', 7);

insert into availability (price, date, cabin_id) Values (140, '2017-04-27', 8);
insert into availability (price, date, cabin_id) Values (140, '2017-04-28', 8);
insert into availability (price, date, cabin_id) Values (140, '2017-04-29', 8);
insert into availability (price, date, cabin_id) Values (140, '2017-04-30', 8);
insert into availability (price, date, cabin_id) Values (140, '2017-05-01', 8);
insert into availability (price, date, cabin_id) Values (140, '2017-05-02', 8);
insert into availability (price, date, cabin_id) Values (140, '2017-05-03', 8);
insert into availability (price, date, cabin_id) Values (140, '2017-05-04', 8);
insert into availability (price, date, cabin_id) Values (140, '2017-05-05', 8);
insert into availability (price, date, cabin_id) Values (140, '2017-05-06', 8);
insert into availability (price, date, cabin_id) Values (140, '2017-05-07', 8);
insert into availability (price, date, cabin_id) Values (140, '2017-05-08', 8);
insert into availability (price, date, cabin_id) Values (140, '2017-05-09', 8);
insert into availability (price, date, cabin_id) Values (140, '2017-05-10', 8);
insert into availability (price, date, cabin_id) Values (140, '2017-05-11', 8);
insert into availability (price, date, cabin_id) Values (140, '2017-05-12', 8);
insert into availability (price, date, cabin_id) Values (140, '2017-05-13', 8);
insert into availability (price, date, cabin_id) Values (140, '2017-05-14', 8);
insert into availability (price, date, cabin_id) Values (140, '2017-05-15', 8);
insert into availability (price, date, cabin_id) Values (140, '2017-05-16', 8);
insert into availability (price, date, cabin_id) Values (140, '2017-05-17', 8);
insert into availability (price, date, cabin_id) Values (140, '2017-05-18', 8);
insert into availability (price, date, cabin_id) Values (140, '2017-05-19', 8);
insert into availability (price, date, cabin_id) Values (140, '2017-05-20', 8);
insert into availability (price, date, cabin_id) Values (140, '2017-05-21', 8);
insert into availability (price, date, cabin_id) Values (140, '2017-05-22', 8);
insert into availability (price, date, cabin_id) Values (140, '2017-05-23', 8);
insert into availability (price, date, cabin_id) Values (140, '2017-05-24', 8);
insert into availability (price, date, cabin_id) Values (140, '2017-05-25', 8);
insert into availability (price, date, cabin_id) Values (140, '2017-05-26', 8);
insert into availability (price, date, cabin_id) Values (140, '2017-05-27', 8);
insert into availability (price, date, cabin_id) Values (140, '2017-05-28', 8);
insert into availability (price, date, cabin_id) Values (140, '2017-05-29', 8);
insert into availability (price, date, cabin_id) Values (140, '2017-05-30', 8);
insert into availability (price, date, cabin_id) Values (140, '2017-05-31', 8);

insert into availability (price, date, cabin_id) Values (100, '2017-04-27', 9);
insert into availability (price, date, cabin_id) Values (100, '2017-04-28', 9);
insert into availability (price, date, cabin_id) Values (100, '2017-04-29', 9);
insert into availability (price, date, cabin_id) Values (100, '2017-04-30', 9);
insert into availability (price, date, cabin_id) Values (100, '2017-05-01', 9);
insert into availability (price, date, cabin_id) Values (100, '2017-05-02', 9);
insert into availability (price, date, cabin_id) Values (100, '2017-05-03', 9);
insert into availability (price, date, cabin_id) Values (100, '2017-05-04', 9);
insert into availability (price, date, cabin_id) Values (100, '2017-05-05', 9);
insert into availability (price, date, cabin_id) Values (100, '2017-05-06', 9);
insert into availability (price, date, cabin_id) Values (100, '2017-05-07', 9);
insert into availability (price, date, cabin_id) Values (100, '2017-05-08', 9);
insert into availability (price, date, cabin_id) Values (100, '2017-05-09', 9);
insert into availability (price, date, cabin_id) Values (100, '2017-05-10', 9);
insert into availability (price, date, cabin_id) Values (100, '2017-05-11', 9);
insert into availability (price, date, cabin_id) Values (100, '2017-05-12', 9);
insert into availability (price, date, cabin_id) Values (100, '2017-05-13', 9);
insert into availability (price, date, cabin_id) Values (100, '2017-05-14', 9);
insert into availability (price, date, cabin_id) Values (100, '2017-05-15', 9);
insert into availability (price, date, cabin_id) Values (100, '2017-05-16', 9);
insert into availability (price, date, cabin_id) Values (100, '2017-05-17', 9);
insert into availability (price, date, cabin_id) Values (100, '2017-05-18', 9);
insert into availability (price, date, cabin_id) Values (100, '2017-05-19', 9);
insert into availability (price, date, cabin_id) Values (100, '2017-05-20', 9);
insert into availability (price, date, cabin_id) Values (100, '2017-05-21', 9);
insert into availability (price, date, cabin_id) Values (100, '2017-05-22', 9);
insert into availability (price, date, cabin_id) Values (100, '2017-05-23', 9);
insert into availability (price, date, cabin_id) Values (100, '2017-05-24', 9);
insert into availability (price, date, cabin_id) Values (100, '2017-05-25', 9);
insert into availability (price, date, cabin_id) Values (100, '2017-05-26', 9);
insert into availability (price, date, cabin_id) Values (100, '2017-05-27', 9);
insert into availability (price, date, cabin_id) Values (100, '2017-05-28', 9);
insert into availability (price, date, cabin_id) Values (100, '2017-05-29', 9);
insert into availability (price, date, cabin_id) Values (100, '2017-05-30', 9);
insert into availability (price, date, cabin_id) Values (100, '2017-05-31', 9);

insert into availability (price, date, cabin_id) Values (110, '2017-04-27', 10);
insert into availability (price, date, cabin_id) Values (110, '2017-04-28', 10);
insert into availability (price, date, cabin_id) Values (110, '2017-04-29', 10);
insert into availability (price, date, cabin_id) Values (110, '2017-04-30', 10);
insert into availability (price, date, cabin_id) Values (110, '2017-05-01', 10);
insert into availability (price, date, cabin_id) Values (110, '2017-05-02', 10);
insert into availability (price, date, cabin_id) Values (110, '2017-05-03', 10);
insert into availability (price, date, cabin_id) Values (110, '2017-05-04', 10);
insert into availability (price, date, cabin_id) Values (110, '2017-05-05', 10);
insert into availability (price, date, cabin_id) Values (110, '2017-05-06', 10);
insert into availability (price, date, cabin_id) Values (110, '2017-05-07', 10);
insert into availability (price, date, cabin_id) Values (110, '2017-05-08', 10);
insert into availability (price, date, cabin_id) Values (110, '2017-05-08', 10);
insert into availability (price, date, cabin_id) Values (110, '2017-05-10', 10);
insert into availability (price, date, cabin_id) Values (110, '2017-05-11', 10);
insert into availability (price, date, cabin_id) Values (110, '2017-05-12', 10);
insert into availability (price, date, cabin_id) Values (110, '2017-05-13', 10);
insert into availability (price, date, cabin_id) Values (110, '2017-05-14', 10);
insert into availability (price, date, cabin_id) Values (110, '2017-05-15', 10);
insert into availability (price, date, cabin_id) Values (110, '2017-05-16', 10);
insert into availability (price, date, cabin_id) Values (110, '2017-05-17', 10);
insert into availability (price, date, cabin_id) Values (110, '2017-05-18', 10);
insert into availability (price, date, cabin_id) Values (110, '2017-05-19', 10);
insert into availability (price, date, cabin_id) Values (110, '2017-05-20', 10);
insert into availability (price, date, cabin_id) Values (110, '2017-05-21', 10);
insert into availability (price, date, cabin_id) Values (110, '2017-05-22', 10);
insert into availability (price, date, cabin_id) Values (110, '2017-05-23', 10);
insert into availability (price, date, cabin_id) Values (110, '2017-05-24', 10);
insert into availability (price, date, cabin_id) Values (110, '2017-05-25', 10);
insert into availability (price, date, cabin_id) Values (110, '2017-05-26', 10);
insert into availability (price, date, cabin_id) Values (110, '2017-05-27', 10);
insert into availability (price, date, cabin_id) Values (110, '2017-05-28', 10);
insert into availability (price, date, cabin_id) Values (110, '2017-05-29', 10);
insert into availability (price, date, cabin_id) Values (110, '2017-05-30', 10);
insert into availability (price, date, cabin_id) Values (110, '2017-05-31', 10);

insert into availability (price, date, cabin_id) Values (105, '2017-04-27', 11);
insert into availability (price, date, cabin_id) Values (105, '2017-04-28', 11);
insert into availability (price, date, cabin_id) Values (105, '2017-04-29', 11);
insert into availability (price, date, cabin_id) Values (105, '2017-04-30', 11);
insert into availability (price, date, cabin_id) Values (105, '2017-05-21', 11);
insert into availability (price, date, cabin_id) Values (105, '2017-05-22', 11);
insert into availability (price, date, cabin_id) Values (105, '2017-05-23', 11);
insert into availability (price, date, cabin_id) Values (105, '2017-05-24', 11);
insert into availability (price, date, cabin_id) Values (105, '2017-05-25', 11);
insert into availability (price, date, cabin_id) Values (105, '2017-05-26', 11);

insert into availability (price, date, cabin_id) Values (110, '2017-04-27', 12);
insert into availability (price, date, cabin_id) Values (110, '2017-04-28', 12);
insert into availability (price, date, cabin_id) Values (110, '2017-04-29', 12);
insert into availability (price, date, cabin_id) Values (110, '2017-04-30', 12);
insert into availability (price, date, cabin_id) Values (110, '2017-05-21', 12);
insert into availability (price, date, cabin_id) Values (110, '2017-05-22', 12);
insert into availability (price, date, cabin_id) Values (110, '2017-05-23', 12);
insert into availability (price, date, cabin_id) Values (110, '2017-05-24', 12);
insert into availability (price, date, cabin_id) Values (110, '2017-05-25', 12);
insert into availability (price, date, cabin_id) Values (110, '2017-05-26', 12);

insert into availability (price, date, cabin_id) Values (50, '2017-04-27', 13);
insert into availability (price, date, cabin_id) Values (50, '2017-04-28', 13);
insert into availability (price, date, cabin_id) Values (50, '2017-04-29', 13);
insert into availability (price, date, cabin_id) Values (50, '2017-04-30', 13);
insert into availability (price, date, cabin_id) Values (50, '2017-05-21', 13);
insert into availability (price, date, cabin_id) Values (50, '2017-05-22', 13);
insert into availability (price, date, cabin_id) Values (50, '2017-05-23', 13);
insert into availability (price, date, cabin_id) Values (50, '2017-05-24', 13);
insert into availability (price, date, cabin_id) Values (50, '2017-05-25', 13);
insert into availability (price, date, cabin_id) Values (50, '2017-05-26', 13);

insert into availability (price, date, cabin_id) Values (70, '2017-04-27', 14);
insert into availability (price, date, cabin_id) Values (70, '2017-04-28', 14);
insert into availability (price, date, cabin_id) Values (70, '2017-04-29', 14);
insert into availability (price, date, cabin_id) Values (70, '2017-04-30', 14);
insert into availability (price, date, cabin_id) Values (70, '2017-05-21', 14);
insert into availability (price, date, cabin_id) Values (70, '2017-05-22', 14);
insert into availability (price, date, cabin_id) Values (70, '2017-05-23', 14);
insert into availability (price, date, cabin_id) Values (70, '2017-05-24', 14);
insert into availability (price, date, cabin_id) Values (70, '2017-05-25', 14);
insert into availability (price, date, cabin_id) Values (70, '2017-05-26', 14);

insert into availability (price, date, cabin_id) Values (100, '2017-04-27', 15);
insert into availability (price, date, cabin_id) Values (100, '2017-04-28', 15);
insert into availability (price, date, cabin_id) Values (100, '2017-04-29', 15);
insert into availability (price, date, cabin_id) Values (100, '2017-04-30', 15);
insert into availability (price, date, cabin_id) Values (100, '2017-05-21', 15);
insert into availability (price, date, cabin_id) Values (100, '2017-05-22', 15);
insert into availability (price, date, cabin_id) Values (100, '2017-05-23', 15);
insert into availability (price, date, cabin_id) Values (100, '2017-05-24', 15);
insert into availability (price, date, cabin_id) Values (100, '2017-05-25', 15);
insert into availability (price, date, cabin_id) Values (100, '2017-05-26', 15);

insert into availability (price, date, cabin_id) Values (105, '2017-04-27', 16);
insert into availability (price, date, cabin_id) Values (105, '2017-04-28', 16);
insert into availability (price, date, cabin_id) Values (105, '2017-04-29', 16);
insert into availability (price, date, cabin_id) Values (105, '2017-04-30', 16);
insert into availability (price, date, cabin_id) Values (105, '2017-05-21', 16);
insert into availability (price, date, cabin_id) Values (105, '2017-05-22', 16);
insert into availability (price, date, cabin_id) Values (105, '2017-05-23', 16);
insert into availability (price, date, cabin_id) Values (105, '2017-05-24', 16);
insert into availability (price, date, cabin_id) Values (105, '2017-05-25', 16);
insert into availability (price, date, cabin_id) Values (105, '2017-05-26', 16);

insert into availability (price, date, cabin_id) Values (103, '2017-04-27', 17);
insert into availability (price, date, cabin_id) Values (103, '2017-04-28', 17);
insert into availability (price, date, cabin_id) Values (103, '2017-04-29', 17);
insert into availability (price, date, cabin_id) Values (103, '2017-04-30', 17);
insert into availability (price, date, cabin_id) Values (103, '2017-05-21', 17);
insert into availability (price, date, cabin_id) Values (103, '2017-05-22', 17);
insert into availability (price, date, cabin_id) Values (103, '2017-05-23', 17);
insert into availability (price, date, cabin_id) Values (103, '2017-05-24', 17);
insert into availability (price, date, cabin_id) Values (103, '2017-05-25', 17);
insert into availability (price, date, cabin_id) Values (103, '2017-05-26', 17);

insert into availability (price, date, cabin_id) Values (140, '2017-04-27', 18);
insert into availability (price, date, cabin_id) Values (140, '2017-04-28', 18);
insert into availability (price, date, cabin_id) Values (140, '2017-04-29', 18);
insert into availability (price, date, cabin_id) Values (140, '2017-04-30', 18);
insert into availability (price, date, cabin_id) Values (140, '2017-05-21', 18);
insert into availability (price, date, cabin_id) Values (140, '2017-05-22', 18);
insert into availability (price, date, cabin_id) Values (140, '2017-05-23', 18);
insert into availability (price, date, cabin_id) Values (140, '2017-05-24', 18);
insert into availability (price, date, cabin_id) Values (140, '2017-05-25', 18);
insert into availability (price, date, cabin_id) Values (140, '2017-05-26', 18);

insert into availability (price, date, cabin_id) Values (200, '2017-04-27', 19);
insert into availability (price, date, cabin_id) Values (200, '2017-04-28', 19);
insert into availability (price, date, cabin_id) Values (200, '2017-04-29', 19);
insert into availability (price, date, cabin_id) Values (200, '2017-04-30', 19);
insert into availability (price, date, cabin_id) Values (200, '2017-05-21', 19);
insert into availability (price, date, cabin_id) Values (200, '2017-05-22', 19);
insert into availability (price, date, cabin_id) Values (200, '2017-05-23', 19);
insert into availability (price, date, cabin_id) Values (200, '2017-05-24', 19);
insert into availability (price, date, cabin_id) Values (200, '2017-05-25', 19);
insert into availability (price, date, cabin_id) Values (200, '2017-05-26', 19);

insert into availability (price, date, cabin_id) Values (170, '2017-04-27', 20);
insert into availability (price, date, cabin_id) Values (170, '2017-04-28', 20);
insert into availability (price, date, cabin_id) Values (170, '2017-04-29', 20);
insert into availability (price, date, cabin_id) Values (170, '2017-04-30', 20);
insert into availability (price, date, cabin_id) Values (170, '2017-05-21', 20);
insert into availability (price, date, cabin_id) Values (170, '2017-05-22', 20);
insert into availability (price, date, cabin_id) Values (170, '2017-05-23', 20);
insert into availability (price, date, cabin_id) Values (170, '2017-05-24', 20);
insert into availability (price, date, cabin_id) Values (170, '2017-05-25', 20);
insert into availability (price, date, cabin_id) Values (170, '2017-05-26', 20);

insert into availability (price, date, cabin_id) Values (107, '2017-04-27', 21);
insert into availability (price, date, cabin_id) Values (107, '2017-04-28', 21);
insert into availability (price, date, cabin_id) Values (107, '2017-04-29', 21);
insert into availability (price, date, cabin_id) Values (107, '2017-04-30', 21);
insert into availability (price, date, cabin_id) Values (107, '2017-05-01', 21);
insert into availability (price, date, cabin_id) Values (107, '2017-05-02', 21);
insert into availability (price, date, cabin_id) Values (107, '2017-05-03', 21);
insert into availability (price, date, cabin_id) Values (107, '2017-05-04', 21);
insert into availability (price, date, cabin_id) Values (107, '2017-05-05', 21);
insert into availability (price, date, cabin_id) Values (107, '2017-05-06', 21);
insert into availability (price, date, cabin_id) Values (107, '2017-05-07', 21);
insert into availability (price, date, cabin_id) Values (107, '2017-05-08', 21);
insert into availability (price, date, cabin_id) Values (107, '2017-05-09', 21);
insert into availability (price, date, cabin_id) Values (107, '2017-05-10', 21);
insert into availability (price, date, cabin_id) Values (107, '2017-05-11', 21);
insert into availability (price, date, cabin_id) Values (107, '2017-05-12', 21);
insert into availability (price, date, cabin_id) Values (107, '2017-05-13', 21);
insert into availability (price, date, cabin_id) Values (107, '2017-05-14', 21);
insert into availability (price, date, cabin_id) Values (107, '2017-05-15', 21);
insert into availability (price, date, cabin_id) Values (107, '2017-05-16', 21);
insert into availability (price, date, cabin_id) Values (107, '2017-05-17', 21);
insert into availability (price, date, cabin_id) Values (107, '2017-05-18', 21);
insert into availability (price, date, cabin_id) Values (107, '2017-05-19', 21);
insert into availability (price, date, cabin_id) Values (107, '2017-05-20', 21);
insert into availability (price, date, cabin_id) Values (107, '2017-05-21', 21);
insert into availability (price, date, cabin_id) Values (107, '2017-05-22', 21);
insert into availability (price, date, cabin_id) Values (107, '2017-05-23', 21);
insert into availability (price, date, cabin_id) Values (107, '2017-05-24', 21);
insert into availability (price, date, cabin_id) Values (107, '2017-05-25', 21);
insert into availability (price, date, cabin_id) Values (107, '2017-05-26', 21);
insert into availability (price, date, cabin_id) Values (107, '2017-05-27', 21);
insert into availability (price, date, cabin_id) Values (107, '2017-05-28', 21);
insert into availability (price, date, cabin_id) Values (107, '2017-05-29', 21);
insert into availability (price, date, cabin_id) Values (107, '2017-05-30', 21);
insert into availability (price, date, cabin_id) Values (107, '2017-05-31', 21);

insert into availability (price, date, cabin_id) Values (100, '2017-04-27', 22);
insert into availability (price, date, cabin_id) Values (100, '2017-04-28', 22);
insert into availability (price, date, cabin_id) Values (100, '2017-04-29', 22);
insert into availability (price, date, cabin_id) Values (100, '2017-04-30', 22);
insert into availability (price, date, cabin_id) Values (100, '2017-05-01', 22);
insert into availability (price, date, cabin_id) Values (100, '2017-05-02', 22);
insert into availability (price, date, cabin_id) Values (100, '2017-05-03', 22);
insert into availability (price, date, cabin_id) Values (100, '2017-05-04', 22);
insert into availability (price, date, cabin_id) Values (100, '2017-05-05', 22);
insert into availability (price, date, cabin_id) Values (100, '2017-05-06', 22);
insert into availability (price, date, cabin_id) Values (100, '2017-05-07', 22);
insert into availability (price, date, cabin_id) Values (100, '2017-05-08', 22);
insert into availability (price, date, cabin_id) Values (100, '2017-05-09', 22);
insert into availability (price, date, cabin_id) Values (100, '2017-05-10', 22);
insert into availability (price, date, cabin_id) Values (100, '2017-05-11', 22);
insert into availability (price, date, cabin_id) Values (100, '2017-05-12', 22);
insert into availability (price, date, cabin_id) Values (100, '2017-05-13', 22);
insert into availability (price, date, cabin_id) Values (100, '2017-05-14', 22);
insert into availability (price, date, cabin_id) Values (100, '2017-05-15', 22);
insert into availability (price, date, cabin_id) Values (100, '2017-05-16', 22);
insert into availability (price, date, cabin_id) Values (100, '2017-05-17', 22);
insert into availability (price, date, cabin_id) Values (100, '2017-05-18', 22);
insert into availability (price, date, cabin_id) Values (100, '2017-05-19', 22);
insert into availability (price, date, cabin_id) Values (100, '2017-05-20', 22);
insert into availability (price, date, cabin_id) Values (100, '2017-05-21', 22);
insert into availability (price, date, cabin_id) Values (100, '2017-05-22', 22);
insert into availability (price, date, cabin_id) Values (100, '2017-05-23', 22);
insert into availability (price, date, cabin_id) Values (100, '2017-05-24', 22);
insert into availability (price, date, cabin_id) Values (100, '2017-05-25', 22);
insert into availability (price, date, cabin_id) Values (100, '2017-05-26', 22);
insert into availability (price, date, cabin_id) Values (100, '2017-05-27', 22);
insert into availability (price, date, cabin_id) Values (100, '2017-05-28', 22);
insert into availability (price, date, cabin_id) Values (100, '2017-05-29', 22);
insert into availability (price, date, cabin_id) Values (100, '2017-05-30', 22);
insert into availability (price, date, cabin_id) Values (100, '2017-05-31', 22);

insert into availability (price, date, cabin_id) Values (70, '2017-04-27', 23);
insert into availability (price, date, cabin_id) Values (70, '2017-04-28', 23);
insert into availability (price, date, cabin_id) Values (70, '2017-04-29', 23);
insert into availability (price, date, cabin_id) Values (70, '2017-04-30', 23);
insert into availability (price, date, cabin_id) Values (70, '2017-05-01', 23);
insert into availability (price, date, cabin_id) Values (70, '2017-05-02', 23);
insert into availability (price, date, cabin_id) Values (70, '2017-05-03', 23);
insert into availability (price, date, cabin_id) Values (70, '2017-05-04', 23);
insert into availability (price, date, cabin_id) Values (70, '2017-05-05', 23);
insert into availability (price, date, cabin_id) Values (70, '2017-05-06', 23);
insert into availability (price, date, cabin_id) Values (70, '2017-05-07', 23);
insert into availability (price, date, cabin_id) Values (70, '2017-05-08', 23);
insert into availability (price, date, cabin_id) Values (70, '2017-05-09', 23);
insert into availability (price, date, cabin_id) Values (70, '2017-05-10', 23);
insert into availability (price, date, cabin_id) Values (70, '2017-05-11', 23);
insert into availability (price, date, cabin_id) Values (70, '2017-05-12', 23);
insert into availability (price, date, cabin_id) Values (70, '2017-05-13', 23);
insert into availability (price, date, cabin_id) Values (70, '2017-05-14', 23);
insert into availability (price, date, cabin_id) Values (70, '2017-05-15', 23);
insert into availability (price, date, cabin_id) Values (70, '2017-05-16', 23);
insert into availability (price, date, cabin_id) Values (70, '2017-05-17', 23);
insert into availability (price, date, cabin_id) Values (70, '2017-05-18', 23);
insert into availability (price, date, cabin_id) Values (70, '2017-05-19', 23);
insert into availability (price, date, cabin_id) Values (70, '2017-05-20', 23);
insert into availability (price, date, cabin_id) Values (70, '2017-05-21', 23);
insert into availability (price, date, cabin_id) Values (70, '2017-05-22', 23);
insert into availability (price, date, cabin_id) Values (70, '2017-05-23', 23);
insert into availability (price, date, cabin_id) Values (70, '2017-05-24', 23);
insert into availability (price, date, cabin_id) Values (70, '2017-05-25', 23);
insert into availability (price, date, cabin_id) Values (70, '2017-05-26', 23);
insert into availability (price, date, cabin_id) Values (70, '2017-05-27', 23);
insert into availability (price, date, cabin_id) Values (70, '2017-05-28', 23);
insert into availability (price, date, cabin_id) Values (70, '2017-05-29', 23);
insert into availability (price, date, cabin_id) Values (70, '2017-05-30', 23);
insert into availability (price, date, cabin_id) Values (70, '2017-05-31', 23);

insert into availability (price, date, cabin_id) Values (100, '2017-04-27', 24);
insert into availability (price, date, cabin_id) Values (100, '2017-04-28', 24);
insert into availability (price, date, cabin_id) Values (100, '2017-04-29', 24);
insert into availability (price, date, cabin_id) Values (100, '2017-04-30', 24);
insert into availability (price, date, cabin_id) Values (100, '2017-05-01', 24);
insert into availability (price, date, cabin_id) Values (100, '2017-05-02', 24);
insert into availability (price, date, cabin_id) Values (100, '2017-05-03', 24);
insert into availability (price, date, cabin_id) Values (100, '2017-05-04', 24);
insert into availability (price, date, cabin_id) Values (100, '2017-05-05', 24);
insert into availability (price, date, cabin_id) Values (100, '2017-05-06', 24);
insert into availability (price, date, cabin_id) Values (100, '2017-05-07', 24);
insert into availability (price, date, cabin_id) Values (100, '2017-05-08', 24);
insert into availability (price, date, cabin_id) Values (100, '2017-05-09', 24);
insert into availability (price, date, cabin_id) Values (100, '2017-05-10', 24);
insert into availability (price, date, cabin_id) Values (100, '2017-05-11', 24);
insert into availability (price, date, cabin_id) Values (100, '2017-05-11', 24);
insert into availability (price, date, cabin_id) Values (100, '2017-05-12', 24);
insert into availability (price, date, cabin_id) Values (100, '2017-05-13', 24);
insert into availability (price, date, cabin_id) Values (100, '2017-05-14', 24);
insert into availability (price, date, cabin_id) Values (100, '2017-05-15', 24);
insert into availability (price, date, cabin_id) Values (100, '2017-05-16', 24);
insert into availability (price, date, cabin_id) Values (100, '2017-05-17', 24);
insert into availability (price, date, cabin_id) Values (100, '2017-05-18', 24);
insert into availability (price, date, cabin_id) Values (100, '2017-05-19', 24);
insert into availability (price, date, cabin_id) Values (100, '2017-05-20', 24);
insert into availability (price, date, cabin_id) Values (100, '2017-05-21', 24);
insert into availability (price, date, cabin_id) Values (100, '2017-05-22', 24);
insert into availability (price, date, cabin_id) Values (100, '2017-05-23', 24);
insert into availability (price, date, cabin_id) Values (100, '2017-05-24', 24);
insert into availability (price, date, cabin_id) Values (100, '2017-05-25', 24);
insert into availability (price, date, cabin_id) Values (100, '2017-05-26', 24);
insert into availability (price, date, cabin_id) Values (100, '2017-05-27', 24);
insert into availability (price, date, cabin_id) Values (100, '2017-05-28', 24);
insert into availability (price, date, cabin_id) Values (100, '2017-05-29', 24);
insert into availability (price, date, cabin_id) Values (100, '2017-05-30', 24);
insert into availability (price, date, cabin_id) Values (100, '2017-05-31', 24);

insert into cabin_picture (id, file_path, priority, cabin_id) Values
(1, 'cabinPictures/cabin1_main.jpeg', 1, 1),
(2, 'cabinPictures/cabin1_pic1.jpeg', 0, 1),
(3, 'cabinPictures/cabin1_pic2.jpeg', 0, 1),
(4, 'cabinPictures/cabin1_pic3.jpeg', 0, 1),
(5, 'cabinPictures/cabin1_pic4.jpeg', 0, 1),
(6, 'cabinPictures/cabin2_main.jpeg', 1, 2),
(7, 'cabinPictures/cabin2_pic1.jpeg', 0, 2),
(8, 'cabinPictures/cabin2_pic2.jpeg', 0, 2),
(9, 'cabinPictures/cabin2_pic3.jpeg', 0, 2),
(10, 'cabinPictures/cabin2_pic4.jpeg', 0, 2),
(11, 'cabinPictures/cabin2_pic5.jpeg', 0, 2),
(12, 'cabinPictures/cabin3_main.jpeg', 1, 3),
(13, 'cabinPictures/cabin3_pic1.jpeg', 0, 3),
(14, 'cabinPictures/cabin3_pic2.jpeg', 0, 3),
(15, 'cabinPictures/cabin3_pic3.jpeg', 0, 3),
(16, 'cabinPictures/cabin4_main.jpeg', 1, 4),
(17, 'cabinPictures/cabin4_pic1.jpeg', 0, 4),
(18, 'cabinPictures/cabin4_pic2.jpeg', 0, 4),
(19, 'cabinPictures/cabin4_pic3.jpeg', 0, 4),
(20, 'cabinPictures/cabin4_pic4.jpeg', 0, 4),
(21, 'cabinPictures/cabin4_pic5.jpeg', 0, 4),
(22, 'cabinPictures/cabin5_main.jpeg', 1, 5),
(23, 'cabinPictures/cabin5_pic1.jpeg', 0, 5),
(24, 'cabinPictures/cabin5_pic2.jpeg', 0, 5),
(25, 'cabinPictures/cabin5_pic3.jpeg', 0, 5),
(26, 'cabinPictures/cabin5_pic4.jpeg', 0, 5),
(27, 'cabinPictures/cabin5_pic5.jpeg', 0, 5),
(28, 'cabinPictures/cabin5_pic6.jpeg', 0, 5),
(29, 'cabinPictures/cabin5_pic7.jpeg', 0, 5),
(30, 'cabinPictures/cabin5_pic8.jpeg', 0, 5),
(31, 'cabinPictures/cabin6_main.jpeg', 1, 6),
(32, 'cabinPictures/cabin6_pic1.jpeg', 0, 6),
(33, 'cabinPictures/cabin6_pic2.jpeg', 0, 6),
(34, 'cabinPictures/cabin6_pic3.jpeg', 0, 6),
(35, 'cabinPictures/cabin6_pic4.jpeg', 0, 6),
(36, 'cabinPictures/cabin6_pic5.jpeg', 0, 6),
(37, 'cabinPictures/cabin6_pic6.jpeg', 0, 6),
(38, 'cabinPictures/cabin6_pic7.jpeg', 0, 6),
(39, 'cabinPictures/cabin7_main.jpeg', 1, 7),
(40, 'cabinPictures/cabin7_pic1.jpeg', 0, 7),
(41, 'cabinPictures/cabin7_pic2.jpeg', 0, 7),
(42, 'cabinPictures/cabin7_pic3.jpeg', 0, 7),
(43, 'cabinPictures/cabin7_pic4.jpeg', 0, 7),
(44, 'cabinPictures/cabin7_pic5.jpeg', 0, 7),
(45, 'cabinPictures/cabin8_main.jpeg', 1, 8),
(46, 'cabinPictures/cabin8_pic1.jpeg', 0, 8),
(47, 'cabinPictures/cabin8_pic2.jpeg', 0, 8),
(48, 'cabinPictures/cabin8_pic3.jpeg', 0, 8),
(49, 'cabinPictures/cabin8_pic4.jpeg', 0, 8),
(50, 'cabinPictures/cabin8_pic5.jpeg', 0, 8),
(51, 'cabinPictures/cabin8_pic6.jpeg', 0, 8),
(52, 'cabinPictures/cabin8_pic7.jpeg', 0, 8),
(53, 'cabinPictures/cabin8_pic8.jpeg', 0, 8),
(54, 'cabinPictures/cabin9_main.jpeg', 1, 9),
(55, 'cabinPictures/cabin9_pic1.jpeg', 0, 9),
(56, 'cabinPictures/cabin9_pic2.jpeg', 0, 9),
(57, 'cabinPictures/cabin9_pic3.jpeg', 0, 9),
(58, 'cabinPictures/cabin9_pic4.jpeg', 0, 9),
(59, 'cabinPictures/cabin9_pic5.jpeg', 0, 9),
(60, 'cabinPictures/cabin9_pic6.jpeg', 0, 9),
(61, 'cabinPictures/cabin10_main.jpeg', 1, 10),
(62, 'cabinPictures/cabin11_main.jpeg', 1, 11),
(63, 'cabinPictures/cabin12_main.jpeg', 1, 12),
(64, 'cabinPictures/cabin13_main.jpeg', 1, 13),
(65, 'cabinPictures/cabin14_main.jpeg', 1, 14),
(66, 'cabinPictures/cabin15_main.jpeg', 1, 15),
(67, 'cabinPictures/cabin16_main.jpeg', 1, 16),
(68, 'cabinPictures/cabin17_main.jpeg', 1, 17),
(69, 'cabinPictures/cabin18_main.jpeg', 1, 18),
(70, 'cabinPictures/cabin19_main.jpeg', 1, 19),
(71, 'cabinPictures/cabin20_main.jpeg', 1, 20),
(72, 'cabinPictures/cabin21_main.jpeg', 1, 21),
(73, 'cabinPictures/cabin22_main.jpeg', 1, 22),
(74, 'cabinPictures/cabin23_main.jpeg', 1, 23),
(75, 'cabinPictures/cabin24_main.jpeg', 1, 24);
