INSERT INTO public.coffeeshop (id, shop_name, latitude, longitude, address, created_at, modified_at, active) VALUES
(uuid_generate_v4(), 'Bean Brew', 21.025191514501138, 105.78820768205858, '123 Main St, New York, NY ~ 2km', now(), now(), true),
(uuid_generate_v4(), 'Cafe Delights', 21.038155409612806, 105.7835170125348, '456 Elm St, Los Angeles ~ 3km' , now(), now(), true),
(uuid_generate_v4(), 'The Coffee Spot', 21.037632004604696, 105.8346789185189, '101 Maple Ave, San Francisco ~ 7km', now(), now(), true);

INSERT INTO public.menu (id, shop_id, menu_name, created_at, modified_at, active)
VALUES
  (uuid_generate_v4(), (SELECT id FROM public.coffeeshop WHERE shop_name = 'Bean Brew' LIMIT 1), 'Breakfast Menu', now(), NULL, TRUE),
  (uuid_generate_v4(), (SELECT id FROM public.coffeeshop WHERE shop_name = 'Bean Brew' LIMIT 1), 'Lunch Menu', now(), NULL, TRUE),
  (uuid_generate_v4(), (SELECT id FROM public.coffeeshop WHERE shop_name = 'Cafe Delights' LIMIT 1), 'Lunch Specials', now(), NULL, TRUE),
  (uuid_generate_v4(), (SELECT id FROM public.coffeeshop WHERE shop_name = 'Cafe Delights' LIMIT 1), 'Dinner Menu', now(), NULL, TRUE),
  (uuid_generate_v4(), (SELECT id FROM public.coffeeshop WHERE shop_name = 'Cafe Delights' LIMIT 1), 'Dessert Menu', now(), NULL, TRUE),
  (uuid_generate_v4(), (SELECT id FROM public.coffeeshop WHERE shop_name = 'The Coffee Spot' LIMIT 1), 'Seasonal Menu', now(), NULL, FALSE);

-- Items for Breakfast Menu
INSERT INTO public.menu_item (id, menu_id, item_name, description, price, created_at, modified_at, active)
VALUES
  (uuid_generate_v4(), (SELECT id FROM public.menu WHERE menu_name = 'Breakfast Menu' LIMIT 1), 'French Toast', 'Sweet French toast with berries', 8.49, now(), NULL, TRUE),
  (uuid_generate_v4(), (SELECT id FROM public.menu WHERE menu_name = 'Breakfast Menu' LIMIT 1), 'Breakfast Burrito', 'Eggs, sausage, and cheese in a tortilla', 9.49, now(), NULL, TRUE),
  (uuid_generate_v4(), (SELECT id FROM public.menu WHERE menu_name = 'Breakfast Menu' LIMIT 1), 'Smoothie Bowl', 'Fruit smoothie topped with granola and nuts', 7.99, now(), NULL, TRUE),
  (uuid_generate_v4(), (SELECT id FROM public.menu WHERE menu_name = 'Breakfast Menu' LIMIT 1), 'Bagel with Lox', 'Bagel with smoked salmon and cream cheese', 10.49, now(), NULL, TRUE);

-- Items for Lunch Menu
INSERT INTO public.menu_item (id, menu_id, item_name, description, price, created_at, modified_at, active)
VALUES
  (uuid_generate_v4(), (SELECT id FROM public.menu WHERE menu_name = 'Lunch Menu' LIMIT 1), 'Tomato Basil Soup', 'Fresh tomato soup with basil', 5.99, now(), NULL, TRUE),
  (uuid_generate_v4(), (SELECT id FROM public.menu WHERE menu_name = 'Lunch Menu' LIMIT 1), 'BLT Sandwich', 'Bacon, lettuce, and tomato on toasted bread', 8.99, now(), NULL, TRUE),
  (uuid_generate_v4(), (SELECT id FROM public.menu WHERE menu_name = 'Lunch Menu' LIMIT 1), 'Chicken Caesar Wrap', 'Grilled chicken with Caesar dressing in a wrap', 9.49, now(), NULL, TRUE),
  (uuid_generate_v4(), (SELECT id FROM public.menu WHERE menu_name = 'Lunch Menu' LIMIT 1), 'Veggie Burger', 'Vegetarian burger with all the fixings', 10.99, now(), NULL, TRUE);

-- Items for Dinner Menu
INSERT INTO public.menu_item (id, menu_id, item_name, description, price, created_at, modified_at, active)
VALUES
  (uuid_generate_v4(), (SELECT id FROM public.menu WHERE menu_name = 'Dinner Menu' LIMIT 1), 'Spaghetti Carbonara', 'Pasta with creamy carbonara sauce and pancetta', 12.99, now(), NULL, TRUE),
  (uuid_generate_v4(), (SELECT id FROM public.menu WHERE menu_name = 'Dinner Menu' LIMIT 1), 'Grilled Salmon', 'Salmon fillet grilled to perfection with lemon', 14.49, now(), NULL, TRUE),
  (uuid_generate_v4(), (SELECT id FROM public.menu WHERE menu_name = 'Dinner Menu' LIMIT 1), 'Beef Stroganoff', 'Tender beef with a creamy mushroom sauce', 15.99, now(), NULL, TRUE),
  (uuid_generate_v4(), (SELECT id FROM public.menu WHERE menu_name = 'Dinner Menu' LIMIT 1), 'Vegetable Stir-Fry', 'Mixed vegetables stir-fried with soy sauce', 11.49, now(), NULL, TRUE);

