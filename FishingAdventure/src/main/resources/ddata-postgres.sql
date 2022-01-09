--
-- Data for Name: additional_service; Type: TABLE DATA; Schema: public; Owner: postgres
--
INSERT INTO additional_service
VALUES (101, 'TV cable', 2);
INSERT INTO additional_service
VALUES (102, 'Air conditioner', 2);
INSERT INTO additional_service
VALUES (103, 'Parking', 2);
INSERT INTO additional_service
VALUES (104, 'Mini bar', 5);


--
-- Data for Name: address; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO address
VALUES (101, 'Michigan', 'USA', '5023 N Mason Rd Merrill', 65717);
INSERT INTO address
VALUES (102, 'Iowa', 'USA', '19043 477th St Mystic', 52240);
INSERT INTO address
VALUES (103, 'Missouri', 'USA', '3202 Cotton Hollow Rd Norwood', 63376);
INSERT INTO address
VALUES (104, 'Maryland', 'USA', '113 Columbia St Cumberland', 21502);
INSERT INTO address
VALUES (105, 'Georgia', 'USA', '11555 Ga 296 Hwy Stapleton', 30823);
INSERT INTO address
VALUES (106, 'California', 'USA', '7530 Eucalyptus Hl La Mesa', 91942);
INSERT INTO address
VALUES (107, 'Ohio', 'USA', '4244 Oak St Portsmouth', 45662);
INSERT INTO address
VALUES (108, 'New York', 'USA', '786 Starlight Rd Monticello', 12701);
INSERT INTO address
VALUES (109, 'West Virginia', 'USA', '117 5th Ave Montgomery', 25136);
INSERT INTO address
VALUES (110, 'Texas', 'USA', '1303 Mill St Gatesville', 76528);


--
-- Data for Name: location; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO location
VALUES (101, 43.36570497356441, -84.39013327190807, 101);
INSERT INTO location
VALUES (102, 40.78633152583986, -92.92523800843121, 102);
INSERT INTO location
VALUES (103, 37.18116303269594, -92.44589345275644, 103);
INSERT INTO location
VALUES (104, 39.656673718531984, -78.76215358922337, 104);
INSERT INTO location
VALUES (105, 33.203423087217345, -82.46834757407572, 105);
INSERT INTO location
VALUES (106, 32.76353103123202, -117.03570268758152, 106);
INSERT INTO location
VALUES (107, 38.75878534057671, -82.93150086041696, 107);
INSERT INTO location
VALUES (108, 41.647718520799145, -74.7783140738154, 108);
INSERT INTO location
VALUES (109, 38.18147925034275, -81.31890907577667, 109);
INSERT INTO location
VALUES (110, 31.439867194681277, -97.74321707227516, 110);



--
-- Data for Name: appointment_chosen_services; Type: TABLE DATA; Schema: public; Owner: postgres
--


--
-- Data for Name: appointment_services; Type: TABLE DATA; Schema: public; Owner: postgres
--


--
-- Data for Name: boat_fishing_equipment; Type: TABLE DATA; Schema: public; Owner: postgres
--


--
-- Data for Name: boat_navigation_equipment; Type: TABLE DATA; Schema: public; Owner: postgres
--

-- Data for Name: fishing_equipment; Type: TABLE DATA; Schema: public; Owner: postgres
--
INSERT INTO fishing_equipment
VALUES (101, 'Fishing rod');
INSERT INTO fishing_equipment
VALUES (102, 'Fishing net');
INSERT INTO fishing_equipment
VALUES (103, 'Small fishing set');



--
-- Data for Name: navigation_equipment; Type: TABLE DATA; Schema: public; Owner: postgres
--
INSERT INTO navigation_equipment
VALUES (101, 'GPS');
INSERT INTO navigation_equipment
VALUES (102, 'Fish finder');



--
-- Data for Name: user_category; Type: TABLE DATA; Schema: public; Owner: postgres
--
INSERT INTO user_category
VALUES (1, 'REGULAR', 0, 0);
INSERT INTO user_category
VALUES (2, 'SILVER', 0, 0);
INSERT INTO user_category
VALUES (3, 'GOLD', 0, 0);


