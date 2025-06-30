INSERT INTO payment (
    id,
    booking_id,
    amount,
    payment_method,
    billing_unit,
    billing_rate
) VALUES
  (
    '1',
    '1',
    5.00,
    'credit_card',
    'per_hour',
    5.00
  ),
  (
    '2',
    '2',
    7.50,
    'paypal',
    'per_kilometer',
    7.50
  );
