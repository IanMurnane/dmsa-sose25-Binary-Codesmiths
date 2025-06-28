INSERT INTO vehicles (
id,
provider_id,
type,
model,
price_per_hour,
billing_model,
status,
location,
last_updated
) VALUES
(1, 1011, 'car', 'Suzuki Swift', 2.51, 'time-based', 'available', '51.491050 7.401587', TIMESTAMP '2025-05-20 12:00:00'),
(2, 1011, 'scooter', 'Segway-Ninebot E2', 2.51, 'time-based', 'available', '51.492050 7.402587', TIMESTAMP '2025-05-20 12:00:00'),
(3, 1011, 'bike', 'Lime Bicycle', 2.51, 'time-based', 'available', '51.501050 7.411587', TIMESTAMP '2025-05-20 12:00:00');