--
-- Data for Name: role; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO role
VALUES (1, 'ROLE_VACATION_HOME_OWNER');
INSERT INTO role
VALUES (2, 'ROLE_CLIENT');
INSERT INTO role
VALUES (3, 'ROLE_FISHING_INSTRUCTOR');
INSERT INTO role
VALUES (4, 'ROLE_ADMIN');
INSERT INTO role
VALUES (5, 'ROLE_BOAT_OWNER');



--
-- Data for Name: admin; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO admin
VALUES (113, true, '', 'admin1@gmail.com', false, NULL, 'Natalie',
        '$2a$10$jE9Jzh8T5l/xn92gu6pfOOC/Y4tVRZNruktQFMM13FOSwxYuvSgc2', '608-216-4921', 0, 'Jones', 101, NULL, 4);
INSERT INTO admin
VALUES (114, true, '', 'admin2@gmail.com', false, NULL, 'Billy',
        '$2a$10$jE9Jzh8T5l/xn92gu6pfOOC/Y4tVRZNruktQFMM13FOSwxYuvSgc2', '608-216-4921', 0, 'Morales', 102, NULL, 4);
INSERT INTO admin
VALUES (115, true, '', 'admin3@gmail.com', false, NULL, 'Toby',
        '$2a$10$jE9Jzh8T5l/xn92gu6pfOOC/Y4tVRZNruktQFMM13FOSwxYuvSgc2', '608-216-4921', 0, 'Dale', 108, NULL, 4);


--
-- Data for Name: boat_owner; Type: TABLE DATA; Schema: public; Owner: postgres
--
INSERT INTO boat_owner
VALUES (110, true, '', 'boatOwner1@gmail.com', false, NULL, 'Samuel',
        '$2a$10$jE9Jzh8T5l/xn92gu6pfOOC/Y4tVRZNruktQFMM13FOSwxYuvSgc2', '608-216-4921', 0, 'Bull', 110, NULL, 5);
INSERT INTO boat_owner
VALUES (111, true, '', 'boatOwner2@gmail.com', false, NULL, 'Zakk',
        '$2a$10$jE9Jzh8T5l/xn92gu6pfOOC/Y4tVRZNruktQFMM13FOSwxYuvSgc2', '608-216-4921', 0, 'Morales', 103, NULL, 5);
INSERT INTO boat_owner
VALUES (112, true, '', 'boatOwner3@gmail.com', false, NULL, 'Toby',
        '$2a$10$jE9Jzh8T5l/xn92gu6pfOOC/Y4tVRZNruktQFMM13FOSwxYuvSgc2', '608-216-4921', 0, 'Puckett', 106, NULL, 5);



--
-- Data for Name: boat; Type: TABLE DATA; Schema: public; Owner: postgres
--
INSERT INTO boat
VALUES (113, 0,
        'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry''s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.',
        false, 'Legacy', 2, 50.0, 4.58, 102, 10, 1000, 158785, 10, 'type', 110);
INSERT INTO boat
VALUES (114, 0,
        'It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.',
        false, 'Big Nauti', 2, 50.0, 3.68, 108, 10, 1000, 158755, 10, 'type', 110);
INSERT INTO boat
VALUES (115, 0,
        'Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old.',
        false, 'Chaste', 2, 50.0, 2.91, 109, 10, 1000, 578785, 10, 'type', 111);
INSERT INTO boat
VALUES (116, 0,
        'Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source.',
        false, 'Uptown Girl', 2, 50.0, 4.33, 106, 10, 1000, 666785, 10, 'type', 111);
INSERT INTO boat
VALUES (117, 0,
        'Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of "de Finibus Bonorum et Malorum" (The Extremes of Good and Evil) by Cicero, written in 45 BC. This book is a treatise on the theory of ethics, very popular during the Renaissance.',
        false, 'Big Top', 2, 50.0, 3.97, 107, 10, 1000, 958885, 10, 'type', 112);
INSERT INTO boat
VALUES (118, 0,
        'It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using Content here, content here.',
        false, 'Wildcard', 2, 50.0, 3.85, 103, 10, 1000, 178685, 10, 'type', 112);


