use support;
INSERT INTO Topic (`id`, `name`) VALUES ('1', 'Other');
INSERT INTO Topic (`id`, `name`) VALUES ('2', 'Problem with connect');
INSERT INTO Topic (`id`, `name`) VALUES ('3', 'Problem with wi-fi');
INSERT INTO Inquiry (`id`, `create_date`, `customer_name`, `description`,`topic_id`) VALUES ('1', '2016-07-10 19:32:49', 'Ivanov Ivan', 'I have a problem with wi-fi', '3');
INSERT INTO Inquiry (`id`, `create_date`, `customer_name`, `description`,`topic_id`) VALUES ('2', '2016-07-12 18:52:29', 'Petrov Petr', 'I have a problem with connect', '2');
INSERT INTO Inquiry_attributes (`inquiry_id`, `attributes`) VALUES ('1', 'Other');
INSERT INTO Inquiry_attributes (`inquiry_id`, `attributes`) VALUES ('2', 'Siemens A70');