-- Items for Dessert Menu
INSERT INTO public.menu_item (id, menu_id, item_name, description, price, created_at, modified_at, active)
VALUES
  (uuid_generate_v4(), (SELECT id FROM public.menu WHERE menu_name = 'Dessert Menu' LIMIT 1), 'Cheesecake', 'Creamy cheesecake with a graham cracker crust', 6.49, now(), NULL, TRUE),
  (uuid_generate_v4(), (SELECT id FROM public.menu WHERE menu_name = 'Dessert Menu' LIMIT 1), 'Apple Pie', 'Warm apple pie with a flaky crust', 5.99, now(), NULL, TRUE),
  (uuid_generate_v4(), (SELECT id FROM public.menu WHERE menu_name = 'Dessert Menu' LIMIT 1), 'Tiramisu', 'Classic Italian dessert with coffee and mascarpone', 7.99, now(), NULL, TRUE),
  (uuid_generate_v4(), (SELECT id FROM public.menu WHERE menu_name = 'Dessert Menu' LIMIT 1), 'Brownie Sundae', 'Chocolate brownie topped with ice cream and fudge', 8.49, now(), NULL, TRUE);

-- Items for Lunch Specials
INSERT INTO public.menu_item (id, menu_id, item_name, description, price, created_at, modified_at, active)
VALUES
  (uuid_generate_v4(), (SELECT id FROM public.menu WHERE menu_name = 'Lunch Specials' LIMIT 1), 'Philly Cheesesteak', 'Tender steak with melted cheese in a hoagie roll', 11.49, now(), NULL, TRUE),
  (uuid_generate_v4(), (SELECT id FROM public.menu WHERE menu_name = 'Lunch Specials' LIMIT 1), 'Chicken Quesadilla', 'Chicken and cheese in a crispy tortilla', 9.99, now(), NULL, TRUE),
  (uuid_generate_v4(), (SELECT id FROM public.menu WHERE menu_name = 'Lunch Specials' LIMIT 1), 'Greek Salad', 'Salad with olives, feta, and cucumbers', 7.99, now(), NULL, TRUE),
  (uuid_generate_v4(), (SELECT id FROM public.menu WHERE menu_name = 'Lunch Specials' LIMIT 1), 'Sloppy Joes', 'Ground beef in a tangy sauce on a bun', 8.49, now(), NULL, TRUE);

-- Items for Seasonal Menu
INSERT INTO public.menu_item (id, menu_id, item_name, description, price, created_at, modified_at, active)
VALUES
  (uuid_generate_v4(), (SELECT id FROM public.menu WHERE menu_name = 'Seasonal Menu' LIMIT 1), 'Pumpkin Spice Latte', 'Seasonal latte with pumpkin spice', 5.49, now(), NULL, TRUE),
  (uuid_generate_v4(), (SELECT id FROM public.menu WHERE menu_name = 'Seasonal Menu' LIMIT 1), 'Apple Cider Donuts', 'Freshly baked apple cider donuts', 4.99, now(), NULL, TRUE),
  (uuid_generate_v4(), (SELECT id FROM public.menu WHERE menu_name = 'Seasonal Menu' LIMIT 1), 'Gingerbread Cookies', 'Spiced gingerbread cookies', 3.99, now(), NULL, TRUE),
  (uuid_generate_v4(), (SELECT id FROM public.menu WHERE menu_name = 'Seasonal Menu' LIMIT 1), 'Eggnog Milkshake', 'Creamy milkshake with eggnog flavor', 6.49, now(), NULL, TRUE);

INSERT INTO public.queue (id, shop_id, queue_name, capacity, created_at, modified_at, active)
VALUES
  (uuid_generate_v4(), (SELECT id FROM public.coffeeshop WHERE shop_name = 'Bean Brew' LIMIT 1), 'Zeus', 10, now(), NULL, TRUE),
  (uuid_generate_v4(), (SELECT id FROM public.coffeeshop WHERE shop_name = 'Bean Brew' LIMIT 1), 'Poseidon', 20, now(), NULL, FALSE),
  (uuid_generate_v4(), (SELECT id FROM public.coffeeshop WHERE shop_name = 'The Coffee Spot' LIMIT 1), 'Athena', 25, now(), NULL, TRUE),
  (uuid_generate_v4(), (SELECT id FROM public.coffeeshop WHERE shop_name = 'The Coffee Spot' LIMIT 1), 'Hades', 5, now(), NULL, TRUE),
  (uuid_generate_v4(), (SELECT id FROM public.coffeeshop WHERE shop_name = 'Cafe Delights' LIMIT 1), 'Oceanus', 10, now(), NULL, FALSE);