--
-- Data for Name: boat_navigation_equipment; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO boat_navigation_equipment
VALUES (113, 101);
INSERT INTO boat_navigation_equipment
VALUES (113, 102);


--
-- Data for Name: boat_fishing_equipment; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO boat_fishing_equipment
VALUES (113, 101);
INSERT INTO boat_fishing_equipment
VALUES (113, 102);
INSERT INTO boat_fishing_equipment
VALUES (113, 103);


--
-- Data for Name: client; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO client
VALUES (104, true, '', 'client1@gmail.com', false, NULL, 'Terrence',
        '$2a$10$jE9Jzh8T5l/xn92gu6pfOOC/Y4tVRZNruktQFMM13FOSwxYuvSgc2', '731-214-6745', 0, 'Welsh', 101, 1, 2, 0);
INSERT INTO client
VALUES (105, true, '', 'client2@gmail.com', false, NULL, 'Susan',
        '$2a$10$jE9Jzh8T5l/xn92gu6pfOOC/Y4tVRZNruktQFMM13FOSwxYuvSgc2', '301-854-6603', 0, 'Foster', 105, 1, 2, 0);
INSERT INTO client
VALUES (106, true, '', 'client3@gmail.com', false, NULL, 'Chad',
        '$2a$10$jE9Jzh8T5l/xn92gu6pfOOC/Y4tVRZNruktQFMM13FOSwxYuvSgc2', '718-259-1288', 0, 'Mill', 109, 1, 2, 0);
--
-- Data for Name: client_subscriptions; Type: TABLE DATA; Schema: public; Owner: postgres
--


--
-- Data for Name: confirmation_token; Type: TABLE DATA; Schema: public; Owner: postgres
--

--
-- Data for Name: fishing_instructor; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO fishing_instructor
VALUES (107, true,
        'There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don''t look even slightly believable.',
        'instructor1@gmail.com', false, NULL, 'Niko', '$2a$10$jE9Jzh8T5l/xn92gu6pfOOC/Y4tVRZNruktQFMM13FOSwxYuvSgc2',
        '347-668-3901', 0, 'Moore', 105, NULL, 3);
INSERT INTO fishing_instructor
VALUES (108, true,
        'There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don''t look even slightly believable.',
        'instructor2@gmail.com', false, NULL, 'Olivia', '$2a$10$jE9Jzh8T5l/xn92gu6pfOOC/Y4tVRZNruktQFMM13FOSwxYuvSgc2',
        '310-496-6362', 0, 'Wolf', 102, NULL, 3);
INSERT INTO fishing_instructor
VALUES (109, true,
        'There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don''t look even slightly believable.',
        'instructor3@gmail.com', false, NULL, 'Samuel', '$2a$10$jE9Jzh8T5l/xn92gu6pfOOC/Y4tVRZNruktQFMM13FOSwxYuvSgc2',
        '608-216-4921', 0, 'Crossley', 110, NULL, 3);


--
-- Data for Name: fishing_adventure; Type: TABLE DATA; Schema: public; Owner: postgres
--
INSERT INTO fishing_adventure
VALUES (107, 0,
        'All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet.',
        false, 'Tahoe adventure', 2, 50.0, 2.58, 108, 7200000000000, 107);
INSERT INTO fishing_adventure
VALUES (108, 0,
        'The first line of Lorem Ipsum, "Lorem ipsum dolor sit amet..", comes from a line in section 1.10.32.',
        false, 'Nevada adventure', 2, 50.0, 4.58, 105, 5400000000000, 107);
INSERT INTO fishing_adventure
VALUES (109, 0,
        'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry''s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.',
        false, 'Oregon adventure', 2, 50.0, 3.54, 102, 7200000000000,108);
INSERT INTO fishing_adventure
VALUES (110, 0,
        'Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old.',
        false, 'Marion and Moultrie adventure', 2, 50.0, 4.98, 109, 7200000000000, 108);
INSERT INTO fishing_adventure
VALUES (111, 0,
        'Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for lorem ipsum will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).',
        false, 'Okeechobee adventure', 2, 50.0, 2.13, 103, 5400000000000, 109);
