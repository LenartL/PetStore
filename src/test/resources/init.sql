INSERT INTO public.pets (pet_id, name, status) VALUES (1, 'Cat', 'AVAILABLE');
INSERT INTO public.pets (pet_id, name, status) VALUES (2, 'Dog', 'AVAILABLE');
INSERT INTO public.pets (pet_id, name, status) VALUES (3, 'Snake', 'NOT_AVAILABLE');
INSERT INTO public.pets (pet_id, name, status) VALUES (4, 'Tiger', 'NOT_AVAILABLE');
INSERT INTO public.pets (pet_id, name, status) VALUES (5, 'Capibara', 'AVAILABLE');

INSERT INTO public.orders (complete, quantity, ship_date, status, pet_id) VALUES (true, 1, '2024-11-29 10:37:25', 'DELIVERED', 1);