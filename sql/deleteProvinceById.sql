create
    definer = root@localhost procedure deleteProvinceById(IN _id bigint)
begin
    update customers
    set province_id = null
    where province_id = _id;
    delete  from provinces where id = _id;
end;
