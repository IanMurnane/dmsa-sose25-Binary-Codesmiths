INSERT INTO payment (
    id,
    booking_id,
    user_id,
    amount,
    payment_method,
    billing_unit,
    billing_rate
) VALUES
  (
    '002',
    1,
    1,
    2.50,
    'credit_card',
    'per_hour',
    2.50
  ),
  (
    '001',
    2,
    2,
    4.00,
    'paypal',
    'per_kilometer',
    4.00
  );
