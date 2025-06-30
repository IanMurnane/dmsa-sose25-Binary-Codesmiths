INSERT INTO payments (
    id,
    bookingId,
    amount,
    paymentMethod,
    billingUnit,
    billingRate
) VALUES
  (
    '100',
    '1',
    5.00,
    'credit_card',
    'per_hour',
    5.00
  ),
  (
    '101',
    '2',
    7.50,
    'paypal',
    'per_kilometer',
    7.50
  );