INSERT INTO fishing_adventure
VALUES (112, 0,
        'There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don''t look even slightly believable.',
        false, 'Hartwell adventure', 2, 50.0, 3.38, 104, 7200000000000, 109);



--
-- Data for Name: fishing_eq_in_boat; Type: TABLE DATA; Schema: public; Owner: postgres
--


--
-- Data for Name: image; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO image
VALUES (101, true, 'c1.jpg');
INSERT INTO image
VALUES (102, true, 'c3.jpg');
INSERT INTO image
VALUES (103, true, 'c11.jpg');
INSERT INTO image
VALUES (104, true, 'c12.jpg');
INSERT INTO image
VALUES (105, true, 'c13.jpg');
INSERT INTO image
VALUES (106, true, 'c14.jpg');
INSERT INTO image
VALUES (107, true, 'fa5.jpg');
INSERT INTO image
VALUES (108, true, 'fa6.jpg');
INSERT INTO image
VALUES (109, true, 'fa7.jpg');
INSERT INTO image
VALUES (110, true, 'fa8.jpg');
INSERT INTO image
VALUES (111, true, 'fa11.jpg');
INSERT INTO image
VALUES (112, true, 'fa12.jpg');
INSERT INTO image
VALUES (113, true, 'b10.jpg');
INSERT INTO image
VALUES (114, true, 'b1.jpg');
INSERT INTO image
VALUES (115, true, 'b2.jpg');
INSERT INTO image
VALUES (116, true, 'b3.jpg');
INSERT INTO image
VALUES (117, true, 'b5.jpg');
INSERT INTO image
VALUES (118, true, 'b6.jpg');
INSERT INTO image
VALUES (119, false, 'c16.jpg');
INSERT INTO image
VALUES (120, false, 'c16-2.jpg');
INSERT INTO image
VALUES (121, false, 'c16-3.jpg');
INSERT INTO image
VALUES (122, false, 'c16-4.jpg');
INSERT INTO image
VALUES (123, false, 'c16-5.jpg');



--
-- Data for Name: navigation_eq_in_boat; Type: TABLE DATA; Schema: public; Owner: postgres
--


--
-- Data for Name: reservation_report; Type: TABLE DATA; Schema: public; Owner: postgres
--


--
-- Data for Name: review; Type: TABLE DATA; Schema: public; Owner: postgres
--


--
-- Data for Name: room; Type: TABLE DATA; Schema: public; Owner: postgres
--


--
-- Data for Name: rule; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO rule
VALUES (101, 'Pets', true);
INSERT INTO rule
VALUES (102, 'No smoking', false);
INSERT INTO rule
VALUES (103, 'No littering', false);


--
-- Data for Name: rules_in_service_profile; Type: TABLE DATA; Schema: public; Owner: postgres
--


--
-- Data for Name: service_profile_additional_services; Type: TABLE DATA; Schema: public; Owner: postgres
--
INSERT INTO service_profile_additional_services
VALUES (102, 101);
INSERT INTO service_profile_additional_services
VALUES (102, 102);
INSERT INTO service_profile_additional_services
VALUES (102, 103);
INSERT INTO service_profile_additional_services
VALUES (113, 104);



--
-- Data for Name: service_profile_images; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO service_profile_images
VALUES (102, 102);
INSERT INTO service_profile_images
VALUES (102, 120);
INSERT INTO service_profile_images
VALUES (102, 121);
INSERT INTO service_profile_images
VALUES (102, 122);
INSERT INTO service_profile_images
VALUES (102, 123);
INSERT INTO service_profile_images
VALUES (101, 101);
INSERT INTO service_profile_images
VALUES (103, 103);
INSERT INTO service_profile_images
VALUES (104, 104);
INSERT INTO service_profile_images
VALUES (105, 105);
INSERT INTO service_profile_images
VALUES (106, 106);
INSERT INTO service_profile_images
VALUES (107, 107);
INSERT INTO service_profile_images
VALUES (108, 108);
INSERT INTO service_profile_images
VALUES (109, 109);
INSERT INTO service_profile_images
VALUES (110, 110);
INSERT INTO service_profile_images
VALUES (111, 111);
INSERT INTO service_profile_images
VALUES (112, 112);
INSERT INTO service_profile_images
VALUES (113, 113);
INSERT INTO service_profile_images
VALUES (114, 114);
INSERT INTO service_profile_images
VALUES (115, 115);
INSERT INTO service_profile_images
VALUES (116, 116);
INSERT INTO service_profile_images
VALUES (117, 117);
INSERT INTO service_profile_images
VALUES (118, 118);


