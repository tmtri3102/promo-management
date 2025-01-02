create
    definer = root@localhost procedure countCustomerByProvince()
begin
    SELECT
        p.id,
        p.name,
        COUNT(*) AS customer_count
    FROM
        provinces p
            LEFT JOIN
        customers c ON p.id = c.province_id
    GROUP BY
        p.id;
end;
