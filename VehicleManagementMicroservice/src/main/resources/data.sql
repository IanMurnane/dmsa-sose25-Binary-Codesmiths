INSERT INTO vehicles (provider_id, type, model, price_per_hour, billing_model, status, location, last_updated)
VALUES
(101, 'scooter', 'Xiaomi M365', 2.50, 'time-based', 'available', 'Berlin', NOW()),
(102, 'bike', 'VanMoof S3', 1.80, 'time-based', 'in_use', 'Hamburg', NOW()),
(103, 'car', 'Tesla Model 3', 15.00, 'time-based', 'maintenance', 'Munich', NOW());