--
-- Data for Name: service_profile_rules; Type: TABLE DATA; Schema: public; Owner: postgres
--
INSERT INTO service_profile_rules
VALUES (102, 101);
INSERT INTO service_profile_rules
VALUES (102, 102);
INSERT INTO service_profile_rules
VALUES (102, 103);
INSERT INTO service_profile_rules
VALUES (113, 103);



--
-- Data for Name: vacation_home_owner; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO vacation_home_owner
VALUES (101, true, '', 'homeOwner1@gmail.com', false, NULL, 'Marisa',
        '$2a$10$jE9Jzh8T5l/xn92gu6pfOOC/Y4tVRZNruktQFMM13FOSwxYuvSgc2', '704-249-0886', 0, 'Car', 104, NULL, 1);
INSERT INTO vacation_home_owner
VALUES (102, true, '', 'homeOwner2@gmail.com', false, NULL, 'Kie',
        '$2a$10$jE9Jzh8T5l/xn92gu6pfOOC/Y4tVRZNruktQFMM13FOSwxYuvSgc2', '859-486-5125', 0, 'Mathews', 105, NULL, 1);
INSERT INTO vacation_home_owner
VALUES (103, true, '', 'homeOwner3@gmail.com', false, NULL, 'Beverly',
        '$2a$10$jE9Jzh8T5l/xn92gu6pfOOC/Y4tVRZNruktQFMM13FOSwxYuvSgc2', '316-302-5432', 0, 'Barnard', 106, NULL, 1);


--
-- Data for Name: vacation_home; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO vacation_home
VALUES (101, 0,
        ' Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).',
        false, 'Lakeview Cottage', 2, 50.0, 3.89, 101, 101);
INSERT INTO vacation_home
VALUES (102, 0,
        ' Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).',
        false, 'Villa Madam', 2, 50.0, 4.5, 102, 101);
INSERT INTO vacation_home
VALUES (103, 0,
        ' Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).',
        false, 'Winter Acres', 2, 50.0, 3.25, 107, 102);
INSERT INTO vacation_home
VALUES (104, 0,
        ' Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).',
        false, 'Eagle Cascades', 2, 50.0, 2.5, 108, 102);
INSERT INTO vacation_home
VALUES (105, 0,
        ' Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).',
        false, 'Green Palace', 2, 50.0, 4.92, 109, 103);
INSERT INTO vacation_home
VALUES (106, 0,
        ' Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).',
        false, 'Coast House', 2, 50.0, 1.5, 110, 103);


--
-- Data for Name: vacation_home_rooms; Type: TABLE DATA; Schema: public; Owner: postgres
--


--
-- Name: additional_service_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--


--
-- Name: availability_date_range; Type: SEQUENCE SET; Schema: public; Owner: postgres
--
INSERT INTO availability_date_range
VALUES (101, '2022-01-15 20:11:52.452', '2022-01-03 20:11:52.452', 101);
INSERT INTO availability_date_range
VALUES (102, '2022-01-15 20:11:52.452', '2022-01-03 20:11:52.452', 102);
INSERT INTO availability_date_range
VALUES (103, '2022-01-15 20:11:52.452', '2022-01-03 20:11:52.452', 103);
INSERT INTO availability_date_range
VALUES (104, '2022-01-15 20:11:52.452', '2022-01-03 20:11:52.452', 104);
INSERT INTO availability_date_range
VALUES (105, '2022-01-15 20:11:52.452', '2022-01-03 20:11:52.452', 105);
INSERT INTO availability_date_range
VALUES (106, '2022-01-15 20:11:52.452', '2022-01-03 20:11:52.452', 106);
INSERT INTO availability_date_range
VALUES (107, '2022-01-15 20:11:52.452', '2022-01-03 20:11:52.452', 107);
INSERT INTO availability_date_range
VALUES (108, '2022-01-15 20:11:52.452', '2022-01-03 20:11:52.452', 108);
INSERT INTO availability_date_range
VALUES (109, '2022-01-15 20:11:52.452', '2022-01-03 20:11:52.452', 109);
INSERT INTO availability_date_range
VALUES (110, '2022-01-15 20:11:52.452', '2022-01-03 20:11:52.452', 110);
INSERT INTO availability_date_range
VALUES (111, '2022-01-15 20:11:52.452', '2022-01-03 20:11:52.452', 111);
INSERT INTO availability_date_range
VALUES (112, '2022-01-15 20:11:52.452', '2022-01-03 20:11:52.452', 112);
INSERT INTO availability_date_range
VALUES (113, '2022-01-15 20:11:52.452', '2022-01-03 20:11:52.452', 113);
INSERT INTO availability_date_range
VALUES (114, '2022-01-15 20:11:52.452', '2022-01-03 20:11:52.452', 114);
INSERT INTO availability_date_range
VALUES (115, '2022-01-15 20:11:52.452', '2022-01-03 20:11:52.452', 115);
INSERT INTO availability_date_range
VALUES (116, '2022-01-15 20:11:52.452', '2022-01-03 20:11:52.452', 116);
INSERT INTO availability_date_range
VALUES (117, '2022-01-15 20:11:52.452', '2022-01-03 20:11:52.452', 117);
INSERT INTO availability_date_range
VALUES (118, '2022-01-15 20:11:52.452', '2022-01-03 20:11:52.452', 118);

--
-- Data for Name: subscriptions; Type: TABLE DATA; Schema: public; Owner: postgres
--
INSERT INTO client_subscriptions
VALUES (104, 101);
INSERT INTO client_subscriptions
VALUES (104, 113);
INSERT INTO client_subscriptions
VALUES (104, 107);

--
-- Data for Name: appointment; Type: TABLE DATA; Schema: public; Owner: postgres
--
INSERT INTO appointment
VALUES (101, '2022-01-07 15:44:00', 0, 604779299000000000, '2022-01-07 15:44:00', true, 2, false, '', 50,
        '2022-01-05 15:44:00');
INSERT INTO appointment
VALUES (102, '2022-01-09 15:44:00', 0, 604779299000000000, '2022-01-09 15:44:00', true, 2, false, '', 50,
        '2022-01-07 15:44:00');
INSERT INTO appointment
VALUES (103, '2022-01-11 15:44:00', 0, 604779299000000000, '2022-01-11 15:44:00', true, 2, false, '', 50,
        '2022-01-09 15:44:00');
INSERT INTO appointment
VALUES (104, '2022-01-25 15:44:00', 10, 604779299000000000, '2022-01-13 15:44:00', false, 2, false, '', 44,
        '2022-01-11 15:44:00');
INSERT INTO appointment
VALUES (105, '2022-01-05 15:43:20.716', 20, 604779299000000000, '2022-01-15 15:43:00', false, 2, false, '', 40,
        '2022-01-14 15:43:00');

--
-- Data for Name: service_profile_appointments; Type: TABLE DATA; Schema: public; Owner: postgres
--
INSERT INTO service_profile_appointments
VALUES (101, 101);
INSERT INTO service_profile_appointments
VALUES (107, 102);
INSERT INTO service_profile_appointments
VALUES (113, 103);
INSERT INTO service_profile_appointments
VALUES (101, 104);
INSERT INTO service_profile_appointments
VALUES (113, 105);


--
-- Data for Name: reservation; Type: TABLE DATA; Schema: public; Owner: postgres
--
INSERT INTO reservation
VALUES (101, false, 101, 104, null);
INSERT INTO reservation
VALUES (102, false, 102, 104, null);
INSERT INTO reservation
VALUES (103, false, 103, 104, null);

--
-- Data for Name: delete_request; Type: TABLE DATA; Schema: public; Owner: postgres
--
INSERT INTO delete_request
VALUES (101, '2022-01-05 15:43:20.716', 'client3@gmail.com', 'Not interested in new adventures anymore.', false,
        'ROLE_CLIENT');
